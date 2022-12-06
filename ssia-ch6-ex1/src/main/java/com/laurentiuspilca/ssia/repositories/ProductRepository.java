package com.laurentiuspilca.ssia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laurentiuspilca.ssia.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
