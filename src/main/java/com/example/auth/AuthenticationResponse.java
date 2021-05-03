package com.example.auth;

import com.example.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
	
	private String token;
    public AuthenticationResponse(String token) {
    	
		
    	this.token = token;
	}
	public String getToken() {
		return token;
	}
	
	public Integer getTokenExpirationTime() { // in ms
		
		return JwtService.EXPIRATION_TIME_TOKEN;
		
	}
    
	public Integer getMaximumBooksLoan() {
		
		return User.MAX_LOANS;
			
	}
	
	public Integer getMaximumBooksReservation() {
		
		return User.MAX_RESERVATIONS;	
		
	}
    

}