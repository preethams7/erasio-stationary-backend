package com.hospital.base.business.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hospital.base.business.auth.UserAuthorityServiceImpl;
import com.hospital.base.core.account.accounts.AccountsEntity;
import com.hospital.base.core.account.accounts.AccountsRepository;
import com.hospital.base.core.account.accounts.AccountsService;
import com.hospital.base.core.account.authority.AccountAuthorityService;
import com.hospital.base.core.account.permissions.AccountPermissionService;

@Primary
@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	AccountsRepository repository;

	@Autowired
	AccountAuthorityService authService;
	
	@Autowired 
	AccountPermissionService permService;
	
	@Autowired
	UserAuthorityServiceImpl authSrvImpl;
	
	@Autowired 
	AccountsService accService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountsEntity user = repository.findByUsername(username);
		if (user!=null) {
			UserDetails userDetails = accService.loadUserByUsername(user.getUsername());
			return new User(userDetails.getUsername(), userDetails.getPassword(),userDetails.getAuthorities());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		
	}

}