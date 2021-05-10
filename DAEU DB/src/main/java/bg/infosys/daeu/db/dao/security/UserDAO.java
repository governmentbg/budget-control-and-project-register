package bg.infosys.daeu.db.dao.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.common.db.dao.aux.ListModelFilter;
import bg.infosys.common.db.dao.security.IUserDao;
import bg.infosys.common.utils.Strings;
import bg.infosys.daeu.db.dto.SearchDTO;
import bg.infosys.daeu.db.entity.security.Authority;
import bg.infosys.daeu.db.entity.security.User;

@Repository
public class UserDAO extends GenericDaoImpl<User, Integer> implements IUserDao<User, Authority> {

	public User find(String username) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.equal(root.get("username"), username));

		try {
			return createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<User> findByAuthority(List<Authority> authorities) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		for (Authority authority : authorities) {
			predicates.add(builder.isMember(authority, root.get(User._authorities)));
		}

		query.where(builder.or((Predicate[])predicates.toArray()));

		return createQuery(query).getResultList();
	}

	public User findByAuthority(Authority authority) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(builder.isMember(authority, root.get(User._authorities)));

		try {
			return createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<User> findMultipleByAuthorityName(String name) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		Join<User, Authority> userAuthorityJoin = root.join(User._authorities, JoinType.LEFT);

		criteria.select(root);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(userAuthorityJoin.get(Authority._name), name));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getResultList();
	}
	
	public List<User> findMultipleByAuthorityNameAndEnabled(String name) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		Join<User, Authority> userAuthorityJoin = root.join(User._authorities, JoinType.LEFT);

		criteria.select(root);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(userAuthorityJoin.get(Authority._name), name));
		predicates.add(builder.equal(root.get(User._enabled), true));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getResultList();
	}
	
	public List<User> findMultipleByChildAuthorityName(String name) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		Join<User, Authority> userAuthorityJoin = root.join(User._authorities, JoinType.LEFT);
		Join<Authority, Authority> authorityAuthorityJoin = userAuthorityJoin.join(Authority._children, JoinType.LEFT);

		criteria.select(root);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(authorityAuthorityJoin.get(Authority._name), name));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getResultList();
	}
	
	public List<User> findMultipleByChildAuthorityNameAndEnabled(String name) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		Join<User, Authority> userAuthorityJoin = root.join(User._authorities, JoinType.LEFT);
		Join<Authority, Authority> authorityAuthorityJoin = userAuthorityJoin.join(Authority._children, JoinType.LEFT);

		criteria.select(root);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.equal(authorityAuthorityJoin.get(Authority._name), name));
		predicates.add(builder.equal(root.get(User._enabled), true));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getResultList();
	}

	public List<User> findSubordinatesByName(String name, Integer userId, List<Authority> authorities) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		criteria.select(root);

		List<Predicate> predicates = new ArrayList<>();
		List<Predicate> authoritiesPredicates = new ArrayList<Predicate>();

		predicates.add(builder.or(
				builder.like(builder.lower(root.get(User._firstName)), "%" + name.toLowerCase() + "%"),
				builder.like(builder.lower(root.get(User._lastName)), "%" + name.toLowerCase() + "%")));
		predicates.add(builder.equal(root.get(User._enabled), true));



		authorities.forEach(authority -> authoritiesPredicates.add(builder.isMember(authority, root.get(User._authorities))));

		predicates.add(builder.or(authoritiesPredicates.toArray(new Predicate[authoritiesPredicates.size()])));

		//		predicates.add(builder.or(new Predicate[] {builder.equal(root.join(User._authorities).get(Authority._department).get(Department._parent).get(_id), departmentId), 
		//									builder.equal(root.join(User._authorities).get(Authority._department).get(_id), departmentId),
		//									builder.equal(root.join(User._authorities).get(Authority._position).get(Position._parent).get(_id), positionId)}));

		//		predicates.add(builder.or(builder.equal(root.join(User._authorities).get(Authority._department).get(Department._parent).get(_id), departmentId), 
		//									builder.equal(root.join(User._authorities).get(Authority._department).get(_id), departmentId)));
		//		predicates.add(builder.equal(root.join(User._authorities).get(Authority._position).get(Position._parent).get(_id), positionId));
		//		predicates.add(builder.equal(root.join(User._authorities).get(Authority._department).get(Department._parent).get(_id), departmentId));
		//		predicates.add(builder.equal(root.join(User._authorities).get(Authority._position).get(Position._parent).get(_id), positionId));

		predicates.add(builder.notEqual(root.get(_id), userId));

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		return createQuery(criteria).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> findUsersByProperties(SearchDTO searchObject, ListModelFilter filter) {
		StringBuilder strQuery = new StringBuilder();
		strQuery.append("select U.* from security.users U ");
		strQuery.append("left join security.users_authorities UA ON UA.user_id = U.id ");
		strQuery.append("left join security.authorities A ON A.id = UA.authority_id ");
		strQuery.append("where 1=1 ");
		strQuery.append(getSearchWhereClause(searchObject));

		strQuery.append(filterProps(filter));
		Query q = getEntityManager().createNativeQuery(strQuery.toString(), User.class);
		setFilterPropsToQuery(filter, q);

		return q.getResultList();
	}

	public long countUsersByProperties(SearchDTO searchObject) {
		StringBuilder strQuery = new StringBuilder();
		strQuery.append("select count(U.*) from security.users U ");
		strQuery.append("left join security.users_authorities UA ON UA.user_id = U.id ");
		strQuery.append("left join security.authorities A ON A.id = UA.authority_id ");
		strQuery.append("where 1=1 ");
		strQuery.append(getSearchWhereClause(searchObject));

		Query q = getEntityManager().createNativeQuery(strQuery.toString());
		int result = ((Number) q.getSingleResult()).intValue();

		return result;
	}

	private String getSearchWhereClause(SearchDTO searchObject) {
		StringBuilder sb = new StringBuilder("");
		if (!Strings.isEmpty(searchObject.getName())) {
			sb.append("AND (lower(U.username) like lower('%"+searchObject.getName().trim().replaceAll(" ","%%")+"%') "
					+ "OR lower(U.first_name) like lower('%"+searchObject.getName().trim().replaceAll(" ","%%")+"%') "
					+ "OR lower(U.last_name) like lower('%"+searchObject.getName().trim().replaceAll(" ","%%")+"%')) ");
		}

		if (searchObject.getSubject() != null) {
			sb.append("AND U.subject_id = " +searchObject.getSubject().getId());
		}

		if (searchObject.getAuthority() != null) {
			sb.append("AND A.id = " + searchObject.getAuthority().getId());
		}


		if (searchObject.getEnabled() != null) {
			sb.append("AND U.enabled = "+searchObject.getEnabled());
		}
		return sb.toString();
	}

	private String filterProps(ListModelFilter filter) {
		StringBuilder sb = new StringBuilder(" ORDER BY ");
		if (filter.isAscending()) {
			sb.append(filter.getOrderBy() + " ASC ");
		} else {
			sb.append(filter.getOrderBy() + " DESC ");
		}

		return sb.toString();
	}

	private void setFilterPropsToQuery(ListModelFilter filter, Query q) {
		q.setFirstResult(filter.getFrom());
		q.setMaxResults(filter.getLimit());
	}

	public boolean checkIfUserExist(String username, Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<User> root = criteria.from(User.class);
		if(id != null) {
			predicates.add(builder.notEqual(root.get(User._id), id));
		}
		predicates.add(builder.like(root.get(User._username), username));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		if (!createQuery(criteria).getResultList().isEmpty()){
			return true;
		}
		return false;
	}
	
	public boolean checkIfEmailExist(String email, Integer id) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<User> root = criteria.from(User.class);

		if(id != null) {
			predicates.add(builder.notEqual(root.get(User._id), id));
		}
		
		predicates.add(builder.equal(root.get(User._enabled), true));
		predicates.add(builder.equal(builder.upper(root.get(User._email)), email.toUpperCase()));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		if (!createQuery(criteria).getResultList().isEmpty()){
			return true;
		}
		return false;
	}

	public boolean existingUserAuthority(Integer id, Authority auth) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<User> root = criteria.from(User.class);

		if(id != null) {
			predicates.add(builder.notEqual(root.get(User._id), id));
		}
		predicates.add(builder.isMember(auth ,root.get(User._authorities)));
		predicates.add(builder.equal(root.get(User._enabled), true));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		if (!createQuery(criteria).getResultList().isEmpty()){
			return true;
		}
		return false;
	}

	public List<User> findUsersByAuthority(Authority authority) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<User> root = criteria.from(User.class);
		predicates.add(builder.isMember(authority ,root.get(User._authorities)));
		predicates.add(builder.equal(root.get(User._enabled), true));
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		return createQuery(criteria).getResultList();
	}

	public void resetPassword(String newPass, String email) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaUpdate<User> criteria = builder.createCriteriaUpdate(User.class);
		Root<User> root = criteria.from(User.class);
		
		criteria.set(root.get(User._password), newPass);
		
		criteria.where(builder.equal(root.get(User._email), email));
		
		getEntityManager().createQuery(criteria).executeUpdate();
	}

	public User findByEmail(String email) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<User> root = criteria.from(User.class);
		
		predicates.add(builder.equal(root.get(User._email), email));
		predicates.add(builder.equal(root.get(User._enabled), true));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		return createQuery(criteria).getSingleResult();
	}

}
