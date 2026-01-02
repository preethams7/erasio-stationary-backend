package com.hospital.base.core.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospital.base.admin.setting.messaging.MessagingEntity;
import com.hospital.base.admin.setting.messaging.MessagingService;
import com.hospital.base.core.features.FeatureService;
import com.hospital.base.utils.DateTimeUtils;

@Component
public class NotificationsAction {

	@Autowired
	FeatureService featureService;
	
	@Autowired
	NotificationsService service;
	
	@Autowired 
	MessagingService msgSsrvc;
	
	@Autowired 
	DateTimeUtils dtu;
	
	public void createNotification(String topic, String username) {
		NotificationsEntity notification = new NotificationsEntity();
		if (featureService.checkFeatureStatus("NOTIFICATIONS")) {
			notification.setUsername(username);
			MessagingEntity msg = msgSsrvc.getMessagingByTopic(topic);
			notification.setSubject(msg.getSubject());
			notification.setMessage(msg.getBody());
			notification.setTimestamp(dtu.getCurrentDateTime());
			notification.setRead(false);
			service.createNotification(notification);
		}
		
	}
}
