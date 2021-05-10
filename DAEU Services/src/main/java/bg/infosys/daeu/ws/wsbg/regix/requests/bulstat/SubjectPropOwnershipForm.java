package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectPropOwnershipForm", namespace = "http://www.bulstat.bg/SubjectPropOwnershipForm", propOrder = {
    "form",
    "percent"
})
public class SubjectPropOwnershipForm
    extends Entry
{

    @XmlElement(name = "Form")
    protected NomenclatureEntry form;
    @XmlElement(name = "Percent")
    protected BigDecimal percent;

    /**
     * Gets the value of the form property.
     * 
     * @return
     *     possible object is
     *     {@link NomenclatureEntry }
     *     
     */
    public NomenclatureEntry getForm() {
        return form;
    }

    /**
     * Sets the value of the form property.
     * 
     * @param value
     *     allowed object is
     *     {@link NomenclatureEntry }
     *     
     */
    public void setForm(NomenclatureEntry value) {
        this.form = value;
    }

    /**
     * Gets the value of the percent property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPercent() {
        return percent;
    }

    /**
     * Sets the value of the percent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPercent(BigDecimal value) {
        this.percent = value;
    }

}
