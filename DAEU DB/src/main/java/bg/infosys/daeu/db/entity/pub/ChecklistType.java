package bg.infosys.daeu.db.entity.pub;

import javax.persistence.CascadeType;
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

@Audited
@Entity
@Table(name = "n_checklist_types", schema="public")
public class ChecklistType implements Comparable<ChecklistType> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = _description)
	private String description;
	public static final String _description = "description";
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "form_type_id")
	private FormType formType;
	public static final String _formType = "formType";
	
	@Column(name = "is_valid")
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	@Column(name = "is_crucial")
	private String isCrucial = "N";
	public static final String _isCrucial = "isCrucial";
	
	@Column(name = "order_num")
	private Integer orderNum;
	public static final String _orderNum = "orderNum";

	@Column(name = "is_primary")
	private String isPrimary = "N";
	public static final String _isPrimary = "isPrimary";
	
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public FormType getFormType() {
		return formType;
	}
	
	public void setFormType(FormType formType) {
		this.formType = formType;
	}
	
	public String getIsValid() {
		return isValid;
	}
	
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getIsCrucial() {
		return isCrucial;
	}

	public void setIsCrucial(String isCrucial) {
		this.isCrucial = isCrucial;
	}

	public String getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}

	@Override
	public int compareTo(ChecklistType o) {
		if (this.orderNum == null && o.getOrderNum() == null) {
			return 0;
		} else if (this.orderNum == null) {
			return -1;
		} else if (o.getOrderNum() == null) {
			return 1;
		} else {
			return getOrderNum() - o.getOrderNum();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChecklistType other = (ChecklistType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	public enum ChecklistTypeConst {
		TECH_SPECS_MAIN		(9,	"Бюджетен контрол"),
		TECH_SPECS_CONTROL	(9,	"Проектни предложения и дейности"),
		TECH_SPECS_SECURITY	(9,	"Технически спецификации"),
		PROJECTS_MAIN		(7,	"Проектни предложения и дейности"),
		PROJECTS_CONTROL	(7,	"Проектни предложения и дейности");
		
		private final Integer formTypeId;
		private final String name;

		private ChecklistTypeConst(Integer formTypeId, String name) {
			this.formTypeId = formTypeId;
			this.name = name;
		}

		public Integer getFormTypeId() {
			return formTypeId;
		}

		public String getName() {
			return name;
		}
	}
}
