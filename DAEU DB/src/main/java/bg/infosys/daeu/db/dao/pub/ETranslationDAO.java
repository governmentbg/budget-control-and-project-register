package bg.infosys.daeu.db.dao.pub;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.pub.ETranslation;

@Repository
public class ETranslationDAO extends GenericDaoImpl<ETranslation, Integer>{

	public ETranslation findByRequestId(int requestId) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ETranslation> criteria = builder.createQuery(ETranslation.class);
		Root<ETranslation> root = criteria.from(ETranslation.class);
		criteria.where(builder.equal(root.get(ETranslation._requestId), requestId));
		return createQuery(criteria).getResultList()
				.stream().findFirst().orElse(null);
	}

}
