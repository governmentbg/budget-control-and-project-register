package bg.infosys.daeu.db.dao.budgets;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.budgets.BudgetFormVersion;
import bg.infosys.daeu.db.entity.budgets.ExpenseDetail;
import bg.infosys.daeu.db.entity.budgets.RowValue;
import bg.infosys.daeu.db.entity.budgets.RowValueChangeType;
import bg.infosys.daeu.db.entity.budgets.VersionRow;

@Repository
public class VersionRowDAO extends GenericDaoImpl<VersionRow, Integer> {

//	public List<VersionRow> findVersionRowsMinimumByBudgetFormVersion(BudgetFormVersion budgetFormVersion) {
//		CriteriaBuilder builder = criteriaBuilder();
//		CriteriaQuery<VersionRow> criteria = builder.createQuery(VersionRow.class);
//		Root<VersionRow> root = criteria.from(VersionRow.class);
//		Restrictions r = new Restrictions();
//		Fetch<Object, Object> values = root.fetch(VersionRow._values, JoinType.LEFT);
//		root.fetch(VersionRow._expenseType, JoinType.LEFT);
//		values.fetch(RowValue._selectedCpvCode, JoinType.LEFT);
//		r.add(builder.equal(root.get(VersionRow._budgetFormVersion), budgetFormVersion));
//		r.add(builder.equal(root.get(VersionRow._isValid), "Y"));
//		r.apply(builder, root, criteria, true);
//		criteria.distinct(true);
//		return createQuery(criteria).getResultList();
//	}
	
	public List<VersionRow> findVersionRowsByBudgetFormVersion(BudgetFormVersion budgetFormVersion) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<VersionRow> criteria = builder.createQuery(VersionRow.class);
		Root<VersionRow> root = criteria.from(VersionRow.class);
		Restrictions r = new Restrictions();
		Fetch<Object, Object> values = root.fetch(VersionRow._values, JoinType.LEFT);
		root.fetch(VersionRow._expenseType, JoinType.LEFT);
		values.fetch(RowValue._selectedCpvCode, JoinType.LEFT);
		values.fetch(RowValue._expenseDetails, JoinType.LEFT).fetch(ExpenseDetail._subject, JoinType.LEFT);
		values.fetch(RowValue._rowValueChangeTypes, JoinType.LEFT).fetch(RowValueChangeType._changeType, JoinType.LEFT);
//		values.fetch(RowValue._formConfig, JoinType.LEFT).fetch(FormConfig._formColumnType, JoinType.LEFT).fetch(FormColumnType._numenclatures, JoinType.LEFT);
		r.add(builder.equal(root.get(VersionRow._budgetFormVersion), budgetFormVersion));
		r.add(builder.equal(root.get(VersionRow._isValid), "Y"));
		r.apply(builder, root, criteria, true);
		criteria.distinct(true);
		return createQuery(criteria).getResultList();
	}

}
