package com.webbuild.javabrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webbuild.javabrains.model.Suppliers;

public interface SuppliersRepository extends JpaRepository<Suppliers, Long>{

	Suppliers findBySupplierID(int id);

}
