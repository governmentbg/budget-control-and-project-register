package bg.infosys.daeu.db.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bg.infosys.common.utils.DateTimeUtil;
import bg.infosys.daeu.db.entity.pub.ChecklistType;
import bg.infosys.daeu.db.entity.pub.ExecutionStatus;
import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.db.entity.pub.ModuleType;
import bg.infosys.daeu.db.entity.pub.OrganizationType;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.pub.Subject;
import bg.infosys.daeu.db.entity.pub.TranslationLanguage;
import bg.infosys.daeu.db.entity.security.Authority;

public class SearchDTO {
	private String name;
	private String outgoingNumber;
	private Status status;
	private Date startPeriod;
	private Date endPeriod;
	private OrganizationType orgType;
	private String isValid;
	private String identNum;
	private FormType formType;
	private ChecklistType checklistType;
	private ModuleType moduleType;
	private Boolean enabled;
	private Authority authority;
	private Subject subject;
	private List<Subject> subjectList = new ArrayList<Subject>();
	// Projects
	private String type;
	private Integer page;
	
	private boolean isTotal;
	private Integer year;
	private TranslationLanguage language;
	// Reports
	private ExecutionStatus executionStatus;
	private List<Status> selectedStatuses = new ArrayList<Status>();
	private AppVisualizationDTO appVizualizationDto;
	private String keyWord;
	private List<Integer> selectedYears = new ArrayList<Integer>();
	
	public SearchDTO() {}
	
	public SearchDTO(Integer activePage) {
		this.page = activePage;
	}

	// If all types are clicked ('F7' and 'F8') --> clear "type" property.
	public String getFormattedTypeForProject() {
		if (this.type != null) {
			return this.type.length() == 2 ? this.type : null;
		} else {
			return null;
		}
	}
	
	// If all types are clicked ('F1' and 'F2') --> clear "type" property.
	public String getFormattedTypeForBudget() {
		if (this.type != null) {
			return this.type.length() == 2 ? this.type : null;
		} else {
			return null;
		}
	}
	
	/* Getters & Setters */
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setName(Subject subject) {
		this.name = subject.getLastVersion().getFullName();
	}

	public String getOutgoingNumber() {
		return outgoingNumber;
	}

	public void setOutgoingNumber(String outgoingNumber) {
		this.outgoingNumber = outgoingNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getStartPeriod() {
		return startPeriod;
	}

	public void setStartPeriod(Date startPeriod) {
		this.startPeriod = startPeriod;
	}

	public Date getEndPeriod() {
		return endPeriod;
	}

	public void setEndPeriod(Date endPeriod) {
		this.endPeriod = endPeriod;
	}

	public FormType getFormType() {
		return formType;
	}

	public void setFormType(FormType formType) {
		this.formType = formType;
	}

	public OrganizationType getOrgType() {
		return orgType;
	}

	public void setOrgType(OrganizationType orgType) {
		this.orgType = orgType;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getIdentNum() {
		return identNum;
	}

	public void setIdentNum(String identNum) {
		this.identNum = identNum;
	}

	public ChecklistType getChecklistType() {
		return checklistType;
	}

	public void setChecklistType(ChecklistType checklistType) {
		this.checklistType = checklistType;
	}

	public ModuleType getModuleType() {
		return moduleType;
	}

	public void setModuleType(ModuleType moduleType) {
		this.moduleType = moduleType;
	}

	public boolean isTotal() {
		return isTotal;
	}

	public void setTotal(boolean isTotal) {
		this.isTotal = isTotal;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public ExecutionStatus getExecutionStatus() {
		return executionStatus;
	}

	public void setExecutionStatus(ExecutionStatus executionStatus) {
		this.executionStatus = executionStatus;
	}
	
	public List<Status> getSelectedStatuses() {
		return selectedStatuses;
	}

	public void setSelectedStatuses(List<Status> selectedStatuses) {
		this.selectedStatuses = selectedStatuses;
	}
	
	public AppVisualizationDTO getAppVizualizationDto() {
		return appVizualizationDto;
	}

	public void setAppVizualizationDto(AppVisualizationDTO appVizualizationDto) {
		this.appVizualizationDto = appVizualizationDto;
	}
	
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public TranslationLanguage getLanguage() {
		return language;
	}

	public void setLanguage(TranslationLanguage language) {
		this.language = language;
	}
	
	public List<Integer> getSelectedYears() {
		return selectedYears;
	}

	public void setSelectedYears(List<Integer> selectedYears) {
		this.selectedYears = selectedYears;
	}

	public void formatDates() {
		if (startPeriod != null) startPeriod = DateTimeUtil.atStartOfDay(startPeriod);
		if (endPeriod != null) endPeriod = DateTimeUtil.atEndOfDay(endPeriod);
	}
}
