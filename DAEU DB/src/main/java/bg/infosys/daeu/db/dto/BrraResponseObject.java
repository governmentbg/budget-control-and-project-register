package bg.infosys.daeu.db.dto;

import javax.xml.bind.annotation.XmlElement;


public class BrraResponseObject {
	@XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "UIC")
    protected String uic;
    @XmlElement(name = "Company")
    protected String company;
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUic() {
		return uic;
	}

	public void setUic(String uic) {
		this.uic = uic;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
