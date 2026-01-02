package com.hospital.base.core.group.groups;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class GroupsService {

	@Autowired
	GroupsRepository repository;
	
	@Autowired
	GroupsService service;
	

	public GroupsService(GroupsRepository groupRepository) {
		this.repository = groupRepository;
	}

	public List<GroupsEntity> getAllGroups() {
		List<GroupsEntity> groupList = repository.findAll();

		if (groupList.size() > 0) {
			return groupList;
		} else {
			return new ArrayList<GroupsEntity>();
		}
	}

	public GroupsEntity getGroupsById(Long id) throws RecordNotFoundException {
		Optional<GroupsEntity> feature = repository.findById(id);

		if (feature.isPresent()) {
			return feature.get();
		} else {
			throw new RecordNotFoundException("No Group exist for given id", id);
		}
	}


	public GroupsEntity createUpdateGroup(GroupsEntity entity) throws RecordNotFoundException {
			entity = repository.save(entity);
		return entity;
	}
	
	public boolean deleteGroup(GroupsEntity entity) throws RecordNotFoundException {
		repository.delete(entity);
	return true;
}

	 public GroupsEntity findByGroupname(String name) {
	        return repository.findByGroupname(name);
	    }

}
