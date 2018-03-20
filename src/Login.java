import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField; 


public class Login {
	public static JFrame frame = new JFrame("Algonquin College Kiosk");
	
    public static void main(String[] args) {    

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JPanel splashPanel = new JPanel();
 
        frame.add(panel);

        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");

        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);
     
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);
        
        frame.setVisible(true);
       
        loginButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		frame.setContentPane(splashPanel(splashPanel));
        		System.out.println("Login Button Clicked");
        		frame.invalidate();
        		frame.validate();
        	}
        });
    }

    private static JPanel splashPanel(JPanel splashPanel) {
    	
    	JPanel timeTablePanel = new JPanel();
    	JPanel requestAppointmentPanel = new JPanel();
    	JPanel updateScreenPanel = new JPanel();
    	JPanel cancelClassPanel = new JPanel();

    	splashPanel.setLayout(new GridBagLayout());
    	
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridy = GridBagConstraints.RELATIVE;
        
        // Timetable
        gbc.weighty = 1;
        JButton timeTableButton = new JButton("Time Table");
        splashPanel.add(timeTableButton, gbc);
        timeTableButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		JPanel previous = (JPanel) frame.getContentPane();
        		frame.setContentPane(timeTablePanel(timeTablePanel, previous));
        		System.out.println("Timetable Button Clicked");
        		frame.invalidate();
        		frame.validate();
        	}
        });
        
        // Appointment Request
        gbc.weighty = 2;
        JButton requestAppointmentButton = new JButton("Student Request Appointment");
        splashPanel.add(requestAppointmentButton, gbc);
        requestAppointmentButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		JPanel previous = (JPanel) frame.getContentPane();
        		frame.setContentPane(requestAppointmentPanel(requestAppointmentPanel, previous));
        		System.out.println("Request Appointment Button Clicked");
        		frame.invalidate();
        		frame.validate();
        	}
        });
        
        // Update Screen
        gbc.weighty = 3;
        JButton updateScreenButton = new JButton("Update Main Screen");
        splashPanel.add(updateScreenButton, gbc);
        updateScreenButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		JPanel previous = (JPanel) frame.getContentPane();
        		frame.setContentPane(updateScreenPanel(updateScreenPanel, previous));
        		System.out.println("Update Screen Button Clicked");
        		frame.invalidate();
        		frame.validate();
        	}
        });
        
        // Cancel Class
        gbc.weighty = 4;
        JButton cancelClassButton = new JButton("Class Cancellation");
        splashPanel.add(cancelClassButton, gbc);
        cancelClassButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		JPanel previous = (JPanel) frame.getContentPane();
        		frame.setContentPane(cancelClassPanel(cancelClassPanel, previous));
        		System.out.println("Cancel Class Button Clicked");
        		frame.invalidate();
        		frame.validate();
        	}
        });
 
        return splashPanel;
    }
    
    private static JPanel timeTablePanel(JPanel timeTablePanel, JPanel previous) {

    	
    	timeTablePanel.setLayout(new GridBagLayout());
    	
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridy = GridBagConstraints.RELATIVE;
        
        gbc.weighty = 1;
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		frame.getContentPane().removeAll();
        		previousPanel(previous);
        		System.out.println("Back Button Clicked");
        		frame.validate();
        	}
        });
        // Simple text field to show which panel is active (testing only)
        JLabel panelLabel = new JLabel("Timetable");
        
        timeTablePanel.add(backButton, gbc);
        timeTablePanel.add(panelLabel, gbc);
        return timeTablePanel;
    }
    
    private static JPanel requestAppointmentPanel(JPanel requestAppointmentPanel, JPanel previous) {

    	requestAppointmentPanel.setLayout(new GridBagLayout());
    	
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridy = GridBagConstraints.RELATIVE;
        
        gbc.weighty = 1;
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		frame.getContentPane().removeAll();
        		previousPanel(previous);
        		System.out.println("Back Button Clicked");
        		frame.validate();
        	}
        });
                
        // Simple text field to show which panel is active (testing only)
        JLabel panelLabel = new JLabel("Request Appointment");
        
        requestAppointmentPanel.add(backButton, gbc);
        requestAppointmentPanel.add(panelLabel, gbc);

        return requestAppointmentPanel;
    }
    
    private static JPanel updateScreenPanel(JPanel updateScreenPanel, JPanel previous) {

    	updateScreenPanel.setLayout(new GridBagLayout());
    	
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridy = GridBagConstraints.RELATIVE;
        
        gbc.weighty = 1;
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		frame.getContentPane().removeAll();
        		previousPanel(previous);
        		System.out.println("Back Button Clicked");
        		frame.validate();
        	}
        });
        
        // Simple text field to show which panel is active (testing only)
        JLabel panelLabel = new JLabel("Update Screen");
        
        updateScreenPanel.add(backButton, gbc);
        updateScreenPanel.add(panelLabel, gbc);

        return updateScreenPanel;
    }
    
    private static JPanel cancelClassPanel(JPanel cancelClassPanel, JPanel previous) {

    	cancelClassPanel.setLayout(new GridBagLayout());
    	
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridy = GridBagConstraints.RELATIVE;
        
        gbc.weighty = 1;
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		frame.getContentPane().removeAll();
        		previousPanel(previous);
        		System.out.println("Back Button Clicked");
        		frame.validate();
        	}
        });
        
        // Simple text field to show which panel is active (testing only)
        JLabel panelLabel = new JLabel("Cancel Class");
        
        cancelClassPanel.add(backButton, gbc);
        cancelClassPanel.add(panelLabel, gbc);

        return cancelClassPanel;
    }

    private static void previousPanel(JPanel previous){
    	frame.setContentPane(previous);
    }
}