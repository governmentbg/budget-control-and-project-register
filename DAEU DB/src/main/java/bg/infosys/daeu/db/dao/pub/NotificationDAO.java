package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.ListModelFilter;
import bg.infosys.daeu.db.entity.pub.Notification;
import bg.infosys.daeu.db.entity.security.User;

@Repository
public class NotificationDAO extends GenericDaoImpl<Notification, Integer> {

	public List<Notification> findByUser(User user, ListModelFilter filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Notification> criteria = builder.createQuery(Notification.class);
		Root<Notification> root = criteria.from(Notification.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(Notification._user), user));
		predicates.add(builder.equal(root.get(Notification._isVisible), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(Notification._seen)), builder.desc(root.get(Notification._receivedTimestamp)));
		
		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList();
	}
	
	public long countByUser(User user) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Notification> root = criteria.from(Notification.class);

		criteria.select(builder.count(root));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(Notification._user), user));
		predicates.add(builder.equal(root.get(Notification._isVisible), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getSingleResult();
	}
	
	public long newNotificationsForUser(User user) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Notification> root = criteria.from(Notification.class);

		criteria.select(builder.count(root));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(Notification._user), user));
		predicates.add(builder.equal(root.get(Notification._isVisible), "Y"));
		predicates.add(builder.equal(root.get(Notification._seen), false));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getSingleResult();
	}
	
}
