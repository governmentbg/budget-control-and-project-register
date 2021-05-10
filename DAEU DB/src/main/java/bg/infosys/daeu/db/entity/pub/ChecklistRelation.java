package bg.infosys.daeu.db.entity.pub;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "checklist_relations", schema="public")
public class ChecklistRelation implements Comparable<ChecklistRelation> {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	@JoinColumn(name = "parent_id")
	private Checklist parentChecklist;
	public static final String _parentChecklist = "parentChecklist";
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "child_id")
	private Checklist childChecklist;
	public static final String _childChecklist = "childChecklist";
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Checklist getParentChecklist() {
		return parentChecklist;
	}
	
	public void setParentChecklist(Checklist parentChecklist) {
		this.parentChecklist = parentChecklist;
	}
	
	public Checklist getChildChecklist() {
		return childChecklist;
	}
	
	public void setChildChecklist(Checklist chilChecklist) {
		this.childChecklist = chilChecklist;
	}

	@Override
	public int compareTo(ChecklistRelation o) {
		if (o == null || o.getChildChecklist() == null || o.getChildChecklist().getChecklistType() == null) {
			return 1;
		}
		
		return this.childChecklist.getChecklistType().getOrderNum() - o.getChildChecklist().getChecklistType().getOrderNum();
	}
}
