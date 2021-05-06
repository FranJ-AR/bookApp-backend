package com.example.services;

import java.util.List;

import com.example.model.Subcategory;

public interface ISubcategoryService {

	List<Subcategory> getAllSubcategories();

	boolean existsById(Long id);

}