package com.example.repositories;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>, BookRepositoryCustom{
	
	/*
	 * @Query("SELECT b FROM Book b WHERE 1=1 " + "and (:authorId = b.author_id) " +
	 * "and (:categoryId = category_id) " + "and (:subcategoryId = subcategory_id) "
	 * + "and (book.name LIKE %:titleSubstring%)" )
	 */
	
	  @Query("SELECT book FROM Book book WHERE 1=1 " +
	  "AND (:authorId IS NULL OR :authorId = author_id) " +
	  "AND (:categoryId IS NULL OR :categoryId = category_id) " +
	  "AND (:subcategoryId IS NULL OR :subcategoryId = subcategory_id) " +
	  "AND (:titleSubstring IS NULL OR book.name LIKE %:titleSubstring%)" )
	 

	public Iterable<Book> findBooksByParams(
			@Param("titleSubstring") 
			String titleSubstring, 
			@Param("authorId") Long authorId, 
			@Param("categoryId") 
			Long categoryId, 
			@Param("subcategoryId") 
			Long subcategoryId);
	
	/*
	 * @Query("SELECT book FROM Book book WHERE 1 " +
	 * "and (:authorId is null or :authorId = book.author_id)" +
	 * "and (:categoryId is null or :categoryId = book.category_id)" +
	 * "and (:subcategoryId is null or :subcategoryId = book.subcategory_id)" +
	 * "and (book.name LIKE %:titleSubstring%)" )
	 */
	
	

}
