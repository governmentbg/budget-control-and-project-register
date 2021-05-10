package bg.infosys.daeu.db.dao.projects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.ListModelFilter;
import bg.infosys.common.utils.Strings;
import bg.infosys.daeu.db.dto.AppVisualizationDTO;
import bg.infosys.daeu.db.dto.CustomDaoUtils;
import bg.infosys.daeu.db.dto.PublicDetail;
import bg.infosys.daeu.db.dto.SearchDTO;
import bg.infosys.daeu.db.entity.projects.ExecutionData;
import bg.infosys.daeu.db.entity.projects.ExecutionValue;
import bg.infosys.daeu.db.entity.projects.Project;
import bg.infosys.daeu.db.entity.projects.ProjectBudget;
import bg.infosys.daeu.db.entity.projects.ProjectFormConfig;
import bg.infosys.daeu.db.entity.projects.ProjectMainData;
import bg.infosys.daeu.db.entity.projects.ProjectMainDataValue;
import bg.infosys.daeu.db.entity.projects.ProjectVersion;
import bg.infosys.daeu.db.entity.projects.ProjectVersionUser;
import bg.infosys.daeu.db.entity.projects.RowType;
import bg.infosys.daeu.db.entity.pub.ExecutionStatus;
import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;
import bg.infosys.daeu.db.entity.security.User;

@Repository
public class ProjectDAO extends GenericDaoImpl<Project, Integer> {

	public List<Project> findByProperties(SearchDTO so, ListModelFilter filter, List<Status> allowedStatuses, User user) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
		
		if (user == null) {
			Root<Project> projectRoot;
			projectRoot = criteria.from(Project.class);
			
			List<Predicate> projectPredicates;
			projectPredicates = getWhereClause(so, builder, projectRoot);
			projectRoot.fetch(Project._projectVersion, JoinType.LEFT);
			projectRoot.fetch(Project._subjectVersion, JoinType.LEFT);
			projectRoot.fetch(Project._status , JoinType.LEFT);
			projectRoot.fetch(Project._formType, JoinType.LEFT);
			
			if (so.getStatus() == null) {
				List<Predicate> statusPredicates = new ArrayList<Predicate>();
				
				for (Status status : allowedStatuses) {
					statusPredicates.add(builder.equal(projectRoot.get(Project._status), status));
				}
			
				criteria.where(builder.and(builder.and(projectPredicates.toArray(new Predicate[projectPredicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
			} else {
				criteria.where(builder.and(projectPredicates.toArray(new Predicate[projectPredicates.size()])));
			}
			if (filter != null && filter.getOrderBy() != null && filter.getOrderBy().equals("id")) {
				filter.setOrderBy("projectVersion.subjectOutgoingDate");
			}
			CustomDaoUtils.setCriteriaFilterProps(builder, projectRoot, criteria, filter);
			List<Order> order = new ArrayList<>();
			order.addAll(criteria.getOrderList());
			if (filter.isAscending()) {
				order.add(builder.asc(projectRoot.get(Project._projectVersion).get(ProjectVersion._id)));
			} else {
				order.add(builder.desc(projectRoot.get(Project._projectVersion).get(ProjectVersion._id)));
			}
			criteria.orderBy(order);
		} else {
			Root<ProjectVersionUser> verisonUserRoot = criteria.from(ProjectVersionUser.class);
			List<Predicate> versionUserPredicates = new ArrayList<Predicate>();

			Join<ProjectVersionUser, ProjectVersion> projectVersionJoin = verisonUserRoot.join(ProjectVersionUser._projectVersion, JoinType.LEFT);
			Join<ProjectVersion, Project> projectJoin = projectVersionJoin.join(ProjectVersion._project, JoinType.LEFT);
			
			projectJoin.fetch(Project._status, JoinType.LEFT);
			projectJoin.fetch(Project._subjectVersion, JoinType.LEFT);
			projectJoin.fetch(Project._projectVersion, JoinType.LEFT);
			projectJoin.fetch(Project._formType, JoinType.LEFT);
			
			if (so.getSubject() != null) {
				versionUserPredicates.add(builder.equal(projectJoin.get(Project._subjectVersion).get(SubjectVersion._subject), so.getSubject()));
			}

			if (!Strings.isEmpty(so.getOutgoingNumber())) {
				versionUserPredicates.add(builder.like(projectJoin.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingNum), 
						"%" + so.getOutgoingNumber() + "%"));
			}

			if (so.getStatus() != null) {
				versionUserPredicates.add(builder.equal(projectJoin.get(Project._status), so.getStatus()));
			}

			if (so.getStartPeriod() != null) {
				versionUserPredicates.add(builder.greaterThanOrEqualTo(projectJoin.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate), so.getStartPeriod()));
			}

			if (so.getEndPeriod() != null) {
				versionUserPredicates.add(builder.lessThanOrEqualTo(projectJoin.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate), so.getEndPeriod()));
			}
			
			if (so.getType() != null && so.getType().length() == 2) {
				versionUserPredicates.add(builder.equal(projectJoin.get(Project._formType).get(FormType._code), so.getType()));
			}
			
			versionUserPredicates.add(builder.equal(verisonUserRoot.get(ProjectVersionUser._assignee), user));
			versionUserPredicates.add(builder.equal(verisonUserRoot.get(ProjectVersionUser._isValid), "Y"));
		
			if (so.getStatus() == null) {
				List<Predicate> statusPredicates = new ArrayList<Predicate>();
				
				for (Status status : allowedStatuses) {
					statusPredicates.add(builder.equal(projectJoin.get(Project._status), status));
				}
			
				criteria.where(builder.and(builder.and(versionUserPredicates.toArray(new Predicate[versionUserPredicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
			} else {
				criteria.where(builder.and(versionUserPredicates.toArray(new Predicate[versionUserPredicates.size()])));
			}
			
			if (filter != null && filter.getOrderBy() != null && filter.getOrderBy().equals("id")) {
				filter.setOrderBy("projectVersion.subjectOutgoingDate");
			}
			
			String orderBy = filter.getOrderBy();
			
			
			Path<?> orderByPath = null;
			
			for (String token : orderBy.split("\\.")) {
				if (orderByPath == null) {
					orderByPath = projectJoin.get(token);
				} else {
					orderByPath = orderByPath.get(token);
				}
			}
			
			String secondOrder = "projectVersion.id";
			
			Path<?> secondOrderByPath = null;
			
			for (String token : secondOrder.split("\\.")) {
				if (secondOrderByPath == null) {
					secondOrderByPath = projectJoin.get(token);
				} else {
					secondOrderByPath = secondOrderByPath.get(token);
				}
			}

			if (filter.isAscending()) {
				criteria.orderBy(builder.asc(orderByPath),builder.asc(secondOrderByPath)).distinct(true);
			} else {
				criteria.orderBy(builder.desc(orderByPath),builder.desc(secondOrderByPath)).distinct(true);
			}
			
			criteria.multiselect(projectVersionJoin.get(ProjectVersion._project));
		}

		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList()
				.stream().map(tuple -> tuple.get(0, Project.class)).collect(Collectors.toList());
	}
	
	public List<AppVisualizationDTO> findByAllowedStatus(List<Status> allowedStatuses, ListModelFilter filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AppVisualizationDTO> criteria = builder.createQuery(AppVisualizationDTO.class);
		Root<Project> root = criteria.from(Project.class);
		
		criteria.multiselect(root.get(_id), root.get(Project._status), root.get(Project._formType).get(FormType._name),
				root.get(Project._subjectVersion).get(SubjectVersion._fullName), root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate),
				root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingNum));

		List<Predicate> predicates = new ArrayList<>();
		
		for (Status status : allowedStatuses) {
			predicates.add(builder.equal(root.get(Project._status), status));
		}
		criteria.where(builder.or(predicates.toArray(new Predicate[predicates.size()])));
		
		List<Order> order = new ArrayList<>();
		order.add(builder.desc(root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate)));
		order.add(builder.desc(root.get(Project._projectVersion).get(ProjectVersion._id)));
		criteria.orderBy(order);
		
		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList();
	}
	
	public long countByAllowedStatus(List<Status> allowedStatuses) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Project> root = criteria.from(Project.class);
		List<Predicate> predicates = new ArrayList<>();
		for (Status status : allowedStatuses) {
			predicates.add(builder.equal(root.get(Project._status), status));
		}
		criteria.select(builder.count(root));
		criteria.where(builder.or(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}

	public long countByProperties(SearchDTO so, List<Status> allowedStatuses, User user) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

		if (user == null) {
			Root<Project> projectRoot;
			projectRoot = criteria.from(Project.class);
			
			List<Predicate> projectPredicates;
			projectPredicates = getWhereClause(so, builder, projectRoot);
			
			if (so.getStatus() == null) {
				List<Predicate> statusPredicates = new ArrayList<Predicate>();
				
				for (Status status : allowedStatuses) {
					statusPredicates.add(builder.equal(projectRoot.get(Project._status), status));
				}
			
				criteria.where(builder.and(builder.and(projectPredicates.toArray(new Predicate[projectPredicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
			} else {
				criteria.where(builder.and(projectPredicates.toArray(new Predicate[projectPredicates.size()])));
			}
			
			criteria.select(builder.count(projectRoot));
		} else {
			Root<ProjectVersionUser> verisonUserRoot = criteria.from(ProjectVersionUser.class);
			List<Predicate> versionUserPredicates = new ArrayList<Predicate>();

			Join<ProjectVersionUser, ProjectVersion> projectVersionJoin = verisonUserRoot.join(ProjectVersionUser._projectVersion, JoinType.LEFT);
			
			versionUserPredicates.add(builder.equal(verisonUserRoot.get(ProjectVersionUser._assignee), user));
			versionUserPredicates.add(builder.equal(verisonUserRoot.get(ProjectVersionUser._isValid), "Y"));
			
			if (so.getSubject() != null) {
				versionUserPredicates.add(builder.equal(projectVersionJoin.get(ProjectVersion._project).get(Project._subjectVersion).get(SubjectVersion._subject), so.getSubject()));
			}

			if (!Strings.isEmpty(so.getOutgoingNumber())) {
				versionUserPredicates.add(builder.like(projectVersionJoin.get(ProjectVersion._subjectOutgoingNum), 
						"%" + so.getOutgoingNumber() + "%"));
			}

			if (so.getStatus() != null) {
				versionUserPredicates.add(builder.equal(projectVersionJoin.get(ProjectVersion._project).get(Project._status), so.getStatus()));
			}

			if (so.getStartPeriod() != null) {
				versionUserPredicates.add(builder.greaterThanOrEqualTo(projectVersionJoin.get(ProjectVersion._subjectOutgoingDate), so.getStartPeriod()));
			}

			if (so.getEndPeriod() != null) {
				versionUserPredicates.add(builder.lessThanOrEqualTo(projectVersionJoin.get(ProjectVersion._subjectOutgoingDate), so.getEndPeriod()));
			}
			
			if (so.getStatus() == null) {
				List<Predicate> statusPredicates = new ArrayList<Predicate>();
				
				for (Status status : allowedStatuses) {
					statusPredicates.add(builder.equal(projectVersionJoin.get(ProjectVersion._project).get(Project._status), status));
				}
			
				criteria.where(builder.and(builder.and(versionUserPredicates.toArray(new Predicate[versionUserPredicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
			} else {
				criteria.where(builder.and(versionUserPredicates.toArray(new Predicate[versionUserPredicates.size()])));
			}
			
			
			criteria.select(builder.countDistinct(projectVersionJoin.get(ProjectVersion._project)));
		}

		return createQuery(criteria).getSingleResult();
	}

	public List<AppVisualizationDTO> findByProperties(SearchDTO so) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AppVisualizationDTO> criteria = builder.createQuery(AppVisualizationDTO.class);
		Root<Project> root = criteria.from(Project.class);
		List<Predicate> predicates = getWhereClause(so, builder, root);
		criteria.multiselect(root.get(_id), root.get(Project._status), root.get(Project._formType).get(FormType._name),
				root.get(Project._subjectVersion).get(SubjectVersion._fullName), root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate),
				root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingNum));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.desc(root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate)), builder.desc(root.get(Project._projectVersion).get(ProjectVersion._id)));

		return createQuery(criteria).getResultList();
	}

	public Project initFullProjectData(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
		Root<Project> root = criteria.from(Project.class);

		criteria.where(builder.equal(root.get(Project._id), id));
		return createQuery(criteria).getSingleResult();
	}

	private List<Predicate> getWhereClause(SearchDTO so, CriteriaBuilder builder, Root<Project> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!Strings.isEmpty(so.getFormattedTypeForProject())) {
			predicates.add(builder.equal(root.get(Project._formType).get(FormType._code), so.getFormattedTypeForProject()));
		}

		if (so.getSubject() != null) {
			predicates.add(builder.equal(root.get(Project._subjectVersion).get(SubjectVersion._subject), so.getSubject()));
		}

		if (!Strings.isEmpty(so.getOutgoingNumber())) {
			predicates.add(builder.like(builder.upper(root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingNum)), "%" + so.getOutgoingNumber().toUpperCase() + "%"));
		}

		if (so.getStatus() != null) {
			predicates.add(builder.equal(root.get(Project._status), so.getStatus()));
		}

		if (so.getStartPeriod() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate), so.getStartPeriod()));
		}

		if (so.getEndPeriod() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate), so.getEndPeriod()));
		}
		return predicates;
	}

	public List<AppVisualizationDTO> findByStatus(String status) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AppVisualizationDTO> criteria = builder.createQuery(AppVisualizationDTO.class);
		Root<Project> root = criteria.from(Project.class);
		
		criteria.multiselect(root.get(Project._projectVersion));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(Project._status).get(Status._code), status));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate)));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<PublicDetail> getProjectMainDataForDeployment(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<PublicDetail> criteria = builder.createQuery(PublicDetail.class);
		Root<Project> root = criteria.from(Project.class);
		Join<Project, ProjectVersion> projectVersionJoin = root.join(ProjectBudget._projectVersion, JoinType.LEFT);
		Join<ProjectVersion, ProjectMainData> projectMainDataJoin = projectVersionJoin.join(ProjectVersion._projectMainData, JoinType.LEFT);
		Join<ProjectMainData, ProjectMainDataValue> projectMainDataValueJoin = projectMainDataJoin.join(ProjectMainData._mainDataValues, JoinType.LEFT);
		
		criteria.multiselect(projectMainDataValueJoin.get(ProjectMainDataValue._projectFormConfig).get(ProjectFormConfig._rowLabel),
				projectMainDataValueJoin.get(ProjectMainDataValue._value),
				projectMainDataValueJoin.get(ProjectMainDataValue._projectFormConfig).get(ProjectFormConfig._rowType).get(RowType._code));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(GenericDaoImpl._id), id));
		predicates.add(builder.isNotNull(projectMainDataValueJoin.get(ProjectMainDataValue._projectFormConfig).get(ProjectFormConfig._rowLabel)));
		
		predicates.add(builder.equal(projectMainDataValueJoin.get(ProjectMainDataValue._projectFormConfig).get(ProjectFormConfig._isDeployableField), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<PublicDetail> getProjectExecutionDataForDeployment(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<PublicDetail> criteria = builder.createQuery(PublicDetail.class);
		Root<Project> root = criteria.from(Project.class);
		Join<Project, ProjectVersion> projectVersionJoin = root.join(ProjectBudget._projectVersion, JoinType.LEFT);
		Join<ProjectVersion, ProjectMainData> projectMainDataJoin = projectVersionJoin.join(ProjectVersion._projectMainData, JoinType.LEFT);
		Join<ProjectMainData, ExecutionData> executionDataJoin = projectMainDataJoin.join(ProjectMainData._executionDatas, JoinType.LEFT);
		Join<ExecutionData, ExecutionValue> executionValueJoin = executionDataJoin.join(ExecutionData._executionValues, JoinType.LEFT);
		
		criteria.multiselect(executionValueJoin.get(ExecutionValue._projectFormConfig).get(ProjectFormConfig._rowLabel),
				executionValueJoin.get(ExecutionValue._value), executionValueJoin.get(ExecutionValue._executionData).get(_id),
				executionValueJoin.get(ExecutionValue._projectFormConfig).get(ProjectFormConfig._rowType).get(RowType._code));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(GenericDaoImpl._id), id));
		predicates.add(builder.isNotNull(executionValueJoin.get(ExecutionValue._projectFormConfig).get(ProjectFormConfig._rowLabel)));
		
		predicates.add(builder.equal(executionValueJoin.get(ExecutionValue._projectFormConfig).get(ProjectFormConfig._isValid), "Y"));
		predicates.add(builder.equal(executionValueJoin.get(ExecutionValue._projectFormConfig).get(ProjectFormConfig._isDeployableField), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
	
	public String getProjectNameById(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<Project> root = criteria.from(Project.class);
		Join<Project, ProjectVersion> projectVersionJoin = root.join(ProjectBudget._projectVersion, JoinType.LEFT);
		Join<ProjectVersion, ProjectMainData> projectMainDataJoin = projectVersionJoin.join(ProjectVersion._projectMainData, JoinType.LEFT);
		Join<ProjectMainData, ProjectMainDataValue> projectMainDataValueJoin = projectMainDataJoin.join(ProjectMainData._mainDataValues, JoinType.LEFT);
		
		criteria.multiselect(projectMainDataValueJoin.get(ProjectMainDataValue._value));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(GenericDaoImpl._id), id));
		predicates.add(builder.equal(projectMainDataValueJoin.get(ProjectMainDataValue._projectFormConfig).get(ProjectFormConfig._rowType).get(RowType._code), "NAMETXT"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public long countByFinalStatusAndPeriod(List<Status> finalStatuses, Date startPeriod, Date endPeriod) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Project> root = criteria.from(Project.class);
		List<Predicate> predicates = new ArrayList<>();
		for (Status status : finalStatuses) {
			predicates.add(builder.equal(root.get(Project._finalStatus), status));
		}
		List<Predicate> periodPredicates = new ArrayList<>();
		if (startPeriod != null) {
			periodPredicates.add(builder.greaterThanOrEqualTo(root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate), startPeriod));
		}

		if (endPeriod != null) {
			periodPredicates.add(builder.lessThanOrEqualTo(root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate), endPeriod));
		}
		criteria.select(builder.count(root));
		criteria.where(builder.and(builder.and(periodPredicates.toArray(new Predicate[periodPredicates.size()])),builder.or(predicates.toArray(new Predicate[predicates.size()]))));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public long countByAllowedStatusAndPeriod(List<Status> allowedStatuses, Date startPeriod, Date endPeriod) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Project> root = criteria.from(Project.class);
		List<Predicate> predicates = new ArrayList<>();
		for (Status status : allowedStatuses) {
			predicates.add(builder.equal(root.get(Project._status), status));
		}
		List<Predicate> periodPredicates = new ArrayList<>();
		if (startPeriod != null) {
			periodPredicates.add(builder.greaterThanOrEqualTo(root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate), startPeriod));
		}

		if (endPeriod != null) {
			periodPredicates.add(builder.lessThanOrEqualTo(root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate), endPeriod));
		}
		criteria.select(builder.count(root));
		criteria.where(builder.and(builder.and(periodPredicates.toArray(new Predicate[periodPredicates.size()])),builder.or(predicates.toArray(new Predicate[predicates.size()]))));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public long countByExecutionStatus(ExecutionStatus status) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Project> root = criteria.from(Project.class);
		criteria.select(builder.count(root));
		criteria.where(builder.equal(root.get(Project._executionStatus), status));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public List<Project> findReportsByProperties(SearchDTO so) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
		Root<Project> root = criteria.from(Project.class);
		Join<Project, ProjectVersion> versionJoin = root.join(Project._projectVersion, JoinType.LEFT);
		Join<ProjectVersion, ProjectMainData> mainDataJoin = versionJoin.join(ProjectVersion._projectMainData, JoinType.LEFT);
		Join<ProjectMainData, ProjectMainDataValue> mainDataValueJoin = mainDataJoin.join(ProjectMainData._mainDataValues, JoinType.LEFT);
		Join<ProjectMainDataValue, ProjectFormConfig> formConfigJoin = mainDataValueJoin.join(ProjectMainDataValue._projectFormConfig, JoinType.LEFT);
		Join<ProjectFormConfig, RowType> rowTypeJoin = formConfigJoin.join(ProjectFormConfig._rowType, JoinType.LEFT);
		List<Predicate> predicates = getReportWhereClause(so, builder, root);
		List<Predicate> statusPredicates = new ArrayList<Predicate>();
		List<Predicate> yearsPredicates = new ArrayList<Predicate>();
		if (!so.getSelectedYears().isEmpty()) {
			predicates.add(builder.equal(rowTypeJoin.get(RowType._code), "MULTICOMBOYEAR"));
			for (Integer year : so.getSelectedYears()) {
				yearsPredicates.add(builder.like(mainDataValueJoin.get(ProjectMainDataValue._value), "%" + year + "%"));
			}
		}
		if (so.getSelectedStatuses() != null && !so.getSelectedStatuses().isEmpty()) {
			for (Status status : so.getSelectedStatuses()) {
				if (status.getIsFinalStage()) {
					statusPredicates.add(builder.equal(root.get(Project._finalStatus), status));
				} else {
					statusPredicates.add(builder.equal(root.get(Project._status), status));
				}
			}
			if (!so.getSelectedYears().isEmpty()) {
				criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()])),
						builder.or(yearsPredicates.toArray(new Predicate[yearsPredicates.size()])))).distinct(true);
			} else {
				criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()])))).distinct(true);
			}
		} else {
			if (!so.getSelectedYears().isEmpty()) {
				criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.or(yearsPredicates.toArray(new Predicate[yearsPredicates.size()])))).distinct(true);
			} else {
				criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).distinct(true);
			}
		}
		
		
		return createQuery(criteria).getResultList();
	}
	
	private List<Predicate> getReportWhereClause(SearchDTO so, CriteriaBuilder builder, Root<Project> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!Strings.isEmpty(so.getFormattedTypeForProject())) {
			predicates.add(builder.equal(root.get(Project._formType).get(FormType._code), so.getFormattedTypeForProject()));
		}

		if (so.getSubject() != null) {
			predicates.add(builder.equal(root.get(Project._subjectVersion).get(SubjectVersion._subject), so.getSubject()));
		}

		if (so.getSelectedYears().isEmpty()) {
			if (so.getStartPeriod() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate), so.getStartPeriod()));
			}
	
			if (so.getEndPeriod() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get(Project._projectVersion).get(ProjectVersion._subjectOutgoingDate), so.getEndPeriod()));
			}
		}
		
		if (so.getExecutionStatus() != null) {
			predicates.add(builder.equal(root.get(Project._executionStatus), so.getExecutionStatus()));
		}
		
		if (so.getAppVizualizationDto() != null && so.getAppVizualizationDto().getId() != null) {
			predicates.add(builder.equal(root.get(Project._id), so.getAppVizualizationDto().getId()));
		}
		return predicates;
	}
	
	public String getResourceURI(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<Project> root = criteria.from(Project.class);
		
		criteria.select(root.get(Project._projectVersion).get(ProjectVersion._openDataURI));
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(Project._projectVersion).get(ProjectVersion._id), id));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
	
	public Project getByGuid(String guid) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Project> criteria = builder.createQuery(Project.class);
		Root<Project> root = criteria.from(Project.class);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(Project._guid), guid));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		try {
			return createQuery(criteria).getSingleResult();			
		} catch (NoResultException e) {
			return null;
		}
	}
}
