package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CapitalAmountType", propOrder = {
 "value",
 "euro"
})
public class CapitalAmountType {

 @XmlElement(name = "Value")
 protected String value;
 @XmlElement(name = "Euro")
 protected String euro;

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

 /**
  * Gets the value of the euro property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getEuro() {
     return euro;
 }

 /**
  * Sets the value of the euro property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setEuro(String value) {
     this.euro = value;
 }

}
