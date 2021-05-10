package bg.infosys.daeu.db.entity.projects;

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

import bg.infosys.daeu.db.entity.security.User;

@Audited
@Entity
@Table(schema = "projects", name = "project_notes")
public class ProjectNote {
	@Id
	@SequenceGenerator(name = "project_notes_seq", sequenceName = "projects.project_notes_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_notes_seq")
	private Integer id;
	public static final String _id = "id";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;
	public static final String _project = "project";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_version_id")
	private ProjectVersion version;
	public static final String _version = "version";
	
	@Column(name = _note)
	private String note;
	public static final String _note = "note";
	
	@Column(name = "is_common")
	private String isCommon;
	public static final String _isCommon = "isCommon";
	
	@Column(name = "is_valid")
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	public ProjectVersion getVersion() {
		return version;
	}

	public void setVersion(ProjectVersion version) {
		this.version = version;
	}
}
