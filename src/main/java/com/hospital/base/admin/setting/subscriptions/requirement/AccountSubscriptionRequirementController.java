package com.hospital.base.admin.setting.subscriptions.requirement;

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

import com.hospital.base.exceptions.RecordNotFoundException;

@RestController
@PreAuthorize("hasAuthority('VIEW SUBSCRIPTIONS')")
@RequestMapping("/api/subscription-check")
public class AccountSubscriptionRequirementController {

	@Autowired
	AccountSubscriptionRequirementService service;
	
	  @GetMapping
	    public ResponseEntity<List<AccountSubscriptionRequirementEntity>> getAllAccounts() {
	        List<AccountSubscriptionRequirementEntity> list = service.getAllAccountSubscrptionRequirementss();
	 
	        return new ResponseEntity<List<AccountSubscriptionRequirementEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id}")
	    public ResponseEntity<AccountSubscriptionRequirementEntity> getsubReqById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	AccountSubscriptionRequirementEntity entity = service.getAccountSubscriptionequirementsById(id);
	 
	        return new ResponseEntity<AccountSubscriptionRequirementEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	  
	    @GetMapping("/username={username}")
	    public ResponseEntity<AccountSubscriptionRequirementEntity> getSubReqByUsername(@PathVariable("username") String username)
	                                                    throws RecordNotFoundException {
	    	AccountSubscriptionRequirementEntity entity = service.getAccountSubscriptionRequirementByUsername(username);
	 
	    	return new ResponseEntity<AccountSubscriptionRequirementEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @PostMapping("/update")
	    public ResponseEntity<AccountSubscriptionRequirementEntity> updateAccount(@Valid @RequestBody AccountSubscriptionRequirementEntity entity)
	                                                    throws RecordNotFoundException {
	    	AccountSubscriptionRequirementEntity updated = service.updateAccountSubscrptionRequirements(entity);
	        return new ResponseEntity<AccountSubscriptionRequirementEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
}
