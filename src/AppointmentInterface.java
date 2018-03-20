import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppointmentInterface extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    String[] appointTimes = {"10am", "12pm", "1pm", "3pm", "4pm"};	
    JButton cancelButton;
    JButton confirmButton;
    JButton dateButton;
    JDialog cancelDialog = new JDialog();
    DateChooserPanel calendar = new DateChooserPanel();

    
    
    String confirmDialog = "Appointment Scheduled";

    public static void main(String[] args) {
    	
    	    	
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        JLabel appointLabel = new JLabel("Select appointment time");
        GridBagConstraints gbcAppoint = new GridBagConstraints();
        gbcAppoint.insets = new Insets(0, 0, 0, 5);
        gbcAppoint.anchor = GridBagConstraints.EAST;
        gbcAppoint.gridx = 0;
        gbcAppoint.gridy = 0;
        panel.add(appointLabel, gbcAppoint);

        JComboBox comboBox = new JComboBox(appointTimes);
        comboBox.setSelectedIndex(1);
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 1;
        gbc_comboBox.gridy = 0;
        panel.add(comboBox, gbc_comboBox);
        
        confirmButton = new JButton("Confirm Appointment");
        GridBagConstraints gbcConfirm = new GridBagConstraints();
        gbcConfirm.insets = new Insets(10,0,0,0);
        gbcConfirm.anchor = GridBagConstraints.PAGE_END;
        panel.add(confirmButton);
        
        cancelButton = new JButton("Cancel");
        GridBagConstraints gbcCancel1 = new GridBagConstraints();
        gbcCancel1.insets = new Insets(10,0,0,0);
        gbcCancel1.anchor = GridBagConstraints.PAGE_END;
        panel.add(cancelButton);
        
        JLabel selectDateLabel = new JLabel("Please select a date");
        GridBagConstraints gbcDateLabel = new GridBagConstraints();
        gbcDateLabel.anchor = GridBagConstraints.PAGE_START;
        gbcDateLabel.gridy=2;
        panel.add(selectDateLabel);
        
        dateButton = new JButton("Calendar");
        GridBagConstraints gbcDate = new GridBagConstraints();
        gbcDate.anchor = GridBagConstraints.PAGE_END;
        gbcDate.gridy = 2;
        panel.add(dateButton);
        
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
				JOptionPane.showConfirmDialog(null,"Are you sure?");		
			}});
		dateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton dateAction = (JButton) e.getSource();
				//calendar.getCalendarPanel();
				panel.add(calendar);
		}});
        
        

        pack();
    }

}