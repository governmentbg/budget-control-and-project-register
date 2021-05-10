package bg.infosys.daeu.ws.wsbg.rir.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * в този клас се слагат всичките възможни полета за Fields, сбор полета за всичките класове, тьй като е невъзможно Jackson да създаде обекти спрямо полетата им.
 */
class Fields {
    private String cpv_category_id;
    private String cpv_category_id_friendlyname;
    private String cpv_class_id;
    private String cpv_class_id_friendlyname;
    private String cpv_group_id;
    private String cpv_group_id_friendlyname;
    private String cpv_section_id;
    private String ebk_codes_id_friendlyname;
    private String purchase_date;
    private String status;
    private String cpv_section_id_friendlyname;
    private String code;
    private String friendlyname;
    private String iisda_id;
    private String legacy_organization_id;
    private String name;
    private String phone;
    private String key;
    private String acquiring_price;
    private String month;
    private String year;
    private String reason;
    private String planned_resources;
    private String org_id;
    private String org_id_friendlyname;
    
    public String getEbk_codes_id_friendlyname() {
        return ebk_codes_id_friendlyname;
    }

    public void setEbk_codes_id_friendlyname(String ebk_codes_id_friendlyname) {
        this.ebk_codes_id_friendlyname = ebk_codes_id_friendlyname;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getCpv_category_id() {
        return cpv_category_id;
    }

    public void setCpv_category_id(String cpv_category_id) {
        this.cpv_category_id = cpv_category_id;
    }

    public String getCpv_category_id_friendlyname() {
        return cpv_category_id_friendlyname;
    }

    public void setCpv_category_id_friendlyname(String cpv_category_id_friendlyname) {
        this.cpv_category_id_friendlyname = cpv_category_id_friendlyname;
    }

    public String getCpv_class_id() {
        return cpv_class_id;
    }

    public void setCpv_class_id(String cpv_class_id) {
        this.cpv_class_id = cpv_class_id;
    }

    public String getCpv_class_id_friendlyname() {
        return cpv_class_id_friendlyname;
    }

    public void setCpv_class_id_friendlyname(String cpv_class_id_friendlyname) {
        this.cpv_class_id_friendlyname = cpv_class_id_friendlyname;
    }

    public String getCpv_group_id() {
        return cpv_group_id;
    }

    public void setCpv_group_id(String cpv_group_id) {
        this.cpv_group_id = cpv_group_id;
    }

    public String getCpv_group_id_friendlyname() {
        return cpv_group_id_friendlyname;
    }

    public void setCpv_group_id_friendlyname(String cpv_group_id_friendlyname) {
        this.cpv_group_id_friendlyname = cpv_group_id_friendlyname;
    }

    public String getCpv_section_id() {
        return cpv_section_id;
    }

    public void setCpv_section_id(String cpv_section_id) {
        this.cpv_section_id = cpv_section_id;
    }

    public String getCpv_section_id_friendlyname() {
        return cpv_section_id_friendlyname;
    }

    public void setCpv_section_id_friendlyname(String cpv_section_id_friendlyname) {
        this.cpv_section_id_friendlyname = cpv_section_id_friendlyname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFriendlyname() {
        return friendlyname;
    }

    public void setFriendlyname(String friendlyname) {
        this.friendlyname = friendlyname;
    }

    public String getIisda_id() {
        return iisda_id;
    }

    public void setIisda_id(String iisda_id) {
        this.iisda_id = iisda_id;
    }

    public String getLegacy_organization_id() {
        return legacy_organization_id;
    }

    public void setLegacy_organization_id(String legacy_organization_id) {
        this.legacy_organization_id = legacy_organization_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

	public String getAcquiring_price() {
		return acquiring_price;
	}

	public void setAcquiring_price(String acquiring_price) {
		this.acquiring_price = acquiring_price;
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

	public String getPlanned_resources() {
		return planned_resources;
	}

	public void setPlanned_resources(String planned_resources) {
		this.planned_resources = planned_resources;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getOrg_id_friendlyname() {
		return org_id_friendlyname;
	}

	public void setOrg_id_friendlyname(String org_id_friendlyname) {
		this.org_id_friendlyname = org_id_friendlyname;
	}
	
}
