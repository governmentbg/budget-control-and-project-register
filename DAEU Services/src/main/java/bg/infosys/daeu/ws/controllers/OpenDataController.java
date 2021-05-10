package bg.infosys.daeu.ws.controllers;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.infosys.daeu.db.entity.pub.OpenData;
import bg.infosys.daeu.ws.exceptions.MissingInputValuesException;
import bg.infosys.daeu.ws.integrations.OpenDataRequestBuilder;
import bg.infosys.daeu.ws.wsbg.opendata.OpenDataDaeuServiceResponseObject;
import bg.infosys.daeu.ws.wsbg.opendata.OpenDataRequestObject;
import bg.infosys.daeu.ws.wsbg.opendata.OpenDataResponseObject;

@RestController
@RequestMapping("openData")
public class OpenDataController {

    @Autowired
    OpenDataRequestBuilder openDataRequestBuilder;

    @PostMapping(value = "/generateOpenData")
    public ResponseEntity<OpenDataDaeuServiceResponseObject> generateOpenData(@RequestBody OpenDataRequestObject openDataRequestObject) {

        if (!ObjectUtils.allNotNull(openDataRequestObject.getDataSet(), openDataRequestObject.getResource(), openDataRequestObject.getResourceData())) {
            throw new MissingInputValuesException("Задайте DataSet и Ресурс за записване на данни");
        }
        if (ObjectUtils.isEmpty(openDataRequestObject.getDataSet()) && ObjectUtils.isNotEmpty(openDataRequestObject.getResource())) {
            throw new MissingInputValuesException("Ресурс без съответен DataSet не може да бъде записан");
        }
        
        boolean success = true;
        String dataSetURI = null, resourceURI = null;
        OpenData dataSet = null;
        if (openDataRequestObject.getDataSet().hasMissingURI()) {
        	OpenDataResponseObject response = openDataRequestBuilder.sendRequest(openDataRequestObject.getDataSet()).getBody();
        	if (response == null || !response.isSuccess()) {
        		success = false;
        	} else {
	        	dataSetURI = response.getDatasetUri();
	        	openDataRequestObject.getResource().setDataSetURI(dataSetURI);
	        	dataSet = openDataRequestBuilder.saveData(openDataRequestBuilder.convertToOpenData(dataSetURI, "Dataset", openDataRequestObject.getModuleType(), null));
        	}
        }
        
        if (openDataRequestObject.getResource().hasMissingURI() && success) {
        	OpenDataResponseObject response = openDataRequestBuilder.sendRequest(openDataRequestObject.getResource()).getBody();
        	if (response == null || !response.isSuccess()) {
        		success = false;
        	} else {
	        	resourceURI = response.getResourceUri();
	        	openDataRequestObject.getResourceData().setResourceURI(resourceURI);
	        	openDataRequestBuilder.saveData(openDataRequestBuilder.convertToOpenData(resourceURI, "Resource", openDataRequestObject.getModuleType(), dataSet));
        	}
        }
        if (success) {
        	OpenDataResponseObject response = openDataRequestBuilder.sendRequest(openDataRequestObject.getResourceData()).getBody();
        	if (response == null || !response.isSuccess()) {
        		success = false;
        	}
        }
        
        return ResponseEntity.ok(new OpenDataDaeuServiceResponseObject(success, resourceURI));
    }

}
