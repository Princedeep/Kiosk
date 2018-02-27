


import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField; 

public class View {
	static JPanel panel = new JPanel();
	static  Database db= new Database();
	private static  Connection con = null; // Connection variable used for creating connection
	private  String connectionString = "jdbc:mysql://localhost/info"; //String variable for connnection url
	private  String username = "assignment1"; // String variable for Username of database
	private String password = "password"; //
	static Statement st;
	static ResultSet exe;
	public static JFrame frame = new JFrame("Algonquin College Kiosk");
	  static JPasswordField passwordText = new JPasswordField(20);
	static JTextField userText = new JTextField(20);
	
	public View() {
		
	}
    public static void main(String[] args) {    
    
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JPanel splashPanel = new JPanel();
        JPanel splashPanel2 = new JPanel();
 
        frame.add(panel);

        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");

        userLabel.setBounds(10,40,80,25);
        panel.add(userLabel);
        

		JRadioButton studentBtn = new JRadioButton("student");
        studentBtn.setBounds(20,02,70,20);
        studentBtn.setVisible(true);
        panel.add(studentBtn);
        

		JRadioButton adminBtn = new JRadioButton("admin");
        adminBtn.setBounds(100,02,90,20);
        adminBtn.setVisible(true);
        panel.add(adminBtn);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,40,165,25);
        panel.add(userText);
     
       String user;
     
       
       
     
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,80,80,25);
        panel.add(passwordLabel);

       
        passwordText.setBounds(100,80,165,25);
        panel.add(passwordText);
        
        String password;
       

        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 150, 80, 25);
        panel.add(loginButton);
      

      
ButtonGroup gp = new ButtonGroup();
gp.add(studentBtn);
gp.add(adminBtn);

        
        frame.setVisible(true);
      
        loginButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		
        		
       			 
        		 if (studentBtn.isSelected()==true) {
        			 
        			 frame.setContentPane(splashPanel2(splashPanel2));
        			 
        		 }
        		 
                 if (adminBtn.isSelected()==true) {
        			 
        			 frame.setContentPane(splashPanel(splashPanel));
        			 
        		 }
        		
        		System.out.println("Button Clicked");
        		frame.invalidate();
        		frame.validate();
        	
        	}   });
    }

    private static JPanel splashPanel(JPanel splashPanel) {

    	splashPanel.setLayout(new GridBagLayout());
    	
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridy = GridBagConstraints.RELATIVE;
        
        gbc.weighty = 1;
        JButton timeTableButton = new JButton("Time Table");
        splashPanel.add(timeTableButton, gbc);
        
    
       
        gbc.weighty = 2;
        JButton updateScreenButton = new JButton("Update Main Screen");
        splashPanel.add(updateScreenButton, gbc);
        
        gbc.weighty = 3;
        JButton usrBtn= new JButton("Add user");
        splashPanel.add(usrBtn, gbc);
        
        usrBtn.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){

        		   db.openConnection();
        			db.insert();
        			JOptionPane.showMessageDialog(frame,
        				    "User is added to database.");
  
        		  
        	}});
     
        
        
        gbc.weighty = 4;
        JButton back = new JButton("Go back");
        splashPanel.add(back, gbc);
        
        back.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		
      		     
        
        	}});
       
        
        
 
        return splashPanel;
    }
    
    public JPasswordField getPass() {
    	return passwordText;
    }
    
    public JTextField getuser() {
    	return userText;
    }
    
    public void setuser( JTextField userText) {
    	this.userText=userText;
    }
    
    public void setpass(JPasswordField passwordText) {
    	this.passwordText=passwordText;
    }
    private static JPanel splashPanel2(JPanel splashPanel2) {

    	splashPanel2.setLayout(new GridBagLayout());
    	
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridy = GridBagConstraints.RELATIVE;
        
        gbc.weighty = 1;
        JButton timeTableButton = new JButton("Time Table");
        splashPanel2.add(timeTableButton, gbc);
        
        gbc.weighty = 2;
        JButton requestAppointmentButton = new JButton("Student Request Appointment");
        splashPanel2.add(requestAppointmentButton, gbc);
       
   
        
        gbc.weighty = 4;
        JButton cancelClassButton = new JButton("Class Cancellation");
        splashPanel2.add(cancelClassButton, gbc);
        
        
        gbc.weighty = 5;
        JButton back = new JButton("Go back");
        splashPanel2.add(back, gbc);

        
 
        return splashPanel2;
    }
    
    
    
    

}