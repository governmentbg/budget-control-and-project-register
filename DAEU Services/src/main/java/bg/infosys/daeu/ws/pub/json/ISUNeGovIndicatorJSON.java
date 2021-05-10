package bg.infosys.daeu.ws.pub.json;

public class ISUNeGovIndicatorJSON {
	private String name;
	private String measure;
	private Integer target;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public Integer getTarget() {
		return target;
	}
	
	public void setTarget(Integer target) {
		this.target = target;
	}
}
