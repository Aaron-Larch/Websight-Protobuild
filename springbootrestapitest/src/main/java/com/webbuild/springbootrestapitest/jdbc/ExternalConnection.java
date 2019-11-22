package com.webbuild.springbootrestapitest.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.webbuild.springbootrestapitest.model.TableObjects;

public class ExternalConnection {
	//Static Server Data
		private static String myDriver = "oracle.jdbc.OracleDriver";
		private static String server = "jdbc:oracle:thin:@localhost:1521:xe";
		private static String username = "system";
		private static String password = "student";
		
		static List<TableObjects>Topics = new ArrayList<>(Arrays.asList());

		public static Connection dbConnect() {
			try {
				// create our mysql database connection
				Class.forName(myDriver);
				Connection conn = DriverManager.getConnection(server, username, password);
				return conn;
				
			}catch(Exception e){
				System.err.println("Got an exception! ");
				System.err.println(e.getMessage());
				return null;
			}
		}
		
		public static List<TableObjects> SpainShipping() {
			try {
				// create the java statement
				Statement sta = dbConnect().createStatement();
				
				// execute the query, and get a java resultset
				String query = "SELECT * FROM ORDERS WHERE SHIPCOUNTRY='Spain'";
				ResultSet rs = sta.executeQuery(query);
				
				//populate the object
				while (rs.next()) {
					Topics.add(new TableObjects(
							rs.getString("ORDERID"),
							rs.getString("CUSTOMERID"),
							rs.getString("EMPLOYEEID"),
							rs.getString("SHIPVIA"),
							rs.getString("FREIGHT"),
							rs.getString("SHIPNAME"),
							rs.getString("SHIPCOUNTRY")
					));
				}
				sta.close();
				return Topics;
				
			}catch(Exception e){
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
			}
			return null;
		}
		
		public static void ShippingUpdate(List<TableObjects> TableData) {
			try {
				// create the java statement
				Statement sta = dbConnect().createStatement();
				
				for (int i = 0; i < TableData.size(); i++) {	
					String pushShatement="UPDATE ORDERS SET "+
							"CUSTOMERID = '" + TableData.get(i).getCUSTOMERID()+"', "+
							"EMPLOYEEID = '" + TableData.get(i).getEMPLOYEEID()+"', "+
							"SHIPVIA = '" + TableData.get(i).getSHIPVIA()+"', "+ 
							"FREIGHT = '" + TableData.get(i).getFREIGHT()+"', "+ 
							"SHIPNAME = '" + TableData.get(i).getSHIPNAME()+"', "+
							"SHIPCOUNTRY = '" + TableData.get(i).getSHIPCOUNTRY()+
					"' WHERE ORDERID = '"+ TableData.get(i).getORDERID()+"'";
					sta.executeUpdate(pushShatement);
				}
				
				sta.close();
				System.out.println("Database updated");
			}catch(Exception e){
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
			}
		}
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			//List<TableObjects> shipSpain = ExternalConnection.SpainShipping();
		    //for(TableObjects model : shipSpain) {
		    	//System.out.println(model.getORDERID());
		    // }
			
		}
}
