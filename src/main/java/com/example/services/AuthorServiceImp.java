package com.example.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Author;
import com.example.repositories.AuthorRepositoryImp;

@Service
public class AuthorServiceImp implements IAuthorService {
	
	@Autowired
	public AuthorRepositoryImp authorRepositoryImp;
	
	@Override
	public List<Author> findAllBySubstringName(String substringName){
		
		List<Author> authors = new ArrayList<Author>();
		
		authorRepositoryImp.findAllBySubstringName(substringName).forEach(authors::add);
		
		return authors;
		
	}
	
	@Override
	public boolean existsById(Long authorId) {
		
		if(authorId == null) return false;
		
		return authorRepositoryImp.existsById(authorId);
	}

}
