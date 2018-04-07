import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class TimetableProf extends JPanel {
	GridLayout layout = new GridLayout(4, 1);
	JLabel text = new JLabel("Timetable");
	JButton btnLoad = new JButton("Load Timetable");
	JButton btnAdd = new JButton("Add To Timetable");
	JButton btnExport = new JButton("Export Timetable");
	JButton btnEdit = new JButton("Edit");
	JButton btnDel = new JButton("Delete Row");
	JButton btnSave = new JButton("Save Changes");
	JFileChooser fc = new JFileChooser();
	JTable table = new JTable();
	String details;
	File file;
	File outputFile;
	List<String[]> data = new ArrayList<String[]>();
	String [] rowData;
	String columnNames[] = {"Subject", "Start Date", "Start Time", "End Date", "End Time"};
	String HEADER = ("Subject, Start Date, Start Time, End Date, End Time");
	DefaultTableModel model = new DefaultTableModel(0, 0){
		public boolean isCellEditable(int row, int column) {
			if (row > 0)
				return false;
		        else
		        return false;
		}
	};
	
	JTextField subjectField = new JTextField();
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	DateFormat timeFormat = new SimpleDateFormat("h:mm a");
	JFormattedTextField startDateField = new JFormattedTextField(dateFormat);
	JFormattedTextField startTimeField = new JFormattedTextField(timeFormat);
	JFormattedTextField endDateField = new JFormattedTextField(dateFormat);
	JFormattedTextField endTimeField = new JFormattedTextField(timeFormat);
	
	private Connection con = null;
	PreparedStatement pstmt = null;
	private final String connectionString = "jdbc:mysql://localhost/message_store?useSSL=false";
	private final String username = "message";
	private final String password = "password";

	FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");

	TimetableProf(){	
		JFrame window = (JFrame) SwingUtilities.getWindowAncestor(this);
		this.setLayout(layout);
		fc.addChoosableFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		add(btnLoad);
		add(btnAdd);
		add(btnExport);
		add(btnEdit);
		add(btnDel);
		add(btnSave);

		model.setColumnIdentifiers(columnNames);

		table.setModel(model);
		table.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		add(table);
		
		initialLoad();
		
		btnLoad.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				int returnVal = fc.showOpenDialog(getParent());
				file = fc.getSelectedFile();		
				CSVReader();
			}
		}); 
		btnAdd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String subject = JOptionPane.showInputDialog("Enter a Subject to add:");
				while(subject.isEmpty()){
					JOptionPane.showMessageDialog(btnAdd, "Do not leave subject blank");
					subject = JOptionPane.showInputDialog("Enter a Subject to add:");
				}
					
				String startDate = JOptionPane.showInputDialog("Enter a Start Date to add:");
				while(startDate.isEmpty()){
					JOptionPane.showMessageDialog(btnAdd, "Do not leave start date blank");
					startDate = JOptionPane.showInputDialog("Enter a Start Date to add:");
				}
				
				String startTime = JOptionPane.showInputDialog("Enter an Start Time to add:");
				while(startDate.isEmpty()){
					JOptionPane.showMessageDialog(btnAdd, "Do not leave start date blank");
					startDate = JOptionPane.showInputDialog("Enter a Start Date to add:");
				}
				
				String endDate = JOptionPane.showInputDialog("Enter an End Date to add:");
				while(endDate.isEmpty()){
					JOptionPane.showMessageDialog(btnAdd, "Do not leave end date blank");
					endDate = JOptionPane.showInputDialog("Enter an End Date to add:");
				}
				String endTime = JOptionPane.showInputDialog("Enter an End Time to add:");
				while(endTime.isEmpty()){
					JOptionPane.showMessageDialog(btnAdd, "Do not leave end time blank");
					endTime = JOptionPane.showInputDialog("Enter an End Time to add:");
				}

				String rowData[] = new String[5];
				rowData[0] = subject; 
				rowData[1] = startDate; 
				rowData[2] = startTime; 
				rowData[3] = endDate; 
				rowData[4] = endTime; 
				model.addRow(rowData);
				data.add(rowData);
			}
		}); 
		
		// Edit button
		JPanel editPanel = new JPanel();
		GridLayout editPanelLayout = new GridLayout(5, 2);
		editPanel.setLayout(editPanelLayout);
		editPanel.add(new JLabel("Subject: "));
		editPanel.add(subjectField);
		editPanel.add(new JLabel("Start Date: "));
		editPanel.add(startDateField);
		editPanel.add(new JLabel("Start Time: "));
		editPanel.add(startTimeField);
		editPanel.add(new JLabel("End Date: "));
		editPanel.add(endDateField);
		editPanel.add(new JLabel("End Time: "));
		editPanel.add(endTimeField);
		
		btnEdit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<String> vector = new Vector<String>();
				vector = (Vector<String>) model.getDataVector().elementAt(table.getSelectedRow());
				rowData = vector.toArray(new String[vector.size()]);
				
				subjectField.setText(rowData[0]);
				startDateField.setText(rowData[1]);
				startTimeField.setText(rowData[2]);
				endDateField.setText(rowData[3]);
				endTimeField.setText(rowData[4]);
				
				int result = JOptionPane.showConfirmDialog(null, editPanel, "Edit timetable entry", JOptionPane.OK_CANCEL_OPTION);
				
				if (result == JOptionPane.OK_OPTION){
					rowData[0] = subjectField.getText();
					rowData[1] = startDateField.getText();
					rowData[2] = startTimeField.getText();
					rowData[3] = endDateField.getText();
					rowData[4] = endTimeField.getText();
				}
				int row = table.getSelectedRow();
				int colCount = model.getColumnCount();
				
				for(int i = 0; i < colCount; i++){
					model.setValueAt(rowData[i], row, i);
				}
				data.remove(row);
				data.add(row, rowData);
			}
		}); 
		btnExport.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					fc.showSaveDialog(getParent());
					file = fc.getSelectedFile();		
					FileWriter fileWriter = new FileWriter(file, false);
					//fileWriter.append(HEADER);
					for(int row = 0; row < model.getRowCount(); row++){
						for(int col = 0; col < model.getColumnCount(); col++){
							fileWriter.write(model.getValueAt(row, col).toString() + ',');
							System.out.println(model.getValueAt(row, col).toString());
						}
						fileWriter.write("\n");
					}
					fileWriter.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});
		
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int selectedRowIndex = table.getSelectedRow();
					model.removeRow(selectedRowIndex);
					data.remove(selectedRowIndex);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});	
		
		btnSave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					saveTimetable();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
	}
	
	public void refreshData(){
		data.removeAll(data);
		
		for(int row = 0; row < model.getRowCount(); row++){
			for(int col = 0; col < model.getColumnCount(); col++){
				rowData[row] = (String) model.getValueAt(row, col);
			}
			data.add(rowData);
		}
	}
	
	public void saveTimetable(){
		try {
			con = getConnection();
			
			// Drop the table in the database
			pstmt = con.prepareStatement("DROP TABLE IF EXISTS TIMETABLE");
			
			// Execute query
			pstmt.executeUpdate();
			
			// Create new table, using records stored in the GUI table
			String createQuery = "CREATE TABLE TIMETABLE ("
					+ "Subject VARCHAR(50), "
					+ "StartDate VARCHAR(50),"
					+ "StartTime VARCHAR(50),"
					+ "EndDate VARCHAR(50),"
					+ "EndTime VARCHAR(50));" ;
			
			pstmt = con.prepareStatement(createQuery);
			pstmt.executeUpdate();
			
			// Insert records into database
			for(int row = 0; row < model.getRowCount(); row++){
				String insertQuery = "INSERT INTO TIMETABLE VALUES (?, ?, ?, ?, ?);";
				pstmt = con.prepareStatement(insertQuery);
				String[] rowData = data.get(row);
				pstmt.setString(1, rowData[0]);
				pstmt.setString(2, rowData[1]);
				pstmt.setString(3, rowData[2]);
				pstmt.setString(4, rowData[3]);
				pstmt.setString(5, rowData[4]);
				System.out.println(pstmt);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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

	public void initialLoad(){
		try{
			con = getConnection();
			
			String readQuery = "SELECT * FROM TIMETABLE";
			pstmt = con.prepareStatement(readQuery);
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);
			
			
			// Iterate through the result set, adding to the arraylist
			while(rs.next()){
				String subject = rs.getString("SUBJECT");
				String startDate = rs.getString("STARTDATE");
				String startTime = rs.getString("STARTTIME");
				String endDate = rs.getString("ENDDATE");
				String endTime = rs.getString("ENDTIME");
				data.add(new String[]{subject, startDate, startTime, endDate, endTime});
				model.addRow(new String[]{subject, startDate, startTime, endDate, endTime});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void  CSVReader(){
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			Object[] lines = br.lines().toArray();

			for(int i = 0; i <lines.length; i++){
				rowData = lines[i].toString().split(",");
				model.addRow(rowData);		
				data.add(rowData);
				model.fireTableDataChanged();
			}
		} catch (FileNotFoundException e){

		}
	}
}
