import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;


public class NotificationStudent extends JPanel {
	GridLayout layout = new GridLayout(4, 1);
	JLabel text = new JLabel("Notification");
	JButton btnNew = new JButton("New Notification");
	JButton btnDel = new JButton("Delete Row");
	JTable table = new JTable();
	String details;
	List<Object[]> data = new ArrayList<Object[]>();
	List rowData = new ArrayList();
	String columnNames[] = {"Message"};
	String HEADER = ("Message");
	DefaultTableModel model = new DefaultTableModel(0, 0){
		public boolean isCellEditable(int row, int column) {
			if (row > 0)
				return false;
		        else
		        return false;
		}
	};
	
	JTextField messageField = new JTextField();
	private Connection con = null;
	PreparedStatement pstmt = null;
	private final String connectionString = "jdbc:mysql://localhost/message_store?useSSL=false";
	private final String username = "message";
	private final String password = "password";
	
	public Connection getConnection() throws SQLException{
		try {
		if(con != null){
			System.out.println("Cannot create new connection, one exists already");
		}
		else{
			con = DriverManager.getConnection(connectionString, username, password);
		}
		}
		catch(SQLException ex) {
			System.out.println(ex.getMessage());
			throw ex;
		}
		return con;
	}

	
	NotificationStudent(){	
		JFrame window = (JFrame) SwingUtilities.getWindowAncestor(this);
		this.setLayout(layout);
		
		model.setColumnIdentifiers(columnNames);

		table.setModel(model);
		table.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		add(table);
		
		// Populate the table
		dbLoad();
		populateTable();
		
	}
	public void dbLoad(){
		try{
			con = getConnection();
			
			String readQuery = "SELECT * FROM MESSAGE_STORE";
			pstmt = con.prepareStatement(readQuery);
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);
			
			
			// Iterate through the result set, adding to the arraylist
			while(rs.next()){
				int id = rs.getInt("ID");
				String message = rs.getString("MESSAGE");
				boolean checkRead = rs.getBoolean("CHECK_READ");
				data.add(new Object[]{id, message, checkRead});
				model.addRow(new Object[]{id, message, checkRead});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void populateTable(){
		try {
			con = getConnection();
			
			// Select all records from message_store
			pstmt = con.prepareStatement("SELECT message FROM message_store");
			
			// Execute query
			ResultSet rs = pstmt.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));	
			for(int row = 0; row < model.getRowCount(); row++){
				System.out.println(model.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
