
//Adam Tremblett

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class TimetableStudent extends JPanel {
	GridLayout layout = new GridLayout(4, 1);
	JLabel text = new JLabel("Timetable");
	JTable table = new JTable();

	List<String[]> data = new ArrayList<String[]>();
	String[] rowData;
	String columnNames[] = { "Subject", "Start Date", "Start Time", "End Date", "End Time" };
	String HEADER = ("Subject, Start Date, Start Time, End Date, End Time");
	DefaultTableModel model = new DefaultTableModel(0, 0) {
		public boolean isCellEditable(int row, int column) {
			if (row > 0)
				return false;
			else
				return false;
		}
	};

	private Connection con = null;
	PreparedStatement pstmt = null;
	private final String connectionString = "jdbc:mysql://localhost/kiosk?useSSL=false";
	private final String username = "root";
	private final String password = "root";

	TimetableStudent() {
		JFrame window = (JFrame) SwingUtilities.getWindowAncestor(this);
		this.setLayout(layout);

		model.setColumnIdentifiers(columnNames);

		table.setModel(model);
		table.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		add(table);

		initialLoad();
	}

	public Connection getConnection() throws SQLException {
		try {
			if (con != null) {
				System.out.println("Cannot create new connection, one exists already");
			} else {
				con = DriverManager.getConnection(connectionString, username, password);
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}
		return con;
	}

	public void initialLoad() {
		try {
			con = DriverManager.getConnection(connectionString, username, password);

			String readQuery = "SELECT * FROM TIMETABLE";
			pstmt = con.prepareStatement(readQuery);

			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);

			// Iterate through the result set, adding to the arraylist
			while (rs.next()) {
				String subject = rs.getString("SUBJECT");
				String startDate = rs.getString("STARTDATE");
				String startTime = rs.getString("STARTTIME");
				String endDate = rs.getString("ENDDATE");
				String endTime = rs.getString("ENDTIME");
				data.add(new String[] { subject, startDate, startTime, endDate, endTime });
				model.addRow(new String[] { subject, startDate, startTime, endDate, endTime });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
