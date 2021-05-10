package bg.infosys.daeu.ws.wsbg.regix.mon;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertifiedDocumentType", propOrder = {
    "intID",
    "intStudentID",
    "idType",
    "vcName1",
    "vcName2",
    "vcName3",
    "intDocumentType",
    "vcDocumentName",
    "vcPrnSer",
    "vcPrnNo",
    "vcRegNo",
    "dtCertDate",
    "documentImages"
})
public class CertifiedDocumentType {

    protected Integer intID;
    protected String intStudentID;
    @XmlElement(name = "IDType")
    @XmlSchemaType(name = "string")
    protected IdentifierType idType;
    protected String vcName1;
    protected String vcName2;
    protected String vcName3;
    protected Integer intDocumentType;
    protected String vcDocumentName;
    protected String vcPrnSer;
    protected String vcPrnNo;
    protected String vcRegNo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dtCertDate;
    @XmlElement(name = "DocumentImages")
    protected CertifiedDocumentType.DocumentImages documentImages;

    public Integer getIntID() {
        return intID;
    }

    public void setIntID(Integer value) {
        this.intID = value;
    }

    public String getIntStudentID() {
        return intStudentID;
    }

    public void setIntStudentID(String value) {
        this.intStudentID = value;
    }

    public IdentifierType getIDType() {
        return idType;
    }

    public void setIDType(IdentifierType value) {
        this.idType = value;
    }

    public String getVcName1() {
        return vcName1;
    }

    public void setVcName1(String value) {
        this.vcName1 = value;
    }

    public String getVcName2() {
        return vcName2;
    }

    public void setVcName2(String value) {
        this.vcName2 = value;
    }

    public String getVcName3() {
        return vcName3;
    }

    public void setVcName3(String value) {
        this.vcName3 = value;
    }

    public Integer getIntDocumentType() {
        return intDocumentType;
    }

    public void setIntDocumentType(Integer value) {
        this.intDocumentType = value;
    }

    public String getVcDocumentName() {
        return vcDocumentName;
    }

    public void setVcDocumentName(String value) {
        this.vcDocumentName = value;
    }

    public String getVcPrnSer() {
        return vcPrnSer;
    }

    public void setVcPrnSer(String value) {
        this.vcPrnSer = value;
    }

    public String getVcPrnNo() {
        return vcPrnNo;
    }

    public void setVcPrnNo(String value) {
        this.vcPrnNo = value;
    }

    public String getVcRegNo() {
        return vcRegNo;
    }

    public void setVcRegNo(String value) {
        this.vcRegNo = value;
    }

    public XMLGregorianCalendar getDtCertDate() {
        return dtCertDate;
    }

    public void setDtCertDate(XMLGregorianCalendar value) {
        this.dtCertDate = value;
    }

    public CertifiedDocumentType.DocumentImages getDocumentImages() {
        return documentImages;
    }

    public void setDocumentImages(CertifiedDocumentType.DocumentImages value) {
        this.documentImages = value;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "documentImage"
    })
    public static class DocumentImages {

        @XmlElement(name = "DocumentImage")
        protected List<byte[]> documentImage;

        public List<byte[]> getDocumentImage() {
            if (documentImage == null) {
                documentImage = new ArrayList<byte[]>();
            }
            return this.documentImage;
        }

    }

}
