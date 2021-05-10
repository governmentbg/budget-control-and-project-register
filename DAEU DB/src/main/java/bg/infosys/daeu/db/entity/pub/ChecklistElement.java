package bg.infosys.daeu.db.entity.pub;

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

@Audited
@Entity
@Table(name = "n_checklist_elements")
public class ChecklistElement implements Comparable<ChecklistElement> {

	@Id
	@SequenceGenerator(name = "checklist_elements_seq", sequenceName = "n_checklist_elements_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "checklist_elements_seq")
	private Integer id;
	
	@Column(name = "description")
	private String description;
	public static final String _description = "description";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private ChecklistElement parent;
	public static final String _parent = "parent";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "component_type_code")
	private ComponentType componentType;
	public static final String _componentType = "componentType";
	
	@Column(name = "order_num")
	private Integer orderNum;
	public static final String _orderNum = "orderNum";
	
	@Column(name = "is_valid")
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ChecklistElement getParent() {
		return parent;
	}
	
	public void setParent(ChecklistElement parent) {
		this.parent = parent;
	}
	
	public ComponentType getComponentType() {
		return componentType;
	}
	
	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}
	
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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
		ChecklistElement other = (ChecklistElement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int compareTo(ChecklistElement o) {
		return this.orderNum - o.getOrderNum();
	}
}
