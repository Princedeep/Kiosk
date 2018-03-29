import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class DetailsPanel extends JPanel {
	GridLayout layout = new GridLayout(5, 1);
	JLabel text = new JLabel("Details");
	JButton btnBack = new JButton("Back To Timetable");
	JButton btnDelete = new JButton("Delete Current Item");
	JTable table = new JTable();
	String columnNames[] = {"Subject", "Start Date", "Start Time", "End Date", "End Time"};
		
	DetailsPanel(String details){	
		this.setLayout(layout);
		
		add(text);
		add(btnBack);
		add(btnDelete);
		
		btnBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}); 
		btnDelete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}); 
	}
}
