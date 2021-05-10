package bg.infosys.daeu.ws.controllers;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bg.infosys.daeu.db.entity.pub.ETranslation;
import bg.infosys.daeu.ws.integrations.ETranslateRequestBuilder;
import bg.infosys.daeu.ws.wsbg.etranslate.ETranslateRequestObject;


@RestController
@RequestMapping("eTranslate")
public class ETranslateEntryController {

    @Autowired
    private ETranslateRequestBuilder requestBuilder;

    @SuppressWarnings("rawtypes")
	@PostMapping(value = "/translate")
    public ResponseEntity translate(@RequestBody ETranslateRequestObject eTranslateRequestObject) throws IOException {
    	Integer requestId = requestBuilder.buildRequest(eTranslateRequestObject);
        return ResponseEntity.ok(requestId);
    }

    @RequestMapping(value = "/receiveCallback", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void receiveCallback(@RequestParam(value = "request-id") String idRequest,
                                @RequestParam(value = "target-language") String targetLanguage,
                                @RequestParam(value = "translated-text") String translatedText) { 
    	ETranslation translation = requestBuilder.findTranslationByRequestId(idRequest);
    	translation.setTranslatedText(translatedText);
    	requestBuilder.saveTranslation(translation);
    }
    
}
