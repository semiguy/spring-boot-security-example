package com.laurentiuspilca.ssia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        
    	UserDetailsManager uds = new InMemoryUserDetailsManager();

        UserDetails u1 = User.withUsername("john")
        		.password("12345")
                .authorities("read")
                .build();

        UserDetails u2 = User.withUsername("jane")
        		.password("12345")
        		.authorities("read", "premium")
                .build();

        uds.createUser(u1);
        uds.createUser(u2);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.authorizeRequests() // 사용자 인증을 위한 경로의 조건으로 정규식을 이용
                .regexMatchers(".*/(us|uk|ca)+/(en|fr).*")
                    .authenticated()
            .anyRequest()
            	.hasAuthority("premium");	// 사용자가 프리미엄 액세스를 이용하는 데 필요한 다른 경로를 구성.
    }
}
