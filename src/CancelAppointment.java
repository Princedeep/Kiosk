//Brandon Le

import java.awt.BorderLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import net.proteanit.sql.DbUtils;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CancelAppointment extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    JButton buttonc;
    JButton viewAppointments;
    JPanel contentPanel;
    JButton confirmButton;
    JTable table;
    String bd;
    AppointmentDb db = new AppointmentDb();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   CancelAppointment frame = new CancelAppointment();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    //Default constructor
    public CancelAppointment() {
    	
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        setLayout(gbl_panel);
        setBackground(Color.WHITE);
        
        //Button to open up JFrame and see appointment interface
        viewAppointments = new JButton("View Appointments");
        GridBagConstraints gbcViewApp = new GridBagConstraints();

        gbcViewApp.gridx = 1;
        gbcViewApp.gridy=  1;
        add(viewAppointments,gbcViewApp);

		viewAppointments.setForeground(new Color(60, 165, 38));
		viewAppointments.setBackground(Color.DARK_GRAY);
		viewAppointments.setOpaque(true);

        
        confirmButton = new JButton("Confirm");      
        buttonc = new JButton("Cancel Selected Appointment");
        
        //Action listener to confirm the selected row gets removed from the database
        buttonc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
							
				db.deleteAppointment(table);
				db.getAppointInfo(table);
				JOptionPane.showMessageDialog(null, "Appointment removed");
			}
		});
        
        //Button to query appointment table and open up in new JFrame
        viewAppointments.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		   JFrame f= new JFrame();
        		   table = new JTable();
        	        f.setSize(700, 500);
        	      
        	        f.add(buttonc);
        	        buttonc.setBounds(100, 150, 200, 100);
        	       
        			db.getAppointInfo(table);
        			f.add(table);
        			f.setVisible(true);
        	}
        });
       
    }

}