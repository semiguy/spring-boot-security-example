package com.laurentiuspilca.ssia.services;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.laurentiuspilca.ssia.entities.User;
import com.laurentiuspilca.ssia.model.CustomUserDetails;
import com.laurentiuspilca.ssia.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//return null;
		// 예외 인스턴스를 만들기
		Supplier<UsernameNotFoundException> s = 
				() -> new UsernameNotFoundException("Problem during authentication!");
		
		// 사용자를 포함한 Optional 인스턴스를 반환하거나 
		// 사용자가 없으면 비어 있는 Optional 인스턴스 반환
		User u = userRepository.findUserByUsername(username)
				// Optional 인스턴스가 비어 있으면 정의된 공급자에서 생성한 예외를 투척하고, 
				// 그렇치 않으면 User 인스턴스 반환
				.orElseThrow(s); 
		
		// User 인스턴스를 CustomUserDetails로 래핑.
		return new CustomUserDetails(u);
	}
	
	
}
