package bg.infosys.daeu.ws.pub.json;

import java.math.BigDecimal;

public class ISUNIndicatorJSON {
	private String name;
	private String trend;
	private String measure;
	private BigDecimal base;
	private BigDecimal target;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTrend() {
		return trend;
	}
	
	public void setTrend(String trend) {
		this.trend = trend;
	}
	
	public BigDecimal getBase() {
		return base;
	}
	
	public void setBase(BigDecimal base) {
		this.base = base;
	}
	
	public BigDecimal getTarget() {
		return target;
	}
	
	public void setTarget(BigDecimal target) {
		this.target = target;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}
}
