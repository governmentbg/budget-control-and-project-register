package bg.infosys.daeu.db.entity.budgets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.ComponentType.ComponentTypeConst;
import bg.infosys.daeu.db.entity.pub.EBKCode;

@Audited
@Entity
@Table(schema = "budgets", name = "row_values")
public class RowValue implements Comparable<RowValue> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public static final String _id = "id";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "row_id")
	private VersionRow versionRow;
	public static final String _versionRow = "versionRow";
	
	@Column(name = _value)
	private Integer value=0;
	public static final String _value = "value";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_config_id")
	private FormConfig formConfig;
	public static final String _formConfig = "formConfig";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ebk_code_id")
	private EBKCode ebkCode;
	public static final String _ebkCode = "ebkCode";
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "selected_cpv_code_id")
	private SelectedCPVCode selectedCpvCode;
	public static final String _selectedCpvCode = "selectedCpvCode";
	
	@Column(name = "automatic_value")
	private Integer automaticValue;
	public static final String _automaticValue = "automaticValue";
	
	@Column(name = "approved_value")
	private String approvedValue;
	public static final String _approvedValue = "approvedValue";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "row_value_type_code")
	private RowValueType rowValueType;
	public static final String _rowValueType = "rowValueType";
	
	@Column(name = "text_value")
	private String textValue;
	public static final String _textValue = "textValue";
	
	@OneToMany(mappedBy = "rowValue", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<RowValueChangeType> rowValueChangeTypes = new HashSet<RowValueChangeType>();
	public static final String _rowValueChangeTypes = "rowValueChangeTypes";
	
	@Where(clause = "is_valid = 'Y'")
	@OneToMany(mappedBy = "rowValue", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ExpenseDetail> expenseDetails;
	public static final String _expenseDetails = "expenseDetails";
	
	@Transient
	private boolean hasDifference;
	
	@Transient
	private boolean isVisible = true;
	
	public RowValue() {}
	
	public RowValue(FormConfig formConfig, VersionRow versionRow) {
		this.formConfig = formConfig;
		this.versionRow = versionRow;
	}
	
	public RowValue(VersionRow versionRow, FormConfig formConfig, RowValueType rowValueType) {
		this.versionRow = versionRow;
		this.formConfig = formConfig;
		this.rowValueType = rowValueType;
	}
	
	@Override
	public int compareTo(RowValue o) {
		return this.formConfig.getOrderNum() - ((o).getFormConfig().getOrderNum());
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public VersionRow getVersionRow() {
		return versionRow;
	}
	
	public void setVersionRow(VersionRow versionRow) {
		this.versionRow = versionRow;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
	}
	
	public FormConfig getFormConfig() {
		return formConfig;
	}
	
	public void setFormConfig(FormConfig formConfig) {
		this.formConfig = formConfig;
	}
	
	public EBKCode getEbkCode() {
		return ebkCode;
	}
	
	public void setEbkCode(EBKCode ebkCode) {
		this.ebkCode = ebkCode;
	}
	
	public SelectedCPVCode getSelectedCpvCode() {
		return selectedCpvCode;
	}
	
	public void setSelectedCpvCode(SelectedCPVCode selectedCpvCode) {
		this.selectedCpvCode = selectedCpvCode;
	}
	
	public Integer getAutomaticValue() {
		return automaticValue;
	}
	
	public void setAutomaticValue(Integer automaticValue) {
		this.automaticValue = automaticValue;
	}
	
	public String getApprovedValue() {
		return approvedValue;
	}
	
	public void setApprovedValue(String approvedValue) {
		this.approvedValue = approvedValue;
	}

	public RowValueType getRowValueType() {
		return rowValueType;
	}

	public void setRowValueType(RowValueType rowValueType) {
		this.rowValueType = rowValueType;
	}

	public String getTextValue() {
		return textValue;
	}

	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	public Set<RowValueChangeType> getRowValueChangeTypes() {
		return rowValueChangeTypes;
	}

	public void setRowValueChangeTypes(Set<RowValueChangeType> rowValueChangeTypes) {
		this.rowValueChangeTypes = rowValueChangeTypes;
	}
	
	public Set<ExpenseDetail> getExpenseDetails() {
		return expenseDetails;
	}

	public void setExpenseDetails(Set<ExpenseDetail> expenseDetails) {
		this.expenseDetails = expenseDetails;
	}
	
	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public RowValue copyFrom(VersionRow versionRow, RowValue that) {
		if (that != null) {
			this.versionRow = versionRow;
			this.value = that.value;
			this.formConfig = that.formConfig;
			this.ebkCode = that.ebkCode;
			this.selectedCpvCode = that.selectedCpvCode;
			this.automaticValue = that.value;
			this.approvedValue = that.approvedValue;
			this.rowValueType = that.rowValueType;
			this.textValue = that.textValue;
			this.rowValueChangeTypes = new HashSet<RowValueChangeType>();
			for (RowValueChangeType changeType : that.rowValueChangeTypes) {
				this.rowValueChangeTypes.add(new RowValueChangeType().copyFrom(this, changeType));
			}
			if (that.expenseDetails != null) {
				this.expenseDetails = new LinkedHashSet<ExpenseDetail>();
				Iterator<ExpenseDetail> iterator = that.expenseDetails.iterator();
				while (iterator.hasNext()) {
					this.expenseDetails.add(new ExpenseDetail().copyFrom(this, iterator.next()));
				}
			}
		}
		return this;
	}
	
	public RowValue copyFromPrevApp(VersionRow versionRow, RowValue that, Map<FormConfig, FormConfig> newConfigs) {
		if (that != null) {
			this.versionRow = versionRow;
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
			this.ebkCode = that.ebkCode;
			if (that.selectedCpvCode != null && that.selectedCpvCode.getCpvCode() != null) {
				this.selectedCpvCode = new SelectedCPVCode(that.selectedCpvCode.getCpvCode());
			}
			this.automaticValue = that.value;
			this.approvedValue = that.approvedValue;
			this.rowValueType = that.rowValueType;
			this.textValue = that.textValue;
			this.rowValueChangeTypes = new HashSet<RowValueChangeType>();
			for (RowValueChangeType changeType : that.rowValueChangeTypes) {
				this.rowValueChangeTypes.add(new RowValueChangeType().copyFrom(this, changeType));
			}
			if (that.expenseDetails != null) {
				this.expenseDetails = new LinkedHashSet<ExpenseDetail>();
				Iterator<ExpenseDetail> iterator = that.expenseDetails.iterator();
				while (iterator.hasNext()) {
					this.expenseDetails.add(new ExpenseDetail().copyFrom(this, iterator.next()));
				}
			}
		}
		return this;
	}

	public boolean isHasDifference() {
		if (this.value != null && this.automaticValue != null && (this.approvedValue == null || this.approvedValue != null && !this.approvedValue.equals("Y")) && 
				!this.value.equals(this.automaticValue) && this.formConfig.getFormColumnType().getComponentType().getCode().equals(ComponentTypeConst.NUM.getCode()) && 
						this.formConfig.getFormColumnType().getIsSummed().equals("Y") && !this.formConfig.getFormColumnType().getCode().equals("TOTAL")) {
			return true;
		}
		return false;
	}
}
