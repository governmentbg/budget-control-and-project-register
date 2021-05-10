package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.LetterTemplate;
import bg.infosys.daeu.db.entity.pub.LetterType;
import bg.infosys.daeu.db.entity.pub.ModuleType;
import bg.infosys.daeu.db.entity.pub.Status;

@Repository
public class LetterTemplateDAO extends GenericDaoImpl<LetterTemplate, Integer> {
	
	public List<LetterTemplate> getLetterTemplatesByModuleType(ModuleType moduleType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<LetterTemplate> criteria = builder.createQuery(LetterTemplate.class);
		Root<LetterTemplate> root = criteria.from(LetterTemplate.class);
		
		root.fetch(LetterTemplate._type, JoinType.LEFT).fetch(LetterType._finalStatus, JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(LetterTemplate._type).get(LetterType._moduleType), moduleType));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<LetterTemplate> getLetterTemplatesByFormTypeAndStatus(ModuleType moduleType, Status status) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<LetterTemplate> criteria = builder.createQuery(LetterTemplate.class);
		Root<LetterTemplate> root = criteria.from(LetterTemplate.class);
		
		root.fetch(LetterTemplate._type, JoinType.LEFT).fetch(LetterType._finalStatus, JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(LetterTemplate._type).get(LetterType._moduleType), moduleType));
		predicates.add(builder.equal(root.get(LetterTemplate._type).get(LetterType._finalStatus), status));
		predicates.add(builder.equal(root.get(LetterTemplate._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}

}
