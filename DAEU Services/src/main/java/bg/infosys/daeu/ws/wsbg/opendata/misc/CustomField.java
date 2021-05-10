package bg.infosys.daeu.ws.wsbg.opendata.misc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomField {

    @JsonProperty("label")
    private Pair<String, String> label;
    @JsonProperty("value")
    private Pair<String, String> value;

    public CustomField() {}
    
    public CustomField(String label, String value) {
        this.label = new ImmutablePair<>("bg", label);
        this.value = new ImmutablePair<>("bg", value);
    }

}