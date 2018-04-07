import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class SplashPanel {

	GridLayout layout = new GridLayout(1, 2);
	JFrame frame = new JFrame("Student Panel");
	JPanel menuPanel = new JPanel(new GridLayout(3, 1));
	JPanel contentPanel = new JPanel();
	JButton btnTimeTable = new JButton("Timetable");
	JButton btnScheduleAppointment = new JButton("Schedule Appointment");
	JButton btnRoomFinder = new JButton("Cancel Class");
	TimetablePanel Timetable = new TimetablePanel();
	SchedulePanel ScheduleAppointment = new SchedulePanel();

	public void SplashPanel() {
		frame.setLayout(layout);

		menuPanel.add(btnScheduleAppointment);
		menuPanel.add(btnTimeTable);
		menuPanel.add(btnRoomFinder);

		frame.add(menuPanel);
		frame.add(contentPanel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		btnTimeTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.add(menuPanel);
				frame.add(Timetable);
				frame.repaint();
				frame.revalidate();
			}
		});
		btnScheduleAppointment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.add(menuPanel);
				frame.add(ScheduleAppointment);
				frame.repaint();
				frame.revalidate();
			}
		});
		btnRoomFinder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(menuPanel);
				JFrame frame1 = new JFrame("Room Finder");
				JButton back = new JButton("Exit");
				JFXPanel fx = new JFXPanel();
				fx.setLayout(null);
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
			}
		});
	}
}
