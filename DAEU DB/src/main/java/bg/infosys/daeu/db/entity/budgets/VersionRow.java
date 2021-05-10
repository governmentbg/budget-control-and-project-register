package bg.infosys.daeu.db.entity.budgets;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
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

import bg.infosys.daeu.db.entity.pub.ExpenseType;

@Audited
@Entity
@Table(schema = "budgets", name = "version_rows")
public class VersionRow{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public static final String _id="id";

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "version_id")
	private BudgetFormVersion budgetFormVersion;
	public static final String _budgetFormVersion = "budgetFormVersion";

	@Column(name = _section)
	private String section;
	public static final String _section = "section";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "expense_type_code")
	private ExpenseType expenseType;
	public static final String _expenseType = "expenseType";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";

	@OrderBy(RowValue._formConfig)
	@OneToMany(mappedBy = "versionRow", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private SortedSet<RowValue> values = new TreeSet<RowValue>();
	public static final String _values = "values";
	
	public VersionRow() {
		isValid="Y";
	}
	
	public VersionRow(VersionRow row) {
		this.id = row.id;
		this.budgetFormVersion = row.budgetFormVersion;
		this.section = row.section;
		this.expenseType = row.expenseType;
		this.values = row.values;
		this.isValid = row.isValid;
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

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public SortedSet<RowValue> getValues() {
		return values;
	}

	public void setValues(SortedSet<RowValue> values) {
		this.values = values;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}
	
	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}
	
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public VersionRow copyFrom(BudgetFormVersion version, VersionRow that) {
		if (that != null) {
			this.budgetFormVersion = version;
			this.section = that.section;
			this.expenseType = that.expenseType;
			this.isValid = that.isValid;
			this.values = new TreeSet<RowValue>();
			for (RowValue value : that.values) {
				this.values.add(new RowValue().copyFrom(this, value));
			}
		}
		return this;
	}
	
	public VersionRow copyFromPrevApp(BudgetFormVersion version, VersionRow that, Map<FormConfig, FormConfig> newConfigs) {
		if (that != null) {
			this.budgetFormVersion = version;
			this.section = that.section;
			this.expenseType = that.expenseType;
			this.isValid = that.isValid;
			this.values = new TreeSet<RowValue>();
			for (RowValue value : that.values) {
				this.values.add(new RowValue().copyFromPrevApp(this, value, newConfigs));
			}
		}
		return this;
	}

}
