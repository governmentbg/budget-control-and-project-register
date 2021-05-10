package bg.infosys.daeu.ws.pub.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "budget_year")
public class BudgetYear {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "reg_id")
	private Integer regId;
	public static final String _regId = "regId";
	
	@Column(name = _year)
	private String year;
	public static final String _year = "year";
	
	@Column(name = _units)
	private Integer units;
	public static final String _units = "units";
	
	@Column(name = _total)
	private Integer total;
	public static final String _total = "total";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "detail_id")
	private PublicBudgetFormDataDetail detail;
	public static final String _detail = "detail";
	
	public BudgetYear() {}

	public BudgetYear(Integer regId, String year, Integer units, Integer total, PublicBudgetFormDataDetail data) {
		this.regId = regId;
		this.year = year;
		this.units = units;
		this.total = total;
		this.detail = data;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public PublicBudgetFormDataDetail getDetail() {
		return detail;
	}

	public void setDetail(PublicBudgetFormDataDetail detail) {
		this.detail = detail;
	}
}
