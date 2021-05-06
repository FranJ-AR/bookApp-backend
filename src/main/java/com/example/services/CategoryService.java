package com.example.services;

import java.util.List;

import com.example.model.Book;
import com.example.model.Category;

public interface CategoryService {

	List<Book> getBooksFromCategory(long categoryId);

	List<Category> getAllCategories();

	boolean existsById(Long id);

}