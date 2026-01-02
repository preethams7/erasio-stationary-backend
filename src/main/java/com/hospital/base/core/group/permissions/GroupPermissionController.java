package com.hospital.base.core.group.permissions;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.exceptions.RecordNotFoundException;

@RestController
@PreAuthorize("hasAuthority('USER MANAGEMENT')")
@RequestMapping("/api/groups/permissions")
public class GroupPermissionController {

	@Autowired
	GroupPermissionService service;
	
	  @GetMapping
	    public ResponseEntity<List<GroupPermissionEntity>> getAllGroups() {
	        List<GroupPermissionEntity> list = service.getAllGroupsPermissions();
	 
	        return new ResponseEntity<List<GroupPermissionEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<GroupPermissionEntity> getGroupById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	GroupPermissionEntity entity = service.getGroupPermissionsById(id);
	 
	        return new ResponseEntity<GroupPermissionEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	  
	    
	    @PostMapping("/update")
	    public ResponseEntity<GroupPermissionEntity> updateGroup(@Valid @RequestBody GroupPermissionEntity group)
	                                                    throws RecordNotFoundException {
	    	GroupPermissionEntity updated = service.createUpdateGroupPermission(group);
	        return new ResponseEntity<GroupPermissionEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/delete")
	    public ResponseEntity<String> deleteGroup(@Valid @RequestBody GroupPermissionEntity group)
	                                                    throws RecordNotFoundException {
	    	if(group.getId()==1 || group.getId()==2) {
	    		return new ResponseEntity<String>("success", new HttpHeaders(), HttpStatus.LOCKED);
	    	}else {
	    		service.deleteGroupPermission(group);
	    		return new ResponseEntity<String>("success", new HttpHeaders(), HttpStatus.OK);
	    	}
	    	
	        
	    }
}
