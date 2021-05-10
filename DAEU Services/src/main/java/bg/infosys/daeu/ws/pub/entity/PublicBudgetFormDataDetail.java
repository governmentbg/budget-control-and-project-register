package bg.infosys.daeu.ws.pub.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "public_budget_form_data_details")
public class PublicBudgetFormDataDetail {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "reg_id")
	private Integer regId;
	public static final String _regId = "regId";
	
	@Column(name = "cpv_code")
	private String cpvCode;
	public static final String _cpvCode = "cpvCode";
	
	@Column(name = "cpv_name")
	private String cpvName;
	public static final String _cpvName = "cpvName";
	
	@Column(name = "ebk_code")
	private String ebkCode;
	public static final String _ebkCode = "ebkCode";
	
	@Column(name = _change)
	private Integer change;
	public static final String _change = "change";
	
	@OneToMany(mappedBy = "detail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<BudgetYear> years;
	public static final String _years = "years";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "budget_id")
	private PublicBudgetFormData publicBudgetFormData;
	public static final String _publicBudgetFormData = "publicBudgetFormData";
	
	public PublicBudgetFormDataDetail() {}

	public PublicBudgetFormDataDetail(Integer regId, String cpvCode, String cpvName, String ebkCode, Integer change, PublicBudgetFormData data) {
		years = new ArrayList<BudgetYear>();
		this.regId = regId;
		this.cpvCode = cpvCode;
		this.cpvName = cpvName;
		this.ebkCode = ebkCode;
		this.change = change;
		this.publicBudgetFormData = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRegId() {
		return regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public String getCpvCode() {
		return cpvCode;
	}

	public void setCpvCode(String cpvCode) {
		this.cpvCode = cpvCode;
	}

	public String getCpvName() {
		return cpvName;
	}

	public void setCpvName(String cpvName) {
		this.cpvName = cpvName;
	}

	public String getEbkCode() {
		return ebkCode;
	}

	public void setEbkCode(String ebkCode) {
		this.ebkCode = ebkCode;
	}

	public Integer getChange() {
		return change;
	}

	public void setChange(Integer change) {
		this.change = change;
	}

	public List<BudgetYear> getYears() {
		return years;
	}

	public void setYears(List<BudgetYear> years) {
		this.years = years;
	}

	public PublicBudgetFormData getPublicBudgetFormData() {
		return publicBudgetFormData;
	}

	public void setPublicBudgetFormData(PublicBudgetFormData publicBudgetFormData) {
		this.publicBudgetFormData = publicBudgetFormData;
	}
}
