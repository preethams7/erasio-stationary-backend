package com.hospital.base.core.permissions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

	@Autowired
	PermissionService service;
	
	  @GetMapping
	    public ResponseEntity<List<PermissionEntity>> getAllPermission() {
	        List<PermissionEntity> list = service.getAllPermissions();
	 
	        return new ResponseEntity<List<PermissionEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<PermissionEntity> getRoleById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	PermissionEntity entity = service.getPermissionById(id);
	 
	        return new ResponseEntity<PermissionEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
}
