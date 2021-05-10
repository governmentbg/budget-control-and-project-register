package bg.infosys.daeu.db.dao.tech_specs;

import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.tech_specs.TechRowType;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsFormConfig;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsType;

@Repository
public class TechSpecsFormConfigDAO extends GenericDaoImpl<TechSpecsFormConfig, Integer> {

	public List<TechSpecsFormConfig> findAllByFormType(TechSpecsType selectedType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<TechSpecsFormConfig> criteria = builder.createQuery(TechSpecsFormConfig.class);
		Root<TechSpecsFormConfig> root = criteria.from(TechSpecsFormConfig.class);
		Restrictions r = new Restrictions();

		if(selectedType == null) {
			r.add(builder.isNull((root.get(TechSpecsFormConfig._techSpecsType)))); 
		}else {
			r.add(builder.equal(root.get(TechSpecsFormConfig._techSpecsType), selectedType));
		}
		
		root.fetch(TechSpecsFormConfig._rowType, JoinType.LEFT).fetch(TechRowType._numenclatures, JoinType.LEFT);
		
		r.add(builder.equal(root.get(TechSpecsFormConfig._isValid), "Y"));
		criteria.orderBy(builder.asc(root.get(TechSpecsFormConfig._orderNum)));
		r.apply(builder, root, criteria, true);
		return createQuery(criteria).getResultList();

	}

	public List<TechSpecsFormConfig> findAllTechspecsFormConfigs() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<TechSpecsFormConfig> criteria = builder.createQuery(TechSpecsFormConfig.class);
		Root<TechSpecsFormConfig> root = criteria.from(TechSpecsFormConfig.class);
		Restrictions r = new Restrictions();

		root.fetch(TechSpecsFormConfig._rowType, JoinType.LEFT).fetch(TechRowType._numenclatures, JoinType.LEFT);
		
		r.add(builder.equal(root.get(TechSpecsFormConfig._isValid), "Y"));
		criteria.orderBy(builder.asc(root.get(TechSpecsFormConfig._orderNum)));
		r.apply(builder, root, criteria, true);
		return createQuery(criteria).getResultList();
	}
	
	public Collection<TechSpecsFormConfig> findAllInvalid() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<TechSpecsFormConfig> criteria = builder.createQuery(TechSpecsFormConfig.class);
		Root<TechSpecsFormConfig> root = criteria.from(TechSpecsFormConfig.class);
		Restrictions r = new Restrictions();

		r.add(builder.equal(root.get(TechSpecsFormConfig._isValid), "N"));
		
		criteria.orderBy(builder.asc(root.get(TechSpecsFormConfig._sectionNum)));
		r.apply(builder, root, criteria, true);
		return createQuery(criteria).getResultList();
	}
}
