package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectPropState", namespace = "http://www.bulstat.bg/SubjectPropState", propOrder = {
    "state"
})
public class SubjectPropState
    extends Entry
{

    @XmlElement(name = "State")
    protected NomenclatureEntry state;

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link NomenclatureEntry }
     *     
     */
    public NomenclatureEntry getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link NomenclatureEntry }
     *     
     */
    public void setState(NomenclatureEntry value) {
        this.state = value;
    }

}
