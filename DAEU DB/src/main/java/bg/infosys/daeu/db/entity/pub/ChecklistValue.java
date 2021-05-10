package bg.infosys.daeu.db.entity.pub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "checklist_values")
public class ChecklistValue implements Comparable<ChecklistValue> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checklist_config_id")
	private ChecklistConfig config;
	public static final String _config = "config";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checklist_id")
	private Checklist checklist;
	public static final String _checklist = "checklist";
	
	@Column(name = _value)
	private String value;
	public static final String _value = "value";
	
	@Transient
	private boolean isVisible = true;
	
	public boolean getVisible() {
		return isVisible;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public ChecklistConfig getConfig() {
		return config;
	}
	
	public void setConfig(ChecklistConfig config) {
		this.config = config;
	}
	
	public Checklist getChecklist() {
		return checklist;
	}
	
	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public ChecklistValue copyFrom(Checklist checklist, ChecklistValue that) {
		if (that != null) {
			this.config = that.config;
			this.checklist = checklist;
			this.value = that.value;
		}
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChecklistValue other = (ChecklistValue) obj;
		if (id == null && other.id == null)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(ChecklistValue o) {
		if (this.equals(o)) {
			return 0;
		}
		
		if (this.getConfig().getChecklistElement().getOrderNum().equals(o.getConfig().getChecklistElement().getOrderNum())) {
			if (id == null && o.id == null) {
				return -1;
			}
			
			return this.id - o.getId();
		}
		
		return this.getConfig().getChecklistElement().getOrderNum() - o.getConfig().getChecklistElement().getOrderNum();
	}
	
}
