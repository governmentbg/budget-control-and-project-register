package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetailType", propOrder = {
 "fieldName",
 "fieldCode",
 "fieldOrder",
 "subject"
})
public class DetailType {

 @XmlElement(name = "FieldName", namespace = "http://egov.bg/RegiX/AV/TR")
 protected String fieldName;
 @XmlElement(name = "FieldCode", namespace = "http://egov.bg/RegiX/AV/TR")
 protected String fieldCode;
 @XmlElement(name = "FieldOrder", namespace = "http://egov.bg/RegiX/AV/TR")
 protected String fieldOrder;
 @XmlElement(name = "Subject", namespace = "http://egov.bg/RegiX/AV/TR")
 protected SubjectType subject;

 /**
  * Gets the value of the fieldName property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getFieldName() {
     return fieldName;
 }

 /**
  * Sets the value of the fieldName property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setFieldName(String value) {
     this.fieldName = value;
 }

 /**
  * Gets the value of the fieldCode property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getFieldCode() {
     return fieldCode;
 }

 /**
  * Sets the value of the fieldCode property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setFieldCode(String value) {
     this.fieldCode = value;
 }

 /**
  * Gets the value of the fieldOrder property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getFieldOrder() {
     return fieldOrder;
 }

 /**
  * Sets the value of the fieldOrder property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setFieldOrder(String value) {
     this.fieldOrder = value;
 }

 /**
  * Gets the value of the subject property.
  * 
  * @return
  *     possible object is
  *     {@link SubjectType }
  *     
  */
 public SubjectType getSubject() {
     return subject;
 }

 /**
  * Sets the value of the subject property.
  * 
  * @param value
  *     allowed object is
  *     {@link SubjectType }
  *     
  */
 public void setSubject(SubjectType value) {
     this.subject = value;
 }

}