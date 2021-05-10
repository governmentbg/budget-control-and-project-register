package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.AttachedDocType;
import bg.infosys.daeu.db.entity.pub.ModuleType;

@Repository
public class AttachedDocTypeDAO extends GenericDaoImpl<AttachedDocType, Integer> {
	
	public List<AttachedDocType> findAllAttachedDocTypesByForm(ModuleType moduleType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AttachedDocType> criteria = builder.createQuery(AttachedDocType.class);
		Root<AttachedDocType> root = criteria.from(AttachedDocType.class);
		
		criteria.where(builder.equal(root.get(AttachedDocType._moduleType), moduleType));
		
		return createQuery(criteria).getResultList();
	}

	public AttachedDocType findByModuleTypeAndCode(String moduleTypeCode, String code) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AttachedDocType> criteria = builder.createQuery(AttachedDocType.class);
		Root<AttachedDocType> root = criteria.from(AttachedDocType.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(builder.equal(root.get(AttachedDocType._moduleType).get(ModuleType._code), moduleTypeCode));
		predicates.add(builder.equal(root.get(AttachedDocType._code), code));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
}
