package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.BookParamsFinder;
import com.example.model.Book;
import com.example.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubcategoryService subcategoryService;
	
	@Override
	public List<Book> findAllBooks() {
		
		List<Book> bookList = new ArrayList<Book>();
		
		bookRepository.findAll().forEach(bookList::add); // Adds books to bookList
		
		return bookList;
	}
	
	@Override
	public List<Book> findBooksByParams(BookParamsFinder bookParamsFinder){
		
		String titleSubstring = bookParamsFinder.getTitleSubstring();
		
		Long authorId = bookParamsFinder.getAuthorId();
		
		Long categoryId = bookParamsFinder.getCategoryId();
		
		Long subcategoryId = bookParamsFinder.getSubcategoryId();
		
		// If an element does not exist on the DB, make it null
		
		if( titleSubstring == null) titleSubstring = "";
		
		if( !authorService.existsById(authorId)) authorId = (long)-1;
		
		if( !categoryService.existsById(categoryId)) categoryId = (long)-1;
		
		if( !subcategoryService.existsById(subcategoryId)) subcategoryId = (long)-1;
		
		Iterable<Book> iterableBook = bookRepository.findBooksByParams( titleSubstring,
				authorId, categoryId, subcategoryId);
		
		List<Book> books = new ArrayList<Book>();
				
		iterableBook.forEach(books::add);
		
		return books;
		
		
	}
	
	@Override
	public Book findById(long bookId) {
		
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		
		if(optionalBook.isEmpty()) return null;
		
		return optionalBook.get();
		
	}
	
}
