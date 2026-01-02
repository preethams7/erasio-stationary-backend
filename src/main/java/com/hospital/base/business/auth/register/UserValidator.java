package com.hospital.base.business.auth.register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hospital.base.core.account.accounts.AccountsEntity;
import com.hospital.base.core.account.accounts.AccountsService;

@Component
public class UserValidator implements Validator{

	@Autowired
    private AccountsService userService;
	
	 @Override
	    public boolean supports(Class<?> aClass) {
	        return AccountsEntity.class.equals(aClass);
	    }

	 
	 @Override
	    public void validate(Object o, Errors errors) {
	        AccountsEntity user = (AccountsEntity) o;

	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
	        if (user.getUsername().length() < 6 ) {
	            errors.rejectValue("username", "The username must be more than 6 characters");
	        }
	        if (userService.findByUsername(user.getUsername()) != null) {
	            errors.rejectValue("username", "Email already registered");
	        }

	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
	        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
	            errors.rejectValue("password", "The [password must be more than 8 characters and less than 32 characters");
	        }

	        if (!user.getPasswordConfirm().equals(user.getPassword())) {
	            errors.rejectValue("passwordConfirm", "Passwords do not match");
	        }
	    }
}
