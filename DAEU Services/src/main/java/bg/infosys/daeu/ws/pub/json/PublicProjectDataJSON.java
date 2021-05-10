package bg.infosys.daeu.ws.pub.json;

import java.util.List;

import bg.infosys.daeu.db.dto.PublicAttachedDoc;
import bg.infosys.daeu.db.dto.PublicDetail;

public class PublicProjectDataJSON {
	private Integer publicId;
	
	private String subjectName;
	private String apprDate;
	private String dateCreated;
	private String projectName;
	private String status;
	private String execStatus;
	private String formType;
	
	private List<PublicDetail> additionalFields;
	private List<PublicAttachedDoc> techSpecsAttachedDocs;
	
	public PublicProjectDataJSON() {}

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

	public List<PublicDetail> getAdditionalFields() {
		return additionalFields;
	}

	public String getExecStatus() {
		return execStatus;
	}

	public void setExecStatus(String execStatus) {
		this.execStatus = execStatus;
	}

	public void setAdditionalFields(List<PublicDetail> additionalFields) {
		this.additionalFields = additionalFields;
	}

	public List<PublicAttachedDoc> getTechSpecsAttachedDocs() {
		return techSpecsAttachedDocs;
	}

	public void setTechSpecsAttachedDocs(List<PublicAttachedDoc> techSpecsAttachedDocs) {
		this.techSpecsAttachedDocs = techSpecsAttachedDocs;
	}
}
