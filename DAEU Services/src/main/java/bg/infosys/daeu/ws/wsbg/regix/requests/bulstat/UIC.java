package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UIC", namespace = "http://www.bulstat.bg/UIC", propOrder = {
 "uic",
 "uicType"
})
public class UIC
 extends Entry
{

 @XmlElement(name = "UIC")
 protected String uic;
 @XmlElement(name = "UICType")
 protected NomenclatureEntry uicType;

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
  * Gets the value of the uicType property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getUICType() {
     return uicType;
 }

 /**
  * Sets the value of the uicType property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setUICType(NomenclatureEntry value) {
     this.uicType = value;
 }

}