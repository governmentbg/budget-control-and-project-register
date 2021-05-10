package bg.infosys.daeu.db.dao.budgets;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.budgets.BudgetNomenclatures;
import bg.infosys.daeu.db.entity.budgets.FormColumnType;
import bg.infosys.daeu.db.entity.budgets.FormConfig;

@Repository
public class BudgetNomenclaturesDAO extends GenericDaoImpl<BudgetNomenclatures, Integer>{

	public List<BudgetNomenclatures> findAllBudgetNomenclaturesByConfig(FormConfig config) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetNomenclatures> criteria = builder.createQuery(BudgetNomenclatures.class);
		Root<BudgetNomenclatures> root = criteria.from(BudgetNomenclatures.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.join(BudgetNomenclatures._formColumnType).get(FormColumnType._id), config.getFormColumnType().getId()));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}

}
