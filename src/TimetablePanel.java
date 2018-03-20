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

		model.setColumnIdentifiers(columnNames);

		table.setModel(model);
		table.setCellSelectionEnabled(true);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = table.getSelectedRow();
				int column = table.getColumnCount();

				for(int i = 0; i < column; i++) {
					details = (String) table.getValueAt(selectedRow, i);
					System.out.println(details);
				}
				DetailsPanel detailsPanel = new DetailsPanel(details);

				window.add(detailsPanel);
				window.revalidate();
			}
		});

		add(table);
		btnLoad.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(getParent());
				file = fc.getSelectedFile();		
				CSVReader();
			}
		}); 
		btnAdd.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] fields= {
						"Subject", subjectField,
						"Start Date", startDateField,
						"Start Time", startTimeField,
						"End Date", endDateField,
						"End Time", endTimeField
				};
				
				/**
				 * The following allows user to input information for timetable one field
				 * at a time. This solution is more elegant for a touch screen, prevents
				 * mistyping. Restrictions on the input types can be added.
				 */
				
				String subject = JOptionPane.showInputDialog(null, "Input Subject");
				String startDate = JOptionPane.showInputDialog(null, "Input Start Date");
				String startTime = JOptionPane.showInputDialog(null, "Input Start Time");
				String endDate = JOptionPane.showInputDialog(null, "Input End Date");
				String endTime = JOptionPane.showInputDialog(null, "Input End Time");
				
				model.addRow(new Object[]{
						subject,
						startDate,
						startTime,
						endDate,
						endTime						
				});
				
				/**
				 * Second Option for user input, more fields on one window 
				 * so less elegant for touch screen use
				 * JOptionPane.showConfirmDialog(null, fields, "Input new timetable item", JOptionPane.OK_CANCEL_OPTION);	
								
				model.addRow(new Object[]{
						subjectField.getText().toString(),
						startDateField.getText().toString(),
						startTimeField.getText().toString(),
						endDateField.getText().toString(),
						endTimeField.getText().toString()
				});
				 */
				
				// Create JOptionPane or other method of receiving user input
				// Use that input to add a new row to the model by populating the above Object array
				// When the array is populated call the model.addRow() method to insert the new row
			}
		});
		btnExport.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FileWriter fileWriter = new FileWriter(outputFile);
					fileWriter.append(HEADER);
					for(int row = 1; row < model.getRowCount(); row++){
						for(int col = 0; col < model.getColumnCount(); col++){
							fileWriter.append(model.getValueAt(row, col).toString());
							fileWriter.append(",");
							System.out.println(model.getValueAt(row, col).toString());
						}
						fileWriter.append("\n");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});

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

