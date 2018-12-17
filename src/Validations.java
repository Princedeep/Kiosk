
/*File: Validations.java
 * Authors: Princedeep Singh and Jay Patel
 * Date Last Modified: 17 March,2018
 * Description: This class contains all methods for validations like 
 * access levels, input formats etc.
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Validations {

	/**
	 * No argument default constructor
	 */

	public static int loginAccount;

	public Validations() {

	}

	/**
	 * Email Validation Refrences for help:
	 * https://stackoverflow.com/questions/8204680/java-regex-email
	 */

	public boolean isValidEmailAddress(String email) {
		// string for refrence to match entered string
		String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern pattern = Pattern.compile(emailPattern); // Pattern to compile passed value
		Matcher m = pattern.matcher(email); // Matching passed string with given emailPattern

		if (!m.matches()) {
			JOptionPane.showMessageDialog(null, "Please enter the valid email", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return m.matches();
	}

	/**
	 * 
	 * Password Validation Refrences for help :
	 * https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
	 */
	public boolean isValidPassword(JPasswordField f) {
		// string for refrence to match entered string
		String passwordFormat = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=])\\S{8,}\\z";
		Pattern pattern = java.util.regex.Pattern.compile(passwordFormat); // Pattern to compile passed value
		Matcher m = pattern.matcher(f.getText()); // Matching passed string with given passwordFormat
		if (!m.matches()) {
			JOptionPane.showMessageDialog(null,
					"Password must contains a digit, a lower case, An upper case letter , A special character ,At least 8 characters",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return m.matches();
	}

	/**
	 * 
	 * Validation for empty fields in student and professor window
	 */

	public boolean validatefields(JTextField a, JTextField b, JTextField c) {
		// Checking if any text field is empty or not
		if (a.getText().isEmpty() || b.getText().isEmpty() || c.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Key Listener for ID in order to take id in specified format
	 * 
	 * @param Id
	 *
	 */
	public void validateId(JTextField Id) {
		Id.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char cha = e.getKeyChar();
				if (!((cha >= '0') && (cha <= '9'))) { // checking id must be between numbers 0 to 9. No letters or
														// special chars

					e.consume(); // deleting everything in id field
				} else if ((cha == KeyEvent.VK_BACK_SPACE) || (cha == KeyEvent.VK_DELETE)) {
					e.consume();// deleting everything in id field
				} else if (Id.getText().length() >= 8) {// condition for taking only 8 digits in id field
					e.consume();// deleting everything in id field

				}

			}
		});

	}

	/**
	 * 
	 * Method which contains Eventhandler for login Button to check validation from
	 * database It takes button and text field as argument
	 * 
	 */
	public void validateBtn(JButton loginButton, JTextField Name, JPasswordField pass, JTextField Id) {
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// if (Id.getText().length() < 8) {
				// JOptionPane.showMessageDialog(null, "Please Enter 8 digit Id", "Error",
				// JOptionPane.ERROR_MESSAGE);
				// } else {

				Database db = new Database();
				// db.openConnection();
				try {
					String name = Name.getText();
					String pass1 = String.valueOf(pass.getPassword());
					String id = Id.getText();

					/*
					 * On Switch cases display the specific window for logged user
					 * 
					 */

					if (db.getresult(name, pass1, id)) {
						switch (loginAccount) {
						case 1:
							// if credentials match from admin table admin panel will be loaded
							JOptionPane.showMessageDialog(null, " Admin Login Successfull");
							AdminPanel a = new AdminPanel();
							a.AdminPanel();

							break;
						case 2:
							// if credentials match from student table student panel will be loaded
							JOptionPane.showMessageDialog(null, " Student Login Successfull");
							StudentPanel s = new StudentPanel();
							break;
						case 3:
							// if credentials match from professor table professor panel will be loaded
							JOptionPane.showMessageDialog(null, " Professor Login Successfull");
							ProfessorPanel p = new ProfessorPanel();
							break;
						}
					} else {
						JOptionPane.showMessageDialog(null, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});
	}

}