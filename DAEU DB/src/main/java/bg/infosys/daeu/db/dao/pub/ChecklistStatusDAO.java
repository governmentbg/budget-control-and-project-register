package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.ChecklistStatus;

@Repository
public class ChecklistStatusDAO extends GenericDaoImpl<ChecklistStatus, Integer> {
	
	public ChecklistStatus findByCode(String code) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ChecklistStatus> criteria = builder.createQuery(ChecklistStatus.class);
		Root<ChecklistStatus> root = criteria.from(ChecklistStatus.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ChecklistStatus._code), code));
		predicates.add(builder.equal(root.get(ChecklistStatus._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
}
