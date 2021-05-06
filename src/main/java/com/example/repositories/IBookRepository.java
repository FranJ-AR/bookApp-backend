package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Book;

public interface IBookRepository {

	Iterable<Book> findBooksByParams(String titleSubstring, Long authorId, Long categoryId, Long subcategoryId);

}