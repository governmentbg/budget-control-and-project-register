package bg.infosys.daeu.db.dao.pub;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.pub.ChecklistAuthority;
import bg.infosys.daeu.db.entity.pub.ChecklistConfig;
import bg.infosys.daeu.db.entity.pub.ChecklistType;

@Repository
public class ChecklistConfigDAO extends GenericDaoImpl<ChecklistConfig, Integer> {

	public List<ChecklistConfig> findChecklistElementsByChecklistType(ChecklistType checklistType, boolean dontGetHiddenColumns) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ChecklistConfig> criteria = builder.createQuery(ChecklistConfig.class);
		Root<ChecklistConfig> root = criteria.from(ChecklistConfig.class);
		
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(ChecklistConfig._checklistType), checklistType));
		r.add(builder.equal(root.get(ChecklistConfig._isValid), "Y"));
		if(dontGetHiddenColumns) {
			Join<ChecklistConfig, ChecklistAuthority> join = root.join(ChecklistConfig._checklistAuthority, JoinType.LEFT);
			root.join(ChecklistConfig._checklistAuthority, JoinType.LEFT);
			
			r.add(builder.or(builder.equal(join.get(ChecklistAuthority._hideColumn), "N"),
								builder.isNull(root.get(ChecklistConfig._checklistAuthority))));//do not get hidden columns 
		}
//		criteria.orderBy(builder.asc(root.get(FormConfig._orderNum)));
		r.apply(builder, root, criteria, true);
		
		return createQuery(criteria).getResultList();
	}

	public List<ChecklistConfig> findAllChecklistElementsByChecklistType(ChecklistType checklistType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ChecklistConfig> criteria = builder.createQuery(ChecklistConfig.class);
		Root<ChecklistConfig> root = criteria.from(ChecklistConfig.class);
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(ChecklistConfig._checklistType), checklistType));
		r.add(builder.equal(root.get(ChecklistConfig._isValid), "Y"));
		r.apply(builder, root, criteria, true);
		return createQuery(criteria).getResultList();
	}

}
