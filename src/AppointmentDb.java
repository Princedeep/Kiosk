import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

//Brandon and Prince

public class AppointmentDb {
	private int recordsInserted;
	private static Connection con = null; // Connection variable used for creating connection
	private static String connectionString = "jdbc:mysql://localhost/kiosk?useSSL=false"; // String variable for
																							// connnection url
	private static String username = "root"; // String variable for Username of database
	private static String password = "root"; // String variable for password of database
	private String a = null;
	private JTable table;
	// Model m = new Model();
	PreparedStatement pstmt = null;

	// This method is called when the professor button is pressed in
	// AppointmentInterface to query the professor emails
	public void FillTable(JTable table) {

		try {

			con = DriverManager.getConnection(connectionString, username, password);
			String query = "select EmailAddress from professor";
			Statement st = con.createStatement();
			ResultSet set = st.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(set));
		} catch (Exception e) {

		}
	}

	// Used for the View and Cancel appointment button to select all information in
	// the appointments table
	public void getAppointInfo(JTable table) {
		try {

			con = DriverManager.getConnection(connectionString, username, password);
			String query = "select * from appointments";
			Statement st = con.createStatement();
			ResultSet set = st.executeQuery(query);
			table.setModel(DbUtils.resultSetToTableModel(set));
		} catch (Exception e) {

		}
	}

	// Used in the CancelAppointment interface to delete information from database
	protected void deleteAppointment(JTable table1) {
		try {

			con = DriverManager.getConnection(connectionString, username, password);
			String query = "DELETE from appointments WHERE AppointmentID = ?";
			java.sql.PreparedStatement st = con.prepareStatement(query);

			// int selectedColumn = table1.getSelectedColumn();
			int selectedRow = table1.getSelectedRow();
			String bd = table1.getValueAt(selectedRow, 5).toString();
			st.setString(1, bd);
			int count = st.executeUpdate();
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

	// Insert data from AppointmentInterface fields to database
	protected void InsertData(JTextField c, JTextField profEmail, String daySelected, String timeSelected,
			String roomSelected) {

		try {
			con = DriverManager.getConnection(connectionString, username, password);
			String query = "insert into appointments(StudentId,ProfessorEmail,Day,AppointmentTime,Room) Values(?,?,?,?,?)";// SQL
																															// query
			java.sql.PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, c.getText());
			stmt.setString(2, profEmail.getText());
			stmt.setString(3, daySelected);
			stmt.setString(4, timeSelected);
			stmt.setString(5, roomSelected);
			int count = stmt.executeUpdate();

			if (count > 0) {
				JOptionPane.showMessageDialog(null, "Saving Data");
			}
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
