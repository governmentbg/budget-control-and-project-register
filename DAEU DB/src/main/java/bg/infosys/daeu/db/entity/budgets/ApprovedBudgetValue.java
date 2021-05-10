package bg.infosys.daeu.db.entity.budgets;

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

import bg.infosys.daeu.db.entity.pub.ExpenseType;

@Audited
@Entity
@Table(name = "n_approved_budget_values")
public class ApprovedBudgetValue {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_budget_id")
	private ApprovedBudget approvedBudget;
	public static final String _approvedBudget = "approvedBudget";
	
	@Column(name = _value)
	private Integer value = 0;
	public static final String _value = "value";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "expense_type_code")
	private ExpenseType expenseType;
	public static final String _expenseType = "expenseType";
	
	public ApprovedBudgetValue() {}
	
	public ApprovedBudgetValue(Integer id, Integer currentValue, ApprovedBudget approvedBudget, ExpenseType type) {
		this.id = id;
		this.value = currentValue;
		this.approvedBudget = approvedBudget;
		this.expenseType = type;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public ApprovedBudget getApprovedBudget() {
		return approvedBudget;
	}
	
	public void setApprovedBudget(ApprovedBudget approvedBudget) {
		this.approvedBudget = approvedBudget;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public ExpenseType getExpenseType() {
		return expenseType;
	}
	
	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public ApprovedBudgetValue copyFrom(ApprovedBudgetValue that) {
		if (that != null) {
			this.id = that.id;
			this.approvedBudget = that.approvedBudget;
			this.value = that.value;
			this.expenseType = that.expenseType;
		}
		return this;
	}
	
}
