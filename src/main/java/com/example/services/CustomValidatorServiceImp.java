package com.example.services;

import org.springframework.stereotype.Component;

import com.example.exceptions.CustomUserPasswordSizeException;

@Component
public class CustomValidatorServiceImp implements ICustomValidatorService {
	
	public CustomValidatorServiceImp() {
		
		
	}
	
	@Override
	public boolean isValidSizeUserPassword(String username, String password) throws CustomUserPasswordSizeException {
		
		if(username.length() >= 5 && password.length() >= 5) {
			
			return true;
			
		}
		
		throw new CustomUserPasswordSizeException("User and password must be at least 5 characters length");
		
	}

}
