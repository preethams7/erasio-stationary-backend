package com.hospital.base.business.auth;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.core.account.accounts.AccountsEntity;
import com.hospital.base.core.account.accounts.AccountsService;
import com.hospital.base.core.email.EmailService;

@RestController
@CrossOrigin
public class AuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private EmailService emailService;

	@Autowired
	AccountsService accService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String baseContext() throws Exception {
		return "redirect:/welcome";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ResponseEntity<String> welcome() throws Exception {
		return new ResponseEntity<String>("Welcome", new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<AccountsEntity> forgotPassword(@Valid @RequestBody AccountsEntity account,
			HttpServletRequest request) throws Exception {

		// Lookup user in database by e-mail
		AccountsEntity optional = accService.findByUsername(account.getUsername());

		if (optional == null) {
			return new ResponseEntity<AccountsEntity>(new AccountsEntity(), new HttpHeaders(), HttpStatus.OK);
		} else {
			optional.setTempAuthCode(UUID.randomUUID().toString());
			accService.updateAccount(optional);

			logger.info("Sending an email for password reset");
			String appUrl = request.getScheme() + "://" + request.getServerName();
			SimpleMailMessage mail = new SimpleMailMessage();

			mail.setFrom("no-reply@evique-digital.com");
			mail.setTo(optional.getUsername());
			mail.setSubject("Evique-Digital: Password Reset Request");
			mail.setText("To reset your password, click the link below:\n" + appUrl + "/validatePasswordReset?token="
					+ optional.getTempAuthCode());
			emailService.sendEmail(mail);

			return new ResponseEntity<AccountsEntity>(optional, new HttpHeaders(), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/validatePasswordReset", method = RequestMethod.POST)
	public ResponseEntity<String> forgotPassword(@RequestParam("token") String token) throws Exception {

		AccountsEntity user = accService.findByTempAuthCode(token);

		if (user != null) {

			return new ResponseEntity<String>("User Validated successfully.", new HttpHeaders(), HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("The token is invalid.", new HttpHeaders(), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/validateotp", method = RequestMethod.POST)
	public ResponseEntity<String> validateOtp(@RequestParam("token") String token,
			@RequestParam("username") String username) throws Exception {

		AccountsEntity user = accService.findByTempAuthCodeAndUsername(token, username);

		if (user != null) {
			accService.validateEmail(user);

			return new ResponseEntity<String>("User Validated successfully.", new HttpHeaders(), HttpStatus.OK);

		} else {
			return new ResponseEntity<String>("The token is invalid.", new HttpHeaders(), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/resetPassword", method = RequestMethod.POST)
	public ResponseEntity<String> resetPassword(@Valid @RequestBody AccountsEntity account) throws Exception {

		AccountsEntity optional = accService.findByUsername(account.getUsername());

		if (account.getPassword().equals(account.getPasswordConfirm())) {
			optional.setPassword(account.getPassword());
			account = accService.updateAccountPassword(optional);
			account = accService.clearToken(account);
		}

		return new ResponseEntity<String>("Account updated successfully.", new HttpHeaders(), HttpStatus.OK);

	}

}
