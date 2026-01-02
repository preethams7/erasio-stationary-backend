package com.hospital.base.core.account.authority;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.exceptions.RecordNotFoundException;

@RestController
@RequestMapping("/api/authority")
public class AccountAuthorityController {

	@Autowired
	AccountAuthorityService service;
	
	  @GetMapping
	    public ResponseEntity<List<AccountAuthorityEntity>> getAllAuthorities() {
	        List<AccountAuthorityEntity> list = service.getAllAuthorities();
	 
	        return new ResponseEntity<List<AccountAuthorityEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<AccountAuthorityEntity> getAccountById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	AccountAuthorityEntity entity = service.getAuthorityById(id);
	 
	        return new ResponseEntity<AccountAuthorityEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	  
	    
	    @PostMapping
	    public ResponseEntity<AccountAuthorityEntity> updateAccountStatus(@Valid @RequestBody AccountAuthorityEntity authority)
	                                                    throws RecordNotFoundException {
	    	AccountAuthorityEntity updated = service.createOrUpdateAuthority(authority);
	        return new ResponseEntity<AccountAuthorityEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
}
