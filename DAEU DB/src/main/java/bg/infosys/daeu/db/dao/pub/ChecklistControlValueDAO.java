package bg.infosys.daeu.db.dao.pub;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.Checklist;
import bg.infosys.daeu.db.entity.pub.ChecklistControlValue;

@Repository
public class ChecklistControlValueDAO extends GenericDaoImpl<ChecklistControlValue, Integer> {
	
	public ChecklistControlValue initCheckListValues(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ChecklistControlValue> criteria = builder.createQuery(ChecklistControlValue.class);
		Root<ChecklistControlValue> root = criteria.from(ChecklistControlValue.class);
		
		root.fetch(ChecklistControlValue._checklist, JoinType.LEFT);
		root.fetch(ChecklistControlValue._checklistControlConfig, JoinType.LEFT);
		criteria.where(builder.equal(root.get(Checklist._id), id));
		
		return createQuery(criteria).getSingleResult();
	}
}
