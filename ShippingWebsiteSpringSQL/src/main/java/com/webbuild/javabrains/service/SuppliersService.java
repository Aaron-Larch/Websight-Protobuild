package com.webbuild.javabrains.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.webbuild.javabrains.model.Suppliers;
import com.webbuild.javabrains.repository.SuppliersRepository;

public class SuppliersService {
	@Autowired //call the user table in the database
    private SuppliersRepository suppliersRepository;
	
	public Suppliers findBySupplierID(int id) {
		return suppliersRepository.findBySupplierID(id);
	}
}
