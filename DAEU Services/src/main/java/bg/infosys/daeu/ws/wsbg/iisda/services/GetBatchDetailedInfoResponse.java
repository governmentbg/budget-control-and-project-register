package bg.infosys.daeu.ws.wsbg.iisda.services;


import bg.infosys.daeu.ws.wsbg.iisda.models.ArrayOfBatchType;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "getBatchDetailedInfoResult"
})
@XmlRootElement(name = "GetBatchDetailedInfoResponse", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
public class GetBatchDetailedInfoResponse {

    @XmlElement(name = "GetBatchDetailedInfoResult", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
    private ArrayOfBatchType getBatchDetailedInfoResult;

    public ArrayOfBatchType getGetBatchDetailedInfoResult() {
        return getBatchDetailedInfoResult;
    }

    public void setGetBatchDetailedInfoResult(ArrayOfBatchType value) {
        this.getBatchDetailedInfoResult = value;
    }

}