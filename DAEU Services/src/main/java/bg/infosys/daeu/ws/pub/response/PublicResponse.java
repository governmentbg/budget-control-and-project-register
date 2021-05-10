package bg.infosys.daeu.ws.pub.response;

public class PublicResponse {
    private String message;
    private String reason;
    private Integer publicId;
    
    public PublicResponse() {}
    
    public PublicResponse(String message, String reason) {
    	this.message = message;
    	this.reason = reason;
    }
    
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getPublicId() {
		return publicId;
	}

	public void setPublicId(Integer publicId) {
		this.publicId = publicId;
	}

	public enum ReponseMessage {
    	SUCCESSFUL_SYNC		("Successful synchronization!"),
    	UNAUTHORIZED		("Unauthorized!"),
    	FAILED_SYNC			("Synchronization failed!");
    	
    	private final String message;
    	
    	private ReponseMessage(String message) {
    		this.message = message;
    	}
    	
    	public String getMessage() {
			return message;
		}
    }
}

