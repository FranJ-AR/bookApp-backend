package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Book;
import com.example.entity.Category;

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
	
	
}
