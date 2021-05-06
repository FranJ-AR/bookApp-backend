package com.example.services;

import com.example.model.User;

public interface IUserService {

	User loadUser(String userName);

	Long saveUser(User user);

	boolean existsUser(String userName);

}