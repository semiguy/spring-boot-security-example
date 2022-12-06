package com.laurentiuspilca.ssia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@SpringBootApplication
public class SsiaCh6Ex1Application {

	public static void main(String[] args) {
		SpringApplication.run(SsiaCh6Ex1Application.class, args);
	}
	
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public SCryptPasswordEncoder sCryptPasswordEncoder() {
//		
//		return new SCryptPasswordEncoder();
//	}
}
