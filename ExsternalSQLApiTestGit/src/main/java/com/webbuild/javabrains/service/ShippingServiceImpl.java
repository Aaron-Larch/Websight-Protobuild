package com.webbuild.javabrains.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webbuild.javabrains.jdbc.ExternalConnection;
import com.webbuild.javabrains.model.TableObjects;
import com.webbuild.javabrains.repository.ShippingRepository;

@Service
@Transactional
public class ShippingServiceImpl implements ShippingRepository {
	
	//populate the data layer
	private List<TableObjects> shipSpain = ExternalConnection.SpainShipping();
	
	//select * from orders where shipcity= 'spain'
	@Override
	public List<TableObjects> getAllOrders(){
		return shipSpain;
	}

	//select * from orders where shipcity= 'spain' and orderid = '{id}'
	@Override
	public TableObjects getOrders(String id){
		return shipSpain.stream().filter(t -> t.getORDERID().equals(id)).findFirst().get();
}

	//insert into orders(ORDERID, EMPLOYEEID, ect.) Values ('object.getORDERID', 'object.getEMPLOYEEID', ect.)
	@Override
	public void addOrder(TableObjects order) {
		shipSpain.add(order);
	}

	//update orders set EMPLOYEEID= '{user input}', ect. where ORDERID = '{id}'
	@Override
	public void updateOrder(TableObjects order, String id) {
		for (int i=0; i<shipSpain.size(); i++) {
			TableObjects t=shipSpain.get(i);
			if (t.getORDERID().equals(id)) {
				shipSpain.set(i,order);
			return;
			}
		}
	}
	
	//delete from orders where ORDERID = 'insert row here'
	@Override
	public void deleteOrder(String id) {
		shipSpain.removeIf(t -> t.getORDERID().equals(id));
	}
	
	//COMMIT shipSpain
	@Override
	public void updateTable() {
		ExternalConnection.ShippingUpdate(shipSpain);
	}
}