package bg.infosys.daeu.db.dao.budgets;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.budgets.RowValue;
import bg.infosys.daeu.db.entity.budgets.RowValueChangeType;

@Repository
public class RowValueChangeTypeDAO extends GenericDaoImpl<RowValueChangeType, Integer> {

	public List<RowValueChangeType> findRowValueChangeTypesByRowValue(RowValue value) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<RowValueChangeType> criteria = builder.createQuery(RowValueChangeType.class);
		Root<RowValueChangeType> root = criteria.from(RowValueChangeType.class);
		Restrictions r = new Restrictions();
		root.fetch(RowValueChangeType._changeType,JoinType.LEFT);
		r.add(builder.equal(root.get(RowValueChangeType._rowValue), value));
		r.apply(builder, root, criteria, true);
		
		return createQuery(criteria).getResultList();
	}

}
