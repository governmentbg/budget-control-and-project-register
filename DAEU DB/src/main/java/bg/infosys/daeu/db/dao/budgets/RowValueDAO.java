package bg.infosys.daeu.db.dao.budgets;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.budgets.Auxheader;
import bg.infosys.daeu.db.entity.budgets.AuxheaderType;
import bg.infosys.daeu.db.entity.budgets.BudgetForm;
import bg.infosys.daeu.db.entity.budgets.BudgetFormVersion;
import bg.infosys.daeu.db.entity.budgets.ExpenseDetail;
import bg.infosys.daeu.db.entity.budgets.FormColumnType;
import bg.infosys.daeu.db.entity.budgets.FormConfig;
import bg.infosys.daeu.db.entity.budgets.RowValue;
import bg.infosys.daeu.db.entity.budgets.VersionRow;
import bg.infosys.daeu.db.entity.pub.ExpenseType;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.pub.Status.StatusConst;
import bg.infosys.daeu.db.entity.pub.Subject;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;

@Repository
public class RowValueDAO extends GenericDaoImpl<RowValue, Integer> {

	public List<Integer> findByProperties(Subject subject, List<String> years) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Integer> criteria = builder.createQuery(Integer.class);
		Root<RowValue> root = criteria.from(RowValue.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		criteria.select(root.get(RowValue._value));
		predicates.add(builder.equal(root.get(RowValue._versionRow).get(VersionRow._isValid), "Y"));
		predicates.add(
				builder.equal(root.get(RowValue._versionRow).get(VersionRow._budgetFormVersion)
						.get(BudgetFormVersion._budgetForm).get(BudgetForm._subjectVersion).get(SubjectVersion._subject), subject));
		
		predicates.add(builder.equal(root.get(RowValue._formConfig).get(FormConfig._auxheader).get(Auxheader._auxheaderType).get(AuxheaderType._code), "FUND"));
		
		List<Predicate> yearPredicates = new ArrayList<Predicate>();
		for (String year : years) {
			yearPredicates.add(builder.equal(root.get(RowValue._formConfig).get(FormConfig._auxheader).get(Auxheader._year), year));
		}
		
		predicates.add(builder.or(
			builder.equal(root.get(RowValue._versionRow).get(VersionRow._budgetFormVersion).get(BudgetFormVersion._budgetForm).get(BudgetForm._finalStatus).get(Status._code), StatusConst.APPROVED.name()),
			builder.equal(root.get(RowValue._versionRow).get(VersionRow._budgetFormVersion).get(BudgetFormVersion._budgetForm).get(BudgetForm._finalStatus).get(Status._code), StatusConst.APPROVED_MARKS.name())));
		
		predicates.add(builder.equal(root.get(RowValue._formConfig).get(FormConfig._formColumnType).get(FormColumnType._code), "TOTAL"));
		predicates.add(builder.equal(root.get(RowValue._versionRow).get(VersionRow._expenseType).get(ExpenseType._code), "TOTAL"));
		
		criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])), builder.or(yearPredicates.toArray(new Predicate[yearPredicates.size()]))));
		
		return createQuery(criteria).setMaxResults(3).getResultList();
	}

	public RowValue initRowValue(RowValue selectedValue) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<RowValue> criteria = builder.createQuery(RowValue.class);
		Root<RowValue> root = criteria.from(RowValue.class);
		Restrictions r = new Restrictions();
		root.fetch(RowValue._expenseDetails, JoinType.LEFT).fetch(ExpenseDetail._subject, JoinType.LEFT);
		r.add(builder.equal(root.get(RowValue._id), selectedValue.getId()));
		r.apply(builder, root, criteria, true);
		criteria.distinct(true);
		return createQuery(criteria).getSingleResult();
	}
}
