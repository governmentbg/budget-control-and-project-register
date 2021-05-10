package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MandateType", propOrder = {
 "type",
 "mandateValue"
})
public class MandateType {

 @XmlElement(name = "Type")
 protected String type;
 @XmlElement(name = "MandateValue")
 protected String mandateValue;

 /**
  * Gets the value of the type property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getType() {
     return type;
 }

 /**
  * Sets the value of the type property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setType(String value) {
     this.type = value;
 }

 /**
  * Gets the value of the mandateValue property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getMandateValue() {
     return mandateValue;
 }

 /**
  * Sets the value of the mandateValue property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setMandateValue(String value) {
     this.mandateValue = value;
 }

}
