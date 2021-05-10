package bg.infosys.daeu.ws.wsbg.regix.mon.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import bg.infosys.daeu.ws.wsbg.regix.mon.IdentifierType;

@XmlRootElement(name="DiplomaDocumentsRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiplomaSearchType", namespace="http://egov.bg/RegiX/MOMN/RDSO/DiplomaDocumentsRequest", propOrder = {
    "studentID",
    "idType",
    "documentNumber"
})
public class DiplomaSearchType {

    @XmlElement(name = "StudentID", required = true)
    protected String studentID;
    @XmlElement(name = "IDType", required = true)
    @XmlSchemaType(name = "string")
    protected IdentifierType idType;
    @XmlElement(name = "DocumentNumber", required = true)
    protected String documentNumber;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String value) {
        this.studentID = value;
    }

    public IdentifierType getIDType() {
        return idType;
    }

    public void setIDType(IdentifierType value) {
        this.idType = value;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String value) {
        this.documentNumber = value;
    }

}
