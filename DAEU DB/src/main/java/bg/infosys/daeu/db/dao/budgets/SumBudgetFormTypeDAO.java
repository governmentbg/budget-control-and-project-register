package bg.infosys.daeu.db.dao.budgets;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.budgets.SumBudgetFormType;

@Repository
public class SumBudgetFormTypeDAO extends GenericDaoImpl<SumBudgetFormType, String>{

	public SumBudgetFormType findSumBudgetFormTypeByCode(String budgetFormTypeCode) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<SumBudgetFormType> criteria = builder.createQuery(SumBudgetFormType.class);
		Root<SumBudgetFormType> root = criteria.from(SumBudgetFormType.class);
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(SumBudgetFormType._code), budgetFormTypeCode));
		r.apply(builder, root, criteria, true);
		return createQuery(criteria).getSingleResult();
	}

}
