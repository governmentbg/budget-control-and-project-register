package bg.infosys.daeu.ws.wsbg.iisda.models;

import bg.infosys.daeu.ws.wsbg.iisda.models.enums.AdmStructureKindsEnum;
import bg.infosys.daeu.ws.wsbg.iisda.models.enums.BatchStatusEnum;
import bg.infosys.daeu.ws.wsbg.iisda.models.enums.BatchTypeEnum;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchType", propOrder = {
        "administration",
//        "act",
//        "policyArea",
//        "governingBody",
        "government",
        "prevGovernment",
        "stewardWithMoneyBatch",
        "standaloneStructsToBatch",
        "advBoardsToComOrAdmStruct"
//        "townHalls",
//        "deputyMayors",
//        "batchIAAPosition",
//        "batchIAAPerson",
//        "staffNumbers",
//        "vacantPositionsNumbers"
})
public class BatchType {

    @XmlElement(name = "Administration")
    protected AdministrationType administration;
//    @XmlElement(name = "Act")
//    protected List<ActType> act;
//    @XmlElement(name = "PolicyArea")
//    protected List<PolicyAreaType> policyArea;
//    @XmlElement(name = "GoverningBody")
//    protected List<GoverningBodyType> governingBody;
    @XmlElement(name = "Government")
    protected GovernmentType government;
    @XmlElement(name = "PrevGovernment")
    protected GovernmentType prevGovernment;
    @XmlElement(name = "StewardWithMoneyBatch")
    protected BatchRelationshipType stewardWithMoneyBatch;
    @XmlElement(name = "StandaloneStructsToBatch")
    protected List<BatchRelationshipType> standaloneStructsToBatch;
    @XmlElement(name = "AdvBoardsToComOrAdmStruct")
    protected List<BatchRelationshipType> advBoardsToComOrAdmStruct;
//    @XmlElement(name = "TownHalls")
//    protected List<TownHallType> townHalls;
//    @XmlElement(name = "DeputyMayors")
//    protected List<DeputyMayorType> deputyMayors;
//    @XmlElement(name = "BatchIAAPosition")
//    protected List<BatchIAAPositionType> batchIAAPosition;
//    @XmlElement(name = "BatchIAAPerson")
//    protected List<BatchIAAPersonType> batchIAAPerson;
//    @XmlElement(name = "StaffNumbers")
//    protected StaffNumbersType staffNumbers;
//    @XmlElement(name = "VacantPositionsNumbers")
//    protected VacantPositionsNumbersType vacantPositionsNumbers;
    @XmlAttribute(name = "BatchID", required = true)
    protected long batchID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "Type", required = true)
    protected BatchTypeEnum type;
    @XmlAttribute(name = "IdentificationNumber")
    protected String identificationNumber;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "Status", required = true)
    protected BatchStatusEnum status;
    @XmlAttribute(name = "BudgetOfficerLevel")
    protected Byte budgetOfficerLevel;
    @XmlAttribute(name = "AdmStructureKind")
    protected AdmStructureKindsEnum admStructureKind;

    public long getBatchID() {
        return batchID;
    }

    public void setBatchID(long batchID) {
        this.batchID = batchID;
    }

    public long getVersionID() {
        return versionID;
    }

    public void setVersionID(long versionID) {
        this.versionID = versionID;
    }

    public BatchTypeEnum getType() {
        return type;
    }

    public void setType(BatchTypeEnum type) {
        this.type = type;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BatchStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BatchStatusEnum status) {
        this.status = status;
    }

    public Byte getBudgetOfficerLevel() {
        return budgetOfficerLevel;
    }

    public void setBudgetOfficerLevel(Byte budgetOfficerLevel) {
        this.budgetOfficerLevel = budgetOfficerLevel;
    }

    public AdmStructureKindsEnum getAdmStructureKind() {
        return admStructureKind;
    }

    public void setAdmStructureKind(AdmStructureKindsEnum admStructureKind) {
        this.admStructureKind = admStructureKind;
    }

    public AdministrationType getAdministration() {
        return administration;
    }

    public void setAdministration(AdministrationType administration) {
        this.administration = administration;
    }

    public GovernmentType getGovernment() {
        return government;
    }

    public void setGovernment(GovernmentType government) {
        this.government = government;
    }

    public GovernmentType getPrevGovernment() {
        return prevGovernment;
    }

    public void setPrevGovernment(GovernmentType prevGovernment) {
        this.prevGovernment = prevGovernment;
    }

    public List<BatchRelationshipType> getStandaloneStructsToBatch() {
        return standaloneStructsToBatch;
    }

    public void setStandaloneStructsToBatch(List<BatchRelationshipType> standaloneStructsToBatch) {
        this.standaloneStructsToBatch = standaloneStructsToBatch;
    }

    public BatchRelationshipType getStewardWithMoneyBatch() {
        return stewardWithMoneyBatch;
    }

    public void setStewardWithMoneyBatch(BatchRelationshipType stewardWithMoneyBatch) {
        this.stewardWithMoneyBatch = stewardWithMoneyBatch;
    }

    public List<BatchRelationshipType> getAdvBoardsToComOrAdmStruct() {
        return advBoardsToComOrAdmStruct;
    }

    public void setAdvBoardsToComOrAdmStruct(List<BatchRelationshipType> advBoardsToComOrAdmStruct) {
        this.advBoardsToComOrAdmStruct = advBoardsToComOrAdmStruct;
    }
}

