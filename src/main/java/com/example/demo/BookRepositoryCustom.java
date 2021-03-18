package com.example.demo;

import java.util.List;

import com.example.entity.Book;

public interface BookRepositoryCustom {
	
	public List<Book>findBooksByParams(BookParamsFinder bookParamsFinder);

}
