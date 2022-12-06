package com.laurentiuspilca.ssia.entities;

import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Getter
@Setter
@Entity
public class Otp {
	
	@Id
	private String username;
	private String code;
}
