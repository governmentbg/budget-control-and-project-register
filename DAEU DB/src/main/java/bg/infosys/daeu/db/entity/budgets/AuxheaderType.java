package bg.infosys.daeu.db.entity.budgets;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(schema = "budgets", name = "n_auxheader_types")
public class AuxheaderType {
	@Id
	@Column(name = _code, nullable = false)
	private String code;
	public static final String _code = "code";
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@Column(name = "is_visible")
	private String isVisible;
	public static final String _isVisible = "isVisible";
	
	@Column(name = "short_name")
	private String shortName;
	public static final String _shortName="shortName";
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "auxheaderType")
	private List<Auxheader> auxheaders;
	
	public AuxheaderType() {}
	
	public AuxheaderType(String code, String name, String isVisible, String shortName) {
		this.code = code;
		this.name = name;
		this.isValid = "Y";
		this.isVisible = isVisible;
		this.shortName = shortName;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsValid() {
		return isValid;
	}
	
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public List<Auxheader> getAuxheaders() {
		return auxheaders;
	}

	public void setAuxheaders(List<Auxheader> auxheaders) {
		this.auxheaders = auxheaders;
	}
	
	public String getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public enum AuxheaderTypeConst {
		
		APPROVED		("APPROVED",		"Утвърден бюджет"),
		CHANGE  		("CHANGE", 			"Предложение за промяна"),
		CPV  			("CPV", 			"CPV"),
		EBK  			("EBK", 			"EBK"),
		EXPENSE 		("EXPENSE", 		"Разход"),
		F3 				("F3", 				"Колони със стойности във форма F3"),
		F3CEILING 		("F3CEILING", 		"Разходен таван"),
		F3CHANGE  		("F3CHANGE", 		"Изменение"),
		F3FORECAST		("F3FORECAST", 		"Прогноза"),
		F3LABELS 		("F3LABELS", 		"Колони с лейбъли"),
		F3LAW  			("F3LAW", 			"Закон"),
		F3REPORT 		("F3REPORT", 		"Отчет"),
		FUND  			("FUND", 			"Прогнози"),
		Q1  			("Q1", 				"Отчет за първо тримесечие"),
		Q2 				("Q2", 				"Отчет за второ тримесечие"),
		Q3   			("Q3", 				"Отчет за трето тримесечие"),
		Q4   			("Q4", 				"Отчет за четвърто тримесечие"),
		REPORT  		("REPORT", 			"Годишен отчет"),
		NEW_TYPE        ("NEW_TYPE",        "AuxheaderType добавен от конфига");
		
		private final String code;
		private final String name;
		
		private AuxheaderTypeConst(String code, String name) {
			this.code = code;
			this.name = name;
		}
		
		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}
}
