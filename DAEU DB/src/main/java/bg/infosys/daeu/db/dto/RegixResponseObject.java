package bg.infosys.daeu.db.dto;

import org.apache.commons.lang3.ObjectUtils;

public class RegixResponseObject {

	private String uic;
	private String cyrillicName;
	private String manager;
	private String status;

	public RegixResponseObject(BulstatResponseObject bulstatResponseObject) {
		if(bulstatResponseObject.getSubject() != null) {
			if(ObjectUtils.allNotNull(bulstatResponseObject.getSubject().getUic())) {
				this.setUic(bulstatResponseObject.getSubject().getUic().getUic());
			}
			if(ObjectUtils.allNotNull(bulstatResponseObject.getSubject().getLegalEntitySubject(), bulstatResponseObject.getSubject().getLegalEntitySubject().getCyrillicFullName())) {
				this.setCyrillicName(bulstatResponseObject.getSubject().getLegalEntitySubject().getCyrillicFullName());
			}
		}
		if(!bulstatResponseObject.getManagers().isEmpty()) {
			this.setManager(bulstatResponseObject.getManagers().get(0).getRelatedSubject().getNaturalPersonSubject().getCyrillicName());
		}
	}

	public RegixResponseObject(BrraResponseObject brraResponseObject) {
		this.setUic(brraResponseObject.getUic());
		this.setCyrillicName(brraResponseObject.getCompany());
		this.setStatus(brraResponseObject.getStatus());
	}

	public String getUic() {
		return uic;
	}
	public void setUic(String uic) {
		this.uic = uic;
	}
	public String getCyrillicName() {
		return cyrillicName;
	}
	public void setCyrillicName(String cyrillicName) {
		this.cyrillicName = cyrillicName;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
