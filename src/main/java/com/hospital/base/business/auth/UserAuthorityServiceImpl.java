package com.hospital.base.business.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.hospital.base.core.account.authority.AccountAuthorityEntity;
import com.hospital.base.core.account.permissions.AccountPermissionEntity;

@Component
public class UserAuthorityServiceImpl {

	public Collection<SimpleGrantedAuthority> mapAuthorities(List<AccountAuthorityEntity> authority, List<AccountPermissionEntity> permission) {
		
		Collection<SimpleGrantedAuthority> accountAuthority = authority.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
		Collection<SimpleGrantedAuthority> permissions = permission.stream().map(perm -> new SimpleGrantedAuthority(perm.getPermission())).collect(Collectors.toList());
		List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		updatedAuthorities.addAll(accountAuthority);
		updatedAuthorities.addAll(permissions);
		

		

		/*
		 * SecurityContextHolder.getContext().setAuthentication( new
		 * UsernamePasswordAuthenticationToken(
		 * SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
		 * SecurityContextHolder.getContext().getAuthentication().getCredentials(),
		 * updatedAuthorities) );
		 */
		
		
		
		return updatedAuthorities;
		
	}
}
