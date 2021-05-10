package bg.infosys.daeu.ws.integrations;


import bg.infosys.daeu.db.entity.pub.OrganizationType;
import bg.infosys.daeu.db.entity.pub.Subject;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;
import bg.infosys.daeu.db.entity.pub.OrganizationType.OrganizationTypeConst;
import bg.infosys.daeu.ws.services.IISDAServices;
import bg.infosys.daeu.ws.wsbg.iisda.misc.Calendar;
import bg.infosys.daeu.ws.wsbg.iisda.models.*;
import bg.infosys.daeu.ws.wsbg.iisda.models.enums.BatchStatusEnum;
import bg.infosys.daeu.ws.wsbg.iisda.services.IBatchInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.namespace.QName;
import javax.xml.ws.soap.AddressingFeature;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class IISDARequestBuilder {

//	public static final String ORGANIZATION_TYPE = "административен орган";
	private static final Logger logger = LoggerFactory.getLogger(IISDARequestBuilder.class);

	private final String url;
	private final String qNameLocation;
	private final String localPart;

	private IBatchInfoService sPort;

	@Autowired
	private IISDAServices iisdaServices;

	public IISDARequestBuilder() {
		this.url = "https://iisda.government.bg/Services/RAS/RAS.Integration.Host/BatchInfoService.svc?singleWsdl";
		this.qNameLocation = "http://iisda.government.bg/RAS/IntegrationServices";
		this.localPart = "BatchInfoService";
		this.sPort = init();
	}

	private IBatchInfoService init() {
		try {
			URL serviceUrl = new URL(this.url);
			QName qname = new QName(this.qNameLocation, this.localPart);
			javax.xml.ws.Service service = javax.xml.ws.Service.create(serviceUrl, qname);
			return service
					.getPort(IBatchInfoService.class, new AddressingFeature(true, true));

		} catch (MalformedURLException e) {
			logger.error("url to IISDA wsdl is broken : {} ", e.getMessage());
			return null;
		}
	}

	// calls remote service for overall batchIDinfo -- all Units are returned (filtered by active here)
	public List<BatchIdentificationInfoType> getListOfUnits() {
		logger.info("Getting Batch of Units");

		ArrayOfBatchIdentificationInfoType searchBatchesIdentificationInfo = sPort.searchBatchesIdentificationInfo(null, null, null, null, null, Calendar.toXMLGregorianCalendar(new Date()), null);
		List<BatchIdentificationInfoType> result = searchBatchesIdentificationInfo.getBatchIdentificationInfoType().stream().filter(batch -> batch.getStatus().equals(BatchStatusEnum.ACTIVE)).collect(Collectors.toList());

		return result.parallelStream().map(batchIdentificationInfoType -> {
			List<BatchType> batchInfo = getInfoForBatch(batchIdentificationInfoType.getIdentificationNumber());
			batchInfo.forEach(element -> {
				if (Objects.nonNull(element.getStewardWithMoneyBatch())) {
					result.stream()
					.filter(batch -> batch.getIdentificationNumber().equals(element.getStewardWithMoneyBatch().getRelatedBatch().getIdentificationNumber())) // get parent from current batch list
					.findFirst()
					.ifPresent(parent -> { // set parent-child relationships
						parent.addChild(batchIdentificationInfoType);
						batchIdentificationInfoType.setParent(parent);
					});
				}
			});

			return batchIdentificationInfoType;
		}).collect(Collectors.toList());
	}

	// calls remote service for DetailedInfo per batchID
	public List<BatchType> getInfoForBatch(String idNumber) {
//		logger.info("Getting Batch info for unit {}", idNumber);

		ArrayOfString batchIdentificationNumber = new ArrayOfString();
		batchIdentificationNumber.getString().add(idNumber);

		ArrayOfBatchType one = sPort.getBatchDetailedInfo(batchIdentificationNumber, Calendar.toXMLGregorianCalendar(new Date()), null);

		return one.getBatchType();
	}

	public Subject convertToSingleEntity(BatchIdentificationInfoType batchIdentificationInfoType, OrganizationType org) {

		String isValid = batchIdentificationInfoType.getStatus().equals(BatchStatusEnum.ACTIVE) ? "Y" : "N";

		Subject subject = new Subject();
		SubjectVersion subjectVersion = new SubjectVersion();
		subject.setOrgType(org);
		subject.setIsValid(isValid);
		subject.setLastVersion(subjectVersion);
		subject.setIdentNum(batchIdentificationInfoType.getUIC());
		subject.setAdminKind(batchIdentificationInfoType.getAdmStructureKind().toString());
		subject.setIisdaNumber(batchIdentificationInfoType.getIdentificationNumber());
		subjectVersion.setFullName(batchIdentificationInfoType.getName());
		subjectVersion.setSubject(subject);
		subjectVersion.setEmail("");

		return subject;
	}

	public List<Subject> processNewBatch(List<BatchIdentificationInfoType> toProcess) {
		OrganizationType org = iisdaServices.getOrganizationByCode(OrganizationTypeConst.AO.getCode());

		List<Subject> currentBatch = getCurrentBatch(org);
		List<Subject> newToProcess = new ArrayList<>();

		currentBatch.forEach(currentElement -> {
			Optional<BatchIdentificationInfoType> info = toProcess.stream().filter(e -> 
			(e.getUIC() != null && currentElement.getIdentNum() != null && e.getUIC().equals(currentElement.getIdentNum())) ||
			e.getIdentificationNumber().equals(currentElement.getIisdaNumber())).findAny();
			info.ifPresentOrElse(e -> { // if received element exist in current database

				Subject newElement = convertToSingleEntity(e, org);

				if (newElement.hasChanged(currentElement)) { // apply changes if any

					currentElement.setIsValid(newElement.getIsValid());
					currentElement.setIdentNum(newElement.getIdentNum());
					currentElement.setIisdaNumber(newElement.getIisdaNumber());
					currentElement.setAdminKind(newElement.getAdminKind());
					
				}
				if (Objects.nonNull(e.getParent())) {
					SubjectVersion parentVersion = currentBatch.stream().filter(ea -> ea.getLastVersion() != null && 
					(ea.getIdentNum() != null && e.getParent() != null && e.getParent().getUIC() != null && ea.getIdentNum().equals(e.getParent().getUIC()) ||
							ea.getIisdaNumber() != null && e.getParent() != null && e.getParent().getIdentificationNumber() != null && ea.getIisdaNumber().equals(e.getParent().getIdentificationNumber()))
							).map(Subject::getLastVersion).findFirst().orElse(null);

					if (Objects.isNull(parentVersion)) {
						currentElement.getLastVersion().setParent(convertToSingleEntity(e.getParent(), org).getLastVersion());
					} else {
						currentElement.getLastVersion().setParent(parentVersion);
					}
				}

				newToProcess.add(currentElement);
			}, () -> { // if database has an element that is not in the received list -> the received element should not be valid anymore
//				if (currentElement.getIsValid().equals("Y")) {
//					currentElement.setIsValid("N");
//					newToProcess.add(currentElement);
//				}
			});
		});
		toProcess.forEach(e -> { // any received elements that are not in current database, add as new
			Subject s = convertToSingleEntity(e, org);
			if (!currentBatch.contains(s)) {
				newToProcess.add(s);
			}
		});

		return newToProcess;

	}

	@Transactional
	public void saveToDB(List<Subject> converted) {
		logger.info("Started saving #{} of records to database", converted.size());
		converted.forEach(subject -> iisdaServices.saveSubject(subject));
	}

	public List<Subject> getCurrentBatch(OrganizationType orgType) {
		return iisdaServices.findAllSubjects(orgType);
	}

	public void setIBatchService(IBatchInfoService iBatchInfoService) {
		this.sPort = iBatchInfoService;
	}

	public void setIisdaServices(IISDAServices iisdaServices) {
		this.iisdaServices = iisdaServices;
	}

}