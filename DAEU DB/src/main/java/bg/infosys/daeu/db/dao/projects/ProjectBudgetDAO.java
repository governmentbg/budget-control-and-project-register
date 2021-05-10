package bg.infosys.daeu.db.dao.projects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.dto.ProjectsIntegrationDto;
import bg.infosys.daeu.db.entity.budgets.SumBudgetFormType.SumBudgetFormTypeConst;
import bg.infosys.daeu.db.entity.projects.Project;
import bg.infosys.daeu.db.entity.projects.ProjectBudget;
import bg.infosys.daeu.db.entity.projects.ProjectBudgetType;
import bg.infosys.daeu.db.entity.projects.ProjectBudgetType.BudgetTypeConst;
import bg.infosys.daeu.db.entity.projects.ProjectFormConfig;
import bg.infosys.daeu.db.entity.projects.ProjectMainData;
import bg.infosys.daeu.db.entity.projects.ProjectMainDataValue;
import bg.infosys.daeu.db.entity.projects.ProjectVersion;
import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.pub.Status.StatusConst;
import bg.infosys.daeu.db.entity.pub.Subject;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;

@Repository
public class ProjectBudgetDAO extends GenericDaoImpl<ProjectBudget, Integer> {

	public List<ProjectsIntegrationDto> findTotalBudgetForPeriod(Subject subject, List<String> years, List<ProjectMainData> mainDatas, String sumBudgetFormType, Integer projectId) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<ProjectsIntegrationDto> criteria = builder.createQuery(ProjectsIntegrationDto.class);
		Root<ProjectBudget> root = criteria.from(ProjectBudget.class);
		Join<ProjectBudget, ProjectVersion> projectVersionJoin = root.join(ProjectBudget._projectVersion, JoinType.LEFT);
		Join<ProjectVersion, ProjectMainData> projectMainDataJoin = projectVersionJoin.join(ProjectVersion._projectMainData, JoinType.LEFT);
		Join<ProjectMainData, ProjectMainDataValue> projectMainDataValueJoin = projectMainDataJoin.join(ProjectMainData._mainDataValues, JoinType.LEFT);
		
		List<Predicate> predicates = new ArrayList<>();
		criteria.multiselect(root.get(ProjectBudget._value), root.get(ProjectBudget._projectVersion).get(ProjectVersion._project).get(Project._formType).get(FormType._name),
			root.get(ProjectBudget._projectVersion).get(ProjectVersion._subjectOutgoingDate), root.get(ProjectBudget._projectVersion).get(ProjectVersion._subjectOutgoingNum),
					root.get(ProjectBudget._projectVersion).get(ProjectVersion._project).get(Project._status), projectMainDataValueJoin.get(ProjectMainDataValue._value));
		
		if (projectId != null) {
			predicates.add(builder.notEqual(root.get(ProjectBudget._projectVersion).get(ProjectVersion._project).get(_id), projectId));
		}
		predicates.add(builder.equal(root.get(ProjectBudget._projectBudgetType).get(ProjectBudgetType._code), BudgetTypeConst.BUDGET.getCode()));
		predicates.add(builder.notEqual(root.get(ProjectBudget._projectVersion)
			.get(ProjectVersion._project).get(Project._status).get(Status._code), StatusConst.NOT_APPROVED.name()));
		predicates.add(builder.equal(projectMainDataValueJoin.get(ProjectMainDataValue._projectFormConfig).get(ProjectFormConfig._rowLabel), "Период на изпълнение"));//FIXME check this string
		predicates.add(builder.equal(root.get(ProjectBudget._projectVersion).get(ProjectVersion._project).get(Project._projectVersion), root.get(ProjectBudget._projectVersion)));
		
		List<Predicate> yearPredicates = new ArrayList<>();
		for (String year : years) {
			yearPredicates.add(builder.like(projectMainDataValueJoin.get(ProjectMainDataValue._value), "%" + year + "%"));
		}
		List<Predicate> mainDataPredicates = new ArrayList<>();
		if (mainDatas != null) {
			for (ProjectMainData mainData : mainDatas) {
				mainDataPredicates.add(builder.equal(projectVersionJoin.get(ProjectVersion._projectMainData), mainData));
			}
			List<Predicate> subjectPredicates = new ArrayList<>();
			subjectPredicates.add(builder.equal(root.get(ProjectBudget._projectVersion).get(ProjectVersion._project).get(Project._subjectVersion).get(SubjectVersion._subject), subject));
			if (sumBudgetFormType != null && sumBudgetFormType.equals(SumBudgetFormTypeConst.TOTAL.getCode()) && subject.getLastVersion().getChildren() != null && !subject.getLastVersion().getChildren().isEmpty()) {
				for (SubjectVersion version : subject.getLastVersion().getChildren()) {
					subjectPredicates.add(builder.equal(root.get(ProjectBudget._projectVersion).get(ProjectVersion._project).get(Project._subjectVersion).get(SubjectVersion._subject), version.getSubject()));
				}
			} 
			
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])), builder.or(yearPredicates.toArray(new Predicate[yearPredicates.size()])), 
					builder.or(mainDataPredicates.toArray(new Predicate[mainDataPredicates.size()])), 
							builder.or(subjectPredicates.toArray(new Predicate[subjectPredicates.size()])));
		} else {
			predicates.add(builder.equal(root.get(ProjectBudget._projectVersion).get(ProjectVersion._project).get(Project._subjectVersion).get(SubjectVersion._subject), subject));
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])), builder.or(yearPredicates.toArray(new Predicate[yearPredicates.size()])));
		}
		
		criteria.orderBy(builder.desc(root.get(ProjectBudget._projectVersion).get(ProjectVersion._subjectOutgoingDate)));

		return createQuery(criteria).getResultList();
	}
}
