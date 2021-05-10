package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.LetterControlConfig;
import bg.infosys.daeu.db.entity.pub.ModuleType;

@Repository
public class LetterControlConfigDAO extends GenericDaoImpl<LetterControlConfig, Integer> {

	public List<LetterControlConfig> getLetterTemplatesByFormType(ModuleType moduleType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<LetterControlConfig> criteria = builder.createQuery(LetterControlConfig.class);
		Root<LetterControlConfig> root = criteria.from(LetterControlConfig.class);
		
		criteria.select(root);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(LetterControlConfig._moduleType), moduleType));
		predicates.add(builder.equal(root.get(LetterControlConfig._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(LetterControlConfig._orderNum)));
		
		return createQuery(criteria).getResultList();
	}
	
	public LetterControlConfig getNextLetterControlConfigByFormType(ModuleType moduleType, Integer next) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<LetterControlConfig> criteria = builder.createQuery(LetterControlConfig.class);
		Root<LetterControlConfig> root = criteria.from(LetterControlConfig.class);
		
		criteria.select(root);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(LetterControlConfig._moduleType), moduleType));
		predicates.add(builder.equal(root.get(LetterControlConfig._isValid), "Y"));
		if (next == null) next = 1;
		predicates.add(builder.equal(root.get(LetterControlConfig._orderNum), next));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList().stream().findFirst().orElse(null);
	}
	
}
