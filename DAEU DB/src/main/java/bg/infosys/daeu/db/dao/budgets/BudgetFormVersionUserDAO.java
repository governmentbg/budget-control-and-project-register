package bg.infosys.daeu.db.dao.budgets;

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
import bg.infosys.daeu.db.entity.budgets.BudgetForm;
import bg.infosys.daeu.db.entity.budgets.BudgetFormVersion;
import bg.infosys.daeu.db.entity.budgets.BudgetFormVersionUser;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.security.Authority;
import bg.infosys.daeu.db.entity.security.User;

@Repository
public class BudgetFormVersionUserDAO extends GenericDaoImpl<BudgetFormVersionUser, Integer> {
	
	public List<AppVisualizationDTO> findByUserAndStatus(User user, List<Status> statuses) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AppVisualizationDTO> criteria = builder.createQuery(AppVisualizationDTO.class);
		Root<BudgetFormVersionUser> root = criteria.from(BudgetFormVersionUser.class);
		
		criteria.select(builder.construct(AppVisualizationDTO.class, root.get(BudgetFormVersionUser._budgetFormVersion)));
		
		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> statusPredicates = new ArrayList<>();
		
		predicates.add(builder.or(builder.equal(root.get(BudgetFormVersionUser._assignee), user),
					   builder.equal(root.get(BudgetFormVersionUser._assigner), user)));
		
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._isValid), "Y"));
		predicates.add(builder.isNull(root.get(BudgetFormVersionUser._budgetFormVersion).get(BudgetFormVersion._parent)));
		if (statuses != null && !statuses.isEmpty()) {
			for (Status status : statuses) {
				statusPredicates.add(builder.equal(root.get(BudgetFormVersionUser._budgetFormVersion).get(BudgetFormVersion._budgetForm).get(BudgetForm._status), status));
			}
		}
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._budgetFormVersion).get(BudgetFormVersion._budgetForm).get(BudgetForm._budgetFormVersion), root.get(BudgetFormVersionUser._budgetFormVersion)));
		
		criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])), 
				   builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
		
		List<Order> order = new ArrayList<>();
		order.add(builder.desc(root.get(BudgetFormVersionUser._budgetFormVersion).get(BudgetFormVersion._subjectOutgoingDate)));
		order.add(builder.desc(root.get(BudgetFormVersionUser._budgetFormVersion).get(BudgetFormVersion._id)));
		criteria.orderBy(order);
		
		criteria.groupBy(root.get(BudgetFormVersionUser._budgetFormVersion), root.get(BudgetFormVersionUser._budgetFormVersion).get(BudgetFormVersion._subjectOutgoingDate));
		
		return createQuery(criteria).getResultList();
		
	}
	
	public List<User> findDistributedUsers(Integer userId, Integer versionId) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<BudgetFormVersionUser> root = criteria.from(BudgetFormVersionUser.class);
		
		criteria.select(builder.construct(User.class, root.get(BudgetFormVersionUser._assignee).get(_id), 
													  root.get(BudgetFormVersionUser._assignee).get(User._firstName), 
													  root.get(BudgetFormVersionUser._assignee).get(User._lastName)));
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._budgetFormVersion).get(_id), versionId));
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._assigner).get(_id), userId));
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._isValid), "Y"));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getResultList();
	}
	
	public BudgetFormVersionUser findByVersionAndUser(Integer versionId, Integer userId) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetFormVersionUser> criteria = builder.createQuery(BudgetFormVersionUser.class);
		Root<BudgetFormVersionUser> root = criteria.from(BudgetFormVersionUser.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._budgetFormVersion).get(_id), versionId));
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._assignee).get(_id), userId));
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public User findAssignerByAuthority(Authority authority) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<BudgetFormVersionUser> root = criteria.from(BudgetFormVersionUser.class);
		
		criteria.select(root.get(BudgetFormVersionUser._assigner));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._isValid), "Y"));
		predicates.add(builder.isMember(authority ,root.get(BudgetFormVersionUser._assigner).get(User._authorities)));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}

	public List<BudgetFormVersionUser> getAssigmentsByAuthorities(BudgetFormVersion budgetFormVersion ,Set<Authority> authorities) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetFormVersionUser> criteria = builder.createQuery(BudgetFormVersionUser.class);
		Root<BudgetFormVersionUser> root = criteria.from(BudgetFormVersionUser.class);
		
		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> authorityPredicates = new ArrayList<Predicate>();
		
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._isValid), "Y"));
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._budgetFormVersion), budgetFormVersion));
		
		for (Authority authority : authorities) {
			authorityPredicates.add(builder.or(builder.isMember(authority, root.get(BudgetFormVersionUser._assigner).get(User._authorities)),
											   builder.isMember(authority, root.get(BudgetFormVersionUser._assignee).get(User._authorities))));
		}
		
		criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()]))),
					   builder.or(authorityPredicates.toArray(new Predicate[authorityPredicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<String> getAllAssigneeEmailsForVersion(BudgetFormVersion budgetFormVersion) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<BudgetFormVersionUser> root = criteria.from(BudgetFormVersionUser.class);
		
		criteria.select(root.get(BudgetFormVersionUser._assignee).get(User._email));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._isValid), "Y"));
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._budgetFormVersion), budgetFormVersion));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<User> getUsersByBudgetFormVersion(BudgetFormVersion budgetFormVersion) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<BudgetFormVersionUser> root = criteria.from(BudgetFormVersionUser.class);
		
		criteria.select(root.get(BudgetFormVersionUser._assignee));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._isValid), "Y"));
		predicates.add(builder.equal(root.get(BudgetFormVersionUser._budgetFormVersion), budgetFormVersion));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
}
