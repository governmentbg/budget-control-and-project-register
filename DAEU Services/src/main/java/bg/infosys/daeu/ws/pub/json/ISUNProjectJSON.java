package bg.infosys.daeu.ws.pub.json;

import java.math.BigDecimal;
import java.util.List;

public class ISUNProjectJSON {
	private String guid;
	private Integer majorVersion;
	private Integer minorVersion;
	private String linkForProjectPreview;
	private String programme;
	private String name;
	private Integer duration;
	private Boolean providesDevelopmentOrUpgrade;
	private String purpose;
	private String candidateUin;
	private String candidateUinType;
	private String candidateName;
	private List<ISUNProjectActivityJSON> activities;
	private BigDecimal totalProjectCost;
	private List<ISUNIndicatorJSON> indicators;
	private List<ISUNeGovIndicatorJSON> eGovernmentIndicators;
	private ISUNIndicativeBudgetJSON indicativeBudget;
	private List<ISUNRegisteredDatabasesInfoJSON> informationSystemsRegistersDatabases;
	
	public String getGuid() {
		return guid;
	}
	
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	public Integer getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(Integer majorVersion) {
		this.majorVersion = majorVersion;
	}

	public Integer getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	public String getLinkForProjectPreview() {
		return linkForProjectPreview;
	}
	
	public void setLinkForProjectPreview(String linkForProjectPreview) {
		this.linkForProjectPreview = linkForProjectPreview;
	}
	
	public String getProgramme() {
		return programme;
	}
	
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getDuration() {
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public Boolean getProvidesDevelopmentOrUpgrade() {
		return providesDevelopmentOrUpgrade;
	}
	
	public void setProvidesDevelopmentOrUpgrade(Boolean providesDevelopmentOrUpgrade) {
		this.providesDevelopmentOrUpgrade = providesDevelopmentOrUpgrade;
	}
	
	public String getPurpose() {
		return purpose;
	}
	
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public String getCandidateUin() {
		return candidateUin;
	}
	
	public void setCandidateUin(String candidateUin) {
		this.candidateUin = candidateUin;
	}
	
	public String getCandidateUinType() {
		return candidateUinType;
	}
	
	public void setCandidateUinType(String candidateUinType) {
		this.candidateUinType = candidateUinType;
	}
	
	public String getCandidateName() {
		return candidateName;
	}
	
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	public List<ISUNProjectActivityJSON> getActivities() {
		return activities;
	}
	
	public void setActivities(List<ISUNProjectActivityJSON> activities) {
		this.activities = activities;
	}
	
	public BigDecimal getTotalProjectCost() {
		return totalProjectCost;
	}
	
	public void setTotalProjectCost(BigDecimal totalProjectCost) {
		this.totalProjectCost = totalProjectCost;
	}
	
	public List<ISUNIndicatorJSON> getIndicators() {
		return indicators;
	}
	
	public void setIndicators(List<ISUNIndicatorJSON> indicators) {
		this.indicators = indicators;
	}
	
	public List<ISUNeGovIndicatorJSON> geteGovernmentIndicators() {
		return eGovernmentIndicators;
	}
	
	public void seteGovernmentIndicators(List<ISUNeGovIndicatorJSON> eGovernmentIndicators) {
		this.eGovernmentIndicators = eGovernmentIndicators;
	}
	
	public ISUNIndicativeBudgetJSON getIndicativeBudget() {
		return indicativeBudget;
	}
	
	public void setIndicativeBudget(ISUNIndicativeBudgetJSON indicativeBudget) {
		this.indicativeBudget = indicativeBudget;
	}
	
	public List<ISUNRegisteredDatabasesInfoJSON> getInformationSystemsRegistersDatabases() {
		return informationSystemsRegistersDatabases;
	}
	
	public void setInformationSystemsRegistersDatabases(
			List<ISUNRegisteredDatabasesInfoJSON> informationSystemsRegistersDatabases) {
		this.informationSystemsRegistersDatabases = informationSystemsRegistersDatabases;
	}
}
