package bg.infosys.daeu.db.dao.registration;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.ListModelFilter;
import bg.infosys.daeu.db.entity.pub.ModuleType;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.registration.RegistrationUser;

@Repository
public class RegistrationUserDAO extends GenericDaoImpl<RegistrationUser, Integer> {

	public List<RegistrationUser> findAllRequestsByStatus(String statusCode, String moduleType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<RegistrationUser> criteria = builder.createQuery(RegistrationUser.class);
		Root<RegistrationUser> root = criteria.from(RegistrationUser.class);
		
		List<Predicate> predicates = new ArrayList<>();
		criteria.select(root);
		
		predicates.add(builder.equal(root.get(RegistrationUser._status).get(Status._name), statusCode));
		predicates.add(builder.equal(root.get(RegistrationUser._status).get(Status._moduleType).get(ModuleType._code), moduleType));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(GenericDaoImpl._id)));

		return createQuery(criteria).getResultList();
	}
	
	public List<RegistrationUser> findAllRequestsByStatusPaged(String statusCode, String moduleType,
			ListModelFilter filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<RegistrationUser> criteria = builder.createQuery(RegistrationUser.class);
		Root<RegistrationUser> root = criteria.from(RegistrationUser.class);
		
		List<Predicate> predicates = new ArrayList<>();
		criteria.select(root);
		
		predicates.add(builder.equal(root.get(RegistrationUser._status).get(Status._code), statusCode));
		predicates.add(builder.equal(root.get(RegistrationUser._status).get(Status._moduleType).get(ModuleType._code), moduleType));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.desc(root.get(GenericDaoImpl._id)));

		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList();
	}

	public List<RegistrationUser> findAllRequestsByEmail(String email) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<RegistrationUser> criteria = builder.createQuery(RegistrationUser.class);
		Root<RegistrationUser> root = criteria.from(RegistrationUser.class);
		
		List<Predicate> predicates = new ArrayList<>();
		criteria.select(root);
		
		predicates.add(builder.isNotNull(root.get(RegistrationUser._email)));
		predicates.add(builder.equal(root.get(RegistrationUser._email), email));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(GenericDaoImpl._id)));

		return createQuery(criteria).getResultList();
	}
	
	public List<RegistrationUser> findRequestsByEmailPaged(String email, ListModelFilter filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<RegistrationUser> criteria = builder.createQuery(RegistrationUser.class);
		Root<RegistrationUser> root = criteria.from(RegistrationUser.class);
		
		List<Predicate> predicates = new ArrayList<>();
		criteria.select(root);
		
		predicates.add(builder.isNotNull(root.get(RegistrationUser._email)));
		predicates.add(builder.equal(root.get(RegistrationUser._email), email));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(GenericDaoImpl._id)));

		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList();
	}

	public boolean checkIfUserExist(String username) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<RegistrationUser> criteria = builder.createQuery(RegistrationUser.class);
		Root<RegistrationUser> root = criteria.from(RegistrationUser.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.like(root.get(RegistrationUser._username), username));
		predicates.add(builder.equal(root.get(RegistrationUser._isActive), "Y"));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		if (!createQuery(criteria).getResultList().isEmpty()){
			return true;
		}
		return false;
	}
	
	public long countRequestsByEmail(String email) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<RegistrationUser> root = criteria.from(RegistrationUser.class);
		
		List<Predicate> predicates = new ArrayList<>();
		criteria.select(builder.count(root));
		
		predicates.add(builder.isNotNull(root.get(RegistrationUser._email)));
		predicates.add(builder.equal(root.get(RegistrationUser._email), email));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}

	public long countRequestsByStatusPaged(String statusCode, String moduleType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<RegistrationUser> root = criteria.from(RegistrationUser.class);
		
		List<Predicate> predicates = new ArrayList<>();
		criteria.select(builder.count(root));
		
		predicates.add(builder.equal(root.get(RegistrationUser._status).get(Status._code), statusCode));
		predicates.add(builder.equal(root.get(RegistrationUser._status).get(Status._moduleType).get(ModuleType._code), moduleType));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getSingleResult();
	}

}
