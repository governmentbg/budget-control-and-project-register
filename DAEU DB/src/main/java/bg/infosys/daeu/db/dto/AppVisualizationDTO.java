package bg.infosys.daeu.db.dto;

import java.util.Date;

import bg.infosys.daeu.db.entity.budgets.BudgetFormVersion;
import bg.infosys.daeu.db.entity.projects.ProjectVersion;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsVersion;

public class AppVisualizationDTO {
	private Integer id;
	
	private Status status;
	private String type;
	private String name;
	private String outNum;
	private Date date;
	
	private String inOutNum;
	private Date inOutDate;
	private boolean forSend;
	
	public AppVisualizationDTO() {}
	
	public AppVisualizationDTO(Integer id, Status status, String type, String name, Date dateCreated) {
		init(id, status, type, name, dateCreated, null);
	}
	
	public AppVisualizationDTO(Integer id, Status status, String type, String name, Date outDate, String outNum) {
		init(id, status, type, name, outDate, outNum);
	}
	
	public AppVisualizationDTO(Object obj) {
		if(obj instanceof BudgetFormVersion) {
			init(((BudgetFormVersion) obj).getId(), ((BudgetFormVersion) obj).getBudgetForm().getStatus(), ((BudgetFormVersion) obj).getBudgetForm().getFormType().getName(),
					((BudgetFormVersion) obj).getBudgetForm().getSubjectVersion().getFullName(),
						((BudgetFormVersion) obj).getSubjectOutgoingDate(), ((BudgetFormVersion) obj).getSubjectOutgoingNum());
		} else if (obj instanceof ProjectVersion) {
			init(((ProjectVersion) obj).getId(), ((ProjectVersion) obj).getProject().getStatus(), ((ProjectVersion) obj).getProject().getFormType().getName(),
					((ProjectVersion) obj).getProject().getSubjectVersion() != null? ((ProjectVersion) obj).getProject().getSubjectVersion().getFullName() : "",
						((ProjectVersion) obj).getSubjectOutgoingDate(), ((ProjectVersion) obj).getSubjectOutgoingNum());
		} else {
			init(((TechSpecsVersion) obj).getId(), ((TechSpecsVersion) obj).getTechSpecs().getStatus(), ((TechSpecsVersion) obj).getTechSpecs().getFormType().getName(),
					((TechSpecsVersion) obj).getTechSpecs().getSubjectVersion().getFullName(),
						((TechSpecsVersion) obj).getSubjectOutgoingDate(), ((TechSpecsVersion) obj).getSubjectOutgoingNum());
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInOutNum() {
		return inOutNum;
	}

	public void setInOutNum(String inOutNum) {
		this.inOutNum = inOutNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getInOutDate() {
		return inOutDate;
	}

	public void setInOutDate(Date inOutDate) {
		this.inOutDate = inOutDate;
	}

	public String getOutNum() {
		return outNum;
	}

	public void setOutNum(String outNum) {
		this.outNum = outNum;
	}
	
	public boolean isForSend() {
		return forSend;
	}

	public void setForSend(boolean forSend) {
		this.forSend = forSend;
	}

	private void init(Integer id, Status status, String type, String name, Date dateCreated, String outNum) {
		this.id = id;
		this.status = status;
		this.type = type;
		this.name = name;
		this.date = dateCreated;
		this.outNum = outNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppVisualizationDTO other = (AppVisualizationDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
