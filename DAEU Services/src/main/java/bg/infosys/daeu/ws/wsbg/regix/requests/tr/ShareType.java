package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShareType", propOrder = {
 "type",
 "count",
 "nominalValue"
})
public class ShareType {

 @XmlElement(name = "Type")
 protected String type;
 @XmlElement(name = "Count")
 protected String count;
 @XmlElement(name = "NominalValue")
 protected String nominalValue;

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
  * Gets the value of the count property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getCount() {
     return count;
 }

 /**
  * Sets the value of the count property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setCount(String value) {
     this.count = value;
 }

 /**
  * Gets the value of the nominalValue property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getNominalValue() {
     return nominalValue;
 }

 /**
  * Sets the value of the nominalValue property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setNominalValue(String value) {
     this.nominalValue = value;
 }

}
