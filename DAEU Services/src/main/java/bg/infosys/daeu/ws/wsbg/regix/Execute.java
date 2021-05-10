package bg.infosys.daeu.ws.wsbg.regix;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"request"
})
@XmlRootElement(name = "Execute")
public class Execute {
	protected ServiceRequestData request;

	/**
	 * Gets the value of the request property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link ServiceRequestData }
	 *     
	 */
	public ServiceRequestData getRequest() {
		return request;
	}

	/**
	 * Sets the value of the request property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link ServiceRequestData }
	 *     
	 */
	public void setRequest(ServiceRequestData value) {
		this.request = value;
	}
}
