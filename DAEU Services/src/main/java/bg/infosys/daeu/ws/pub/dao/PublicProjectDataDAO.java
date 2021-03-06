package bg.infosys.daeu.ws.pub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import bg.infosys.daeu.ws.pub.entity.PublicProjectData;

@Repository
public class PublicProjectDataDAO {
	public static String ID = "id";
	
	@PersistenceContext(unitName = "secondaryEntityManager")
	private EntityManager entityManager;
	
	public PublicProjectData findByPublicId(Integer id) throws NoResultException, NonUniqueResultException {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<PublicProjectData> criteria = builder.createQuery(PublicProjectData.class);
		Root<PublicProjectData> root = criteria.from(PublicProjectData.class);
		
		criteria.select(root);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ID), id));
		
		criteria.where(builder.or(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getSingleResult();
	}
	
	public PublicProjectData saveOrUpdate(PublicProjectData entity) {
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
