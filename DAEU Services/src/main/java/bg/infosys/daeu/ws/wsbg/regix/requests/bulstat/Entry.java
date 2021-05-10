package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entry", namespace = "http://www.bulstat.bg/Entry", propOrder = {
 "entryTime"
})
@XmlSeeAlso({
 UIC.class
})
public class Entry {

 @XmlElement(name = "EntryTime")
 @XmlSchemaType(name = "dateTime")
 protected XMLGregorianCalendar entryTime;

 /**
  * Gets the value of the entryTime property.
  * 
  * @return
  *     possible object is
  *     {@link XMLGregorianCalendar }
  *     
  */
 public XMLGregorianCalendar getEntryTime() {
     return entryTime;
 }

 /**
  * Sets the value of the entryTime property.
  * 
  * @param value
  *     allowed object is
  *     {@link XMLGregorianCalendar }
  *     
  */
 public void setEntryTime(XMLGregorianCalendar value) {
     this.entryTime = value;
 }

}
