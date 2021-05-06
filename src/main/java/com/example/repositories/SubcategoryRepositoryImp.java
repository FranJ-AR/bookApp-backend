package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Subcategory;

@Repository
public interface SubcategoryRepositoryImp extends CrudRepository<Subcategory, Long>, ISubcategoryRepository{
		
	

}
