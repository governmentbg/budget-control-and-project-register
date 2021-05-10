package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;

@Repository
public class SubjectVersionDAO extends GenericDaoImpl<SubjectVersion, Integer> {

	public List<SubjectVersion> searchVersions(String searchPhrase) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<SubjectVersion> criteria = builder.createQuery(SubjectVersion.class);
		Root<SubjectVersion> root = criteria.from(SubjectVersion.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.like(builder.lower(root.get(SubjectVersion._fullName)), "%" + searchPhrase.toLowerCase() + "%"));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).setMaxResults(10).getResultList();
	}

}
