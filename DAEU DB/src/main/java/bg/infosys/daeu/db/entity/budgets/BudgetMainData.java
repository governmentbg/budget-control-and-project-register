package bg.infosys.daeu.db.entity.budgets;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "budgets", name = "budget_main_data")
public class BudgetMainData{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "budget_form_version_id")
	private BudgetFormVersion budgetFormVersion;
	public static final String _budgetFormVersion = "budgetFormVersion";
	
	@OrderBy("formConfig asc")
	@OneToMany(mappedBy = "budgetMainData", cascade = CascadeType.ALL)
	private SortedSet<BudgetMainDataValue> budgetMainDataValues;
	public static final String _budgetMainDataValues = "budgetMainDataValues";
	
	public BudgetMainData() {
		this.budgetMainDataValues = new TreeSet<BudgetMainDataValue>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BudgetFormVersion getBudgetFormVersion() {
		return budgetFormVersion;
	}

	public void setBudgetFormVersion(BudgetFormVersion budgetFormVersion) {
		this.budgetFormVersion = budgetFormVersion;
	}

	public SortedSet<BudgetMainDataValue> getBudgetMainDataValues() {
		return budgetMainDataValues;
	}

	public void setBudgetMainDataValues(SortedSet<BudgetMainDataValue> budgetMainDataValues) {
		this.budgetMainDataValues = budgetMainDataValues;
	}

	public BudgetMainData copyFrom(BudgetFormVersion budgetFormVersion, BudgetMainData that, Map<FormConfig, FormConfig> newConfigs) {
		if (that != null) {
			this.budgetFormVersion = budgetFormVersion;
			this.budgetMainDataValues = new TreeSet<BudgetMainDataValue>();
			for (BudgetMainDataValue value : that.budgetMainDataValues) {
				this.budgetMainDataValues.add(new BudgetMainDataValue().copyFrom(this, value, newConfigs));
			}
		}
		return this;
	}

}
