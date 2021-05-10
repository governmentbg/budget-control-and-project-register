package bg.infosys.daeu.db.entity.budgets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "budgets", name = "n_sum_budget_form_types")
public class SumBudgetFormType {

	@Id
	@Column(name = _code, nullable = false)
	private String code;
	public static final String _code = "code";
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
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
	
	public enum SumBudgetFormTypeConst {
		
		TOTAL 		("TOTAL", 		"Обобщена"),
		NOT_TOTAL 	("NOTTOTAL", 	"Необобщена");
		
		private final String code;
		private final String name;

		private SumBudgetFormTypeConst(String code, String name) {
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
