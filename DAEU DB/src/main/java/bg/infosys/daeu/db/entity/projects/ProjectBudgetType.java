package bg.infosys.daeu.db.entity.projects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "projects", name = "n_project_budget_types")
public class ProjectBudgetType {
	@Id
	@SequenceGenerator(name = "n_project_budget_types_seq", sequenceName = "n_project_budget_types_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "n_project_budget_types_seq")
	private Integer id;
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = _placeholder)
	private String placeholder;
	public static final String _placeholder = "placeholder";
	
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
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
	
	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public enum BudgetTypeConst {
//		E_GOV		("EGOV", "Разходи за дейности по електронно управление и ИКТ"),
//		ICT			("ICT", "Разходи за ИКТ дейности"),
		BUDGET		("BUDGET", "Обща стойност на проектното предложение ");
		
		private final String name;
		private final String code;

		private BudgetTypeConst(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getName() {
			return name;
		}
		
		public String getCode() {
			return code;
		}
	}
}
