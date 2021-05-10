package bg.infosys.daeu.db.enums;

public enum QuarterEnum {
	
	Q1    	("Q1"),
	Q2		("Q2"),
	Q3		("Q3"),
	Q4		("Q4");
	
	private final String code;

	private QuarterEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
}
