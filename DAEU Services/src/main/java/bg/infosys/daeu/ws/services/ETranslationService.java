package bg.infosys.daeu.ws.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bg.infosys.daeu.db.dao.pub.ETranslationDAO;
import bg.infosys.daeu.db.entity.pub.ETranslation;

@Service
@Transactional
public class ETranslationService {

	@Autowired
	private ETranslationDAO translationDAO;

	public void saveTranslation(ETranslation translation) {
		translationDAO.saveOrUpdate(translation);
	}

	public ETranslation findByRequestId(int requestId) {
		return translationDAO.findByRequestId(requestId);
	}
	
}
