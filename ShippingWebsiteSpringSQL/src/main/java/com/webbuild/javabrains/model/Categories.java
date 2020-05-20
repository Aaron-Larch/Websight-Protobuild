package com.webbuild.javabrains.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIES") //Table Reference in database
public class Categories {
	
	//Primary key foreign key link to products table
    @Id //identify primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CATEGORYID;
	
    @Column(name = "CATEGORYNAME")
    private String CATEGORYNAME;
    
    @Column(name="DESCRIPTION") 
	private String DESCRIPTION;
    
    @OneToMany//Declare value as receiving value from other table
    private Set<Products> products; //set a many to many relation with the Role table
	
	public int getCATEGORYID() {
		return CATEGORYID;
	}
	public void setCATEGORYID(int cATEGORYID) {
		CATEGORYID = cATEGORYID;
	}
	public String getCATEGORYNAME() {
		return CATEGORYNAME;
	}
	public void setCATEGORYNAME(String cATEGORYNAME) {
		CATEGORYNAME = cATEGORYNAME;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	
	public Set<Products> getProducts() {
		return products;
	}
	public void setProducts(Set<Products> cproducts) {
		products = cproducts;
	}
}
