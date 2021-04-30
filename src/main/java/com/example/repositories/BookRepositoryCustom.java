package com.example.repositories;

import java.util.List;

import com.example.demo.BookParamsFinder;
import com.example.model.Book;

public interface BookRepositoryCustom {
	
	public List<Book>findBooksByParams1(BookParamsFinder bookParamsFinder);

}
