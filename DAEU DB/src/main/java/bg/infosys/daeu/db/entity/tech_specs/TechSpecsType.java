package bg.infosys.daeu.db.entity.tech_specs;

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
@Table(schema = "tech_specs" ,name = "n_tech_specs")
public class TechSpecsType implements Comparable<TechSpecsType> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "row_type_id")
	private TechRowType techRowType;
	public static final String _techRowType = "techRowType";
	
	@Column(name = _value)
	private String value;
	public static final String _value = "value";
	
	@Column(name = "order_num")
	private Integer orderNum = 0;
	public static final String _orderNum = "orderNum";
	
	@Column(name = "is_valid")
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	public TechSpecsType() {}
	
	public TechSpecsType(TechRowType techRowType, Integer orderNum) {
		this.techRowType = techRowType;
		this.orderNum = orderNum;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public TechRowType getTechRowType() {
		return techRowType;
	}
	
	public void setTechRowType(TechRowType techRowType) {
		this.techRowType = techRowType;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
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
	public int compareTo(TechSpecsType o) {
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
