package bg.infosys.daeu.ws.wsbg.regix;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"argument"
})
@XmlRootElement(name = "CheckResult")
public class CheckResult {
	protected ServiceCheckResultArgument argument;

	/**
	 * Gets the value of the argument property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link ServiceCheckResultArgument }
	 *     
	 */
	public ServiceCheckResultArgument getArgument() {
		return argument;
	}

	/**
	 * Sets the value of the argument property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link ServiceCheckResultArgument }
	 *     
	 */
	public void setArgument(ServiceCheckResultArgument value) {
		this.argument = value;
	}
}
