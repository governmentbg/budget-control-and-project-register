package bg.infosys.daeu.db.entity.budgets;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.Checklist;
import bg.infosys.daeu.db.entity.pub.Letter;
import bg.infosys.daeu.db.entity.security.User;

@Audited
@Entity
@Table(schema = "budgets", name = "budget_form_versions")
public class BudgetFormVersion {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public static final String _id="id";
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "budget_form_id")
	private BudgetForm budgetForm;
	public static final String _budgetForm = "budgetForm";
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "letter_id")
	private Letter letter;
	public static final String _letter = "letter";
	
	@Column(name = "date_created", columnDefinition = "TIMESTAMP")
	private Date dateCreated;
	public static final String _dateCreated = "dateCreated";
	
	@Column(name = "last_update", columnDefinition = "TIMESTAMP")
	private Date lastUpdate;
	public static final String _lastUpdate = "lastUpdate";
	
	@Column(name = "outgoing_num")
	private String outgoingNum;
	public static final String _outgoingNum = "outgoingNum";
	
	@Column(name = "outgoing_date", columnDefinition = "DATE")
	private Date outgoingDate;
	public static final String _outgoingDate = "outgoingDate";
	
	@Column(name = "incoming_num")
	private String incomingNum;
	public static final String _incomingNum = "incomingNum";
	
	@Column(name = "incoming_date", columnDefinition = "DATE")
	private Date incomingDate;
	public static final String _incomingDate = "incomingDate";
	
	@Column(name = "subject_outgoing_num")
	private String subjectOutgoingNum;
	public static final String _subjectOutgoingNum = "subjectOutgoingNum";
	
	@Column(name = "subject_outgoing_date", columnDefinition = "DATE")
	private Date subjectOutgoingDate;
	public static final String _subjectOutgoingDate = "subjectOutgoingDate";
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "checklist_id")
	private Checklist checklist;
	public static final String _checklist = "checklist";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	private User createdBy;
	public static final String _createdBy = "createdBy";
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id")
	private BudgetFormVersion parent;
	public static final String _parent = "parent";
	
	@Column(name = "created_at_timestamp")
	private String createdAtTimestamp;
	public static final String _createdAtTimestamp = "createdAtTimestamp";
	
	@Column(name = "returned_at_timestamp")
	private String returnedAtTimestamp;
	public static final String _returnedAtTimestamp = "returnedAtTimestamp";
	
	@Column(name = "signed_file_path")
	private String signedFilePath;
	public static final String _signedFilePath = "signedFilePath";
	
	@Where(clause = "is_valid = 'Y'")
	@OneToMany(mappedBy = "budgetFormVersion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VersionRow> versionRow;
	public static final String _versionRow = "versionRow";
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "budget_main_data_id")
	private BudgetMainData budgetMainData;
	public static final String _budgetMainData = "budgetMainData";
	
	@Column(name="is_empty")
	private Boolean isEmpty;
	public static final String _isEmpty = "isEmpty";
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "attached_doc_id")
	private BudgetsAttachedDoc attachedDoc;
	public static final String _attachedDoc = "attachedDoc";
	
	@OneToMany(mappedBy = "budgetFormVersion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy
	private Set<BudgetNote> notes;
	public static final String _notes = "notes";
	
	public BudgetFormVersion() {
		versionRow = new LinkedHashSet<VersionRow>();
		this.notes = new HashSet<BudgetNote>();
		this.budgetMainData = new BudgetMainData();
		this.budgetMainData.setBudgetFormVersion(this);
		setIsEmpty(false);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BudgetForm getBudgetForm() {
		return budgetForm;
	}

	public void setBudgetForm(BudgetForm budgetForm) {
		this.budgetForm = budgetForm;
	}

	public Letter getLetter() {
		return letter;
	}

	public void setLetter(Letter letter) {
		this.letter = letter;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getOutgoingNum() {
		return outgoingNum;
	}

	public void setOutgoingNum(String outgoingNum) {
		this.outgoingNum = outgoingNum;
	}

	public Date getOutgoingDate() {
		return outgoingDate;
	}

	public void setOutgoingDate(Date outgoingDate) {
		this.outgoingDate = outgoingDate;
	}

	public String getIncomingNum() {
		return incomingNum;
	}

	public void setIncomingNum(String incomingNum) {
		this.incomingNum = incomingNum;
	}

	public Date getIncomingDate() {
		return incomingDate;
	}

	public void setIncomingDate(Date incomingDate) {
		this.incomingDate = incomingDate;
	}

	public Checklist getChecklist() {
		return checklist;
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}

	public Set<VersionRow> getVersionRow() {
		return versionRow;
	}

	public void setVersionRow(Set<VersionRow> versionRow) {
		this.versionRow = versionRow;
	}

	public BudgetFormVersion getParent() {
		return parent;
	}

	public void setParent(BudgetFormVersion parent) {
		this.parent = parent;
	}
	
	public BudgetMainData getBudgetMainData() {
		return budgetMainData;
	}

	public void setBudgetMainData(BudgetMainData budgetMainData) {
		this.budgetMainData = budgetMainData;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	public Boolean getIsEmpty() {
		return isEmpty;
	}

	public void setIsEmpty(Boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	public String getSubjectOutgoingNum() {
		return subjectOutgoingNum;
	}

	public void setSubjectOutgoingNum(String subjectOutgoingNum) {
		this.subjectOutgoingNum = subjectOutgoingNum;
	}

	public Date getSubjectOutgoingDate() {
		return subjectOutgoingDate;
	}

	public void setSubjectOutgoingDate(Date subjectOutgoingDate) {
		this.subjectOutgoingDate = subjectOutgoingDate;
	}

	public String getCreatedAtTimestamp() {
		return createdAtTimestamp;
	}

	public void setCreatedAtTimestamp(String createdAtTimestamp) {
		this.createdAtTimestamp = createdAtTimestamp;
	}

	public String getReturnedAtTimestamp() {
		return returnedAtTimestamp;
	}

	public void setReturnedAtTimestamp(String returnedAtTimestamp) {
		this.returnedAtTimestamp = returnedAtTimestamp;
	}

	public String getSignedFilePath() {
		return signedFilePath;
	}

	public void setSignedFilePath(String signedFilePath) {
		this.signedFilePath = signedFilePath;
	}

	public Set<BudgetNote> getNotes() {
		return notes;
	}

	public void setNotes(Set<BudgetNote> notes) {
		this.notes = notes;
	}
	
	public BudgetsAttachedDoc getAttachedDoc() {
		return attachedDoc;
	}

	public void setAttachedDoc(BudgetsAttachedDoc attachedDoc) {
		this.attachedDoc = attachedDoc;
	}

	public BudgetFormVersion copyFrom(BudgetFormVersion that) {
		if (that != null) {
			this.budgetForm = that.budgetForm;
			if (this.letter != null) {
				this.letter = new Letter().copyFrom(that.letter);
			}
			this.dateCreated = that.dateCreated;
			this.lastUpdate = that.lastUpdate;
			this.isEmpty = that.isEmpty;
			this.versionRow = new LinkedHashSet<VersionRow>();
			for (VersionRow row : that.versionRow) {
				this.versionRow.add(new VersionRow().copyFrom(this,row));
			}
			this.budgetMainData = new BudgetMainData().copyFrom(this, that.budgetMainData, null);
			this.createdBy = that.createdBy;
		}
		return this;
	}
	
	public BudgetFormVersion copyFromPrevApp(BudgetFormVersion that, Map<FormConfig, FormConfig> newConfigs) {
		if (that != null) {
			this.versionRow = new LinkedHashSet<VersionRow>();
			for (VersionRow row : that.versionRow) {
				if (row.getIsValid().equals("Y")) {
					this.versionRow.add(new VersionRow().copyFromPrevApp(this,row, newConfigs));
				}
			}
			this.budgetMainData = new BudgetMainData().copyFrom(this, that.budgetMainData, newConfigs);
		}
		return this;
	}

}
