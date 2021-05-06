package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.ComponentScan;

import com.example.model.User;
import com.example.services.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ComponentScan("com.example.services")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
    private IUserService userService;
 
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.loadUser(username);
        //if(user == null) 
        	
        if(user == null) throw new UsernameNotFoundException("User not found");
        return new CustomUserDetails(user);
    }
}