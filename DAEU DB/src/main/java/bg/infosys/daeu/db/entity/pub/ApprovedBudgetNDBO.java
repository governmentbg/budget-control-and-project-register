package bg.infosys.daeu.db.entity.pub;

import bg.infosys.daeu.db.entity.budgets.ApprovedBudget;
import bg.infosys.daeu.db.entity.budgets.ApprovedBudgetValue;
import bg.infosys.daeu.db.entity.pub.ExpenseType.ExpenseTypeConst;

public class ApprovedBudgetNDBO {

	private Integer approvedBudgetId;
	private Subject subject;
	private ApprovedBudgetValue currentValue;
	private ApprovedBudgetValue capitalValue;
	private ApprovedBudgetValue totalValue;
	private int year;
	private String isValid;
	
	public ApprovedBudgetNDBO(ApprovedBudget approvedBudget) {
		this.approvedBudgetId = approvedBudget.getId();
		this.subject = approvedBudget.getSubject();
		this.year = approvedBudget.getYear().intValue();
		for (ApprovedBudgetValue value : approvedBudget.getApprovedBudgetValues()) {
			if (value.getExpenseType().getCode().equals(ExpenseTypeConst.CURRENT.getCode())) {
				this.currentValue = value;
			} else if (value.getExpenseType().getCode().equals(ExpenseTypeConst.CAPITAL.getCode())) {
				this.capitalValue = value;
			} else if (value.getExpenseType().getCode().equals(ExpenseTypeConst.TOTAL.getCode())) {
				this.totalValue = value;
			}
		}
		this.isValid = approvedBudget.getIsValid();
	}
	
	public ApprovedBudgetNDBO() {
		this.subject = new Subject();
		currentValue = new ApprovedBudgetValue();
		capitalValue = new ApprovedBudgetValue();
		totalValue = new ApprovedBudgetValue();
		isValid = "Y";
	}
	
	public Subject getSubject() {
		return subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	

	public ApprovedBudgetValue getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(ApprovedBudgetValue currentValue) {
		this.currentValue = currentValue;
	}

	public ApprovedBudgetValue getCapitalValue() {
		return capitalValue;
	}

	public void setCapitalValue(ApprovedBudgetValue capitalValue) {
		this.capitalValue = capitalValue;
	}

	public ApprovedBudgetValue getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(ApprovedBudgetValue totalValue) {
		this.totalValue = totalValue;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Integer getApprovedBudgetId() {
		return approvedBudgetId;
	}

	public void setApprovedBudgetId(Integer approvedBudgetId) {
		this.approvedBudgetId = approvedBudgetId;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public boolean isChanged(ApprovedBudgetNDBO approvedBudget) {
		if (this.capitalValue.getValue().equals(approvedBudget.getCapitalValue().getValue()) && 
				this.currentValue.getValue().equals(approvedBudget.getCurrentValue().getValue()) &&
					this.totalValue.getValue().equals(approvedBudget.getTotalValue().getValue())) {
			return false;
		}
		
		if (this.capitalValue.getValue() == 0 && this.totalValue.getValue() == 0 && this.currentValue.getValue() == 0
				&& this.year == 0 && (this.subject == null || this.subject.getId() <= 0)) {
			return false;
		}
		
		return true;
	}
	
	public ApprovedBudgetNDBO copyFrom(ApprovedBudgetNDBO that) {
		if (that != null) {
			this.approvedBudgetId = that.approvedBudgetId;
			this.subject = that.subject;
			this.currentValue = new ApprovedBudgetValue().copyFrom(that.currentValue);
			this.capitalValue = new ApprovedBudgetValue().copyFrom(that.capitalValue);
			this.totalValue = new ApprovedBudgetValue().copyFrom(that.totalValue);
			this.year = that.year;
			this.isValid = that.isValid;
		}
		return this;
	}
}
