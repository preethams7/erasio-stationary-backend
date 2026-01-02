package com.hospital.base.core.group.users;
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
@RequestMapping("/api/groups/users")
public class GroupUsersController {

	@Autowired
	GroupUsersService service;
	
	  @GetMapping
	    public ResponseEntity<List<GroupUsersEntity>> getAllGroupUsers() {
	        List<GroupUsersEntity> list = service.getAllGroupsUsers();
	 
	        return new ResponseEntity<List<GroupUsersEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<GroupUsersEntity> getGroupUsersById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	GroupUsersEntity entity = service.getGroupPermissionsById(id);
	 
	        return new ResponseEntity<GroupUsersEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	  
	    @GetMapping("/group={groupname}")
	    public ResponseEntity<List<GroupUsersEntity>> getGroupUsersByGroupName(@PathVariable("groupname") String groupname)
	                                                    throws RecordNotFoundException {
	    	List<GroupUsersEntity> entity = service.findByGroupname(groupname);
	 
	        return new ResponseEntity<List<GroupUsersEntity>>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	  
	    @GetMapping("/user={username}")
	    public ResponseEntity<List<GroupUsersEntity>> getGroupsByUserName(@PathVariable("username") String username)
	                                                    throws RecordNotFoundException {
	    	List<GroupUsersEntity> entity = service.findByUsername(username);
	 
	        return new ResponseEntity<List<GroupUsersEntity>>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @PostMapping("/update")
	    public ResponseEntity<GroupUsersEntity> updateGroupUsers(@Valid @RequestBody GroupUsersEntity group)
	                                                    throws RecordNotFoundException {
	    	GroupUsersEntity updated = service.createUpdateGroupUsers(group);
	        return new ResponseEntity<GroupUsersEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/delete")
	    public ResponseEntity<String> deleteGroupUsers(@Valid @RequestBody GroupUsersEntity group)
	                                                    throws RecordNotFoundException {
	    	if(group.getId()==1 || group.getId()==2) {
	    		return new ResponseEntity<String>("success", new HttpHeaders(), HttpStatus.LOCKED);
	    	}else {
	    		service.deleteGroupUsers(group);
	    		return new ResponseEntity<String>("success", new HttpHeaders(), HttpStatus.OK);
	    	}
	    	
	        
	    }
}
