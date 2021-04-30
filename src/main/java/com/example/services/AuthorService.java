package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Author;
import com.example.repositories.AuthorRepository;

@Service
public class AuthorService {
	
	@Autowired
	public AuthorRepository authorRepository;
	
	public List<Author> findAllBySubstringName(String substringName){
		
		List<Author> authors = new ArrayList<Author>();
		
		authorRepository.findAllBySubstringName(substringName).forEach(authors::add);
		
		return authors;
		
	}
	
	public boolean existsById(Integer id) {
		
		if(id == null) return false;
		
		return authorRepository.existsById(id);
	}

}
