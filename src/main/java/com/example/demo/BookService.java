package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Book;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAllBooks() {
		
		List<Book> bookList = new ArrayList<Book>();
		
		bookRepository.findAll().forEach(bookList::add); // Adds books to bookList
		
		return bookList;
	}

}
