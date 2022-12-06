package com.laurentiuspilca.ssia.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class InMemoryUserDetailsService implements UserDetailsService {
	
	// 메모리내 사용자의 목록을 관리
	private final List<UserDetails> users;
	
	public InMemoryUserDetailsService(List<UserDetails> users) {
		
		this.users = users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return users.stream()
				.filter(u -> u.getUsername().equals(username)) // 사용자 목록에서 요청된 사용자 이름과 일치하는 항목을 필터링한다.
				.findFirst() // 일치하는 사용자가 있으면 반환한다.
				.orElseThrow(() -> new UsernameNotFoundException("User not found")); // 사용자 이름이 존재하지 않으면 예외를 발생한다.
	}
	
	
}
