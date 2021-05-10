package bg.infosys.daeu.ws.wsbg.regix.mon.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import bg.infosys.daeu.ws.wsbg.regix.mon.DiplomaDocumentType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiplomaDocumentsType", namespace = "http://egov.bg/RegiX/MOMN/RDSO/DiplomaDocumentsResponse", propOrder = {
    "generationTimeStamp",
    "diplomaDocument"
})
@XmlRootElement(name="DiplomaDocumentsResponse")
public class DiplomaDocumentsType {

    @XmlElement(name = "GenerationTimeStamp")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar generationTimeStamp;
    @XmlElement(name = "DiplomaDocument", nillable = true)
    protected List<DiplomaDocumentType> diplomaDocument;

    public XMLGregorianCalendar getGenerationTimeStamp() {
        return generationTimeStamp;
    }

    public void setGenerationTimeStamp(XMLGregorianCalendar value) {
        this.generationTimeStamp = value;
    }

    public List<DiplomaDocumentType> getDiplomaDocument() {
        if (diplomaDocument == null) {
            diplomaDocument = new ArrayList<DiplomaDocumentType>();
        }
        return this.diplomaDocument;
    }

}
