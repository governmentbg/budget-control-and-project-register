package bg.infosys.daeu.ws.wsbg.regix.responses.tr.validuic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import bg.infosys.daeu.ws.wsbg.regix.requests.tr.LegalFormType;
import bg.infosys.daeu.ws.wsbg.regix.requests.tr.StatusType;

@XmlRootElement(name = "ValidUICResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidUICResponseType", namespace = "http://egov.bg/RegiX/AV/TR/ValidUICResponse", propOrder = {
    "status",
    "uic",
    "company",
    "legalForm",
    "dataValidForDate"
})
public class ValidUICResponseType {

    @XmlElement(name = "Status")
    protected StatusType status;
    @XmlElement(name = "UIC")
    protected String uic;
    @XmlElement(name = "Company")
    protected String company;
    @XmlElement(name = "LegalForm")
    protected LegalFormType legalForm;
    @XmlElement(name = "DataValidForDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataValidForDate;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusType }
     *     
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *     
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the uic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUIC() {
        return uic;
    }

    /**
     * Sets the value of the uic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUIC(String value) {
        this.uic = value;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompany(String value) {
        this.company = value;
    }

    /**
     * Gets the value of the legalForm property.
     * 
     * @return
     *     possible object is
     *     {@link LegalFormType }
     *     
     */
    public LegalFormType getLegalForm() {
        return legalForm;
    }

    /**
     * Sets the value of the legalForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalFormType }
     *     
     */
    public void setLegalForm(LegalFormType value) {
        this.legalForm = value;
    }

    /**
     * Gets the value of the dataValidForDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataValidForDate() {
        return dataValidForDate;
    }

    /**
     * Sets the value of the dataValidForDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataValidForDate(XMLGregorianCalendar value) {
        this.dataValidForDate = value;
    }
}
