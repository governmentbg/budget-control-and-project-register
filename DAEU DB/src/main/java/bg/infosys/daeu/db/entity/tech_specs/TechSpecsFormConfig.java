package bg.infosys.daeu.db.entity.tech_specs;

import java.util.Date;
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
import org.hibernate.validator.constraints.Length;

import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.db.entity.pub.TranslationTechSpecsConfig;

@Audited
@Entity
@Table(schema = "tech_specs" ,name = "tech_specs_form_config")
public class TechSpecsFormConfig implements Comparable<TechSpecsFormConfig> {
	@Id
	@SequenceGenerator(name = "tech_specs_form_config_seq", sequenceName = "tech_specs.tech_specs_form_config_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tech_specs_form_config_seq")
	private Integer id;
	
	@Column(name = "order_num")
	private Integer orderNum;
	public static final String _orderNum = "orderNum";
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tech_specs_row_type_id")
	private TechRowType rowType;
	public static final String _rowType = "rowType";
	
	@ManyToOne
	@JoinColumn(name = "tech_specs_form_type_id")
	private FormType formType;
	public static final String _formType = "formType";
	
	@Column(name = "row_label")
	@Length(max = 512)
	private String rowLabel;
	public static final String _rowLabel = "rowLabel";
	
	@Column(name = _placeholder)
	@Length(max = 512)
	private String placeholder;
	public static final String _placeholder = "placeholder";
	
	@Column(name = "is_valid")
	@Length(max = 1)
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	@Column(name = "section_num")
	private Short sectionNum;
	public static final String _sectionNum = "sectionNum";
	
	@Column(name = "date_created")
	private Date dateCreated = new Date();
	public static final String _dateCreated = "dateCreated";
	
	@Column(name = "is_public_field")
	private String isPublicField;
	public static final String _isPublicField = "isPublicField";
	
	@Column(name = "is_deployable_field")
	private String isDeployableField;
	public static final String _isDeployableField = "isDeployableField";
	
	@ManyToOne
	@JoinColumn(name = "n_tech_specs_id")
	private TechSpecsType techSpecsType;
	public static final String _techSpecsType = "techSpecsType";
	
	@OneToMany(mappedBy = "config", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<TranslationTechSpecsConfig> translationConfigs = new LinkedHashSet<TranslationTechSpecsConfig>();
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
	
	public TechRowType getRowType() {
		return rowType;
	}
	
	public void setRowType(TechRowType rowType) {
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
	
	public Short getSectionNum() {
		return sectionNum;
	}
	
	public void setSectionNum(Short sectionNum) {
		this.sectionNum = sectionNum;
	}

	public TechSpecsType getTechSpecsType() {
		return techSpecsType;
	}

	public void setTechSpecsType(TechSpecsType techSpecsType) {
		this.techSpecsType = techSpecsType;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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
	
	public Set<TranslationTechSpecsConfig> getTranslationConfigs() {
		return translationConfigs;
	}
	
	public void setTranslationConfigs(Set<TranslationTechSpecsConfig> translationConfigs) {
		this.translationConfigs = translationConfigs;
	}
	
	//NO
//	public TechSpecsFormConfig copyFrom(TechSpecsFormConfig that) {
//		if (that != null) {
//			this.setOrderNum(that.getOrderNum());
//			this.setRowLabel(that.getRowLabel());
//			this.setPlaceholder(that.getPlaceholder());
//			this.setRowType(that.getRowType());
//			this.setTechSpecsType(that.getTechSpecsType());
//		}
//		
//		return this;
//	}
	
	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
	public String getLabelName() {
		String labelName = "";
		for (TranslationTechSpecsConfig config : this.translationConfigs) {
			if (config.getLanguage().getCode().equals(lang)) {
				labelName = config.geteTranslation().getTranslatedText();
			}
		}
		if (labelName.equals("")) {
			labelName = this.rowLabel;
		}
		
		return labelName;
	}

	@Override
	public int compareTo(TechSpecsFormConfig o) {
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
