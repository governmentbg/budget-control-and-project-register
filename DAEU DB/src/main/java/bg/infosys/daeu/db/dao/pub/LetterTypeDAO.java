package bg.infosys.daeu.db.dao.pub;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.daeu.db.entity.pub.LetterType;
import bg.infosys.daeu.db.entity.pub.ModuleType;

@Repository
public class LetterTypeDAO extends GenericDaoImpl<LetterType, Integer> {

	public List<LetterType> findAllValidTypes(ModuleType module) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<LetterType> criteria = builder.createQuery(LetterType.class);
		Root<LetterType> root = criteria.from(LetterType.class);
		
		root.fetch(LetterType._finalStatus, JoinType.LEFT);
		
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(LetterType._isValid), "Y"));
		r.add(builder.equal(root.get(LetterType._moduleType), module));
		r.apply(builder, root, criteria, true);
		
		return createQuery(criteria).getResultList();
	}
	
	public List<LetterType> findAllTypes(ModuleType module) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<LetterType> criteria = builder.createQuery(LetterType.class);
		Root<LetterType> root = criteria.from(LetterType.class);
		
		root.fetch(LetterType._finalStatus, JoinType.LEFT);
		
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(LetterType._moduleType), module));
		r.apply(builder, root, criteria, true);
		
		return createQuery(criteria).getResultList();
	}

}
