package com.hospital.base.core.group.permissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class GroupPermissionService {

	@Autowired
	GroupPermissionRepository repository;
	
	@Autowired
	GroupPermissionService service;
	

	public GroupPermissionService(GroupPermissionRepository groupRepository) {
		this.repository = groupRepository;
	}

	public List<GroupPermissionEntity> getAllGroupsPermissions() {
		List<GroupPermissionEntity> groupList = repository.findAll();

		if (groupList.size() > 0) {
			return groupList;
		} else {
			return new ArrayList<GroupPermissionEntity>();
		}
	}

	public GroupPermissionEntity getGroupPermissionsById(Long id) throws RecordNotFoundException {
		Optional<GroupPermissionEntity> feature = repository.findById(id);

		if (feature.isPresent()) {
			return feature.get();
		} else {
			throw new RecordNotFoundException("No Group Permission exist for given id", id);
		}
	}


	public GroupPermissionEntity createUpdateGroupPermission(GroupPermissionEntity entity) throws RecordNotFoundException {
			entity = repository.save(entity);
		return entity;
	}
	
	public boolean deleteGroupPermission(GroupPermissionEntity entity) throws RecordNotFoundException {
		repository.delete(entity);
	return true;
}

	 public GroupPermissionEntity findByGroupname(String name) {
	        return repository.findByGroupname(name);
	    }

	 public GroupPermissionEntity findByPermission(String name) {
	        return repository.findByPermission(name);
	    }
}
