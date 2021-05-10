package bg.infosys.daeu.ws.wsbg.regix.requests.bulstat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectPropLifeTime", propOrder = {
    "date",
    "description"
})
public class SubjectPropLifeTime
    extends Entry
{

    @XmlElement(name = "Date")
    protected Object date;
    @XmlElement(name = "Description")
    protected String description;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDate(Object value) {
        this.date = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
