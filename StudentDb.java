
/**
 * File: StudentDb
 * Author: Princedeep Singh
 * Description: Class used by Student panel for database operations
 * Date Last Modified:
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

public class StudentDb {
	private int recordsInserted; // Integer to keep track of record inserted
	private static Connection con = null; // Connection variable used for creating connection
	private static String connectionString = "jdbc:mysql://localhost/kiosk"; // String variable for connnection url
	private static String username = "root"; // String variable for Username of database
	private static String password = "death101"; // String variable for password of database
	private String a = null;
	JTable t = new JTable();

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

	/**
	 * 
	 * Method to delete record from Student Table
	 * 
	 */
	protected void deleteStudent(JTextField Id) {
		try {
			String query = "DELETE FROM student WHERE StudentID =?";
			con = DriverManager.getConnection(connectionString, username, password);
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, Id.getText());
			int count = stmt.executeUpdate();
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "Deleted Sucessfully");
			} else {
				JOptionPane.showMessageDialog(null, "Id not Found", "Error", JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	/**
	 * Method to update student
	 *
	 */
	protected void updateStu(JTextField a, JTextField b, JTextField c, JPasswordField e) {
		try {
			con = DriverManager.getConnection(connectionString, username, password);

			String query = "UPDATE student SET StudentName=?, EmailAddress=?,Password=? WHERE StudentID=?";
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			if (md5(e.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(null, "Error in hashing password");
			}

			stmt.setString(1, b.getText());
			stmt.setString(2, c.getText());
			stmt.setString(4, a.getText());
			stmt.setString(3, md5(e.getPassword()));

			int count = stmt.executeUpdate();

			if (count > 0) {
				JOptionPane.showMessageDialog(null, "Updated Sucessfully");
			} else {
				JOptionPane.showMessageDialog(null, "Id not Found", "Error", JOptionPane.ERROR_MESSAGE);
			}
			con.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void FillTable(JTable table) {

		try {

			con = DriverManager.getConnection(connectionString, username, password);
			String query = "select * from student";
			Statement st = con.createStatement();
			ResultSet set = st.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(set));
		} catch (Exception e) {

		}
	}

	/**
	 * Method to insert data of student in database
	 */
	protected void InsertData(JTextField a, JTextField b, JTextField c, JPasswordField e) {
		try {
			con = DriverManager.getConnection(connectionString, username, password);
			String query = "insert into student(StudentID,StudentName,EmailAddress,Password)  Values(?,?,?,?)";
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, a.getText());
			stmt.setString(2, b.getText());
			stmt.setString(3, c.getText());

			if (md5(e.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(null, "Error in hashing password");
			}
			stmt.setString(4, md5(e.getPassword()));
			int count = stmt.executeUpdate();

			if (count > 0) {
				JOptionPane.showMessageDialog(null, "Saving Data");
			}
			con.close();
		} catch (SQLException ex) {

			JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
		}

	}
}
