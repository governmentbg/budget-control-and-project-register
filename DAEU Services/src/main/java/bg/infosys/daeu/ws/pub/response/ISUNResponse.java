package bg.infosys.daeu.ws.pub.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ISUNResponse {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String errorCode;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String errorMessage;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer id;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public enum ReponseMessage {
    	NОТ_REQUIRED 		("NОТ_REQUIRED" ,"No approval required for project proposal with GUID "),
    	NОТ_RETURNED 		("NОТ_RETURNED" ,"Project is not returned! "),
    	UNAUTHORIZED		("UNAUTHORIZED", "Unauthorized!"),
    	FAILED				("SAVE_ERROR" ,"Project save failed!");
    	
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
