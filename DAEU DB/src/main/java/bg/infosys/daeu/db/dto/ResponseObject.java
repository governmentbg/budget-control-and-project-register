package bg.infosys.daeu.db.dto;


public class ResponseObject {
	private Integer code;
	private String message;
	private String className;
	private String key;
	private RIRFields fields;

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public RIRFields getFields() {
		return fields;
	}
	public void setFields(RIRFields fields) {
		this.fields = fields;
	}
	@Override
	public String toString() {
		return "ResponseObject [code=" + code + ", message=" + message + ", className=" + className + ", key=" + key
				+ ", fields=" + fields + "]";
	}
	
	
}
