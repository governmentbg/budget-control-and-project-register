package bg.infosys.daeu.ws.wsbg.rir;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Map;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RIRKey {

    private Integer id;

    private Map<String, String> criteria;

    private String query;

    public void setID(int id) {
        setValues(id, null, null);
    }

    public void setCriteria(Map<String, String> criteria) {
        setValues(null, null, criteria);
    }

    public void setQuery(String query) {
        setValues(null, query, null);
    }

    private void setValues(Integer id, String query, Map<String, String> criteria) {
        this.id = id;
        this.query = query;
        this.criteria = criteria;
    }

    public Object getValue() {
        return ObjectUtils.firstNonNull(id, criteria, query);
    }
}
