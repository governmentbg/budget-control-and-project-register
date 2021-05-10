package bg.infosys.daeu.ws.pub.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bg.infosys.daeu.db.dto.PublicAttachedDoc;
import bg.infosys.daeu.db.dto.PublicBudgetAdditionalField;
import bg.infosys.daeu.db.dto.PublicDetail;
import bg.infosys.daeu.db.dto.PublicTechSpecsDetail;
import bg.infosys.daeu.ws.pub.entity.AttachedDoc;
import bg.infosys.daeu.ws.pub.entity.BudgetYear;
import bg.infosys.daeu.ws.pub.entity.PublicBudgetFormAdditionalData;
import bg.infosys.daeu.ws.pub.entity.PublicBudgetFormData;
import bg.infosys.daeu.ws.pub.entity.PublicBudgetFormDataDetail;
import bg.infosys.daeu.ws.pub.entity.PublicProjectData;
import bg.infosys.daeu.ws.pub.entity.PublicProjectDataDetail;
import bg.infosys.daeu.ws.pub.entity.PublicTechSpecData;
import bg.infosys.daeu.ws.pub.entity.PublicTechSpecDataDetail;
import bg.infosys.daeu.ws.pub.json.BudgetDetailJSON;
import bg.infosys.daeu.ws.pub.json.BudgetYearJSON;
import bg.infosys.daeu.ws.pub.json.PublicBudgetFormDataJSON;
import bg.infosys.daeu.ws.pub.json.PublicProjectDataJSON;
import bg.infosys.daeu.ws.pub.json.PublicTechSpecDataJSON;
import bg.infosys.daeu.ws.services.PublicInfoService;

@Component
public class PublicDataUtil {
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@Autowired PublicInfoService publicInfoService;
	
	public PublicBudgetFormData parseBudgetData(PublicBudgetFormDataJSON json) {
		PublicBudgetFormData data = new PublicBudgetFormData();
		if (json != null) {
			data.setSubjectName(json.getSubjectName());
			data.setDateCreated(DATE_FORMAT.format(new Date()));
			data.setApprDate(json.getApprDate());
			data.setFormType(json.getFormType());
			data.setBudgetFormType(json.getBudgetFormType());
			data.setStatus(json.getStatus());
		}
		return data;
	}
	
	public PublicProjectData parseProject(PublicProjectDataJSON json) {
		PublicProjectData data = new PublicProjectData();
		if (json != null) {
			data.setSubjectName(json.getSubjectName());
			data.setProjectName(json.getProjectName());
			data.setDateCreated(DATE_FORMAT.format(new Date()));
			data.setApprDate(json.getApprDate());
			data.setFormType(json.getFormType());
			data.setStatus(json.getStatus());
			data.setExecStatus(json.getExecStatus());
		}
		return data;
	}

	public PublicTechSpecData parseTechSpec(PublicTechSpecDataJSON json) {
		PublicTechSpecData data = new PublicTechSpecData();
		if (json != null) {
			data.setSubjectName(json.getSubjectName());
			data.setDateCreated(DATE_FORMAT.format(new Date()));
			data.setApprDate(json.getApprDate());
			data.setStatus(json.getStatus());
		}
		return data;
	}
	
	public PublicBudgetFormData syncBudgetData(PublicBudgetFormData entity, PublicBudgetFormDataJSON json) {
		if (entity != null) {
			entity.setSubjectName(json.getSubjectName());
			entity.setDateCreated(DATE_FORMAT.format(new Date()));
			entity.setApprDate(json.getApprDate());
			entity.setFormType(json.getFormType());
			entity.setBudgetFormType(json.getBudgetFormType());
			entity.setStatus(json.getStatus());
		}
		return entity;
	}
	
	public PublicProjectData syncProject(PublicProjectData entity, PublicProjectDataJSON json) {
		if (entity != null) {
			entity.setSubjectName(json.getSubjectName());
			entity.setProjectName(json.getProjectName());
			entity.setDateCreated(DATE_FORMAT.format(new Date()));
			entity.setApprDate(json.getApprDate());
			entity.setFormType(json.getFormType());
			entity.setStatus(json.getStatus());
			entity.setExecStatus(json.getExecStatus());
		}
		return entity;
	}

	public PublicTechSpecData syncTechSpec(PublicTechSpecData entity, PublicTechSpecDataJSON json) {
		if (entity != null) {
			entity.setSubjectName(json.getSubjectName());
			entity.setDateCreated(DATE_FORMAT.format(new Date()));
			entity.setApprDate(json.getApprDate());
			entity.setStatus(json.getStatus());
		}
		return entity;
	}
	
	public void saveAdditionalBudgetFormData(PublicBudgetFormDataJSON json, PublicBudgetFormData data) {
		for (BudgetDetailJSON detail : json.getDetails()) {
			PublicBudgetFormDataDetail newDet = new PublicBudgetFormDataDetail(detail.getRegId(),
					detail.getCpvCode(), detail.getCpvName(), detail.getEbkCode(), detail.getChange(), data);
			for (BudgetYearJSON year : detail.getYears()) {
				BudgetYear newYear = new BudgetYear(year.getRegId(), year.getYear(), year.getUnits(), year.getTotal(), newDet);
				newDet.getYears().add(newYear);
			}
			publicInfoService.savePublicDetail(newDet);
		}
		
		for (PublicBudgetAdditionalField field : json.getAdditionalFields()) {
			publicInfoService.saveBudgetAddField(new PublicBudgetFormAdditionalData(field.getName(), field.getValue(), field.getRowType(), data));
		}
	}
	
	public void saveAdditionalProjectData(PublicProjectDataJSON json, PublicProjectData data) {
		List<PublicProjectDataDetail> currDetails = publicInfoService.findProjectDetailsById(json.getPublicId());
		for (PublicDetail pubData : json.getAdditionalFields()) {
			if (!checkExistingProjectDetail(pubData, currDetails)) {
				publicInfoService.savePublicDetail(new PublicProjectDataDetail(pubData.getName(), pubData.getValue(), pubData.getExecId(), pubData.getRowType(), data));
			}
		}
	}
	
	public void saveAttachedDocs(List<PublicAttachedDoc> attachedDocs, Integer entityId, String module, String fileType) {
		if (attachedDocs != null) {
			for (PublicAttachedDoc doc : attachedDocs) {
				String encodedData = Base64.getEncoder().encodeToString(doc.getData());
				publicInfoService.saveAttachedDoc(new AttachedDoc(doc.getFileName(), doc.getContentType(), encodedData, entityId, module, fileType));
			}
		}
	}
	
	public void saveAdditionalTechSpecsData(PublicTechSpecDataJSON json, PublicTechSpecData data) {
		List<PublicTechSpecDataDetail> currDetails = publicInfoService.findTechSpecDetailsById(json.getPublicId());
		for (PublicTechSpecsDetail pubData : json.getAdditionalFields()) {
			if (!checkExistingTechSpecsDetail(pubData, currDetails)) {
				publicInfoService.savePublicDetail(new PublicTechSpecDataDetail(pubData.getName(), pubData.getValue(), pubData.getRowType(), data));
			}
		}
	}
	
	public void syncAdditionalBudgetFormData(PublicBudgetFormDataJSON json, Set<PublicBudgetFormDataDetail> currDetails) {
		for (PublicBudgetFormDataDetail detail : currDetails) {
			BudgetDetailJSON jsonForDetail = json.getDetails().stream().filter(j -> j.getRegId().equals(detail.getRegId())).findFirst().orElse(null);
			if (jsonForDetail != null) {
				for (BudgetYear year : detail.getYears()) {
					BudgetYearJSON jsonForYear = jsonForDetail.getYears().stream().filter(j -> j.getRegId().equals(year.getRegId())).findFirst().orElse(null);
					if (jsonForYear != null) {
						year.setUnits(jsonForYear.getUnits());
						year.setTotal(jsonForYear.getTotal());
						publicInfoService.saveBudgetYear(year);
					}
				}
			}
		}
	}
	
	public void syncAdditionalBudgetFormFields(PublicBudgetFormDataJSON json, PublicBudgetFormData data) {
		List<PublicBudgetFormAdditionalData> currAddData = publicInfoService.findBudgetFormAddFieldsById(json.getPublicId());
		for (PublicBudgetAdditionalField field : json.getAdditionalFields()) {
			if (!checkExistingBudgetAdditionalField(field, currAddData)) {
				publicInfoService.saveBudgetAddField(new PublicBudgetFormAdditionalData(field.getName(), field.getValue(), field.getRowType(), data));
			}
		}
	}
	
	private boolean checkExistingBudgetAdditionalField(PublicBudgetAdditionalField pubData, List<PublicBudgetFormAdditionalData> currDetails) {
		for (PublicBudgetFormAdditionalData detail : currDetails) {
			if (detail.getName().equals(pubData.getName())) {
				detail.setValue(pubData.getValue());
				publicInfoService.saveBudgetAddField(detail);
				return true;
			}
		}
		return false;
	}
	
	private boolean checkExistingProjectDetail(PublicDetail pubData, List<PublicProjectDataDetail> currDetails) {
		for (PublicProjectDataDetail detail : currDetails) {
			if ( ( detail.getExecId() == null && detail.getName().equals(pubData.getName()) ) ||
					( detail.getExecId() != null && detail.getExecId().equals(pubData.getExecId()) && detail.getName().equals(pubData.getName()) ) ) {
				detail.setValue(pubData.getValue());
				publicInfoService.savePublicDetail(detail);
				return true;
			}
		}
		return false;
	}
	
	private boolean checkExistingTechSpecsDetail(PublicTechSpecsDetail pubData, List<PublicTechSpecDataDetail> currDetails) {
		for (PublicTechSpecDataDetail detail : currDetails) {
			if (detail.getName().equals(pubData.getName())) {
				detail.setValue(pubData.getValue());
				publicInfoService.savePublicDetail(detail);
				return true;
			}
		}
		return false;
	}
}