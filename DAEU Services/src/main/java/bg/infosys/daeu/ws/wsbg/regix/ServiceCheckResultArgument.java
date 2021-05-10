package bg.infosys.daeu.ws.wsbg.regix;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceCheckResultArgument", propOrder = {
		"serviceCallID"
})
public class ServiceCheckResultArgument {
	@XmlElement(name = "ServiceCallID", required = true)
	protected BigDecimal serviceCallID;

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
}
