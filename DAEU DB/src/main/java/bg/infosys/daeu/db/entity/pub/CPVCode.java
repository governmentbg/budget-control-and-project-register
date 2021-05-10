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
@Table(name = "n_cpv_codes")
public class CPVCode {

	@Id
	@SequenceGenerator(name = "n_cpv_codes_seq", sequenceName = "n_cpv_codes_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "n_cpv_codes_seq")
	private Integer id;

	@Column(name = "order_num")
	private Double orderNum;
	public static final String _orderNum = "orderNum";

	@Column(name = _code)
	private String code;
	public static final String _code = "code";

	@Column(name = _name)
	private String name;
	public static final String _name = "name";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent")
	private CPVCode parent;
	public static final String _parent = "parent";

	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@Column(name = "common_used")
	private String commonUsed;
	public static final String _commonUsed = "commonUsed";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Double orderNum) {
		this.orderNum = orderNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CPVCode getParent() {
		return parent;
	}

	public void setParent(CPVCode parent) {
		this.parent = parent;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getCommonUsed() {
		return commonUsed;
	}

	public void setCommonUsed(String commonUsed) {
		this.commonUsed = commonUsed;
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
		CPVCode other = (CPVCode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public CPVCode copyFrom(CPVCode that) {
		if (that != null) {
			this.id = that.id;
			this.orderNum = that.orderNum;
			this.code = that.code;
			this.name = that.name;
			this.parent = that.parent;
			this.isValid = that.isValid;
			this.commonUsed = that.commonUsed;
		}
		return this;
	}
}
