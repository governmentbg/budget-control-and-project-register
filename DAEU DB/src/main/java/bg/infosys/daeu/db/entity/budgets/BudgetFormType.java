package bg.infosys.daeu.db.entity.budgets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.FormType;

@Audited
@Entity
@Table(schema = "budgets", name = "n_budget_form_types")
public class BudgetFormType {

	@Id
	@Column(name = _code, nullable = false)
	private String code;
	public static final String _code = "code";
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_type_id")
	private FormType formType;
	public static final String _formType = "formType";
	
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
	public FormType getFormType() {
		return formType;
	}
	public void setFormType(FormType formType) {
		this.formType = formType;
	}


	public enum BudgetFormTypeConst {
		FORECAST 		("F", 	"Тригодишна бюджетна прогноза"),
		DRAFT_BUDGET 	("DB", 	"Проектобюджет и актуализирани прогнози"),
		APPR_BUDGET		("AB", 	"Утвърден бюджет"),
		CHANGE	 		("CH", 	"Промени"),
		REPORT	 		("R", 	"Отчети");
		
		private final String code;
		private final String name;

		private BudgetFormTypeConst(String code, String name) {
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
