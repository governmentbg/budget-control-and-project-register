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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import bg.infosys.daeu.db.entity.security.User;

@Audited
@Entity
@Table(schema = "tech_specs" ,name = "tech_specs_notes")
public class TechSpecsNote {
	@Id
	@SequenceGenerator(name = "tech_specs_notes_seq", sequenceName = "tech_specs.tech_specs_notes_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tech_specs_notes_seq")
	private Integer id;
	public static final String _id = "id";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tech_specs_id")
	private TechSpecs techSpecs;
	public static final String _techSpecs = "techSpecs";
	
	@Column(name = _note)
	private String note;
	public static final String _note = "note";
	
	@ManyToOne
	@JoinColumn(name = "tech_specs_version_id")
	private TechSpecsVersion techSpecsVersion;
	public static final String _techSpecsVersion = "techSpecsVersion";
	
	@Column(name = "is_common")
	private String isCommon;
	public static final String _isCommon = "isCommon";
	
	@Column(name = "is_valid")
	@Length(max = 1)
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	public static final String _user = "user";
	
	@Column(name = _date)
	private Date date = new Date();
	public static final String _date = "date";
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getIsValid() {
		return isValid;
	}
	
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	public String getIsCommon() {
		return isCommon;
	}

	public void setIsCommon(String isCommon) {
		this.isCommon = isCommon;
	}

	public TechSpecs getTechSpecs() {
		return techSpecs;
	}

	public void setTechSpecs(TechSpecs techSpecs) {
		this.techSpecs = techSpecs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TechSpecsVersion getTechSpecsVersion() {
		return techSpecsVersion;
	}

	public void setTechSpecsVersion(TechSpecsVersion techSpecsVersion) {
		this.techSpecsVersion = techSpecsVersion;
	}
}
