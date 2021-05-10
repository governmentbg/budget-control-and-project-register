package bg.infosys.daeu.ws.wsbg.iisda.models;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdministrationType", propOrder = {
        "unitPositionsSubjectOfHeadOfAdm",
        "unitPositionsInAdm",
//        "correspondenceData",
//        "address",
//        "workingTime",
//        "admTerritorialRange",
//        "secretaryAbsenceReason",
//        "secretaryPosition"
})
public class AdministrationType {

    public List<UnitPositionCommonDataType> getUnitPositionsSubjectOfHeadOfAdm() {
        return unitPositionsSubjectOfHeadOfAdm;
    }

    public List<UnitPositionCommonDataType> getUnitPositionsInAdm() {
        return unitPositionsInAdm;
    }

    public Long getHeadPositionID() {
        return headPositionID;
    }

    @XmlElement(name = "UnitPositionsSubjectOfHeadOfAdm")
    protected List<UnitPositionCommonDataType> unitPositionsSubjectOfHeadOfAdm;
    @XmlElement(name = "UnitPositionsInAdm")
    protected List<UnitPositionCommonDataType> unitPositionsInAdm;

    @XmlAttribute(name = "BatchID", required = true)
    protected long batchID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "UIC")
    protected String uic;
    @XmlAttribute(name = "HeadPositionID")
    protected Long headPositionID;

    public long getBatchID() {
        return batchID;
    }

}