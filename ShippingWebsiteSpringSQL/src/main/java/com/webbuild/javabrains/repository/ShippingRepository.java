package com.webbuild.javabrains.repository;

import java.util.List;

import com.webbuild.javabrains.model.TableObjects;
import com.webbuild.javabrains.model.User;

//Interface controlling the System database
public interface ShippingRepository {
	//Call all stored methods
	public List<TableObjects> getAllOrders(String country);
	public TableObjects getOrders(String id);
	public void addOrder(TableObjects order);
	public void updateOrder(TableObjects order, String id);
	public void deleteOrder(String id); 
	public void updateTable(User usr);
	public List<TableObjects> getUserTable(String id);
	public double[][] collectdata(String collum);
	
}

