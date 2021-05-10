package bg.infosys.daeu.db.dao.budgets;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.budgets.BudgetFormType;
import bg.infosys.daeu.db.entity.pub.FormType;

@Repository
public class BudgetFormTypeDAO extends GenericDaoImpl<BudgetFormType, String>{

	public List<BudgetFormType> getBudgetFormTypesByFormType(FormType formType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetFormType> criteria = builder.createQuery(BudgetFormType.class);
		Root<BudgetFormType> root = criteria.from(BudgetFormType.class);
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(BudgetFormType._formType), formType));
		r.apply(builder, root, criteria, true);
		
		return createQuery(criteria).getResultList();
	}

}
