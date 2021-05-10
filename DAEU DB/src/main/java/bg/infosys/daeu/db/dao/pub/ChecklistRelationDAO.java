package bg.infosys.daeu.db.dao.pub;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.Checklist;
import bg.infosys.daeu.db.entity.pub.ChecklistRelation;

@Repository
public class ChecklistRelationDAO extends GenericDaoImpl<ChecklistRelation, Integer> {

	public List<ChecklistRelation> findByParent(Integer parentId) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ChecklistRelation> criteria = builder.createQuery(ChecklistRelation.class);
		Root<ChecklistRelation> root = criteria.from(ChecklistRelation.class);
		Fetch<Object, Object> childChecklistFetch = root.fetch(ChecklistRelation._childChecklist, JoinType.LEFT);
		childChecklistFetch.fetch(Checklist._checklistType, JoinType.LEFT);
		childChecklistFetch.fetch(Checklist._checklistStatus, JoinType.LEFT);
		
		criteria.where(builder.equal(root.get(ChecklistRelation._parentChecklist).get(_id), parentId));
		
		return createQuery(criteria).getResultList();
	}
	
}
