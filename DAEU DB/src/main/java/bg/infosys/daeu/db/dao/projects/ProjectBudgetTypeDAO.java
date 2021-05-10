package bg.infosys.daeu.db.dao.projects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.projects.ProjectBudgetType;

@Repository
public class ProjectBudgetTypeDAO extends GenericDaoImpl<ProjectBudgetType, Integer> {

	public List<ProjectBudgetType> findAllValid() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectBudgetType> criteria = builder.createQuery(ProjectBudgetType.class);
		Root<ProjectBudgetType> root = criteria.from(ProjectBudgetType.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(builder.equal(root.get(ProjectBudgetType._isValid), "Y"));
		criteria.where(builder.or(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
	
	public ProjectBudgetType findByCode(String code) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectBudgetType> criteria = builder.createQuery(ProjectBudgetType.class);
		Root<ProjectBudgetType> root = criteria.from(ProjectBudgetType.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(builder.equal(root.get(ProjectBudgetType._isValid), "Y"));
		predicates.add(builder.equal(root.get(ProjectBudgetType._code), code));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		try {
			return createQuery(criteria).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
