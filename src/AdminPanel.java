
/*File: Adminpanel
 * Authors: Princedeep Singh and Ayaz Khan
 * Date created: 9 April,2018
 * Description: This gui is for admin to perform various operatu=ions 
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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

public class AdminPanel {

	public AdminPanel() {

	}

	/**
	 * Creating layout and panels
	 */
	GridLayout layout = new GridLayout(1, 2); // Creating new grid layout
	JFrame frame = new JFrame("Admin Panel"); // Creating main frame

	JPanel adminPanel = new JPanel(new GridLayout(7, 1)); // Setting layout of main Panel
	JPanel contentPanel = new JPanel(); // Creating content panel

	/**
	 * Craeting Required buttons
	 */

	JButton addProfessor = new JButton("Add Professor");

	JButton addStu = new JButton("Add Student");

	JButton roomFind = new JButton("Room Finder");
	JButton delStu = new JButton("Delete student Account");
	JButton delProf = new JButton("Delete Professor Account");
	JButton logout = new JButton("Logout");

	/**
	 * Calling student and professor panel
	 */
	AddStudent add = new AddStudent(); // Creating new instance of add student class
	View myView = new View();
	AddProfessor addProf = new AddProfessor(); // Creating new instance of add professor class

	public void AdminPanel() {
		frame.getContentPane().setLayout(layout); // Setting layout of frame
		adminPanel.setBackground(Color.WHITE);
		/**
		 * Adding Buttons to admin panel
		 */

		addStu.setForeground(new Color(60, 165, 38));
		addStu.setBackground(Color.DARK_GRAY);
		addStu.setOpaque(true);

		/**
		 * Adding color to text of buttons
		 */
		addProfessor.setForeground(new Color(60, 165, 38));
		roomFind.setForeground(new Color(60, 165, 38));
		logout.setForeground(new Color(60, 165, 38));

		/**
		 * Adding background color to buttons
		 */

		roomFind.setBackground(Color.DARK_GRAY);
		addProfessor.setBackground(Color.DARK_GRAY);
		logout.setBackground(Color.DARK_GRAY);

		addProfessor.setOpaque(true);
		roomFind.setOpaque(true);
		logout.setOpaque(true);

		/**
		 * Adding all buttons to panel
		 */
		adminPanel.add(addStu);
		adminPanel.add(addProfessor);
		adminPanel.add(roomFind);
		adminPanel.add(logout);

		/**
		 * Adding Panel to Frame
		 */
		contentPanel.setLayout(new BorderLayout()); // Setting boeder layout to content panel
		frame.getContentPane().add(adminPanel);
		JLabel bg = null;

		// Adding background logo
		try {
			bg = new JLabel(new ImageIcon(ImageIO.read(new File("logo.jpg"))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		bg.setBounds(800, 700, 20, 40);

		contentPanel.add(bg);

		frame.getContentPane().add(contentPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); // Making frame visible
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Setting frame size maximum

		delProf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll(); // Removing anything in content pane
				frame.getContentPane().add(adminPanel); // loading main admin panel
				frame.getContentPane().add(addProf); // Loading professor panel
				frame.repaint(); // reloading everything again
				frame.revalidate();
			}
		});

		/**
		 * Adding Action listener on every button
		 */
		addProfessor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll(); // Removing anything in content pane
				frame.getContentPane().add(adminPanel); // loading main admin panel
				frame.getContentPane().add(addProf); // Loading professor panel
				frame.repaint(); // reloading everything again
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
				frame.getContentPane().removeAll(); // Removing anything in content pane
				frame.getContentPane().add(adminPanel); // loading main admin panel
				frame.getContentPane().add(add); // Loading student panel
				frame.repaint(); // reloading everything again
				frame.revalidate();
			}
		});

		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false); // Making frame not visible

			}

		});

		/**
		 * Loading room finder panel
		 */
		roomFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				frame.getContentPane().removeAll(); // Removing anything in content pane

				contentPanel.setLayout(new BorderLayout());
				frame.getContentPane().add(adminPanel);
				JLabel bg = null;

				// Adding background logo
				try {
					bg = new JLabel(new ImageIcon(ImageIO.read(new File("logo.jpg"))));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				bg.setBounds(800, 700, 20, 40);

				contentPanel.add(bg);
				frame.getContentPane().add(contentPanel);
				frame.getContentPane().add(adminPanel);
				JFrame frame1 = new JFrame("Room Finder"); // Creating new jframe
				JButton back = new JButton("Exit"); // Creating new J button
				back.setForeground(new Color(60, 165, 38));
				back.setBackground(Color.DARK_GRAY);
				JFXPanel fx = new JFXPanel();
				fx.setLayout(null); // setting layout of frame to null
				frame1.getContentPane().add(fx);
				back.setBounds(1400, 100, 80, 20);
				fx.add(back); // adding button to panel

				// Action handler for back button in webview
				back.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame1.setVisible(false);
					}

				});

				// Creation of scene and future interactions with JFXPanel

				Platform.runLater(() -> {

					WebView webView = new WebView(); // creating new webview

					fx.setScene(new Scene(webView)); // loading webview into scene and loading scene into panel

					webView.getEngine().load("http://lyceum.algonquincollege.com/roomfinder/"); // connecting to rom
					// finder api

					frame1.setUndecorated(true);
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame1.setVisible(true); // Making main frame visible
					frame1.setExtendedState(JFrame.MAXIMIZED_BOTH);

				});

				frame.repaint(); // reloading everything again
				// frame.revalidate();
			}
		});

	}
}