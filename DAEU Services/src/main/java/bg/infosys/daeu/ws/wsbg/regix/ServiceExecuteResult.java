package bg.infosys.daeu.ws.wsbg.regix;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceExecuteResult", propOrder = {
		"serviceCallID",
		"hasError",
		"error"
})
public class ServiceExecuteResult {
	@XmlElement(name = "ServiceCallID", required = true)
	protected BigDecimal serviceCallID;
	@XmlElement(name = "HasError")
	protected boolean hasError;
	@XmlElement(name = "Error")
	protected String error;

	/**
	 * Gets the value of the serviceCallID property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link BigDecimal }
	 *     
	 */
	public BigDecimal getServiceCallID() {
		return serviceCallID;
	}

	/**
	 * Sets the value of the serviceCallID property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link BigDecimal }
	 *     
	 */
	public void setServiceCallID(BigDecimal value) {
		this.serviceCallID = value;
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
