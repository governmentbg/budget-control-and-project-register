package bg.infosys.daeu.ws.wsbg.opendata.requests;

import org.apache.commons.lang3.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonTypeName;

import bg.infosys.common.utils.Strings;
import bg.infosys.daeu.ws.wsbg.opendata.AbstractOpenDataRequest;
import bg.infosys.daeu.ws.wsbg.opendata.misc.OpenDataMethodAction;

@JsonTypeName("resource")
public class Resource extends AbstractOpenDataRequest {
	private static final long serialVersionUID = -7932292919746024030L;
	
	 private ResourceProperties data;
	 private String dataset_uri;
	 protected String resource_uri;
	 
	 public Resource() {}
	 
	 public Resource(OpenDataMethodAction openDataMethodAction) {
        super(openDataMethodAction);
    }

	 // Getter Methods 
	 public ResourceProperties getData() {
	  return data;
	 }

	 public String getDataset_uri() {
	  return dataset_uri;
	 }

	 // Setter Methods 
	 public void setData(ResourceProperties dataObject) {
	  this.data = dataObject;
	 }

	 public void setDataset_uri(String dataset_uri) {
	  this.dataset_uri = dataset_uri;
	 }

	 @Override
    public String toString() {
        return "ResourceMetaDataRequest : " + dataset_uri;
    }

    @Override
    public boolean hasMissingURI() {
        return !ObjectUtils.allNotNull(this.dataset_uri, this.resource_uri);
    }

    @Override
    public void setResourceURI(String resourceURI) {
        if (!Strings.isEmpty(resourceURI)) {
            this.resource_uri = resourceURI;
        }
    }

    @Override
    public void setDataSetURI(String dataSetURI) {
        if (!Strings.isEmpty(dataSetURI)) {
            this.dataset_uri = dataSetURI;
        }
    }

    @Override
    public String getResourceURI() {
        return this.resource_uri;
    }

    @Override
    public String getDataSetURI() {
        return this.dataset_uri;
    }

    @Override
    public String getEntityURI() {
        return resource_uri;
    }
    
    public enum ResourceType {
        FILE(1),
        HYPERLINK(2),
        API(3),
        AUTOMATIC_UPLOAD(4);

        private final int value;

        ResourceType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
