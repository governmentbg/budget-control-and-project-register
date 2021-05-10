package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.db.entity.pub.FormType.FormTypeConst;
import bg.infosys.daeu.db.entity.pub.ModuleType;

@Repository
public class FormTypeDAO extends GenericDaoImpl<FormType, Short> {

	public FormType getBudgetControlFormType(String code) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<FormType> criteria = builder.createQuery(FormType.class);
		Root<FormType> root = criteria.from(FormType.class);
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(FormType._code), code));
		r.add(builder.equal(root.get(FormType._isValid), "Y"));
		r.apply(builder, root, criteria, true);
		
		try {
			return createQuery(criteria).getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

	public List<FormType> getBudgetSearchFormTypes() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<FormType> criteria = builder.createQuery(FormType.class);
		Root<FormType> root = criteria.from(FormType.class);
		Restrictions r = new Restrictions();
		r.add(builder.or(builder.and(builder.equal(root.get(FormType._isValid), "Y"),builder.equal(root.get(FormType._code), FormTypeConst.APP_ONE.getCode())), 
				builder.and(builder.equal(root.get(FormType._isValid), "Y"),builder.equal(root.get(FormType._code), FormTypeConst.APP_TWO.getCode()))));
		r.apply(builder, root, criteria, true);
		
		return createQuery(criteria).getResultList();
	}
	
	public List<FormType> getFormTypesByModule(String code) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<FormType> criteria = builder.createQuery(FormType.class);
		Root<FormType> root = criteria.from(FormType.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(FormType._moduleType).get(ModuleType._code), code));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<FormType> findAllValidFormTypes(ModuleType module) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<FormType> criteria = builder.createQuery(FormType.class);
		Root<FormType> root = criteria.from(FormType.class);
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(FormType._isValid), "Y"));
		r.add(builder.equal(root.get(FormType._moduleType), module));
		r.apply(builder, root, criteria, true);
		
		return createQuery(criteria).getResultList();
	}
	
}
