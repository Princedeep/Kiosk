import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Graphics;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Insets;
import java.awt.SystemColor;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

//Brandon Le

public class AppointmentInterface extends JPanel{
	//public static JFrame frame = new JFrame("Algonquin College Kiosk");
    private static final long serialVersionUID = 1L;
	
    //Variable declaration
    private static JPanel contentPane;
    String[] appointTimes = {"10am", "12pm", "1pm", "3pm", "4pm"};//Place holder list	
    String[] roomNum = {"C347", "T324", "B242", "T126"};
    String[] day = {"03/03/2018", "05/05/2018", "06/06/2020"};
    JButton cancelButton;
    JButton confirmButton;
    JButton dateButton;
    JPanel contentPanel;
    JButton selectProf;
    JButton addProf;
    JDialog cancelDialog = new JDialog();
    ImagePanel image = new ImagePanel();
    SplashPanel splashPanel = new SplashPanel();
    Validations v = new Validations();
    AppointmentDb db = new AppointmentDb();
    View view = new View();
    String bd;
    String c= view.valueId;
    private JTable table;
    
    //Used to connect to database, will need to be changed depending on DB password and user
    private static Connection con = null; // Connection variable used for creating connection
	private static String connectionString = "jdbc:mysql://localhost/kiosk"; // String variable for connnection url
	private static String username = "root"; // String variable for Username of database
	private static String password = "death101"; // String variable for password of database
    
    String confirmDialog = "Appointment Scheduled";

    public static void main(String[] args) {
    	//ImagePanel.paintComponent();
    	
    	    	
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   AppointmentInterface frame = new AppointmentInterface();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    //Default constructor called
    public AppointmentInterface() {
    	
    	GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        setLayout(gbl_panel);
        setBackground(Color.WHITE);
        JLabel imageLabel = new JLabel("", JLabel.CENTER);
        add(imageLabel);
        
        JLabel profLabel = new JLabel("Professor's Email");
        GridBagConstraints gbcProfLabel = new GridBagConstraints();
        gbcProfLabel.gridx = 0;
        gbcProfLabel.gridy = 1;
        add(profLabel, gbcProfLabel);
        
        JLabel studentLabel = new JLabel("Your student ID");
        GridBagConstraints gbcStudentLabel = new GridBagConstraints();
        gbcStudentLabel.gridx = 2;
        gbcStudentLabel.gridy = 1;
        add(studentLabel,gbcStudentLabel);
        
        //Field for student ID
        JTextField studentId = new JTextField();
        studentId.setPreferredSize(new Dimension(200,25));
        GridBagConstraints gbcStudentId = new GridBagConstraints();
        gbcStudentId.fill=GridBagConstraints.HORIZONTAL;
        gbcStudentId.weightx = 1;
        gbcStudentId.gridx = 3;
        gbcStudentId.gridy = 1;
        add(studentId,gbcStudentId);
        
        //Field for professor email
        JTextField profEmail = new JTextField();
        profEmail.setPreferredSize(new Dimension(500,25));
        GridBagConstraints gbcProfEmail = new GridBagConstraints();
        gbcProfEmail.fill=GridBagConstraints.HORIZONTAL;
        gbcProfEmail.weightx=1;
        gbcProfEmail.gridx = 1;
        gbcProfEmail.gridy = 1;
        add(profEmail,gbcProfEmail);

        JLabel appointLabel = new JLabel("Select appointment time");
        GridBagConstraints gbcAppoint = new GridBagConstraints();
        gbcAppoint.insets = new Insets(0, 0, 0, 5);
        gbcAppoint.anchor = GridBagConstraints.EAST;
        gbcAppoint.gridx = 0;
        gbcAppoint.gridy = 0;
        add(appointLabel, gbcAppoint);

        //Combo box with placeholders for time (hh:pm/am) for appointment
        JComboBox comboBox = new JComboBox(appointTimes);
        comboBox.setForeground(new Color(60,165,38));
        comboBox.setSelectedIndex(1);
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 1;
        gbc_comboBox.gridy = 0;
        add(comboBox, gbc_comboBox);
        
        JLabel roomLabel = new JLabel("Select room for appointment");
        GridBagConstraints gbcRoomLabel = new GridBagConstraints();
        gbcRoomLabel.gridx = 0;
        gbcRoomLabel.gridy = 3;
        add(roomLabel, gbcRoomLabel);
        
        //Combo box for room number (with placeholders)
        JComboBox roomComboBox = new JComboBox(roomNum);
        roomComboBox.setForeground(new Color(60,165,38));
        roomComboBox.setBackground(Color.DARK_GRAY);
        roomComboBox.setOpaque(true);
        roomComboBox.setSelectedIndex(1);
        GridBagConstraints gbcRoomBox = new GridBagConstraints();
        gbcRoomBox.weightx=1.5;
        gbcRoomBox.gridx = 1;
        gbcRoomBox.gridy = 3;
        add(roomComboBox,gbcRoomBox);
        
        //Button used to open a new JFrame with a table of professor emails
        selectProf = new JButton("Select Prof");
        selectProf.setForeground(new Color(60,165,38));
        GridBagConstraints gbcProfButton = new GridBagConstraints();
        //gbcProfButton.gridwidth=1;
        gbcProfButton.fill = GridBagConstraints.HORIZONTAL;
        gbcProfButton.gridx=0;
        gbcProfButton.gridy=2;
        add(selectProf,gbcProfButton);
        
        //Button used to add appointment information to the database
        confirmButton = new JButton("Confirm Appointment");
        confirmButton.setBackground(Color.DARK_GRAY);
        confirmButton.setForeground(new Color(60,165,38));
        confirmButton.setOpaque(true);
        GridBagConstraints gbcConfirm = new GridBagConstraints();
        gbcConfirm.insets = new Insets(10,0,0,0);
        gbcConfirm.anchor = GridBagConstraints.PAGE_END;
        gbcConfirm.gridx = 5;
        gbcConfirm.gridy = 4;
        add(confirmButton,gbcConfirm);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setForeground(new Color(60,165,38));
        cancelButton.setBackground(Color.DARK_GRAY);
        cancelButton.setOpaque(true);
        GridBagConstraints gbcCancel1 = new GridBagConstraints();
        gbcCancel1.insets = new Insets(10,0,0,0);
        gbcCancel1.anchor = GridBagConstraints.PAGE_END;
        gbcCancel1.gridx = 6;
        gbcCancel1.gridy = 4;
        add(cancelButton,gbcCancel1);
        
        JLabel selectDateLabel = new JLabel("Please select a date");
        GridBagConstraints gbcDateLabel = new GridBagConstraints();
        gbcDateLabel.anchor = GridBagConstraints.PAGE_START;
        gbcDateLabel.gridy=0;
        gbcDateLabel.gridx = 2;
        gbcDateLabel.insets = new Insets(10,10,10,10);
        add(selectDateLabel,gbcDateLabel);
        
        //Combo box (with placeholders) for selecting the date of the appointment
        JComboBox dayCombo = new JComboBox(day);
        dayCombo.setBackground(Color.DARK_GRAY);
        dayCombo.setForeground(new Color(60,165,38));
        dayCombo.setOpaque(true);
        dayCombo.setSelectedIndex(1);
        GridBagConstraints gbcDate = new GridBagConstraints();
        gbcDate.anchor = GridBagConstraints.PAGE_END;
        gbcDate.gridx=3;
        gbcDate.gridy = 0;
        add(dayCombo,gbcDate);
        
        //Button to confirm the prof email selected
        JButton addProf = new JButton("Confirm Prof");
        addProf.setBounds(400,400,200,50);
        addProf.setVisible(true);
        
        //Grab the current selection in the combo box and turn it into a string
        String daySelected = dayCombo.getSelectedItem().toString();
        String timeSelected = comboBox.getSelectedItem().toString();
        String roomSelected = roomComboBox.getSelectedItem().toString();
        
		addProf.setForeground(new Color(60, 165, 38));
		addProf.setBackground(Color.DARK_GRAY);
		addProf.setOpaque(true);
		
		selectProf.setForeground(new Color(60, 165, 38));
		selectProf.setBackground(Color.DARK_GRAY);
		selectProf.setOpaque(true);
        
		//Create a new JFrame for the professor table
        JFrame fp= new JFrame();
        fp.setSize(700, 500);
        fp.setLayout(null);
        
        //Add the table with the professor email to JFrame
        table = new JTable();
        GridBagConstraints gbcTable = new GridBagConstraints();
        gbcTable.gridx=4;
        gbcTable.gridy=1;
        table.setPreferredSize(new Dimension(500,300));
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setBounds(6, 8, 520, 240);
		table.setBackground(Color.WHITE);
		table.setForeground(SystemColor.desktop);
		db.FillTable(table);//Grab the information for professor's email from database and add it to the table
		add(table,gbcTable);
		fp.add(table);
		fp.add(addProf);
		
		
		
//		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBounds(10, 11, 705, 282);
//		scrollPane.setVisible(true);
//		add(scrollPane);
		
		//Action listener to add button to database using InsertData method
        confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton confButton = (JButton) e.getSource();
				db.InsertData(studentId,profEmail,daySelected,timeSelected,roomSelected);
				JOptionPane.showMessageDialog(null, confirmDialog);
				
			}});
        //Set the JPanel to invisible when hitting cancel button
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent t) {
				JButton cancButton = (JButton) t.getSource();	
				int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure?");
				if(dialogResult == JOptionPane.YES_OPTION){
					setVisible(false);
				}
			}});
		
		//Open the frame containing the table of professor emails
		selectProf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent t){
				fp.setVisible(true);
			}
		});
		
		//Button to confirm the current selected professor email and add to the JPanel
		addProf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent t){
				try{
					int selectedRow = table.getSelectedRow();
					int selectedColumn = table.getSelectedColumn();
					bd = table.getValueAt(selectedRow, selectedColumn).toString();
					profEmail.setText(bd);
				}catch(Exception e){
					
				}
			}
		});
		
		
    }

}