package com.example.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.BookParamsFinder;
import com.example.model.Book;

public class BookRepositoryCustomImpl implements BookRepositoryCustom{
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	public List<Book>findBooksByParams1(BookParamsFinder bookParamsFinder){
		
		 EntityManager entitymanager = entityManagerFactory.createEntityManager();
	     
	     /*TypedQuery<Book> query = entitymanager.createQuery("SELECT b FROM Book b",Book.class);
	     
	     List<Book> books = query.getResultList();
	      
	      return books;*/
		 
		 CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
		
		 /*CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
		 
		 Root<Book> rootBooks = criteriaQuery.from(Book.class);
		 
		 TypedQuery<Book> typedQuery = entitymanager.createQuery(criteriaQuery);
		 
		 List<Book> books = typedQuery.getResultList();*/
		
		 return null;
	     
	      
	      
	}

}
