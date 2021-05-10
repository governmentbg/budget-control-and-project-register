package bg.infosys.daeu.db.entity.budgets;

import java.util.Date;
import java.util.Objects;

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

import bg.infosys.daeu.db.entity.security.User;

@Audited
@Entity
@Table(schema = "budgets", name = "budget_notes")
public class BudgetNote {
	@Id
	@SequenceGenerator(name = "budget_notes_seq", sequenceName = "budgets.budget_notes_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budget_notes_seq")
	private Integer id;
	public static final String _id = "id";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "budget_form_version_id")
	private BudgetFormVersion budgetFormVersion;
	public static final String _budgetFormVersion = "budgetFormVersion";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "budget_form_id")
	private BudgetForm budgetForm;
	public static final String _budgetForm = "budgetForm";
	
	@Column(name = _note)
	private String note;
	public static final String _note = "note";
	
	@Column(name = "is_common")
	private String isCommon;
	public static final String _isCommon = "isCommon";
	
	@Column(name = "is_valid")
	private String isValid = "Y";
	public static final String _isValid = "isValid";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	public static final String _user = "user";
	
	@Column(name = _date)
	private Date date = new Date();
	public static final String _date = "date";
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BudgetFormVersion getBudgetFormVersion() {
		return budgetFormVersion;
	}

	public void setBudgetFormVersion(BudgetFormVersion budgetFormVersion) {
		this.budgetFormVersion = budgetFormVersion;
	}

	public String getNote() {
		return note;
	}

	public BudgetForm getBudgetForm() {
		return budgetForm;
	}

	public void setBudgetForm(BudgetForm budgetForm) {
		this.budgetForm = budgetForm;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	public String getIsCommon() {
		return isCommon;
	}

	public void setIsCommon(String isCommon) {
		this.isCommon = isCommon;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BudgetNote other = (BudgetNote) obj;
		return Objects.equals(id, other.id);
	}
}
