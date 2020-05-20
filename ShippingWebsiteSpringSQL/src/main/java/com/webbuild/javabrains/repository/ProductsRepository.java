package com.webbuild.javabrains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webbuild.javabrains.model.Products;

public interface ProductsRepository  extends JpaRepository<Products, Long>{
	List<Products> findByCategoryID(int id);

	List<Products> findByProductName(String id);
	
}
