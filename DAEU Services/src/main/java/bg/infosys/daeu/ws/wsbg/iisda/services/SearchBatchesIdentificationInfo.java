package bg.infosys.daeu.ws.wsbg.iisda.services;


import bg.infosys.daeu.ws.wsbg.iisda.models.enums.AdmStructureKindsEnum;
import bg.infosys.daeu.ws.wsbg.iisda.models.enums.BatchStatusEnum;
import bg.infosys.daeu.ws.wsbg.iisda.models.enums.BatchTypeEnum;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "batchIdentificationNumber",
        "batchUIC",
        "admStructureKind",
        "batchType",
        "status",
        "dateAt",
        "versionIDAt"
})
@XmlRootElement(name = "SearchBatchesIdentificationInfo", namespace = "http://iisda.government.bg/RAS/IntegrationServices")
public class SearchBatchesIdentificationInfo {

    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices")
    protected String batchIdentificationNumber;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices")
    protected String batchUIC;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected AdmStructureKindsEnum admStructureKind;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected BatchTypeEnum batchType;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected BatchStatusEnum status;
    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateAt;

    public String getBatchIdentificationNumber() {
        return batchIdentificationNumber;
    }

    public void setBatchIdentificationNumber(String batchIdentificationNumber) {
        this.batchIdentificationNumber = batchIdentificationNumber;
    }

    public String getBatchUIC() {
        return batchUIC;
    }

    public void setBatchUIC(String batchUIC) {
        this.batchUIC = batchUIC;
    }

    public AdmStructureKindsEnum getAdmStructureKind() {
        return admStructureKind;
    }

    public void setAdmStructureKind(AdmStructureKindsEnum admStructureKind) {
        this.admStructureKind = admStructureKind;
    }

    public BatchTypeEnum getBatchType() {
        return batchType;
    }

    public void setBatchType(BatchTypeEnum batchType) {
        this.batchType = batchType;
    }

    public BatchStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BatchStatusEnum status) {
        this.status = status;
    }

    public XMLGregorianCalendar getDateAt() {
        return dateAt;
    }

    public void setDateAt(XMLGregorianCalendar dateAt) {
        this.dateAt = dateAt;
    }

    public Long getVersionIDAt() {
        return versionIDAt;
    }

    public void setVersionIDAt(Long versionIDAt) {
        this.versionIDAt = versionIDAt;
    }

    @XmlElement(namespace = "http://iisda.government.bg/RAS/IntegrationServices", required = true, type = Long.class, nillable = true)
    protected Long versionIDAt;


}
