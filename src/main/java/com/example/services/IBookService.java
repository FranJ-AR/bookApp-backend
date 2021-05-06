package com.example.services;

import java.util.List;

import com.example.demo.BookParamsFinder;
import com.example.model.Book;

public interface IBookService {

	List<Book> findAllBooks();

	List<Book> findBooksByParams(BookParamsFinder bookParamsFinder);

	Book findById(long bookId);

}