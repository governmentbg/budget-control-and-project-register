package bg.infosys.daeu.ws.pub.json;

public class BudgetYearJSON {
	private Integer regId;
	private String year;
	private Integer units;
	private Integer total;
	
	public BudgetYearJSON() {}
	
	public BudgetYearJSON(Integer regId, String year, Integer units, Integer total) {
		this.regId = regId;
		this.year = year;
		this.units = units;
		this.total = total;
	}

	public Integer getRegId() {
		return regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}