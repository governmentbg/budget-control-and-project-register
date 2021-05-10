package bg.infosys.daeu.ws.wsbg.regix.nra;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseIdentityType", propOrder = {
    "id",
    "type"
})
public class ResponseIdentityType {

    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "TYPE")
    @XmlSchemaType(name = "string")
    protected EikTypeType type;
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public EikTypeType getType() {
		return type;
	}
	public void setType(EikTypeType type) {
		this.type = type;
	}
    
}
