package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectRelAssignee", namespace = "http://www.bulstat.bg/SubjectRelAssignee", propOrder = {
    "relatedSubjects",
    "type"
})
public class SubjectRelAssignee
    extends SubscriptionElement
{

    @XmlElement(name = "RelatedSubjects")
    protected List<Subject> relatedSubjects;
    @XmlElement(name = "Type")
    protected NomenclatureEntry type;

    public List<Subject> getRelatedSubjects() {
        if (relatedSubjects == null) {
            relatedSubjects = new ArrayList<Subject>();
        }
        return this.relatedSubjects;
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
