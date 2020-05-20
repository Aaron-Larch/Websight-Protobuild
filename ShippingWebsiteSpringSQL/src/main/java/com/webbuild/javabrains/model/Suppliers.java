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
@Table(name = "SUPPLIERS") //Table Reference in database
public class Suppliers {
	
	//Primary key foreign key link to products table
    @Id //identify primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplierID;
	
    @Column(name="COMPANYNAME")
    private String companyName;
	
    @Column(name="ADDRESS")
	private String address;
	
    @Column(name="CITY")
	private String city;
	
    @Column(name="REGION")
	private String region;
	
	@Column(name="POSTALCODE")
	private String postalCode;
	
	private String country;
	
	private String phone;
	
	@OneToMany//Declare value as receiving value from other table
	private Set<Products> products; //set a many to many relation with the Role table

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Products> getProducts() {
		return products;
	}

	public void setProducts(Set<Products> products) {
		this.products = products;
	}
}
