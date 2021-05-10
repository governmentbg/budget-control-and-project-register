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

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.security.Authority;

@Audited
@Entity
@Table(name = "letter_control_config", schema="public")
public class LetterControlConfig {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Authority authority;
	public static final String _authority = "authority";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "module_type_code")
	private ModuleType moduleType;
	public static final String _moduleType = "moduleType";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@Column(name = "order_num")
	private Short orderNum;
	public static final String _orderNum = "orderNum";
	
	public LetterControlConfig() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public ModuleType getModuleType() {
		return moduleType;
	}

	public void setModuleType(ModuleType moduleType) {
		this.moduleType = moduleType;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Short getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Short orderNum) {
		this.orderNum = orderNum;
	}
}
