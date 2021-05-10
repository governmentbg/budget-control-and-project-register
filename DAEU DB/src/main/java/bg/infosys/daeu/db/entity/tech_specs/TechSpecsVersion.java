package bg.infosys.daeu.db.entity.tech_specs;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.CPVCode;
import bg.infosys.daeu.db.entity.pub.Checklist;
import bg.infosys.daeu.db.entity.pub.Letter;
import bg.infosys.daeu.db.entity.security.User;

@Audited
@Entity
@Table(schema = "tech_specs" ,name = "tech_specs_versions")
public class TechSpecsVersion {
	@Id
	@SequenceGenerator(name = "tech_specs_versions_seq", sequenceName = "tech_specs.tech_specs_versions_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tech_specs_versions_seq")
	private Integer id;
	public static final String _id = "id";
	
	@OneToOne
	@NotNull
	@JoinColumn(name = "tech_spec_id")
	private TechSpecs techSpecs;
	public static final String _techSpecs = "techSpecs";
	
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
	
	@Column(name = "open_data_uri")
	private String openDataURI;
	public static final String _openDataURI = "openDataURI";
	
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
	
	@Column(name = "created_at_timestamp")
	private String createdAtTimestamp;
	public static final String _createdAtTimestamp = "createdAtTimestamp";
	
	@Column(name = "returned_at_timestamp")
	private String returnedAtTimestamp;
	public static final String _returnedAtTimestamp = "returnedAtTimestamp";
	
	@Column(name = "signed_file_path")
	private String signedFilePath;
	public static final String _signedFilePath = "signedFilePath";
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "checklist_id")
	private Checklist checklist;
	public static final String _checklist = "checklist";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by")
	private User createdBy;
	public static final String _createdBy = "createdBy";
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "techSpecsVersion", fetch = FetchType.LAZY, orphanRemoval = true)
	@OrderBy
	private SortedSet<TechSpecsVersionValue> values;
	public static final String _values = "values";
	
	@Where(clause = "is_valid = 'Y'")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "techSpecsVersion", fetch = FetchType.LAZY)
	@OrderBy
	private SortedSet<TechSpecsAttachedDoc> attachedDocs;
	public static final String _attachedDocs = "attachedDocs";
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.JOIN)
	@JoinTable(name = "tech_specs_versions_cpv", schema="tech_specs",
			joinColumns = @JoinColumn(name="tech_specs_version_id"),
			inverseJoinColumns = @JoinColumn(name = "cpv_id"))
	private Set<CPVCode> cpvCodes;
	public static final String _cpvCodes = "cpvCodes";
	
	@OneToMany(mappedBy = "techSpecsVersion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@OrderBy
	private Set<TechSpecsNote> notes;
	public static final String _notes = "notes";
	
	public TechSpecsVersion() {
		this.attachedDocs = new TreeSet<TechSpecsAttachedDoc>();
		this.values = new TreeSet<TechSpecsVersionValue>();
		this.cpvCodes = new TreeSet<CPVCode>();
		this.notes = new HashSet<TechSpecsNote>();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public TechSpecs getTechSpecs() {
		return techSpecs;
	}
	
	public void setTechSpecs(TechSpecs techSpecs) {
		this.techSpecs = techSpecs;
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
	
	public SortedSet<TechSpecsVersionValue> getValues() {
		return values;
	}

	public void setValues(SortedSet<TechSpecsVersionValue> values) {
		this.values = values;
	}

	public SortedSet<TechSpecsAttachedDoc> getAttachedDocs() {
		return attachedDocs;
	}

	public void setAttachedDocs(SortedSet<TechSpecsAttachedDoc> attachedDocs) {
		this.attachedDocs = attachedDocs;
	}

	public String getOpenDataURI() {
		return openDataURI;
	}

	public void setOpenDataURI(String openDataURI) {
		this.openDataURI = openDataURI;
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

	public Set<CPVCode> getCpvCodes() {
		return cpvCodes;
	}

	public void setCpvCodes(Set<CPVCode> cpvCodes) {
		this.cpvCodes = cpvCodes;
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

	public Set<TechSpecsNote> getNotes() {
		return notes;
	}

	public void setNotes(Set<TechSpecsNote> notes) {
		this.notes = notes;
	}

	public TechSpecsVersion copyFrom(TechSpecsVersion that) {
		if (that != null) {
			for (TechSpecsAttachedDoc techSpecsAttachedDoc : that.getAttachedDocs()) {
				TechSpecsAttachedDoc newDoc = new TechSpecsAttachedDoc().copyFrom(techSpecsAttachedDoc);
				newDoc.setTechSpecsVersion(this);
				
				this.attachedDocs.add(newDoc);
			}
			
			for (TechSpecsVersionValue value : that.getValues()) {
				TechSpecsVersionValue newValue = new TechSpecsVersionValue().copyFrom(value);
				newValue.setTechSpecsVersion(this);
				
				this.values.add(newValue);
			}
			
//			this.dateCreated = that.getDateCreated();
			this.techSpecs = that.getTechSpecs();
//			this.outgoingDate = that.getOutgoingDate();
//			this.outgoingNum = that.getOutgoingNum();
			//this.letter = new Letter().copyFrom(that.getLetter());
			this.lastUpdate = that.getLastUpdate();
			this.createdBy = that.createdBy;
			this.cpvCodes = that.cpvCodes;
			this.openDataURI = that.openDataURI;
		}
		
		return this;
	}
}
