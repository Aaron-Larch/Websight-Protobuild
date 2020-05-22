package com.webbuild.javabrains.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.webbuild.javabrains.model.OrderDetails;

public class SecondSQLConnection {
	//Static Server connection Data Source
			private static String myDriver = "oracle.jdbc.OracleDriver";
			private static String server = "jdbc:oracle:thin:@localhost:1521:xe";
			private static String username = "system";
			private static String password = "student";
			static List<OrderDetails> orderDe;

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
			
			public static List<OrderDetails> order(int product){
				try {
					Statement sta = dbConnect().createStatement();
					ResultSet rs;
					orderDe = new ArrayList<>(Arrays.asList());
					// execute the query just like any normal SQL , and get a java result set
					String inputquery = "SELECT * FROM ORDERDETAILS Where productID = "+product
							+" And Discount =(SELECT Max(Discount) FROM ORDERDETAILS Where productID = "+product+")";
							 
					rs = sta.executeQuery(inputquery);
					
					while (rs.next()) {
						orderDe.add(new OrderDetails(
								rs.getInt("OrderID"),
								rs.getInt("ProductID"),
								rs.getDouble("UnitPrice"),
								rs.getDouble("Discount")
						));
					}
					sta.close();//close off server connection. release use resources 
					return orderDe;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
}
