package com.webbuild.javabrains.repository;

import java.util.List;

import com.webbuild.javabrains.model.TableObjects;

public interface ShippingRepository {
	public List<TableObjects> getAllOrders(String country);
	public TableObjects getOrders(String id);
	public void addOrder(TableObjects order);
	public void updateOrder(TableObjects order, String id);
	public void deleteOrder(String id); 
	public void updateTable();
	public List<TableObjects> getSingleTable();
	public double[][] collectdata(String collum);
}

