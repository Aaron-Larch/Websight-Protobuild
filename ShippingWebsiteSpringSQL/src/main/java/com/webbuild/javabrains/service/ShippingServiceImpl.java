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
		//search for match 
		for(int i=0; i < shipping.length; i++) {
			if(shipping[i].get(0).getSHIPCOUNTRY().equalsIgnoreCase(country)) {
				return shipRegion=shipping[i]; //set temp table to make editing essayer to perform
		     }
		}
		return null;
	}

	//select * from orders where customer= '{id}'
	@Override
	public List<TableObjects> getUserTable(String id, String role){
		if(shipRegion==null) {
			shipRegion=ExternalConnection.Shipping(id, role); //run program
		}
		return shipRegion;
		}
	
	
	//select * from orders where shipcity= '{region}' and orderid = '{id}'
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
				shipRegion.set(i, order);
				return;
			}
		}
	}
	
	//delete from orders where ORDERID = 'insert row here'
	@Override
	public void deleteOrder(String id) {
		shipRegion.removeIf(t -> t.getORDERID().equals(id));
	}
	
	//COMMIT shipSpain table
	@Override
	public void updateTable() {	
		for(int i=0; i < shipping.length; i++) {ExternalConnection.Update(shipping[i]);}
	}
	
	//Bring user choice back into the equation. Decide on witch collum of information the user wants to work with
	@Override
	public double[][] collectdata(String collum) {
		double[][] databox= new double[shipping.length][];
		double maxVal = 0;
		double [] temp;
		
		for(int i=0; i < shipping.length; i++) {
			databox[i] = new double[shipping[i].size()];
			//gather data by product per region 
			switch(collum.toLowerCase()){	
			case"employeeid":
				//create a nested group of arrays using collum information
				for(int ii=0; ii < shipping[i].size(); ii++) {
					databox[i][ii]=Double.parseDouble(shipping[i].get(ii).getEMPLOYEEID().trim());
					}
				temp=databox[i];
				maxVal=Statistics.minMax(temp, "max"); //find and store the highest value of the array
				break;
				
			case"shipvia":
				//create a nested group of arrays using collum information
				for(int ii=0; ii < shipping[i].size(); ii++) {
					databox[i][ii]=Double.parseDouble(shipping[i].get(ii).getSHIPVIA().trim());
					}
				temp=databox[i];
				maxVal=Statistics.minMax(temp, "max"); //find and store the highest value of the array
				break;
				
			case"freight":
				//create a nested group of arrays using collum information
				for(int ii=0; ii < shipping[i].size(); ii++) {
					databox[i][ii]=Double.parseDouble(shipping[i].get(ii).getFREIGHT().trim());
					
				}
				temp=databox[i];
				maxVal=Statistics.minMax(temp, "max"); //find and store the highest value of the array
				break;
				
			default:
				//User Error handling
				System.out.println(collum + " appears to be an invalid Collum in this table. This report has been reset.");
				databox=null;
				break;
				}
			
			//Custom print out message
			System.out.print("The sample size taken for this week was " + Math.abs(shipping[i].size()) + 
					". The highest recorded value for this stock was " + maxVal + "\n" +
					"The stock values for " + collum + " in Region "+shipping[i].get(0).getSHIPCOUNTRY()+
					": " + Arrays.toString(databox[i]) + "\n\n");
		}
		return databox;
	}
}