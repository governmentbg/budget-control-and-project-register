package bg.infosys.daeu.ws.pub.json;

import java.util.List;

public class ISUNDecisionJSON {
	private String guid;
	private Integer majorVersion;
	private Integer minorVersion;
	private DAEUStatus status;
	private List<ISUNCommentJSON> comments;
	
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
	
	public DAEUStatus getStatus() {
		return status;
	}

	public void setStatus(DAEUStatus status) {
		this.status = status;
	}

	public List<ISUNCommentJSON> getComments() {
		return comments;
	}
	
	public void setComments(List<ISUNCommentJSON> comments) {
		this.comments = comments;
	}
	
	public enum DAEUStatus {
		FOR_EDITING, APPROVED, REFUSAL_TO_APPROVE
	}
}
