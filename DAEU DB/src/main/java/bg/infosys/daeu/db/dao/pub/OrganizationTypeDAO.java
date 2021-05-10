package bg.infosys.daeu.db.dao.pub;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.OrganizationType;

@Repository
public class OrganizationTypeDAO extends GenericDaoImpl<OrganizationType, Integer> {

	public OrganizationType getOrganizationByCode(String code) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<OrganizationType> criteria = builder.createQuery(OrganizationType.class);
		Root<OrganizationType> root = criteria.from(OrganizationType.class);
		criteria.where(builder.like(root.get(OrganizationType._code), code));
		return createQuery(criteria).getResultList()
				.stream().findFirst().orElse(null);
	}

}
