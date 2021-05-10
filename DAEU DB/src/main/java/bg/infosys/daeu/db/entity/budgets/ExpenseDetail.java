package bg.infosys.daeu.db.entity.budgets;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.Subject;

@Audited
@Entity
@Table(schema = "budgets", name = "expense_details")
public class ExpenseDetail {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "text")
	private String text;
	public static final String _text = "text";
	
	@Column(name = _value)
	private Integer value;
	public static final String _value = "value";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "row_value_id")
	private RowValue rowValue;
	public static final String _rowValue = "rowValue";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;
	public static final String _subject = "subject";
	
	@Column(name = "date_created", columnDefinition = "TIMESTAMP")
	private Date dateCreated;
	public static final String _dateCreated = "dateCreated";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public RowValue getRowValue() {
		return rowValue;
	}
	public void setRowValue(RowValue rowValue) {
		this.rowValue = rowValue;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	public ExpenseDetail copyFrom(RowValue rowValue, ExpenseDetail that) {
		if (that != null) {
			this.rowValue = rowValue;
			this.text = that.text;
			this.value = that.value;
			this.subject = that.subject;
			this.dateCreated = new Date();
			this.isValid = that.isValid;
		}
		return this;
	}
}
