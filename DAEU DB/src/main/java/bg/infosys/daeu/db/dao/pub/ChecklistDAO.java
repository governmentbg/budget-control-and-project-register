package bg.infosys.daeu.db.dao.pub;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.Checklist;
import bg.infosys.daeu.db.entity.pub.ChecklistConfig;
import bg.infosys.daeu.db.entity.pub.ChecklistControlValue;
import bg.infosys.daeu.db.entity.pub.ChecklistElement;
import bg.infosys.daeu.db.entity.pub.ChecklistType;
import bg.infosys.daeu.db.entity.pub.ChecklistValue;

@Repository
public class ChecklistDAO extends GenericDaoImpl<Checklist, Integer> {

	public Checklist initCheckListValues(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Checklist> criteria = builder.createQuery(Checklist.class);
		Root<Checklist> root = criteria.from(Checklist.class);
		root.fetch(Checklist._checklistValues, JoinType.LEFT).fetch(ChecklistValue._config, JoinType.LEFT)
															 .fetch(ChecklistConfig._checklistElement, JoinType.LEFT).fetch(ChecklistElement._parent, JoinType.LEFT);
		root.fetch(Checklist._checklistControlValues, JoinType.LEFT).fetch(ChecklistControlValue._user, JoinType.LEFT);
		root.fetch(Checklist._checklistType, JoinType.LEFT).fetch(ChecklistType._formType, JoinType.LEFT);
		root.fetch(Checklist._checklistStatus, JoinType.LEFT);
		criteria.where(builder.equal(root.get(Checklist._id), id));
		return createQuery(criteria).getSingleResult();
	}

}
