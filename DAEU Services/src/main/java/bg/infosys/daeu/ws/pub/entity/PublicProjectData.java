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
@Table(name = "public_project_data")
public class PublicProjectData {
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
	
	@Column(name = "project_name")
	private String projectName;
	public static final String _projectName = "projectName";
	
	@Column(name = _status)
	private String status;
	public static final String _status = "status";
	
	@Column(name = "exec_status")
	private String execStatus;
	public static final String _execStatus = "execStatus";
	
	@Column(name = "form_type")
	private String formType;
	public static final String _formType = "formType";
	
	@OneToMany(mappedBy = "publicProjectData", fetch = FetchType.EAGER)
	private Set<PublicProjectDataDetail> publicProjectDataDetails;
	public static final String _publicProjectDataDetails = "publicProjectDataDetails";
	
	public PublicProjectData() {}

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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getExecStatus() {
		return execStatus;
	}

	public void setExecStatus(String execStatus) {
		this.execStatus = execStatus;
	}

	public Set<PublicProjectDataDetail> getPublicProjectDataDetails() {
		return publicProjectDataDetails;
	}

	public void setPublicProjectDataDetails(Set<PublicProjectDataDetail> publicProjectDataDetails) {
		this.publicProjectDataDetails = publicProjectDataDetails;
	}
}
