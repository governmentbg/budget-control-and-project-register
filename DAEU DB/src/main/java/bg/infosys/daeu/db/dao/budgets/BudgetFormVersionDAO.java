package bg.infosys.daeu.db.dao.budgets;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.budgets.BudgetFormVersion;

@Repository
public class BudgetFormVersionDAO extends GenericDaoImpl<BudgetFormVersion, Integer> {
	public void updateReturnedAtTimestamp(Integer id, String token) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<BudgetFormVersion> criteria = builder.createCriteriaUpdate(BudgetFormVersion.class);
		Root<BudgetFormVersion> root = criteria.from(BudgetFormVersion.class);
		
		criteria.set(root.get(BudgetFormVersion._returnedAtTimestamp), token);
		
		criteria.where(builder.equal(root.get(_id), id));
		
		getEntityManager().createQuery(criteria).executeUpdate();
	}
}
