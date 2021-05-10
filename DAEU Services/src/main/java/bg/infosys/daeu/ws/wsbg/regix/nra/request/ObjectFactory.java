package bg.infosys.daeu.ws.wsbg.regix.nra.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


@XmlRegistry
public class ObjectFactory {

    private final static QName _EmploymentContractsRequest_QNAME = new QName("http://egov.bg/RegiX/NRA/EmploymentContracts/Request", "EmploymentContractsRequest");

    public ObjectFactory() {
    }

    public EmploymentContractsRequest createEmploymentContractsRequest() {
        return new EmploymentContractsRequest();
    }

    public IdentityTypeRequest createIdentityTypeRequest() {
        return new IdentityTypeRequest();
    }

    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts/Request", name = "EmploymentContractsRequest")
    public JAXBElement<EmploymentContractsRequest> createEmploymentContractsRequest(EmploymentContractsRequest value) {
        return new JAXBElement<EmploymentContractsRequest>(_EmploymentContractsRequest_QNAME, EmploymentContractsRequest.class, null, value);
    }

}
