package com.sam.mum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sam.mum.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	
	Optional<User> findUserByEmail(String email);
	
}
