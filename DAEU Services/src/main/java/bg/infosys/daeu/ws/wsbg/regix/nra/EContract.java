package bg.infosys.daeu.ws.wsbg.regix.nra;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EContract", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts", propOrder = {
    "contractorBulstat",
    "contractorName",
    "individualEIK",
    "individualNames",
    "startDate",
    "lastAmendDate",
    "endDate",
    "reason",
    "timeLimit",
    "ecoCode",
    "professionCode",
    "remuneration",
    "professionName",
    "ekatteCode"
})
public class EContract {

    @XmlElement(name = "ContractorBulstat", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    protected String contractorBulstat;
    @XmlElement(name = "ContractorName", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    protected String contractorName;
    @XmlElement(name = "IndividualEIK", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    protected String individualEIK;
    @XmlElement(name = "IndividualNames", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    protected String individualNames;
    @XmlElement(name = "StartDate", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "LastAmendDate", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastAmendDate;
    @XmlElement(name = "EndDate", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "Reason", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    protected String reason;
    @XmlElement(name = "TimeLimit", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar timeLimit;
    @XmlElement(name = "EcoCode", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    protected String ecoCode;
    @XmlElement(name = "ProfessionCode", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    protected String professionCode;
    @XmlElement(name = "Remuneration", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    protected BigDecimal remuneration;
    @XmlElement(name = "ProfessionName", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    protected String professionName;
    @XmlElement(name = "EKATTECode", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
    protected String ekatteCode;
	
    public String getContractorBulstat() {
		return contractorBulstat;
	}
	public void setContractorBulstat(String contractorBulstat) {
		this.contractorBulstat = contractorBulstat;
	}
	public String getContractorName() {
		return contractorName;
	}
	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}
	public String getIndividualEIK() {
		return individualEIK;
	}
	public void setIndividualEIK(String individualEIK) {
		this.individualEIK = individualEIK;
	}
	public String getIndividualNames() {
		return individualNames;
	}
	public void setIndividualNames(String individualNames) {
		this.individualNames = individualNames;
	}
	public XMLGregorianCalendar getStartDate() {
		return startDate;
	}
	public void setStartDate(XMLGregorianCalendar startDate) {
		this.startDate = startDate;
	}
	public XMLGregorianCalendar getLastAmendDate() {
		return lastAmendDate;
	}
	public void setLastAmendDate(XMLGregorianCalendar lastAmendDate) {
		this.lastAmendDate = lastAmendDate;
	}
	public XMLGregorianCalendar getEndDate() {
		return endDate;
	}
	public void setEndDate(XMLGregorianCalendar endDate) {
		this.endDate = endDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public XMLGregorianCalendar getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(XMLGregorianCalendar timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getEcoCode() {
		return ecoCode;
	}
	public void setEcoCode(String ecoCode) {
		this.ecoCode = ecoCode;
	}
	public String getProfessionCode() {
		return professionCode;
	}
	public void setProfessionCode(String professionCode) {
		this.professionCode = professionCode;
	}
	public BigDecimal getRemuneration() {
		return remuneration;
	}
	public void setRemuneration(BigDecimal remuneration) {
		this.remuneration = remuneration;
	}
	public String getProfessionName() {
		return professionName;
	}
	public void setProfessionName(String professionName) {
		this.professionName = professionName;
	}
	public String getEkatteCode() {
		return ekatteCode;
	}
	public void setEkatteCode(String ekatteCode) {
		this.ekatteCode = ekatteCode;
	}

}
