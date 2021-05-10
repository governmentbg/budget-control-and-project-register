package bg.infosys.daeu.db.dao.tech_specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.ListModelFilter;
import bg.infosys.daeu.db.entity.projects.ProjectNote;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecs;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsNote;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsVersion;

@Repository
public class TechSpecsNoteDAO extends GenericDaoImpl<TechSpecsNote, Integer> {

	public List<TechSpecsNote> findNotesForTechSpecs(TechSpecs techSpecs, ListModelFilter filter, String isCommon) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<TechSpecsNote> criteria = builder.createQuery(TechSpecsNote.class);
		Root<TechSpecsNote> root = criteria.from(TechSpecsNote.class);

		root.fetch(TechSpecsNote._user, JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(TechSpecsNote._techSpecs), techSpecs));
		predicates.add(builder.equal(root.get(TechSpecsNote._isValid), "Y"));
		if (isCommon != null) {
			predicates.add(builder.equal(root.get(ProjectNote._isCommon), isCommon));
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.desc(root.get(TechSpecsNote._date)));

		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList();
	}

	public long findNotesForTechSpecsCount(TechSpecs techSpecs, String isCommon) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<TechSpecsNote> root = criteria.from(TechSpecsNote.class);

		criteria.select(builder.count(root));

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(TechSpecsNote._techSpecs), techSpecs));
		predicates.add(builder.equal(root.get(TechSpecsNote._isValid), "Y"));
		if (isCommon != null) {
			predicates.add(builder.equal(root.get(ProjectNote._isCommon), isCommon));
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getSingleResult();
	}
	
	public void makeAllVersionNotesCommon(TechSpecsVersion version) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<TechSpecsNote> criteria = builder.createCriteriaUpdate(TechSpecsNote.class);
		Root<TechSpecsNote> root = criteria.from(TechSpecsNote.class);
		
		criteria.set(root.get(TechSpecsNote._isCommon), "Y");
		
		criteria.where(builder.equal(root.get(TechSpecsNote._techSpecsVersion), version));
		
		getEntityManager().createQuery(criteria).executeUpdate();
	}
	
}
