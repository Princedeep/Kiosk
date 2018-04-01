import java.awt.Color;
import java.awt.Font;
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

public class AddStudent extends JFXPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField2;
	private JPasswordField textField3;
	private JTextField textField4;
	private JTextField textField5;
	Database db = new Database();
	StudentDb db2 = new StudentDb();
	JButton btnAddStudent = new JButton("Add Student");
	Validations v = new Validations();
	private JButton btnDeleteStu;
	private JButton btnUpdateStu;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public AddStudent() {

		setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter Course id");
		lblNewLabel.setBounds(10, 291, 150, 70);
		add(lblNewLabel);

		JLabel lblEnterStudentEmail = new JLabel("Enter student name");
		lblEnterStudentEmail.setBounds(10, 327, 150, 70);
		add(lblEnterStudentEmail);

		JLabel lblEnter = new JLabel("Enter Password ");
		lblEnter.setBounds(10, 362, 150, 70);
		add(lblEnter);

		JLabel lblEnterStudentName = new JLabel("Enter id ");
		lblEnterStudentName.setBounds(10, 390, 150, 80);
		add(lblEnterStudentName);

		JLabel ld = new JLabel("Enter email ");
		ld.setBounds(10, 428, 150, 80);
		add(ld);

		textField = new JTextField();
		textField.setBounds(141, 316, 100, 20);
		add(textField);
		textField.setColumns(10);

		textField2 = new JTextField();
		textField2.setBounds(141, 352, 100, 20);
		add(textField2);
		textField2.setColumns(10);

		textField3 = new JPasswordField();
		textField3.setBounds(141, 387, 100, 20);
		add(textField3);
		textField3.setColumns(10);

		textField4 = new JTextField();
		textField4.setBounds(141, 422, 100, 20);
		add(textField4);
		textField3.setColumns(10);

		textField5 = new JTextField();
		textField5.setBounds(141, 458, 100, 20);
		add(textField5);
		textField3.setColumns(10);

		v.validateId(textField4);

		btnAddStudent.setBounds(313, 315, 150, 23);
		add(btnAddStudent);

		btnDeleteStu = new JButton("Delete Student");
		btnDeleteStu.setBounds(313, 386, 150, 23);
		add(btnDeleteStu);
		btnDeleteStu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (v.validatefields(textField, textField2, textField3, textField4, textField5)
						&& v.isValidEmailAddress(textField5.getText()) && v.isValidPassword(textField3)) {
					db2.deleteStudent(textField4);
					db2.FillTable(table);
				}
			}
		});

		btnUpdateStu = new JButton("Update Student");
		btnUpdateStu.setBounds(313, 457, 150, 23);
		add(btnUpdateStu);

		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setBounds(6, 8, 520, 240);
		table.setBackground(Color.WHITE);
		table.setForeground(SystemColor.desktop);
		db2.FillTable(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 705, 282);
		scrollPane.setVisible(true);
		add(scrollPane);
		btnUpdateStu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (v.validatefields(textField, textField2, textField3, textField4, textField5)
						&& v.isValidEmailAddress(textField5.getText()) && v.isValidPassword(textField3)) {
					db2.updateStu(textField, textField2, textField3, textField4, textField5);
					db2.FillTable(table);
				}
			}
		});

		btnAddStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (v.validatefields(textField, textField2, textField3, textField4, textField5)
						&& v.isValidEmailAddress(textField5.getText()) && v.isValidPassword(textField3)) {

					db2.InsertData(textField, textField2, textField3, textField4, textField5);
					db2.FillTable(table);
				}
			}
		});

	}
}
