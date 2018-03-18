
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View {
	private static JPanel panel = new JPanel();
	static Statement st;
	public static JFrame frame = new JFrame("Algonquin College Kiosk");// Creating
																		// new
																		// frame
	static JTextField Id = new JTextField(8);

	public void getView() {
		Validations v = new Validations();
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Code to exit
																// main frame

		frame.add(panel);// Adding Panel to frame
		panel.setLayout(null);

		/**
		 * Creating labels for user and password
		 */
		JLabel userLabel = new JLabel("Please Enter your ID");
		userLabel.setBounds(120, 40, 150, 25);
		panel.add(userLabel);// Adding user label to panel

		/**
		 * Creating text field for Id setting validations. Id field will only
		 * take numbers and limited to 8 in length
		 */
		Id.setBounds(100, 70, 165, 25);
		panel.add(Id);
		v.validateId(Id);

		/**
		 * Creating Button to log in into system
		 */
		JButton loginButton = new JButton("login");
		loginButton.setBounds(130, 100, 80, 25);
		loginButton.setHorizontalAlignment(JLabel.CENTER);
		loginButton.setVerticalAlignment(JLabel.CENTER);

		panel.add(loginButton);
		v.validateBtn(loginButton, Id);

		/**
		 * Making everything in frame visible to user
		 */

		frame.setVisible(true);
		frame.invalidate();
		frame.validate();

	}

}