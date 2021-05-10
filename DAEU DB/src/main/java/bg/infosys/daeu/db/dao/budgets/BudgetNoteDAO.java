package bg.infosys.daeu.db.dao.budgets;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.ListModelFilter;
import bg.infosys.daeu.db.entity.budgets.BudgetForm;
import bg.infosys.daeu.db.entity.budgets.BudgetFormVersion;
import bg.infosys.daeu.db.entity.budgets.BudgetNote;
import bg.infosys.daeu.db.entity.projects.ProjectNote;

@Repository
public class BudgetNoteDAO extends GenericDaoImpl<BudgetNote, Integer> {

	public List<BudgetNote> findNotesForBudget(BudgetForm budgetForm, ListModelFilter filter, String isCommon) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetNote> criteria = builder.createQuery(BudgetNote.class);
		Root<BudgetNote> root = criteria.from(BudgetNote.class);
		
		root.fetch(BudgetNote._user, JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(BudgetNote._budgetForm), budgetForm));
		predicates.add(builder.equal(root.get(BudgetNote._isValid), "Y"));
		if (isCommon != null) {
			predicates.add(builder.equal(root.get(ProjectNote._isCommon), isCommon));
		}
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.desc(root.get(BudgetNote._date)));
		
		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList();
	}

	public long findNotesForBudgetCount(BudgetForm budgetForm, String isCommon) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<BudgetNote> root = criteria.from(BudgetNote.class);
	
		criteria.select(builder.count(root));
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(BudgetNote._budgetForm), budgetForm));
		predicates.add(builder.equal(root.get(BudgetNote._isValid), "Y"));
		if (isCommon != null) {
			predicates.add(builder.equal(root.get(ProjectNote._isCommon), isCommon));
		}
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public void makeAllVersionNotesCommon(BudgetFormVersion version) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<BudgetNote> criteria = builder.createCriteriaUpdate(BudgetNote.class);
		Root<BudgetNote> root = criteria.from(BudgetNote.class);
		
		criteria.set(root.get(BudgetNote._isCommon), "Y");
		
		criteria.where(builder.equal(root.get(BudgetNote._budgetFormVersion), version));
		
		getEntityManager().createQuery(criteria).executeUpdate();
	}

}
