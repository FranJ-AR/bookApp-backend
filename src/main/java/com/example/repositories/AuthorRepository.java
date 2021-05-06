package com.example.repositories;

import org.springframework.stereotype.Repository;

import com.example.model.Author;

@Repository
public interface AuthorRepository {

	Iterable<Author> findAllBySubstringName(String substringName);

	boolean existsById(Long authorId);

}