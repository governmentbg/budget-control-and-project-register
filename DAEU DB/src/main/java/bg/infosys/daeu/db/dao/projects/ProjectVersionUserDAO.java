package bg.infosys.daeu.db.dao.projects;

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
import bg.infosys.daeu.db.entity.projects.Project;
import bg.infosys.daeu.db.entity.projects.ProjectVersion;
import bg.infosys.daeu.db.entity.projects.ProjectVersionUser;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.security.Authority;
import bg.infosys.daeu.db.entity.security.User;

@Repository
public class ProjectVersionUserDAO extends GenericDaoImpl<ProjectVersionUser, Integer> {
	
	public List<AppVisualizationDTO> findByUserAndStatus(User user, List<Status> statuses) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AppVisualizationDTO> criteria = builder.createQuery(AppVisualizationDTO.class);
		Root<ProjectVersionUser> root = criteria.from(ProjectVersionUser.class);
		
		criteria.select(builder.construct(AppVisualizationDTO.class, root.get(ProjectVersionUser._projectVersion)));
		
		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> statusPredicates = new ArrayList<>();
		
		predicates.add(builder.or(builder.equal(root.get(ProjectVersionUser._assignee), user),
					   builder.equal(root.get(ProjectVersionUser._assigner), user)));
		
		predicates.add(builder.equal(root.get(ProjectVersionUser._isValid), "Y"));
		if (statuses != null && !statuses.isEmpty()) {
			for (Status status : statuses) {
				statusPredicates.add(builder.equal(root.get(ProjectVersionUser._projectVersion).get(ProjectVersion._project).get(Project._status), status));
			}
		}
		predicates.add(builder.equal(root.get(ProjectVersionUser._projectVersion).get(ProjectVersion._project).get(Project._projectVersion), root.get(ProjectVersionUser._projectVersion)));
		
		criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])), 
				   builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
		
		List<Order> order = new ArrayList<>();
		order.add(builder.desc(root.get(ProjectVersionUser._projectVersion).get(ProjectVersion._subjectOutgoingDate)));
		order.add(builder.desc(root.get(ProjectVersionUser._projectVersion).get(ProjectVersion._id)));
		criteria.orderBy(order);
		
		criteria.groupBy(root.get(ProjectVersionUser._projectVersion), root.get(ProjectVersionUser._projectVersion).get(ProjectVersion._subjectOutgoingDate));
		
		return createQuery(criteria).getResultList();
	}

	public List<User> findDistributedUsers(Integer userId, Integer versionId) {
		
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<ProjectVersionUser> root = criteria.from(ProjectVersionUser.class);
		
		criteria.select(builder.construct(User.class, root.get(ProjectVersionUser._assignee).get(_id), 
													  root.get(ProjectVersionUser._assignee).get(User._firstName), 
													  root.get(ProjectVersionUser._assignee).get(User._lastName)));
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ProjectVersionUser._projectVersion).get(_id), versionId));
		predicates.add(builder.equal(root.get(ProjectVersionUser._assigner).get(_id), userId));
		predicates.add(builder.equal(root.get(ProjectVersionUser._isValid), "Y"));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getResultList();
	}

	public ProjectVersionUser findByVersionAndUser(Integer versionId, Integer assigneeId, Integer assignerId) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectVersionUser> criteria = builder.createQuery(ProjectVersionUser.class);
		Root<ProjectVersionUser> root = criteria.from(ProjectVersionUser.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ProjectVersionUser._projectVersion).get(_id), versionId));
		predicates.add(builder.equal(root.get(ProjectVersionUser._assignee).get(_id), assigneeId));
		predicates.add(builder.equal(root.get(ProjectVersionUser._assigner).get(_id), assignerId));
		predicates.add(builder.equal(root.get(ProjectVersionUser._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public List<ProjectVersionUser> getAssigmentsByAuthorities(ProjectVersion projectVersion, Set<Authority> authorities) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectVersionUser> criteria = builder.createQuery(ProjectVersionUser.class);
		Root<ProjectVersionUser> root = criteria.from(ProjectVersionUser.class);
		
		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> authorityPredicates = new ArrayList<Predicate>();
		
		predicates.add(builder.equal(root.get(ProjectVersionUser._isValid), "Y"));
		predicates.add(builder.equal(root.get(ProjectVersionUser._projectVersion), projectVersion));
		
		for (Authority authority : authorities) {
			authorityPredicates.add(builder.or(builder.isMember(authority, root.get(ProjectVersionUser._assigner).get(User._authorities)),
											   builder.isMember(authority, root.get(ProjectVersionUser._assignee).get(User._authorities))));
		}
		
		criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()]))),
					   builder.or(authorityPredicates.toArray(new Predicate[authorityPredicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<String> getAllAssigneeEmailsForVersion(ProjectVersion projectVersion) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<ProjectVersionUser> root = criteria.from(ProjectVersionUser.class);
		
		criteria.select(root.get(ProjectVersionUser._assignee).get(User._email));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(ProjectVersionUser._isValid), "Y"));
		predicates.add(builder.equal(root.get(ProjectVersionUser._projectVersion), projectVersion));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}

	public List<User> getUsersByProjectVersion(ProjectVersion projectVersion) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<ProjectVersionUser> root = criteria.from(ProjectVersionUser.class);
		
		criteria.select(root.get(ProjectVersionUser._assignee));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(ProjectVersionUser._isValid), "Y"));
		predicates.add(builder.equal(root.get(ProjectVersionUser._projectVersion), projectVersion));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
}
