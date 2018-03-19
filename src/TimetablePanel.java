import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class TimetablePanel extends JPanel {
	GridLayout layout = new GridLayout(5, 1);
	JLabel text = new JLabel("Timetable");
	JButton btnLoad = new JButton("Load Timetable");
	JButton btnAdd = new JButton("Add To Timetable");
	JFileChooser fc = new JFileChooser();
	JTable table = new JTable();
	File file;
	Object[][] data;
	String columnNames[] = {"Subject", "Start Date", "Start Time", "End Date", "End Time"};
	DefaultTableModel model = new DefaultTableModel(0, 0){
		public boolean isCellEditable(int row, int column) {
	        return false;
	    }
	};
	
	FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
	
	
	TimetablePanel(){	
		this.setLayout(layout);
		fc.addChoosableFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		add(text);
		add(btnLoad);
		add(btnAdd);
		
		model.setColumnIdentifiers(columnNames);
		
		table.setModel(model);
		table.setCellSelectionEnabled(true);
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent e) {
	          String selectedData = null;

	          int[] selectedRow = table.getSelectedRows();
	          int[] selectedColumns = table.getSelectedColumns();

	          for (int i = 0; i < selectedRow.length; i++) {
	            for (int j = 0; j < selectedColumns.length; j++) {
	              selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
	            }
	          }
	          System.out.println("Selected: " + selectedData);
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
				model.addRow(new Object[]{});
				// Create JOptionPane or other method of receiving user input
				// Use that input to add a new row to the model by populating the above Object array
				// When the array is populated call the model.addRow() method to insert the new row
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
