package bg.infosys.daeu.db.entity.pub;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "n_budgetary_procedure_dates")
public class BudgetaryProcedureDate {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = _description)
	private String description;
	public static final String _description = "description";
	
	@Column(name = _date)
	private Date date;
	public static final String _date = "date";
	
	@Column(name = _isValid)
	private String isValid;
	public static final String _isValid = "is_valid";
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public BudgetaryProcedureDate copyFrom(BudgetaryProcedureDate that) {
		if (that != null) {
			this.id = that.id;
			this.description = that.description;
			this.date = that.date;
			this.isValid = that.isValid;
		}
		
		return this;
	}
}
