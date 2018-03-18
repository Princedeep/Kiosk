
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

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Validations {

	/**
	 * No argument default constructor
	 */
	public Validations() {

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
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {

					e.consume();
				} else if (Id.getText().length() >= 8) {
					e.consume();

				}
			}
		});

	}

	/**
	 * Method which contains Eventhandler for login Button to check validation from
	 * database It takes button and text field as argument
	 */
	public void validateBtn(JButton loginButton, JTextField Id) {
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database db = new Database();
				// db.openConnection();
				try {

					String id = Id.getText();

					if (db.getresult(id).next()) {
						JOptionPane.showMessageDialog(null, "Login Successfull");
					}

					else {
						JOptionPane.showMessageDialog(null, "Invalid credentials");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
