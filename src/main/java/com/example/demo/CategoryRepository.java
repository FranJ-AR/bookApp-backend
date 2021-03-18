package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Category;
import com.example.entity.Subcategory;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>{
	
	@Query("SELECT c FROM Category c ORDER BY c.name")
	public Iterable<Category> findAllOrderedASC();

}
