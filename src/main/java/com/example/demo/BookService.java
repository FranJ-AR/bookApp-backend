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
		
		Integer authorId = bookParamsFinder.getAuthorId();
		
		Integer categoryId = bookParamsFinder.getCategoryId();
		
		Integer subcategoryId = bookParamsFinder.getSubcategoryId();
		
		// If an id does not exist on the DB, make it null
		
		if( !authorService.existsById(authorId)) bookParamsFinder.setAuthorId(null);
		
		if( !categoryService.existsById(categoryId)) bookParamsFinder.setCategoryId(null);
		
		if( !subcategoryService.existsById(subcategoryId)) bookParamsFinder.setSubcategoryId(null);
		
		return bookRepository.findBooksByParams(bookParamsFinder);
		
		
		
	}

}
