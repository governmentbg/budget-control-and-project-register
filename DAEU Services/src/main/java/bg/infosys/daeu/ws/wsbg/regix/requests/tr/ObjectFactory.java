package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import bg.infosys.daeu.ws.wsbg.regix.requests.tr.actualstate.ActualStateRequestType;
import bg.infosys.daeu.ws.wsbg.regix.requests.tr.validuic.ValidUICRequestType;
import bg.infosys.daeu.ws.wsbg.regix.responses.tr.actualstate.ActualStateResponseType;
import bg.infosys.daeu.ws.wsbg.regix.responses.tr.validuic.ValidUICResponseType;

@XmlRegistry
public class ObjectFactory {

    private final static QName _ValidUICRequest_QNAME = new QName("http://egov.bg/RegiX/AV/TR/ValidUICRequest", "ValidUICRequest");
    private final static QName _ValidUICResponse_QNAME = new QName("http://egov.bg/RegiX/AV/TR/ValidUICResponse", "ValidUICResponse");
    private final static QName _ActualStateResponse_QNAME = new QName("http://egov.bg/RegiX/AV/TR/ActualStateResponse", "ActualStateResponse");
    private final static QName _ActualStateRequest_QNAME = new QName("http://egov.bg/RegiX/AV/TR/ActualStateRequest", "ActualStateRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bg.government.regixclient.requests.av
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DetailType }
     * 
     */
    public DetailType createDetailType() {
        return new DetailType();
    }

    /**
     * Create an instance of {@link MandateType }
     * 
     */
    public MandateType createMandateType() {
        return new MandateType();
    }

    /**
     * Create an instance of {@link AddemptionOfTraderType }
     * 
     */
    public AddemptionOfTraderType createAddemptionOfTraderType() {
        return new AddemptionOfTraderType();
    }

    /**
     * Create an instance of {@link SubjectOfActivityType }
     * 
     */
    public SubjectOfActivityType createSubjectOfActivityType() {
        return new SubjectOfActivityType();
    }

    /**
     * Create an instance of {@link Persons }
     * 
     */
//    public Persons createPersons() {
//        return new Persons();
//    }

    /**
     * Create an instance of {@link ContactsType }
     * 
     */
    public ContactsType createContactsType() {
        return new ContactsType();
    }

    /**
     * Create an instance of {@link LegalFormType }
     * 
     */
    public LegalFormType createLegalFormType() {
        return new LegalFormType();
    }

    /**
     * Create an instance of {@link CapitalAmountType }
     * 
     */
    public CapitalAmountType createCapitalAmountType() {
        return new CapitalAmountType();
    }

    /**
     * Create an instance of {@link NonMonetaryDepositsType }
     * 
     */
    public NonMonetaryDepositsType createNonMonetaryDepositsType() {
        return new NonMonetaryDepositsType();
    }

    /**
     * Create an instance of {@link SeatType }
     * 
     */
    public SeatType createSeatType() {
        return new SeatType();
    }

    /**
     * Create an instance of {@link PersonInformationType }
     * 
     */
//    public PersonInformationType createPersonInformationType() {
//        return new PersonInformationType();
//    }

    /**
     * Create an instance of {@link SubjectType }
     * 
     */
    public SubjectType createSubjectType() {
        return new SubjectType();
    }

    /**
     * Create an instance of {@link SharesType }
     * 
     */
    public SharesType createSharesType() {
        return new SharesType();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link ShareType }
     * 
     */
    public ShareType createShareType() {
        return new ShareType();
    }

    /**
     * Create an instance of {@link FieldsType }
     * 
     */
//    public FieldsType createFieldsType() {
//        return new FieldsType();
//    }

    /**
     * Create an instance of {@link NKIDType }
     * 
     */
    public NKIDType createNKIDType() {
        return new NKIDType();
    }

    /**
     * Create an instance of {@link ActualStateRequestType }
     * 
     */
    public ActualStateRequestType createActualStateRequestType() {
        return new ActualStateRequestType();
    }

    /**
     * Create an instance of {@link ActualStateResponseType }
     * 
     */
    public ActualStateResponseType createActualStateResponseType() {
        return new ActualStateResponseType();
    }

    /**
     * Create an instance of {@link ValidUICRequestType }
     * 
     */
    public ValidUICRequestType createValidUICRequestType() {
        return new ValidUICRequestType();
    }

    /**
     * Create an instance of {@link ValidUICResponseType }
     * 
     */
    public ValidUICResponseType createValidUICResponseType() {
        return new ValidUICResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidUICRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/AV/TR/ValidUICRequest", name = "ValidUICRequest")
    public JAXBElement<ValidUICRequestType> createValidUICRequest(ValidUICRequestType value) {
        return new JAXBElement<ValidUICRequestType>(_ValidUICRequest_QNAME, ValidUICRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualStateResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/AV/TR/ActualStateResponse", name = "ActualStateResponse")
    public JAXBElement<ActualStateResponseType> createActualStateResponse(ActualStateResponseType value) {
        return new JAXBElement<ActualStateResponseType>(_ActualStateResponse_QNAME, ActualStateResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualStateRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/AV/TR/ActualStateRequest", name = "ActualStateRequest")
    public JAXBElement<ActualStateRequestType> createActualStateRequest(ActualStateRequestType value) {
        return new JAXBElement<ActualStateRequestType>(_ActualStateRequest_QNAME, ActualStateRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidUICResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/AV/TR/ValidUICResponse", name = "ValidUICResponse")
    public JAXBElement<ValidUICResponseType> createValidUICResponse(ValidUICResponseType value) {
        return new JAXBElement<ValidUICResponseType>(_ValidUICResponse_QNAME, ValidUICResponseType.class, null, value);
    }
}