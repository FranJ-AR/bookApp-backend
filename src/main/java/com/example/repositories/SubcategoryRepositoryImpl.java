package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Subcategory;

@Repository
public interface SubcategoryRepositoryImpl extends CrudRepository<Subcategory, Long>, SubcategoryRepository{
		
	@Query("SELECT s FROM Subcategory s ORDER BY s.name")
	public Iterable<Subcategory> findAllOrderedASC();

}
