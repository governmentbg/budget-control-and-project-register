package bg.infosys.daeu.db.dto;

import java.math.BigDecimal;
import java.util.Date;

import bg.infosys.daeu.db.entity.pub.Status;

public class ProjectsIntegrationDto {
	private BigDecimal value;
	
	private String type;
	private Date outDate;
	private String outNum;
	private Status status;
	private String period;
	
	public ProjectsIntegrationDto(BigDecimal value, String type, Date outDate, String outNum, Status status, String period) {
		this.value = value;
		this.type = type;
		this.outDate = outDate;
		this.outNum = outNum;
		this.status = status;
		this.period = period;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public String getOutNum() {
		return outNum;
	}

	public void setOutNum(String outNum) {
		this.outNum = outNum;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
}
