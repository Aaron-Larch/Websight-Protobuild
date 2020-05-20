package com.webbuild.javabrains.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS") //Table Reference in database
public class Products {
	
	//Primary key foreign key link to products table
    @Id //identify primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productID;
    
    @Column(name="PRODUCTNAME")
	private String productName;
    
    @Column(name="QUANTITYPERUNIT")
	private String quantityPerUnit;
    
    @Column(name="UNITSINSTOCK")
	private int unitsInStock;
    
    @Column(name="UNITSONORDER")
	private int unitsOnOrder;
    
    @Column(name="UNITPRICE")
	private double unitprice;
	
	private int supplierID;
	
	private int categoryID;
	
	@ManyToMany
	private Set<Categories> Categories;
	
	@ManyToMany
	private Set<Suppliers> Suppliers;

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public int getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public int getUnitsOnOrder() {
		return unitsOnOrder;
	}

	public void setUnitsOnOrder(int unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}

	public double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(double unitprice) {
		this.unitprice = unitprice;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	
	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public Set<Categories> getCategories() {
		return Categories;
	}

	public void setCategories(Set<Categories> categories) {
		this.Categories = categories;
	}
	public Set<Suppliers> getSuppliers() {
		return Suppliers;
	}

	public void setSuppliers(Set<Suppliers> suppliers) {
		Suppliers = suppliers;
	}
}
