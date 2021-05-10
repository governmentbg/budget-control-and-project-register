package bg.infosys.daeu.ws.pub.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ISUNInternalResponse {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String errorCode;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String errorMessage;
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public enum ReponseMessage {
    	UNAUTHORIZED		("UNAUTHORIZED", "Unauthorized!"),
    	FAILED				("DECISION_SENDING_ERROR" ,"Decision sending failed!");
    	
    	private final String code;
    	private final String message;
    	
    	private ReponseMessage(String code, String message) {
    		this.message = message;
    		this.code = code;
    	}
    	
    	public String getMessage() {
			return message;
		}
    	
    	public String getCode() {
    		return code;
    	}
    }
}
