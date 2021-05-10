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
import bg.infosys.daeu.db.entity.pub.OpenData;

@Repository
public class OpenDataDAO extends GenericDaoImpl<OpenData, Integer> {

	public String findOpenDataURI(String type, String moduleTypeCode) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<OpenData> root = criteria.from(OpenData.class);
		
		criteria.select(root.get(OpenData._openDataURI));
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(OpenData._openDataType), type));
		predicates.add(builder.equal(root.get(OpenData._moduleType).get(ModuleType._code), moduleTypeCode));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList().stream().findFirst().orElse(null);
	}
}
