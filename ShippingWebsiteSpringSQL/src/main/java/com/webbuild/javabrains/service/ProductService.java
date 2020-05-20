package com.webbuild.javabrains.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.webbuild.javabrains.model.Products;
import com.webbuild.javabrains.repository.ProductsRepository;

public class ProductService {
	@Autowired //call the user table in the database
    private ProductsRepository productsRepository;
	
	
	//SELECT * FROM PRODUCTS Where CATEGORYID = input
	public List<Products> findByCategoryID(int id) {
		return productsRepository.findByCategoryID(id);
    }
	
	public List<Products> findByProductName(String id) {
		return productsRepository.findByProductName(id);
    }
}
