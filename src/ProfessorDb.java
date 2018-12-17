
/**
 * File: Professor Db
 * Author: Princedeep Singh
 * Description: This class is used by Add professor class for database operations.
 * 
 * Refrences rs2xml.jar : https://sourceforge.net/projects/finalangelsanddemons/
 * 
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class ProfessorDb {

	private static Connection con = null; // Connection variable used for creating connection
	private static String connectionString = "jdbc:mysql://localhost/kiosk"; // String variable for connnection url
	private static String username = "root"; // String variable for Username of database
	private static String password = "root"; // String variable for password of database
	private String a = null;
	JTable t = new JTable(); // Creating new Jtable

	/**
	 * 
	 * Method to hash password
	 * 
	 */
	private String hashPassword(char[] ch) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5"); // Creating instance of Message digest class
			md.update((new String(ch)).getBytes("UTF-8")); // Updating passed string as bytes
			String strPass = new String(md.digest()); // Converting password into new string
			return strPass; // returning String
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return "";

	}

	/**
	 * 
	 * Method to delete record from Professor Table
	 * 
	 */
	protected void deleteProfessor(JTextField Id) {
		try {
			String query = "DELETE FROM professor WHERE ProfessorID =?"; // Query to delete data from professor table
			con = DriverManager.getConnection(connectionString, username, password); // Connecting to database
			java.sql.PreparedStatement pst = con.prepareStatement(query); // Prepared statement for query passed
			pst.setString(1, Id.getText()); // Setting value of id passed as argument
			pst.executeUpdate(); // executing query
			JOptionPane.showMessageDialog(null, "Delete successfully");
			con.close();
		} catch (SQLException ex) { // Catching exception
			JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method to update Professor table
	 *
	 */
	protected void updateProf(JTextField a, JTextField b, JTextField c, JPasswordField e) {
		try {
			// Query to update professor table according to new values
			String query = "UPDATE professor SET ProfessorName=?, EmailAddress=?,Password=? WHERE ProfessorID=?";
			con = DriverManager.getConnection(connectionString, username, password); // Connecting to database
			java.sql.PreparedStatement stmt = con.prepareStatement(query); // Prepared statement for query passed
			if (hashPassword(e.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(null, "Error in hashing password");
			} // Showing error if password cannot be hashed

			// Setting values passed as argument in query
			stmt.setString(1, b.getText());
			stmt.setString(2, c.getText());
			stmt.setString(4, a.getText());
			stmt.setString(3, hashPassword(e.getPassword()));

			int count = stmt.executeUpdate();

			// Checking if query worked or not
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "Updated Sucessfully");
			} else {
				JOptionPane.showMessageDialog(null, "Id not Found", "Error", JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Method to Fill jtable
	 * 
	 * @param table
	 *            variable for jtable. An external jar rs2xml is used in this
	 *            method. DbUtlis class is provided by external jar which gives
	 *            model
	 */
	public void FillTable(JTable table) {

		try {

			con = DriverManager.getConnection(connectionString, username, password);// Connecting to database
			String query = "select * from professor"; // Query to select all data from professor table
			Statement st = con.createStatement(); // Creating statement
			ResultSet set = st.executeQuery(query); // Saving result set of query

			table.setModel(DbUtils.resultSetToTableModel(set)); // Loading result set into table
		} catch (Exception e) {

		}
	}

	/**
	 * Method to insert data of student in database
	 */
	protected void InsertData(JTextField a, JTextField b, JTextField c, JPasswordField d) {
		try {
			con = DriverManager.getConnection(connectionString, username, password); // Connecting to database
			// Query to insert data into professor table
			String query = "insert into professor(ProfessorID,ProfessorName,EmailAddress,Password)  Values(?,?,?,?)";
			java.sql.PreparedStatement stmt = con.prepareStatement(query); // Prepared statement for query passed

			// Setting values passed as argument in query
			stmt.setString(1, a.getText());
			stmt.setString(2, b.getText());
			stmt.setString(3, c.getText());

			if (hashPassword(d.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(null, "Error in hashing password");
			}
			stmt.setString(4, hashPassword(d.getPassword()));// Hashing password
			int count = stmt.executeUpdate(); // Executing query

			// Checking if quey worked or not
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "Saving Data");
			}
			con.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}
