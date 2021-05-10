package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LegalEntity", namespace = "http://www.bulstat.bg/LegalEntity", propOrder = {
 "country",
 "legalForm",
 "legalStatute",
 "subjectGroup",
 "cyrillicFullName",
 "cyrillicShortName",
 "latinFullName",
 "subordinateLevel",
 "trStatus"
})
public class LegalEntity
 extends Entry
{

 @XmlElement(name = "Country")
 protected NomenclatureEntry country;
 @XmlElement(name = "LegalForm")
 protected NomenclatureEntry legalForm;
 @XmlElement(name = "LegalStatute")
 protected NomenclatureEntry legalStatute;
 @XmlElement(name = "SubjectGroup")
 protected NomenclatureEntry subjectGroup;
 @XmlElement(name = "CyrillicFullName")
 protected String cyrillicFullName;
 @XmlElement(name = "CyrillicShortName")
 protected String cyrillicShortName;
 @XmlElement(name = "LatinFullName")
 protected String latinFullName;
 @XmlElement(name = "SubordinateLevel")
 protected NomenclatureEntry subordinateLevel;
 @XmlElement(name = "TRStatus")
 protected String trStatus;

 /**
  * Gets the value of the country property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getCountry() {
     return country;
 }

 /**
  * Sets the value of the country property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setCountry(NomenclatureEntry value) {
     this.country = value;
 }

 /**
  * Gets the value of the legalForm property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getLegalForm() {
     return legalForm;
 }

 /**
  * Sets the value of the legalForm property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setLegalForm(NomenclatureEntry value) {
     this.legalForm = value;
 }

 /**
  * Gets the value of the legalStatute property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getLegalStatute() {
     return legalStatute;
 }

 /**
  * Sets the value of the legalStatute property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setLegalStatute(NomenclatureEntry value) {
     this.legalStatute = value;
 }

 /**
  * Gets the value of the subjectGroup property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getSubjectGroup() {
     return subjectGroup;
 }

 /**
  * Sets the value of the subjectGroup property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setSubjectGroup(NomenclatureEntry value) {
     this.subjectGroup = value;
 }

 /**
  * Gets the value of the cyrillicFullName property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getCyrillicFullName() {
     return cyrillicFullName;
 }

 /**
  * Sets the value of the cyrillicFullName property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setCyrillicFullName(String value) {
     this.cyrillicFullName = value;
 }

 /**
  * Gets the value of the cyrillicShortName property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getCyrillicShortName() {
     return cyrillicShortName;
 }

 /**
  * Sets the value of the cyrillicShortName property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setCyrillicShortName(String value) {
     this.cyrillicShortName = value;
 }

 /**
  * Gets the value of the latinFullName property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getLatinFullName() {
     return latinFullName;
 }

 /**
  * Sets the value of the latinFullName property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setLatinFullName(String value) {
     this.latinFullName = value;
 }

 /**
  * Gets the value of the subordinateLevel property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getSubordinateLevel() {
     return subordinateLevel;
 }

 /**
  * Sets the value of the subordinateLevel property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setSubordinateLevel(NomenclatureEntry value) {
     this.subordinateLevel = value;
 }

 /**
  * Gets the value of the trStatus property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getTRStatus() {
     return trStatus;
 }

 /**
  * Sets the value of the trStatus property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setTRStatus(String value) {
     this.trStatus = value;
 }

}
