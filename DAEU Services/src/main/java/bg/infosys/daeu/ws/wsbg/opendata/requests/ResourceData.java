package bg.infosys.daeu.ws.wsbg.opendata.requests;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeName;

import bg.infosys.common.utils.Strings;
import bg.infosys.daeu.ws.wsbg.opendata.AbstractOpenDataRequest;
import bg.infosys.daeu.ws.wsbg.opendata.misc.OpenDataMethodAction;

@JsonTypeName("resourceData")
public class ResourceData extends AbstractOpenDataRequest {
	private static final long serialVersionUID = 4095799623388121104L;
	
	 private String extension_format;
	 private boolean map_data;
	 private String resource_uri;
	 Map<String, String[]> data = new LinkedHashMap<>();

	 public ResourceData() {}
	 
	 public ResourceData(OpenDataMethodAction openDataMethodAction) {
        super(openDataMethodAction);
    }

	 // Getter Methods 
	 public Map<String, String[]> getData() {
		return data;
	}

	 public String getExtension_format() {
	  return extension_format;
	 }

	 public boolean getMap_data() {
	  return map_data;
	 }

	 public String getResource_uri() {
	  return resource_uri;
	 }

	 // Setter Methods 
	 public void setData(Map<String, String[]> data) {
		this.data = data;
	}

	 public void setExtension_format(String extension_format) {
	  this.extension_format = extension_format;
	 }

	 public void setMap_data(boolean map_data) {
	  this.map_data = map_data;
	 }

	 public void setResource_uri(String resource_uri) {
	  this.resource_uri = resource_uri;
	 }

	 @Override
    public void setResourceURI(String resourceURI) {
        this.resource_uri = resourceURI;
    }

    @Override
    public void setDataSetURI(String dataSetURI) {
        return;
    }

    @Override
    public String getResourceURI() {
        return resource_uri;
    }

    @Override
    public String getDataSetURI() {
        return null;
    }

    @Override
    public boolean hasMissingURI() {
        return Strings.isEmpty(resource_uri);
    }

    @Override
    public String getEntityURI() {
        return resource_uri;
    }

	@Override
	public String toString() {
		return "Resource Data Request : " + resource_uri;
	}
}