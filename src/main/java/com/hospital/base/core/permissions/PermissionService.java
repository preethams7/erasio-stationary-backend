package com.hospital.base.core.permissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.base.exceptions.RecordNotFoundException;

@Service
public class PermissionService {
	
	 @Autowired
	 PermissionRepository repository;
	 
	 public List<PermissionEntity> getAllPermissions()
	    {
	        List<PermissionEntity> permissionList = repository.findAll();
	         
	        if(permissionList.size() > 0) {
	            return permissionList;
	        } else {
	            return new ArrayList<PermissionEntity>();
	        }
	    }
	 
	   public PermissionEntity getPermissionById(Long id) throws RecordNotFoundException
	    {
	        Optional<PermissionEntity> permission = repository.findById(id);
	         
	        if(permission.isPresent()) {
	            return permission.get();
	        } else {
	            throw new RecordNotFoundException("No permission exist for given id",id);
	        }
	    }
	     

}
