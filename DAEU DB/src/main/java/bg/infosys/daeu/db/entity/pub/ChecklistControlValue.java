package bg.infosys.daeu.db.entity.pub;

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
@Table(name = "checklist_control_values", schema="public")
public class ChecklistControlValue implements Comparable<ChecklistControlValue> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checklist_id")
	private Checklist checklist;
	public static final String _checklist = "checklist";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	public static final String _user = "user";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checklist_control_config_id")
	private ChecklistControlConfig checklistControlConfig;
	public static final String _checklistControlConfig = "checklistControlConfig";
	
	@ManyToOne
	@JoinColumn(name = "checklist_authority_id")
	private ChecklistAuthority checklistAuthority;
	public static final String _checklistAuthority = "checklistAuthority";
	
	@Column(name = _value)
	private String value;
	public static final String _value = "value";
	
	@Column(name = "is_valid")
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	@Column(name = "comment")
	private String comment;
	public static final String _comment = "comment";
	
	@Column(name = "inserted_timestamp")
	private Date insertedTimestamp;
	public static final String _insertedTimestamp = "insertedTimestamp";

	@Column(name = "has_returned")
	private String hasReturned = "N";
	public static final String _hasReturned = "hasReturned";
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Date getInsertedTimestamp() {
		return insertedTimestamp;
	}

	public void setInsertedTimestamp(Date insertedTimestamp) {
		this.insertedTimestamp = insertedTimestamp;
	}

	public ChecklistControlConfig getChecklistControlConfig() {
		return checklistControlConfig;
	}

	public void setChecklistControlConfig(ChecklistControlConfig checklistControlConfig) {
		this.checklistControlConfig = checklistControlConfig;
	}

	public Checklist getChecklist() {
		return checklist;
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getHasReturned() {
		return hasReturned;
	}

	public void setHasReturned(String hasReturned) {
		this.hasReturned = hasReturned;
	}
	
	public ChecklistAuthority getChecklistAuthority() {
		return checklistAuthority;
	}

	public void setChecklistAuthority(ChecklistAuthority checklistAuthority) {
		this.checklistAuthority = checklistAuthority;
	}

	@Override
	public int compareTo(ChecklistControlValue o) {
		return this.getChecklistControlConfig().getOrderNum() - o.getChecklistControlConfig().getOrderNum();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChecklistControlValue other = (ChecklistControlValue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
