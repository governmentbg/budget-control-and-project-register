package bg.infosys.daeu.ws.pub.json;

import java.util.List;

public class BudgetDetailJSON {
	private Integer regId;
	private String cpvCode;
	private String cpvName;
	private String ebkCode;
	private Integer change;
	
	private List<BudgetYearJSON> years;
	
	public BudgetDetailJSON() {}
	
	public Integer getRegId() {
		return regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public String getCpvCode() {
		return cpvCode;
	}

	public void setCpvCode(String cpvCode) {
		this.cpvCode = cpvCode;
	}

	public String getCpvName() {
		return cpvName;
	}

	public void setCpvName(String cpvName) {
		this.cpvName = cpvName;
	}

	public String getEbkCode() {
		return ebkCode;
	}

	public void setEbkCode(String ebkCode) {
		this.ebkCode = ebkCode;
	}

	public Integer getChange() {
		return change;
	}

	public void setChange(Integer change) {
		this.change = change;
	}

	public List<BudgetYearJSON> getYears() {
		return years;
	}

	public void setYears(List<BudgetYearJSON> years) {
		this.years = years;
	}
}
