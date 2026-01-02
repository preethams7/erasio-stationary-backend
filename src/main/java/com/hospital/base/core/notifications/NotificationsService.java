package com.hospital.base.core.notifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class NotificationsService {

	@Autowired
	NotificationsRepository repository;

	public List<NotificationsEntity> getAllNotifications() {
		List<NotificationsEntity> list = repository.findAll();
		if (list.size() > 0) {
			return list;
		} else {
			return new ArrayList<NotificationsEntity>();
		}
	}

	public List<NotificationsEntity> getAllUserNotifications(String username) {
		List<NotificationsEntity> list = repository.findByUsername(username);
		if (list.size() > 0) {
			return list;
		} else {
			return new ArrayList<NotificationsEntity>();
		}
	}

	public NotificationsEntity getNotificationById(Long id) throws RecordNotFoundException {
		Optional<NotificationsEntity> permission = repository.findById(id);

		if (permission.isPresent()) {
			return permission.get();
		} else {
			throw new RecordNotFoundException("No notification exist for given id", id);
		}
	}
	
	public NotificationsEntity getNotificationByUserAndId(String username, Long id) throws RecordNotFoundException {
		Optional<NotificationsEntity> permission = repository.findByUsernameAndId(username,id);

		if (permission.isPresent()) {
			return permission.get();
		} else {
			throw new RecordNotFoundException("No notification exist for given id", id);
		}
	}
	
	public List<NotificationsEntity> getAllUserNotificationsByReadStatus(boolean status) {
		List<NotificationsEntity> list = repository.findByRead(status);
		if (list.size() > 0) {
			return list;
		} else {
			return new ArrayList<NotificationsEntity>();
		}
	}
	
	public List<NotificationsEntity> getAllUserNotificationsByUsernameAndReadStatus(String username, boolean status) {
		List<NotificationsEntity> list = repository.findByUsernameAndRead(username, status);
		if (list.size() > 0) {
			return list;
		} else {
			return new ArrayList<NotificationsEntity>();
		}
	}
	
	public NotificationsEntity createNotification(NotificationsEntity entity) {
		entity = repository.save(entity);
		return entity;
	}

	public NotificationsEntity updateNotification(NotificationsEntity updated) {
		NotificationsEntity entity = repository.save(updated);
		return entity;
	}

	
}
