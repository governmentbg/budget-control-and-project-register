package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectPropActivityKID2008", namespace = "http://www.bulstat.bg/SubjectPropActivityKID2008", propOrder = {
    "kid2008"
})
public class SubjectPropActivityKID2008
    extends Entry
{

    @XmlElement(name = "KID2008")
    protected NomenclatureEntry kid2008;

    /**
     * Gets the value of the kid2008 property.
     * 
     * @return
     *     possible object is
     *     {@link NomenclatureEntry }
     *     
     */
    public NomenclatureEntry getKID2008() {
        return kid2008;
    }

    /**
     * Sets the value of the kid2008 property.
     * 
     * @param value
     *     allowed object is
     *     {@link NomenclatureEntry }
     *     
     */
    public void setKID2008(NomenclatureEntry value) {
        this.kid2008 = value;
    }

}
