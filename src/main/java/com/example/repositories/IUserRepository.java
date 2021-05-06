package com.example.repositories;

import com.example.model.User;

public interface IUserRepository {

	User findByName(String name);

}