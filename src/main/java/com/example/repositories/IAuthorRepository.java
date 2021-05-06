package com.example.repositories;

import com.example.model.Author;

public interface IAuthorRepository {

	//@Query("SELECT a FROM Author a WHERE a.name ILIKE %:substringName% ORDER BY a.name")
	Iterable<Author> findAllBySubstringName(String substringName);

	boolean existsById(Long authorId);

}