package bg.infosys.daeu.ws.wsbg.iisda.services;

import bg.infosys.daeu.ws.wsbg.iisda.models.ArrayOfBatchIdentificationInfoType;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "searchBatchesIdentificationInfoResult"
})
@XmlRootElement(name = "SearchBatchesIdentificationInfoResponse", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
public class SearchBatchesIdentificationInfoResponse {

    @XmlElement(name = "SearchBatchesIdentificationInfoResult", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
    protected ArrayOfBatchIdentificationInfoType searchBatchesIdentificationInfoResult;

    public ArrayOfBatchIdentificationInfoType getSearchBatchesIdentificationInfoResult() {
        return searchBatchesIdentificationInfoResult;
    }

    public void setSearchBatchesIdentificationInfoResult(ArrayOfBatchIdentificationInfoType value) {
        this.searchBatchesIdentificationInfoResult = value;
    }

}