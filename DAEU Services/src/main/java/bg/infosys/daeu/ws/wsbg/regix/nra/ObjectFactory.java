package bg.infosys.daeu.ws.wsbg.regix.nra;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


@XmlRegistry
public class ObjectFactory {

    private static final QName _EContractReasonType_QNAME = new QName("http://egov.bg/RegiX/NRA/EmploymentContracts", "EContractReasonType");
    private static final QName _IDType_QNAME = new QName("http://egov.bg/RegiX/NRA/EmploymentContracts", "IDType");
    private static final QName _EikTypeType_QNAME = new QName("http://egov.bg/RegiX/NRA/EmploymentContracts", "EikTypeType");
    private static final QName _ContractsFilterType_QNAME = new QName("http://egov.bg/RegiX/NRA/EmploymentContracts", "ContractsFilterType");
    private static final QName _EContracts_QNAME = new QName("http://egov.bg/RegiX/NRA/EmploymentContracts", "EContracts");

    public ObjectFactory() {
    }

    public ResponseIdentityType createResponseIdentityType() {
        return new ResponseIdentityType();
    }

    public StatusType createStatusType() {
        return new StatusType();
    }

    public EContract createEContract() {
        return new EContract();
    }

    public EContracts createEContracts() {
        return new EContracts();
    }

    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts", name = "EContractReasonType")
    public JAXBElement<String> createEContractReasonType(String value) {
        return new JAXBElement<String>(_EContractReasonType_QNAME, String.class, null, value);
    }

    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts", name = "IDType")
    public JAXBElement<String> createIDType(String value) {
        return new JAXBElement<String>(_IDType_QNAME, String.class, null, value);
    }

    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts", name = "EikTypeType")
    public JAXBElement<EikTypeType> createEikTypeType(EikTypeType value) {
        return new JAXBElement<EikTypeType>(_EikTypeType_QNAME, EikTypeType.class, null, value);
    }

    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts", name = "ContractsFilterType")
    public JAXBElement<ContractsFilterType> createContractsFilterType(ContractsFilterType value) {
        return new JAXBElement<ContractsFilterType>(_ContractsFilterType_QNAME, ContractsFilterType.class, null, value);
    }
    
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts", name = "EContracts")
    public JAXBElement<EContracts> createEContracts(EContracts value) {
        return new JAXBElement<EContracts>(_EContracts_QNAME, EContracts.class, null, value);
    }
}
