package bg.infosys.daeu.ws.wsbg.iisda.models;

import bg.infosys.daeu.ws.wsbg.iisda.models.enums.ObjectKindEnum;
import bg.infosys.daeu.ws.wsbg.iisda.models.enums.UnitPositionAdministrationTypeEnum;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)

public class UnitPositionCommonDataType {
    @XmlAttribute(name = "UnitPosID", required = true)
    protected long unitPosID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "Kind")
    protected ObjectKindEnum kind;
    @XmlAttribute(name = "ParentID")
    protected Long parentID;
    @XmlAttribute(name = "AdministrationType")
    protected UnitPositionAdministrationTypeEnum administrationType;
    @XmlAttribute(name = "Rank", required = true)
    protected long rank;

    public long getUnitPosID() {
        return unitPosID;
    }

    public void setUnitPosID(long unitPosID) {
        this.unitPosID = unitPosID;
    }

    public long getVersionID() {
        return versionID;
    }

    public void setVersionID(long versionID) {
        this.versionID = versionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(XMLGregorianCalendar creationDate) {
        this.creationDate = creationDate;
    }

    public XMLGregorianCalendar getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(XMLGregorianCalendar closingDate) {
        this.closingDate = closingDate;
    }

    public boolean isProvideAccessForPeopleWithDisabilities() {
        return provideAccessForPeopleWithDisabilities;
    }

    public void setProvideAccessForPeopleWithDisabilities(boolean provideAccessForPeopleWithDisabilities) {
        this.provideAccessForPeopleWithDisabilities = provideAccessForPeopleWithDisabilities;
    }

    @XmlAttribute(name = "CreationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar creationDate;
    @XmlAttribute(name = "ClosingDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar closingDate;
    @XmlAttribute(name = "ProvideAccessForPeopleWithDisabilities", required = true)
    protected boolean provideAccessForPeopleWithDisabilities;
//    @XmlAttribute(name = "Status", required = true)
//    protected UnitPositionStatusEnum status;
}
