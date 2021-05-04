package com.example.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.util.beans.BeanInfoHelper.ReturningBeanInfoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.BookParamsFinder;
import com.example.model.Book;
import com.example.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubcategoryService subcategoryService;
	
	public List<Book> findAllBooks() {
		
		List<Book> bookList = new ArrayList<Book>();
		
		bookRepository.findAll().forEach(bookList::add); // Adds books to bookList
		
		return bookList;
	}
	
	public List<Book> findBooksByParams(BookParamsFinder bookParamsFinder){
		
		String titleSubstring = bookParamsFinder.getTitleSubstring();
		
		Long authorId = bookParamsFinder.getAuthorId();
		
		Long categoryId = bookParamsFinder.getCategoryId();
		
		Long subcategoryId = bookParamsFinder.getSubcategoryId();
		
		// If an element does not exist on the DB, make it null
		
		//if( titleSubstring == null) titleSubstring = "";
		
		if( !authorService.existsById(authorId)) authorId = null;
		
		if( !categoryService.existsById(categoryId)) categoryId = null;
		
		if( !subcategoryService.existsById(subcategoryId)) subcategoryId = null;
		
		Iterable<Book> iterableBook = bookRepository.findBooksByParams( titleSubstring,
				authorId, categoryId, subcategoryId);
		
		List<Book> books = new ArrayList<Book>();
				
		iterableBook.forEach(books::add);
		
		return books;
		
		
	}
	
	public Book findById(int bookId) {
		
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		
		if(optionalBook.isEmpty()) return null;
		
		return optionalBook.get();
		
	}
	
}
