package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Book;
import com.example.model.Category;
import com.example.repositories.CategoryRepositoryImpl;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepositoryImpl categoryRepositoryImpl;
	
	@Override
	@Deprecated
	public List<Book> getBooksFromCategory(long categoryId) {
		
		Optional<Category> optionalCategory = categoryRepositoryImpl.findById(categoryId);
		
		if(! optionalCategory.isPresent() ) return null;
		
		return optionalCategory.get().getBooks();
			
	}
	
	@Override
	public List<Category> getAllCategories(){
		
		List<Category> categories = new ArrayList<Category>();
		
		categoryRepositoryImpl.findAllOrderedASC().forEach(categories::add);
		
		return categories;
	}
	
	@Override
	public boolean existsById(Long id) {
		
		if(id == null) return false;
		
		return categoryRepositoryImpl.existsById(id);
	}
	
	
}
