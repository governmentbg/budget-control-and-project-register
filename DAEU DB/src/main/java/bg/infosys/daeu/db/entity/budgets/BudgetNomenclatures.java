package bg.infosys.daeu.db.entity.budgets;

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
@Table(schema = "budgets", name = "n_budgets")
public class BudgetNomenclatures implements Comparable<BudgetNomenclatures>{

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne()
	@JoinColumn(name = "column_type_id")
	private FormColumnType formColumnType;
	public static final String _formColumnType = "formColumnType";
	
	@Column(name = _value)
	private String value;
	public static final String _value = "value";
	
	@Column(name = "order_num")
	private Integer orderNum = 0;
	public static final String _orderNum = "orderNum";
	
	@Column(name = "is_valid")
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	public BudgetNomenclatures() {}
	
	public BudgetNomenclatures(FormColumnType type, Integer orderNum) {
		this.formColumnType = type;
		this.orderNum = orderNum;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public FormColumnType getFormColumnType() {
		return formColumnType;
	}
	public void setFormColumnType(FormColumnType formColumnType) {
		this.formColumnType = formColumnType;
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
	
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@Override
	public int compareTo(BudgetNomenclatures o) {
		if (this.orderNum == null) {
			return -1;
		} else if(o.getOrderNum() == null) {
			return 1;
		} else if (this.orderNum == null && o.getOrderNum() == null) {
			return 0;
		} else {
			return this.orderNum - o.getOrderNum();
		}
	}
	
}
