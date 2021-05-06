package com.example.repositories;

import org.springframework.data.jpa.repository.Query;

import com.example.model.Subcategory;

public interface ISubcategoryRepository {

	Iterable<Subcategory> findAllOrderedASC();

}