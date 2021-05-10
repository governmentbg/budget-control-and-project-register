package bg.infosys.daeu.db.dao.tech_specs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.dto.AppVisualizationDTO;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.security.Authority;
import bg.infosys.daeu.db.entity.security.User;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecs;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsVersion;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsVersionUser;

@Repository
public class TechSpecsVersionUserDAO extends GenericDaoImpl<TechSpecsVersionUser, Integer> {
	
	public List<AppVisualizationDTO> findByUserAndStatus(User user, List<Status> statuses) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AppVisualizationDTO> criteria = builder.createQuery(AppVisualizationDTO.class);
		Root<TechSpecsVersionUser> root = criteria.from(TechSpecsVersionUser.class);
		
		criteria.select(builder.construct(AppVisualizationDTO.class, root.get(TechSpecsVersionUser._techSpecsVersion)));
		
		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> statusPredicates = new ArrayList<>();
		
		predicates.add(builder.or(builder.equal(root.get(TechSpecsVersionUser._assignee), user),
					   builder.equal(root.get(TechSpecsVersionUser._assigner), user)));
		
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._isValid), "Y"));
		if (statuses != null && !statuses.isEmpty()) {
			for (Status status : statuses) {
				statusPredicates.add(builder.equal(root.get(TechSpecsVersionUser._techSpecsVersion).get(TechSpecsVersion._techSpecs).get(TechSpecs._status), status));	
			}
		}
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._techSpecsVersion).get(TechSpecsVersion._techSpecs).get(TechSpecs._techSpecsVersion), root.get(TechSpecsVersionUser._techSpecsVersion)));
		
		criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])), 
								   builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
		
		List<Order> order = new ArrayList<>();
		order.add(builder.desc(root.get(TechSpecsVersionUser._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingDate)));
		order.add(builder.desc(root.get(TechSpecsVersionUser._techSpecsVersion).get(TechSpecsVersion._id)));
		criteria.orderBy(order);
		
		criteria.groupBy(root.get(TechSpecsVersionUser._techSpecsVersion), root.get(TechSpecsVersionUser._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingDate));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<User> findDistributedUsers(Integer userId, Integer versionId) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<TechSpecsVersionUser> root = criteria.from(TechSpecsVersionUser.class);
		
		criteria.select(builder.construct(User.class, root.get(TechSpecsVersionUser._assignee).get(_id), 
													  root.get(TechSpecsVersionUser._assignee).get(User._firstName), 
													  root.get(TechSpecsVersionUser._assignee).get(User._lastName)));
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._techSpecsVersion).get(_id), versionId));
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._assigner).get(_id), userId));
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._isValid), "Y"));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getResultList();
	}
	
	public TechSpecsVersionUser findByVersionAndUser(Integer versionId, Integer userId) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<TechSpecsVersionUser> criteria = builder.createQuery(TechSpecsVersionUser.class);
		Root<TechSpecsVersionUser> root = criteria.from(TechSpecsVersionUser.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._techSpecsVersion).get(_id), versionId));
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._assignee).get(_id), userId));
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
	
//	public void updateVersionUser(Integer versionId, Integer userId) {
//		CriteriaBuilder builder = criteriaBuilder();
//		CriteriaUpdate<TechSpecsVersionUser> criteria = builder.createCriteriaUpdate(TechSpecsVersionUser.class);
//		Root<TechSpecsVersionUser> root = criteria.from(TechSpecsVersionUser.class);
//
//		criteria.set(root.get(TechSpecsVersionUser._isValid), "N");
//
//		List<Predicate> predicates = new ArrayList<>();
//		predicates.add(builder.equal(root.get(TechSpecsVersionUser._techSpecsVersion).get(_id), versionId));
//		predicates.add(builder.equal(root.get(TechSpecsVersionUser._assignee).get(_id), userId));
//		
//		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//		
//		createQuery(criteria);
//	}

	public User findAssignerByAuthority(Authority authority) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<TechSpecsVersionUser> root = criteria.from(TechSpecsVersionUser.class);
		
		criteria.select(root.get(TechSpecsVersionUser._assigner));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._isValid), "Y"));
		predicates.add(builder.isMember(authority ,root.get(TechSpecsVersionUser._assigner).get(User._authorities)));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}

	public List<TechSpecsVersionUser> getAssigmentsByAuthorities(TechSpecsVersion techSpecsVersion ,Set<Authority> authorities) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<TechSpecsVersionUser> criteria = builder.createQuery(TechSpecsVersionUser.class);
		Root<TechSpecsVersionUser> root = criteria.from(TechSpecsVersionUser.class);
		
		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> authorityPredicates = new ArrayList<Predicate>();
		
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._isValid), "Y"));
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._techSpecsVersion), techSpecsVersion));
		
		for (Authority authority : authorities) {
			authorityPredicates.add(builder.or(builder.isMember(authority, root.get(TechSpecsVersionUser._assigner).get(User._authorities)),
											   builder.isMember(authority, root.get(TechSpecsVersionUser._assignee).get(User._authorities))));
		}
		
		criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()]))),
					   builder.or(authorityPredicates.toArray(new Predicate[authorityPredicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<String> getAllAssigneeEmailsForVersion(TechSpecsVersion techSpecsVersion) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<TechSpecsVersionUser> root = criteria.from(TechSpecsVersionUser.class);
		
		criteria.select(root.get(TechSpecsVersionUser._assignee).get(User._email));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._isValid), "Y"));
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._techSpecsVersion), techSpecsVersion));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}

	public List<User> getUsersByTechSpecsVersion(TechSpecsVersion version) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<TechSpecsVersionUser> root = criteria.from(TechSpecsVersionUser.class);
		
		criteria.select(root.get(TechSpecsVersionUser._assignee));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._isValid), "Y"));
		predicates.add(builder.equal(root.get(TechSpecsVersionUser._techSpecsVersion), version));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
}
