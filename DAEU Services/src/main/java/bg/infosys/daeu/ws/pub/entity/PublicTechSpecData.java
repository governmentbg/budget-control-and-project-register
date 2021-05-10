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
@Table(name = "public_tech_spec_data")
public class PublicTechSpecData {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "subject_name")
	private String subjectName;
	public static final String _subjectName = "subjectName";
	
	@Column(name = "date_created")
	private String dateCreated;
	public static final String _dateCreated = "dateCreated";

	@Column(name = "appr_date")
	private String apprDate;
	public static final String _apprDate = "apprDate";
	
	@Column(name = _status)
	private String status;
	public static final String _status = "status";
	
	@OneToMany(mappedBy = "publicTechSpecData", fetch = FetchType.EAGER)
	private Set<PublicTechSpecDataDetail> publicTechSpecDataDetails;
	public static final String _publicTechSpecDataDetails = "publicTechSpecDataDetails";
	
	public PublicTechSpecData() {}
	
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

	public Set<PublicTechSpecDataDetail> getPublicTechSpecDataDetails() {
		return publicTechSpecDataDetails;
	}

	public void setPublicTechSpecDataDetails(Set<PublicTechSpecDataDetail> publicTechSpecDataDetails) {
		this.publicTechSpecDataDetails = publicTechSpecDataDetails;
	}
}
