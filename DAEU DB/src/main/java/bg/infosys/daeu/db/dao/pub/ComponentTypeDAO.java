package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.ComponentType;

@Repository
public class ComponentTypeDAO extends GenericDaoImpl<ComponentType, String> {

	public List<ComponentType> findAllComponentTypesForCreate() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ComponentType> criteria = builder.createQuery(ComponentType.class);
		Root<ComponentType> root = criteria.from(ComponentType.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ComponentType._canCreate), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}

	public ComponentType findComponentTypeByCode(String code) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ComponentType> criteria = builder.createQuery(ComponentType.class);
		Root<ComponentType> root = criteria.from(ComponentType.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ComponentType._code), code));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}

}
