package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Category;

@Repository
public interface CategoryRepositoryImp extends CrudRepository<Category, Long>, ICategoryRepository{

}
