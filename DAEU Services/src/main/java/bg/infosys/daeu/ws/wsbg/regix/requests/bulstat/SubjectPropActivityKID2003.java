package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectPropActivityKID2003", namespace = "http://www.bulstat.bg/SubjectPropActivityKID2003", propOrder = {
    "nkid2003"
})
public class SubjectPropActivityKID2003
    extends Entry
{

    @XmlElement(name = "NKID2003")
    protected NomenclatureEntry nkid2003;

    /**
     * Gets the value of the nkid2003 property.
     * 
     * @return
     *     possible object is
     *     {@link NomenclatureEntry }
     *     
     */
    public NomenclatureEntry getNKID2003() {
        return nkid2003;
    }

    /**
     * Sets the value of the nkid2003 property.
     * 
     * @param value
     *     allowed object is
     *     {@link NomenclatureEntry }
     *     
     */
    public void setNKID2003(NomenclatureEntry value) {
        this.nkid2003 = value;
    }

}
