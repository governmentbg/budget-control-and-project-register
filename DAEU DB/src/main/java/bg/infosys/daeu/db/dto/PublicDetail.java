package bg.infosys.daeu.db.dto;

public class PublicDetail {
	private Integer execId;
	private String rowType;
	
	private String name;
	private String value;
	
	public PublicDetail() {}
	
	public PublicDetail(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public PublicDetail(String name, String value, String rowType) {
		this.name = name;
		this.value = value;
		this.rowType = rowType;
	}
	
	public PublicDetail(String name, String value, Integer execId) {
		this.execId = execId;
		this.name = name;
		this.value = value;
	}
	
	public PublicDetail(String name, String value, Integer execId, String rowType) {
		this.execId = execId;
		this.name = name;
		this.value = value;
		this.rowType = rowType;
	}

	public Integer getExecId() {
		return execId;
	}

	public void setExecIdId(Integer execId) {
		this.execId = execId;
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

	public void setExecId(Integer execId) {
		this.execId = execId;
	}
}
