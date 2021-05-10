package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for LegalFormType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LegalFormType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LegalFormAbbr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LegalFormName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LegalFormType", propOrder = {
    "legalFormAbbr",
    "legalFormName"
})
public class LegalFormType {

    @XmlElement(name = "LegalFormAbbr", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String legalFormAbbr;
    @XmlElement(name = "LegalFormName", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String legalFormName;

    /**
     * Gets the value of the legalFormAbbr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalFormAbbr() {
        return legalFormAbbr;
    }

    /**
     * Sets the value of the legalFormAbbr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalFormAbbr(String value) {
        this.legalFormAbbr = value;
    }

    /**
     * Gets the value of the legalFormName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalFormName() {
        return legalFormName;
    }

    /**
     * Sets the value of the legalFormName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalFormName(String value) {
        this.legalFormName = value;
    }

}
