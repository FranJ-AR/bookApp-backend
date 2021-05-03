package com.example.auth;

import com.example.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Getter
public class AuthenticationRequest {
    private String username;
    private String password;
    
	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean hasUsernameAndPassword() {
		
		if(getUsername() != null && getPassword() != null) {
			
			return true;
		}
		
		return false;
	}
    
}
