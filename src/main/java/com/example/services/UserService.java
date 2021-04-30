package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User loadUser(String userName) {
		
		User user = userRepository.findByName(userName);
		
		return user;
		
	}
	
	public Integer saveUser(User user) {
		
		if(existsUser(user.getName())) {
			
			return -1; //if user exists, fail to add to the DB
		}
		
		String encodedPassword = 
				bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		
		userRepository.save(user);
		
		return user.getId();
		
	}
	
	public boolean existsUser(String userName) {
		
		User user = loadUser(userName);
		
		return user != null; // true if exists user, false if not exists
		
	}

}
