
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

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class AdminPanel {

	public AdminPanel() {

	}

	/**
	 * Creating layout and panels
	 */
	GridLayout layout = new GridLayout(1, 2);
	JFrame frame = new JFrame("Admin Panel");
	JPanel adminPanel = new JPanel(new GridLayout(3, 1));
	JPanel contentPanel = new JPanel();

	/**
	 * Craeting Required buttons
	 */

	JButton addProfessor = new JButton("Add Professor");

	JButton addStu = new JButton("Add Student");

	JButton roomFind = new JButton("Room Finder");
	JButton delStu = new JButton("Delete student Account");
	JButton delProf = new JButton("Delete Professor Account");

	/**
	 * Calling student and professor panel
	 */
	AddStudent add = new AddStudent();
	AddProfessor addProf = new AddProfessor();

	public void AdminPanel() {
		frame.getContentPane().setLayout(layout); // Setting layout of frame

		/**
		 * Adding Buttons to admin panel
		 */
		adminPanel.add(addStu);
		adminPanel.add(addProfessor);
		adminPanel.add(roomFind);

		/**
		 * Adding Panel to Frame
		 */
		frame.getContentPane().add(adminPanel);
		frame.getContentPane().add(contentPanel);
		// frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); // Making frame visible
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Setting frame size maximum

		delProf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(adminPanel);
				frame.getContentPane().add(addProf);
				frame.repaint();
				frame.revalidate();
			}
		});

		/**
		 * Adding Action listener on every button
		 */
		addProfessor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(adminPanel);
				frame.getContentPane().add(addProf);
				frame.repaint();
				frame.revalidate();
			}
		});

		addProfessor.setBounds(100, 300, 110, 34);
		/**
		 * Loading student Panel
		 */
		addStu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(adminPanel);
				frame.getContentPane().add(add);
				frame.repaint();
				frame.revalidate();
			}
		});
		/**
		 * Loading room finder panel
		 */
		roomFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				frame.getContentPane().removeAll();
				frame.getContentPane().add(adminPanel);
				JFrame frame1 = new JFrame("Room Finder");
				JButton back = new JButton("<html><a href='#' class='button'>Button</a></html>");
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

				frame.repaint();
				// frame.revalidate();
			}
		});

	}
}