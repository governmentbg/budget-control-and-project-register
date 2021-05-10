package bg.infosys.daeu.ws.pub.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "public_budget_form_data")
public class PublicBudgetFormData {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "subject_name")
	private String subjectName;
	public static final String _subjectName = "subjectName";

	@Column(name = "appr_date")
	private String apprDate;
	public static final String _apprDate = "apprDate";
	
	@Column(name = "date_created")
	private String dateCreated;
	public static final String _dateCreated = "dateCreated";
	
	@Column(name = _status)
	private String status;
	public static final String _status = "status";
	
	@Column(name = "form_type")
	private String formType;
	public static final String _formType = "formType";
	
	@Column(name = "budget_form_type")
	private String budgetFormType;
	public static final String _budgetFormType = "budgetFormType";
	
	@OneToMany(mappedBy = "publicBudgetFormData", fetch = FetchType.EAGER)
	private Set<PublicBudgetFormDataDetail> publicBudgetFormDataDetails;
	public static final String _publicBudgetFormDataDetails = "publicBudgetFormDataDetails";
	
	@OneToMany(mappedBy = "publicBudgetFormData", fetch = FetchType.EAGER)
	private Set<PublicBudgetFormAdditionalData> publicBudgetFormAdditionalData;
	public static final String _publicBudgetFormAdditionalData = "publicBudgetFormAdditionalData";
	
	public PublicBudgetFormData() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getApprDate() {
		return apprDate;
	}

	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
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

	public Set<PublicBudgetFormDataDetail> getPublicBudgetFormDataDetails() {
		return publicBudgetFormDataDetails;
	}

	public void setPublicBudgetFormDataDetails(Set<PublicBudgetFormDataDetail> publicBudgetFormDataDetails) {
		this.publicBudgetFormDataDetails = publicBudgetFormDataDetails;
	}

	public Set<PublicBudgetFormAdditionalData> getPublicBudgetFormAdditionalData() {
		return publicBudgetFormAdditionalData;
	}

	public void setPublicBudgetFormAdditionalData(Set<PublicBudgetFormAdditionalData> publicBudgetFormAdditionalData) {
		this.publicBudgetFormAdditionalData = publicBudgetFormAdditionalData;
	}
}
