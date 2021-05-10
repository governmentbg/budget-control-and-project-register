package bg.infosys.daeu.db.entity.projects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.Checklist;
import bg.infosys.daeu.db.entity.pub.EBKCode;
import bg.infosys.daeu.db.entity.pub.Funding;
import bg.infosys.daeu.db.entity.pub.Letter;
import bg.infosys.daeu.db.entity.security.User;

@Audited
@Entity
@Table(schema = "projects", name = "project_versions")
public class ProjectVersion {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public static final String _id = "id";
	
	@OneToOne
	@JoinColumn(name = "project_id")
	private Project project;
	public static final String _project = "project";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "funding_id")
	private Funding funding;
	public static final String _funding = "funding";
	
	@Column(name = "funding_description")
	private String fundingDescription;
	public static final String _fundingDescription = "fundingDescription";
	
	@Column(name = "open_data_uri")
	private String openDataURI;
	public static final String _openDataURI = "openDataURI";
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "letter_id")
	private Letter letter;
	public static final String _letter = "letter";
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.JOIN)
	@JoinTable(name = "project_version_ebk", schema="projects",
			joinColumns = @JoinColumn(name="project_version_id"),
			inverseJoinColumns = @JoinColumn(name = "ebk_id"))
	private Set<EBKCode> EBKCodes;
	public static final String _ebkCodes = "ebkCodes";
	
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
	
	@Column(name = "subject_outgoing_num")
	private String subjectOutgoingNum;
	public static final String _subjectOutgoingNum = "subjectOutgoingNum";
	
	@Column(name = "subject_outgoing_date", columnDefinition = "DATE")
	private Date subjectOutgoingDate;
	public static final String _subjectOutgoingDate = "subjectOutgoingDate";
	
	@Column(name = "incoming_num")
	private String incomingNum;
	public static final String _incomingNum = "incomingNum";
	
	@Column(name = "incoming_date", columnDefinition = "DATE")
	private Date incomingDate;
	public static final String _incomingDate = "incomingDate";
	
	@Column(name = "created_at_timestamp")
	private String createdAtTimestamp;
	public static final String _createdAtTimestamp = "createdAtTimestamp";
	
	@Column(name = "returned_at_timestamp")
	private String returnedAtTimestamp;
	public static final String _returnedAtTimestamp = "returnedAtTimestamp";
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "checklist_id")
	private Checklist checklist;
	public static final String _checklist = "checklist";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	private User createdBy;
	public static final String _createdBy = "createdBy";
	
	@Column(name = "signed_file_path")
	private String signedFilePath;
	public static final String _signedFilePath = "signedFilePath";
	
	@Where(clause = "is_valid = 'Y'")
	@OneToMany(mappedBy = "projectVersion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy
	private SortedSet<AttachedDoc> attachedDoc;
	public static final String _attachedDoc = "attachedDoc";
	
	@OneToMany(mappedBy = "projectVersion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy
	private SortedSet<ProjectBudget> projectBudget;
	public static final String _projectBudget = "projectBudget";
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "project_main_data_id")
	private ProjectMainData projectMainData;
	public static final String _projectMainData = "projectMainData";
	
	@Column(name = "isun_minor_version")
	private Integer isunMinorVersion;
	public static final String _isunMinorVersion = "isunMinorVersion";
	
	@Column(name = "isun_link")
	private String isunLink;
	public static final String _isunLink = "isunLink";
	
	@Column(name = "e_service")
	private String eService;
	public static final String _eService = "eService";
	
	@OneToMany(mappedBy = "version", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy
	private Set<ProjectNote> notes;
	public static final String _notes = "notes";
	
	public ProjectVersion() {
		this.checklist = new Checklist();
		this.projectMainData = new ProjectMainData();
		this.projectMainData.setProjectVersion(this);
		this.attachedDoc = new TreeSet<AttachedDoc>();
		this.projectBudget = new TreeSet<ProjectBudget>();
		this.EBKCodes = new TreeSet<EBKCode>();
		this.notes = new HashSet<ProjectNote>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Funding getFunding() {
		return funding;
	}

	public void setFunding(Funding funding) {
		this.funding = funding;
	}

	public String getFundingDescription() {
		return fundingDescription;
	}

	public void setFundingDescription(String fundingDescription) {
		this.fundingDescription = fundingDescription;
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

	public SortedSet<AttachedDoc> getAttachedDoc() {
		return attachedDoc;
	}

	public void setAttachedDoc(SortedSet<AttachedDoc> attachedDoc) {
		this.attachedDoc = attachedDoc;
	}

	public ProjectMainData getProjectMainData() {
		return projectMainData;
	}

	public void setProjectMainData(ProjectMainData projectMainData) {
		this.projectMainData = projectMainData;
	}

	public SortedSet<ProjectBudget> getProjectBudget() {
		return projectBudget;
	}

	public void setProjectBudget(SortedSet<ProjectBudget> projectBudget) {
		this.projectBudget = projectBudget;
	}

	public String getOpenDataURI() {
		return openDataURI;
	}

	public void setOpenDataURI(String openDataURI) {
		this.openDataURI = openDataURI;
	}

	public Set<EBKCode> getEBKCodes() {
		return EBKCodes;
	}

	public void setEBKCodes(Set<EBKCode> EBKCodes) {
		this.EBKCodes = EBKCodes;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
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
	
	public Integer getIsunMinorVersion() {
		return isunMinorVersion;
	}

	public void setIsunMinorVersion(Integer isunMinorVersion) {
		this.isunMinorVersion = isunMinorVersion;
	}

	public String getIsunLink() {
		return isunLink;
	}

	public void setIsunLink(String isunLink) {
		this.isunLink = isunLink;
	}

	public Set<ProjectNote> getNotes() {
		return notes;
	}

	public void setNotes(Set<ProjectNote> notes) {
		this.notes = notes;
	}

	public String geteService() {
		return eService;
	}

	public void seteService(String eService) {
		this.eService = eService;
	}

	public ProjectVersion copyFrom(ProjectVersion that) {
		if (that != null) {
			this.project = that.project;
			this.funding = that.funding;
			this.fundingDescription = that.fundingDescription;
			if(this.letter != null) {
				this.letter = new Letter().copyFrom(that.letter);
			}
			this.lastUpdate = that.lastUpdate;
			this.EBKCodes = that.EBKCodes;
			this.openDataURI = that.openDataURI;
			this.eService = that.eService;
			this.projectBudget = new TreeSet<ProjectBudget>();
			for (ProjectBudget budget : that.projectBudget) {
				this.projectBudget.add(new ProjectBudget().copyFrom(this, budget));
			}
			
			for (AttachedDoc attachedDoc : that.getAttachedDoc()) {
				AttachedDoc newDoc = new AttachedDoc().copyFrom(this, attachedDoc);
				this.attachedDoc.add(newDoc);
			}
			
			this.projectMainData = new ProjectMainData().copyFrom(this, that.projectMainData);
			this.createdBy = that.createdBy;
			this.isunMinorVersion = that.isunMinorVersion;
			this.isunLink = that.isunLink;
		}
		return this;
	}
}
