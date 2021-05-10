package bg.infosys.daeu.ws.wsbg.regix;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"checkResultResult"
})
@XmlRootElement(name = "CheckResultResponse")
public class CheckResultResponse {
	@XmlElement(name = "CheckResultResult")
	protected ServiceResultData checkResultResult;

	/**
	 * Gets the value of the checkResultResult property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link ServiceResultData }
	 *     
	 */
	public ServiceResultData getCheckResultResult() {
		return checkResultResult;
	}

	/**
	 * Sets the value of the checkResultResult property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link ServiceResultData }
	 *     
	 */
	public void setCheckResultResult(ServiceResultData value) {
		this.checkResultResult = value;
	}
}
