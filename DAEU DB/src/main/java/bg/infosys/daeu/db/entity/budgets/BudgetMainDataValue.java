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

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "budgets", name = "budget_main_data_values")
public class BudgetMainDataValue implements Comparable<BudgetMainDataValue>{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "budget_main_data_id")
	private BudgetMainData budgetMainData;
	public static final String _budgetMainData = "budgetMainData";
	
	@Column(name = _value)
	private String value;
	public static final String _value = "value";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "budget_form_config_id")
	private FormConfig formConfig;
	public static final String _formConfig = "formConfig";
	
	@Where(clause = "is_valid = 'Y'")
	@OrderBy("year")
	@OneToMany(mappedBy = "mainDataValue", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private SortedSet<MainDataInfo> budgetMainDataInfo;
	public static final String _budgetMainDataInfo = "budgetMainDataInfo";
	
	
	public BudgetMainDataValue() {}
	
	public BudgetMainDataValue(BudgetMainData budgetMainData, FormConfig formConfig) {
		this.budgetMainData = budgetMainData;
		this.formConfig = formConfig;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BudgetMainData getBudgetMainData() {
		return budgetMainData;
	}

	public void setBudgetMainData(BudgetMainData budgetMainData) {
		this.budgetMainData = budgetMainData;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FormConfig getFormConfig() {
		return formConfig;
	}

	public void setFormConfig(FormConfig formConfig) {
		this.formConfig = formConfig;
	}

	
	public SortedSet<MainDataInfo> getBudgetMainDataInfo() {
		return budgetMainDataInfo;
	}

	public void setBudgetMainDataInfo(SortedSet<MainDataInfo> budgetMainDataInfo) {
		this.budgetMainDataInfo = budgetMainDataInfo;
	}

	public BudgetMainDataValue copyFrom(BudgetMainData data, BudgetMainDataValue that, Map<FormConfig, FormConfig> newConfigs) {
		if (that != null) {
			this.budgetMainData = data;
			this.value = that.value;
			if (newConfigs != null) {
				FormConfig newConfig = newConfigs.get(that.formConfig);
				if (newConfig != null) {
					this.formConfig = newConfig;
				} else {
					this.formConfig = that.formConfig;
				}
			} else {
				this.formConfig = that.formConfig;
			}
			this.budgetMainDataInfo = new TreeSet<MainDataInfo>();
			for (MainDataInfo value : that.budgetMainDataInfo) {
				this.budgetMainDataInfo.add(new MainDataInfo().copyFrom(this, value));
			}
		}
		return this;
	}

	@Override
	public int compareTo(BudgetMainDataValue o) {
		return this.formConfig.getOrderNum().compareTo(o.getFormConfig().getOrderNum());
	}

}
