package bg.infosys.daeu.ws.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.infosys.daeu.db.dao.pub.MailTemplateDAO;
import bg.infosys.daeu.db.dao.pub.MailTypeDAO;
import bg.infosys.daeu.db.entity.pub.MailTemplate;
import bg.infosys.daeu.db.entity.pub.MailType;
import bg.infosys.daeu.db.entity.pub.ModuleType;

@Service
public class MailService {
	@Autowired private MailTemplateDAO mailTemplateDAO;
	@Autowired private MailTypeDAO mailTypeDAO;

	@Transactional
	public MailTemplate findTemplateByModuleTypeAndDescription(String moduleType, String description) {
		return mailTemplateDAO.findTemplateByModuleTypeAndDescription(moduleType, description);
	}
	
	@Transactional
	public List<MailType> findAllEmailTypesByModule(ModuleType module) {
		return mailTypeDAO.findAllTypes(module);
	}
	
	@Transactional
	public List<MailTemplate> findAllEmailTemplatesByModule(ModuleType module) {
		return mailTemplateDAO.getEmailTemplatesByModuleType(module);
	}
	
	//NOT USED
//	@Transactional
//	public List<MailType> findAllValidEmailTypesByModule(ModuleType module) {
//		return mailTypeDAO.findAllValidTypes(module);
//	}

	@Transactional
	public void saveMailType(MailType editObject) {
		mailTypeDAO.saveOrUpdate(editObject);
	}
	
	@Transactional
	public void saveMailTemplate(MailTemplate editObject) {
		mailTemplateDAO.saveOrUpdate(editObject);
	}
}
