package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Book;

@Repository
public interface BookRepositoryImpl extends CrudRepository<Book, Long>, BookRepository{
	
	 @Query(value = "SELECT * FROM Book book WHERE 1=1 " +
			  "AND (:authorId = -1 OR :authorId = author_id) " +
			  "AND (:categoryId = -1 OR :categoryId = category_id) " +
			  "AND (:subcategoryId = -1 OR :subcategoryId = subcategory_id) " +
			  "AND (:titleSubstring = \'\' " +
			  "OR lower(book_name) LIKE lower(concat('%', :titleSubstring,'%')) ) "+
			  "order by book_name", nativeQuery = true)
			 

			public Iterable<Book> findBooksByParams(
					@Param("titleSubstring") 
					String titleSubstring, 
					@Param("authorId") Long authorId, 
					@Param("categoryId") 
					Long categoryId, 
					@Param("subcategoryId") 
					Long subcategoryId);
	 
	 

}
