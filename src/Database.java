import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/* File: Database.java
 * Author: Princedeep Singh
 * Date Last Modified: Feb, 2018
 * Description: Simple database class to create queries and operations to database
 */

public class Database {
	private int recordsInserted; // Integer to keep track of record inserted
	private static Connection con = null; // Connection variable used for creating connection
	private static String connectionString = "jdbc:mysql://localhost/info"; // String variable for connnection url
	private static String username = "assignment1"; // String variable for Username of database
	private static String password = "password"; // String variable for password of database
	private String a = null;

	// private static PreparedStatement pstmt = null; // Variable for sql prepared
	// statement
	public Database() {

	}

	/**
	 * Method to Open Database Connection
	 */
	public static void openConnection() {

		try {
			if (con != null) // Condition to check null value of connection
			{
				System.out.println("Cannot create new connection from consumer, one exists already");
				// Statement to Print message if above condition is true
			} else { // Execute con statement on failure of if condition
				con = DriverManager.getConnection(connectionString, username, password); // Opening connection
			}

		} // end of openConnection() method

		/**
		 * Catch block to catch Sql Exception
		 */
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Method to close Database connection
	 */
	private void closeConnection() {
		try {
			if (con != null) { // Condition to check null value of connection

				con.close();// closing connection
			}
		}

		/**
		 * Catch block to catch Sql Exception
		 */
		catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */

	protected ResultSet getresult(String id) throws SQLException {
		con = DriverManager.getConnection(connectionString, username, password);
		ResultSet exe;
		String query = "Select * from admin where id=? ";
		java.sql.PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, id);
		exe = stmt.executeQuery();

		return exe;

	}//
}
