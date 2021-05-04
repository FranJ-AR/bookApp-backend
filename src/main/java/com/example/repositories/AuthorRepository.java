package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer>{
	
	//@Query("SELECT a FROM Author a WHERE lower(a.name) LIKE lower(concat('%', :substringName,'%')) ORDER BY a.name")
	@Query("SELECT a FROM Author a WHERE a.name LIKE %:substringName% ORDER BY a.name")
	public Iterable<Author> findAllBySubstringName(@Param("substringName")String substringName);

	public boolean existsById(Long authorId);

}
