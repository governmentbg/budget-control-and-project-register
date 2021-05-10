package bg.infosys.daeu.db.dto;

public class PublicBudgetAdditionalField {
	private String rowType;
	
	private String name;
	private String value;
	
	public PublicBudgetAdditionalField() {}
	
	public PublicBudgetAdditionalField(String name, String value, String rowType) {
		this.name = name;
		this.value = value;
		this.rowType = rowType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRowType() {
		return rowType;
	}

	public void setRowType(String rowType) {
		this.rowType = rowType;
	}
}
