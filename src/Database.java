import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/* File: Database.java
 * Author: Princedeep Singh , Jay Patel
 * Date Last Modified: Feb, 2018
 * Description: Simple database class to create queries and operations to database
 */

public class Database {
	private int recordsInserted; // Integer to keep track of record inserted
	private static Connection con = null; // Connection variable used for creating connection
	private static String connectionString = "jdbc:mysql://localhost/kiosk"; // String variable for connnection url
	private static String username = "root"; // String variable for Username of database
	private static String password = "root"; // String variable for password of database
	public String yourID;
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
	 * Method to hash password Refrences for help:
	 * https://stackoverflow.com/questions/30560830/generating-an-md5-hash-with-a-char
	 */
	private String hashPassword(char[] ch) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); // Creating instance of Message digest class
			md.update((new String(ch)).getBytes("UTF-8")); // Updating passed string as bytes
			String strPass = new String(md.digest()); // Converting password into new string
			return strPass; // returning String
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

		}
		return "";

	}

	/**
	 * Method is used for authentication of users based on result which matches with
	 * passed credentials and return boolean as true
	 * 
	 * @param name
	 *            gives name of user
	 * @param pass
	 *            password of user
	 * @param id
	 *            id of user
	 * @return
	 * @throws SQLException
	 *             for any error in sql query or connection
	 * @author Jay Patel
	 */
	protected Boolean getresult(String name, String pass, String id) throws SQLException {
		con = DriverManager.getConnection(connectionString, username, password); // Creating connection with database

		/**
		 * --Admin 1
		 */
		Boolean idFound = false; // creating boolean variable
		ResultSet exeAdmin; // Creating resultset to save result of query
		/**
		 * Query to check match from admin table
		 */
		String queryAdmin = "Select * from admin where AdminName=? and Password=? and AdminID=? ";
		java.sql.PreparedStatement stmtAdmin = con.prepareStatement(queryAdmin);

		/**
		 * Setting values passed as string in method
		 */
		stmtAdmin.setString(1, name);
		stmtAdmin.setString(2, pass);
		stmtAdmin.setString(3, id);

		exeAdmin = stmtAdmin.executeQuery(); // Executing query

		/**
		 * -- Student 2
		 */
		ResultSet exeStudent; // Creating resultset to save result of query
		/**
		 * Query to check match from student table
		 */
		String queryStudent = "Select * from student where StudentName=? and Password=? and StudentID=?";
		java.sql.PreparedStatement stmtStudent = con.prepareStatement(queryStudent);
		char[] password = pass.toCharArray();

		/**
		 * Setting values passed as string in method
		 */
		stmtStudent.setString(1, name);
		stmtStudent.setString(2, hashPassword(password));
		stmtStudent.setString(3, id);
		exeStudent = stmtStudent.executeQuery(); // Executing query

		/**
		 * -- Proff 3
		 */
		ResultSet exeProf; // Creating resultset to save result of query
		/**
		 * Query to check match from professor table
		 */
		String queryProff = "Select * from professor where ProfessorName=? and Password=? and ProfessorID=? ";
		java.sql.PreparedStatement stmtProff = con.prepareStatement(queryProff);

		/**
		 * Setting values passed as string in method
		 */
		stmtProff.setString(1, name);
		stmtProff.setString(2, hashPassword(password));
		stmtProff.setString(3, id);
		exeProf = stmtProff.executeQuery(); // Executing query

		/**
		 * Setting value of boolean variable true according to match case
		 */

		if (exeAdmin.next() == true) {
			idFound = true;
			Validations.loginAccount = 1;
		}
		if (exeStudent.next() == true) {
			idFound = true;
			Validations.loginAccount = 2;
		}
		if (exeProf.next()) {
			idFound = true;
			Validations.loginAccount = 3;
		}

		return idFound; // returning value of boolean variable

	}
}