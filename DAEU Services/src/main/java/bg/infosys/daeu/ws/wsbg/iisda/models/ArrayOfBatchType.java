package bg.infosys.daeu.ws.wsbg.iisda.models;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBatchType", namespace = "http://iisda.government.bg/RAS/IntegrationServices", propOrder = {
        "batchType"
})
public class ArrayOfBatchType {

    @XmlElement(name = "BatchType", nillable = true)
    private List<BatchType> batchType;

    public List<BatchType> getBatchType() {
        if (batchType == null) {
            batchType = new ArrayList<BatchType>();
        }
        return this.batchType;
    }
}