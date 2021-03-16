package com.example.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
    
    

	
}