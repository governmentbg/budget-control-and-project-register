package bg.infosys.daeu.db.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bg.infosys.daeu.db.entity.pub.CPVCode;
import bg.infosys.daeu.db.entity.pub.EBKCode;
import bg.infosys.daeu.db.entity.pub.Subject;

public class ReportDTO {

	private String name;
	private Integer firstCurrent = 0;
	private Integer firstCapital = 0;
	private Integer firstTotal = 0;
	private Integer secondCurrent = 0;
	private Integer secondCapital = 0;
	private Integer secondTotal = 0;
	private Integer thirdCurrent = 0;
	private Integer thirdCapital = 0;
	private Integer thirdTotal = 0;
	private boolean isTotal;
	private CPVCode cpvCode;
	private EBKCode ebkCode;
	private Subject subject;
	private Integer year;

	// Projects
	private Integer id;
	private String type;
	private Date inOutDate;
	private String statusName;
	
	private Integer q1Current = 0;
	private Integer q1Capital = 0;
	private Integer q1Total = 0;
	private Integer q2Current = 0;
	private Integer q2Capital = 0;
	private Integer q2Total = 0;
	private Integer q3Current = 0;
	private Integer q3Capital = 0;
	private Integer q3Total = 0;
	private Integer q4Current = 0;
	private Integer q4Capital = 0;
	private Integer q4Total = 0;
	private Integer reportCurrent = 0;
	private Integer reportCapital = 0;
	private Integer reportTotal = 0;
	private String outgoingNum;
	//BudgetChangesReport
	private List<ReportDTO> childs = new ArrayList<ReportDTO>();
	private List<Subject> subjects = new ArrayList<Subject>();

	public ReportDTO(Integer id, String type, String statusName, String name, Date outDate) {
		this.id = id;
		this.type = type;
		this.statusName = statusName;
		this.name = name;
		this.inOutDate = outDate;
	}

	public ReportDTO() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFirstCurrent() {
		return firstCurrent;
	}

	public void setFirstCurrent(Integer firstCurrent) {
		this.firstCurrent = firstCurrent;
	}

	public Integer getFirstCapital() {
		return firstCapital;
	}

	public void setFirstCapital(Integer firstCapital) {
		this.firstCapital = firstCapital;
	}

	public Integer getFirstTotal() {
		return firstTotal;
	}

	public void setFirstTotal(Integer firstTotal) {
		this.firstTotal = firstTotal;
	}

	public Integer getSecondCurrent() {
		return secondCurrent;
	}

	public void setSecondCurrent(Integer secondCurrent) {
		this.secondCurrent = secondCurrent;
	}

	public Integer getSecondCapital() {
		return secondCapital;
	}

	public void setSecondCapital(Integer secondCapital) {
		this.secondCapital = secondCapital;
	}

	public Integer getSecondTotal() {
		return secondTotal;
	}

	public void setSecondTotal(Integer secondTotal) {
		this.secondTotal = secondTotal;
	}

	public Integer getThirdCurrent() {
		return thirdCurrent;
	}

	public void setThirdCurrent(Integer thirdCurrent) {
		this.thirdCurrent = thirdCurrent;
	}

	public Integer getThirdCapital() {
		return thirdCapital;
	}

	public void setThirdCapital(Integer thirdCapital) {
		this.thirdCapital = thirdCapital;
	}

	public Integer getThirdTotal() {
		return thirdTotal;
	}

	public void setThirdTotal(Integer thirdTotal) {
		this.thirdTotal = thirdTotal;
	}

	public boolean isTotal() {
		return isTotal;
	}

	public void setTotal(boolean isTotal) {
		this.isTotal = isTotal;
	}

	public CPVCode getCpvCode() {
		return cpvCode;
	}

	public void setCpvCode(CPVCode cpvCode) {
		this.cpvCode = cpvCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getInOutDate() {
		return inOutDate;
	}

	public void setInOutDate(Date inOutDate) {
		this.inOutDate = inOutDate;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getQ1Current() {
		return q1Current;
	}

	public void setQ1Current(Integer q1Current) {
		this.q1Current = q1Current;
	}

	public Integer getQ1Capital() {
		return q1Capital;
	}

	public void setQ1Capital(Integer q1Capital) {
		this.q1Capital = q1Capital;
	}

	public Integer getQ1Total() {
		return q1Total;
	}

	public void setQ1Total(Integer q1Total) {
		this.q1Total = q1Total;
	}

	public Integer getQ2Current() {
		return q2Current;
	}

	public void setQ2Current(Integer q2Current) {
		this.q2Current = q2Current;
	}

	public Integer getQ2Capital() {
		return q2Capital;
	}

	public void setQ2Capital(Integer q2Capital) {
		this.q2Capital = q2Capital;
	}

	public Integer getQ2Total() {
		return q2Total;
	}

	public void setQ2Total(Integer q2Total) {
		this.q2Total = q2Total;
	}

	public Integer getQ3Current() {
		return q3Current;
	}

	public void setQ3Current(Integer q3Current) {
		this.q3Current = q3Current;
	}

	public Integer getQ3Capital() {
		return q3Capital;
	}

	public void setQ3Capital(Integer q3Capital) {
		this.q3Capital = q3Capital;
	}

	public Integer getQ3Total() {
		return q3Total;
	}

	public void setQ3Total(Integer q3Total) {
		this.q3Total = q3Total;
	}

	public Integer getQ4Current() {
		return q4Current;
	}

	public void setQ4Current(Integer q4Current) {
		this.q4Current = q4Current;
	}

	public Integer getQ4Capital() {
		return q4Capital;
	}

	public void setQ4Capital(Integer q4Capital) {
		this.q4Capital = q4Capital;
	}

	public Integer getQ4Total() {
		return q4Total;
	}

	public void setQ4Total(Integer q4Total) {
		this.q4Total = q4Total;
	}

	public Integer getReportCurrent() {
		return reportCurrent;
	}

	public void setReportCurrent(Integer reportCurrent) {
		this.reportCurrent = reportCurrent;
	}

	public Integer getReportCapital() {
		return reportCapital;
	}

	public void setReportCapital(Integer reportCapital) {
		this.reportCapital = reportCapital;
	}

	public Integer getReportTotal() {
		return reportTotal;
	}

	public void setReportTotal(Integer reportTotal) {
		this.reportTotal = reportTotal;
	}

	public List<ReportDTO> getChilds() {
		return childs;
	}

	public void setChilds(List<ReportDTO> childs) {
		this.childs = childs;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public EBKCode getEbkCode() {
		return ebkCode;
	}

	public void setEbkCode(EBKCode ebkCode) {
		this.ebkCode = ebkCode;
	}

	public String getOutgoingNum() {
		return outgoingNum;
	}

	public void setOutgoingNum(String outgoingNum) {
		this.outgoingNum = outgoingNum;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
}
