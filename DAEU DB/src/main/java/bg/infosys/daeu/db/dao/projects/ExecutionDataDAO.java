package bg.infosys.daeu.db.dao.projects;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.projects.ExecutionData;
import bg.infosys.daeu.db.entity.pub.CPVCode;

@Repository
public class ExecutionDataDAO extends GenericDaoImpl<ExecutionData, Integer> {

	public List<ExecutionData> findExecutionDatasByCpv(CPVCode cpvCode) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ExecutionData> criteria = builder.createQuery(ExecutionData.class);
		Root<ExecutionData> root = criteria.from(ExecutionData.class);
		
		Restrictions r = new Restrictions();
		r.add(builder.isMember(cpvCode, root.get(ExecutionData._cpvCodes)));
		r.apply(builder, root, criteria, true);

		return createQuery(criteria).getResultList();
	}
}
