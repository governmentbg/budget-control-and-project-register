package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.ModuleType;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.pub.StatusAuthority;
import bg.infosys.daeu.db.entity.security.Authority;

@Repository
public class StatusAuthorityDAO extends GenericDaoImpl<StatusAuthority, Integer> {
	@Transactional
	public List<StatusAuthority> findByAuthority(Authority authority, String moduleCode) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<StatusAuthority> criteria = builder.createQuery(StatusAuthority.class);
		Root<StatusAuthority> root = criteria.from(StatusAuthority.class);
		Fetch<Object, Object> statusJoin = root.fetch(StatusAuthority._status, JoinType.LEFT);
		statusJoin.fetch(Status._moduleType, JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(builder.equal(root.get(StatusAuthority._isValid), "Y"));
		predicates.add(builder.equal(root.get(StatusAuthority._authority), authority));
		predicates.add(builder.equal(root.get(StatusAuthority._status).get(Status._moduleType).get(ModuleType._code), moduleCode));
		predicates.add(builder.equal(root.get(StatusAuthority._status).get(Status._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(StatusAuthority._status).get(Status._orderNum)));
		return createQuery(criteria).getResultList();
	}
}
