package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NaturalPerson", namespace = "http://www.bulstat.bg/NaturalPerson", propOrder = {
 "country",
 "egn",
 "lnc",
 "cyrillicName",
 "latinName",
 "birthDate",
 "identificationDoc"
})
public class NaturalPerson
 extends Entry
{

 @XmlElement(name = "Country")
 protected NomenclatureEntry country;
 @XmlElement(name = "EGN")
 protected String egn;
 @XmlElement(name = "LNC")
 protected String lnc;
 @XmlElement(name = "CyrillicName", required = true)
 protected String cyrillicName;
 @XmlElement(name = "LatinName", required = true)
 protected String latinName;
 @XmlElement(name = "BirthDate")
 protected Object birthDate;
 @XmlElement(name = "IdentificationDoc")
 protected IdentificationDoc identificationDoc;

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
  * Gets the value of the egn property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getEGN() {
     return egn;
 }

 /**
  * Sets the value of the egn property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setEGN(String value) {
     this.egn = value;
 }

 /**
  * Gets the value of the lnc property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getLNC() {
     return lnc;
 }

 /**
  * Sets the value of the lnc property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setLNC(String value) {
     this.lnc = value;
 }

 /**
  * Gets the value of the cyrillicName property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getCyrillicName() {
     return cyrillicName;
 }

 /**
  * Sets the value of the cyrillicName property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setCyrillicName(String value) {
     this.cyrillicName = value;
 }

 /**
  * Gets the value of the latinName property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getLatinName() {
     return latinName;
 }

 /**
  * Sets the value of the latinName property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setLatinName(String value) {
     this.latinName = value;
 }

 /**
  * Gets the value of the birthDate property.
  * 
  * @return
  *     possible object is
  *     {@link Object }
  *     
  */
 public Object getBirthDate() {
     return birthDate;
 }

 /**
  * Sets the value of the birthDate property.
  * 
  * @param value
  *     allowed object is
  *     {@link Object }
  *     
  */
 public void setBirthDate(Object value) {
     this.birthDate = value;
 }

 /**
  * Gets the value of the identificationDoc property.
  * 
  * @return
  *     possible object is
  *     {@link IdentificationDoc }
  *     
  */
 public IdentificationDoc getIdentificationDoc() {
     return identificationDoc;
 }

 /**
  * Sets the value of the identificationDoc property.
  * 
  * @param value
  *     allowed object is
  *     {@link IdentificationDoc }
  *     
  */
 public void setIdentificationDoc(IdentificationDoc value) {
     this.identificationDoc = value;
 }

}
