package bg.infosys.daeu.ws.wsbg.regix.nra.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import bg.infosys.daeu.ws.wsbg.regix.nra.ContractsFilterType;
import bg.infosys.daeu.ws.wsbg.regix.nra.EContracts;
import bg.infosys.daeu.ws.wsbg.regix.nra.ResponseIdentityType;
import bg.infosys.daeu.ws.wsbg.regix.nra.StatusType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmploymentContractsResponse", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts/Response", propOrder = {
    "identity",
    "eContracts",
    "status",
    "contractsFilter",
    "reportDate"
})
@XmlRootElement(name = "EmploymentContractsResponse")
public class EmploymentContractsResponse {

    @XmlElement(name = "Identity")
    protected ResponseIdentityType identity;
    @XmlElement(name = "EContracts")
    protected EContracts eContracts;
    @XmlElement(name = "Status")
    protected StatusType status;
    @XmlElement(name = "ContractsFilter")
    @XmlSchemaType(name = "string")
    protected ContractsFilterType contractsFilter;
    @XmlElement(name = "ReportDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar reportDate;
	
    public ResponseIdentityType getIdentity() {
		return identity;
	}
	public void setIdentity(ResponseIdentityType identity) {
		this.identity = identity;
	}
	public EContracts getEContracts() {
		return eContracts;
	}
	public void setEContracts(EContracts eContracts) {
		this.eContracts = eContracts;
	}
	public StatusType getStatus() {
		return status;
	}
	public void setStatus(StatusType status) {
		this.status = status;
	}
	public ContractsFilterType getContractsFilter() {
		return contractsFilter;
	}
	public void setContractsFilter(ContractsFilterType contractsFilter) {
		this.contractsFilter = contractsFilter;
	}
	public XMLGregorianCalendar getReportDate() {
		return reportDate;
	}
	public void setReportDate(XMLGregorianCalendar reportDate) {
		this.reportDate = reportDate;
	}

}
