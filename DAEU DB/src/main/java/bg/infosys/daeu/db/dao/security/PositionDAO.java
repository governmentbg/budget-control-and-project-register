package bg.infosys.daeu.db.dao.security;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.security.Position;

@Repository
public class PositionDAO extends GenericDaoImpl<Position, Short> {
	public List<Position> findPositionsByParent(Position parent) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Position> criteria = builder.createQuery(Position.class);
		Root<Position> root = criteria.from(Position.class);
		
		criteria.where(builder.equal(root.get(Position._parent), parent));
		
		return createQuery(criteria).getResultList();
	}
	
	public Position findPositionByName(String name) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Position> criteria = builder.createQuery(Position.class);
		Root<Position> root = criteria.from(Position.class);
		
		criteria.where(builder.equal(root.get(Position._name), name));
		
		return createQuery(criteria).getSingleResult();
	}
}
