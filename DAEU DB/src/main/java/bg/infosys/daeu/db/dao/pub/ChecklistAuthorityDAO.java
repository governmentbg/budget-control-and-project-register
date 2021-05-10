package bg.infosys.daeu.db.dao.pub;

import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.pub.ChecklistAuthority;
import bg.infosys.daeu.db.entity.pub.ChecklistType;

@Repository
public class ChecklistAuthorityDAO  extends GenericDaoImpl<ChecklistAuthority, Integer>{

	public Collection<ChecklistAuthority> findAllValidChecklistAuthoritiesByType(ChecklistType checklistType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ChecklistAuthority> criteria = builder.createQuery(ChecklistAuthority.class);
		Root<ChecklistAuthority> root = criteria.from(ChecklistAuthority.class);
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(ChecklistAuthority._isValid), "Y"));
		r.add(builder.equal(root.get(ChecklistAuthority._checklistType), checklistType));
		r.apply(builder, root, criteria, true);
		
		return createQuery(criteria).getResultList();
	}

}
