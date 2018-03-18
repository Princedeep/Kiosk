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

				con = DriverManager.getConnection(connectionString, username, password); // Opening
																							// connection
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
		/**
		 * Catch block to catch Sql Exception
		 */
		catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	}

	protected Boolean getresult(String id) throws SQLException {
		con = DriverManager.getConnection(connectionString, username, password);

		// -- Admin  1
		Boolean idFound = false;
		ResultSet exeAdmin;
		String queryAdmin = "Select * from admin where id=? ";
		java.sql.PreparedStatement stmtAdmin = con.prepareStatement(queryAdmin);
		stmtAdmin.setString(1, id);
		exeAdmin = stmtAdmin.executeQuery();

		// -- Student  2
 		ResultSet exeStudent;
		String queryStudent = "Select * from student where id=? ";
		java.sql.PreparedStatement stmtStudent = con.prepareStatement(queryStudent);
		stmtStudent.setString(1, id);
		exeStudent = stmtStudent.executeQuery();

		// -- Proff   3
		ResultSet exeProf;
		String queryProff = "Select * from professors where id=? ";
		java.sql.PreparedStatement stmtProff = con.prepareStatement(queryProff);
		stmtProff.setString(1, id);
		exeProf = stmtProff.executeQuery();

		
		if (exeAdmin.next() == true) {
			idFound = true;
			Validations.loginAccount=1;
		}
		if (exeStudent.next() == true) {
			idFound = true;
			Validations.loginAccount=2;
		}
		if (exeProf.next()) {
			idFound = true;
			Validations.loginAccount=3;
		}

		return idFound;

	}

}
