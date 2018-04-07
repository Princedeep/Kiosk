import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StudentPanel{
	GridLayout layout = new GridLayout(1, 2);
	JFrame window = new JFrame("Multiple Panels");
	JPanel menuPanel = new JPanel(new GridLayout(3,1));
	JPanel contentPanel = new JPanel();
	JButton btnTimeTable = new JButton("Timetable");
	
	JButton btnNotification = new JButton("Notification");
	TimetableStudent Timetable = new TimetableStudent();
	SchedulePanel ScheduleAppointment = new SchedulePanel();
	NotificationProf NotificationProf = new NotificationProf();
		
	StudentPanel(){
		window.setLayout(layout);
		
		menuPanel.add(btnTimeTable);
		menuPanel.add(btnNotification);
		
		window.add(menuPanel);
		window.add(contentPanel);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		btnTimeTable.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				window.getContentPane().removeAll();
				window.repaint();
				window.add(menuPanel);
				window.add(Timetable);
				window.revalidate();
			}
		}); 
		
		btnNotification.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				window.getContentPane().removeAll();
				window.repaint();
				window.add(menuPanel);
				window.add(NotificationProf);
				window.revalidate();
			}
		});
	}	
}
