package bg.infosys.daeu.ws.integrations;

import java.util.Arrays;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import bg.infosys.daeu.db.entity.pub.ModuleType;
import bg.infosys.daeu.db.entity.pub.OpenData;
import bg.infosys.daeu.ws.services.OpenDataService;
import bg.infosys.daeu.ws.wsbg.opendata.AbstractOpenDataRequest;
import bg.infosys.daeu.ws.wsbg.opendata.OpenDataResponseObject;

@Component
public class OpenDataRequestBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OpenDataRequestBuilder.class);

    private RestTemplate restTemplate;

    @Value("${open.data_api}")
    private String api_key;

    @Value("${open.data.url}")
    private String url;

    @Autowired
    private OpenDataService openDataService;

    public OpenDataRequestBuilder() {
        restTemplate = new RestTemplate();
    }

    @SuppressWarnings("deprecation")
	public ResponseEntity<OpenDataResponseObject> sendRequest(AbstractOpenDataRequest openDataRequest) {
        ResponseEntity<OpenDataResponseObject> response = null;
        openDataRequest.setApiKey(api_key);

        String uri = url + openDataRequest.getOpenDataMethodAction().getValue();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<AbstractOpenDataRequest> entity = new HttpEntity<>(openDataRequest, headers);

        try {
            response = restTemplate.postForEntity(uri, entity, OpenDataResponseObject.class); //HttpStatusCodeException
        } catch (HttpStatusCodeException httpStatusCodeException) {
			String decoded = StringEscapeUtils.unescapeJson(httpStatusCodeException.getResponseBodyAsString());
            logger.error("Error code {} with {}", httpStatusCodeException.getRawStatusCode(), decoded);
            //throw new RestExchangeException(httpStatusCodeException.getStatusCode(), httpStatusCodeException.getMessage());
        }
        return response != null ? response : ResponseEntity.noContent().build();
    }

    /*public OpenDataRequestObject process() {
        AbstractOpenDataRequest currentElement = openDataRequestObject.getRootRequest();

        while (currentElement != null) {
            if (currentElement.hasMissingURI()) {
                logger.info("class : {} , need api : {}", currentElement.getClass().getName(), currentElement.hasMissingURI());

                OpenDataResponseObject response = sendRequest(currentElement).getBody();
                if (ObjectUtils.isNotEmpty(response)) {
//                    AbstractOpenDataRequest finalCurrentElement = currentElement;
//                    response.getDatasetUri().ifPresent(finalCurrentElement::setDataSetURI);


                    currentElement.setDataSetURI(response.getDatasetUri());
                    currentElement.setResourceURI(response.getResourceUri());

                    if (ObjectUtils.isEmpty(openDataService.findByURI(currentElement.getEntityURI()))) {
                        saveData(convertToOpenData(currentElement));
                    }
                }
            }

            if (currentElement.hasChild()) {
                currentElement.getChild().setDataSetURI(currentElement.getDataSetURI());
                currentElement.getChild().setResourceURI(currentElement.getResourceURI());
            }
            currentElement = currentElement.getChild();
        }
        return openDataRequestObject;
    }*/

    @Transactional
    public OpenData saveData(OpenData convertedOpenData) {
        return openDataService.saveOpenData(convertedOpenData);
    }
    
    public OpenData convertToOpenData(String uri, String type, ModuleType moduleType, OpenData parent) {
        OpenData openData = new OpenData();
        openData.setOpenDataURI(uri);
        openData.setOpenDataType(type);
        openData.setModuleType(moduleType);
        openData.setParent(parent);

        return openData;
    }
}
