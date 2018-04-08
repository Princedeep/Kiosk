import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppointmentInterface extends JPanel{
	//public static JFrame frame = new JFrame("Algonquin College Kiosk");
    private static final long serialVersionUID = 1L;
	
    private static JPanel contentPane;
    String[] appointTimes = {"10am", "12pm", "1pm", "3pm", "4pm"};//Place holder list	
    String[] roomNum = {"C347", "T324", "B242", "T126"};
    String[] day = {"03/03/2018", "05/05/2018", "06/06/2020"};
    JButton cancelButton;
    JButton confirmButton;
    JButton dateButton;
    JDialog cancelDialog = new JDialog();
    ImagePanel image = new ImagePanel();
    SplashPanel splashPanel = new SplashPanel();

    
    
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
    
    

    public AppointmentInterface() {
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //contentPane = new JPanel();
        //contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //contentPane.setLayout(new GridBagLayout());
        //setContentPane(contentPane);
        
        //ImageIcon image = new ImageIcon("C:\\Users\\Brandon\\Pictures\\logo-algonquin");

       
       
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        setLayout(gbl_panel);
        
        JLabel imageLabel = new JLabel("", JLabel.CENTER);
        add(imageLabel);
        
        JLabel profLabel = new JLabel("Professor's Email");
        GridBagConstraints gbcProfLabel = new GridBagConstraints();
        gbcProfLabel.gridx = 0;
        gbcProfLabel.gridy = 1;
        add(profLabel, gbcProfLabel);
        
        JTextField profEmail = new JTextField();
        profEmail.setPreferredSize(new Dimension(300,25));
        GridBagConstraints gbcProfEmail = new GridBagConstraints();
        gbcProfEmail.gridx = 1;
        gbcProfEmail.gridy = 1;
        add(profEmail,gbcProfEmail);
        
        JLabel reasonLabel = new JLabel("Reason for appointment?");
        GridBagConstraints gbcReasonLabel = new GridBagConstraints();
        gbcReasonLabel.gridx = 0;
        gbcReasonLabel.gridy = 3;
        add(reasonLabel,gbcReasonLabel);
        
        JTextField reasonText = new JTextField();
        reasonText.setPreferredSize(new Dimension(300,100));
        GridBagConstraints gbcReasonText = new GridBagConstraints();
        gbcReasonText.gridx = 1;
        gbcReasonText.gridy = 3;
        add(reasonText,gbcReasonText);

        JLabel appointLabel = new JLabel("Select appointment time");
        GridBagConstraints gbcAppoint = new GridBagConstraints();
        gbcAppoint.insets = new Insets(0, 0, 0, 5);
        gbcAppoint.anchor = GridBagConstraints.EAST;
        gbcAppoint.gridx = 0;
        gbcAppoint.gridy = 0;
        add(appointLabel, gbcAppoint);

        JComboBox comboBox = new JComboBox(appointTimes);
        comboBox.setSelectedIndex(1);
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 1;
        gbc_comboBox.gridy = 0;
        add(comboBox, gbc_comboBox);
        
        JLabel roomLabel = new JLabel("Select room for appointment");
        GridBagConstraints gbcRoomLabel = new GridBagConstraints();
        gbcRoomLabel.gridx = 0;
        gbcRoomLabel.gridy = 5;
        add(roomLabel, gbcRoomLabel);
        
        JComboBox roomComboBox = new JComboBox(roomNum);
        roomComboBox.setSelectedIndex(1);
        GridBagConstraints gbcRoomBox = new GridBagConstraints();
        gbcRoomBox.gridx = 1;
        gbcRoomBox.gridy = 5;
        add(roomComboBox,gbcRoomBox);
        
        
        confirmButton = new JButton("Confirm Appointment");
        confirmButton.setBackground(Color.GREEN);
        GridBagConstraints gbcConfirm = new GridBagConstraints();
        gbcConfirm.insets = new Insets(10,0,0,0);
        gbcConfirm.anchor = GridBagConstraints.PAGE_END;
        gbcConfirm.gridx = 5;
        gbcConfirm.gridy = 5;
        add(confirmButton,gbcConfirm);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.green);
        GridBagConstraints gbcCancel1 = new GridBagConstraints();
        gbcCancel1.insets = new Insets(10,0,0,0);
        gbcCancel1.anchor = GridBagConstraints.PAGE_END;
        gbcCancel1.gridx = 6;
        gbcCancel1.gridy = 5;
        add(cancelButton,gbcCancel1);
        
        JLabel selectDateLabel = new JLabel("Please select a date");
        GridBagConstraints gbcDateLabel = new GridBagConstraints();
        gbcDateLabel.anchor = GridBagConstraints.PAGE_START;
        gbcDateLabel.gridy=0;
        gbcDateLabel.gridx = 2;
        gbcDateLabel.insets = new Insets(10,10,10,10);
        add(selectDateLabel,gbcDateLabel);
        
        JComboBox dayCombo = new JComboBox(day);
        dayCombo.setSelectedIndex(1);
        GridBagConstraints gbcDate = new GridBagConstraints();
        gbcDate.anchor = GridBagConstraints.PAGE_END;
        gbcDate.gridx=3;
        gbcDate.gridy = 0;
        add(dayCombo,gbcDate);
        
        confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton confButton = (JButton) e.getSource();
				JOptionPane.showMessageDialog(null, confirmDialog);
			}});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent t) {
				JButton cancButton = (JButton) t.getSource();	
				int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure?");
				if(dialogResult == JOptionPane.YES_OPTION){
					//frame.getContentPane().removeAll();
	        		//previousPanel(previous);
					//GridBagLayout.add(splashPanel);
	        		//frame.validate();
				}
			}});

//		dateButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JButton dateAction = (JButton) e.getSource();
//				//calendar.getCalendarPanel();
//				panel.add(calendar);
//		}});
        
        

        //pack();
    }

}