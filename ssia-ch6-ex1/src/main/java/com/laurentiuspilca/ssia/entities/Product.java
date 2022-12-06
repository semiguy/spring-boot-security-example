package com.laurentiuspilca.ssia.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.laurentiuspilca.ssia.entities.enums.Currency;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private double price;
	
	@Enumerated(EnumType.STRING)
	private Currency currency;
}
