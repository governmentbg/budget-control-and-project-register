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

@Repository
public class ModuleTypeDAO extends GenericDaoImpl<ModuleType, String>{

	public ModuleType findModuleType(String code) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ModuleType> criteria = builder.createQuery(ModuleType.class);
		Root<ModuleType> root = criteria.from(ModuleType.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ModuleType._code), code));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}

	public List<ModuleType> findAllLetterModuleTypes() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ModuleType> criteria = builder.createQuery(ModuleType.class);
		Root<ModuleType> root = criteria.from(ModuleType.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(ModuleType._hasLetters), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}

}
