package bg.infosys.daeu.ws.wsbg.regix;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import bg.infosys.daeu.ws.wsbg.regix.mon.request.DiplomaSearchType;
import bg.infosys.daeu.ws.wsbg.regix.nra.request.EmploymentContractsRequest;
import bg.infosys.daeu.ws.wsbg.regix.requests.bulstat.GetStateOfPlayRequest;
import bg.infosys.daeu.ws.wsbg.regix.requests.tr.actualstate.ActualStateRequestType;
import bg.infosys.daeu.ws.wsbg.regix.requests.tr.validuic.ValidUICRequestType;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequestData", propOrder = {
    "operation",
    "argument",
    "eidToken",
    "callContext",
    "callbackURL",
    "employeeEGN",
    "citizenEGN",
    "signResult",
    "returnAccessMatrix"
})
@XmlSeeAlso({ValidUICRequestType.class, ActualStateRequestType.class, GetStateOfPlayRequest.class, EmploymentContractsRequest.class, DiplomaSearchType.class})
public class ServiceRequestData {
	 	@XmlElement(name = "Operation")
	    protected String operation;
	    @XmlElement(name = "Argument")
	    protected Argument argument;
	    @XmlElement(name = "EIDToken")
	    protected String eidToken;
	    @XmlElement(name = "CallContext")
	    protected CallContext callContext;
	    @XmlElement(name = "CallbackURL")
	    protected String callbackURL;
	    @XmlElement(name = "EmployeeEGN")
	    protected String employeeEGN;
	    @XmlElement(name = "CitizenEGN")
	    protected String citizenEGN;
	    @XmlElement(name = "SignResult")
	    protected boolean signResult;
	    @XmlElement(name = "ReturnAccessMatrix")
	    protected boolean returnAccessMatrix;

	    /**
	     * Gets the value of the operation property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getOperation() {
	        return operation;
	    }

	    /**
	     * Sets the value of the operation property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setOperation(String value) {
	        this.operation = value;
	    }

	    /**
	     * Gets the value of the argument property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link ServiceRequestData.Argument }
	     *     
	     */
	    public ServiceRequestData.Argument getArgument() {
	        return argument;
	    }

	    /**
	     * Sets the value of the argument property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link ServiceRequestData.Argument }
	     *     
	     */
	    public void setArgument(ServiceRequestData.Argument value) {
	        this.argument = value;
	    }

	    /**
	     * Gets the value of the eidToken property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getEIDToken() {
	        return eidToken;
	    }

	    /**
	     * Sets the value of the eidToken property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setEIDToken(String value) {
	        this.eidToken = value;
	    }

	    /**
	     * Gets the value of the callContext property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link CallContext }
	     *     
	     */
	    public CallContext getCallContext() {
	        return callContext;
	    }

	    /**
	     * Sets the value of the callContext property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link CallContext }
	     *     
	     */
	    public void setCallContext(CallContext value) {
	        this.callContext = value;
	    }

	    /**
	     * Gets the value of the callbackURL property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getCallbackURL() {
	        return callbackURL;
	    }

	    /**
	     * Sets the value of the callbackURL property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setCallbackURL(String value) {
	        this.callbackURL = value;
	    }

	    /**
	     * Gets the value of the employeeEGN property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getEmployeeEGN() {
	        return employeeEGN;
	    }

	    /**
	     * Sets the value of the employeeEGN property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setEmployeeEGN(String value) {
	        this.employeeEGN = value;
	    }

	    /**
	     * Gets the value of the citizenEGN property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getCitizenEGN() {
	        return citizenEGN;
	    }

	    /**
	     * Sets the value of the citizenEGN property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setCitizenEGN(String value) {
	        this.citizenEGN = value;
	    }

	    /**
	     * Gets the value of the signResult property.
	     * 
	     */
	    public boolean isSignResult() {
	        return signResult;
	    }

	    /**
	     * Sets the value of the signResult property.
	     * 
	     */
	    public void setSignResult(boolean value) {
	        this.signResult = value;
	    }

	    /**
	     * Gets the value of the returnAccessMatrix property.
	     * 
	     */
	    public boolean isReturnAccessMatrix() {
	        return returnAccessMatrix;
	    }

	    /**
	     * Sets the value of the returnAccessMatrix property.
	     * 
	     */
	    public void setReturnAccessMatrix(boolean value) {
	        this.returnAccessMatrix = value;
	    }


	    /**
	     * <p>Java class for anonymous complex type.
	     * 
	     * <p>The following schema fragment specifies the expected content contained within this class.
	     * 
	     * <pre>
	     * &lt;complexType>
	     *   &lt;complexContent>
	     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	     *       &lt;sequence>
	     *         &lt;any/>
	     *       &lt;/sequence>
	     *     &lt;/restriction>
	     *   &lt;/complexContent>
	     * &lt;/complexType>
	     * </pre>
	     * 
	     * 
	     */
	    @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	        "any"
	    })
	    public static class Argument {

	        @XmlAnyElement(lax = true)
	        protected Object any;

	        /**
	         * Gets the value of the any property.
	         * 
	         * @return
	         *     possible object is
	         *     {@link Object }
	         *     
	         */
	        public Object getAny() {
	            return any;
	        }

	        /**
	         * Sets the value of the any property.
	         * 
	         * @param value
	         *     allowed object is
	         *     {@link Object }
	         *     
	         */
	        public void setAny(Object value) {
	            this.any = value;
	        }

	    }
}
