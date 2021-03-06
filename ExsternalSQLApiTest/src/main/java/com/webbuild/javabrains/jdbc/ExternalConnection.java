package com.webbuild.javabrains.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.webbuild.javabrains.model.TableObjects;



public class ExternalConnection{
	//Static Server connection Data Source
		private static String myDriver = "oracle.jdbc.OracleDriver";
		private static String server = "jdbc:oracle:thin:@localhost:1521:xe";
		private static String username = "system";
		private static String password = "student";
		static List<TableObjects>Topics;

		//connect to a remote server
		public static Connection dbConnect() {
			try {
				// create a handshake connection between java and the desired SQL server. 
				Class.forName(myDriver);
				//connect to an SQL  database.
				Connection conn = DriverManager.getConnection(server, username, password);
				return conn;
				
			}catch(Exception e){
				//error handling
				System.err.println("Got an exception! ");
				System.err.println(e.getMessage());
				return null;
			}
		}
		
		//populate data layer
		public static List<TableObjects> Shipping(String field) {
			try {
				// create the java statement to connect to a specific database 
				Statement sta = dbConnect().createStatement();
				Topics = new ArrayList<>(Arrays.asList());
				// execute the query just like any normal SQL , and get a java result set
				String query = "SELECT * FROM ORDERS WHERE SHIPCOUNTRY='"+field+"'";
				ResultSet rs = sta.executeQuery(query);
				
				//populate the object with the returned results
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
				sta.close();//close off server connection. release use resources 
				return Topics;
				
			}catch(Exception e){
				//error handling
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
			}
			return null;
		}
		
		//update a table with the information on the data layer
		public static void Update(List<TableObjects> TableData) {
			try {
				// create the java statement
				Statement sta = dbConnect().createStatement();
				
				//generate the SQL query dynamically
				for (int i = 0; i < TableData.size(); i++) {	
					
					//build a check statement to see if the object needs to be updated or inserted
					String checkstatement = "select COUNT(*) from orders where ORDERID = '"+TableData.get(i).getORDERID()+"'";
					
					//update statement
					String updateStatement = "UPDATE ORDERS SET "+
							"CUSTOMERID = '" + TableData.get(i).getCUSTOMERID()+"', "+
							"EMPLOYEEID = '" + TableData.get(i).getEMPLOYEEID()+"', "+
							"SHIPVIA = '" + TableData.get(i).getSHIPVIA()+"', "+ 
							"FREIGHT = '" + TableData.get(i).getFREIGHT()+"', "+ 
							"SHIPNAME = '" + TableData.get(i).getSHIPNAME()+"', "+
							"SHIPCOUNTRY = '" + TableData.get(i).getSHIPCOUNTRY()+
					"' WHERE ORDERID = '"+ TableData.get(i).getORDERID()+"'";
					
					//insert statement
					String insertStatement = "insert into orders "+
					"(ORDERID, CUSTOMERID, EMPLOYEEID, SHIPVIA, FREIGHT, SHIPNAME, SHIPCOUNTRY)"+
							   "values ('"+TableData.get(i).getORDERID()+"', '"+
							   		TableData.get(i).getCUSTOMERID()+"', '"+
							   		TableData.get(i).getEMPLOYEEID()+"', '"+
							   		TableData.get(i).getSHIPVIA()+"', '"+
							   		TableData.get(i).getFREIGHT()+"', '"+
							   		TableData.get(i).getSHIPNAME()+"', '"+
							   		TableData.get(i).getSHIPCOUNTRY()+"')";
					
					//if exists (select * from table with (updlock,serializable) where key = @key)
					ResultSet rs = sta.executeQuery(checkstatement);
					rs.next();
					if(rs.getInt("COUNT(*)")==1) { //if rowcount = 1 then
						System.out.println(rs.getInt("COUNT(*)"));
						sta.executeUpdate(updateStatement);
					}else { //if rowcount = 0 then
						System.out.println(rs.getInt("COUNT(*)"));
						sta.executeUpdate(insertStatement);
					}//if rowcount = 2 then this isn't a primary key
					
				}
				sta.close();//close off server connection. release use resources 
				System.out.println("Database updated");
			}catch(Exception e){
				//error handling
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
			}
		}
		
		//delete a table row
		public static void DeleteRow(List<TableObjects> TableData, String Orderid) {
			try {
				// create the java statement to connect to a specific database 
				Statement sta = dbConnect().createStatement();
				for (int i = 0; i < TableData.size(); i++) {
					if(TableData.get(i).getORDERID().equalsIgnoreCase(Orderid)) {
						// execute the query just like any normal SQL , and get a java result set
						String query = "DELETE FROM orders WHERE ORDERID = '"+Orderid+"'";
						sta.executeUpdate(query);
					}
				}
				sta.close();//close off server connection. release use resources 	
			}catch(Exception e){
				//error handling
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
			}
		}
		
		//Determine method by which to organize large amounts of dat to save on memory space
		public static String[] SetSortParamiters() {
			try {
				// create the java statement to connect to a specific database 
				Statement sta = dbConnect().createStatement();
				int i=0;
				
				//get the size of the array needed to sort all objects
				String StartingQuery= "select count(DISTINCT SHIPCOUNTRY) AS \"Size\" from Orders";
				ResultSet storedValue = sta.executeQuery(StartingQuery);
				storedValue.next();
				String[] Country=new String[storedValue.getInt("Size")];
		        
				//Create list of Search Terms
				String CollectCountery = "select DISTINCT SHIPCOUNTRY from Orders";
				ResultSet list = sta.executeQuery(CollectCountery);
				while(list.next()){
					Country[i]=list.getString("SHIPCOUNTRY");
					i++;
				}
				
				sta.close();//close off server connection. release use resources 
				return Country;
				
			}catch(Exception e){
				//error handling
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
				return null;
			}
		}
		
		@SuppressWarnings("unchecked") //Create a catalog of tables sorting Data by the populate and sort methods
		public static ArrayList<TableObjects>[] SortedShipping() {
			try {
				String[] SortList = SetSortParamiters(); //collect parameters
				ArrayList<TableObjects>[] SortDatabase = new ArrayList[SortList.length];
		        
				for(int i=0; i < SortList.length; i++) {
					SortDatabase[i] = new ArrayList<TableObjects>(); 
					SortDatabase[i].addAll(Shipping(SortList[i])); //populate table
				}
				return SortDatabase;
				
			}catch(Exception e){
				//error handling
				System.err.println("Got an exception!");
				System.err.println(e.getMessage());
				return null;
			}	
		}
		
		//test lab method for ease of debugging
		public static void main(String[] args) {
			/*List<TableObjects> shipSpain = ExternalConnection.SpainShipping();
			ArrayList<TableObjects>[] shipping = SortedShipping();
			for(int i=0; i < shipping.length; i++) {
				for(int ii=0; ii < shipping[i].size(); ii++) {
					System.out.println(shipping[i].get(ii).getORDERID()+" "+shipping[i].get(ii).getSHIPCOUNTRY());
			     }
				System.out.println("New country"+ i);
			}
		   ShippingUpdate(shipSpain);*/
			
		}
}
