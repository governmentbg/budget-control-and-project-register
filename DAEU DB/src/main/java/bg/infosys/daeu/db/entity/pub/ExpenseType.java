package bg.infosys.daeu.db.entity.pub;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "n_expense_types")
public class ExpenseType {
	
	@Id
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	@Column(name = _name)
	private String name;
	public static final String _name = "name";
	
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
	
	public enum ExpenseTypeConst {
		CURRENT  ("CURRENT", "Текущи разходи"),
		CAPITAL  ("CAPITAL", "Капиталови разходи"),
		TOTAL    ("TOTAL",   "Общи разходи");
		
		
		private final String code;
		private final String name;

		private ExpenseTypeConst(String code, String name) {
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
