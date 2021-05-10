package bg.infosys.daeu.db.entity.budgets;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;

@Audited
@Entity
@Table(schema = "budgets", name = "budget_forms")
public class BudgetForm {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public static final String _id = "id";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_type_id")
	private FormType formType;
	public static final String _formType = "formType";
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "last_version_id")
	private BudgetFormVersion budgetFormVersion;
	public static final String _budgetFormVersion = "budgetFormVersion";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_version_id")
	private SubjectVersion subjectVersion;
	public static final String _subjectVersion = "subjectVersion";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
	public static final String _status = "status";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "budget_form_type_code")
	private BudgetFormType budgetFormType;
	public static final String _budgetFormType = "budgetFormType";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sum_budget_form_type_code")
	private SumBudgetFormType sumBudgetFormType;
	public static final String _sumBudgetFormType = "sumBudgetFormType";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "final_status_id")
	private Status finalStatus;
	public static final String _finalStatus = "finalStatus";
	
	@Column(name = "start_year")
	private Integer startYear;
	public static final String _startYear="startYear";
	
	@Column(name = "public_id")
	private Integer publicId;
	public static final String _publicId = "publicId";

	@Column(name = "last_update", columnDefinition = "TIMESTAMP")
	private Date lastUpdate;
	public static final String _lastUpdate = "lastUpdate";
	
	@Transient
	private List<VersionRow> deletedRows = new ArrayList<>();
	
	public BudgetForm() {
		this.budgetFormVersion = new BudgetFormVersion();
		this.budgetFormVersion.setBudgetForm(this);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FormType getFormType() {
		return formType;
	}

	public void setFormType(FormType formType) {
		this.formType = formType;
	}

	public BudgetFormVersion getBudgetFormVersion() {
		return budgetFormVersion;
	}

	public void setBudgetFormVersion(BudgetFormVersion budgetFormVersion) {
		this.budgetFormVersion = budgetFormVersion;
	}

	public SubjectVersion getSubjectVersion() {
		return subjectVersion;
	}

	public void setSubjectVersion(SubjectVersion subjectVersion) {
		this.subjectVersion = subjectVersion;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BudgetFormType getBudgetFormType() {
		return budgetFormType;
	}

	public void setBudgetFormType(BudgetFormType budgetFormType) {
		this.budgetFormType = budgetFormType;
	}

	public SumBudgetFormType getSumBudgetFormType() {
		return sumBudgetFormType;
	}

	public void setSumBudgetFormType(SumBudgetFormType sumBudgetFormType) {
		this.sumBudgetFormType = sumBudgetFormType;
	}

	public Status getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(Status finalStatus) {
		this.finalStatus = finalStatus;
	}

	public Integer getStartYear() {
		return startYear;
	}

	public void setStartYear(Integer startYear) {
		this.startYear = startYear;
	}

	public Integer getPublicId() {
		return publicId;
	}

	public void setPublicId(Integer publicId) {
		this.publicId = publicId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<VersionRow> getDeletedRows() {
		return deletedRows;
	}

	public void setDeletedRows(List<VersionRow> deletedRows) {
		this.deletedRows = deletedRows;
	}
	
}
