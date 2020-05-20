package com.webbuild.javabrains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webbuild.javabrains.model.Categories;


public interface CategoriesRepository extends JpaRepository<Categories, Long> {

	List<Categories> findAll();

}
