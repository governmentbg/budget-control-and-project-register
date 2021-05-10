package bg.infosys.daeu.db.entity.pub;

import java.util.List;

public class ChecklistNDBO {
	private ChecklistElement checklistElement;
	private List<ChecklistValue> values;
	private List<ChecklistAuthority> authorities;
	
	public ChecklistNDBO(ChecklistElement checklistElement, List<ChecklistValue> values) {
		this.checklistElement = checklistElement;
		this.values = values;
	}
	
	public ChecklistNDBO() {
	}

	public ChecklistElement getChecklistElement() {
		return checklistElement;
	}
	
	public void setChecklistElement(ChecklistElement checklistElement) {
		this.checklistElement = checklistElement;
	}
	
	public List<ChecklistValue> getValues() {
		return values;
	}
	
	public void setValues(List<ChecklistValue> values) {
		this.values = values;
	}

	public List<ChecklistAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<ChecklistAuthority> authorities) {
		this.authorities = authorities;
	}
}
