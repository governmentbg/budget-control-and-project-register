package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectPropCollectiveBody", namespace = "http://www.bulstat.bg/SubjectPropCollectiveBody", propOrder = {
    "type",
    "members"
})
public class SubjectPropCollectiveBody
    extends Entry
{

    @XmlElement(name = "Type")
    protected NomenclatureEntry type;
    @XmlElement(name = "Members")
    protected List<SubjectRelCollectiveBodyMember> members;

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

    /**
     * Gets the value of the members property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the members property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMembers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubjectRelCollectiveBodyMember }
     * 
     * 
     */
    public List<SubjectRelCollectiveBodyMember> getMembers() {
        if (members == null) {
            members = new ArrayList<SubjectRelCollectiveBodyMember>();
        }
        return this.members;
    }

}
