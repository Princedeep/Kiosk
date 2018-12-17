
/**
 * File: Add Student
 * Author: Princedeep Singh
 * Last Modified: 09-04-2018
 * Description: This is class which admin is using to perform C.R.U.D operations on student records
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class AddStudent extends JPanel {
	/**
	 *
	 * Creating required buttons and fields
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * Craeting textfields
	 */
	private JTextField textField;
	private JTextField textField2;
	private JPasswordField textField4;
	private JTextField textField3;
	private JTextField textField5;

	Database db = new Database();// Creating new instance of database class
	StudentDb db2 = new StudentDb(); // Creating new instance of student database class
	JButton btnAddStudent = new JButton("Add Student");
	Validations v = new Validations(); // Creating new instance of Validations class
	private JButton btnDeleteStu;
	private JButton btnUpdateStu;
	private JTable table; // Creating new JTable

	/**
	 * Create the panel.
	 */
	public AddStudent() {

		setLayout(new BorderLayout());
		// Creating new lable
		JLabel lblNewLabel = new JLabel("Enter  id");
		lblNewLabel.setBounds(10, 291, 150, 70);
		add(lblNewLabel);// Adding label to panel

		// Creating new lable
		JLabel lblEnterStudentEmail = new JLabel("Enter Student name");
		lblEnterStudentEmail.setBounds(10, 327, 150, 70);
		add(lblEnterStudentEmail);// Adding label to panel

		// Creating new lable
		JLabel lblEnter = new JLabel("Enter Email ");
		lblEnter.setBounds(10, 362, 150, 70);
		add(lblEnter);// Adding label to panel

		// Creating new lable
		JLabel lblEnterStudentName = new JLabel("Enter Password ");
		lblEnterStudentName.setBounds(10, 390, 150, 80);
		add(lblEnterStudentName);// Adding label to panel

		// Creating new textfield
		textField = new JTextField();
		textField.setBounds(141, 316, 100, 20);
		add(textField);// Adding textfield to panel
		textField.setColumns(10);

		// Creating new textfield
		textField2 = new JTextField();
		textField2.setBounds(141, 352, 100, 20);
		add(textField2);// Adding textfield to panel
		textField2.setColumns(10);

		// Creating new textfield
		textField4 = new JPasswordField();
		textField4.setBounds(141, 422, 100, 20);
		add(textField4);// Adding textfield to panel
		textField4.setColumns(10);

		// Creating new textfield
		textField3 = new JTextField();
		textField3.setBounds(141, 387, 100, 20);
		add(textField3);// Adding textfield to panel
		textField3.setColumns(10);

		v.validateId(textField);

		btnAddStudent.setBounds(313, 315, 150, 23);
		add(btnAddStudent);

		btnDeleteStu = new JButton("Delete Student");
		btnDeleteStu.setBounds(313, 386, 150, 23);
		add(btnDeleteStu);
		btnDeleteStu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Checking validations on all fields
				 */
				if (v.validatefields(textField, textField2, textField3) && v.isValidEmailAddress(textField3.getText())
						&& v.isValidPassword(textField4)) {
					db2.deleteStudent(textField);// Deleting record
					db2.FillTable(table);// Filling table with data
				}
			}
		});

		btnUpdateStu = new JButton("Update Student");
		btnUpdateStu.setBounds(313, 457, 150, 23);
		add(btnUpdateStu);

		// setting colour of text on button
		btnAddStudent.setForeground(new Color(60, 165, 38));
		btnDeleteStu.setForeground(new Color(60, 165, 38));
		btnUpdateStu.setForeground(new Color(60, 165, 38));

		// Adding background colour to buttons
		btnAddStudent.setBackground(Color.DARK_GRAY);
		btnDeleteStu.setBackground(Color.DARK_GRAY);
		btnUpdateStu.setBackground(Color.DARK_GRAY);

		btnAddStudent.setOpaque(true);
		btnDeleteStu.setOpaque(true);
		btnUpdateStu.setOpaque(true);
		table = new JTable(); // Creating new jtable
		table.setFont(new Font("Arial", Font.PLAIN, 14));

		table.setBounds(6, 8, 520, 240);
		table.setBackground(Color.WHITE);
		table.setForeground(SystemColor.desktop);
		db2.FillTable(table);

		// Creating new Scroll pane and adding table
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 705, 282);
		scrollPane.setVisible(true);
		add(scrollPane); // Adding scroll pane to panel
		btnUpdateStu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/**
				 * Checking validations on all fields
				 */
				if (v.validatefields(textField, textField2, textField3) && v.isValidEmailAddress(textField3.getText())
						&& v.isValidPassword(textField4)) {
					db2.updateStu(textField, textField2, textField3, textField4); // updating data into database
					db2.FillTable(table); // filling table with data
				}
			}
		});

		btnAddStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				/**
				 * Checking validations on all fields
				 */
				if (v.validatefields(textField, textField2, textField3) && v.isValidEmailAddress(textField3.getText())
						&& v.isValidPassword(textField4)) {

					db2.InsertData(textField, textField2, textField3, textField4); // inserting data into database

					db2.FillTable(table);// filling table with data
				}
			}
		});

		/**
		 * Adding background logo
		 */

		JLabel bg = null;

		try {
			bg = new JLabel(new ImageIcon(ImageIO.read(new File("logo.jpg")))); // Reading logo file
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		bg.setBounds(800, 700, 20, 40);
		bg.setPreferredSize(getMaximumSize());
		add(bg); // Adding logo to panel
	}
}