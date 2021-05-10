package bg.infosys.daeu.db.dao.budgets;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.budgets.ChangeType;

@Repository
public class ChangeTypeDAO extends GenericDaoImpl<ChangeType, Integer> {

	public List<ChangeType> findAllValidChangeTypes() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ChangeType> criteria = builder.createQuery(ChangeType.class);
		Root<ChangeType> root = criteria.from(ChangeType.class);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ChangeType._isValid), "Y"));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
}
