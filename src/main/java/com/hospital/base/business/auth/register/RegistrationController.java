package com.hospital.base.business.auth.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.core.account.accounts.AccountsEntity;
import com.hospital.base.core.account.accounts.AccountsService;
import com.hospital.base.core.email.EmailService;
import com.hospital.base.core.features.FeatureService;
import com.hospital.base.core.notifications.NotificationsAction;

@RestController
@CrossOrigin
public class RegistrationController {
	
	@Autowired
	RegistrationService service;
	
	@Autowired
	FeatureService featureService;
	
	@Autowired
	AccountsService accountsService;
	
	@Autowired
    private UserValidator userValidator;
	
	@Autowired 
	NotificationsAction notif;
	
//	@Autowired
//	private EmailService emailService;

	
	@RequestMapping(value = "/api/register", method = RequestMethod.POST)
	public ResponseEntity<Object> registration(@RequestBody AccountsEntity registrationRequest, BindingResult bindingResult)
	        throws Exception {

	    String status = "failed";
	    if (featureService.checkFeatureStatus("REGISTER")) {
	        userValidator.validate(registrationRequest, bindingResult);

	        if (bindingResult.hasErrors()) {
	            List<ObjectError> errors = bindingResult.getAllErrors();
	            
	            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	        } else {
	            AccountsEntity account = accountsService.createAccount(registrationRequest);
	            status = "success";

	            
//	            notif.createNotification("WELCOME", registrationRequest.getUsername());

	            
	            SimpleMailMessage mail = new SimpleMailMessage();
	            mail.setFrom("no-reply@erasio.com");
	            mail.setTo(account.getUsername());
	            mail.setSubject("Erasio: Account Verification");
	            mail.setText(
	                "Dear User,\n\n" +
	                "Thank you for signing up with Erasio. Please use the following code to verify your account:\n\n" +
	                account.getTempAuthCode() + "\n\n" +
	                "If you did not request this, please ignore this email or contact support immediately.\n\n" +
	                "Best regards,\n" +
	                "The Erasio Team"
	            );

//	            emailService.sendEmail(mail);

	            
	            return new ResponseEntity<>(status, HttpStatus.CREATED);
	        }
	    } else {
	        status = "disabled";
	        
	        return new ResponseEntity<>(status, HttpStatus.FORBIDDEN);
	    }
	
}}
