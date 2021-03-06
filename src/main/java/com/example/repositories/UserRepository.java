package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>, 
	CustomUserRepository{
	
	@Query("SELECT u FROM User u WHERE u.name = :name")
	public User findByName(@Param("name") String name);
	

}
