package com.example.repositories;

import com.example.model.Category;

public interface ICategoryRepository {

	Iterable<Category> findAllOrderedASC();

}