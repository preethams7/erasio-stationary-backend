package com.hospital.base.admin.setting.subscriptions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.exceptions.RecordNotFoundException;
import com.hospital.base.user.subscriptions.AccountSubscriptionEntity;
import com.hospital.base.user.subscriptions.AccountSubscriptionService;

@RestController
@RequestMapping("/api/admin/subscriptions")
public class AdminSubscriptionController {
	@Autowired
	AccountSubscriptionService service;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('VIEW SUBSCRIPTIONS')")
	public ResponseEntity<List<AccountSubscriptionEntity>> getAllUserSubscriptions() {
		List<AccountSubscriptionEntity> list = service.getAllSubscrptions();
		return new ResponseEntity<List<AccountSubscriptionEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('VIEW SUBSCRIPTIONS')")
	public ResponseEntity<AccountSubscriptionEntity> getsubscriptionsById(@PathVariable("id") Long id)
			throws RecordNotFoundException {
		AccountSubscriptionEntity entity = service.getAccountSubscriptionById(id);

		return new ResponseEntity<AccountSubscriptionEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/username={username}")
	@PreAuthorize("hasAuthority('VIEW SUBSCRIPTIONS')")
	public ResponseEntity<List<AccountSubscriptionEntity>> getSubReqByUsername(
			@PathVariable("username") String username) throws RecordNotFoundException {
		List<AccountSubscriptionEntity> entity = service.getAccountSubscriptionByUsername(username);

		return new ResponseEntity<List<AccountSubscriptionEntity>>(entity, new HttpHeaders(), HttpStatus.OK);
	}
}
