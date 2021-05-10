package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.ModuleType;
import bg.infosys.daeu.db.entity.pub.Status;

@Repository
public class StatusDAO extends GenericDaoImpl<Status, Integer> {

	public List<Status> findStatusesByModuleType(String type, boolean b, boolean isFinalStage) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Status> criteria = builder.createQuery(Status.class);
		Root<Status> root = criteria.from(Status.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(Status._moduleType).get(ModuleType._code), type));
		predicates.add(builder.equal(root.get(Status._isFinalStage), isFinalStage));
		predicates.add(builder.equal(root.get(Status._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(Status._orderNum)));
		
		return createQuery(criteria).getResultList();
	}

	public Status findByCodeAndType(String code, String type, boolean isFinalStage) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Status> criteria = builder.createQuery(Status.class);
		Root<Status> root = criteria.from(Status.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(Status._code), code));
		predicates.add(builder.equal(root.get(Status._moduleType).get(ModuleType._code), type));
		predicates.add(builder.equal(root.get(Status._isFinalStage), isFinalStage));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public List<Status> findValidStatusesByModuleType(String type) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Status> criteria = builder.createQuery(Status.class);
		Root<Status> root = criteria.from(Status.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(Status._moduleType).get(ModuleType._code), type));
		predicates.add(builder.equal(root.get(Status._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}

	public List<Status> findAllFinalStatuses(String moduleType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Status> criteria = builder.createQuery(Status.class);
		Root<Status> root = criteria.from(Status.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(Status._moduleType).get(ModuleType._code), moduleType));
		predicates.add(builder.equal(root.get(Status._isFinalStage), true));
		predicates.add(builder.equal(root.get(Status._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}

}
