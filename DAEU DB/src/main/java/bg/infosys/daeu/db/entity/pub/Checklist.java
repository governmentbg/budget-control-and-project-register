package bg.infosys.daeu.db.entity.pub;

import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "checklists")
public class Checklist  implements Comparable<Checklist> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public static final String _id = "id";
	
	@OneToMany(mappedBy = "checklist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy
	private SortedSet<ChecklistValue> checklistValues;
	public static final String _checklistValues = "checklistValues";
	
	@OneToMany(mappedBy = "checklist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy
	private SortedSet<ChecklistControlValue> checklistControlValues;
	public static final String _checklistControlValues = "checklistControlValues";
			
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checklist_type_id")
	private ChecklistType checklistType;
	public static final String _checklistType = "checklistType";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "checklist_status_id")
	private ChecklistStatus checklistStatus;
	public static final String _checklistStatus = "checklistStatus";
	
	@Column(name = "is_finished")
	private String isFinished = "N";
	public static final String _isFinished = "isFinished";
	
	@Version
	@Column(name = "orm_version")
	private Integer version;
	public static final String _version = "version";
	
	@Column(name = "last_update", columnDefinition = "TIMESTAMP")
	private Date lastUpdate;
	public static final String _lastUpdate = "lastUpdate";
	
	public Checklist() {
		this.checklistValues = new TreeSet<ChecklistValue>();
		this.checklistControlValues = new TreeSet<ChecklistControlValue>();
	}
	
	public Checklist(List<ChecklistConfig> checklistConfigs, List<ChecklistControlConfig> checklistControlConfigs) {
		this.checklistValues = new TreeSet<ChecklistValue>();
		this.checklistControlValues = new TreeSet<ChecklistControlValue>();
		
		for (ChecklistConfig config : checklistConfigs) {
			ChecklistValue value = new ChecklistValue();
			
			value.setChecklist(this);
			value.setConfig(config);
			
			this.checklistValues.add(value);
		}
		
		for (ChecklistControlConfig controlConfig : checklistControlConfigs) {
			ChecklistControlValue controlValue = new ChecklistControlValue();
			
			controlValue.setChecklistControlConfig(controlConfig);
			controlValue.setChecklist(this);
			
			this.checklistControlValues.add(controlValue);
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SortedSet<ChecklistValue> getChecklistValues() {
		return checklistValues;
	}

	public void setChecklistValues(SortedSet<ChecklistValue> checklistValues) {
		this.checklistValues = checklistValues;
	}

	public SortedSet<ChecklistControlValue> getChecklistControlValues() {
		return checklistControlValues;
	}

	public void setChecklistControlValues(SortedSet<ChecklistControlValue> checklistControlValues) {
		this.checklistControlValues = checklistControlValues;
	}

	public ChecklistType getChecklistType() {
		return checklistType;
	}

	public void setChecklistType(ChecklistType checklistType) {
		this.checklistType = checklistType;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(String isFinished) {
		this.isFinished = isFinished;
	}

	public ChecklistStatus getChecklistStatus() {
		return checklistStatus;
	}

	public void setChecklistStatus(ChecklistStatus checklistStatus) {
		this.checklistStatus = checklistStatus;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Checklist copyFrom(Object that) {
		if (that != null) {
			
			for (ChecklistValue value : ((Checklist)that).getChecklistValues()) {
				this.checklistValues.add(new ChecklistValue().copyFrom(this, value));
			}
			
			for (ChecklistControlValue checklistControlValue : ((Checklist)that).getChecklistControlValues()) {
				ChecklistControlValue newControlValue = new ChecklistControlValue();
				newControlValue.setChecklistControlConfig(checklistControlValue.getChecklistControlConfig());
				newControlValue.setChecklist(this);
				
				this.checklistControlValues.add(newControlValue);
			}
			this.checklistType = ((Checklist)that).getChecklistType();
		}
		return this;
	}

	@Override
	public int compareTo(Checklist o) {
		if (o == null || o.getChecklistType() == null) {
			return 1;
		}
		
		return this.getChecklistType().getOrderNum() - o.getChecklistType().getOrderNum();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Checklist other = (Checklist) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
