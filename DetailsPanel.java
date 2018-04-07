import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class DetailsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridLayout layout = new GridLayout(5, 1);
	JLabel text = new JLabel("Details");
	JButton btnBack = new JButton("Back To Timetable");
	JButton btnDelete = new JButton("Delete Current Item");
	JTable table = new JTable();
	String columnNames[] = { "Subject", "Start Date", "Start Time", "End Date", "End Time" };

	DetailsPanel(String details) {
		this.setLayout(layout);

		add(text);
		add(btnBack);
		add(btnDelete);

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}
}
