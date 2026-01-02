package com.hospital.base.business.auth.jwt;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hospital.base.user.subscriptions.AccountSubscriptionEntity;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String username;
	private final String lastSession;
	private final String refreshtoken;

	private final Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String jwttoken, UserDetails userDetails, String lastSession,String refreshtoken) {
		this.jwttoken = jwttoken;
		this.authorities = userDetails.getAuthorities();
		this.username = userDetails.getUsername();
		this.lastSession = lastSession;
		this.refreshtoken=refreshtoken;

	}

	public String getLastSession() {
		return lastSession;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public String getUsername() {
		return username;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public String getRefreshtoken() {
		return refreshtoken;
	}
	
}
