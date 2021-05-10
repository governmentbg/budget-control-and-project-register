package bg.infosys.daeu.ws.wsbg.iisda.models;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfString", namespace = "http://iisda.government.bg/RAS/IntegrationServices", propOrder = {
        "string"
})
public class ArrayOfString {

    @XmlElement(nillable = true)
    protected List<String> string;

    public List<String> getString() {
        if (string == null) {
            string = new ArrayList<String>();
        }
        return this.string;
    }
}