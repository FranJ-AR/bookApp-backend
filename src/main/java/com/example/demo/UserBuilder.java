package com.example.demo;

import com.example.entity.User;

public class UserBuilder {
	
	private String username;
	
	private String password;

	public UserBuilder() {
		super();
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
	
	public User buildUser() {
		
		return new User(null, username, password);
	}
	
	public boolean hasUsernameAndPassword() {
		
		if(getUsername() != null && getPassword() != null) {
			
			return true;
		}
		
		return false;
	}
	

}
