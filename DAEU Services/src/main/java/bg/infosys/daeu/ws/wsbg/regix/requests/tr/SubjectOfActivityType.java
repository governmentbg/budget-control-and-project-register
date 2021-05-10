package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectOfActivityType", propOrder = {
    "subject",
    "isBank",
    "isInsurer"
})
public class SubjectOfActivityType {

    @XmlElement(name = "Subject")
    protected String subject;
    @XmlElement(name = "IsBank")
    protected Boolean isBank;
    @XmlElement(name = "IsInsurer")
    protected Boolean isInsurer;

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the isBank property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsBank() {
        return isBank;
    }

    /**
     * Sets the value of the isBank property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsBank(Boolean value) {
        this.isBank = value;
    }

    /**
     * Gets the value of the isInsurer property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsInsurer() {
        return isInsurer;
    }

    /**
     * Sets the value of the isInsurer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsInsurer(Boolean value) {
        this.isInsurer = value;
    }

}
