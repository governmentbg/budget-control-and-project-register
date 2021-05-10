package bg.infosys.daeu.db.entity.pub;

import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "checklist_config")
public class ChecklistConfig implements Comparable<ChecklistConfig> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "checklist_element_id")
	private ChecklistElement checklistElement;
	public static final String _checklistElement = "checklistElement";

	@ManyToOne
	@JoinColumn(name = "checklist_authority_id")
	private ChecklistAuthority checklistAuthority;
	public static final String _checklistAuthority = "checklistAuthority";

	@ManyToOne
	@JoinColumn(name = "checklist_type_id")
	private ChecklistType checklistType;
	public static final String _checklistType = "checklistType";

	@Column(name = "is_valid")
	private String isValid ="Y";
	public static final String _isValid = "isValid";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ChecklistElement getChecklistElement() {
		return checklistElement;
	}

	public void setChecklistElement(ChecklistElement checklistElement) {
		this.checklistElement = checklistElement;
	}

	public String isValid() {
		return isValid;
	}

	public void setValid(String isValid) {
		this.isValid = isValid;
	}

	public static String getIsvalid() {
		return _isValid;
	}

	public ChecklistType getChecklistType() {
		return checklistType;
	}

	public void setChecklistType(ChecklistType checklistType) {
		this.checklistType = checklistType;
	}

	public ChecklistAuthority getChecklistAuthority() {
		return checklistAuthority;
	}

	public void setChecklistAuthority(ChecklistAuthority checklistAuthority) {
		this.checklistAuthority = checklistAuthority;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChecklistConfig other = (ChecklistConfig) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(ChecklistConfig o) {
		if (this.getChecklistAuthority() != null  && o.getChecklistAuthority() != null) {
			return Comparator.comparing((ChecklistConfig c)->c.checklistElement.getOrderNum())
					.thenComparing(c->c.checklistAuthority.getOrderNum())
					.compare(this, o);
		}else if(this.getChecklistAuthority() != null  || o.getChecklistAuthority() != null){
			int i = 0;
			i = Comparator.comparing((ChecklistConfig c)->c.checklistElement.getOrderNum())
					.compare(this, o);
			if(i == 0) {
				return -1;
			}
		}
		return Comparator.comparing((ChecklistConfig c)->c.checklistElement.getOrderNum())
				.compare(this, o);
	}
}
