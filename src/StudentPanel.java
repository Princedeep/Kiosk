//Ayaz

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import net.proteanit.sql.DbUtils;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StudentPanel{
	GridLayout layout = new GridLayout(1, 2);
	JFrame window = new JFrame("Multiple Panels");
	JPanel menuPanel = new JPanel(new GridLayout(7,1));
	JPanel contentPanel = new JPanel();
	JButton logout = new JButton("Logout");
	JButton btnTimeTable = new JButton("Book Appointment");
	JButton btnStudentTimeTable = new JButton("See your timetable");
	JButton btnCancelAppointment = new JButton("View and cancel an appointment");
	Color darkGreen = new Color(60,165,38);
	
	JButton btnNotification = new JButton("Notification");
	TimetableStudent Timetable = new TimetableStudent();
	SchedulePanel ScheduleAppointment = new SchedulePanel();
	NotificationStudent NotificationStudent = new NotificationStudent();
	AppointmentInterface appointInt = new AppointmentInterface();
	CancelAppointment cancelAppoint = new CancelAppointment();
	
		
	StudentPanel(){
		window.setLayout(layout);
		btnTimeTable.setForeground(new Color(60, 165, 38));
		btnNotification.setForeground(darkGreen);
		btnTimeTable.setBackground(Color.DARK_GRAY);
		btnNotification.setBackground(Color.DARK_GRAY);
		btnStudentTimeTable.setBackground(Color.DARK_GRAY);
		btnStudentTimeTable.setForeground(new Color(60,165,38));
		btnNotification.setOpaque(true);
		btnTimeTable.setOpaque(true);
		btnStudentTimeTable.setOpaque(true);
		btnCancelAppointment.setForeground(new Color(60,165,38));
		btnCancelAppointment.setBackground(Color.DARK_GRAY);
		btnCancelAppointment.setOpaque(true);
		logout.setForeground(new Color(60, 165, 38));
		logout.setBackground(Color.DARK_GRAY);
	    logout.setOpaque(true);
		menuPanel.add(btnTimeTable);
		menuPanel.add(btnStudentTimeTable);
		menuPanel.add(btnNotification);
		menuPanel.add(btnCancelAppointment);
		menuPanel.add(logout);
		menuPanel.setBackground(Color.WHITE);
		
		window.add(menuPanel);
		//window.add(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		JLabel bg = null;
		
		try {
			bg = new JLabel(new ImageIcon(ImageIO.read(new File("logo.jpg"))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		bg.setBounds(800, 700, 20, 40);
		
		contentPanel.add(bg);
		contentPanel.setBackground(Color.WHITE);
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
				window.add(appointInt);
				window.revalidate();
			}
		}); 
		
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);
			}
		});
		
		btnNotification.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				window.getContentPane().removeAll();
				window.repaint();
				contentPanel.setBackground(Color.WHITE);
				window.add(menuPanel);
				window.add(NotificationStudent);
				window.revalidate();
			}
		});
		btnCancelAppointment.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
			
				window.getContentPane().removeAll();
				window.repaint();
				contentPanel.setBackground(Color.WHITE);
				window.add(menuPanel);
				window.add(cancelAppoint);
				window.revalidate();
			}
		});
		
		btnStudentTimeTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.getContentPane().removeAll();
				window.repaint();
				contentPanel.setBackground(Color.WHITE);
				window.add(Timetable);
				window.revalidate();
			}
		});
	}	
}
