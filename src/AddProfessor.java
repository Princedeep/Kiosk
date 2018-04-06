
/**
 * File: Add Professor
 * Author: Princedeep Singh
 * Last Modified:
 * Description: This is class which admin is using to perform C.R.U.D operations on professor records
 * 
 */

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	Database db = new Database();
	ProfessorDb db2 = new ProfessorDb();
	JButton btnAddProfessor = new JButton("Add Professor");
	Validations v = new Validations();
	private JButton btnDeleteProf;
	private JButton btnUpdateProf;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public AddProfessor() {

		setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter id");
		lblNewLabel.setBounds(10, 291, 150, 70);
		add(lblNewLabel);

		JLabel lblEnterProfessorEmail = new JLabel("Enter Professor name");
		lblEnterProfessorEmail.setBounds(10, 327, 150, 70);
		add(lblEnterProfessorEmail);

		JLabel lblEnter = new JLabel("Enter Email ");
		lblEnter.setBounds(10, 362, 150, 70);
		add(lblEnter);

		JLabel lblEnterProfessorName = new JLabel("Enter Password ");
		lblEnterProfessorName.setBounds(10, 390, 150, 80);
		add(lblEnterProfessorName);

		textField = new JTextField();
		textField.setBounds(141, 316, 100, 20);
		add(textField);
		textField.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(141, 352, 100, 20);
		add(textField2);
		textField2.setColumns(10);

		textField4 = new JPasswordField();
		textField4.setBounds(141, 422, 100, 20);
		add(textField4);
		textField4.setColumns(10);

		textField3 = new JTextField();
		textField3.setBounds(141, 387, 100, 20);
		add(textField3);
		textField3.setColumns(10);

		v.validateId(textField);

		btnAddProfessor.setBounds(313, 315, 150, 23);
		add(btnAddProfessor);

		btnDeleteProf = new JButton("Delete Professor");
		btnDeleteProf.setBounds(313, 386, 150, 23);
		add(btnDeleteProf);
		btnDeleteProf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (v.validatefields(textField, textField2, textField3) && v.isValidEmailAddress(textField3.getText())
						&& v.isValidPassword(textField4)) {
					db2.deleteProfessor(textField);
					db2.FillTable(table);
				}
				// TODO Auto-generated method stub

			}
		});

		btnUpdateProf = new JButton("Update Professor");
		btnUpdateProf.setBounds(313, 457, 150, 23);
		add(btnUpdateProf);

		table = new JTable();

		table.setBounds(6, 8, 520, 240);
		table.setBackground(Color.WHITE);
		table.setForeground(SystemColor.desktop);
		db2.FillTable(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 705, 282);
		scrollPane.setVisible(true);
		add(scrollPane);

		btnUpdateProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (v.validatefields(textField, textField2, textField3) && v.isValidEmailAddress(textField3.getText())
						&& v.isValidPassword(textField4)) {
					db2.updateProf(textField, textField2, textField3, textField4);
					db2.FillTable(table);
				}
			}
		});

		btnAddProfessor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (v.validatefields(textField, textField2, textField3) && v.isValidEmailAddress(textField3.getText())
						&& v.isValidPassword(textField4)) {

					db2.InsertData(textField, textField2, textField3, textField4);
					db2.FillTable(table);
				}
			}
		});

	}
}
