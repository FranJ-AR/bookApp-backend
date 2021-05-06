package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.User;

@Repository
public interface UserRepositoryImp extends CrudRepository<User, Long>, IUserRepository{
	

}
