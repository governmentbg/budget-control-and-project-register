package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Subject", namespace = "http://www.bulstat.bg/Subject", propOrder = {
 "uic",
 "subjectType",
 "legalEntitySubject",
 "naturalPersonSubject",
 "countrySubject",
 "communications",
 "addresses",
 "remark"
})
public class Subject
 extends Entry
{

 @XmlElement(name = "UIC", required = true)
 protected UIC uic;
 @XmlElement(name = "SubjectType", required = true)
 protected NomenclatureEntry subjectType;
 @XmlElement(name = "LegalEntitySubject")
 protected LegalEntity legalEntitySubject;
 @XmlElement(name = "NaturalPersonSubject")
 protected NaturalPerson naturalPersonSubject;
 @XmlElement(name = "CountrySubject")
 protected NomenclatureEntry countrySubject;
 @XmlElement(name = "Communications")
 protected List<Communication> communications;
 @XmlElement(name = "Addresses")
 protected List<Address> addresses;
 @XmlElement(name = "Remark")
 protected String remark;

 /**
  * Gets the value of the uic property.
  * 
  * @return
  *     possible object is
  *     {@link UIC }
  *     
  */
 public UIC getUIC() {
     return uic;
 }

 /**
  * Sets the value of the uic property.
  * 
  * @param value
  *     allowed object is
  *     {@link UIC }
  *     
  */
 public void setUIC(UIC value) {
     this.uic = value;
 }

 /**
  * Gets the value of the subjectType property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getSubjectType() {
     return subjectType;
 }

 /**
  * Sets the value of the subjectType property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setSubjectType(NomenclatureEntry value) {
     this.subjectType = value;
 }

 /**
  * Gets the value of the legalEntitySubject property.
  * 
  * @return
  *     possible object is
  *     {@link LegalEntity }
  *     
  */
 public LegalEntity getLegalEntitySubject() {
     return legalEntitySubject;
 }

 /**
  * Sets the value of the legalEntitySubject property.
  * 
  * @param value
  *     allowed object is
  *     {@link LegalEntity }
  *     
  */
 public void setLegalEntitySubject(LegalEntity value) {
     this.legalEntitySubject = value;
 }

 /**
  * Gets the value of the naturalPersonSubject property.
  * 
  * @return
  *     possible object is
  *     {@link NaturalPerson }
  *     
  */
 public NaturalPerson getNaturalPersonSubject() {
     return naturalPersonSubject;
 }

 /**
  * Sets the value of the naturalPersonSubject property.
  * 
  * @param value
  *     allowed object is
  *     {@link NaturalPerson }
  *     
  */
 public void setNaturalPersonSubject(NaturalPerson value) {
     this.naturalPersonSubject = value;
 }

 /**
  * Gets the value of the countrySubject property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getCountrySubject() {
     return countrySubject;
 }

 /**
  * Sets the value of the countrySubject property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setCountrySubject(NomenclatureEntry value) {
     this.countrySubject = value;
 }

 /**
  * Gets the value of the communications property.
  * 
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the communications property.
  * 
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getCommunications().add(newItem);
  * </pre>
  * 
  * 
  * <p>
  * Objects of the following type(s) are allowed in the list
  * {@link Communication }
  * 
  * 
  */
 public List<Communication> getCommunications() {
     if (communications == null) {
         communications = new ArrayList<Communication>();
     }
     return this.communications;
 }

 /**
  * Gets the value of the addresses property.
  * 
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the addresses property.
  * 
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getAddresses().add(newItem);
  * </pre>
  * 
  * 
  * <p>
  * Objects of the following type(s) are allowed in the list
  * {@link Address }
  * 
  * 
  */
 public List<Address> getAddresses() {
     if (addresses == null) {
         addresses = new ArrayList<Address>();
     }
     return this.addresses;
 }

 /**
  * Gets the value of the remark property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getRemark() {
     return remark;
 }

 /**
  * Sets the value of the remark property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setRemark(String value) {
     this.remark = value;
 }

}