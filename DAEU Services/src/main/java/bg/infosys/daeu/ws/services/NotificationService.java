package bg.infosys.daeu.ws.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.infosys.daeu.db.dao.pub.NotificationDAO;
import bg.infosys.daeu.db.dao.security.UserDAO;
import bg.infosys.daeu.db.entity.pub.MailTemplate;
import bg.infosys.daeu.db.entity.pub.MailTemplate.MailTemplateConst;
import bg.infosys.daeu.db.entity.pub.Notification;
import bg.infosys.daeu.db.entity.security.User;
import bg.infosys.daeu.ws.pub.util.TagUtil;

@Service
public class NotificationService {
	@Autowired private UserDAO userDAO;
	@Autowired private MailService mailService;
	
	@Autowired private NotificationDAO notificationDAO;

	@Transactional
	public void saveOrUpdate(List<Notification> notifications) {
		for (Notification notification : notifications) {
			notificationDAO.saveOrUpdate(notification);
		}
	}
	
	@Transactional
	public void sendNotificationsToAuth(String role, MailTemplateConst templateConst, Map<String, Object> tags) {
		MailTemplate template =	mailService.
				findTemplateByModuleTypeAndDescription(templateConst.getCode(), templateConst.getDescription());
		List<User> users = userDAO.findMultipleByAuthorityNameAndEnabled(role);
		List<Notification> notifications = new ArrayList<>();
		for (User user : users) {
			notifications.add(new Notification(TagUtil.convertEmailTags(template.getText(), tags), user));
		}
		saveOrUpdate(notifications);
	}
}
