package com.laurentiuspilca.ssia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laurentiuspilca.ssia.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	Optional<User> findUserByUsername(String username);
}
