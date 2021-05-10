package bg.infosys.daeu.ws.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.infosys.daeu.db.entity.pub.Subject;
import bg.infosys.daeu.ws.integrations.IISDARequestBuilder;
import bg.infosys.daeu.ws.wsbg.iisda.models.BatchIdentificationInfoType;

@RestController
@RequestMapping("iisda")
public class IISDAEntryController {

    private static final Logger logger = LoggerFactory.getLogger(IISDAEntryController.class);

    @Autowired
    private IISDARequestBuilder requestBuilder;

    @GetMapping(value = "/getHierarchy")
    public ResponseEntity<Boolean> getHierarchy() {
        logger.info("Received a call to getHierarchy for IISDA");
        List<BatchIdentificationInfoType> organizations = requestBuilder.getListOfUnits();
        List<Subject> listToSave = requestBuilder.processNewBatch(organizations);
        requestBuilder.saveToDB(listToSave);
        return ResponseEntity.ok().body(Boolean.TRUE);
    }

}