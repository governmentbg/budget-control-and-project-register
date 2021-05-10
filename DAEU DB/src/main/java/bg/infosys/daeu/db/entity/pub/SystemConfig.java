package bg.infosys.daeu.db.entity.pub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "system_config", schema="public")
public class SystemConfig {
	@Id
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	@Column(name = _value)
	private String value;
	public static final String _value = "value";
	
	@Column(name = _description)
	private String description;
	public static final String _description = "description";
	
	/* Getters & Setters */
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
