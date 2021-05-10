package bg.infosys.daeu.db.entity.pub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "n_module_types", schema="public")
public class ModuleType {

	@Id
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = "has_letters")
	private String hasLetters;
	public static final String _hasLetters = "hasLetters";
	
	public ModuleType() {}
	
	public ModuleType(String code) {
		this.code = code;
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
	
	public String getHasLetters() {
		return hasLetters;
	}

	public void setHasLetters(String hasLetters) {
		this.hasLetters = hasLetters;
	}

	public enum ModuleTypeConst {
		BUDGETARY_CONTROL	("BC",	"Бюджетен контрол"),
		PROJECTS			("P",	"Проектни предложения и дейности"),
		TECH_SPECS			("TS",	"Технически спецификации"),
		REGISTRATION_MODULE	("REG",	"Модул за регистрация");
		
		private final String code;
		private final String name;

		private ModuleTypeConst(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}
}
