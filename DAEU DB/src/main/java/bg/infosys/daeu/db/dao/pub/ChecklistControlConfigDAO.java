package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.budgets.FormConfig;
import bg.infosys.daeu.db.entity.pub.ChecklistControlConfig;
import bg.infosys.daeu.db.entity.pub.ChecklistType;
import bg.infosys.daeu.db.entity.security.Authority;

@Repository
public class ChecklistControlConfigDAO extends GenericDaoImpl<ChecklistControlConfig, Integer> {

	public List<ChecklistControlConfig> findChecklistControlConfigByType(ChecklistType checklistType, boolean dontGetHiddenColumns) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ChecklistControlConfig> criteria = builder.createQuery(ChecklistControlConfig.class);
		Root<ChecklistControlConfig> root = criteria.from(ChecklistControlConfig.class);
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(ChecklistControlConfig._checklistType), checklistType));
		r.add(builder.like(root.get(FormConfig._isValid), "Y"));
		if(dontGetHiddenColumns) {
			r.add(builder.equal(root.get(ChecklistControlConfig._hideControl), "N")); //do not get hidden control configs
		}
		criteria.orderBy(builder.asc(root.get(FormConfig._orderNum)));
		r.apply(builder, root, criteria, true);
		return createQuery(criteria).getResultList();
	}
	
	public String findFinalAuthorityNameForChecklist(ChecklistType checklistType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<String> query = builder.createQuery(String.class);
		Root<ChecklistControlConfig> root = query.from(ChecklistControlConfig.class);
		
		query.select(root.get(ChecklistControlConfig._authority).get(Authority._name));
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(builder.equal(root.get(ChecklistControlConfig._checklistType), checklistType));
		predicates.add(builder.equal(root.get(ChecklistControlConfig._isFinal), "Y"));
		
		query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(query).getResultList().stream().findFirst().orElse(null);
	}
	
}
