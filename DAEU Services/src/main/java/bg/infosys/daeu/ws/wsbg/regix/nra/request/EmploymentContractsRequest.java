package bg.infosys.daeu.ws.wsbg.regix.nra.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import bg.infosys.daeu.ws.wsbg.regix.nra.ContractsFilterType;

@XmlRootElement(name= "EmploymentContractsRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmploymentContractsRequest", namespace= "http://egov.bg/RegiX/NRA/EmploymentContracts/Request", propOrder = {
    "identity",
    "contractsFilter"
})
public class EmploymentContractsRequest {

    @XmlElement(name = "Identity", required = true, namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts/Request")
    protected IdentityTypeRequest identity;
    @XmlElement(name = "ContractsFilter")
    @XmlSchemaType(name = "string")
    protected ContractsFilterType contractsFilter;
	
    public IdentityTypeRequest getIdentity() {
		return identity;
	}
	public void setIdentity(IdentityTypeRequest identity) {
		this.identity = identity;
	}
	public ContractsFilterType getContractsFilter() {
		return contractsFilter;
	}
	public void setContractsFilter(ContractsFilterType contractsFilter) {
		this.contractsFilter = contractsFilter;
	}
    
}
