package com.hospital.base.admin.setting.messaging;

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
@RequestMapping("/api/messaging")
public class MessagingController {

	@Autowired
	MessagingService service;
	
	  @GetMapping
	  @PreAuthorize("hasAuthority('MANAGE NOTIFICATION')")
	    public ResponseEntity<List<MessagingEntity>> getAllMessaging() {
	        List<MessagingEntity> list = service.getAllMessaging();
	 
	        return new ResponseEntity<List<MessagingEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{id}")
	    @PreAuthorize("hasAuthority('MANAGE NOTIFICATION')")
	    public ResponseEntity<MessagingEntity> getMessagingById(@PathVariable("id") Long id)
	                                                    throws RecordNotFoundException {
	    	MessagingEntity entity = service.getMessagingById(id);
	 
	        return new ResponseEntity<MessagingEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	  
	    @GetMapping("/name={topic}")
	    @PreAuthorize("hasAuthority('MANAGE NOTIFICATION')")
	    public ResponseEntity<MessagingEntity> getMessagingByTopic(@PathVariable("topic") String name)
	                                                    throws RecordNotFoundException {
	    	MessagingEntity entity = service.getMessagingByTopic(name);
	 
	        return new ResponseEntity<MessagingEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    
	    
	    @PostMapping("/create")
	    @PreAuthorize("hasAuthority('MANAGE NOTIFICATION')")
	    public ResponseEntity<MessagingEntity> createMessaging(@Valid @RequestBody MessagingEntity entity)
	                                                    throws RecordNotFoundException {
	    	MessagingEntity updated = service.createMessaging(entity);
	        return new ResponseEntity<MessagingEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	    @PreAuthorize("hasAuthority('MANAGE NOTIFICATION')")
	    @PostMapping("/update")
	    public ResponseEntity<MessagingEntity> updateMessaging(@Valid @RequestBody MessagingEntity entity)
	                                                    throws RecordNotFoundException {
	    	MessagingEntity updated = service.updateMessaging(entity);
	        return new ResponseEntity<MessagingEntity>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    
	   
}
