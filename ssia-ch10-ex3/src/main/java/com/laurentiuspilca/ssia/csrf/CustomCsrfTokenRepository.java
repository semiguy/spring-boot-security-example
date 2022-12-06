package com.laurentiuspilca.ssia.csrf;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import com.laurentiuspilca.ssia.entities.Token;
import com.laurentiuspilca.ssia.repositories.JpaTokenRepository;

public class CustomCsrfTokenRepository implements CsrfTokenRepository {
	
	@Autowired
	private JpaTokenRepository jpaTokenRepository;

	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
		
		//return null;
		String uuid = UUID.randomUUID().toString();
		return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
	}
	
	// 특정 클라이언트를 위해 생성된 토큰을 저장
	@Override
	public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
		
		String identifier = request.getHeader("X-IDENTIFIER"); 
		Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier); // 클라이언트 ID로 데이터베이스에서 토큰을 얻음
		
		if(existingToken.isPresent()) { // ID가 존재하면 새로 생성된 값으로 토큰의 값을 업데이트
			
			Token token = existingToken.get();
			token.setToken(csrfToken.getToken());
		} else { // ID가 존재하지 않으면 생성된 CSRF 토큰의 값과 ID로 새 레코드 생성
			
			Token token = new Token();
			token.setToken(csrfToken.getToken());
			token.setIdentifier(identifier);
			jpaTokenRepository.save(token);
		}
	}
	
	// 메서드 구현은 토큰 세부 정보가 있으면 이를 로드하고 없으면 null을 반환
	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
		
		String identifier = request.getHeader("X-IDENTIFIER");
		Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);
		
		if(existingToken.isPresent()) {
			
			Token token = existingToken.get();
			return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getToken());
		}
		
		return null;
	}
	
	
}
