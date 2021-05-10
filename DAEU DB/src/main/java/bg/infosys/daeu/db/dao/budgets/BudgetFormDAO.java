package bg.infosys.daeu.db.dao.budgets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.ListModelFilter;
import bg.infosys.common.db.dao.aux.Restrictions;
import bg.infosys.common.utils.Strings;
import bg.infosys.daeu.db.dto.AppVisualizationDTO;
import bg.infosys.daeu.db.dto.CustomDaoUtils;
import bg.infosys.daeu.db.dto.PublicBudgetAdditionalField;
import bg.infosys.daeu.db.dto.SearchDTO;
import bg.infosys.daeu.db.entity.budgets.BudgetForm;
import bg.infosys.daeu.db.entity.budgets.BudgetFormType;
import bg.infosys.daeu.db.entity.budgets.BudgetFormType.BudgetFormTypeConst;
import bg.infosys.daeu.db.entity.budgets.BudgetFormVersion;
import bg.infosys.daeu.db.entity.budgets.BudgetFormVersionUser;
import bg.infosys.daeu.db.entity.budgets.BudgetMainData;
import bg.infosys.daeu.db.entity.budgets.BudgetMainDataValue;
import bg.infosys.daeu.db.entity.budgets.FormColumnType;
import bg.infosys.daeu.db.entity.budgets.FormConfig;
import bg.infosys.daeu.db.entity.budgets.SumBudgetFormType;
import bg.infosys.daeu.db.entity.budgets.SumBudgetFormType.SumBudgetFormTypeConst;
import bg.infosys.daeu.db.entity.budgets.VersionRow;
import bg.infosys.daeu.db.entity.pub.Checklist;
import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.db.entity.pub.FormType.FormTypeConst;
import bg.infosys.daeu.db.entity.pub.Letter;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.pub.Status.StatusConst;
import bg.infosys.daeu.db.entity.pub.Subject;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;

@Repository
public class BudgetFormDAO extends GenericDaoImpl<BudgetForm, Integer> {

	public List<BudgetForm> findByProperties(SearchDTO searchObject, ListModelFilter filter, List<Status> allowedStatuses) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		List<Predicate> predicates = getWhereClause(searchObject, builder, root);
		root.fetch(BudgetForm._formType, JoinType.LEFT);
		root.fetch(BudgetForm._status, JoinType.LEFT);
		root.fetch(BudgetForm._budgetFormType, JoinType.LEFT);
		root.fetch(BudgetForm._sumBudgetFormType, JoinType.LEFT);
		root.fetch(BudgetForm._budgetFormVersion, JoinType.LEFT);
		root.fetch(BudgetForm._subjectVersion, JoinType.LEFT);
		
		if (searchObject.getStatus() == null) {
			List<Predicate> statusPredicates = new ArrayList<Predicate>();
			
			for (Status status : allowedStatuses) {
				statusPredicates.add(builder.equal(root.get(BudgetForm._status), status));
			}
		
			criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
		} else {
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		}
		if (filter != null && filter.getOrderBy() != null && filter.getOrderBy().equals("id")) {
			filter.setOrderBy("budgetFormVersion.subjectOutgoingDate");
		}
		CustomDaoUtils.setCriteriaFilterProps(builder, root, criteria, filter);
		List<Order> order = new ArrayList<>();
		order.addAll(criteria.getOrderList());
		if (filter.isAscending()) {
			order.add(builder.asc(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._id)));
		} else {
			order.add(builder.desc(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._id)));
		}
		criteria.orderBy(order);
		
		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList()
				.stream().map(tuple -> tuple.get(0, BudgetForm.class)).collect(Collectors.toList());
	}

	public long countByProperties(SearchDTO searchObject, List<Status> allowedStatuses) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		List<Predicate> predicates = getWhereClause(searchObject,builder,root);
		criteria.select(builder.count(root));
		
		if (searchObject.getStatus() == null) {
			List<Predicate> statusPredicates = new ArrayList<Predicate>();
			
			for (Status status : allowedStatuses) {
				statusPredicates.add(builder.equal(root.get(BudgetForm._status), status));
			}
		
			criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
		} else {
			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		}
		
		return createQuery(criteria).getSingleResult();
	}

	private List<Predicate> getWhereClause(SearchDTO searchObject, CriteriaBuilder builder, Root<BudgetForm> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(searchObject.getFormType() != null && !Strings.isEmpty(searchObject.getFormType().getCode())) {
			predicates.add(builder.equal(root.get(BudgetForm._formType), searchObject.getFormType()));
		} else {
			predicates.add(builder.or(builder.equal(root.get(BudgetForm._formType).get(FormType._code), FormTypeConst.APP_TWO.getCode()), 
					builder.equal(root.get(BudgetForm._formType).get(FormType._code), FormTypeConst.APP_ONE.getCode())));
		}
		if (searchObject.getSubjectList() != null && !searchObject.getSubjectList().isEmpty()) {
			Expression<String> subj = root.get(BudgetForm._subjectVersion).get(SubjectVersion._subject);
			List<Integer> subjIds = new ArrayList<Integer>();
			for (Subject s : searchObject.getSubjectList()) {
				subjIds.add(s.getId());
			}
			predicates.add(subj.in(subjIds));
		}
		if(!Strings.isEmpty(searchObject.getOutgoingNumber())) {
			predicates.add(builder.like(builder.upper(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._subjectOutgoingNum)), "%" + searchObject.getOutgoingNumber().toUpperCase() + "%"));
		}
		if (searchObject.getStatus() != null) {
			predicates.add(builder.equal(root.get(BudgetForm._status), searchObject.getStatus()));
		}
		if (searchObject.getStartPeriod() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._subjectOutgoingDate), searchObject.getStartPeriod()));
		}
		if (searchObject.getEndPeriod() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._subjectOutgoingDate), searchObject.getEndPeriod()));
		}
		if (searchObject.isTotal()) {
			predicates.add(builder.equal(root.get(BudgetForm._sumBudgetFormType).get(SumBudgetFormType._code), SumBudgetFormTypeConst.TOTAL.getCode()));
		} else {
			predicates.add(builder.equal(root.get(BudgetForm._sumBudgetFormType).get(SumBudgetFormType._code), SumBudgetFormTypeConst.NOT_TOTAL.getCode()));
		}
		
		return predicates;
	}

	public BudgetFormVersion getBudgetFormByParentBudgetFormVersion(BudgetFormVersion version) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetFormVersion> criteria = builder.createQuery(BudgetFormVersion.class);
		Root<BudgetFormVersion> root = criteria.from(BudgetFormVersion.class);
		root.fetch(BudgetFormVersion._budgetForm);
		criteria.where(builder.equal(root.get(BudgetFormVersion._parent), version));
		return createQuery(criteria).getSingleResult();
	}

	public List<AppVisualizationDTO> findByProperties(SearchDTO searchObject) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AppVisualizationDTO> criteria = builder.createQuery(AppVisualizationDTO.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		List<Predicate> predicates = getWhereClause(searchObject, builder, root);
		
		criteria.multiselect(root.get(_id), root.get(BudgetForm._status), root.get(BudgetForm._formType).get(FormType._name),
				root.get(BudgetForm._subjectVersion).get(SubjectVersion._fullName), root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._subjectOutgoingDate),
					root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._subjectOutgoingNum));

		if(searchObject.getFormattedTypeForBudget() != null) {
			predicates.add(builder.equal(root.get(BudgetForm._formType).get(FormType._code), searchObject.getFormattedTypeForBudget()));
		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.desc(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._subjectOutgoingDate)), builder.desc(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._id)));
		
		return createQuery(criteria).getResultList();
	}

	public List<AppVisualizationDTO> findByAllowedStatus(List<Status> allowedStatuses, ListModelFilter filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AppVisualizationDTO> criteria = builder.createQuery(AppVisualizationDTO.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);

		criteria.multiselect(root.get(_id), root.get(BudgetForm._status),
				root.get(BudgetForm._formType).get(FormType._name),
				root.get(BudgetForm._subjectVersion).get(SubjectVersion._fullName),
				root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._subjectOutgoingDate), root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._subjectOutgoingNum));

		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> statusPredicates = new ArrayList<Predicate>();
		for (Status status : allowedStatuses) {
			statusPredicates.add(builder.equal(root.get(BudgetForm._status), status));
		}
		
		predicates.add(builder.isNull(root.get(BudgetFormVersionUser._budgetFormVersion).get(BudgetFormVersion._parent)));
		criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
		
		List<Order> order = new ArrayList<>();
		order.add(builder.desc(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._subjectOutgoingDate)));
		order.add(builder.desc(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._id)));
		criteria.orderBy(order);
		

		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList();
	}
	
	public long countByAllowedStatus(List<Status> allowedStatuses) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> statusPredicates = new ArrayList<Predicate>();
		for (Status status : allowedStatuses) {
			statusPredicates.add(builder.equal(root.get(BudgetForm._status), status));
		}
		predicates.add(builder.isNull(root.get(BudgetFormVersionUser._budgetFormVersion).get(BudgetFormVersion._parent)));
		criteria.select(builder.count(root));
		criteria.where(builder.and(builder.and(predicates.toArray(new Predicate[predicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));

		return createQuery(criteria).getSingleResult();
	}

	public BudgetForm initFullBudgetFormData(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetForm> criteria = builder.createQuery(BudgetForm.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		root.fetch(BudgetForm._formType, JoinType.LEFT);
		root.fetch(BudgetForm._status, JoinType.LEFT);
		root.fetch(BudgetForm._finalStatus, JoinType.LEFT);
		root.fetch(BudgetForm._subjectVersion, JoinType.LEFT);
		root.fetch(BudgetForm._budgetFormType, JoinType.LEFT);
		root.fetch(BudgetForm._sumBudgetFormType, JoinType.LEFT);
		Fetch<Object, Object> budgetFormVersionFetch = root.fetch(BudgetForm._budgetFormVersion, JoinType.LEFT);
		budgetFormVersionFetch.fetch(BudgetFormVersion._budgetMainData, JoinType.LEFT)
		.fetch(BudgetMainData._budgetMainDataValues, JoinType.LEFT).fetch(BudgetMainDataValue._budgetMainDataInfo, JoinType.LEFT);
		budgetFormVersionFetch.fetch(BudgetFormVersion._notes, JoinType.LEFT);
		budgetFormVersionFetch.fetch(BudgetFormVersion._parent, JoinType.LEFT);
		budgetFormVersionFetch.fetch(BudgetFormVersion._createdBy, JoinType.LEFT);
		budgetFormVersionFetch.fetch(BudgetFormVersion._letter, JoinType.LEFT).fetch(Letter._template, JoinType.LEFT);
		Fetch<Object, Object> childChecklistFetch = budgetFormVersionFetch.fetch(BudgetFormVersion._checklist, JoinType.LEFT);
		childChecklistFetch.fetch(Checklist._checklistType, JoinType.LEFT);
		childChecklistFetch.fetch(Checklist._checklistStatus, JoinType.LEFT);
		criteria.where(builder.equal(root.get(BudgetForm._id), id));

		return createQuery(criteria).getSingleResult();
	}
	
	public BudgetForm getPreviousBudgetForm(BudgetForm budgetForm, boolean hasPrevApproved) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetForm> criteria = builder.createQuery(BudgetForm.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		Restrictions r = new Restrictions();
		
		if (budgetForm.getFormType().getCode().equals(FormTypeConst.APP_ONE.getCode())) {
			r.add(builder.equal(root.get(BudgetForm._budgetFormType).get(BudgetFormType._code), BudgetFormTypeConst.FORECAST.getCode()));
		} else if (budgetForm.getFormType().getCode().equals(FormTypeConst.APP_TWO.getCode())) {
			if (hasPrevApproved) {
				r.add(builder.equal(root.get(BudgetForm._formType).get(FormType._code), budgetForm.getFormType().getCode()));
			} else {
				r.add(builder.equal(root.get(BudgetForm._budgetFormType).get(BudgetFormType._code), BudgetFormTypeConst.DRAFT_BUDGET.getCode()));
			}
		}
		if (budgetForm.getSumBudgetFormType().getCode().equals(SumBudgetFormTypeConst.TOTAL.getCode())) {
			r.add(builder.or(builder.equal(root.get(BudgetForm._finalStatus).get(Status._code), StatusConst.APPROVED.name()), 
					builder.equal(root.get(BudgetForm._finalStatus).get(Status._code), StatusConst.APPROVED.name())));
		} else if (budgetForm.getSumBudgetFormType().getCode().equals(SumBudgetFormTypeConst.NOT_TOTAL.getCode())) {
			r.add(builder.notEqual(root.get(BudgetForm._status).get(Status._code), StatusConst.NEW.name()));
		}
		r.add(builder.equal(root.get(BudgetForm._sumBudgetFormType).get(SumBudgetFormType._code), budgetForm.getSumBudgetFormType().getCode()));
		if (budgetForm.getSubjectVersion().getSubject() != null) {
			r.add(builder.equal(root.get(BudgetForm._subjectVersion).get(SubjectVersion._subject), budgetForm.getSubjectVersion().getSubject()));
		}
		r.add(builder.equal(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._isEmpty), false));
		criteria.orderBy(builder.desc(root.get(BudgetFormVersion._id)));
		r.apply(builder, root, criteria, true);
		
		try {
		    return (BudgetForm)createQuery(criteria).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
		    return null;
		}
	}

	public List<BudgetForm> findAllChildrenBudgetForms(BudgetForm budgetForm) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetForm> criteria = builder.createQuery(BudgetForm.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		root.fetch(BudgetForm._formType, JoinType.LEFT);
		root.fetch(BudgetForm._status, JoinType.LEFT);
		root.fetch(BudgetForm._budgetFormType, JoinType.LEFT);
		root.fetch(BudgetForm._sumBudgetFormType, JoinType.LEFT);
		root.fetch(BudgetForm._budgetFormVersion, JoinType.LEFT);
		root.fetch(BudgetForm._subjectVersion, JoinType.LEFT);
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(BudgetForm._formType).get(FormType._code), budgetForm.getFormType().getCode()));
		r.add(builder.or(builder.equal(root.get(BudgetForm._subjectVersion).get(SubjectVersion._id), budgetForm.getSubjectVersion().getId()), 
				builder.equal(root.get(BudgetForm._subjectVersion).get(SubjectVersion._parent), budgetForm.getSubjectVersion().getId())));
		if (budgetForm.getFormType().getCode().equals(FormTypeConst.APP_ONE.getCode()) && budgetForm.getBudgetFormType() != null) {
			r.add(builder.equal(root.get(BudgetForm._budgetFormType).get(BudgetFormType._code), budgetForm.getBudgetFormType().getCode()));
		}
		r.add(builder.notEqual(root.get(BudgetForm._status).get(Status._code), StatusConst.NEW.name()));
		r.add(builder.equal(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._isEmpty), false));
		r.add(builder.equal(root.get(BudgetForm._sumBudgetFormType).get(SumBudgetFormType._code), SumBudgetFormTypeConst.NOT_TOTAL.getCode()));
		if (budgetForm.getStartYear() != null) {
			r.add(builder.equal(root.get(BudgetForm._startYear), budgetForm.getStartYear()));
		}
		r.apply(builder, root, criteria, true);
		return createQuery(criteria).getResultList();
	}

	public BudgetForm getlastApprovedBudgetForm(SubjectVersion subjectVersion, FormType formType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetForm> criteria = builder.createQuery(BudgetForm.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		Restrictions r = new Restrictions();
		r.add(builder.equal(root.get(BudgetForm._formType).get(FormType._code), FormTypeConst.APP_TWO.getCode()));
		if (subjectVersion != null) {
			r.add(builder.equal(root.get(BudgetForm._subjectVersion), subjectVersion));
		}
		if (formType != null) {
			r.add(builder.equal(root.get(BudgetForm._formType), formType));
		}
		criteria.orderBy(builder.desc(root.get(BudgetFormVersion._id)));
		r.apply(builder, root, criteria, true);
		
		try {
		    return createQuery(criteria).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
		    return null;
		}
	}

	public List<AppVisualizationDTO> findByStatus(String status) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AppVisualizationDTO> criteria = builder.createQuery(AppVisualizationDTO.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		
		criteria.multiselect(root.get(BudgetFormVersionUser._budgetFormVersion));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(BudgetForm._status).get(Status._code), status));
		predicates.add(builder.isNull(root.get(BudgetFormVersionUser._budgetFormVersion).get(BudgetFormVersion._parent)));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._subjectOutgoingDate)));
		
		return createQuery(criteria).getResultList();
	}

	public BudgetForm getPlannedFundsByStartYear(Integer startYear, Subject subject, boolean summary, String formType) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetForm> criteria = builder.createQuery(BudgetForm.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		List<Predicate> predicates = new ArrayList<>();
		root.fetch(BudgetForm._budgetFormVersion, JoinType.LEFT);
		root.fetch(BudgetForm._subjectVersion, JoinType.LEFT);
		predicates.add(builder.equal(root.get(BudgetForm._formType).get(FormType._code), formType));
		predicates.add(builder.equal(root.get(BudgetForm._startYear), startYear));
		predicates.add(builder.equal(root.get(BudgetForm._subjectVersion).get(SubjectVersion._subject), subject));
		if (summary) {
			predicates.add(builder.equal(root.get(BudgetForm._sumBudgetFormType).get(SumBudgetFormType._code), SumBudgetFormTypeConst.TOTAL.getCode()));
			predicates.add(builder.or(builder.equal(root.get(BudgetForm._finalStatus).get(Status._code), StatusConst.APPROVED.name()), 
					builder.equal(root.get(BudgetForm._finalStatus).get(Status._code), StatusConst.APPROVED_MARKS.name())));
		} else {
			predicates.add(builder.equal(root.get(BudgetForm._sumBudgetFormType).get(SumBudgetFormType._code), SumBudgetFormTypeConst.NOT_TOTAL.getCode()));
			predicates.add(builder.equal(root.get(BudgetForm._status).get(Status._code), StatusConst.FINALIZED.name()));
		}
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.desc(root.get(BudgetForm._id)));
		try {
		    return createQuery(criteria).setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
		    return null;
		}
	}
	
	public List<BudgetForm> getAllApprovedBudgets(SearchDTO searchObject) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<BudgetForm> criteria = builder.createQuery(BudgetForm.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		List<Predicate> predicates = new ArrayList<>();
		root.fetch(BudgetForm._budgetFormVersion, JoinType.LEFT);
		root.fetch(BudgetForm._subjectVersion, JoinType.LEFT);
		String firstYear = new SimpleDateFormat("yyyy").format(searchObject.getStartPeriod());
		Integer startYear = Integer.parseInt(firstYear);
		predicates.add(builder.equal(root.get(BudgetForm._startYear), startYear));
		predicates.add(builder.equal(root.get(BudgetForm._formType).get(FormType._code), FormTypeConst.APP_TWO.getCode()));
		predicates.add(builder.or(builder.equal(root.get(BudgetForm._finalStatus).get(Status._code), StatusConst.APPROVED.name()), 
				builder.equal(root.get(BudgetForm._finalStatus).get(Status._code), StatusConst.APPROVED_MARKS.name())));
		predicates.add(builder.equal(root.get(BudgetForm._subjectVersion).get(SubjectVersion._subject), searchObject.getSubject()));
		predicates.add(builder.equal(root.get(BudgetForm._sumBudgetFormType).get(SumBudgetFormType._code), SumBudgetFormTypeConst.TOTAL.getCode()));
		predicates.add(builder.equal(root.get(BudgetForm._budgetFormVersion).get(BudgetFormVersion._isEmpty), false));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(BudgetForm._id)));
		return createQuery(criteria).getResultList();
	}

	public List<VersionRow> getBudgetFormDataForDeployment(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<VersionRow> criteria = builder.createQuery(VersionRow.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		Join<BudgetForm, BudgetFormVersion> budgetVersionJoin = root.join(BudgetForm._budgetFormVersion, JoinType.LEFT);
		Join<BudgetFormVersion, VersionRow> budgetVersionRowJoin = budgetVersionJoin.join(BudgetFormVersion._versionRow, JoinType.LEFT);
		
		criteria.multiselect(budgetVersionRowJoin);
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(GenericDaoImpl._id), id));
		predicates.add(builder.isNull(budgetVersionRowJoin.get(VersionRow._expenseType)));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<PublicBudgetAdditionalField> getBudgetAdditionalFields(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<PublicBudgetAdditionalField> criteria = builder.createQuery(PublicBudgetAdditionalField.class);
		Root<BudgetForm> root = criteria.from(BudgetForm.class);
		Join<BudgetForm, BudgetFormVersion> budgetFormVersionJoin = root.join(BudgetForm._budgetFormVersion, JoinType.LEFT);
		Join<BudgetFormVersion, BudgetMainData> budgetMainDataJoin = budgetFormVersionJoin.join(BudgetFormVersion._budgetMainData, JoinType.LEFT);
		Join<BudgetMainData, BudgetMainDataValue> budgetMainDataValueJoin = budgetMainDataJoin.join(BudgetMainData._budgetMainDataValues, JoinType.LEFT);
		
		criteria.multiselect(budgetMainDataValueJoin.get(BudgetMainDataValue._formConfig).get(FormConfig._columnName),
				budgetMainDataValueJoin.get(BudgetMainDataValue._value),
				budgetMainDataValueJoin.get(BudgetMainDataValue._formConfig).get(FormConfig._formColumnType).get(FormColumnType._code));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(GenericDaoImpl._id), id));
		predicates.add(builder.isNotNull(budgetMainDataValueJoin.get(BudgetMainDataValue._formConfig).get(FormConfig._columnName)));
		
		predicates.add(builder.equal(budgetMainDataValueJoin.get(BudgetMainDataValue._formConfig).get(FormConfig._isValid), "Y"));
		predicates.add(builder.equal(budgetMainDataValueJoin.get(BudgetMainDataValue._formConfig).get(FormConfig._isDeployableField), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}

}
