package bg.infosys.daeu.db.dao.budgets;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.ListModelFilter;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.budgets.ApprovedBudget;
import bg.infosys.daeu.db.entity.pub.Subject;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;

@Repository
public class ApprovedBudgetDAO extends GenericDaoImpl<ApprovedBudget, Integer>{

	public List<ApprovedBudget> findApprovedBudgetsByYearPaged(Integer year, ListModelFilter filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ApprovedBudget> criteria = builder.createQuery(ApprovedBudget.class);
		Root<ApprovedBudget> root = criteria.from(ApprovedBudget.class);
		root.fetch(ApprovedBudget._approvedBudgetValues, JoinType.LEFT);
		root.fetch(ApprovedBudget._subject, JoinType.LEFT);
		Restrictions r = new Restrictions();
		if (year != null) {
			r.add(builder.equal(root.get(ApprovedBudget._year), year));
		}
		r.apply(builder, root, criteria, true);
		criteria.distinct(true);
		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList();
	}
	
	public long findApprovedBudgetsByYearCount(Integer year) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ApprovedBudget> root = criteria.from(ApprovedBudget.class);
		
		criteria.select(builder.count(root));
		
		Restrictions r = new Restrictions();
		if (year != null) {
			r.add(builder.equal(root.get(ApprovedBudget._year), year));
		}
		r.apply(builder, root, criteria, true);
		return createQuery(criteria).getSingleResult();
	}

	public ApprovedBudget findApprovedBudget(Integer year, SubjectVersion version) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ApprovedBudget> criteria = builder.createQuery(ApprovedBudget.class);
		Root<ApprovedBudget> root = criteria.from(ApprovedBudget.class);
		root.fetch(ApprovedBudget._approvedBudgetValues, JoinType.LEFT);
		root.fetch(ApprovedBudget._subject, JoinType.LEFT);
		Restrictions r = new Restrictions();
		if (year != null) {
			r.add(builder.equal(root.get(ApprovedBudget._year), year));
		}
		if (version != null) {
			r.add(builder.equal(root.get(ApprovedBudget._subject).get(Subject._lastVersion), version));
		}
		r.apply(builder, root, criteria, true);
		try {
		    return createQuery(criteria).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
		    return null;
		}
	}

}
