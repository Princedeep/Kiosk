
/*File: Adminpanel
 * Authors: Princedeep Singh and Ayaz Khan
 * Date created: 19 march,2018
 * Description: This gui is for admin to perform various operatu=ions 
 * 
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdminPanel {

	public AdminPanel() {

	}

	GridLayout layout = new GridLayout(1, 2);
	JFrame frame = new JFrame("Admin Panel");
	JPanel adminPanel = new JPanel(new GridLayout(3, 1));
	JPanel contentPanel = new JPanel();
	JButton addProf = new JButton("Timetable");
	JButton addStu = new JButton("Add Student");
	JButton btnCancelClass = new JButton("Add Professor");
	TimetablePanel Timetable = new TimetablePanel();
	AddStudent add = new AddStudent();
	CancelClassPanel CancelClass = new CancelClassPanel();

	public void AdminPanel() {
		frame.setLayout(layout);

		adminPanel.add(addStu);
		adminPanel.add(addProf);
		adminPanel.add(btnCancelClass);

		frame.add(adminPanel);
		frame.add(contentPanel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		addProf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.add(adminPanel);
				frame.add(Timetable);
				frame.revalidate();
			}
		});
		addStu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.add(adminPanel);
				frame.add(add);
				frame.revalidate();
			}
		});

	}
}