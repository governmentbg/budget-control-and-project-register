package bg.infosys.daeu.db.dao.tech_specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.tech_specs.TechRowType;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsFormConfig;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsType;

@Repository
public class TechSpecsTypeDAO extends GenericDaoImpl<TechSpecsType, Integer> {

	public List<TechSpecsType> findAllTechSpecTypesByConfig(TechSpecsFormConfig config) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<TechSpecsType> criteria = builder.createQuery(TechSpecsType.class);
		Root<TechSpecsType> root = criteria.from(TechSpecsType.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.join(TechSpecsType._techRowType).get(TechRowType._code), config.getRowType().getCode()));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getResultList();
	}
}
