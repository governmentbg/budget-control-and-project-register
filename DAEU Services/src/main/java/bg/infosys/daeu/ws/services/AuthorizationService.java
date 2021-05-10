package bg.infosys.daeu.ws.services;

import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.daeu.db.dao.security.APIUserDAO;

@Service
public class AuthorizationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationService.class);
	
	@Autowired private APIUserDAO aPIUserDAO;
		
	private boolean checkExistingUser(String username, String pass) {
    	return aPIUserDAO.checkExistingUser(username, pass);
	}
	
	@Transactional
	public boolean isAuthorized(String authString) {
		if (authString == null || authString.length() == 0) return false;
		
		try {
			String authInfo = authString.split("\\s+")[1];
			byte[] bytes = Base64.getDecoder().decode(authInfo);
			String decodedAuth = new String(bytes);
			String[] credentials = decodedAuth.split(":");
			if (credentials.length != 2) return false;
			
			boolean isOk = checkExistingUser(credentials[0], credentials[1]);
			if (!isOk) LOGGER.warn("Invalid username or password: [" + decodedAuth + "]");
			return isOk;
		} catch (NullPointerException | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
			return false;
		}
	}
}
