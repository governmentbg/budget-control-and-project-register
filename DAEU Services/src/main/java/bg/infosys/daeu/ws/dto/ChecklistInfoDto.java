package bg.infosys.daeu.ws.dto;

import java.util.List;

import bg.infosys.daeu.db.entity.pub.Checklist;
import bg.infosys.daeu.db.entity.pub.ChecklistRelation;

public class ChecklistInfoDto {
	private Checklist parentChecklist;
	private List<ChecklistRelation> relations;
	
	public Checklist getParentChecklist() {
		return parentChecklist;
	}
	
	public void setParentChecklist(Checklist parentChecklist) {
		this.parentChecklist = parentChecklist;
	}
	
	public List<ChecklistRelation> getRelations() {
		return relations;
	}
	
	public void setRelations(List<ChecklistRelation> relations) {
		this.relations = relations;
	}
}
