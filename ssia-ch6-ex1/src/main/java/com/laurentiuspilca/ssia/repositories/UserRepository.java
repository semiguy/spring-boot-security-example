package com.laurentiuspilca.ssia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laurentiuspilca.ssia.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findUserByUsername(String u);
}
