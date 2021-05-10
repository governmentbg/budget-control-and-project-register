package bg.infosys.daeu.db.entity.pub;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "n_ebk_codes")
public class EBKCode implements Serializable, Comparable<EBKCode> {
	private static final long serialVersionUID = 8034624396395756492L;

	@Id
	@SequenceGenerator(name = "ebk_codes_seq", sequenceName = "n_ebk_codes_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ebk_codes_seq")
	private Integer id;
	
	@Column(name = _code)
	private String code;
	public static final String _code = "code";
	
	@Column(name = _description)
	private String description;
	public static final String _description = "description";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "expense_type_code")
	private ExpenseType expenseType;
	public static final String _expenseType = "expenseType";
	
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
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String isValid() {
		return isValid;
	}
	
	public void setValid(String isValid) {
		this.isValid = isValid;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EBKCode other = (EBKCode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public EBKCode copyFrom(EBKCode that) {
		if (that != null) {
			this.id = that.id;
			this.code = that.code;
			this.description = that.description;
			this.isValid = that.isValid;
			this.expenseType = that.expenseType;
		}
		return this;
	}

	@Override
	public int compareTo(EBKCode arg0) {
		return this.id.compareTo(arg0.getId());
	}
}
