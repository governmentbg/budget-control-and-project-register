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

@Audited
@Entity
@Table(schema = "budgets", name = "main_data_info")
public class MainDataInfo implements Comparable<MainDataInfo>{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = _year)
	private Integer year;
	public static final String _year = "year";
	
	@Column(name = "expense_type")
	private String expenseType;
	public static final String _expenseType = "expenseType";
	
	@Column(name = _budget)
	private Integer budget;
	public static final String _budget = "budget";
	
	@Column(name = _description)
	private String description;
	public static final String _description = "description";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "budget_main_data_value_id")
	private BudgetMainDataValue mainDataValue;
	public static final String _mainDataValue = "mainDataValue";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	public MainDataInfo() {
		isValid = "Y";
	}
	
	public MainDataInfo(BudgetMainDataValue mainDataValue) {
		this.mainDataValue = mainDataValue;
		isValid = "Y";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
	public Integer getBudget() {
		return budget;
	}
	public void setBudget(Integer budget) {
		this.budget = budget;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BudgetMainDataValue getMainDataValue() {
		return mainDataValue;
	}
	public void setMainDataValue(BudgetMainDataValue mainDataValue) {
		this.mainDataValue = mainDataValue;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public MainDataInfo copyFrom(BudgetMainDataValue mainDataValue, MainDataInfo that) {
		if (that != null) {
			this.year = that.year;
			this.expenseType = that.expenseType;
			this.budget = that.budget;
			this.description = that.description;
			this.mainDataValue = mainDataValue;
			this.isValid = that.isValid;
		}
		return this;
	}

	@Override
	public int compareTo(MainDataInfo o) {
		if(this.year != null && o.getYear() != null) {
			return this.year.compareTo(o.getYear());
		} else if(this.id != null && o.getId() != null) {
			return this.id.compareTo(o.getId());
		}
		return 1;
	}


}
