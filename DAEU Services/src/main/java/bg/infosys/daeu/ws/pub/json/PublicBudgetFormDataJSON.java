package bg.infosys.daeu.ws.pub.json;

import java.util.List;

import bg.infosys.daeu.db.dto.PublicBudgetAdditionalField;

public class PublicBudgetFormDataJSON {
	private Integer publicId;
	
	private String subjectName;
	private String dateCreated;
	private String apprDate;
	private String status;
	
	private String formType;
	private String budgetFormType;
	
	private List<BudgetDetailJSON> details;
	private List<PublicBudgetAdditionalField> additionalFields;

	public PublicBudgetFormDataJSON() {}

	public Integer getPublicId() {
		return publicId;
	}

	public void setPublicId(Integer publicId) {
		this.publicId = publicId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getApprDate() {
		return apprDate;
	}

	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getBudgetFormType() {
		return budgetFormType;
	}

	public void setBudgetFormType(String budgetFormType) {
		this.budgetFormType = budgetFormType;
	}

	public List<BudgetDetailJSON> getDetails() {
		return details;
	}

	public void setDetails(List<BudgetDetailJSON> details) {
		this.details = details;
	}

	public List<PublicBudgetAdditionalField> getAdditionalFields() {
		return additionalFields;
	}

	public void setAdditionalFields(List<PublicBudgetAdditionalField> additionalFields) {
		this.additionalFields = additionalFields;
	}
}
