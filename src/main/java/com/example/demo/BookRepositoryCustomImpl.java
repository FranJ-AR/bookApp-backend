package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Book;

public class BookRepositoryCustomImpl implements BookRepositoryCustom{
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	public List<Book>findBooksByParams(BookParamsFinder bookParamsFinder){
		
		 EntityManager entitymanager = entityManagerFactory.createEntityManager();
	     
	     TypedQuery<Book> query = entitymanager.createQuery("SELECT b FROM Book b",Book.class);
	     
	     List<Book> books = query.getResultList();
	      
	      return books;
	      
	      
	}

}
