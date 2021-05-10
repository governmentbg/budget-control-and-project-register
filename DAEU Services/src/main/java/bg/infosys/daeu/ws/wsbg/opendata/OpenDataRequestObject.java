package bg.infosys.daeu.ws.wsbg.opendata;

import bg.infosys.daeu.db.entity.pub.ModuleType;
import bg.infosys.daeu.ws.wsbg.opendata.requests.DataSet;
import bg.infosys.daeu.ws.wsbg.opendata.requests.Resource;
import bg.infosys.daeu.ws.wsbg.opendata.requests.ResourceData;

public class OpenDataRequestObject {
    private DataSet dataSet;
    private Resource resource;
    private ResourceData resourceData;
    private ModuleType moduleType;
    private Integer row;
    
    public OpenDataRequestObject() {}
    
    public OpenDataRequestObject(DataSet dataSet, Resource resource, ResourceData resourceData, ModuleType moduleType, Integer row) {
    	this.dataSet = dataSet;
    	this.resource = resource;
    	this.resourceData = resourceData;
    	this.moduleType = moduleType;
    	this.row = row;
    }

    public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public ResourceData getResourceData() {
		return resourceData;
	}

	public void setResourceData(ResourceData resourceData) {
		this.resourceData = resourceData;
	}
	
	public ModuleType getModuleType() {
		return moduleType;
	}

	public void setModuleType(ModuleType moduleType) {
		this.moduleType = moduleType;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public AbstractOpenDataRequest getRootRequest() {
        return this.dataSet;
    }
}
