package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectPropAccountingRecordForm", namespace = "http://www.bulstat.bg/SubjectPropAccountingRecordForm", propOrder = {
    "form"
})
public class SubjectPropAccountingRecordForm
    extends Entry
{

    @XmlElement(name = "Form")
    protected NomenclatureEntry form;

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

}
