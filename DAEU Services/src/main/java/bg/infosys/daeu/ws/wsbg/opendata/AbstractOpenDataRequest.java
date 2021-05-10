package bg.infosys.daeu.ws.wsbg.opendata;

import java.io.Serializable;

import org.apache.commons.lang3.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import bg.infosys.daeu.ws.wsbg.opendata.misc.OpenDataMethodAction;

//@JsonInclude(JsonInclude.Include.NON_NULL)
/*@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "child")
@JsonSubTypes({
    @JsonSubTypes.Type(value = DataSet.class, name = "dataSet"),
    @JsonSubTypes.Type(value = Resource.class, name = "resource"),
    @JsonSubTypes.Type(value = ResourceData.class, name = "resourceData") }
)*/
public abstract class AbstractOpenDataRequest implements Serializable {

	private static final long serialVersionUID = -7812359290318239437L;

	@JsonProperty("api_key")
    protected String apiKey;

    //@JsonIgnore
    private AbstractOpenDataRequest parent;
   // @JsonIgnore
    private AbstractOpenDataRequest child;
    //@JsonIgnore
    protected OpenDataMethodAction openDataMethodAction;

    public AbstractOpenDataRequest(){}

    public AbstractOpenDataRequest(OpenDataMethodAction openDataMethodAction) {
        this.openDataMethodAction = openDataMethodAction;
    }

    public OpenDataMethodAction getOpenDataMethodAction() {
        return openDataMethodAction;
    }
    
    public void setOpenDataMethodAction(OpenDataMethodAction openDataMethodAction) {
		this.openDataMethodAction = openDataMethodAction;
	}
    
	public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public abstract String toString();

    public abstract boolean hasMissingURI();

    public abstract void setResourceURI(String resourceURI);

    public abstract void setDataSetURI(String dataSetURI);

    public abstract String getResourceURI();

    public abstract String getDataSetURI();

    public abstract String getEntityURI();
    
    public void setParent(AbstractOpenDataRequest openDataRequest) {
        this.parent = openDataRequest;
    }

    public void setChild(AbstractOpenDataRequest openDataRequest) {
        this.child = openDataRequest;
    }

    public AbstractOpenDataRequest getParent() {
        return parent;
    }

    public AbstractOpenDataRequest getChild() {
        return child;
    }

    public boolean hasChild() {
        return ObjectUtils.isNotEmpty(this.child);
    }

    public boolean hasParent() {
        return ObjectUtils.isNotEmpty(this.parent);
    }

}