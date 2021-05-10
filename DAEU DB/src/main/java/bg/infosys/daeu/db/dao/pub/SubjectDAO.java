package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.ListModelFilter;
import bg.infosys.common.utils.Strings;
import bg.infosys.daeu.db.dto.SearchDTO;
import bg.infosys.daeu.db.entity.pub.OrganizationType;
import bg.infosys.daeu.db.entity.pub.Subject;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;

@Repository
public class SubjectDAO extends GenericDaoImpl<Subject, Integer> {

	public List<Subject> searchVersions(String searchPhrase) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Subject> criteria = builder.createQuery(Subject.class);
		Root<Subject> root = criteria.from(Subject.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.like(builder.lower(root.get(Subject._lastVersion).get(SubjectVersion._fullName)), "%" + searchPhrase.toLowerCase() + "%"));
		predicates.add(builder.equal(root.get(Subject._isValid), "Y"));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).setMaxResults(10).getResultList();
	}
	
	public List<Subject> searchVersionsByParent(String searchPhrase, Subject subject) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Subject> criteria = builder.createQuery(Subject.class);
		Root<Subject> root = criteria.from(Subject.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.like(builder.lower(root.get(Subject._lastVersion).get(SubjectVersion._fullName)), "%" + searchPhrase.toLowerCase() + "%"));
		predicates.add(builder.equal(root.get(Subject._isValid), "Y"));
		predicates.add(builder.or(builder.equal(root.get(Subject._lastVersion), subject.getLastVersion()), 
				builder.equal(root.get(Subject._lastVersion).get(SubjectVersion._parent), subject.getLastVersion())));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).setMaxResults(10).getResultList();
	}
	
	public List<Subject> searchParentVersions(String searchPhrase) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Subject> criteria = builder.createQuery(Subject.class);
		Root<Subject> root = criteria.from(Subject.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.like(builder.lower(root.get(Subject._lastVersion).get(SubjectVersion._fullName)), "%" + searchPhrase.toLowerCase() + "%"));
		predicates.add(builder.equal(root.get(Subject._isValid), "Y"));
		predicates.add(builder.isNull(root.get(Subject._lastVersion).get(SubjectVersion._parent)));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).setMaxResults(10).getResultList();
	}

	public List<Subject> findSubjectsByProperties(SearchDTO searchObject, ListModelFilter filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Subject> criteria = builder.createQuery(Subject.class);
		Root<Subject> root = criteria.from(Subject.class);
		root.fetch(Subject._lastVersion, JoinType.LEFT).fetch(SubjectVersion._children, JoinType.LEFT);

		List<Predicate> predicates = getWhereClause(searchObject, builder, root);

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(Subject._lastVersion).get(SubjectVersion._fullName)));
		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList();
	}
	
	public long countSubjectsByProperties(SearchDTO searchObject) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Subject> root = criteria.from(Subject.class);

		criteria.select(builder.count(root));

		List<Predicate> predicates = getWhereClause(searchObject, builder, root);

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getSingleResult();
	}
	
	private List<Predicate> getWhereClause(SearchDTO searchObject, CriteriaBuilder builder, Root<Subject> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!Strings.isEmpty(searchObject.getName())) {
			predicates.add(builder.like(builder.lower(root.get(Subject._lastVersion).get(SubjectVersion._fullName)), "%" + searchObject.getName().toLowerCase() + "%"));
		}

		if (!Strings.isEmpty(searchObject.getIdentNum())) {
			predicates.add(builder.like(root.get(Subject._identNum), "%" + searchObject.getIdentNum().strip() + "%"));
		}
		if (searchObject.getOrgType() != null) {
			predicates.add(builder.equal(root.get(Subject._orgType), searchObject.getOrgType()));
		}

		if (searchObject.getIsValid() != null) {
			predicates.add(builder.equal(root.get(Subject._isValid), searchObject.getIsValid()));
		}

		return predicates;
	}

	public SubjectVersion findLastSubVersion(Subject currentSubject) {
			CriteriaBuilder builder = criteriaBuilder();
			CriteriaQuery<SubjectVersion> criteria = builder.createQuery(SubjectVersion.class);
			Root<Subject> root = criteria.from(Subject.class);
			criteria.where(builder.equal(root.get(Subject._id), currentSubject.getId()));
			criteria.select(root.get(Subject._lastVersion));
			return createQuery(criteria).getResultList()
					.stream().findFirst().orElse(null);

	}

	public List<Subject> findAllSubjectsByOrgType(OrganizationType orgType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Subject> criteria = builder.createQuery(Subject.class);
		Root<Subject> root = criteria.from(Subject.class);
		criteria.where(builder.equal(root.get(Subject._orgType), orgType.getId()));
		return createQuery(criteria).getResultList();
	}

	public boolean checkIfSubjectExist(String identNum, int id) {

			CriteriaBuilder builder = criteriaBuilder();
			CriteriaQuery<Subject> criteria = builder.createQuery(Subject.class);
			List<Predicate> predicates = new ArrayList<>();
			Root<Subject> root = criteria.from(Subject.class);
			predicates.add(builder.notEqual(root.get(Subject._id), id));
			predicates.add(builder.equal(root.get(Subject._identNum), identNum));
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
			
			if (!createQuery(criteria).getResultList().isEmpty()){
				return true;
			}
			return false;
		}

	public List<Subject> findAllChildSubjectsBySubject(Subject subject) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Subject> criteria = builder.createQuery(Subject.class);
		Root<Subject> root = criteria.from(Subject.class);
		criteria.where(builder.equal(root.get(Subject._lastVersion).get(SubjectVersion._parent), subject.getLastVersion().getId()));
		return createQuery(criteria).getResultList();
	}
	
	public List<Subject> findAllValidParentSubjects() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Subject> criteria = builder.createQuery(Subject.class);
		Root<Subject> root = criteria.from(Subject.class);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(Subject._isValid), "Y"));
		predicates.add(builder.isNull(root.get(Subject._lastVersion).get(SubjectVersion._parent)));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		return createQuery(criteria).getResultList();
	}
	
	public Subject findByEIK(String eik) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Subject> criteria = builder.createQuery(Subject.class);
		Root<Subject> root = criteria.from(Subject.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(Subject._isValid), "Y"));
		predicates.add(builder.equal(root.get(Subject._identNum), eik));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList().stream().findFirst().orElse(null);
	}

	public Subject initSubjectChildren(Subject subject) {
			CriteriaBuilder builder = criteriaBuilder();
			CriteriaQuery<Subject> criteria = builder.createQuery(Subject.class);
			Root<Subject> root = criteria.from(Subject.class);
			root.fetch(Subject._lastVersion, JoinType.LEFT).fetch(SubjectVersion._children, JoinType.LEFT);
			criteria.where(builder.equal(root.get(Subject._id), subject.getId()));		
			return createQuery(criteria).getSingleResult();
	}
}
