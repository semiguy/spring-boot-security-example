package com.laurentiuspilca.ssia.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Token {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String identifier;	// 클라이언트의 식별자
	private String token;		// 애플리케이션이 클라이언트를 위해 생성한 CSRF 토큰
}
