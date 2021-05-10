package bg.infosys.daeu.ws.wsbg.iisda.models;


import bg.infosys.daeu.ws.wsbg.iisda.models.enums.GovernmentTypeEnum;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GovernmentType")
public class GovernmentType {

    @XmlAttribute(name = "GovernmentID", required = true)
    protected long governmentID;
    @XmlAttribute(name = "VersionID", required = true)
    protected long versionID;
    @XmlAttribute(name = "Type", required = true)
    protected GovernmentTypeEnum type;
    @XmlAttribute(name = "CeasePowersDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar ceasePowersDate;
    @XmlAttribute(name = "FromDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fromDate;
    @XmlAttribute(name = "ToDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar toDate;

}