package bg.infosys.daeu.ws.wsbg.rir.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseObject {

    @JsonProperty("code")
    private Integer code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("class")
    private String className;
    @JsonProperty("key")
    private String key;
    @JsonProperty("fields")
    private Fields fields;

}

