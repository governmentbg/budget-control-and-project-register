package bg.infosys.daeu.db.dao.projects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.projects.ProjectFormConfig;
import bg.infosys.daeu.db.entity.projects.ProjectNomenclatures;
import bg.infosys.daeu.db.entity.projects.RowType;

@Repository
public class ProjectNomenclaturesDAO extends GenericDaoImpl<ProjectNomenclatures, Integer> {
	
	public List<ProjectNomenclatures> findAllByConfig(ProjectFormConfig config) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectNomenclatures> criteria = builder.createQuery(ProjectNomenclatures.class);
		Root<ProjectNomenclatures> root = criteria.from(ProjectNomenclatures.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.join(ProjectNomenclatures._projectRowType).get(RowType._code), config.getRowType().getCode()));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getResultList();
	}

}
