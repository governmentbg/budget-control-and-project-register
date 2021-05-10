package bg.infosys.daeu.ws.wsbg.regix.nra.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import bg.infosys.daeu.ws.wsbg.regix.nra.EikTypeType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentityTypeRequest", propOrder = {
    "id",
    "type"
})
public class IdentityTypeRequest {

    @XmlElement(name = "ID", required = true)
    protected String id;
    @XmlElement(name = "TYPE", required = true, namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts")
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
