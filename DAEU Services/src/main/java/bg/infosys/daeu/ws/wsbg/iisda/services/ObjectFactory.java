package bg.infosys.daeu.ws.wsbg.iisda.services;


import bg.infosys.daeu.ws.wsbg.iisda.models.ArrayOfBatchIdentificationInfoType;
import bg.infosys.daeu.ws.wsbg.iisda.models.ArrayOfBatchType;
import bg.infosys.daeu.ws.wsbg.iisda.models.ArrayOfString;
import bg.infosys.daeu.ws.wsbg.iisda.models.BatchIdentificationInfoType;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {


    public ObjectFactory() {
    }

    public ArrayOfBatchIdentificationInfoType createArrayOfBatchIdentificationInfoType() {
        return new ArrayOfBatchIdentificationInfoType();
    }

    public BatchIdentificationInfoType createBatchIdentificationInfoType() {
        return new BatchIdentificationInfoType();
    }

    public SearchBatchesIdentificationInfo createSearchBatchesIdentificationInfo() {
        return new SearchBatchesIdentificationInfo();
    }

    public SearchBatchesIdentificationInfoResponse createSearchBatchesIdentificationInfoResponse() {
        return new SearchBatchesIdentificationInfoResponse();
    }

    public GetBatchDetailedInfo createGetBatchDetailedInfo() {
        return new GetBatchDetailedInfo();
    }

    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    public GetBatchDetailedInfoResponse createGetBatchDetailedInfoResponse() {
        return new GetBatchDetailedInfoResponse();
    }

    public ArrayOfBatchType createArrayOfBatchType() {
        return new ArrayOfBatchType();
    }

}