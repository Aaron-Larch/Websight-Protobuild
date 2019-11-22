package com.webbuild.springbootrestapitest.Service;


import java.util.List;

import org.springframework.stereotype.Service;
import com.webbuild.springbootrestapitest.jdbc.ExternalConnection;
import com.webbuild.springbootrestapitest.model.TableObjects;



@Service
public class ShippingDAO {
	
	//populate the data layer
	private List<TableObjects> shipSpain = ExternalConnection.SpainShipping();
	
	//select * from orders where shipcity= spain
	public List<TableObjects> getAllOrders(){
		return shipSpain;
	}

	//select * from orders where shipcity= spain and orderid = 'userer coice"
	public TableObjects getOrders(String id){
		return shipSpain.stream().filter(t -> t.getORDERID().equals(id)).findFirst().get();	
}

	//insert into orders (ORDERID, CUSTOMERID, EMPLOYEEID, SHIPVIA, FREIGHT, SHIPNAME, SHIPCOUNTRY) Values ();
	public void addOrder( TableObjects order) {
		shipSpain.add(order);
	}

	//update orders where shipcity= spain
	public void updateOrder(TableObjects order, String id) {
		for (int i=0; i<shipSpain.size(); i++) {
			TableObjects t=shipSpain.get(i);
			if (t.getORDERID().equals(id)) {
				shipSpain.set(i,order);
			return;
			}
		}
	}
	
	//delete from orders where shipcity= spain
	public void deleteOrder(String id) {
		// TODO Auto-generated method stub
		shipSpain.removeIf(t -> t.getORDERID().equals(id));
	}
	
	//push to table
	public void updateTable() {
		ExternalConnection.ShippingUpdate(shipSpain);
	}
}