package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Book;

@Repository
public interface BookRepositoryImp extends CrudRepository<Book, Long>, BookRepositoryCustom, IBookRepository{
	
	/*
	 * @Query("SELECT b FROM Book b WHERE 1=1 " + "and (:authorId = b.author_id) " +
	 * "and (:categoryId = category_id) " + "and (:subcategoryId = subcategory_id) "
	 * + "and (book.name LIKE %:titleSubstring%)" )
	 */
	
	  //@Query(value = "SELECT book FROM Book book WHERE 1=1", nativeQuery = true)

	  
	
	/*
	 * @Query("SELECT book FROM Book book WHERE 1 " +
	 * "and (:authorId is null or :authorId = book.author_id)" +
	 * "and (:categoryId is null or :categoryId = book.category_id)" +
	 * "and (:subcategoryId is null or :subcategoryId = book.subcategory_id)" +
	 * "and (book.name LIKE %:titleSubstring%)" )
	 */
	
	

}
