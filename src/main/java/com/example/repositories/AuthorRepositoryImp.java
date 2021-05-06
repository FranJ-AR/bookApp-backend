package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Author;

@Repository
public interface AuthorRepositoryImp extends CrudRepository<Author, Long>, IAuthorRepository{

}
