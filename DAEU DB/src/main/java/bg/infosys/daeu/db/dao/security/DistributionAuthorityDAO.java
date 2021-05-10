package bg.infosys.daeu.db.dao.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.security.Authority;
import bg.infosys.daeu.db.entity.security.DistributionAuthority;

@Repository
public class DistributionAuthorityDAO extends GenericDaoImpl<DistributionAuthority, Integer> {
	public List<Authority> findSubordinateAuthorities(Authority authority) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Authority> criteria = builder.createQuery(Authority.class);
		Root<DistributionAuthority> root = criteria.from(DistributionAuthority.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(builder.equal(root.get(DistributionAuthority._parent), authority));
		predicates.add(builder.equal(root.get(DistributionAuthority._isValid), "Y"));
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		criteria.select(root.get(DistributionAuthority._child)).distinct(true);
		
		return createQuery(criteria).getResultList();
	}
	
	public List<DistributionAuthority> findByAuthority(Authority authority) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<DistributionAuthority> criteria = builder.createQuery(DistributionAuthority.class);
		Root<DistributionAuthority> root = criteria.from(DistributionAuthority.class);
		root.fetch(DistributionAuthority._child, JoinType.LEFT);
		
		criteria.select(root);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(builder.equal(root.get(DistributionAuthority._parent), authority));
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		
		return createQuery(criteria).getResultList();
	}

	public List<DistributionAuthority> getChecklistDistributionStructure(Set<Authority> authorities) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<DistributionAuthority> criteria = builder.createQuery(DistributionAuthority.class);
		Root<DistributionAuthority> root = criteria.from(DistributionAuthority.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		List<Predicate> authorityPredicates = new ArrayList<Predicate>();
		
		for (Authority authority : authorities) {
			authorityPredicates.add(builder.or(builder.equal(root.get(DistributionAuthority._parent), authority), builder.equal(root.get(DistributionAuthority._child), authority)));
		}
		predicates.add(builder.equal(root.get(DistributionAuthority._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])), builder.or(authorityPredicates.toArray(new Predicate[authorityPredicates.size()])));
		return createQuery(criteria).getResultList();
	}
}
