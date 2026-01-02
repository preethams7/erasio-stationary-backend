package com.hospital.base.core.account.role;

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
@RequestMapping("/api/accountRole")
public class AccountRoleController {

	@Autowired
	AccountRoleService service;
	
	  @GetMapping
	    public ResponseEntity<List<AccountRoleEntity>> getAllRoles() {
	        List<AccountRoleEntity> list = service.getAllRoles();
	 
	        return new ResponseEntity<List<AccountRoleEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<AccountRoleEntity> getRoleById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	AccountRoleEntity entity = service.getRoleById(id);
	 
	        return new ResponseEntity<AccountRoleEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
}
