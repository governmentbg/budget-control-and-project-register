package bg.infosys.daeu.db.dto;

public class RIRFields {
//	private String cpv_category_id;
    private String cpv_category_id_friendlyname;
//    private String cpv_class_id;
    private String cpv_class_id_friendlyname;
//    private String cpv_group_id;
    private String cpv_group_id_friendlyname;
//    private String cpv_section_id;
    private String ebk_codes_id_friendlyname;
    private String purchase_date;
    private String status;
    private String cpv_section_id_friendlyname;
//    private String code;//not working
    private String friendlyname;
//    private String iisda_id;//not working
//    private String legacy_organization_id; //not working
    private String name;
//    private String phone;//not working
//    private String key;//not working
    private String acquiring_price;
    private String month;
    private String year;
    private String reason;
    private String planned_resources;
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

    public String getCpv_category_id_friendlyname() {
        return cpv_category_id_friendlyname;
    }

    public void setCpv_category_id_friendlyname(String cpv_category_id_friendlyname) {
        this.cpv_category_id_friendlyname = cpv_category_id_friendlyname;
    }

    public String getCpv_class_id_friendlyname() {
        return cpv_class_id_friendlyname;
    }

    public void setCpv_class_id_friendlyname(String cpv_class_id_friendlyname) {
        this.cpv_class_id_friendlyname = cpv_class_id_friendlyname;
    }

    public String getCpv_group_id_friendlyname() {
        return cpv_group_id_friendlyname;
    }

    public void setCpv_group_id_friendlyname(String cpv_group_id_friendlyname) {
        this.cpv_group_id_friendlyname = cpv_group_id_friendlyname;
    }

    public String getCpv_section_id_friendlyname() {
        return cpv_section_id_friendlyname;
    }

    public void setCpv_section_id_friendlyname(String cpv_section_id_friendlyname) {
        this.cpv_section_id_friendlyname = cpv_section_id_friendlyname;
    }

    public String getFriendlyname() {
        return friendlyname;
    }

    public void setFriendlyname(String friendlyname) {
        this.friendlyname = friendlyname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

	public String getOrg_id_friendlyname() {
		return org_id_friendlyname;
	}

	public void setOrg_id_friendlyname(String org_id_friendlyname) {
		this.org_id_friendlyname = org_id_friendlyname;
	}
	
}
