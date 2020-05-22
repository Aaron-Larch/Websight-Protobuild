package com.webbuild.javabrains.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webbuild.javabrains.jdbc.SecondSQLConnection;
import com.webbuild.javabrains.model.OrderDetails;

public interface OrderDetailsRepository  extends JpaRepository<OrderDetails, Long> {
	
	public static List<OrderDetails> FindDiscounts(int id){
		return SecondSQLConnection.order(id);
	}
	
}
