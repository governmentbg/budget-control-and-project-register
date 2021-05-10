package bg.infosys.daeu.db.entity.budgets;

import java.util.HashSet;
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
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.pub.Subject;

@Audited
@Entity
@Table(name = "n_approved_budgets")
public class ApprovedBudget {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;
	public static final String _subject = "subject";

	@Column(name = _year)
	private Integer year;
	public static final String _year = "year";
	
	@Column(name = _isValid)
	private String isValid;
	public static final String _isValid = "is_valid";
	
	@OneToMany(mappedBy = "approvedBudget", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ApprovedBudgetValue> approvedBudgetValues;
	public static final String _approvedBudgetValues = "approvedBudgetValues";
	
	public ApprovedBudget() {
		this.approvedBudgetValues = new HashSet<ApprovedBudgetValue>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Subject getSubject() {
		return subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Set<ApprovedBudgetValue> getApprovedBudgetValues() {
		return approvedBudgetValues;
	}

	public void setApprovedBudgetValues(Set<ApprovedBudgetValue> approvedBudgetValues) {
		this.approvedBudgetValues = approvedBudgetValues;
	}
}
