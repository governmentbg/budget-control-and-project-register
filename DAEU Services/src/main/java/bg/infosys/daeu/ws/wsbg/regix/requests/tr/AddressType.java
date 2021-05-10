package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressType", propOrder = {
    "countryCode",
    "country",
    "isForeign",
    "districtEkatte",
    "district",
    "municipalityEkatte",
    "municipality",
    "settlementEKATTE",
    "settlement",
    "area",
    "areaEkatte",
    "postCode",
    "foreignPlace",
    "housingEstate",
    "street",
    "streetNumber",
    "block",
    "entrance",
    "floor",
    "apartment"
})
public class AddressType {

    @XmlElement(name = "CountryCode", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String countryCode;
    @XmlElement(name = "Country", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String country;
    @XmlElement(name = "IsForeign", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String isForeign;
    @XmlElement(name = "DistrictEkatte", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String districtEkatte;
    @XmlElement(name = "District", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String district;
    @XmlElement(name = "MunicipalityEkatte", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String municipalityEkatte;
    @XmlElement(name = "Municipality", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String municipality;
    @XmlElement(name = "SettlementEKATTE", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String settlementEKATTE;
    @XmlElement(name = "Settlement", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String settlement;
    @XmlElement(name = "Area", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String area;
    @XmlElement(name = "AreaEkatte", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String areaEkatte;
    @XmlElement(name = "PostCode", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String postCode;
    @XmlElement(name = "ForeignPlace", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String foreignPlace;
    @XmlElement(name = "HousingEstate", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String housingEstate;
    @XmlElement(name = "Street", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String street;
    @XmlElement(name = "StreetNumber", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String streetNumber;
    @XmlElement(name = "Block", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String block;
    @XmlElement(name = "Entrance", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String entrance;
    @XmlElement(name = "Floor", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String floor;
    @XmlElement(name = "Apartment", namespace = "http://egov.bg/RegiX/AV/TR")
    protected String apartment;

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the isForeign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsForeign() {
        return isForeign;
    }

    /**
     * Sets the value of the isForeign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsForeign(String value) {
        this.isForeign = value;
    }

    /**
     * Gets the value of the districtEkatte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictEkatte() {
        return districtEkatte;
    }

    /**
     * Sets the value of the districtEkatte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictEkatte(String value) {
        this.districtEkatte = value;
    }

    /**
     * Gets the value of the district property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the value of the district property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrict(String value) {
        this.district = value;
    }

    /**
     * Gets the value of the municipalityEkatte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipalityEkatte() {
        return municipalityEkatte;
    }

    /**
     * Sets the value of the municipalityEkatte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipalityEkatte(String value) {
        this.municipalityEkatte = value;
    }

    /**
     * Gets the value of the municipality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipality() {
        return municipality;
    }

    /**
     * Sets the value of the municipality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipality(String value) {
        this.municipality = value;
    }

    /**
     * Gets the value of the settlementEKATTE property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementEKATTE() {
        return settlementEKATTE;
    }

    /**
     * Sets the value of the settlementEKATTE property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlementEKATTE(String value) {
        this.settlementEKATTE = value;
    }

    /**
     * Gets the value of the settlement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlement() {
        return settlement;
    }

    /**
     * Sets the value of the settlement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlement(String value) {
        this.settlement = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the areaEkatte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaEkatte() {
        return areaEkatte;
    }

    /**
     * Sets the value of the areaEkatte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaEkatte(String value) {
        this.areaEkatte = value;
    }

    /**
     * Gets the value of the postCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets the value of the postCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostCode(String value) {
        this.postCode = value;
    }

    /**
     * Gets the value of the foreignPlace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForeignPlace() {
        return foreignPlace;
    }

    /**
     * Sets the value of the foreignPlace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForeignPlace(String value) {
        this.foreignPlace = value;
    }

    /**
     * Gets the value of the housingEstate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHousingEstate() {
        return housingEstate;
    }

    /**
     * Sets the value of the housingEstate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHousingEstate(String value) {
        this.housingEstate = value;
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
     * Gets the value of the block property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlock() {
        return block;
    }

    /**
     * Sets the value of the block property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlock(String value) {
        this.block = value;
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
