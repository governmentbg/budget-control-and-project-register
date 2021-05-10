package bg.infosys.daeu.ws.services;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.infosys.common.mail.Email;
import bg.infosys.common.mail.MailException;
import bg.infosys.common.mail.Mailer;
import bg.infosys.daeu.db.dao.security.UserDAO;
import bg.infosys.daeu.db.entity.pub.MailTemplate;
import bg.infosys.daeu.db.entity.pub.MailTemplate.MailTemplateConst;
import bg.infosys.daeu.db.entity.pub.MailType;
import bg.infosys.daeu.db.entity.security.User;
import bg.infosys.daeu.ws.pub.util.TagUtil;

@Service
public class MailingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MailingService.class);
	
	@Autowired private UserDAO userDAO;
	@Autowired private MailService mailService;
	
	public void sendMail(String recipient, String subject, String text, MailType type) {
		if (type.getIsValid().equals("Y")) {
			try {
				Mailer.sendMail(new Email(recipient, subject, text));
			} catch (MailException | NullPointerException e) {
				LOGGER.info("Could not send this mail: " + e.getMessage());
			}
		} else {
			LOGGER.info("Current mail type is not valid. The system will not send this mail!");
		}
	}
	
	public void sendMultipleMails(List<User> recipients, String subject, String text, MailType type) {
		for (User recipient : recipients) {
			if (recipient.getEnabled()) {
				sendMail(recipient.getEmail(), subject, text, type);
			}
		}
	}

	public void generateMultipleMails(MailTemplateConst mailTemplate, String authority, Map<String, Object> tags) {
		MailTemplate template =	mailService.findTemplateByModuleTypeAndDescription(mailTemplate.getCode(), mailTemplate.getDescription());
		List<User> users = userDAO.findMultipleByAuthorityName(authority);
		
		String mailSubject = TagUtil.convertEmailTags(template.getSubject(), tags);
		String mailContent = TagUtil.convertEmailTags(template.getText(), tags);
		sendMultipleMails(users, mailSubject, mailContent, template.getType());
	}
}
