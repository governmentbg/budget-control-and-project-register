package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.CPVCode;

@Repository
public class CPVCodeDAO extends GenericDaoImpl<CPVCode, Integer> {

	public List<CPVCode> findAllChildCPVCodes() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<CPVCode> criteria = builder.createQuery(CPVCode.class);
		Root<CPVCode> root = criteria.from(CPVCode.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(CPVCode._isValid), "Y"));
		predicates.add(builder.isNotNull(root.get(CPVCode._parent)));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(CPVCode._orderNum)));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<CPVCode> findAllMostUsedChildCPVCodes() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<CPVCode> criteria = builder.createQuery(CPVCode.class);
		Root<CPVCode> root = criteria.from(CPVCode.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(CPVCode._isValid), "Y"));
		predicates.add(builder.equal(root.get(CPVCode._commonUsed), "Y"));
		predicates.add(builder.isNotNull(root.get(CPVCode._parent)));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(CPVCode._orderNum)));
		
		return createQuery(criteria).getResultList();
	}

	public List<CPVCode> findCPVsByCodeOrName(String content) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<CPVCode> criteria = builder.createQuery(CPVCode.class);
		Root<CPVCode> root = criteria.from(CPVCode.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(CPVCode._isValid), "Y"));
		predicates.add(builder.or(builder.like(root.get(CPVCode._code), "%" + content + "%"), builder.like(root.get(CPVCode._name), "%" + content + "%")));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(CPVCode._orderNum)));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<CPVCode> findAllParentCPVCodes() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<CPVCode> criteria = builder.createQuery(CPVCode.class);
		Root<CPVCode> root = criteria.from(CPVCode.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(CPVCode._isValid), "Y"));
		predicates.add(builder.isNull(root.get(CPVCode._parent)));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(CPVCode._orderNum)));
		
		return createQuery(criteria).getResultList();
	}
}
