package com.webbuild.javabrains.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity //declare data layer
public class OrderDetails {
	
	@Id  //Declare primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OrderID;
	private int ProductID;
	private double UnitPrice;
	private double Discount;
	
	//collect group of object to form a table for better organization
	public OrderDetails(int orderID, int productID, double unitPrice, double discount) {
		super();
		this.OrderID=orderID;
		this.ProductID=productID;
		this.UnitPrice=unitPrice;
		this.Discount=discount;
		}
	
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		this.ProductID = productID;
	}
	public double getUnitPrice() {
		return UnitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		UnitPrice = unitPrice;
	}
	public double getDiscount() {
		return Discount;
	}
	public void setDiscount(double discount) {
		Discount = discount;
	}
	
}
