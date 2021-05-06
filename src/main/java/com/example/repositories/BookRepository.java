package com.example.repositories;

import java.util.Optional;

import com.example.model.Book;

public interface BookRepository {

	Iterable<Book> findBooksByParams(String titleSubstring, Long authorId, Long categoryId, Long subcategoryId);
	
	Iterable<Book> findAll();
	
	Optional<Book> findById(Long bookId);

}