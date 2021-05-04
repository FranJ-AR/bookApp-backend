package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Book;
import com.example.model.Category;
import com.example.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Deprecated
	public List<Book> getBooksFromCategory(int categoryId) {
		
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		
		if(! optionalCategory.isPresent() ) return null;
		
		return optionalCategory.get().getBooks();
			
	}
	
	public List<Category> getAllCategories(){
		
		List<Category> categories = new ArrayList<Category>();
		
		categoryRepository.findAllOrderedASC().forEach(categories::add);
		
		return categories;
	}
	
	public boolean existsById(Integer id) {
		
		if(id == null) return false;
		
		return categoryRepository.existsById(id);
	}

	public boolean existsById(Long categoryId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
