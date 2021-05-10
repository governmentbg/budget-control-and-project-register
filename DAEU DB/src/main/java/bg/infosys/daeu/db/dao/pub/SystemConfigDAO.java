package bg.infosys.daeu.db.dao.pub;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.pub.SystemConfig;

@Repository
public class SystemConfigDAO extends GenericDaoImpl<SystemConfig, String>{

	public SystemConfig findConfigByCode(String code) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<SystemConfig> criteria = builder.createQuery(SystemConfig.class);
		Root<SystemConfig> root = criteria.from(SystemConfig.class);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(SystemConfig._code), code));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}

	public List<SystemConfig> findAllSystemConfigs() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<SystemConfig> criteria = builder.createQuery(SystemConfig.class);
		Root<SystemConfig> root = criteria.from(SystemConfig.class);
		Restrictions r = new Restrictions();
		r.apply(builder, root, criteria, true);
		
		return createQuery(criteria).getResultList();
	}
	
	public long countAllSystemConfigs() {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<SystemConfig> root = criteria.from(SystemConfig.class);
		criteria.select(builder.count(root));
		
		return createQuery(criteria).getSingleResult();
	}

}
