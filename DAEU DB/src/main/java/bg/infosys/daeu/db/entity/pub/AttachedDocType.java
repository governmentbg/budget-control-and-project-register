package bg.infosys.daeu.db.entity.pub;

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
@Table(name = "n_file_types")
public class AttachedDocType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	@ManyToOne
	@JoinColumn(name = "module_type_code")
	private ModuleType moduleType;
	public static final String _moduleType = "moduleType";
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ModuleType getModuleType() {
		return moduleType;
	}

	public void setModuleType(ModuleType moduleType) {
		this.moduleType = moduleType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public enum AttachedDocTypeConst {
		TECH_SPECS		("Техническа спецификация",	"N"),
		TECH_SPECS_ARG_HEAD	("Аргументация за избраната прогнозна стойност", "ARTHEAD"),
		TECH_SPECS_ARG	("Аргументация",	"ARTXT"),
		TECH_SPECS_IND	("Индикативен бюджет",	"INDTXT"),
		PROJECTS		("Проектно предложение",	"N"),
		PROJECTS_ARG	("Аргументация",	"ARTXT"),
		PROJECTS_HIS	("Аргументация историческа справка", "HISTXT"),
		PROJECTS_SUBJ	("Аргументация за АО",	"SUBJTXT"),
		PROJECTS_IND	("Индикативен бюджет",	"INDTXT");
		
		private final String code;
		private final String name;

		private AttachedDocTypeConst(String name, String code) {
			this.name = name;
			this.code = code;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}
}
