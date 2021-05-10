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
import bg.infosys.daeu.db.entity.pub.MailTemplate;
import bg.infosys.daeu.db.entity.pub.MailType;
import bg.infosys.daeu.db.entity.pub.ModuleType;

@Repository
public class MailTemplateDAO extends GenericDaoImpl<MailTemplate, Integer> {

	public MailTemplate findTemplateByModuleTypeAndDescription(String moduleType, String description) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<MailTemplate> criteria = builder.createQuery(MailTemplate.class);
		Root<MailTemplate> root = criteria.from(MailTemplate.class);
		
		root.fetch(MailTemplate._type, JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(MailTemplate._type).get(MailType._moduleType).get(ModuleType._code), moduleType));
		predicates.add(builder.equal(root.get(MailTemplate._type).get(MailType._description), description));
		predicates.add(builder.equal(root.get(MailTemplate._isValid), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public List<MailTemplate> getEmailTemplatesByModuleType(ModuleType moduleType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<MailTemplate> criteria = builder.createQuery(MailTemplate.class);
		Root<MailTemplate> root = criteria.from(MailTemplate.class);
		
		root.fetch(MailTemplate._type, JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(MailTemplate._type).get(MailType._moduleType), moduleType));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
}
