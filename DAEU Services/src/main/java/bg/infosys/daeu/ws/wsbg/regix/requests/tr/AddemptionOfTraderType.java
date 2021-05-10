package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddemptionOfTraderType", propOrder = {
 "address",
 "contacts",
 "competentAuthorityForRegistration",
 "registrationNumber"
})
public class AddemptionOfTraderType {

 @XmlElement(name = "Address")
 protected AddressType address;
 @XmlElement(name = "Contacts")
 protected ContactsType contacts;
 @XmlElement(name = "CompetentAuthorityForRegistration")
 protected String competentAuthorityForRegistration;
 @XmlElement(name = "RegistrationNumber")
 protected String registrationNumber;

 /**
  * Gets the value of the address property.
  * 
  * @return
  *     possible object is
  *     {@link AddressType }
  *     
  */
 public AddressType getAddress() {
     return address;
 }

 /**
  * Sets the value of the address property.
  * 
  * @param value
  *     allowed object is
  *     {@link AddressType }
  *     
  */
 public void setAddress(AddressType value) {
     this.address = value;
 }

 /**
  * Gets the value of the contacts property.
  * 
  * @return
  *     possible object is
  *     {@link ContactsType }
  *     
  */
 public ContactsType getContacts() {
     return contacts;
 }

 /**
  * Sets the value of the contacts property.
  * 
  * @param value
  *     allowed object is
  *     {@link ContactsType }
  *     
  */
 public void setContacts(ContactsType value) {
     this.contacts = value;
 }

 /**
  * Gets the value of the competentAuthorityForRegistration property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getCompetentAuthorityForRegistration() {
     return competentAuthorityForRegistration;
 }

 /**
  * Sets the value of the competentAuthorityForRegistration property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setCompetentAuthorityForRegistration(String value) {
     this.competentAuthorityForRegistration = value;
 }

 /**
  * Gets the value of the registrationNumber property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getRegistrationNumber() {
     return registrationNumber;
 }

 /**
  * Sets the value of the registrationNumber property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setRegistrationNumber(String value) {
     this.registrationNumber = value;
 }

}
