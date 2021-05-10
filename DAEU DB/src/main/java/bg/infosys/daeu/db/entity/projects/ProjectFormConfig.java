package bg.infosys.daeu.db.entity.projects;

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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.db.entity.pub.TranslationProjectConfig;

@Audited
@Entity
@Table(schema = "projects", name = "project_form_config")
public class ProjectFormConfig implements Comparable<ProjectFormConfig>{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "order_num")
	private Integer orderNum;
	public static final String _orderNum = "orderNum";
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "row_type_id")
	private RowType rowType;
	public static final String _rowType = "rowType";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "form_type_id")
	private FormType formType;
	public static final String _formType = "formType";
	
	@Column(name = "row_label")
	private String rowLabel;
	public static final String _rowLabel = "rowLabel";
	
	@Column(name = _placeholder)
	private String placeholder;
	public static final String _placeholder = "placeholder";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@Column(name = "is_public_field")
	private String isPublicField;
	public static final String _isPublicField = "isPublicField";
	
	@Column(name = "is_deployable_field")
	private String isDeployableField;
	public static final String _isDeployableField = "isDeployableField";
	
	@Column(name = "isun_key")
	private String isunKey;
	public static final String _isunKey = "isunKey";
	
	@Column(name = "step_num")
	private Short stepNum;
	public static final String _stepNum = "stepNum";
	
	@OneToMany(mappedBy = "config", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<TranslationProjectConfig> translationConfigs = new LinkedHashSet<TranslationProjectConfig>();
	public static final String _translationConfigs = "translationConfigs";
	
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
	
	public RowType getRowType() {
		return rowType;
	}
	
	public void setRowType(RowType rowType) {
		this.rowType = rowType;
	}
	
	public FormType getFormType() {
		return formType;
	}
	
	public void setFormType(FormType formType) {
		this.formType = formType;
	}
	
	public String getRowLabel() {
		return rowLabel;
	}
	
	public void setRowLabel(String rowLabel) {
		this.rowLabel = rowLabel;
	}
	
	public String getPlaceholder() {
		return placeholder;
	}
	
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
	
	public String getIsValid() {
		return isValid;
	}
	
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getIsPublicField() {
		return isPublicField;
	}

	public void setIsPublicField(String isPublicField) {
		this.isPublicField = isPublicField;
	}

	public String getIsDeployableField() {
		return isDeployableField;
	}

	public void setIsDeployableField(String isDeployableField) {
		this.isDeployableField = isDeployableField;
	}

	public Short getStepNum() {
		return stepNum;
	}

	public void setStepNum(Short stepNum) {
		this.stepNum = stepNum;
	}
	
	public Set<TranslationProjectConfig> getTranslationConfigs() {
		return translationConfigs;
	}

	public void setTranslationConfigs(Set<TranslationProjectConfig> translationConfigs) {
		this.translationConfigs = translationConfigs;
	}
	
	public String getIsunKey() {
		return isunKey;
	}

	public void setIsunKey(String isunKey) {
		this.isunKey = isunKey;
	}

	public String getLabelName() {
		String labelName = "";
		for (TranslationProjectConfig config : this.translationConfigs) {
			if (config.getLanguage().getCode().equals(lang)) {
				labelName = config.geteTranslation().getTranslatedText();
			}
		}
		if (labelName.equals("")) {
			labelName = this.rowLabel;
		}
		
		return labelName;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public int compareTo(ProjectFormConfig o) {
		if (this.orderNum == null) {
			return -1;
		} else if(o.getOrderNum() == null) {
			return 1;
		} else if (this.orderNum == null && o.getOrderNum() == null) {
			return 0;
		} else {
			return this.orderNum - o.getOrderNum();
		}
	}
}
