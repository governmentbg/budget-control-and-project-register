package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NomenclatureEntry", propOrder = {
 "code"
})
public class NomenclatureEntry {

 @XmlElement(name = "Code", required = true)
 protected String code;

 /**
  * Gets the value of the code property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getCode() {
     return code;
 }

 /**
  * Sets the value of the code property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setCode(String value) {
     this.code = value;
 }

}