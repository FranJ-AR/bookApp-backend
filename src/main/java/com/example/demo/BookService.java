package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Book;

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
		
		Integer authorId = bookParamsFinder.getAuthorId();
		
		Integer categoryId = bookParamsFinder.getCategoryId();
		
		Integer subcategoryId = bookParamsFinder.getSubcategoryId();
		
		// If an id does not exist on the DB, make it null
		
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

}
