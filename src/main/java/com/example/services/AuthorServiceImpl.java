package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Author;
import com.example.repositories.AuthorRepository;
import com.example.repositories.AuthorRepositoryImpl;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	public AuthorRepository authorRepository;
	
	@Override
	public List<Author> findAllBySubstringName(String substringName){
		
		List<Author> authors = new ArrayList<Author>();
		
		authorRepository.findAllBySubstringName(substringName).forEach(authors::add);
		
		return authors;
		
	}
	
	@Override
	public boolean existsById(Long authorId) {
		
		if(authorId == null) return false;
		
		return authorRepository.existsById(authorId);
	}

}
