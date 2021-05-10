package bg.infosys.daeu.db.dao.projects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.projects.ExecutionData;
import bg.infosys.daeu.db.entity.projects.ProjectMainData;

@Repository
public class ProjectMainDataDAO extends GenericDaoImpl<ProjectMainData, Integer> {

	public List<ProjectMainData> findProjectMainDatasByExecutionDatas(List<ExecutionData> executionDatas) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectMainData> criteria = builder.createQuery(ProjectMainData.class);
		Root<ProjectMainData> root = criteria.from(ProjectMainData.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();

		for(ExecutionData data : executionDatas) {
			predicates.add(builder.isMember(data, root.get(ProjectMainData._executionDatas)));
		}
		criteria.where(builder.or(predicates.toArray(new Predicate[predicates.size()])));
		return createQuery(criteria).getResultList();
	}
}
