package bg.infosys.daeu.db.dto;

import java.util.Map;

public class RIRDataDTO {

	private String name;
	private String status;
	private String cpvCategory;
	private String cpvClass;
	private String cpvGroup;
	private String ebkCodes;
	private String purchaseDate;
	private String cpvSection;
	private String acquiringPrice;
	//    private String iisda_id;//not working
	//    private String legacy_organization_id; //not working
	private String month;
    private String year;
    private String reason;
    private String plannedResources;
    private String orgName;

	public RIRDataDTO(Map.Entry<String, ResponseObject> entry) {
		if (entry.getValue().getFields().getFriendlyname() == null) {
			this.name = entry.getValue().getFields().getName();
		} else {
			this.name = entry.getValue().getFields().getFriendlyname().isEmpty() ? entry.getValue().getFields().getFriendlyname() : entry.getValue().getFields().getName();
		}
		this.status = entry.getValue().getFields().getStatus();
		this.cpvCategory = entry.getValue().getFields().getCpv_category_id_friendlyname();
		this.cpvClass = entry.getValue().getFields().getCpv_class_id_friendlyname();
		this.cpvGroup = entry.getValue().getFields().getCpv_group_id_friendlyname();
		this.cpvSection = entry.getValue().getFields().getCpv_section_id_friendlyname();
		this.ebkCodes = entry.getValue().getFields().getEbk_codes_id_friendlyname();
		this.purchaseDate = entry.getValue().getFields().getPurchase_date();
		this.acquiringPrice = entry.getValue().getFields().getAcquiring_price();
		this.month = entry.getValue().getFields().getMonth();
		this.year = entry.getValue().getFields().getYear();
		this.reason = entry.getValue().getFields().getReason();
		this.plannedResources = entry.getValue().getFields().getPlanned_resources();
		this.orgName =  entry.getValue().getFields().getOrg_id_friendlyname();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCpvCategory() {
		return cpvCategory;
	}
	public void setCpvCategory(String cpvCategory) {
		this.cpvCategory = cpvCategory;
	}
	public String getCpvClass() {
		return cpvClass;
	}
	public void setCpvClass(String cpvClass) {
		this.cpvClass = cpvClass;
	}
	public String getCpvGroup() {
		return cpvGroup;
	}
	public void setCpvGroup(String cpvGroup) {
		this.cpvGroup = cpvGroup;
	}
	public String getEbkCodes() {
		return ebkCodes;
	}
	public void setEbkCodes(String ebkCodes) {
		this.ebkCodes = ebkCodes;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getCpvSection() {
		return cpvSection;
	}
	public void setCpvSection(String cpvSection) {
		this.cpvSection = cpvSection;
	}

	public String getAcquiringPrice() {
		return acquiringPrice;
	}

	public void setAcquiringPrice(String acquiringPrice) {
		this.acquiringPrice = acquiringPrice;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPlannedResources() {
		return plannedResources;
	}

	public void setPlannedResources(String plannedResources) {
		this.plannedResources = plannedResources;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
