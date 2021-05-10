package bg.infosys.daeu.ws.wsbg.regix.requests.tr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NKIDType", propOrder = {
    "nkiDcode",
    "nkiDname"
})
public class NKIDType {

    @XmlElement(name = "NKIDcode")
    protected String nkiDcode;
    @XmlElement(name = "NKIDname")
    protected String nkiDname;

    /**
     * Gets the value of the nkiDcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNKIDcode() {
        return nkiDcode;
    }

    /**
     * Sets the value of the nkiDcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNKIDcode(String value) {
        this.nkiDcode = value;
    }

    /**
     * Gets the value of the nkiDname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNKIDname() {
        return nkiDname;
    }

    /**
     * Sets the value of the nkiDname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNKIDname(String value) {
        this.nkiDname = value;
    }

}