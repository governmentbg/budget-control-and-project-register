package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.Indicator;

@Repository
public class IndicatorDAO extends GenericDaoImpl<Indicator, Integer> {

	public List<Indicator> findAllIndicatorsWithParent() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Indicator> criteria = builder.createQuery(Indicator.class);
		Root<Indicator> root = criteria.from(Indicator.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.isNotNull(root.get(Indicator._parent)));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<Indicator> findAllRoadMapIndicatorsWithParent() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Indicator> criteria = builder.createQuery(Indicator.class);
		Root<Indicator> root = criteria.from(Indicator.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.isNotNull(root.get(Indicator._parent)));
		predicates.add(builder.equal(root.get(Indicator._isRoadMapIndicator), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(GenericDaoImpl._id)));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<Indicator> findAllISUNindicators() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Indicator> criteria = builder.createQuery(Indicator.class);
		Root<Indicator> root = criteria.from(Indicator.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.isNotNull(root.get(Indicator._isunKey)));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
	
}
