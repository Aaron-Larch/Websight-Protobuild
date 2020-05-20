package com.webbuild.javabrains.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.webbuild.javabrains.model.Categories;
import com.webbuild.javabrains.repository.CategoriesRepository;


public class CategoriesService {
	@Autowired //call the user table in the database
    private CategoriesRepository categoriesRepository;
	
	//select * from Categories
	public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }

}
