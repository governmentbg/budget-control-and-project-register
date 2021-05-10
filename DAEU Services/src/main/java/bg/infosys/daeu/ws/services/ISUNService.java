package bg.infosys.daeu.ws.services;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.databind.ObjectMapper;

import bg.infosys.common.ws.utils.HttpRequest;
import bg.infosys.daeu.db.dao.projects.ProjectBudgetTypeDAO;
import bg.infosys.daeu.db.dao.projects.ProjectDAO;
import bg.infosys.daeu.db.dao.projects.ProjectFormConfigDAO;
import bg.infosys.daeu.db.dao.pub.FormTypeDAO;
import bg.infosys.daeu.db.dao.pub.FundingDAO;
import bg.infosys.daeu.db.dao.pub.IndicatorDAO;
import bg.infosys.daeu.db.dao.pub.StatusDAO;
import bg.infosys.daeu.db.dao.pub.SubjectDAO;
import bg.infosys.daeu.db.entity.projects.AdditionalData;
import bg.infosys.daeu.db.entity.projects.AdditionalDataValue;
import bg.infosys.daeu.db.entity.projects.ExecutionData;
import bg.infosys.daeu.db.entity.projects.ExecutionValue;
import bg.infosys.daeu.db.entity.projects.IndicatorValue;
import bg.infosys.daeu.db.entity.projects.IsunIndicatorValue;
import bg.infosys.daeu.db.entity.projects.Project;
import bg.infosys.daeu.db.entity.projects.ProjectBudget;
import bg.infosys.daeu.db.entity.projects.ProjectBudgetType;
import bg.infosys.daeu.db.entity.projects.ProjectFormConfig;
import bg.infosys.daeu.db.entity.projects.ProjectMainData;
import bg.infosys.daeu.db.entity.projects.ProjectMainDataValue;
import bg.infosys.daeu.db.entity.projects.ProjectVersion;
import bg.infosys.daeu.db.entity.pub.ChecklistRelation;
import bg.infosys.daeu.db.entity.pub.FormType.FormTypeConst;
import bg.infosys.daeu.db.entity.pub.Indicator;
import bg.infosys.daeu.db.entity.pub.MailTemplate.MailTemplateConst;
import bg.infosys.daeu.db.entity.pub.ModuleType.ModuleTypeConst;
import bg.infosys.daeu.db.entity.pub.Status.StatusConst;
import bg.infosys.daeu.db.entity.pub.Subject;
import bg.infosys.daeu.ws.dto.ChecklistInfoDto;
import bg.infosys.daeu.ws.exceptions.ISUNerrorException;
import bg.infosys.daeu.ws.pub.json.ISUNDecisionJSON;
import bg.infosys.daeu.ws.pub.json.ISUNIndicatorJSON;
import bg.infosys.daeu.ws.pub.json.ISUNProjectActivityJSON;
import bg.infosys.daeu.ws.pub.json.ISUNProjectJSON;
import bg.infosys.daeu.ws.pub.json.ISUNRegisteredDatabasesInfoJSON;
import bg.infosys.daeu.ws.pub.json.ISUNeGovIndicatorJSON;
import bg.infosys.daeu.ws.pub.response.ISUNInternalResponse;
import bg.infosys.daeu.ws.pub.response.ISUNResponse;
import bg.infosys.daeu.ws.pub.response.ISUNTokenResponse;
import bg.infosys.daeu.ws.pub.util.TagUtil;

@Service
public class ISUNService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ISUNService.class);
	private static final String TOKEN_URL = "https://umis2020.government.bg/api/token";
	private static final String DECISION_URL = "https://umis2020.government.bg/api/eGovernmentStandpoint";
	
	private static final String ROLE_REGISTRY = "ROLE_L_REGISTRY_OFFICE";
	
	@Autowired private ProjectDAO projectDAO;
	@Autowired private ProjectFormConfigDAO projectFormConfigDAO;
	@Autowired private StatusDAO statusDAO;
	@Autowired private SubjectDAO subjectDAO;
	@Autowired private IndicatorDAO indicatorDAO;
	@Autowired private ProjectBudgetTypeDAO projectBudgetTypeDAO;
	@Autowired private FormTypeDAO formTypeDAO;
	@Autowired private FundingDAO fundingDAO; 
	
	@Autowired private ChecklistService checklistService;
	@Autowired private TimestampService timestampService;
	@Autowired private MailingService mailingService;
	@Autowired private NotificationService notificationService;
	
	@Transactional
	public Integer createNewProject(ISUNProjectJSON projectJSON) throws IllegalArgumentException, IllegalAccessException, ISUNerrorException {
		Subject subject = subjectDAO.findByEIK(projectJSON.getCandidateUin());
		
		if (subject == null) {
			throw new ISUNerrorException(ISUNResponse.ReponseMessage.NОТ_REQUIRED);
		}
		
		Project project = projectDAO.getByGuid(projectJSON.getGuid());
		
		if (project == null) {
			project = new Project();

			project.setFormType(formTypeDAO.getBudgetControlFormType(FormTypeConst.PROJECT.getCode()));
			project.setGuid(projectJSON.getGuid());
			project.setIsunMajorVersion(projectJSON.getMajorVersion());
			
			project.setStatus(statusDAO.findByCodeAndType(StatusConst.SENT.name(), ModuleTypeConst.PROJECTS.getCode(), false));
			project.setSubjectVersion(subject.getLastVersion());
			project.setDateCreated(new Date());
		} else if (!project.getStatus().equals(statusDAO.findByCodeAndType(StatusConst.RETURNED.name(), ModuleTypeConst.PROJECTS.getCode(), false))) {
			throw new ISUNerrorException(ISUNResponse.ReponseMessage.NОТ_RETURNED);
		}
		
		ProjectVersion version = new ProjectVersion();
	
		try {
			version.setCreatedAtTimestamp(timestampService.generateToken("isun-entry"));
		} catch (Exception e) { }
		
		List<Indicator> indicators = indicatorDAO.findAllISUNindicators();
		
		version.setSubjectOutgoingDate(new Date());
		version.setSubjectOutgoingNum("isun-" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		version.setDateCreated(new Date());
		version.setIsunMinorVersion(projectJSON.getMinorVersion());
		
		version.setProjectBudget(createProjectBudget(projectJSON.getTotalProjectCost(), projectBudgetTypeDAO.findByCode("BUDGET"), version));
		version.setFunding(fundingDAO.findByName("по ЕСИФ"));
		
		List<ProjectFormConfig> configs = projectFormConfigDAO.findAllISUNFields();
		
		version.setProjectMainData(createMainData(projectJSON, configs, version, indicators));
		version.setProject(project);
		version.setIsunLink(projectJSON.getLinkForProjectPreview());
		version.seteService(projectJSON.getProvidesDevelopmentOrUpgrade() ? "Y" : "N");
		project.setIsunMajorVersion(projectJSON.getMajorVersion());
		project.setProjectVersion(version);
		
		ChecklistInfoDto info = checklistService.createParentChecklistAndRelations(formTypeDAO.getBudgetControlFormType(FormTypeConst.PROJECT.getCode()), false);
		
		version.setChecklist(info.getParentChecklist());
		
		List<ChecklistRelation> relations = info.getRelations();
		
		if (relations != null && !relations.isEmpty()) {
			for (ChecklistRelation checklistRelation : relations) {
				checklistService.saveChecklistRelation(checklistRelation);
			}
		}
		
		Integer id = projectDAO.save(project);
		
		Map<String, Object> tags = TagUtil.createEmailReplaceStrings(project.getSubjectVersion().getFullName(), project.getProjectVersion().getSubjectOutgoingNum(), project.getProjectVersion().getSubjectOutgoingDate());
		mailingService.generateMultipleMails(MailTemplateConst.PROJECTS_RECEIVED_PROPOSAL, ROLE_REGISTRY, tags);
	
		notificationService.sendNotificationsToAuth(ROLE_REGISTRY, MailTemplateConst.PROJECTS_RECEIVED_PROPOSAL, tags);
		
		return id;
	}
	
	public String getAccessToken(String guid) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		headers.put("Accept",       "application/json");

		HttpRequest request = new HttpRequest(headers, 10000, 10000);

		MultiValueMap<String, String> data= new LinkedMultiValueMap<String, String>();
		data.add("client_id", "daeu_client");
		data.add("client_secret", "6c7643e5-1368-4163-b10e-4189875a1c87");
		data.add("grant_type", "client_credentials");
		if (guid == null) {
			data.add("scope", "external:daeu_service");
		} else {
			data.add("scope", "external:daeu_service;project:preview:" + guid);
		}
		
		ResponseEntity<ISUNTokenResponse> response = null;
		try {
			response = request.doPost(data, TOKEN_URL, ISUNTokenResponse.class);
		} catch (HttpStatusCodeException e) { 
			LOGGER.error("Request URI: " + TOKEN_URL + "; Error on request", e);
			return null;
		} catch (Throwable t) {
			LOGGER.error("Unexpected error. ", t);
			return null;
		}
		
		return "Bearer" + " " + response.getBody().getAccessToken();
	}
	
	public ResponseEntity<ISUNInternalResponse> sendDecision(ISUNDecisionJSON data, String token) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", token);

		HttpRequest request = new HttpRequest(headers, 10000, 10000);
		
		ResponseEntity<ISUNInternalResponse> response = null;
		try {
			response = request.doPost(data, DECISION_URL, ISUNInternalResponse.class);
		} catch (HttpStatusCodeException e) { 
			ISUNInternalResponse requestData = null;
			
			try {
				requestData = objectMapper.readValue(e.getResponseBodyAsString(), ISUNInternalResponse.class);
			} catch (IOException e1) { }
			
			response = new ResponseEntity<ISUNInternalResponse>(requestData, e.getStatusCode());
			
			if (!e.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				LOGGER.error("Request URI: " + DECISION_URL + "; Error on request", e);
			}
			
			return response;
		} catch (Throwable t) {
			LOGGER.error("Unexpected error. ", t);
		}
		
		return response;
	}
	
	private SortedSet<ProjectBudget> createProjectBudget(BigDecimal cost, ProjectBudgetType type, ProjectVersion version) {
		SortedSet<ProjectBudget> result = new TreeSet<ProjectBudget>();
		
		if (type == null) {
			return null;
		}
		
		ProjectBudget value = new ProjectBudget();
		value.setProjectVersion(version);
		value.setProjectBudgetType(type);
		value.setValue(cost);
		
		result.add(value);
		
		return result;
	}
	
	private ProjectMainData createMainData(ISUNProjectJSON projectJSON, List<ProjectFormConfig> configs, 
			ProjectVersion version, List<Indicator> indicators) throws IllegalArgumentException, IllegalAccessException {
		ProjectMainData result = new ProjectMainData();
		
		result.setProjectVersion(version);
		
		for (Field field : projectJSON.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			
			for (ProjectFormConfig config : configs.stream().filter(config -> config.getStepNum() == 1 || config.getStepNum() == 2)
					.collect(Collectors.toList())) {
				
				if (config.getIsunKey() == null) {
					continue;
				}
				
				if (field.getName().equals(config.getIsunKey())) {
					ProjectMainDataValue value = new ProjectMainDataValue();
					value.setProjectFormConfig(config);
					value.setProjectMainData(result);
					value.setValue(getFieldValue(field, projectJSON));
					
					result.getMainDataValues().add(value);
							
					break;
				}
			}
		}
		
		for (Field field : projectJSON.getIndicativeBudget().getClass().getDeclaredFields()) {
			field.setAccessible(true);
			
			for (ProjectFormConfig config : configs.stream().filter(config -> config.getStepNum() == 1 || config.getStepNum() == 2)
					.collect(Collectors.toList())) {
				
				if (config.getIsunKey() == null) {
					continue;
				}
				
				if (field.getName().equals(config.getIsunKey())) {
					ProjectMainDataValue value = new ProjectMainDataValue();
					value.setProjectFormConfig(config);
					value.setProjectMainData(result);
					value.setValue(getFieldValue(field, projectJSON.getIndicativeBudget()));
					
					result.getMainDataValues().add(value);
							
					break;
				}
			}
		}
		
		result.setExecutionDatas(createExecutionDatas(projectJSON.getActivities(), projectJSON.getInformationSystemsRegistersDatabases(), configs, result));
		result.setIsunIndicatorValues(createISUNIndicators(projectJSON.getIndicators(), result));
		result.setIndicatorValues(createIndicatorsValues(projectJSON.geteGovernmentIndicators(), result, indicators));
		
		return result;
	
	}

	private LinkedHashSet<ExecutionData> createExecutionDatas(List<ISUNProjectActivityJSON> activities, List<ISUNRegisteredDatabasesInfoJSON> additionaDatasJSON,List<ProjectFormConfig> configs, ProjectMainData mainData) 
			throws IllegalArgumentException, IllegalAccessException {
		LinkedHashSet<ExecutionData> result = new LinkedHashSet<ExecutionData>();
		
		int orderNum = 0;
		
		for (ISUNProjectActivityJSON activity : activities) {
			Optional<ISUNRegisteredDatabasesInfoJSON> additionaDataOptional = additionaDatasJSON.stream()
							  																	.filter(additionaData -> additionaData.getIndex().equals(activity.getIndex()))
							  																	.findAny();
			
			Field[] fields = activity.getClass().getDeclaredFields();
			
			ExecutionData data = new ExecutionData();
			data.setOrderNum(orderNum);
			data.setProjectMainData(mainData);
			data.setAdditionalData(createAdditionalData(additionaDataOptional, data, configs.stream().filter(config -> config.getStepNum() == 4).collect(Collectors.toList())));
			orderNum++;
			
			for (Field field : fields) {
				field.setAccessible(true);
				
				for (ProjectFormConfig config : configs.stream().filter(config -> config.getStepNum() == 3).collect(Collectors.toList())) {
					
					if (config.getIsunKey() == null) {
						continue;
					}
					
					if (field.getName().equals(config.getIsunKey())) {
						
						ExecutionValue value = new ExecutionValue();
						value.setExecutionData(data);
						value.setProjectFormConfig(config);
						value.setValue(getFieldValue(field, activity));
						
						data.getExecutionValues().add(value);
						break;
					}
				}
			}
			result.add(data);
		}
		
		return result;
	}
	
	private AdditionalData createAdditionalData(Optional<ISUNRegisteredDatabasesInfoJSON> optionalInfoJSON, ExecutionData executionData, List<ProjectFormConfig> configs) 
			throws IllegalArgumentException, IllegalAccessException {
		AdditionalData result = new AdditionalData();
		result.setExecutionData(executionData);
		
		if (optionalInfoJSON.isPresent()) {
			ISUNRegisteredDatabasesInfoJSON infoJSON = optionalInfoJSON.get();
			
			Field[] fields = infoJSON.getClass().getDeclaredFields();
			
			for (Field field : fields) {
				field.setAccessible(true);
				
				for (ProjectFormConfig config : configs) {
					
					if (config.getIsunKey() == null) {
						continue;
					}
					
					if (field.getName().equals(config.getIsunKey())) {
						AdditionalDataValue value = new AdditionalDataValue();
						value.setAdditionalData(result);
						value.setProjectFormConfig(config);
						value.setValue(getFieldValue(field, infoJSON));;
						
						result.getAdditionalDataValues().add(value);
						
						break;
					}
				}
			}
		} else {
			for (ProjectFormConfig config : configs) {
				if (config.getIsunKey() != null) {
					AdditionalDataValue value = new AdditionalDataValue();
					value.setAdditionalData(result);
					value.setProjectFormConfig(config);
					value.setValue(null);
					
					result.getAdditionalDataValues().add(value);
				}
			}
		}
		
		return result;
	}
	
	private Set<IsunIndicatorValue> createISUNIndicators(List<ISUNIndicatorJSON> indicatorsJSON, ProjectMainData mainData) {
		Set<IsunIndicatorValue> result = new HashSet<IsunIndicatorValue>();
		
		for (ISUNIndicatorJSON inicator : indicatorsJSON) {
			IsunIndicatorValue value = new IsunIndicatorValue();
			
			value.setName(inicator.getName());
			value.setTendency(inicator.getTrend());
			value.setUnit(inicator.getMeasure());
			value.setBaseValue(inicator.getBase());
			value.setTargetValue(inicator.getTarget());
			value.setProjectMainData(mainData);
		
			result.add(value);
		}
		
		return result;
	}
	
	
	private SortedSet<IndicatorValue> createIndicatorsValues(List<ISUNeGovIndicatorJSON> eGovernmentIndicators, ProjectMainData mainData, List<Indicator> indicators) {
		SortedSet<IndicatorValue> result = new TreeSet<IndicatorValue>();
		
		for (ISUNeGovIndicatorJSON indicatorJSON : eGovernmentIndicators) {
			IndicatorValue value = new IndicatorValue();
			value.setProjectMainData(mainData);
			value.setTargetValue(indicatorJSON.getTarget());
			if (indicatorJSON.getMeasure().equals("%")) {
				value.setValueType(IndicatorValue.ValueType.PERCENT);
			} else {
				value.setValueType(IndicatorValue.ValueType.NUM);
			}
			
			value.setIndicator(indicators.stream()
										 .filter(indicator -> indicator.getIsunKey().equals(indicatorJSON.getName()))
										 .findFirst().orElseGet(null));
			
			result.add(value);
		}
		
		return result;
	}
	
	private String getFieldValue(Field field, Object obj) throws IllegalArgumentException, IllegalAccessException {
		if (field.getType().equals(String.class)) {
			return (String)field.get(obj);
		} else if (field.getType().equals(Boolean.class)) {
			return (Boolean)field.get(obj) ? "Y" : "N";
		} else if(field.getType().equals(BigDecimal.class)) {
			return ((BigDecimal)field.get(obj)).toPlainString();
		} else if (field.getType().equals(Integer.class)) {
			return ((Integer)field.get(obj)).toString();
		}
		return null;
	}
	
	
}
