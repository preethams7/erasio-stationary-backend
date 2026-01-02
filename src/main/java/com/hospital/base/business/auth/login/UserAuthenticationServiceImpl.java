package com.hospital.base.business.auth.login;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.base.business.auth.UserAuthorityServiceImpl;
import com.hospital.base.core.account.accounts.AccountsEntity;
import com.hospital.base.core.account.accounts.AccountsRepository;
import com.hospital.base.core.account.accounts.AccountsService;
import com.hospital.base.core.account.authority.AccountAuthorityService;
import com.hospital.base.core.account.permissions.AccountPermissionService;

@RestController
@RequestMapping("/api/login")
public class UserAuthenticationServiceImpl implements UserDetailsService{
	
	@Autowired
	AccountsService service;
	
	@Autowired 
	AccountsRepository userRepository;
	
	@Autowired 
	AccountAuthorityService authService;
	
	@Autowired 
	AccountPermissionService permService;
	
	@Autowired
	UserAuthorityServiceImpl authSrvImpl;
	
	@Autowired 
	AccountsService accService;
	
	
	 @Override
	    @Transactional
	    public UserDetails loadUserByUsername(String username) {
	        AccountsEntity user = userRepository.findByUsername(username);
	        if (user == null) throw new UsernameNotFoundException(username);
	        UserDetails userDetail = accService.loadUserByUsername(username);
			return new org.springframework.security.core.userdetails.User(userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
	    }


}
