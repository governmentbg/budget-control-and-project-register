package bg.infosys.daeu.ws.pub.json;

import java.math.BigDecimal;

public class ISUNProjectActivityJSON {
	private Integer index;
	private String name;
	private String description;
	private String executionMethod;
	private String result;
	private Integer startMonth;
	private Integer duration;
	private BigDecimal requiredFinancialResource;
	
	public Integer getIndex() {
		return index;
	}
	
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getExecutionMethod() {
		return executionMethod;
	}
	
	public void setExecutionMethod(String executionMethod) {
		this.executionMethod = executionMethod;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public Integer getStartMonth() {
		return startMonth;
	}
	
	public void setStartMonth(Integer startMonth) {
		this.startMonth = startMonth;
	}
	
	public Integer getDuration() {
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public BigDecimal getRequiredFinancialResource() {
		return requiredFinancialResource;
	}
	
	public void setRequiredFinancialResource(BigDecimal requiredFinancialResource) {
		this.requiredFinancialResource = requiredFinancialResource;
	}
}
