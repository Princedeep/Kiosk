import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class TimetablePanel extends JPanel {
	GridLayout layout = new GridLayout(4, 1);
	JLabel text = new JLabel("Timetable");
	JButton btnLoad = new JButton("Load Timetable");
	JButton btnAdd = new JButton("Add To Timetable");
	JButton btnExport = new JButton("Export Timetable");
	JButton btnEdit = new JButton("Edit");
	JButton btnDel = new JButton("Delete Row");
	JFileChooser fc = new JFileChooser();
	JTable table = new JTable();
	String details;
	File file;
	File outputFile;
	Object[][] data;
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

	FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");

	TimetablePanel(){	
		JFrame window = (JFrame) SwingUtilities.getWindowAncestor(this);
		this.setLayout(layout);
		fc.addChoosableFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		add(btnLoad);
		add(btnAdd);
		add(btnExport);
		add(btnEdit);
		//add(btnDel);

		model.setColumnIdentifiers(columnNames);

		table.setModel(model);
		table.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		add(table);
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

				Object rowData[] = new Object[5];
				rowData[0] = subject; 
				rowData[1] = startDate; 
				rowData[2] = startTime; 
				rowData[3] = endDate; 
				rowData[4] = endTime; 
				model.addRow(rowData);
				// Create JOptionPane or other method of receiving user input
				// Use that input to add a new row to the model by populating the above Object array
				// When the array is populated call the model.addRow() method to insert the new row
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
				String [] rowData = vector.toArray(new String[vector.size()]);
				
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
		
		/*btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int selectedRowIndex = table.getSelectedRow();
					
					subjectField.setText(model.getValueAt(selectedRowIndex, 0).toString());
					startDateField.setText(model.getValueAt(selectedRowIndex, 1).toString());
					startTimeField.setText(model.getValueAt(selectedRowIndex, 2).toString());
					endDateField.setText(model.getValueAt(selectedRowIndex, 3).toString());
					endTimeField.setText(model.getValueAt(selectedRowIndex, 4).toString());
				
					
					model.removeRow(table.getSelectedRow());
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
			}
			
		});*/
		
		
	}
	public void  CSVReader(){
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			Object[] lines = br.lines().toArray();

			for(int i = 0; i <lines.length; i++){
				String[] row = lines[i].toString().split(",");
				model.addRow(row);				
				model.fireTableDataChanged();
			}

		} catch (FileNotFoundException e){

		}
	}
}

