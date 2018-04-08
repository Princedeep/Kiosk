
/* File: View.java
 * Authors:  Princedeep
 * Date Last Modified: March 17, 2018
 * Description - This class creates main gui which has elements like
 * labels, text field and button. This is login screen.
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class View {
	private static JPanel panel = new JPanel();
	static Statement st;
	public static JFrame frame = new JFrame("Algonquin College Kiosk");// Creating
																		// new
																		// frame
	static JTextField Id = new JTextField(8);
	private JPasswordField textField;
	private JTextField textField_1;

	/**
	 * @throws IOException
	 * @wbp.parser.entryPoint
	 */
	public void getView() throws IOException {
		Validations v = new Validations();
		frame.setSize(1200, 800);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Code to exit

		frame.getContentPane().setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());
		frame.getContentPane().add(panel,BorderLayout.NORTH);// Adding Panel to frame

		/**
		 * Creating labels for user and password
		 */
		JLabel userLabel = new JLabel("Enter your Id");
		userLabel.setBounds(649, 326, 150, 25);
		panel.add(userLabel,BorderLayout.CENTER);// Adding user label to panel

		/**
		 * Creating text field for Id setting validations. Id field will only take
		 * numbers and limited to 8 in length
		 */
		Id.setBounds(806, 326, 165, 25);
		panel.add(Id);
		v.validateId(Id);

		/**
		 * Creating Button to log in into system
		 */
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(749, 399, 80, 25);
		
		loginButton.setHorizontalAlignment(JLabel.CENTER);
		loginButton.setVerticalAlignment(JLabel.CENTER);

		panel.add(loginButton);

		JLabel label = new JLabel("Enter your Password");
		label.setBounds(649, 290, 150, 25);
		panel.add(label);

		textField = new JPasswordField(8);
		textField.setBounds(806, 290, 165, 25);
		panel.add(textField);

		textField_1 = new JTextField(8);
		textField_1.setBounds(806, 249, 165, 25);

		panel.add(textField_1);

		JLabel label_1 = new JLabel("Enter your Name");
		label_1.setBounds(649, 254, 150, 10);
		panel.add(label_1);

		//Background Image 
		JLabel bg = new JLabel(new ImageIcon(
				new URL("http://www.ellisdon.com/wp-content/uploads/2016/03/algonquinedit_header@2x.jpg")));
		bg.setVisible(true);
		panel.add(bg);

		v.validateBtn(loginButton, textField_1, textField, Id);
		
		/**
		 * Making everything in frame visible to user
		 */
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.invalidate();
		frame.validate();

	}
}