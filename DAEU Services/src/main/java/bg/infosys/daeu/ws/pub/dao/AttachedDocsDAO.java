package bg.infosys.daeu.ws.pub.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import bg.infosys.daeu.ws.pub.entity.AttachedDoc;

@Repository
public class AttachedDocsDAO {
	public static String ID = "id";
	
	@PersistenceContext(unitName = "secondaryEntityManager")
	private EntityManager entityManager;
	
	public AttachedDoc saveOrUpdate(AttachedDoc entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
}
