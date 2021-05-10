package bg.infosys.daeu.db.dao.budgets;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.budgets.FormColumnType;
import bg.infosys.daeu.db.entity.budgets.FormConfig;
import bg.infosys.daeu.db.entity.projects.RowType;
import bg.infosys.daeu.db.entity.pub.FormType;

@Repository
public class FormConfigDAO extends GenericDaoImpl<FormConfig, Integer> {

	public List<FormConfig> findColumnsByConfig(FormType formType, String section, Integer year) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<FormConfig> criteria = builder.createQuery(FormConfig.class);
		Root<FormConfig> root = criteria.from(FormConfig.class);
		Restrictions r = new Restrictions();
		root.fetch(FormConfig._formType,JoinType.LEFT);
		Fetch<Object, Object> formColumnTypeFetch = root.fetch(FormConfig._formColumnType);
		formColumnTypeFetch.fetch(FormColumnType._numenclatures, JoinType.LEFT);
		formColumnTypeFetch.fetch(FormColumnType._expenseType, JoinType.LEFT);
		if (formType != null) {
			r.add(builder.equal(root.get(FormConfig._formType), formType));
		}
		if (section != null) {
			r.add(builder.equal(root.get(FormConfig._section), section));
		}
		r.add(builder.like(root.get(FormConfig._isValid), "Y"));
		if (year != null) {
			r.add(builder.equal(root.get(FormConfig._year), year));
		}
		criteria.orderBy(builder.asc(root.get(FormConfig._orderNum)));
		criteria.distinct(true);
		r.apply(builder, root, criteria, true);
		return createQuery(criteria).getResultList();
	}
	
	public List<Integer> getValidYearsByFormType(String type) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Integer> criteria = builder.createQuery(Integer.class);
		Root<FormConfig> root = criteria.from(FormConfig.class);
		criteria.select(root.get(FormConfig._year));
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(FormConfig._formType).get(FormType._code), type));
		r.add(builder.equal(root.get(FormConfig._isValid), "Y"));
		r.apply(builder, root, criteria, true);
		criteria.distinct(true);
		return createQuery(criteria).getResultList();
	}
	
	public List<FormConfig> findAllInvalid(String formTypeCode, Integer year) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<FormConfig> criteria = builder.createQuery(FormConfig.class);
		Root<FormConfig> root = criteria.from(FormConfig.class);
		root.fetch(FormConfig._formType, JoinType.LEFT);
		Fetch<Object,Object> rowTypeFetch = root.fetch(FormConfig._formColumnType, JoinType.LEFT);
		rowTypeFetch.fetch(RowType._componentType, JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(FormConfig._isValid), "N"));
		predicates.add(builder.equal(root.get(FormConfig._formType).get(FormType._code), formTypeCode));
		predicates.add(builder.equal(root.get(FormConfig._year), year));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(FormConfig._orderNum)));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<FormConfig> findParentAndChildConfigs(FormType formType, FormType childFormType, Integer year) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<FormConfig> criteria = builder.createQuery(FormConfig.class);
		Root<FormConfig> root = criteria.from(FormConfig.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		List<Predicate> formTypePredicates = new ArrayList<Predicate>();
		root.fetch(FormConfig._formType,JoinType.LEFT);
		root.fetch(FormConfig._formColumnType).fetch(FormColumnType._numenclatures, JoinType.LEFT);
		if (formType != null) {
			formTypePredicates.add(builder.equal(root.get(FormConfig._formType), formType));
		}
		if (childFormType != null) {
			formTypePredicates.add(builder.equal(root.get(FormConfig._formType), childFormType));
		}
		predicates.add(builder.like(root.get(FormConfig._isValid), "Y"));
		if (year != null) {
			predicates.add(builder.equal(root.get(FormConfig._year), year));
		}
		criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.or(formTypePredicates.toArray(new Predicate[formTypePredicates.size()]))));
		criteria.orderBy(builder.asc(root.get(FormConfig._orderNum)));
		criteria.distinct(true);
		return createQuery(criteria).getResultList();
	}

}
