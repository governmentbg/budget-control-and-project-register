package bg.infosys.daeu.ws.pub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import bg.infosys.daeu.ws.pub.entity.PublicBudgetFormDataDetail;

@Repository
public class PublicBudgetFormDataDetailDAO {
	public static String ID = "id";
	
	@PersistenceContext(unitName = "secondaryEntityManager")
	private EntityManager entityManager;
	
	public List<PublicBudgetFormDataDetail> findByPublicId(Integer publicId) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<PublicBudgetFormDataDetail> criteria = builder.createQuery(PublicBudgetFormDataDetail.class);
		Root<PublicBudgetFormDataDetail> root = criteria.from(PublicBudgetFormDataDetail.class);
		
		criteria.select(root);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(PublicBudgetFormDataDetail._publicBudgetFormData).get(ID), publicId));
		
		criteria.where(builder.or(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getResultList();
	}
	
	public PublicBudgetFormDataDetail saveOrUpdate(PublicBudgetFormDataDetail entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	public final CriteriaBuilder criteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}
	
	public final <U> TypedQuery<U> createQuery(CriteriaQuery<U> criteria) {
		return entityManager.createQuery(criteria);
	}
}
