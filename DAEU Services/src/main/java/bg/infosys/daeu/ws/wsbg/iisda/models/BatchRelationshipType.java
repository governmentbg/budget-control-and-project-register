package bg.infosys.daeu.ws.wsbg.iisda.models;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchRelationshipType", propOrder = {
        "relatedBatch"
})
public class BatchRelationshipType {

    @XmlElement(name = "RelatedBatch")
    private BatchIdentificationInfoType relatedBatch;
    @XmlAttribute(name = "BatchRelationshipID", required = true)
    private long batchRelationshipID;
    @XmlAttribute(name = "VersionID", required = true)
    private long versionID;

    public BatchIdentificationInfoType getRelatedBatch() {
        return relatedBatch;
    }

    public void setRelatedBatch(BatchIdentificationInfoType value) {
        this.relatedBatch = value;
    }

    public long getBatchRelationshipID() {
        return batchRelationshipID;
    }

    public void setBatchRelationshipID(long value) {
        this.batchRelationshipID = value;
    }

    public long getVersionID() {
        return versionID;
    }

    public void setVersionID(long value) {
        this.versionID = value;
    }

}