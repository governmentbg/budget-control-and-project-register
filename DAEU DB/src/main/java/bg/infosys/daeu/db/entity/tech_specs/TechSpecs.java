package bg.infosys.daeu.db.entity.tech_specs;

import java.util.Date;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;

@Audited
@Entity
@Table(schema = "tech_specs" ,name = "tech_specs")
public class TechSpecs {
	@Id
	@SequenceGenerator(name = "tech_specs_seq", sequenceName = "tech_specs.tech_specs_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tech_specs_seq")
	private Integer id;
	public static final String _id = "id";
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "last_version_id")
	private TechSpecsVersion techSpecsVersion;
	public static final String _techSpecsVersion = "techSpecsVersion";
	
	@Column(name = "date_created", columnDefinition = "TIMESTAMP")
	private Date dateCreated;
	public static final String _dateCreated = "dateCreated";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_version_id")
	private SubjectVersion subjectVersion;
	public static final String _subjectVersion = "subjectVersion";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
	public static final String _status = "status";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_type_id")
	private FormType formType;
	public static final String _formType = "formType";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "final_status_id")
	private Status finalStatus;
	public static final String _finalStatus = "finalStatus";
	
	@Column(name = "public_id")
	private Integer publicId;
	public static final String _publicId = "publicId";
	
	@Column(name = "last_update", columnDefinition = "TIMESTAMP")
	private Date lastUpdate;
	public static final String _lastUpdate = "lastUpdate";
	
	public TechSpecs() {
		techSpecsVersion = new TechSpecsVersion();
		techSpecsVersion.setTechSpecs(this);
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public TechSpecsVersion getTechSpecsVersion() {
		return techSpecsVersion;
	}
	
	public void setTechSpecsVersion(TechSpecsVersion techSpecsVersion) {
		this.techSpecsVersion = techSpecsVersion;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public FormType getFormType() {
		return formType;
	}
	
	public void setFormType(FormType formType) {
		this.formType = formType;
	}

	public SubjectVersion getSubjectVersion() {
		return subjectVersion;
	}

	public void setSubjectVersion(SubjectVersion subjectVersion) {
		this.subjectVersion = subjectVersion;
	}

	public Status getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(Status finalStatus) {
		this.finalStatus = finalStatus;
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
}
