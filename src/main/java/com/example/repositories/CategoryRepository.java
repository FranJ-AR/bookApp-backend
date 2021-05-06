package com.example.repositories;

import com.example.model.Category;

public interface CategoryRepository {

	Iterable<Category> findAllOrderedASC();

}