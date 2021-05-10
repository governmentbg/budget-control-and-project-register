
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

import bg.infosys.daeu.db.entity.pub.TranslationBudgetAuxheader;

@Audited
@Entity
@Table(schema = "budgets", name = "auxheaders")
public class Auxheader {
	@Id
	@SequenceGenerator(name = "auxheaders_seq", sequenceName = "budgets.auxheaders_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auxheaders_seq")
	private Integer id;
	
	@Column(name = _label)
	private String label;
	public static final String _label = "label";
	
	@Column(name = _colspan)
	private Integer colspan;
	public static final String _colspan = "colspan";
	
	@Column(name = _year)
	private String year;
	public static final String _year = "year";
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "auxheader_type_code")
	private AuxheaderType auxheaderType;
	public static final String _auxheaderType = "auxheaderType";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@OneToMany(mappedBy = "auxheader", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<TranslationBudgetAuxheader> translationAuxheader = new LinkedHashSet<TranslationBudgetAuxheader>();
	public static final String _translationAuxheader = "translationAuxheader";
	
	@Transient
	private boolean collapse;
	
	@Transient
	private String labelName;
	
	@Transient
	private String lang;
	
	public Auxheader() {}
	
	public Auxheader(String label, Integer colspan, String year, AuxheaderType type) {
		this.isValid = "Y";
		this.label = label;
		this.colspan = colspan;
		this.year = year;
		this.auxheaderType = type;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Integer getColspan() {
		return colspan;
	}
	
	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public AuxheaderType getAuxheaderType() {
		return auxheaderType;
	}
	
	public void setAuxheaderType(AuxheaderType auxheaderType) {
		this.auxheaderType = auxheaderType;
	}
	
	public String getIsValid() {
		return isValid;
	}
	
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public boolean isCollapse() {
		return collapse;
	}

	public void setCollapse(boolean collapse) {
		this.collapse = collapse;
	}
	
	public Set<TranslationBudgetAuxheader> getTranslationAuxheader() {
		return translationAuxheader;
	}

	public void setTranslationAuxheader(Set<TranslationBudgetAuxheader> translationAuxheader) {
		this.translationAuxheader = translationAuxheader;
	}

	public String getLabelName() {
		if (!collapse) {
			String labelName = "";
			for (TranslationBudgetAuxheader config : this.translationAuxheader) {
				if (config.getLanguage().getCode().equals(lang)) {
					labelName = config.geteTranslation().getTranslatedText();
				}
			}
			if (labelName.equals("")) {
				labelName = this.label;
			}
			
			return labelName;
		} 
		if (this.auxheaderType.getShortName() != null) {
			return this.auxheaderType.getShortName() + " " + this.year;
		}
		return this.year;
	}
	
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Auxheader copyFrom(Auxheader that, String nextYear) {
		if (that != null ) {
			this.label = that.label;
			this.colspan = that.colspan;
			this.year = nextYear;
			this.auxheaderType = that.auxheaderType;
			this.isValid = that.isValid;
		}
		return this;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
}
