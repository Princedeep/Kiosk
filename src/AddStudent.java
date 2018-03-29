import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddStudent extends JPanel {
	private JTextField textField;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	Database db = new Database();
	JButton btnAddStudent = new JButton("Add Student");

	/**
	 * Create the panel.
	 */
	public AddStudent() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter Student Id");
		lblNewLabel.setBounds(10, 10, 150, 70);
		add(lblNewLabel);

		JLabel lblEnterStudentEmail = new JLabel("Enter student email");
		lblEnterStudentEmail.setBounds(10, 50, 150, 70);
		add(lblEnterStudentEmail);

		JLabel lblEnter = new JLabel("Enter Course id ");
		lblEnter.setBounds(10, 90, 150, 70);
		add(lblEnter);

		JLabel lblEnterStudentName = new JLabel("Enter Student Name");
		lblEnterStudentName.setBounds(10, 130, 150, 80);
		add(lblEnterStudentName);

		textField = new JTextField();
		textField.setBounds(110, 35, 100, 20);
		add(textField);
		textField.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(130, 75, 100, 20);
		add(textField2);
		textField2.setColumns(10);

		textField3 = new JTextField();
		textField3.setBounds(110, 115, 100, 20);
		add(textField3);
		textField3.setColumns(10);

		textField4 = new JTextField();
		textField4.setBounds(125, 155, 100, 20);
		add(textField4);
		textField3.setColumns(10);

		btnAddStudent.setBounds(227, 203, 100, 23);
		add(btnAddStudent);
		btnAddStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				db.InsertData(textField, textField2, textField3, textField4);

			}
		});

	}

}
