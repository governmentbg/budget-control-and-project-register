package bg.infosys.daeu.db.dao.tech_specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsVersion;

@Repository
public class TechSpecsVersionDAO extends GenericDaoImpl<TechSpecsVersion, Integer> {
	public void updateReturnedAtTimestamp(Integer id, String token) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<TechSpecsVersion> criteria = builder.createCriteriaUpdate(TechSpecsVersion.class);
		Root<TechSpecsVersion> root = criteria.from(TechSpecsVersion.class);
		
		criteria.set(root.get(TechSpecsVersion._returnedAtTimestamp), token);
		
		criteria.where(builder.equal(root.get(_id), id));
		
		getEntityManager().createQuery(criteria).executeUpdate();
	}
}
