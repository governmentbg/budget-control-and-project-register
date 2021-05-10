package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Communication", namespace = "http://www.bulstat.bg/Communication", propOrder = {
 "type",
 "value"
})
public class Communication
 extends Entry
{

 @XmlElement(name = "Type", required = true)
 protected NomenclatureEntry type;
 @XmlElement(name = "Value", required = true)
 protected String value;

 /**
  * Gets the value of the type property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getType() {
     return type;
 }

 /**
  * Sets the value of the type property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setType(NomenclatureEntry value) {
     this.type = value;
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