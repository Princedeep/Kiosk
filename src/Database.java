import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/* File: FishStick.java
 * Author: Princedeep Singh
 * Date: Feb, 2018
 * Description: Simple data transfer object.
 */


public class Database{
    private  int recordsInserted; // Integer to keep track of record inserted 
	private static  Connection con = null; // Connection variable used for creating connection
	private  String connectionString = "jdbc:mysql://localhost/info"; //String variable for connnection url
	private  String username = "assignment1"; // String variable for Username of database
	private String password = "password"; // String variable for password of database
	private  String a=null;
	//private static PreparedStatement pstmt = null; // Variable for sql prepared statement
	
	 
	public  void openConnection() {

		try {
			if (con != null) // Condition to check null value of connection
			{
				System.out.println("Cannot create new connection from consumer, one exists already");
				// Statement to Print message if  above condition is true
			} else 
			{ // Execute con statement on failure of if condition
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

public static void insert()
{
	/**
	 * Try block to check if connection is null or closed
	 */
	try {	
		if (con == null || con.isClosed()) // condition to check database connection
		{
        System.out.println("Cannot insert records by consumer, no connection or connection closed");
        // Statement to Print message if  above condition is true
		}

		// Sql statement or query to insert value into database by taking value from fishstick object
		String a =
				"INSERT INTO users (user, password)"  + "VALUES('new', 'passme')" 
		          
		;
		 try {
	                PreparedStatement pstmt = con.prepareStatement(a);
	          
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		// System.out.println("Cannot insert records by consume");
		/**
		 * If condition to check if all records inserted
		 * and then print total number of records inserted
		 */

			
		
		
	
	} 
	
	/**
	 * Catch block to catch Sql Exception
	 */
	catch (SQLException e)
	{
		e.printStackTrace();
	} 
		
		/**
		 * Catch block to catch Sql Exception
		 */
	
	}
}
