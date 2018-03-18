import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
public class Validations {

	public Validations() {

	}
	
	

	
	/**
	 * Key Listener for ID in order to take id in specified format
	 * @param Id
	 *
	 */
public void validateId(JTextField Id) {
		 Id.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	           char c = e.getKeyChar();
	           if (!((c >= '0') && (c <= '9') ||
	              (c == KeyEvent.VK_BACK_SPACE) ||
	              (c == KeyEvent.VK_DELETE))) {
	             
	             e.consume();
	           }else if (Id.getText().length() >= 8 ) {
	         	  e.consume();
	        	   
	           }
	         }
	       });

	 }



/**
 * Method which contains Eventhandler for login Button to check validation from database
 * It takes button and text field as argument
 */
public  void validateBtn(JButton loginButton,JTextField Id) {
	 loginButton.addActionListener(new ActionListener(){
     	public void actionPerformed(ActionEvent e){
     		Database db = new Database();
     		//db.openConnection();
     		try {
     		
     		String id=Id.getText();
     		
     	
     		if (db.getresult(id).next()) {
     			JOptionPane.showMessageDialog(null, "Login Successfull");
                   }
     		
     		else {
     			JOptionPane.showMessageDialog(null, "Invalid credentials");
     		}}
     		catch ( SQLException e1) {
     			e1.printStackTrace();
     		}
     	 }});	
  	}
	
}
