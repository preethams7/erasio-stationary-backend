package com.hospital.base.core.group.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class GroupUsersService {

	@Autowired
	GroupUsersRepository repository;
	
	@Autowired
	GroupUsersService service;
	

	public GroupUsersService(GroupUsersRepository groupRepository) {
		this.repository = groupRepository;
	}

	public List<GroupUsersEntity> getAllGroupsUsers() {
		List<GroupUsersEntity> groupUsersList = repository.findAll();

		if (groupUsersList.size() > 0) {
			return groupUsersList;
		} else {
			return new ArrayList<GroupUsersEntity>();
		}
	}

	public GroupUsersEntity getGroupPermissionsById(Long id) throws RecordNotFoundException {
		Optional<GroupUsersEntity> entity = repository.findById(id);

		if (entity.isPresent()) {
			return entity.get();
		} else {
			throw new RecordNotFoundException("No Group Users exist for given id", id);
		}
	}


	public GroupUsersEntity createUpdateGroupUsers(GroupUsersEntity entity) throws RecordNotFoundException {
			entity = repository.save(entity);
		return entity;
	}
	
	public boolean deleteGroupUsers(GroupUsersEntity entity) throws RecordNotFoundException {
		repository.delete(entity);
	return true;
}

	 public List<GroupUsersEntity> findByGroupname(String name) {
	        return repository.findByGroupname(name);
	    }

	 public List<GroupUsersEntity> findByUsername(String name) {
	        return repository.findByUsername(name);
	    }
}
