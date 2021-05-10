package bg.infosys.daeu.ws.wsbg.regix.requests.tr;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectType", propOrder = {
 "indent",
 "name",
 "indentType"
})
public class SubjectType {

 @XmlElement(name = "Indent", namespace = "http://egov.bg/RegiX/AV/TR")
 protected String indent;
 @XmlElement(name = "Name", namespace = "http://egov.bg/RegiX/AV/TR")
 protected String name;
 @XmlElement(name = "IndentType", namespace = "http://egov.bg/RegiX/AV/TR")
 protected IndentTypeType indentType;

 /**
  * Gets the value of the indent property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getIndent() {
     return indent;
 }

 /**
  * Sets the value of the indent property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setIndent(String value) {
     this.indent = value;
 }

 /**
  * Gets the value of the name property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getName() {
     return name;
 }

 /**
  * Sets the value of the name property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setName(String value) {
     this.name = value;
 }

 /**
  * Gets the value of the indentType property.
  * 
  * @return
  *     possible object is
  *     {@link IndentTypeType }
  *     
  */
 public IndentTypeType getIndentType() {
     return indentType;
 }

 /**
  * Sets the value of the indentType property.
  * 
  * @param value
  *     allowed object is
  *     {@link IndentTypeType }
  *     
  */
 public void setIndentType(IndentTypeType value) {
     this.indentType = value;
 }

}