package com.hospital.base.core.account.accounts;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.core.account.permissions.AccountPermissionEntity;
import com.hospital.base.core.account.permissions.AccountPermissionService;
import com.hospital.base.exceptions.RecordNotFoundException;

@RestController
//@PreAuthorize("hasAuthority('USER MANAGEMENT')")
@RequestMapping("/api/accounts")
public class AccountsController {

	@Autowired
	AccountsService service;
	
	@Autowired
	AccountPermissionService permservice;
	
	  @GetMapping
	    public ResponseEntity<List<AccountsEntity>> getAllAccounts() {
	        List<AccountsEntity> list = service.getAllAccounts();
	 
	        return new ResponseEntity<List<AccountsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<AccountsEntity> getAccountById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	AccountsEntity entity = service.getAccountById(id);
	 
	        return new ResponseEntity<AccountsEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	  
	    @GetMapping("/permission/username={username}")
	    public ResponseEntity<List<AccountPermissionEntity>> getAccountById(@PathVariable("username") String username)
	                                                    throws RecordNotFoundException {
	    	List<AccountPermissionEntity> entity = permservice.getPermissionsByUser(username);
	 
	    	return new ResponseEntity<List<AccountPermissionEntity>>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @PostMapping
	    public ResponseEntity<AccountsEntity> updateAccount(@Valid @RequestBody AccountsEntity account)
	                                                    throws RecordNotFoundException {
	    	AccountsEntity updated = service.updateAccount(account);
	        return new ResponseEntity<AccountsEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    @PostMapping("/create")
	    public ResponseEntity<AccountsEntity> createAccount(@RequestBody AccountsEntity accountsEntity) {
	    	AccountsEntity account = service.createAccount(accountsEntity);
	    	return ResponseEntity.ok(account);
	    }
}
