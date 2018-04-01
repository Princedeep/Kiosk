
/* File: View.java
 * Authors: Adam , Ivan, Princedeep
 * Date Last Modified: March 17, 2018
 * Description - This class creates main gui which has elements like
 * labels, text field and button. This is login screen.
 * 
 */

import java.sql.Statement;

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
	 * @wbp.parser.entryPoint
	 */
	public void getView() {
		Validations v = new Validations();
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Code to exit
																// main frame

		frame.getContentPane().add(panel);// Adding Panel to frame
		panel.setLayout(null);

		/**
		 * Creating labels for user and password
		 */
		JLabel userLabel = new JLabel("Enter your Id");
		userLabel.setBounds(649, 326, 150, 25);
		panel.add(userLabel);// Adding user label to panel

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
		label_1.setBounds(649, 254, 150, 25);
		panel.add(label_1);

		v.validateBtn(loginButton, textField_1, textField, Id);
		/**
		 * Making everything in frame visible to user
		 */
		// frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

		frame.invalidate();
		frame.validate();

	}
}