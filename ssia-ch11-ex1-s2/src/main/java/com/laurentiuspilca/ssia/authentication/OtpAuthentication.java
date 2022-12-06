package com.laurentiuspilca.ssia.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class OtpAuthentication extends UsernamePasswordAuthenticationToken {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8083543454065921135L;

	public OtpAuthentication(Object principal, Object credentials) {
		
		super(principal, credentials);
	}
	
	public OtpAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		
		super(principal, credentials, authorities);
	}
}
