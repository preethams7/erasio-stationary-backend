package com.hospital.base.core.group.groups;
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
@RequestMapping("/api/groups")
public class GroupsController {

	@Autowired
	GroupsService service;
	
	  @GetMapping
	    public ResponseEntity<List<GroupsEntity>> getAllGroups() {
	        List<GroupsEntity> list = service.getAllGroups();
	 
	        return new ResponseEntity<List<GroupsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<GroupsEntity> getGroupById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	GroupsEntity entity = service.getGroupsById(id);
	 
	        return new ResponseEntity<GroupsEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	  
	    
	    @PostMapping("/update")
	    public ResponseEntity<GroupsEntity> updateGroup(@Valid @RequestBody GroupsEntity group)
	                                                    throws RecordNotFoundException {
	    	GroupsEntity updated = service.createUpdateGroup(group);
	        return new ResponseEntity<GroupsEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @DeleteMapping("/delete")
	    public ResponseEntity<String> deleteGroup(@Valid @RequestBody GroupsEntity group)
	                                                    throws RecordNotFoundException {
	    	if(group.getId()==1 || group.getId()==2) {
	    		return new ResponseEntity<String>("success", new HttpHeaders(), HttpStatus.LOCKED);
	    	}else {
	    		service.deleteGroup(group);
	    		return new ResponseEntity<String>("success", new HttpHeaders(), HttpStatus.OK);
	    	}
	    	
	        
	    }
}
