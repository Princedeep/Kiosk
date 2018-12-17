
/**
 * File: Add Professor
 * Author: Princedeep Singh
 * Last Modified: 09-04-2018
 * Description: This is class which admin is using to perform C.R.U.D operations on professor records
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javafx.embed.swing.JFXPanel;

public class AddProfessor extends JFXPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField2;
	private JPasswordField textField4;
	private JTextField textField3;
	private JTextField textField5;
	Database db = new Database();// Creating new instance of database class
	ProfessorDb db2 = new ProfessorDb();// Creating new instance of Professor database class
	JButton btnAddProfessor = new JButton("Add Professor");
	Validations v = new Validations();// Creating new instance of Validations class
	private JButton btnDeleteProf;
	private JButton btnUpdateProf;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public AddProfessor() {

		setLayout(new BorderLayout()); // Setting layout to null

		// Creating new lable
		JLabel lblNewLabel = new JLabel("Enter id");
		lblNewLabel.setBounds(10, 291, 150, 70);
		add(lblNewLabel); // Adding label to panel

		// Creating new lable
		JLabel lblEnterProfessorEmail = new JLabel("Enter Professor name");
		lblEnterProfessorEmail.setBounds(10, 327, 150, 70);
		add(lblEnterProfessorEmail);// Adding label to panel

		// Creating new lable
		JLabel lblEnter = new JLabel("Enter Email ");
		lblEnter.setBounds(10, 362, 150, 70);
		add(lblEnter);// Adding label to panel

		// Creating new lable
		JLabel lblEnterProfessorName = new JLabel("Enter Password ");
		lblEnterProfessorName.setBounds(10, 390, 150, 80);
		add(lblEnterProfessorName);// Adding label to panel

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

		v.validateId(textField); // Checking validation for id

		btnAddProfessor.setBounds(313, 315, 150, 23);
		add(btnAddProfessor); // Adding Button to panel

		btnDeleteProf = new JButton("Delete Professor");
		btnDeleteProf.setBounds(313, 386, 150, 23);
		add(btnDeleteProf);// Adding Button to panel
		// Event handler for delete button
		btnDeleteProf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (v.validatefields(textField, textField2, textField3) && v.isValidEmailAddress(textField3.getText())
						&& v.isValidPassword(textField4)) {
					db2.deleteProfessor(textField);
					db2.FillTable(table);
				}

			}
		});

		btnUpdateProf = new JButton("Update Professor");
		btnUpdateProf.setBounds(313, 457, 150, 23);
		add(btnUpdateProf);

		table = new JTable(); // Creating new jtable

		table.setBounds(6, 8, 520, 240);
		table.setBackground(Color.WHITE);
		table.setForeground(SystemColor.desktop);
		db2.FillTable(table);

		// setting colour of text on button
		btnAddProfessor.setForeground(new Color(60, 165, 38));
		btnDeleteProf.setForeground(new Color(60, 165, 38));
		btnUpdateProf.setForeground(new Color(60, 165, 38));

		// Adding background colour to buttons
		btnAddProfessor.setBackground(Color.DARK_GRAY);
		btnDeleteProf.setBackground(Color.DARK_GRAY);
		btnUpdateProf.setBackground(Color.DARK_GRAY);

		btnAddProfessor.setOpaque(true);
		btnDeleteProf.setOpaque(true);
		btnUpdateProf.setOpaque(true);

		// Creating new Scroll pane and adding table
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 705, 282);
		scrollPane.setVisible(true);
		add(scrollPane); // Adding scroll pane to panel

		btnUpdateProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (v.validatefields(textField, textField2, textField3) && v.isValidEmailAddress(textField3.getText())
						&& v.isValidPassword(textField4)) {
					db2.updateProf(textField, textField2, textField3, textField4); // updating data into database
					db2.FillTable(table);// filling table with data
				}
			}
		});

		btnAddProfessor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * Checking validations on all fields
				 */
				if (v.validatefields(textField, textField2, textField3) && v.isValidEmailAddress(textField3.getText())
						&& v.isValidPassword(textField4)) {

					db2.InsertData(textField, textField2, textField3, textField4); // inserting data into database
					db2.FillTable(table); // filling table with data
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

			e1.printStackTrace();
		}

		bg.setBounds(800, 700, 20, 40);
		bg.setPreferredSize(getMaximumSize());
		add(bg); // Adding logo to panel
	}
}
