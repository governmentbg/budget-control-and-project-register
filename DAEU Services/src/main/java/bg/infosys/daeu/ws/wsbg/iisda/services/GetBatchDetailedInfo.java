package bg.infosys.daeu.ws.wsbg.iisda.services;

import bg.infosys.daeu.ws.wsbg.iisda.models.ArrayOfString;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "batchIdentificationNumber",
        "dateAt",
        "versionIDAt"
})
@XmlRootElement(name = "GetBatchDetailedInfo", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
public class GetBatchDetailedInfo {

    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices")
    protected ArrayOfString batchIdentificationNumber;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateAt;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, type = Long.class, nillable = true)
    protected Long versionIDAt;

    public ArrayOfString getBatchIdentificationNumber() {
        return batchIdentificationNumber;
    }

    public void setBatchIdentificationNumber(ArrayOfString value) {
        this.batchIdentificationNumber = value;
    }

    public XMLGregorianCalendar getDateAt() {
        return dateAt;
    }

    public void setDateAt(XMLGregorianCalendar value) {
        this.dateAt = value;
    }

    public Long getVersionIDAt() {
        return versionIDAt;
    }

    public void setVersionIDAt(Long value) {
        this.versionIDAt = value;
    }

}
