package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Subcategory;

@Service
public class SubcategoryService {
	
	@Autowired
	private SubcategoryRepository subcategoryRepository;
	
	public List<Subcategory> getAllSubcategories(){
		
		List<Subcategory> subcategories = new ArrayList<Subcategory>();
		
		subcategoryRepository.findAllOrderedASC().forEach(subcategories::add);
				
		return subcategories;
	}
	
	public boolean existsById(Integer id) {
		
		if(id == null) return false;
		
		return subcategoryRepository.existsById(id);
	}

}
