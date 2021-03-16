package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.User;

public interface CustomUserRepository {
	
	@Query("SELECT u FROM User u WHERE u.name = :name")
	public User findByName(@Param("name") String name);

}
