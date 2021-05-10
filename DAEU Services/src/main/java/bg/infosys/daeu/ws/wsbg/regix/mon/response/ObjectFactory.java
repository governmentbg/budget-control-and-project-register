package bg.infosys.daeu.ws.wsbg.regix.mon.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


@XmlRegistry
public class ObjectFactory {

    private final static QName _DiplomaDocumentsResponse_QNAME = new QName("http://egov.bg/RegiX/MOMN/RDSO/DiplomaDocumentsResponse", "DiplomaDocumentsResponse");

    public ObjectFactory() {
    }

    public DiplomaDocumentsType createDiplomaDocumentsType() {
        return new DiplomaDocumentsType();
    }

    @XmlElementDecl(namespace = "http://egov.bg/RegiX/MOMN/RDSO/DiplomaDocumentsResponse", name = "DiplomaDocumentsResponse")
    public JAXBElement<DiplomaDocumentsType> createDiplomaDocumentsResponse(DiplomaDocumentsType value) {
        return new JAXBElement<DiplomaDocumentsType>(_DiplomaDocumentsResponse_QNAME, DiplomaDocumentsType.class, null, value);
    }

}
