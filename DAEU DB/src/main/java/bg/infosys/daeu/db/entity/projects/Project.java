package bg.infosys.daeu.db.entity.projects;

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

import bg.infosys.daeu.db.entity.pub.ExecutionStatus;
import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;

@Audited
@Entity
@Table(schema = "projects", name = "projects")
public class Project {
	@Id 
	@SequenceGenerator(name = "projects_seq", sequenceName = "projects.projects_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projects_seq")
	private Integer id;
	public static final String _id = "id";
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "last_version_id")
	private ProjectVersion projectVersion;
	public static final String _projectVersion = "projectVersion";
	
	@Column(name = "date_created", columnDefinition = "TIMESTAMP")
	private Date dateCreated;
	public static final String _dateCreated = "dateCreated";
	
	@Column(name = "public_id")
	private Integer publicId;
	public static final String _publicId = "publicId";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_type_id")
	private FormType formType;
	public static final String _formType = "formType";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_version_id")
	private SubjectVersion subjectVersion;
	public static final String _subjectVersion = "subjectVersion";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id")
	private Status status;
	public static final String _status = "status";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "execution_status_id")
	private ExecutionStatus executionStatus;
	public static final String _executionStatus = "executionStatus";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "final_status_id")
	private Status finalStatus;
	public static final String _finalStatus = "finalStatus";
	
	@Column(name = _guid)
	private String guid;
	public static final String _guid = "guid";
	
	@Column(name = "isun_major_version")
	private Integer isunMajorVersion;
	public static final String _isunMajorVersion = "isunMajorVersion";
	
	public Project() {
		this.projectVersion = new ProjectVersion();
		this.projectVersion.setProject(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProjectVersion getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(ProjectVersion projectVersion) {
		this.projectVersion = projectVersion;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public ExecutionStatus getExecutionStatus() {
		return executionStatus;
	}

	public void setExecutionStatus(ExecutionStatus executionStatus) {
		this.executionStatus = executionStatus;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Integer getIsunMajorVersion() {
		return isunMajorVersion;
	}

	public void setIsunMajorVersion(Integer isunMajorVersion) {
		this.isunMajorVersion = isunMajorVersion;
	}
}
