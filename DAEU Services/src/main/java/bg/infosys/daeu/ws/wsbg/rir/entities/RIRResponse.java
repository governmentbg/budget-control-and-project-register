package bg.infosys.daeu.ws.wsbg.rir.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RIRResponse {

    @JsonProperty("objects")
    Map<String, ResponseObject> objects;

    public Map<String, ResponseObject> getObjects() {
        return objects;
    }

    public void setObjects(Map<String, ResponseObject> objects) {
        this.objects = objects;
    }

}
