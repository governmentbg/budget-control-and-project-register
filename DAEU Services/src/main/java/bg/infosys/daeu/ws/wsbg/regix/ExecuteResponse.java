package bg.infosys.daeu.ws.wsbg.regix;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"executeResult"
})
@XmlRootElement(name = "ExecuteResponse")
public class ExecuteResponse {
	@XmlElement(name = "ExecuteResult")
	protected ServiceExecuteResult executeResult;

	/**
	 * Gets the value of the executeResult property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link ServiceExecuteResult }
	 *     
	 */
	public ServiceExecuteResult getExecuteResult() {
		return executeResult;
	}

	/**
	 * Sets the value of the executeResult property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link ServiceExecuteResult }
	 *     
	 */
	public void setExecuteResult(ServiceExecuteResult value) {
		this.executeResult = value;
	}
}
