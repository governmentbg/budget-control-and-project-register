package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.Funding;

@Repository
public class FundingDAO extends GenericDaoImpl<Funding, Integer> {
	public Funding findByName(String name) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Funding> criteria = builder.createQuery(Funding.class);
		Root<Funding> root = criteria.from(Funding.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(Funding._name), name));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		try {
			return createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
