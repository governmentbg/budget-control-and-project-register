package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectRelBelonging", namespace = "http://www.bulstat.bg/SubjectRelBelonging", propOrder = {
    "relatedSubject",
    "type"
})
public class SubjectRelBelonging
    extends SubscriptionElement
{

    @XmlElement(name = "RelatedSubject")
    protected Subject relatedSubject;
    @XmlElement(name = "Type")
    protected NomenclatureEntry type;

    /**
     * Gets the value of the relatedSubject property.
     * 
     * @return
     *     possible object is
     *     {@link Subject }
     *     
     */
    public Subject getRelatedSubject() {
        return relatedSubject;
    }

    /**
     * Sets the value of the relatedSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subject }
     *     
     */
    public void setRelatedSubject(Subject value) {
        this.relatedSubject = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link NomenclatureEntry }
     *     
     */
    public NomenclatureEntry getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link NomenclatureEntry }
     *     
     */
    public void setType(NomenclatureEntry value) {
        this.type = value;
    }

}
