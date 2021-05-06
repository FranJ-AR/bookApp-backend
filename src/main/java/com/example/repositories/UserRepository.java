package com.example.repositories;

import com.example.model.User;

public interface UserRepository {

	User findByName(String name);

}