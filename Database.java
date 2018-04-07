import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	private static String connectionString = "jdbc:mysql://localhost/kiosk"; // String variable for connnection url
	private static String username = "root"; // String variable for Username of database
	private static String password = "death101"; // String variable for password of database
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
	 * Method to hash password
	 * 
	 */
	private String md5(char[] c) {
		try {
			MessageDigest dg = MessageDigest.getInstance("MD5");
			dg.update((new String(c)).getBytes("UTF-8"));
			String str = new String(dg.digest());
			return str;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	protected Boolean getresult(String name, String pass, String id) throws SQLException {
		con = DriverManager.getConnection(connectionString, username, password);

		// -- Admin 1
		Boolean idFound = false;
		ResultSet exeAdmin;
		String queryAdmin = "Select * from admin where AdminName=? and Password=? and AdminID=? ";
		java.sql.PreparedStatement stmtAdmin = con.prepareStatement(queryAdmin);
		stmtAdmin.setString(1, name);
		stmtAdmin.setString(2, pass);
		stmtAdmin.setString(3, id);

		exeAdmin = stmtAdmin.executeQuery();

		// -- Student 2
		ResultSet exeStudent;
		String queryStudent = "Select * from student where StudentName=? and Password=? and StudentID=?";
		java.sql.PreparedStatement stmtStudent = con.prepareStatement(queryStudent);
		char[] password = pass.toCharArray();
		stmtStudent.setString(1, name);
		stmtStudent.setString(2, md5(password));
		stmtStudent.setString(3, id);
		exeStudent = stmtStudent.executeQuery();

		// -- Proff 3
		ResultSet exeProf;
		String queryProff = "Select * from professor where ProfessorName=? and Password=? and ProfessorID=? ";
		java.sql.PreparedStatement stmtProff = con.prepareStatement(queryProff);
		stmtProff.setString(1, name);
		stmtProff.setString(2, md5(password));
		stmtProff.setString(3, id);
		exeProf = stmtProff.executeQuery();

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

		return idFound;

	}
}