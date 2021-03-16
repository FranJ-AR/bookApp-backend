package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.UserRepository;
import com.example.demo.UserService;
import com.example.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ComponentScan("com.example.demo")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UserService userService;
 
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.loadUser(username);
        //if(user == null) 
        	
        if(user == null) throw new UsernameNotFoundException("User not found");
        return new CustomUserDetails(user);
    }
}