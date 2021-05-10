package bg.infosys.daeu.ws.wsbg.opendata.requests;

import com.fasterxml.jackson.annotation.JsonTypeName;

import bg.infosys.common.utils.Strings;
import bg.infosys.daeu.ws.wsbg.opendata.AbstractOpenDataRequest;
import bg.infosys.daeu.ws.wsbg.opendata.misc.OpenDataMethodAction;

@JsonTypeName("dataSet")
public class DataSet extends AbstractOpenDataRequest {
	private static final long serialVersionUID = -7740119343094178682L;
	
	 private DataSetProperites data;
	 private String dataset_uri;

	 public DataSet() {}

	  public DataSet(OpenDataMethodAction openDataMethodAction) {
        super(openDataMethodAction);
    }

	 public DataSetProperites getData() {
	  return data;
	 }

	 public String getDataset_uri() {
		return dataset_uri;
	}

	public void setDataset_uri(String dataset_uri) {
		this.dataset_uri = dataset_uri;
	}

	public void setData(DataSetProperites dataObject) {
	  this.data = dataObject;
	 }


	 @Override
    public String toString() {
        return "DataSet Request : " + dataset_uri;
    }

    @Override
    public boolean hasMissingURI() {
        return Strings.isEmpty(this.dataset_uri);
    }

    @Override
    public void setResourceURI(String resourceURI) {
        return;
    }

    @Override
    public void setDataSetURI(String dataSetURI) {
        this.dataset_uri = dataSetURI;
    }

    @Override
    public String getResourceURI() {
        return null;
    }

    @Override
    public String getDataSetURI() {
        return this.dataset_uri;
    }

    @Override
    public String getEntityURI() {
        return this.dataset_uri;
    }
}