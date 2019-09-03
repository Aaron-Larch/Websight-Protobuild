/**
 * 
 */
package scrapCode;

import java.sql.*;
import java.util.Arrays;


/**
 * @author gce
 *
 */
//https://www.youtube.com/watch?v=smYXa0a0cLw 2:03 will give you a partial copy of what you need to do filled in the rest with
//https://stackoverflow.com/questions/36038111/connect-to-my-oracle-12c-database-with-java-in-eclipse and some code of my own design
public class callSQL {
	public static Connection dbConnect() {
		try {
			String myDriver = "oracle.jdbc.OracleDriver";
			String server = "jdbc:oracle:thin:@localhost:1521:xe";
			String username = "System";
			String password = "password";
			
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(server, username, password);
			return conn;
			
		}catch(Exception e){
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String myDriver = "oracle.jdbc.driver.OracleDriver";
		String server = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "System";
		String password = "password";

		// our SQL SELECT query.
		// if you only need a few columns, specify them by name instead of using "*"
		String query = "SELECT * FROM EMPLOYEES WHERE HIREDATE=(SELECT MAX(HIREDATE) FROM EMPLOYEES WHERE GENDER='M')";

		try {
			// create our mysql database connection
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(server, username, password);

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			String[] headders= {"EMPLOYEEID", "LASTNAME", "FIRSTNAME","HIREDATE", "TITLE", "DEPARTMENTID"};
			String[] output = new String[headders.length];
			
			while (rs.next()) {
				String id = rs.getString("EMPLOYEEID");
				String firstName = rs.getString("LASTNAME");
				String lastName = rs.getString("FIRSTNAME");
				String dateCreated = rs.getString("HIREDATE");
				String title = rs.getString("TITLE");
				String department = rs.getString("DEPARTMENTID");
				for(int i=0; i<headders.length; i++) {output[i] = rs.getString(headders[i]);}
				
				// print the results
				System.out.println(Arrays.toString(output));
				System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, title, department);
			}
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public callSQL(String server, String username, String password, String driver, String query) {
		try {
			// create our mysql database connection
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(server, username, password);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Date dateCreated = rs.getDate("date_created");
				boolean isAdmin = rs.getBoolean("is_admin");
				int numPoints = rs.getInt("num_points");

				// print the results
				System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
			}
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}
}
