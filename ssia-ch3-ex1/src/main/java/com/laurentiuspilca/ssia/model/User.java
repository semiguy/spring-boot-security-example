package com.laurentiuspilca.ssia.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6691865730779075942L;
	
	private final String username;
	private final String password;
	private final String authority;
	
	public User(String username, String password, String authority) {
		
		this.username = username;
		this.password = password;
		this.authority = authority;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 인스턴스를 만들 때 지정한 이름의 
		// GrantedAuthority 객체만 포함하는 목록을 반환
		// JDK8
		return  Arrays.asList(() -> authority);
		// JDK9
		//return List.of(() -> authority);
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	// 계정은 만료되거나 잠기지 않는다.
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	
}
