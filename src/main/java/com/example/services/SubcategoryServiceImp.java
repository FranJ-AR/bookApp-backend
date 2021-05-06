package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Subcategory;
import com.example.repositories.SubcategoryRepositoryImp;

@Service
public class SubcategoryServiceImp implements ISubcategoryService {
	
	@Autowired
	private SubcategoryRepositoryImp subcategoryRepositoryImp;
	
	@Override
	public List<Subcategory> getAllSubcategories(){
		
		List<Subcategory> subcategories = new ArrayList<Subcategory>();
		
		subcategoryRepositoryImp.findAllOrderedASC().forEach(subcategories::add);
				
		return subcategories;
	}
	
	@Override
	public boolean existsById(Long id) {
		
		if(id == null) return false;
		
		return subcategoryRepositoryImp.existsById(id);
	}

}
