package bg.infosys.daeu.db.entity.pub;

import java.util.SortedSet;
import java.util.TreeSet;

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

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "n_form_types", schema="public")
public class FormType {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_type_code")
	private ModuleType moduleType;
	public static final String _moduleType = "moduleType";
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "formType")
	@OrderBy
	private SortedSet<ChecklistType> types;
	
	public FormType() {
		types = new TreeSet<ChecklistType>();
	}
	
	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public FormType(String code) {
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
	
	public String isValid() {
		return isValid;
	}
	
	public void setValid(String isValid) {
		this.isValid = isValid;
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
	
	public SortedSet<ChecklistType> getTypes() {
		return types;
	}

	public void setTypes(SortedSet<ChecklistType> types) {
		this.types = types;
	}
	
	public enum FormTypeConst {
		APP_ONE			("F1",	"Приложение 1"),
		APP_TWO			("F2",	"Приложение 2"),
		APP_TWO_G		("F3",	"Приложение 2ж"),
		APP_FOUR		("F4",	"Приложение 4"),
		PROJECT			("F7",	"Проектно предложение"),
		ACTIVITY		("F8",	"Дейност"),
		TECH_SPECS		("F9",	"Техническа спецификация");
		
		private final String code;
		private final String name;

		private FormTypeConst(String code, String name) {
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
