package bg.infosys.daeu.db.entity.tech_specs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.security.User;

@Audited
@Entity
@Table(schema = "tech_specs" ,name = "tech_specs_versions_users")
public class TechSpecsVersionUser {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tech_spec_version_id")
	private TechSpecsVersion techSpecsVersion;
	public static final String _techSpecsVersion = "techSpecsVersion";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assigner_user_id")
	private User assigner;
	public static final String _assigner = "assigner";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee_user_id")
	private User assignee;
	public static final String _assignee = "assignee";
	
	@Column(name = "inserted_timestamp")
	private Date insertedTimestamp;
	public static final String _insertedTimestamp = "insertedTimestamp";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	public TechSpecsVersionUser() {}
	
	public TechSpecsVersionUser(TechSpecsVersion version, User assigner, User assignee) {
		this.techSpecsVersion = version;
		this.assigner = assigner;
		this.assignee = assignee;
		this.insertedTimestamp = new Date();
		this.isValid = "Y";
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

	public Date getInsertedTimestamp() {
		return insertedTimestamp;
	}

	public void setInsertedTimestamp(Date insertedTimestamp) {
		this.insertedTimestamp = insertedTimestamp;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public User getAssigner() {
		return assigner;
	}

	public void setAssigner(User assigner) {
		this.assigner = assigner;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
}
