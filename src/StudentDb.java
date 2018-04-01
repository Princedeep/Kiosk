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
	private static String connectionString = "jdbc:mysql://localhost/info"; // String variable for connnection url
	private static String username = "assignment1"; // String variable for Username of database
	private static String password = "password"; // String variable for password of database
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
			String query = "DELETE FROM student WHERE Id =?";
			con = DriverManager.getConnection(connectionString, username, password);
			java.sql.PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, Id.getText());
			int count = pst.executeUpdate();
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
	protected void updateStu(JTextField a, JTextField b, JPasswordField e, JTextField c, JTextField d) {
		try {
			con = DriverManager.getConnection(connectionString, username, password);

			String query = "UPDATE student SET Course_Id=?,Name=?,password=?, Email=? WHERE Id=?";
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			if (md5(e.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(null, "Error in hashing password");
			}

			stmt.setString(1, a.getText());
			stmt.setString(2, b.getText());
			stmt.setString(3, md5(e.getPassword()));
			stmt.setString(5, c.getText());
			stmt.setString(4, d.getText());
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
			// Dont call While loop or if condition here

			table.setModel(DbUtils.resultSetToTableModel(set));
		} catch (Exception e) {

		}
	}

	/**
	 * Method to insert data of student in database
	 */
	protected void InsertData(JTextField a, JTextField b, JPasswordField e, JTextField c, JTextField d) {
		try {
			con = DriverManager.getConnection(connectionString, username, password);
			String query = "insert into student(Course_Id,Name,password,id,Email)  Values(?,?,?,?,?)";
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, a.getText());
			stmt.setString(2, b.getText());
			stmt.setString(4, c.getText());
			stmt.setString(5, d.getText());

			if (md5(e.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(null, "Error in hashing password");
			}
			stmt.setString(3, md5(e.getPassword()));
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
