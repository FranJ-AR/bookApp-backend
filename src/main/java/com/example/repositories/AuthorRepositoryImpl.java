package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Author;

@Repository
public interface AuthorRepositoryImpl extends CrudRepository<Author, Long>, AuthorRepository{
	
	@Query("SELECT a FROM Author a WHERE lower(a.name) LIKE lower(concat('%', :substringName,'%')) ORDER BY a.name")
	Iterable<Author> findAllBySubstringName(String substringName);

	boolean existsById(Long authorId);

}
