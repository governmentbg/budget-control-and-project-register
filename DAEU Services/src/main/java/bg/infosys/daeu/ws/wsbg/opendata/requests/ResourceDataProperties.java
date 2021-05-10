package bg.infosys.daeu.ws.wsbg.opendata.requests;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

public class ResourceDataProperties {
	Map<String, String[]> content = new LinkedHashMap<>();
	
	public ResourceDataProperties() {}

	public Map<String, String[]> getContent() {
		return content;
	}

	public void setContent(Map<String, String[]> content) {
		this.content = content;
	}

	 public void setHeaders(String firstHeader, String... allHeaders) {
		String[] headers;
        headers = ArrayUtils.addAll(new String[]{firstHeader}, allHeaders);
        content.put("headers", headers);
    }

    public void setData(List<String[]> csvData) {
    	Map<String, String[]> rows = new HashMap<>();
        AtomicInteger index = new AtomicInteger();
        rows = csvData.stream().collect(
        		Collectors.toMap(i -> "row" + index.getAndIncrement(), i -> csvData.get(csvData.indexOf(i))));
        content.putAll(rows);
    }
}
