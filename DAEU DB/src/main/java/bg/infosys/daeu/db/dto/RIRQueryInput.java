package bg.infosys.daeu.db.dto;

import bg.infosys.daeu.db.entity.pub.Subject;

public class RIRQueryInput {
	
	private String bulstat;

	private String iisdaNumber;

	private RIRClassENUM className;

	private String filterFromYear;
	
	private String firstYear;
	
	private String secondYear;
	
	private String thirdYear;

	public RIRQueryInput() {
	}

	/* TEST DATA : 000695114 1900   121797867 2019*/
	public RIRQueryInput(Subject subject, Integer startYear) {
		this.setClassName(RIRClassENUM.ASSET);
		this.setBulstat(subject.getIdentNum());
		this.setIisdaNumber(subject.getIisdaNumber());
		this.setFilterFromYear(String.valueOf(startYear - 5));
//		this.setBulstat("000695114");
//		this.setFilterFromYear("1900");
	}
	
	public RIRQueryInput(Subject subject, Integer startYear, RIRClassENUM rirClassEnum) {
		this.setClassName(rirClassEnum);
		this.setBulstat(subject.getIdentNum());
		this.setIisdaNumber(subject.getIisdaNumber());
		this.setFirstYear(String.valueOf(startYear));
		this.setSecondYear(String.valueOf(startYear + 1));
		this.setThirdYear(String.valueOf(startYear + 2));
	}

	public void setIisdaNumber(String iisdaNumber) {
		this.iisdaNumber = iisdaNumber;
	}

	public void setBulstat(String bulstat) {
		this.bulstat = bulstat;
	}

	public String getBulstat() {
		return bulstat;
	}

	public String getIisdaNumber() {
		return iisdaNumber;
	}

	public RIRClassENUM getClassName() {
		return className;
	}

	public void setClassName(RIRClassENUM className) {
		this.className = className;
	}

	public String getFilterFromYear() {
		return filterFromYear;
	}

	public void setFilterFromYear(String filterFromYear) {
		this.filterFromYear = filterFromYear;
	}
	
	public String getFirstYear() {
		return firstYear;
	}

	public void setFirstYear(String firstYear) {
		this.firstYear = firstYear;
	}

	public String getSecondYear() {
		return secondYear;
	}

	public void setSecondYear(String secondYear) {
		this.secondYear = secondYear;
	}

	public String getThirdYear() {
		return thirdYear;
	}

	public void setThirdYear(String thirdYear) {
		this.thirdYear = thirdYear;
	}


	public enum RIRClassENUM {

		ORGANIZATION("Organization"),
		YEARLY_PLAN("Yearly_plan"),
		ASSET("Asset");

		private final String value;

		RIRClassENUM(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

}
