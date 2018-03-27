import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SplashPanel{
	GridLayout layout = new GridLayout(1, 2);
	JFrame window = new JFrame("Multiple Panels");
	JPanel menuPanel = new JPanel(new GridLayout(3,1));
	JPanel contentPanel = new JPanel();
	JButton btnTimeTable = new JButton("Timetable");
	JButton btnScheduleAppointment = new JButton("Schedule Appointment");
	JButton btnCancelClass = new JButton("Cancel Class");
	TimetablePanel Timetable = new TimetablePanel();
	SchedulePanel ScheduleAppointment = new SchedulePanel();
	CancelClassPanel CancelClass = new CancelClassPanel();
		
	SplashPanel(){
		window.setLayout(layout);
		
		menuPanel.add(btnScheduleAppointment);
		menuPanel.add(btnTimeTable);
		menuPanel.add(btnCancelClass);
		
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
		btnScheduleAppointment.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				window.getContentPane().removeAll();
				window.repaint();
				window.add(menuPanel);
				window.add(ScheduleAppointment);
				window.revalidate();
			}
		}); 
		btnCancelClass.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				window.getContentPane().removeAll();
				window.repaint();
				window.add(menuPanel);
				window.add(CancelClass);
				window.revalidate();
			}
		});
	}	
}

