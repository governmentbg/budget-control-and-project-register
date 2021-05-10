package bg.infosys.daeu.ws.wsbg.opendata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.ObjectUtils;

public class OpenDataResponseObject {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("uri")
    private String datasetUri;

    @JsonProperty("data")
    private Data data;

    public OpenDataResponseObject() {

    }

    public OpenDataResponseObject(boolean success, String resourceUri, String datasetUri) {
        this.datasetUri = datasetUri;
        this.success = success;
        this.data = new Data();
        this.data.setResourceUri(resourceUri);

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResourceUri() {
        if (ObjectUtils.isNotEmpty(this.data)) {
            return this.data.getResourceUri();
        }
        return null;
    }

    public Data getData() {
        return this.data;
    }

    public String getDatasetUri() {
        return datasetUri;
    }

    public void setDatasetUri(String datasetUri) {
        this.datasetUri = datasetUri;
    }
}

class Data {

    @JsonProperty("uri")
    private String resourceUri;

    public Data() {

    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

}
