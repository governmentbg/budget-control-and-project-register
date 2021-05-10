package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NonMonetaryDepositType", propOrder = {
 "description",
 "value"
})
public class NonMonetaryDepositType {

 @XmlElement(name = "Description")
 protected String description;
 @XmlElement(name = "Value")
 protected String value;

 /**
  * Gets the value of the description property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getDescription() {
     return description;
 }

 /**
  * Sets the value of the description property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setDescription(String value) {
     this.description = value;
 }

 /**
  * Gets the value of the value property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getValue() {
     return value;
 }

 /**
  * Sets the value of the value property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setValue(String value) {
     this.value = value;
 }

}