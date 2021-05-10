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

import bg.infosys.daeu.db.entity.security.User;

@Audited
@Entity
@Table(schema = "budgets", name = "budget_form_versions_users")
public class BudgetFormVersionUser {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "budget_form_version_id")
	private BudgetFormVersion budgetFormVersion;
	public static final String _budgetFormVersion = "budgetFormVersion";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assigner_user_id")
	private User assigner;
	public static final String _assigner = "assigner";
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assignee_user_id")
	private User assignee;
	public static final String _assignee = "assignee";
	
	@Column(name = "inserted_timestamp")
	private Date insertedTimestamp;
	public static final String _insertedTimestamp = "insertedTimestamp";
	
	@Column(name = "is_valid")
	private String isValid;
	public static final String _isValid = "isValid";
	
	public BudgetFormVersionUser() {}

	public BudgetFormVersionUser(BudgetFormVersion version, User assigner, User assignee) {
		this.budgetFormVersion = version;
		this.assigner = assigner;
		this.assignee = assignee;
		this.insertedTimestamp = new Date();
		this.isValid = "Y";
	}

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

	public User getAssigner() {
		return assigner;
	}

	public void setAssigner(User assigner) {
		this.assigner = assigner;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public Date getInsertedTimestamp() {
		return insertedTimestamp;
	}

	public void setInsertedTimestamp(Date insertedTimestamp) {
		this.insertedTimestamp = insertedTimestamp;
	}
	
	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
}
