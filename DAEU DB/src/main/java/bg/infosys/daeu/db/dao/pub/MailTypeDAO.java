package bg.infosys.daeu.db.dao.pub;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.pub.MailType;
import bg.infosys.daeu.db.entity.pub.ModuleType;

@Repository
public class MailTypeDAO extends GenericDaoImpl<MailType, Integer> {

	public List<MailType> findAllTypes(ModuleType module) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<MailType> criteria = builder.createQuery(MailType.class);
		Root<MailType> root = criteria.from(MailType.class);
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(MailType._moduleType), module));
		r.apply(builder, root, criteria, true);
		
		return createQuery(criteria).getResultList();
	}
}
