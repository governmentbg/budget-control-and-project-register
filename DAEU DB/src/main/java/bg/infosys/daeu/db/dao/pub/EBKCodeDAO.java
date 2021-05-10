package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.EBKCode;

@Repository
public class EBKCodeDAO extends GenericDaoImpl<EBKCode, Integer> {

	public List<EBKCode> findAllValid() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<EBKCode> criteria = builder.createQuery(EBKCode.class);
		Root<EBKCode> root = criteria.from(EBKCode.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(EBKCode._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}

}
