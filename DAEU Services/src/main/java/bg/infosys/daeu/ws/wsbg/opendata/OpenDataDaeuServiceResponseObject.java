package bg.infosys.daeu.ws.wsbg.opendata;

public class OpenDataDaeuServiceResponseObject {
    private boolean success;
    private String resourceUri;

    public OpenDataDaeuServiceResponseObject() {}

    public OpenDataDaeuServiceResponseObject(boolean success, String resourceUri) {
        this.success = success;
        this.resourceUri = resourceUri;

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

	public String getResourceUri() {
		return resourceUri;
	}

	public void setResourceUri(String resourceUri) {
		this.resourceUri = resourceUri;
	}
}
