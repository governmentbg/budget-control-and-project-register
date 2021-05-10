package bg.infosys.daeu.db.dao.tech_specs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.ListModelFilter;
import bg.infosys.common.utils.Strings;
import bg.infosys.daeu.db.dto.AppVisualizationDTO;
import bg.infosys.daeu.db.dto.CustomDaoUtils;
import bg.infosys.daeu.db.dto.PublicTechSpecsDetail;
import bg.infosys.daeu.db.dto.SearchDTO;
import bg.infosys.daeu.db.entity.projects.RowType;
import bg.infosys.daeu.db.entity.pub.Checklist;
import bg.infosys.daeu.db.entity.pub.FormType;
import bg.infosys.daeu.db.entity.pub.Letter;
import bg.infosys.daeu.db.entity.pub.Status;
import bg.infosys.daeu.db.entity.pub.SubjectVersion;
import bg.infosys.daeu.db.entity.security.User;
import bg.infosys.daeu.db.entity.tech_specs.TechRowType;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecs;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsFormConfig;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsVersion;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsVersionUser;
import bg.infosys.daeu.db.entity.tech_specs.TechSpecsVersionValue;

@Repository
public class TechSpecsDAO extends GenericDaoImpl<TechSpecs, Integer> {
	
	public List<TechSpecs> findByProperties(SearchDTO searchObject, ListModelFilter filter, List<Status> allowedStatuses, User user) {

		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
		
		if (user == null) {
			Root<TechSpecs> techSpecsRoot;
			techSpecsRoot = criteria.from(TechSpecs.class);
			
			List<Predicate> techSpecsPredicates = new ArrayList<Predicate>();
			techSpecsPredicates = getWhereClause(searchObject, builder, techSpecsRoot);
			techSpecsRoot.fetch(TechSpecs._techSpecsVersion, JoinType.LEFT);
			techSpecsRoot.fetch(TechSpecs._subjectVersion, JoinType.LEFT);
			techSpecsRoot.fetch(TechSpecs._status , JoinType.LEFT);
			
			if (searchObject.getStatus() == null) {
				List<Predicate> statusPredicates = new ArrayList<Predicate>();
				
				for (Status status : allowedStatuses) {
					statusPredicates.add(builder.equal(techSpecsRoot.get(TechSpecs._status), status));
				}
			
				criteria.where(builder.and(builder.and(techSpecsPredicates.toArray(new Predicate[techSpecsPredicates.size()])), builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
			} else {
				criteria.where(builder.and(techSpecsPredicates.toArray(new Predicate[techSpecsPredicates.size()])));
			}
			if (filter != null && filter.getOrderBy() != null && filter.getOrderBy().equals("id")) {
				filter.setOrderBy("techSpecsVersion.subjectOutgoingDate");
			}
			CustomDaoUtils.setCriteriaFilterProps(builder, techSpecsRoot, criteria, filter);
			List<Order> order = new ArrayList<>();
			order.addAll(criteria.getOrderList());
			if (filter.isAscending()) {
				order.add(builder.asc(techSpecsRoot.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._id)));
			} else {
				order.add(builder.desc(techSpecsRoot.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._id)));
			}
			criteria.orderBy(order);
//			criteria.orderBy(builder.desc(techSpecsRoot.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._dateCreated)));
		} else {
			Root<TechSpecsVersionUser> verisonUserRoot = criteria.from(TechSpecsVersionUser.class);
			List<Predicate> versionUserPredicates = new ArrayList<Predicate>();

			Join<TechSpecsVersionUser, TechSpecsVersion> techSpecsVersionJoin = verisonUserRoot.join(TechSpecsVersionUser._techSpecsVersion, JoinType.LEFT);
			Join<TechSpecsVersion, TechSpecs> techSpecsJoin = techSpecsVersionJoin.join(TechSpecsVersion._techSpecs, JoinType.LEFT);
			
			techSpecsJoin.fetch(TechSpecs._status, JoinType.LEFT);
			techSpecsJoin.fetch(TechSpecs._subjectVersion, JoinType.LEFT);
			techSpecsJoin.fetch(TechSpecs._techSpecsVersion, JoinType.LEFT);
			
			if (searchObject.getSubject() != null) {
				versionUserPredicates.add(builder.equal(techSpecsJoin.get(TechSpecs._subjectVersion).get(SubjectVersion._subject), searchObject.getSubject()));
			}

			if (!Strings.isEmpty(searchObject.getOutgoingNumber())) {
				versionUserPredicates.add(builder.like(techSpecsJoin.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingNum), 
						"%" + searchObject.getOutgoingNumber() + "%"));
			}

			if (searchObject.getStatus() != null) {
				versionUserPredicates.add(builder.equal(techSpecsJoin.get(TechSpecs._status), searchObject.getStatus()));
			}

			if (searchObject.getStartPeriod() != null) {
				versionUserPredicates.add(builder.greaterThanOrEqualTo(techSpecsJoin.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingDate), searchObject.getStartPeriod()));
			}

			if (searchObject.getEndPeriod() != null) {
				versionUserPredicates.add(builder.lessThanOrEqualTo(techSpecsJoin.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingDate), searchObject.getEndPeriod()));
			}
			
			versionUserPredicates.add(builder.equal(verisonUserRoot.get(TechSpecsVersionUser._assignee), user));
			versionUserPredicates.add(builder.equal(verisonUserRoot.get(TechSpecsVersionUser._isValid), "Y"));
		
			if (searchObject.getStatus() == null) {
				List<Predicate> statusPredicates = new ArrayList<Predicate>();
				
				for (Status status : allowedStatuses) {
					statusPredicates.add(builder.equal(techSpecsJoin.get(TechSpecs._status), status));
				}
			
				criteria.where(builder.and(builder.and(versionUserPredicates.toArray(new Predicate[versionUserPredicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
			} else {
				criteria.where(builder.and(versionUserPredicates.toArray(new Predicate[versionUserPredicates.size()])));
			}
			
			if (filter != null && filter.getOrderBy() != null && filter.getOrderBy().equals("id")) {
				filter.setOrderBy("techSpecsVersion.subjectOutgoingDate");
			}
			
			String orderBy = filter.getOrderBy();
			
			
			Path<?> orderByPath = null;
			
			for (String token : orderBy.split("\\.")) {
				if (orderByPath == null) {
					orderByPath = techSpecsJoin.get(token);
				} else {
					orderByPath = orderByPath.get(token);
				}
			}
			
			String secondOrder = "techSpecsVersion.id";
			
			Path<?> secondOrderByPath = null;
			
			for (String token : secondOrder.split("\\.")) {
				if (secondOrderByPath == null) {
					secondOrderByPath = techSpecsJoin.get(token);
				} else {
					secondOrderByPath = secondOrderByPath.get(token);
				}
			}

			if (filter.isAscending()) {
				criteria.orderBy(builder.asc(orderByPath),builder.asc(secondOrderByPath)).distinct(true);
			} else {
				criteria.orderBy(builder.desc(orderByPath),builder.desc(secondOrderByPath)).distinct(true);
			}
			
			criteria.multiselect(techSpecsVersionJoin.get(TechSpecsVersion._techSpecs));
		}

		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList()
				.stream().map(tuple -> tuple.get(0, TechSpecs.class)).collect(Collectors.toList());
	}

	public long countByProperties(SearchDTO searchObject, List<Status> allowedStatuses, User user) {

		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

		if (user == null) {
			Root<TechSpecs> techSpecsRoot;
			techSpecsRoot = criteria.from(TechSpecs.class);
			
			List<Predicate> techSpecsPredicates = new ArrayList<Predicate>();
			techSpecsPredicates = getWhereClause(searchObject, builder, techSpecsRoot);
			
			if (searchObject.getStatus() == null) {
				List<Predicate> statusPredicates = new ArrayList<Predicate>();
				
				for (Status status : allowedStatuses) {
					statusPredicates.add(builder.equal(techSpecsRoot.get(TechSpecs._status), status));
				}
			
				criteria.where(builder.and(builder.and(techSpecsPredicates.toArray(new Predicate[techSpecsPredicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
			} else {
				criteria.where(builder.and(techSpecsPredicates.toArray(new Predicate[techSpecsPredicates.size()])));
			}
			
			criteria.select(builder.count(techSpecsRoot));
		} else {
			Root<TechSpecsVersionUser> verisonUserRoot = criteria.from(TechSpecsVersionUser.class);
			List<Predicate> versionUserPredicates = new ArrayList<Predicate>();

			Join<TechSpecsVersionUser, TechSpecsVersion> techSpecsVersionJoin = verisonUserRoot.join(TechSpecsVersionUser._techSpecsVersion, JoinType.LEFT);
			
			versionUserPredicates.add(builder.equal(verisonUserRoot.get(TechSpecsVersionUser._assignee), user));
			versionUserPredicates.add(builder.equal(verisonUserRoot.get(TechSpecsVersionUser._isValid), "Y"));
			
			if (searchObject.getSubject() != null) {
				versionUserPredicates.add(builder.equal(techSpecsVersionJoin.get(TechSpecsVersion._techSpecs).get(TechSpecs._subjectVersion).get(SubjectVersion._subject), searchObject.getSubject()));
			}

			if (!Strings.isEmpty(searchObject.getOutgoingNumber())) {
				versionUserPredicates.add(builder.like(techSpecsVersionJoin.get(TechSpecsVersion._subjectOutgoingNum), 
						"%" + searchObject.getOutgoingNumber() + "%"));
			}

			if (searchObject.getStatus() != null) {
				versionUserPredicates.add(builder.equal(techSpecsVersionJoin.get(TechSpecsVersion._techSpecs).get(TechSpecs._status), searchObject.getStatus()));
			}

			if (searchObject.getStartPeriod() != null) {
				versionUserPredicates.add(builder.greaterThanOrEqualTo(techSpecsVersionJoin.get(TechSpecsVersion._subjectOutgoingDate), searchObject.getStartPeriod()));
			}

			if (searchObject.getEndPeriod() != null) {
				versionUserPredicates.add(builder.lessThanOrEqualTo(techSpecsVersionJoin.get(TechSpecsVersion._subjectOutgoingDate), searchObject.getEndPeriod()));
			}
			
			if (searchObject.getStatus() == null) {
				List<Predicate> statusPredicates = new ArrayList<Predicate>();
				
				for (Status status : allowedStatuses) {
					statusPredicates.add(builder.equal(techSpecsVersionJoin.get(TechSpecsVersion._techSpecs).get(TechSpecs._status), status));
				}
			
				criteria.where(builder.and(builder.and(versionUserPredicates.toArray(new Predicate[versionUserPredicates.size()])),builder.or(statusPredicates.toArray(new Predicate[statusPredicates.size()]))));
			} else {
				criteria.where(builder.and(versionUserPredicates.toArray(new Predicate[versionUserPredicates.size()])));
			}
			
			
			criteria.select(builder.countDistinct(techSpecsVersionJoin.get(TechSpecsVersion._techSpecs)));
		}

		return createQuery(criteria).getSingleResult();
	}

	public List<AppVisualizationDTO> findByProperties(SearchDTO searchObject) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AppVisualizationDTO> criteria = builder.createQuery(AppVisualizationDTO.class);
		Root<TechSpecs> root = criteria.from(TechSpecs.class);

		List<Predicate> predicates = getWhereClause(searchObject, builder, root);

		criteria.multiselect(root.get(_id), root.get(TechSpecs._status), root.get(TechSpecs._formType).get(FormType._name),
				root.get(TechSpecs._subjectVersion).get(SubjectVersion._fullName), root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingDate),
					root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingNum));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.desc(root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingDate)), builder.desc(root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._id)));
		
		return createQuery(criteria).getResultList();
	}
	
	public List<AppVisualizationDTO> findByAllowedStatus(List<Status> allowedStatuses, ListModelFilter filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AppVisualizationDTO> criteria = builder.createQuery(AppVisualizationDTO.class);
		Root<TechSpecs> root = criteria.from(TechSpecs.class);
		
		criteria.multiselect(root.get(_id), root.get(TechSpecs._status), root.get(TechSpecs._formType).get(FormType._name),
				root.get(TechSpecs._subjectVersion).get(SubjectVersion._fullName), root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingDate),
				root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingNum));

		List<Predicate> predicates = new ArrayList<>();
		
		for (Status status : allowedStatuses) {
			predicates.add(builder.equal(root.get(TechSpecs._status), status));
		}
		criteria.where(builder.or(predicates.toArray(new Predicate[predicates.size()])));
		
		List<Order> order = new ArrayList<>();
		order.add(builder.desc(root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingDate)));
		order.add(builder.desc(root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._id)));
		criteria.orderBy(order);
		
		return createQuery(criteria).setFirstResult(filter.getFrom()).setMaxResults(filter.getLimit()).getResultList();
	}
	
	public long countByAllowedStatus(List<Status> allowedStatuses) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<TechSpecs> root = criteria.from(TechSpecs.class);
		List<Predicate> predicates = new ArrayList<>();
		for (Status status : allowedStatuses) {
			predicates.add(builder.equal(root.get(TechSpecs._status), status));
		}
		criteria.select(builder.count(root));
		criteria.where(builder.or(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
	
	private List<Predicate> getWhereClause(SearchDTO searchObject, CriteriaBuilder builder, Root<TechSpecs> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (searchObject.getSubject() != null) {
			predicates.add(builder.equal(root.get(TechSpecs._subjectVersion).get(SubjectVersion._subject), searchObject.getSubject()));
		}

		if (!Strings.isEmpty(searchObject.getOutgoingNumber())) {
			predicates.add(builder.like(builder.upper(root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingNum)), 
					"%" + searchObject.getOutgoingNumber().toUpperCase() + "%"));
		}

		if (searchObject.getStatus() != null) {
			predicates.add(builder.equal(root.get(TechSpecs._status), searchObject.getStatus()));
		}

		if (searchObject.getStartPeriod() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingDate), searchObject.getStartPeriod()));
		}

		if (searchObject.getEndPeriod() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingDate), searchObject.getEndPeriod()));
		}

		return predicates;
	}

	public TechSpecs initFullTechSpecsData(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<TechSpecs> criteria = builder.createQuery(TechSpecs.class);
		Root<TechSpecs> root = criteria.from(TechSpecs.class);
		root.fetch(TechSpecs._status, JoinType.LEFT);
		root.fetch(TechSpecs._finalStatus, JoinType.LEFT);
		root.fetch(TechSpecs._formType, JoinType.LEFT);
		root.fetch(TechSpecs._subjectVersion, JoinType.LEFT);
		Fetch<Object, Object> techSpecsVersion = root.fetch(TechSpecs._techSpecsVersion , JoinType.LEFT);
		techSpecsVersion.fetch(TechSpecsVersion._letter, JoinType.LEFT).fetch(Letter._template, JoinType.LEFT);
		techSpecsVersion.fetch(TechSpecsVersion._values, JoinType.LEFT).fetch(TechSpecsVersionValue._techSpecsFormConfig, JoinType.LEFT)
		.fetch(TechSpecsFormConfig._rowType, JoinType.LEFT).fetch(TechRowType._numenclatures, JoinType.LEFT);
		techSpecsVersion.fetch(TechSpecsVersion._attachedDocs, JoinType.LEFT);
		techSpecsVersion.fetch(TechSpecsVersion._createdBy, JoinType.LEFT);
		techSpecsVersion.fetch(TechSpecsVersion._notes, JoinType.LEFT);
		Fetch<Object, Object> childChecklistFetch = techSpecsVersion.fetch(TechSpecsVersion._checklist, JoinType.LEFT);
		childChecklistFetch.fetch(Checklist._checklistType, JoinType.LEFT);
		childChecklistFetch.fetch(Checklist._checklistStatus, JoinType.LEFT);
		criteria.where(builder.equal(root.get(TechSpecs._id), id));

		return createQuery(criteria).getSingleResult();
	}

	public List<AppVisualizationDTO> findByStatus(String status) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<AppVisualizationDTO> criteria = builder.createQuery(AppVisualizationDTO.class);
		Root<TechSpecs> root = criteria.from(TechSpecs.class);
		
		criteria.multiselect(root.get(TechSpecs._techSpecsVersion));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(TechSpecs._status).get(Status._code), status));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._subjectOutgoingDate)));
		
		return createQuery(criteria).getResultList();
	}

	public List<PublicTechSpecsDetail> getTechSpecsDataForDeployment(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<PublicTechSpecsDetail> criteria = builder.createQuery(PublicTechSpecsDetail.class);
		Root<TechSpecs> root = criteria.from(TechSpecs.class);
		Join<TechSpecs, TechSpecsVersion> techSpecsVersionJoin = root.join(TechSpecs._techSpecsVersion, JoinType.LEFT);
		Join<TechSpecsVersion, TechSpecsVersionValue> techSpecsVersionValueJoin = techSpecsVersionJoin.join(TechSpecsVersion._values, JoinType.LEFT);
		
		criteria.multiselect(techSpecsVersionValueJoin.get(TechSpecsVersionValue._techSpecsFormConfig).get(TechSpecsFormConfig._rowLabel),
				techSpecsVersionValueJoin.get(TechSpecsVersionValue._value),
				techSpecsVersionValueJoin.get(TechSpecsVersionValue._techSpecsFormConfig).get(TechSpecsFormConfig._rowType).get(RowType._code));
		
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(builder.equal(root.get(GenericDaoImpl._id), id));
		predicates.add(builder.isNotNull(techSpecsVersionValueJoin.get(TechSpecsVersionValue._techSpecsFormConfig).get(TechSpecsFormConfig._rowLabel)));
		
		predicates.add(builder.equal(techSpecsVersionValueJoin.get(TechSpecsVersionValue._techSpecsFormConfig).get(TechSpecsFormConfig._isValid), "Y"));
		predicates.add(builder.equal(techSpecsVersionValueJoin.get(TechSpecsVersionValue._techSpecsFormConfig).get(TechSpecsFormConfig._isDeployableField), "Y"));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getResultList();
	}

	public String getOpenDataRow(Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<TechSpecs> root = criteria.from(TechSpecs.class);
		
		criteria.select(root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._openDataURI));
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(root.get(TechSpecs._techSpecsVersion).get(TechSpecsVersion._id), id));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return createQuery(criteria).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllAuditRevisionsById(Class<?> clazz, int id) {
		try{
			AuditReader reader = AuditReaderFactory.get(getEntityManager().unwrap(Session.class));
			AuditQuery query = reader.createQuery().forRevisionsOfEntityWithChanges(clazz, true);
			query.add(AuditEntity.property("id").eq(id));
			List<Object[]> raw_results = query.getResultList();
			List<Object[]> complete_results = new ArrayList<Object[]>(raw_results.size());
			for (Object[] data : raw_results) {
				int rev_id = ((DefaultRevisionEntity) data[1]).getId();
				AuditQuery q = reader.createQuery()
						.forRevisionsOfEntityWithChanges(clazz, true)
						.add(AuditEntity.revisionNumber().eq(rev_id));
				List<Object[]> real_data = q.getResultList();
				complete_results.addAll(real_data);
			}
			return complete_results;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getRevisionDataByClassAndRev(Class<?> clazz, int revNum) {
		try{
			AuditReader reader = AuditReaderFactory.get(getEntityManager().unwrap(Session.class));
			AuditQuery query = reader.createQuery().forRevisionsOfEntityWithChanges(clazz, true);
			query.add(AuditEntity.revisionNumber().eq(revNum));
			return query.getResultList();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getPreviousVersion(Class<?> clazz, int entityId, int current_rev) {

		AuditReader reader = AuditReaderFactory.get(getEntityManager().unwrap(Session.class));

	    Number prior_revision = (Number) reader.createQuery()
	            .forRevisionsOfEntity(clazz, false, true)
	            .addProjection(AuditEntity.revisionNumber().max())
	            .add(AuditEntity.id().eq(entityId))
	            .add(AuditEntity.revisionNumber().lt(current_rev))
	            .getSingleResult();

	    if (prior_revision != null)
	        return reader.find(clazz, entityId, prior_revision);
	    else
	        return null;
	}

	public Object findObjectFromDB(Class<?> clazz, int id, Table table) {
		StringBuilder sb = new StringBuilder();
		String schema = table.schema().equals("") ? "public" : table.schema();
		sb.append("select o.* from "+schema+"."+table.name()+" o where o.id= "+id);
		Query q = getEntityManager().createNativeQuery(sb.toString(), clazz);
		return  q.getSingleResult();
	}
}
