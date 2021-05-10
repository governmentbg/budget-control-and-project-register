package bg.infosys.daeu.db.dao.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.security.Authority;
import bg.infosys.daeu.db.entity.security.Position;

@Repository
public class AuthorityDAO extends GenericDaoImpl<Authority, Integer> {

	public Authority findByName(String name) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Authority> query = builder.createQuery(Authority.class);
		Root<Authority> root = query.from(Authority.class);
		query.select(root).where(builder.equal(root.get("name"), name));

		try {
			return createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Authority> findAllAuthorities(String type, String isValid) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Authority> query = builder.createQuery(Authority.class);
		Root<Authority> root = query.from(Authority.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(isValid != null && isValid.equals("Y")) {
			predicates.add(builder.equal(root.get(Authority._active), true));
		}
		predicates.add(builder.equal(root.get(Authority._type), type));
		query.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(query).getResultList();
	}
	
	public Authority findByPosition(Position position) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Authority> query = builder.createQuery(Authority.class);
		Root<Authority> root = query.from(Authority.class);
		query.select(root).where(builder.equal(root.get(Authority._position), position));
		
		return createQuery(query).getSingleResult();
	}

	public boolean checkIfAuthorityExist(String name, Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Authority> criteria = builder.createQuery(Authority.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<Authority> root = criteria.from(Authority.class);
		if(id != null) {
			predicates.add(builder.notEqual(root.get(Authority._id), id));
		}
		predicates.add(builder.like(root.get(Authority._name), name));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		if (!createQuery(criteria).getResultList().isEmpty()){
			return true;
		}
		return false;
	}
}
