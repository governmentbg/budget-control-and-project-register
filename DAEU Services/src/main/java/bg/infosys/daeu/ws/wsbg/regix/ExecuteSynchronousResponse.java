package bg.infosys.daeu.ws.wsbg.regix;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"executeSynchronousResult"
})
@XmlRootElement(name = "ExecuteSynchronousResponse")
public class ExecuteSynchronousResponse {
	@XmlElement(name = "ExecuteSynchronousResult")
	protected ServiceResultData executeSynchronousResult;

	/**
	 * Gets the value of the executeSynchronousResult property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link ServiceResultData }
	 *     
	 */
	public ServiceResultData getExecuteSynchronousResult() {
		return executeSynchronousResult;
	}

	/**
	 * Sets the value of the executeSynchronousResult property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link ServiceResultData }
	 *     
	 */
	public void setExecuteSynchronousResult(ServiceResultData value) {
		this.executeSynchronousResult = value;
	}
}
