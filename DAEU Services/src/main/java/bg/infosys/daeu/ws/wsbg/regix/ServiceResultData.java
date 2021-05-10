package bg.infosys.daeu.ws.wsbg.regix;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceResultData", propOrder = {
		"isReady",
		"data",
		"signature",
		"hasError",
		"error"
})
public class ServiceResultData {
	@XmlElement(name = "IsReady")
	protected boolean isReady;
	@XmlElement(name = "Data")
	protected DataContainer data;
	@XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
	protected Signature signature;
	@XmlElement(name = "HasError")
	protected boolean hasError;
	@XmlElement(name = "Error")
	protected String error;

	/**
	 * Gets the value of the isReady property.
	 * 
	 */
	public boolean isIsReady() {
		return isReady;
	}

	/**
	 * Sets the value of the isReady property.
	 * 
	 */
	public void setIsReady(boolean value) {
		this.isReady = value;
	}

	/**
	 * Gets the value of the data property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link DataContainer }
	 *     
	 */
	public DataContainer getData() {
		return data;
	}

	/**
	 * Sets the value of the data property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link DataContainer }
	 *     
	 */
	public void setData(DataContainer value) {
		this.data = value;
	}

	/**
	 * Gets the value of the signature property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Signature }
	 *     
	 */
	public Signature getSignature() {
		return signature;
	}

	/**
	 * Sets the value of the signature property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Signature }
	 *     
	 */
	public void setSignature(Signature value) {
		this.signature = value;
	}

	/**
	 * Gets the value of the hasError property.
	 * 
	 */
	public boolean isHasError() {
		return hasError;
	}

	/**
	 * Sets the value of the hasError property.
	 * 
	 */
	public void setHasError(boolean value) {
		this.hasError = value;
	}

	/**
	 * Gets the value of the error property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getError() {
		return error;
	}

	/**
	 * Sets the value of the error property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setError(String value) {
		this.error = value;
	}
}
