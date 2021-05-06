package com.example.repositories;

import com.example.model.Subcategory;

public interface ISubcategoryRepository {

	Iterable<Subcategory> findAllOrderedASC();

}