package bg.infosys.daeu.db.dao.projects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.projects.ProjectFormConfig;
import bg.infosys.daeu.db.entity.projects.RowType;

@Repository
public class ProjectFormConfigDAO extends GenericDaoImpl<ProjectFormConfig, Integer> {

	public List<ProjectFormConfig> findAllByStepNum(Short stepNum) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectFormConfig> criteria = builder.createQuery(ProjectFormConfig.class);
		Root<ProjectFormConfig> root = criteria.from(ProjectFormConfig.class);
		root.fetch(ProjectFormConfig._formType, JoinType.LEFT);
		Fetch<Object,Object> rowTypeFetch = root.fetch(ProjectFormConfig._rowType, JoinType.LEFT);
		rowTypeFetch.fetch(RowType._componentType, JoinType.LEFT);
		rowTypeFetch.fetch(RowType._numenclatures, JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ProjectFormConfig._stepNum), stepNum));
		predicates.add(builder.equal(root.get(ProjectFormConfig._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(ProjectFormConfig._orderNum)));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<ProjectFormConfig> findAllInvalid() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectFormConfig> criteria = builder.createQuery(ProjectFormConfig.class);
		Root<ProjectFormConfig> root = criteria.from(ProjectFormConfig.class);
		root.fetch(ProjectFormConfig._formType, JoinType.LEFT);
		Fetch<Object,Object> rowTypeFetch = root.fetch(ProjectFormConfig._rowType, JoinType.LEFT);
		rowTypeFetch.fetch(RowType._componentType, JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(ProjectFormConfig._isValid), "N"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(ProjectFormConfig._stepNum)));
		criteria.orderBy(builder.asc(root.get(ProjectFormConfig._orderNum)));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<ProjectFormConfig> findAllValid() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectFormConfig> criteria = builder.createQuery(ProjectFormConfig.class);
		Root<ProjectFormConfig> root = criteria.from(ProjectFormConfig.class);
		root.fetch(ProjectFormConfig._formType, JoinType.LEFT);
		Fetch<Object,Object> rowTypeFetch = root.fetch(ProjectFormConfig._rowType, JoinType.LEFT);
		rowTypeFetch.fetch(RowType._componentType, JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(ProjectFormConfig._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(ProjectFormConfig._stepNum)));
		criteria.orderBy(builder.asc(root.get(ProjectFormConfig._orderNum)));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<ProjectFormConfig> findAllISUNFields() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectFormConfig> criteria = builder.createQuery(ProjectFormConfig.class);
		Root<ProjectFormConfig> root = criteria.from(ProjectFormConfig.class);
		root.fetch(ProjectFormConfig._formType, JoinType.LEFT);
		Fetch<Object,Object> rowTypeFetch = root.fetch(ProjectFormConfig._rowType, JoinType.LEFT);
		rowTypeFetch.fetch(RowType._componentType, JoinType.LEFT);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(ProjectFormConfig._isValid), "Y"));
		predicates.add(builder.equal(root.get(ProjectFormConfig._isPublicField), "N"));
		predicates.add(builder.isNotNull(root.get(ProjectFormConfig._isunKey)));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
}
