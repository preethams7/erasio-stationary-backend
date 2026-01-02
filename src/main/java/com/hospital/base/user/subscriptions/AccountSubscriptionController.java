package com.hospital.base.user.subscriptions;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.HandlerMapping;

import com.hospital.base.business.auth.jwt.JwtTokenUtil;
import com.hospital.base.core.features.FeatureService;
import com.hospital.base.core.notifications.NotificationsAction;
import com.hospital.base.exceptions.FeatureDisabledException;
import com.hospital.base.exceptions.RecordNotFoundException;
import com.hospital.base.user.financial.Payment.PaymentActions;
import com.hospital.base.user.financial.Payment.UserPaymentEntity;
import com.hospital.base.utils.DateTimeUtils;

@RestController
@RequestMapping("/api/user/subscription")
public class AccountSubscriptionController {

	@Autowired
	AccountSubscriptionService service;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	PaymentActions paymentActions;

	@Autowired
	FeatureService featureService;
	
	@Autowired
	DateTimeUtils dtu;
	
	@Autowired 
	NotificationsAction notif;

	@GetMapping
	public ResponseEntity<List<AccountSubscriptionEntity>> getAllUserSubscriptions(HttpServletRequest request) {
		 final String requestTokenHeader = request.getHeader("Authorization");
		  String username = null ;
		  String jwtToken = null;
		  if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			  jwtToken = requestTokenHeader.substring(7);
			  username = jwtTokenUtil.getUsernameFromToken(jwtToken);	  
		  }else {
			  Principal principal = request.getUserPrincipal();
		      username =  principal.getName();
		  }
		List<AccountSubscriptionEntity> list = service.getAllAccountSubscrptions(username);

		return new ResponseEntity<List<AccountSubscriptionEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/status={state}")
	public ResponseEntity<List<AccountSubscriptionEntity>> getAllUserSubscriptionsByState(HttpServletRequest request,
			@PathVariable("state") String state) {
		 final String requestTokenHeader = request.getHeader("Authorization");
		  String username = null ;
		  String jwtToken = null;
		  if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			  jwtToken = requestTokenHeader.substring(7);
			  username = jwtTokenUtil.getUsernameFromToken(jwtToken);	  
		  }else {
			  Principal principal = request.getUserPrincipal();
		      username =  principal.getName();
		  }
		List<AccountSubscriptionEntity> list = service.getAllAccountSubscriptionsByState(username, state);

		return new ResponseEntity<List<AccountSubscriptionEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountSubscriptionEntity> getsubscriptionsById(@PathVariable("id") Long id,
			HttpServletRequest request) throws RecordNotFoundException {
		 final String requestTokenHeader = request.getHeader("Authorization");
		  String username = null ;
		  String jwtToken = null;
		  if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			  jwtToken = requestTokenHeader.substring(7);
			  username = jwtTokenUtil.getUsernameFromToken(jwtToken);	  
		  }else {
			  Principal principal = request.getUserPrincipal();
		      username =  principal.getName();
		  }
		AccountSubscriptionEntity entity = service.getAccountSubscriptionByIdAndUsername(id, username);

		return new ResponseEntity<AccountSubscriptionEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<AccountSubscriptionEntity> createUserSubscription(
			@Valid @RequestBody AccountSubscriptionDO entity, HttpServletRequest request)
			throws RecordNotFoundException {
		 final String requestTokenHeader = request.getHeader("Authorization");
		  String username = null ;
		  String jwtToken = null;
		  if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			  jwtToken = requestTokenHeader.substring(7);
			  username = jwtTokenUtil.getUsernameFromToken(jwtToken);	  
		  }else {
			  Principal principal = request.getUserPrincipal();
		      username =  principal.getName();
		  }
		
		if (featureService.checkFeatureStatuswithToken("SUBSCRIPTION",jwtToken)) {
			UserPaymentEntity paymentEntity = paymentActions.processPaymentDO(username, entity.getPayment());
			AccountSubscriptionEntity accSubEntity = new AccountSubscriptionEntity(); 
			List<AccountSubscriptionEntity> list = service.getAllAccountSubscrptions(username);
			if(list!=null && list.size()>0) {
				String lastDate = dtu.getCurrentDate();
				String newSubDate = "";
				for (AccountSubscriptionEntity accountSubscriptionEntity : list) {
					if(dtu.dateCompare(lastDate, accountSubscriptionEntity.getExpires())<0) {
						lastDate = accountSubscriptionEntity.getExpires();
					}
				}
				newSubDate = dtu.addDays(lastDate, 1);
				accSubEntity.setSubdate(newSubDate);
				accSubEntity.setExpires(dtu.addDays(newSubDate, entity.getDuration()));
			}else {
				accSubEntity.setSubdate(dtu.getCurrentDate());
				accSubEntity.setExpires(dtu.addDays(dtu.getCurrentDate(), entity.getDuration()));
			}
			accSubEntity = service.createUserSubscription(accSubEntity, username, paymentEntity);
			if(accSubEntity!=null) {
				notif.createNotification("NEW SUBSCRIPTION",username);
				return new ResponseEntity<AccountSubscriptionEntity>(accSubEntity, new HttpHeaders(), HttpStatus.OK);
			}else {
				AccountSubscriptionEntity ase = new AccountSubscriptionEntity();
				String newSubDate = dtu.addDays(dtu.getCurrentDate(), -1);
				ase.setSubdate(newSubDate);
				ase.setExpires(newSubDate);
				return new ResponseEntity<AccountSubscriptionEntity>(ase, new HttpHeaders(), HttpStatus.OK);
			}
			
			
		} else {
			throw new FeatureDisabledException("Feature has been disabled. Contact Administrator for support.",
					request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE));
		}
	}
}
