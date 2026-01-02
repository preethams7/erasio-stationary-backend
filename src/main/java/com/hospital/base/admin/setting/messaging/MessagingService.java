package com.hospital.base.admin.setting.messaging;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class MessagingService {

	@Autowired
	MessagingRepository repository;

	
	
	public MessagingService(MessagingRepository repo) {
		this.repository = repo;
	}

	public List<MessagingEntity> getAllMessaging() {
		List<MessagingEntity> objList = repository.findAll();

		if (objList.size() > 0) {
			return objList;
		} else {
			return new ArrayList<MessagingEntity>();
		}
	}

	
	public MessagingEntity getMessagingById(Long id) throws RecordNotFoundException {
		Optional<MessagingEntity> obj = repository.findById(id);

		if (obj.isPresent()) {
			return obj.get();
		} else {
			throw new RecordNotFoundException("No Message exist for given id", id);
		}
	}
	
	public MessagingEntity getMessagingByTopic(String Name) throws RecordNotFoundException {
		MessagingEntity obj = repository.findByTopicIgnoreCase(Name);

		return obj;
	}

	
	public MessagingEntity createMessaging(MessagingEntity entity) throws RecordNotFoundException {
			entity = repository.save(entity);
			entity = repository.findByTopic(entity.getTopic());
		return entity;
	}
	


	public MessagingEntity updateMessaging(MessagingEntity entity) throws RecordNotFoundException {
		if (entity.getId() != null) {
			Optional<MessagingEntity> obj = repository.findById(entity.getId());
			if (obj.isPresent()) {
				MessagingEntity newEntity = obj.get();
				/**
				 * Topics are predefined and integrated to code logic. Do not allow editing of topics.
				 * New topciSs can be added using the data-mysql.sql file
				 */
				//	newEntity.setTopic(entity.getTopic()!=null?entity.getTopic():obj.get().getTopic());
				newEntity.setSubject(entity.getSubject()!=null?entity.getSubject():obj.get().getSubject());
				newEntity.setBody(entity.getBody()!=null?entity.getBody():obj.get().getBody());
				newEntity = repository.save(newEntity);
				return newEntity;
			} else {
				return entity;
			}
		}
		throw new RecordNotFoundException("No Message exist for given name", entity.getTopic());
	}
}
