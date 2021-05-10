package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address", namespace = "http://www.bulstat.bg/Address", propOrder = {
 "addressType",
 "country",
 "postalCode",
 "postalBox",
 "foreignLocation",
 "location",
 "region",
 "street",
 "streetNumber",
 "building",
 "entrance",
 "floor",
 "apartment"
})
public class Address
 extends Entry
{

 @XmlElement(name = "AddressType", required = true)
 protected NomenclatureEntry addressType;
 @XmlElement(name = "Country", required = true)
 protected NomenclatureEntry country;
 @XmlElement(name = "PostalCode")
 protected String postalCode;
 @XmlElement(name = "PostalBox")
 protected String postalBox;
 @XmlElement(name = "ForeignLocation")
 protected String foreignLocation;
 @XmlElement(name = "Location")
 protected NomenclatureEntry location;
 @XmlElement(name = "Region")
 protected NomenclatureEntry region;
 @XmlElement(name = "Street")
 protected String street;
 @XmlElement(name = "StreetNumber")
 protected String streetNumber;
 @XmlElement(name = "Building")
 protected String building;
 @XmlElement(name = "Entrance")
 protected String entrance;
 @XmlElement(name = "Floor")
 protected String floor;
 @XmlElement(name = "Apartment")
 protected String apartment;

 /**
  * Gets the value of the addressType property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getAddressType() {
     return addressType;
 }

 /**
  * Sets the value of the addressType property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setAddressType(NomenclatureEntry value) {
     this.addressType = value;
 }

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
  * Gets the value of the postalCode property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getPostalCode() {
     return postalCode;
 }

 /**
  * Sets the value of the postalCode property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setPostalCode(String value) {
     this.postalCode = value;
 }

 /**
  * Gets the value of the postalBox property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getPostalBox() {
     return postalBox;
 }

 /**
  * Sets the value of the postalBox property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setPostalBox(String value) {
     this.postalBox = value;
 }

 /**
  * Gets the value of the foreignLocation property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getForeignLocation() {
     return foreignLocation;
 }

 /**
  * Sets the value of the foreignLocation property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setForeignLocation(String value) {
     this.foreignLocation = value;
 }

 /**
  * Gets the value of the location property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getLocation() {
     return location;
 }

 /**
  * Sets the value of the location property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setLocation(NomenclatureEntry value) {
     this.location = value;
 }

 /**
  * Gets the value of the region property.
  * 
  * @return
  *     possible object is
  *     {@link NomenclatureEntry }
  *     
  */
 public NomenclatureEntry getRegion() {
     return region;
 }

 /**
  * Sets the value of the region property.
  * 
  * @param value
  *     allowed object is
  *     {@link NomenclatureEntry }
  *     
  */
 public void setRegion(NomenclatureEntry value) {
     this.region = value;
 }

 /**
  * Gets the value of the street property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getStreet() {
     return street;
 }

 /**
  * Sets the value of the street property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setStreet(String value) {
     this.street = value;
 }

 /**
  * Gets the value of the streetNumber property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getStreetNumber() {
     return streetNumber;
 }

 /**
  * Sets the value of the streetNumber property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setStreetNumber(String value) {
     this.streetNumber = value;
 }

 /**
  * Gets the value of the building property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getBuilding() {
     return building;
 }

 /**
  * Sets the value of the building property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setBuilding(String value) {
     this.building = value;
 }

 /**
  * Gets the value of the entrance property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getEntrance() {
     return entrance;
 }

 /**
  * Sets the value of the entrance property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setEntrance(String value) {
     this.entrance = value;
 }

 /**
  * Gets the value of the floor property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getFloor() {
     return floor;
 }

 /**
  * Sets the value of the floor property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setFloor(String value) {
     this.floor = value;
 }

 /**
  * Gets the value of the apartment property.
  * 
  * @return
  *     possible object is
  *     {@link String }
  *     
  */
 public String getApartment() {
     return apartment;
 }

 /**
  * Sets the value of the apartment property.
  * 
  * @param value
  *     allowed object is
  *     {@link String }
  *     
  */
 public void setApartment(String value) {
     this.apartment = value;
 }

}