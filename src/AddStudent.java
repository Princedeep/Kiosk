import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddStudent extends JPanel {
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

		setLayout(new BorderLayout());
		
		JLabel lblNewLabel = new JLabel("Enter  id");
		lblNewLabel.setBounds(10, 291, 150, 70);
		add(lblNewLabel);

		JLabel lblEnterStudentEmail = new JLabel("Enter Student name");
		lblEnterStudentEmail.setBounds(10, 327, 150, 70);
		add(lblEnterStudentEmail);

		JLabel lblEnter = new JLabel("Enter Email ");
		lblEnter.setBounds(10, 362, 150, 70);
		add(lblEnter);

		JLabel lblEnterStudentName = new JLabel("Enter Password ");
		lblEnterStudentName.setBounds(10, 390, 150, 80);
		add(lblEnterStudentName);

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

		btnAddStudent.setBounds(313, 315, 150, 23);
		add(btnAddStudent);

		btnDeleteStu = new JButton("Delete Student");
		btnDeleteStu.setBounds(313, 386, 150, 23);
		add(btnDeleteStu);
		btnDeleteStu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (v.validatefields(textField, textField2, textField3) && v.isValidEmailAddress(textField3.getText())
						&& v.isValidPassword(textField4)) {
					db2.deleteStudent(textField);
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
				if (v.validatefields(textField, textField2, textField3) && v.isValidEmailAddress(textField3.getText())
						&& v.isValidPassword(textField4)) {
					db2.updateStu(textField, textField2, textField3, textField4);
					db2.FillTable(table);
				}
			}
		});

		btnAddStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (v.validatefields(textField, textField2, textField3) && v.isValidEmailAddress(textField3.getText())
						&& v.isValidPassword(textField4)) {

					db2.InsertData(textField, textField2, textField3, textField4);
					db2.FillTable(table);
				}
			}
		});
		JLabel bg=null;
		
			try {
				bg = new JLabel(new ImageIcon(ImageIO.read(new File("logo.jpg"))));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		bg.setBounds(800, 700, 20, 40);
		bg.setPreferredSize(getMaximumSize());
		add(bg);
	}
}
