package bg.infosys.daeu.db.dao.pub;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.ListModelFilter;
import bg.infosys.daeu.db.entity.pub.BudgetaryProcedureDate;

@Repository
public class BudgetaryProcedureDateDAO extends GenericDaoImpl<BudgetaryProcedureDate, Integer> {

	public List<BudgetaryProcedureDate> findAllByExamplePaged(ListModelFilter filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetaryProcedureDate> criteria = builder.createQuery(BudgetaryProcedureDate.class);
		Root<BudgetaryProcedureDate> root = criteria.from(BudgetaryProcedureDate.class);
		
		criteria.select(root);

		criteria.orderBy(builder.desc(root.get(BudgetaryProcedureDate._date)));

		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList();
	}

	public long countAllByExample() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<BudgetaryProcedureDate> root = criteria.from(BudgetaryProcedureDate.class);

		criteria.select(builder.count(root));
		
		return createQuery(criteria).getSingleResult();
	}

}
