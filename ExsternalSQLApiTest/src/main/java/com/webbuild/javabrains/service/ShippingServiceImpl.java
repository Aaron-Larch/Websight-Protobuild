package com.webbuild.javabrains.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webbuild.javabrains.Operations.Statistics;
import com.webbuild.javabrains.jdbc.ExternalConnection;
import com.webbuild.javabrains.model.TableObjects;
import com.webbuild.javabrains.repository.ShippingRepository;

@Service
@Transactional
public class ShippingServiceImpl implements ShippingRepository {
	
	//populate the data layer
	private List<TableObjects> shipRegion;
	private ArrayList<TableObjects>[] shipping = ExternalConnection.SortedShipping();
	
	//select * from orders where shipcity= 'spain'
	@Override
	public List<TableObjects> getAllOrders(String country){
		for(int i=0; i < shipping.length; i++) {
			if(shipping[i].get(0).getSHIPCOUNTRY().equalsIgnoreCase(country)) {
				return shipRegion=shipping[i];
		     }
		}
		return null;
	}

	//select * from orders
	@Override
	public List<TableObjects> getSingleTable(){
		return shipRegion=ExternalConnection.SpainShipping();
		}
	
	//select * from orders where shipcity= 'spain' and orderid = '{id}'
	@Override
	public TableObjects getOrders(String id){
		return shipRegion.stream().filter(t -> t.getORDERID().equals(id)).findFirst().get();
	}

	//insert into orders(ORDERID, EMPLOYEEID, ect.) Values ('object.getORDERID', 'object.getEMPLOYEEID', ect.)
	@Override
	public void addOrder(TableObjects order) {
		shipRegion.add(order);
	}

	//update orders set EMPLOYEEID= '{user input}', ect. where ORDERID = '{id}'
	@Override
	public void updateOrder(TableObjects order, String id) {
		for (int i=0; i<shipRegion.size(); i++) {
			TableObjects t=shipRegion.get(i);
			if (t.getORDERID().equals(id)) {
				shipRegion.set(i,order);
			return;
			}
		}
	}
	
	//for a user controlled front end page
	public void userAddOrder(TableObjects order, String country, String id) {
		getAllOrders(country);
		if(id==null) {shipRegion.add(order);}
		else {updateOrder(order, id);}
	}
	
	//delete from orders where ORDERID = 'insert row here'
	@Override
	public void deleteOrder(String id) {
		shipRegion.removeIf(t -> t.getORDERID().equals(id));
	}
	
	//COMMIT shipSpain
	@Override
	public void updateTable() {	
		for(int i=0; i < shipping.length; i++) {ExternalConnection.ShippingUpdate(shipping[i]);}
	}
	
	//Bring user choice back into the equation. Decide on witch collum of information the user wants to work with
	@Override
	public double[][] collectdata(String collum) {
		double[][] databox= new double[shipping.length][];
		double maxVal = 0;
		double [] temp;
		
		for(int i=0; i < shipping.length; i++) {
			databox[i] = new double[shipping[i].size()];
			switch(collum.toLowerCase()){	
			
			case"employeeid":
				for(int ii=0; ii < shipping[i].size(); ii++) {
					databox[i][ii]=Double.parseDouble(shipping[i].get(ii).getEMPLOYEEID().trim());
					}
				temp=databox[i];
				maxVal=Statistics.minMax(temp, "max");
				break;
				
			case"shipVia":
				for(int ii=0; ii < shipping[i].size(); ii++) {
					databox[i][ii]=Double.parseDouble(shipping[i].get(ii).getSHIPVIA());
					}
				temp=databox[i];
				maxVal=Statistics.minMax(temp, "max");
				break;
				
			case"freight":
				for(int ii=0; ii < shipping[i].size(); ii++) {
					databox[i][ii]=Double.parseDouble(shipping[i].get(ii).getFREIGHT().trim());
					}
				temp=databox[i];
				maxVal=Statistics.minMax(temp, "max");
				break;
				
			default:
				//User Error handling
				System.out.println(collum + " appears to be an invalid Collum in this table. This report has been reset.");
				databox=null;
				break;
				}
			System.out.print("The sample size taken for this week was " + Math.abs(shipping[i].size()) + 
					". The highest recorded value for this stock was " + maxVal + "\n" +
					"The stock values for " + collum + " in Region "+shipping[i].get(0).getSHIPCOUNTRY()+
					": " + Arrays.toString(databox[i]) + "\n\n");
		}
		return databox;
	}
}