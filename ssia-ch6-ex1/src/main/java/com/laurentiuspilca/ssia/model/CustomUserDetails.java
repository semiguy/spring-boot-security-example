package com.laurentiuspilca.ssia.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.laurentiuspilca.ssia.entities.User;

public class CustomUserDetails implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4090010324921609715L;
	
	private final User user;
	
	public CustomUserDetails(User user) {
		
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		//return null;
		return user.getAuthorities().stream()
				.map(a -> new SimpleGrantedAuthority(a.getName())) // DB에서 각 사용자에 대해 발견된 권한
				.collect(Collectors.toList()); // SimpleGrantedAuthority의 모든 인스턴스를 목록으로 수집하고 반환
	}

	@Override
	public String getPassword() {
		
		//return null;
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		//return null;
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		//return false;
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		//return false;
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		//return false;
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		//return false;
		return true;
	}
	
	public final User getUser() {
		
		return user;
	}
	
}
