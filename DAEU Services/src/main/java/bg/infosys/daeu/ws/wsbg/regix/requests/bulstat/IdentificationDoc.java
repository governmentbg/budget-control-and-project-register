package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentificationDoc", namespace = "http://www.bulstat.bg/IdentificationDoc", propOrder = {
 "idType",
 "idNumber",
 "country",
 "issueDate",
 "expiryDate"
})
public class IdentificationDoc
 extends Entry
{

 @XmlElement(name = "IDType")
 protected NomenclatureEntry idType;
 @XmlElement(name = "IDNumber", required = true)
 protected String idNumber;
 @XmlElement(name = "Country")
 protected NomenclatureEntry country;
 @XmlElement(name = "IssueDate")
 protected Object issueDate;
 @XmlElement(name = "ExpiryDate")
 protected Object expiryDate;

 /**
  * Gets the value of the idType property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getIDType() {
     return idType;
 }

 /**
  * Sets the value of the idType property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setIDType(NomenclatureEntry value) {
     this.idType = value;
 }

 /**
  * Gets the value of the idNumber property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getIDNumber() {
     return idNumber;
 }

 /**
  * Sets the value of the idNumber property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setIDNumber(String value) {
     this.idNumber = value;
 }

 /**
  * Gets the value of the country property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getCountry() {
     return country;
 }

 /**
  * Sets the value of the country property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setCountry(NomenclatureEntry value) {
     this.country = value;
 }

 /**
  * Gets the value of the issueDate property.
  * 
  * @return
  *     possible object is
  *     {@link Object }
  *     
  */
 public Object getIssueDate() {
     return issueDate;
 }

 /**
  * Sets the value of the issueDate property.
  * 
  * @param value
  *     allowed object is
  *     {@link Object }
  *     
  */
 public void setIssueDate(Object value) {
     this.issueDate = value;
 }

 /**
  * Gets the value of the expiryDate property.
  * 
  * @return
  *     possible object is
  *     {@link Object }
  *     
  */
 public Object getExpiryDate() {
     return expiryDate;
 }

 /**
  * Sets the value of the expiryDate property.
  * 
  * @param value
  *     allowed object is
  *     {@link Object }
  *     
  */
 public void setExpiryDate(Object value) {
     this.expiryDate = value;
 }

}
