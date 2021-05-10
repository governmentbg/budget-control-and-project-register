package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetStateOfPlayRequest", namespace = "http://www.bulstat.bg/GetStateOfPlayRequest", propOrder = {
 "uic",
 "_case"
})
@XmlRootElement(name = "GetStateOfPlayRequest")
public class GetStateOfPlayRequest {

 @XmlElement(name = "UIC")
 protected String uic;
 @XmlElement(name = "Case")
 protected GetStateOfPlayRequest.Case _case;

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
  * Gets the value of the case property.
  * 
  * @return
  *     possible object is
  *     {@link GetStateOfPlayRequest.Case }
  *     
  */
 public GetStateOfPlayRequest.Case getCase() {
     return _case;
 }

 /**
  * Sets the value of the case property.
  * 
  * @param value
  *     allowed object is
  *     {@link GetStateOfPlayRequest.Case }
  *     
  */
 public void setCase(GetStateOfPlayRequest.Case value) {
     this._case = value;
 }

 @XmlAccessorType(XmlAccessType.FIELD)
 @XmlType(name = "", propOrder = {
     "court",
     "year",
     "number"
 })
 public static class Case {

     @XmlElement(name = "Court", namespace = "http://www.bulstat.bg/GetStateOfPlayRequest", required = true)
     protected NomenclatureEntry court;
     @XmlElement(name = "Year", namespace = "http://www.bulstat.bg/GetStateOfPlayRequest")
     protected int year;
     @XmlElement(name = "Number", namespace = "http://www.bulstat.bg/GetStateOfPlayRequest", required = true)
     protected String number;

     /**
      * Gets the value of the court property.
      * 
      * @return
      *     possible object is
      *     {@link NomenclatureEntry }
      *     
      */
     public NomenclatureEntry getCourt() {
         return court;
     }

     /**
      * Sets the value of the court property.
      * 
      * @param value
      *     allowed object is
      *     {@link NomenclatureEntry }
      *     
      */
     public void setCourt(NomenclatureEntry value) {
         this.court = value;
     }

     /**
      * Gets the value of the year property.
      * 
      */
     public int getYear() {
         return year;
     }

     /**
      * Sets the value of the year property.
      * 
      */
     public void setYear(int value) {
         this.year = value;
     }

     /**
      * Gets the value of the number property.
      * 
      * @return
      *     possible object is
      *     {@link String }
      *     
      */
     public String getNumber() {
         return number;
     }

     /**
      * Sets the value of the number property.
      * 
      * @param value
      *     allowed object is
      *     {@link String }
      *     
      */
     public void setNumber(String value) {
         this.number = value;
     }

 }

}