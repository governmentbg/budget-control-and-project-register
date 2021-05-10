package bg.infosys.daeu.db.entity.budgets;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.ComponentType;
import bg.infosys.daeu.db.entity.pub.ExpenseType;

@Audited
@Entity
@Table(schema = "budgets", name = "n_form_column_types")
public class FormColumnType {
	@Id
	@SequenceGenerator(name = "n_form_column_types_seq", sequenceName = "budgets.n_form_column_types_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "n_form_column_types_seq")
	private Integer id;
	public static final String _id = "id";
	
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	@ManyToOne()
	@JoinColumn(name = "component_type_code")
	private ComponentType componentType;
	public static final String _componentType = "componentType";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@Column(name = "is_summed")
	private String isSummed;
	public static final String _isSummed = "isSummed";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "expense_type_code")
	private ExpenseType expenseType;
	public static final String _expenseType = "expenseType";
	
	@OneToMany(mappedBy = "formColumnType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy
	@Where(clause = "is_valid = 'Y'")
	private SortedSet<BudgetNomenclatures> numenclatures;
	public static final String _numenclatures = "numenclatures";
	
	public FormColumnType() {
		numenclatures = new TreeSet<BudgetNomenclatures>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public ComponentType getComponentType() {
		return componentType;
	}
	
	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}
	
	public String getIsValid() {
		return isValid;
	}
	
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getIsSummed() {
		return isSummed;
	}

	public void setIsSummed(String isSummed) {
		this.isSummed = isSummed;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}
	
	public SortedSet<BudgetNomenclatures> getNumenclatures() {
		return numenclatures;
	}

	public void setNumenclatures(SortedSet<BudgetNomenclatures> numenclatures) {
		this.numenclatures = numenclatures;
	}



	public enum FormColumnTypeConst {
		ELEMENTORDER		("ELEMENTORDER",	"Номер на CPV"),
		CPVCODE  			("CPVCODE", 		"CPVCODE"),
		CPVNAME 			("CPVNAME", 		"CPVNAME"),
		EBKCODE 			("EBKCODE", 		"EBKCODE"),
		UNITS 				("UNITS", 			"Брой единици"),
		FUND  				("FUND", 			"Бюджет по АО"),
		FUND2  				("FUND2", 			"ЕСИФ"),
		FUND3  				("FUND3", 			"Други"),
		TOTAL  				("TOTAL", 			"Обща сума"),
		CHANGE  			("CHANGE", 			"Предложение за промяна"),
		F4CAPITAL  			("F4CAPITAL", 		"Капиталови във форма Предложение за промяна"),
		F4TXT  				("F4TXT", 			"Текстови полета в Предложение за промяна"),
		F4COMBO  			("F4COMBO", 		"Комбобокс в Предложение за промяна"),
		CURRENT  			("CURRENT", 		"Текущи разходи"),
		CAPITAL  			("CAPITAL", 		"Капиталови разходи"),
		TOTALCOSTS  		("TOTALCOSTS", 		"Общи разходи"),
		TXT  				("TXT", 			"Текстови полета"),
		ADDGRD  			("ADDGRD", 			"Комбобокс в Добавяне на бюджетна година"),
		YN  				("FYN", 			"Радио бутони Да/Не"),
		ADDGRDDSC  			("ADDGRDDSC", 		"Комбобокс 2 в Добавяне на бюджетна година"),
		F4CURRENT 			("F4CURRENT", 		"Текущи разходи в Предложение за промяна"),
		F3LAW  				("F3LAW", 			"Закон в форма F3"),
		F3REPORT  			("F3REPORT", 		"Отчет във форма F3"),
		F1TXT  				("F1TXT", 			"Текстови във форма F1"),
		FTXT  				("FTXT", 			"Текстови във форма F1 - 2ро задължително"),
		F1YN  				("F1YN", 			"Радио 2 бутони Да/Не");
		
		private final String code;
		private final String name;

		private FormColumnTypeConst(String code, String name) {
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



	public FormColumnType copyFrom(FormColumnType that) {
		if (that != null) {
			this.code = that.code;
			this.componentType = that.componentType;
			this.isValid = that.isValid;
			this.isSummed = that.isSummed;
			this.expenseType = that.expenseType;
			if (that.numenclatures != null) {
				this.numenclatures = new TreeSet<BudgetNomenclatures>(that.numenclatures);
			}
		}
		return this;
	}
}
