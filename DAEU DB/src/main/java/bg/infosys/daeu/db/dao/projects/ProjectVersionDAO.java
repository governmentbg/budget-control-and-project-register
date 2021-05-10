package bg.infosys.daeu.db.dao.projects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.projects.ProjectVersion;

@Repository
public class ProjectVersionDAO extends GenericDaoImpl<ProjectVersion, Integer> {
	public void updateReturnedAtTimestamp(Integer id, String token) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<ProjectVersion> criteria = builder.createCriteriaUpdate(ProjectVersion.class);
		Root<ProjectVersion> root = criteria.from(ProjectVersion.class);
		
		criteria.set(root.get(ProjectVersion._returnedAtTimestamp), token);
		
		criteria.where(builder.equal(root.get(_id), id));
		
		getEntityManager().createQuery(criteria).executeUpdate();
	}
}
