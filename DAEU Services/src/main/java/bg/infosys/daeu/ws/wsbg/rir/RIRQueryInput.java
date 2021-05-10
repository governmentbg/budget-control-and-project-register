package bg.infosys.daeu.ws.wsbg.rir;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RIRQueryInput {

    @JsonProperty("bulstat")
    private String bulstat;

    @JsonProperty("iisdaNumber")
    private String iisdaNumber;

    @JsonProperty("className")
    private RIRClassENUM className;

    @JsonProperty("filterFromYear")
    private String year;
    
    @JsonProperty("firstYear")
    private String firstYear;
    
    @JsonProperty("secondYear")
    private String secondYear;
    
    @JsonProperty("thirdYear")
    private String thirdYear;

    public RIRQueryInput() {
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
    
}
