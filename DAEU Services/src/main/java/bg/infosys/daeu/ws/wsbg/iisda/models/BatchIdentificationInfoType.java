package bg.infosys.daeu.ws.wsbg.iisda.models;

import bg.infosys.daeu.ws.wsbg.iisda.models.enums.AdmStructureKindsEnum;
import bg.infosys.daeu.ws.wsbg.iisda.models.enums.BatchStatusEnum;
import bg.infosys.daeu.ws.wsbg.iisda.models.enums.BatchTypeEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchIdentificationInfoType")
public class BatchIdentificationInfoType {

    @XmlAttribute(name = "BatchID", required = true)
    protected long batchID;
    @XmlAttribute(name = "Type", required = true)
    protected BatchTypeEnum type;
    @XmlAttribute(name = "IdentificationNumber")
    protected String identificationNumber;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "AdmStructureKind", required = true)
    protected AdmStructureKindsEnum admStructureKind;
    @XmlAttribute(name = "UIC")
    protected String uic;
    @XmlAttribute(name = "Status", required = true)
    protected BatchStatusEnum status;

    private List<BatchIdentificationInfoType> childBatchID = new ArrayList<>();
    private BatchIdentificationInfoType parent;
    private AdministrationType administration;

    public long getBatchID() {
        return batchID;
    }

    public void setBatchID(long value) {
        this.batchID = value;
    }

    public BatchTypeEnum getType() {
        return type;
    }

    public void setType(BatchTypeEnum value) {
        this.type = value;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String value) {
        this.identificationNumber = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public AdmStructureKindsEnum getAdmStructureKind() {
        return admStructureKind;
    }

    public void setAdmStructureKind(AdmStructureKindsEnum value) {
        this.admStructureKind = value;
    }

    public String getUIC() {
        return uic;
    }

    public void setUIC(String value) {
        this.uic = value;
    }

    public BatchStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BatchStatusEnum value) {
        this.status = value;
    }

    public List<BatchIdentificationInfoType> getChildBatchID() {
        return childBatchID;
    }

    public void setChildBatchID(List<BatchIdentificationInfoType> childBatchID) {
        this.childBatchID = childBatchID;
    }

    public void setParent(BatchIdentificationInfoType batchIdentificationInfoType) {
        this.parent = batchIdentificationInfoType;
    }

    public BatchIdentificationInfoType getParent() {
        return this.parent;
    }

    public void addChild(BatchIdentificationInfoType batchIdentificationInfoType) {
        this.childBatchID.add(batchIdentificationInfoType);
    }

    @Override
    public String toString() {
        return "{" + getName() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatchIdentificationInfoType that = (BatchIdentificationInfoType) o;

        return new EqualsBuilder()
                .append(batchID, that.batchID)
                .append(type, that.type)
                .append(identificationNumber, that.identificationNumber)
                .append(name, that.name)
                .append(admStructureKind, that.admStructureKind)
                .append(uic, that.uic)
                .append(status, that.status)
                .isEquals();

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(batchID)
                .append(type)
                .append(identificationNumber)
                .append(name)
                .append(admStructureKind)
                .append(uic)
                .append(status)
                .toHashCode();
    }

    public void setAdministration(AdministrationType administration) {
        this.administration = administration;
    }

    public AdministrationType getAdministration() {
        return this.administration;
    }

}
