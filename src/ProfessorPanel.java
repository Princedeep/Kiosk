//Adam Tremblett

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

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class ProfessorPanel{
	GridLayout layout = new GridLayout(1, 2);
	JFrame window = new JFrame("Multiple Panels");
	JPanel menuPanel = new JPanel(new GridLayout(7,1));
	JPanel contentPanel = new JPanel();
	JButton btnTimeTable = new JButton("Timetable");
	JButton roomFind = new JButton("Room finder");
	JButton logout = new JButton("Logout");
	JButton btnNotification = new JButton("Notification");
	TimetableProf Timetable = new TimetableProf();
	SchedulePanel ScheduleAppointment = new SchedulePanel();
	NotificationProf NotificationProf = new NotificationProf();
		
	ProfessorPanel(){
		window.setLayout(layout);
		btnTimeTable.setForeground(new Color(60,165,38));
		btnNotification.setForeground(new Color(60, 165, 38));
		btnTimeTable.setBackground(Color.DARK_GRAY);
		btnNotification.setBackground(Color.DARK_GRAY);
		
		roomFind.setForeground(new Color(60, 165, 38));
		roomFind.setBackground(Color.DARK_GRAY);
		roomFind.setOpaque(true);
		
		logout.setForeground(new Color(60, 165, 38));
		logout.setBackground(Color.DARK_GRAY);
	    logout.setOpaque(true);
	    
		menuPanel.add(btnTimeTable);
		menuPanel.add(btnNotification);
		menuPanel.add(roomFind);
		menuPanel.setBackground(Color.WHITE);
		menuPanel.add(logout);
		
		//window.add(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		window.getContentPane().add(menuPanel);
		JLabel bg = null;

		try {
			bg = new JLabel(new ImageIcon(ImageIO.read(new File("logo.jpg"))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		bg.setBounds(800, 700, 20, 40);

		contentPanel.add(bg);
		window.add(contentPanel);
		
		window.getContentPane().add(contentPanel);
		
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
				window.add(menuPanel);
				window.add(NotificationProf);
				window.revalidate();
			}
		});
		
		roomFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				window.getContentPane().removeAll();
				contentPanel.setLayout(new BorderLayout());
				window.getContentPane().add(menuPanel);
				JLabel bg = null;

				try {
					bg = new JLabel(new ImageIcon(ImageIO.read(new File("logo.jpg"))));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				bg.setBounds(800, 700, 20, 40);

				contentPanel.add(bg);
				window.getContentPane().add(contentPanel);
				window.getContentPane().add(menuPanel);
				JFrame frame1 = new JFrame("Room Finder");
				JButton back = new JButton("Exit");
			    back.setForeground(new Color(60, 165, 38));
				back.setBackground(Color.DARK_GRAY);
				JFXPanel fx = new JFXPanel();
				fx.setLayout(null);
				back.setVisible(true);
				frame1.getContentPane().add(fx);
				back.setBounds(1400, 100, 80, 20);
				fx.add(back);

				back.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame1.setVisible(false);
					}
				});
				

				// Creation of scene and future interactions with JFXPanel

				Platform.runLater(() -> {

					WebView webView = new WebView();

					fx.setScene(new Scene(webView));

					webView.getEngine().load("http://lyceum.algonquincollege.com/roomfinder/");

					// frame1.setUndecorated(true);
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setVisible(true);
					frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);

				});

				window.repaint();
				// frame.revalidate();
			}
		});

	}	
}
