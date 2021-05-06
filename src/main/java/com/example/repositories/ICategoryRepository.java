package com.example.repositories;

import org.springframework.data.jpa.repository.Query;

import com.example.model.Category;

public interface ICategoryRepository {

	Iterable<Category> findAllOrderedASC();

}