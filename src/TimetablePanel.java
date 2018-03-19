import java.util.List;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;






public class TimetablePanel extends JPanel {
	GridLayout layout = new GridLayout(1, 1);
	JLabel text = new JLabel("Timetable");
	JButton btnLoad = new JButton("Load Timetable");
	JButton btnAdd = new JButton("Add To Timetable");
	JFileChooser fc = new JFileChooser();
	JTable table;
	File file;
	String rowData[][];
	String columnNames[] = {"Subject", "Start Date", "Start Time", "End Date", "End Time"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	
	FileNameExtensionFilter filter = new FileNameExtensionFilter("*.csv", "csv");
	
	
	TimetablePanel(){	
		this.setLayout(layout);
		fc.addChoosableFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		add(text);
		add(btnLoad);
		add(btnAdd);
				
		btnLoad.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fc.showOpenDialog(getParent());
				System.out.println(returnVal);
				file = fc.getSelectedFile();				
			}
		}); 
	}
}
