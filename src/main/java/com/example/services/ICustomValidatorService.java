package com.example.services;

import com.example.exceptions.CustomUserPasswordSizeException;

public interface ICustomValidatorService {

	boolean isValidSizeUserPassword(String username, String password) throws CustomUserPasswordSizeException;

}