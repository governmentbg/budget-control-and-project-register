package bg.infosys.daeu.ws.pub.json;

import java.util.List;

import bg.infosys.daeu.db.dto.PublicTechSpecsDetail;

public class PublicTechSpecDataJSON {
	private Integer publicId;
	
	private String subjectName;
	private String dateCreated;
	private String apprDate;
	private String status;
	
	private List<PublicTechSpecsDetail> additionalFields;

	public PublicTechSpecDataJSON() {}

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

	public List<PublicTechSpecsDetail> getAdditionalFields() {
		return additionalFields;
	}

	public void setAdditionalFields(List<PublicTechSpecsDetail> additionalFields) {
		this.additionalFields = additionalFields;
	}
}
