package com.example.repositories;

import com.example.model.Subcategory;

public interface SubcategoryRepository {

	Iterable<Subcategory> findAllOrderedASC();

}