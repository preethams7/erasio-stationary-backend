package com.hospital.base.core.account.accounts.bio;

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
@RequestMapping("/api/account-bio")
public class AccountBioController {

	@Autowired
	AccountBioService service;
	
	
	  @GetMapping
	    public ResponseEntity<List<AccountBioEntity>> getAllAccountBios() {
	        List<AccountBioEntity> list = service.getAllAccounts();
	 
	        return new ResponseEntity<List<AccountBioEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<AccountBioEntity> getAccountBioById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	AccountBioEntity entity = service.getAccountBioById(id);
	 
	        return new ResponseEntity<AccountBioEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	  
	    @GetMapping("/username={username}")
	    public ResponseEntity<AccountBioEntity> getAccountBioByUsername(@PathVariable("username") String username)
	                                                    throws RecordNotFoundException {
	    	AccountBioEntity entity = service.findByEmail(username);
	 
	    	return new ResponseEntity<AccountBioEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @GetMapping("/phone={phone}")
	    public ResponseEntity<AccountBioEntity> getAccountBioByPhone(@PathVariable("phone") String phone)
	                                                    throws RecordNotFoundException {
	    	AccountBioEntity entity = service.findByPhone(phone);
	 
	    	return new ResponseEntity<AccountBioEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @PostMapping("/create")
	    public ResponseEntity<AccountBioEntity> createAccountBio(@Valid @RequestBody AccountBioEntity account)
	                                                    throws RecordNotFoundException {
	    	AccountBioEntity updated = service.createAccount(account);
	        return new ResponseEntity<AccountBioEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @PostMapping("/update")
	    public ResponseEntity<AccountBioEntity> updateAccountBio(@Valid @RequestBody AccountBioEntity account)
	                                                    throws RecordNotFoundException {
	    	AccountBioEntity updated = service.updateAccountBio(account);
	        return new ResponseEntity<AccountBioEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
}
