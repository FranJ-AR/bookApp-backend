package com.example.services;

import java.util.List;

import com.example.model.Author;

public interface IAuthorService {

	List<Author> findAllBySubstringName(String substringName);

	boolean existsById(Long authorId);

}