package bg.infosys.daeu.ws.wsbg.regix;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessMatrixType", propOrder = {
		"hasAccess",
		"name",
		"properties"
})
@XmlSeeAlso({
	DataContainer.Matrix.class
})
public class AccessMatrixType {
	@XmlElement(name = "HasAccess")
	protected boolean hasAccess;
	@XmlElement(name = "Name")
	protected String name;
	@XmlElement(name = "Properties")
	protected ArrayOfAMPropertyType properties;

	/**
	 * Gets the value of the hasAccess property.
	 * 
	 */
	public boolean isHasAccess() {
		return hasAccess;
	}

	/**
	 * Sets the value of the hasAccess property.
	 * 
	 */
	public void setHasAccess(boolean value) {
		this.hasAccess = value;
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
	 * Gets the value of the properties property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link ArrayOfAMPropertyType }
	 *     
	 */
	public ArrayOfAMPropertyType getProperties() {
		return properties;
	}

	/**
	 * Sets the value of the properties property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link ArrayOfAMPropertyType }
	 *     
	 */
	public void setProperties(ArrayOfAMPropertyType value) {
		this.properties = value;
	}
}
