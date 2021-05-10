package bg.infosys.daeu.ws.wsbg.iisda.models;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBatchIdentificationInfoType", namespace = "http://iisda.government.bg/RAS/IntegrationServices", propOrder = {
        "batchIdentificationInfoType"
})
public class ArrayOfBatchIdentificationInfoType {

    @XmlElement(name = "BatchIdentificationInfoType", nillable = true)
    protected List<BatchIdentificationInfoType> batchIdentificationInfoType;

    public List<BatchIdentificationInfoType> getBatchIdentificationInfoType() {
        if (batchIdentificationInfoType == null) {
            batchIdentificationInfoType = new ArrayList<>();
        }
        return this.batchIdentificationInfoType;
    }

}