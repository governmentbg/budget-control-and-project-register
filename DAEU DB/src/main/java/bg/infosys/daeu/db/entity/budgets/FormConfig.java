package bg.infosys.daeu.db.entity.budgets;

import java.util.LinkedHashSet;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.db.entity.pub.TranslationBudgetConfig;

@Audited
@Entity
@Table(schema = "budgets", name = "form_config")
public class FormConfig {
	@Id
	@SequenceGenerator(name = "form_config_seq", sequenceName = "budgets.form_config_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "form_config_seq")
	private Integer id;
	
	@Column(name = "order_num")
	private Integer orderNum;
	public static final String _orderNum = "orderNum";

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "column_type_id")
	private FormColumnType formColumnType;
	public static final String _formColumnType = "formColumnType";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_type_id")
	private FormType formType;
	public static final String _formType = "formType";
	
	@Column(name = "column_name")
	private String columnName;
	public static final String _columnName = "columnName";
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "auxheader_id")
	private Auxheader auxheader;
	public static final String _auxheader = "auxheader";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@Column(name = _section)
	private String section;
	public static final String _section = "section";
	
	@Column(name = "is_deployable_field")
	private String isDeployableField;
	public static final String _isDeployableField = "isDeployableField";
	
	@Column(name = "year")
	private Integer year;
	public static final String _year="year";
	
	@OneToMany(mappedBy = "formConfig", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<TranslationBudgetConfig> translationConfigs = new LinkedHashSet<TranslationBudgetConfig>();
	public static final String _translationConfigs = "translationConfigs";
	
	@Transient
	private boolean isVisible = true;
	
	@Transient
	private String lang;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}
	
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	public FormColumnType getFormColumnType() {
		return formColumnType;
	}
	
	public void setFormColumnType(FormColumnType formColumnType) {
		this.formColumnType = formColumnType;
	}
	
	public FormType getFormType() {
		return formType;
	}
	
	public void setFormType(FormType formType) {
		this.formType = formType;
	}
	
	public String getColumnName() {
		return columnName;
	}
	
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public Auxheader getAuxheader() {
		return auxheader;
	}
	
	public void setAuxheader(Auxheader auxheader) {
		this.auxheader = auxheader;
	}
	
	public String getIsValid() {
		return isValid;
	}
	
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	public String getSection() {
		return section;
	}
	
	public void setSection(String section) {
		this.section = section;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public String getIsDeployableField() {
		return isDeployableField;
	}

	public void setIsDeployableField(String isDeployableField) {
		this.isDeployableField = isDeployableField;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Set<TranslationBudgetConfig> getTranslationConfigs() {
		return translationConfigs;
	}

	public void setTranslationConfigs(Set<TranslationBudgetConfig> translationConfigs) {
		this.translationConfigs = translationConfigs;
	}
	
	public String getLabelName() {
		String labelName = "";
		for (TranslationBudgetConfig config : this.translationConfigs) {
			if (config.getLanguage().getCode().equals(lang)) {
				labelName = config.geteTranslation().getTranslatedText();
			}
		}
		if (labelName.equals("")) {
			labelName = this.columnName;
		}
		
		return labelName;
	}
	
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public FormConfig copyFrom(FormConfig that, Integer year) {
		if (that != null) {
			this.orderNum = that.orderNum;
			this.formColumnType = that.formColumnType;
			this.formType = that.formType;
			this.columnName = that.columnName;
			this.auxheader = that.auxheader;
			this.isValid = that.isValid;
			this.section = that.section;
			this.isDeployableField = that.isDeployableField;
			this.year = year;
		}
		return this;
	}
	
}
