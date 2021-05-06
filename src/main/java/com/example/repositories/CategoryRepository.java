package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{
	
	@Query("SELECT c FROM Category c ORDER BY c.name")
	public Iterable<Category> findAllOrderedASC();

}
