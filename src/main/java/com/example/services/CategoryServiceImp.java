package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Book;
import com.example.model.Category;
import com.example.repositories.CategoryRepositoryImp;

@Service
public class CategoryServiceImp implements ICategoryService {
	
	@Autowired
	private CategoryRepositoryImp categoryRepositoryImp;
	
	@Override
	@Deprecated
	public List<Book> getBooksFromCategory(long categoryId) {
		
		Optional<Category> optionalCategory = categoryRepositoryImp.findById(categoryId);
		
		if(! optionalCategory.isPresent() ) return null;
		
		return optionalCategory.get().getBooks();
			
	}
	
	@Override
	public List<Category> getAllCategories(){
		
		List<Category> categories = new ArrayList<Category>();
		
		categoryRepositoryImp.findAllOrderedASC().forEach(categories::add);
		
		return categories;
	}
	
	@Override
	public boolean existsById(Long id) {
		
		if(id == null) return false;
		
		return categoryRepositoryImp.existsById(id);
	}
	
	
}
