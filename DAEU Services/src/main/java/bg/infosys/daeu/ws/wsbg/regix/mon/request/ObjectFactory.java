package bg.infosys.daeu.ws.wsbg.regix.mon.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


@XmlRegistry
public class ObjectFactory {

    private final static QName _DiplomaDocumentsRequest_QNAME = new QName("http://egov.bg/RegiX/MOMN/RDSO/DiplomaDocumentsRequest", "DiplomaDocumentsRequest");

    public ObjectFactory() {
    }

    public DiplomaSearchType createDiplomaSearchType() {
        return new DiplomaSearchType();
    }

    @XmlElementDecl(namespace = "http://egov.bg/RegiX/MOMN/RDSO/DiplomaDocumentsRequest", name = "DiplomaDocumentsRequest")
    public JAXBElement<DiplomaSearchType> createDiplomaDocumentsRequest(DiplomaSearchType value) {
        return new JAXBElement<DiplomaSearchType>(_DiplomaDocumentsRequest_QNAME, DiplomaSearchType.class, null, value);
    }

}
