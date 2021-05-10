package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectRelManager", namespace = "http://www.bulstat.bg/SubjectRelManager", propOrder = {
    "relatedSubject",
    "position",
    "representedSubjects"
})
public class SubjectRelManager
    extends SubscriptionElement
{

    @XmlElement(name = "RelatedSubject")
    protected Subject relatedSubject;
    @XmlElement(name = "Position")
    protected NomenclatureEntry position;
    @XmlElement(name = "RepresentedSubjects")
    protected List<Subject> representedSubjects;

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
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link NomenclatureEntry }
     *     
     */
    public NomenclatureEntry getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link NomenclatureEntry }
     *     
     */
    public void setPosition(NomenclatureEntry value) {
        this.position = value;
    }

    /**
     * Gets the value of the representedSubjects property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the representedSubjects property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRepresentedSubjects().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Subject }
     * 
     * 
     */
    public List<Subject> getRepresentedSubjects() {
        if (representedSubjects == null) {
            representedSubjects = new ArrayList<Subject>();
        }
        return this.representedSubjects;
    }

}
