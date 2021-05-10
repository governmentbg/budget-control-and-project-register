package bg.infosys.daeu.db.dao.pub;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.ExecutionStatus;

@Repository
public class ExecutionStatusDAO extends GenericDaoImpl<ExecutionStatus, Integer> {

	public ExecutionStatus findByCode(String code) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ExecutionStatus> criteria = builder.createQuery(ExecutionStatus.class);
		Root<ExecutionStatus> root = criteria.from(ExecutionStatus.class);
		criteria.where(builder.equal(root.get(ExecutionStatus._code), code));
		
		return createQuery(criteria).getSingleResult();
	}
	public List<ExecutionStatus> findAllValidStatuses() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ExecutionStatus> criteria = builder.createQuery(ExecutionStatus.class);
		Root<ExecutionStatus> root = criteria.from(ExecutionStatus.class);
		criteria.where(builder.equal(root.get(ExecutionStatus._isValid), "Y"));
		
		return createQuery(criteria).getResultList();
	}
}
