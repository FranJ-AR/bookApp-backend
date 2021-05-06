package com.example.services;

import java.util.List;

import com.example.model.Book;
import com.example.model.BookParamsFinder;

public interface BookService {

	List<Book> findAllBooks();

	List<Book> findBooksByParams(BookParamsFinder bookParamsFinder);

	Book findById(long bookId);

}