package bg.infosys.daeu.db.dao.projects;

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
import bg.infosys.daeu.db.entity.projects.Project;
import bg.infosys.daeu.db.entity.projects.ProjectNote;
import bg.infosys.daeu.db.entity.projects.ProjectVersion;

@Repository
public class ProjectNoteDAO extends GenericDaoImpl<ProjectNote, Integer> {
	
	public List<ProjectNote> findNotesForProject(Project project, ListModelFilter filter, String isCommon) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectNote> criteria = builder.createQuery(ProjectNote.class);
		Root<ProjectNote> root = criteria.from(ProjectNote.class);

		root.fetch(ProjectNote._user, JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ProjectNote._project), project));
		predicates.add(builder.equal(root.get(ProjectNote._isValid), "Y"));
		if (isCommon != null) {
			predicates.add(builder.equal(root.get(ProjectNote._isCommon), isCommon));
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.desc(root.get(ProjectNote._date)));

		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList();
	}
	
	public List<ProjectNote> findNotesForProject(Project project) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectNote> criteria = builder.createQuery(ProjectNote.class);
		Root<ProjectNote> root = criteria.from(ProjectNote.class);

		root.fetch(ProjectNote._user, JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ProjectNote._project), project));
		predicates.add(builder.equal(root.get(ProjectNote._isValid), "Y"));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.desc(root.get(ProjectNote._date)));

		return createQuery(criteria).getResultList();
	}

	public long findNotesForProjectCount(Project project, String isCommon) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ProjectNote> root = criteria.from(ProjectNote.class);

		criteria.select(builder.count(root));

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ProjectNote._project), project));
		predicates.add(builder.equal(root.get(ProjectNote._isValid), "Y"));
		if (isCommon != null) {
			predicates.add(builder.equal(root.get(ProjectNote._isCommon), isCommon));
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getSingleResult();
	}
	
	public void makeAllVersionNotesCommon(ProjectVersion version) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<ProjectNote> criteria = builder.createCriteriaUpdate(ProjectNote.class);
		Root<ProjectNote> root = criteria.from(ProjectNote.class);
		
		criteria.set(root.get(ProjectNote._isCommon), "Y");
		
		criteria.where(builder.equal(root.get(ProjectNote._version), version));
		
		getEntityManager().createQuery(criteria).executeUpdate();
	}
}
