package bg.infosys.daeu.ws.wsbg.regix.mon;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public CertifiedDocumentType createCertifiedDocumentType() {
        return new CertifiedDocumentType();
    }

    public DiplomaDocumentType createDiplomaDocumentType() {
        return new DiplomaDocumentType();
    }

    public CertifiedDocumentType.DocumentImages createCertifiedDocumentTypeDocumentImages() {
        return new CertifiedDocumentType.DocumentImages();
    }

    public DiplomaDocumentType.DocumentImages createDiplomaDocumentTypeDocumentImages() {
        return new DiplomaDocumentType.DocumentImages();
    }

}
