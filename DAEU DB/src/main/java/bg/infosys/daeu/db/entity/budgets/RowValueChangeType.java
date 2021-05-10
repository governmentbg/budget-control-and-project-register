package bg.infosys.daeu.db.entity.budgets;

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

@Audited
@Entity
@Table(schema="budgets", name = "row_value_change_types")
public class RowValueChangeType {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "row_value_id")
	private RowValue rowValue;
	public static final String _rowValue = "rowValue";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "change_type_id")
	private ChangeType changeType;
	public static final String _changeType = "changeType";
	
	@Column(name = "is_valid")
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	public RowValueChangeType() {}

	public RowValueChangeType(RowValue rowValue, ChangeType changeType) {
		this.rowValue = rowValue;
		this.changeType = changeType;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RowValue getRowValue() {
		return rowValue;
	}

	public void setRowValue(RowValue rowValue) {
		this.rowValue = rowValue;
	}

	public ChangeType getChangeType() {
		return changeType;
	}

	public void setChangeType(ChangeType changeType) {
		this.changeType = changeType;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public RowValueChangeType copyFrom(RowValue rowValue, RowValueChangeType that) {
		if (that != null) {
			this.rowValue = rowValue;
			this.changeType = that.changeType;
			this.isValid = that.isValid;
		}
		return this;
	}
	
}
