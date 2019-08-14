/**
 * 
 */
package scrapCode;

import java.sql.*;

/**
 * @author gce
 *
 */
public class callSQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array;
		double sum;

		String myDriver = "oracle.jdbc.driver.OracleDriver";
		String server = "jdbc:oracle:thin:localhost:1521:xe";
		String username = "System";
		String password = "password";

		// our SQL SELECT query.
		// if you only need a few columns, specify them by name instead of using "*"
		String query = "SELECT * FROM EMPLOYEES WHERE HIREDATE=(SELECT MAX(HIREDATE) FROM EMPLOYEES WHERE GENDER='M')";

		array = javaTestRun.createArray(5, 20);
		sum = javaTestRun.average(array);
		System.out.print(sum + "\n");

		try {
			// create our mysql database connection
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(server, username, password);

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
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
