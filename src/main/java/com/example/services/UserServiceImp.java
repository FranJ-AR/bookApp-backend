package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repositories.UserRepositoryImp;

@Service
public class UserServiceImp implements IUserService {
	
	@Autowired
	private UserRepositoryImp userRepositoryImp;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User loadUser(String userName) {
		
		User user = userRepositoryImp.findByName(userName);
		
		return user;
		
	}
	
	@Override
	public Long saveUser(User user) {
		
		if(existsUser(user.getName())) {
			
			return (long) -1; //if user exists, fail to add to the DB
		}
		
		String encodedPassword = 
				bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		
		userRepositoryImp.save(user);
		
		return user.getId();
		
	}
	
	@Override
	public boolean existsUser(String userName) {
		
		User user = loadUser(userName);
		
		return user != null; // true if exists user, false if not exists
		
	}

}
