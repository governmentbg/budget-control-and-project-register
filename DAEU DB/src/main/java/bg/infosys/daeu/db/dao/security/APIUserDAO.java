package bg.infosys.daeu.db.dao.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import bg.infosys.common.db.dao.GenericDaoImpl;
import bg.infosys.daeu.db.entity.security.APIUser;
import bg.infosys.daeu.db.entity.security.User;

@Repository
public class APIUserDAO extends GenericDaoImpl<APIUser, Integer> {

	public boolean checkExistingUser(String username, String pass) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<APIUser> criteria = builder.createQuery(APIUser.class);
		Root<APIUser> root = criteria.from(APIUser.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(builder.like(root.get(User._username), username));
		predicates.add(builder.like(root.get(User._password), pass));
		
		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

		if (!createQuery(criteria).getResultList().isEmpty()){
			return true;
		}
		return false;
	}

}
