import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CancelAppointment extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    String[] appointTimes = {"20/10/2018-10am", "21/10/2018-12pm", "25/10/2018-1pm"};	
    JButton button;
    JButton confirmButton;

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

    public CancelAppointment() {

        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        setLayout(gbl_panel);

        JLabel cancelLabel = new JLabel("Select appointment to cancel");
        GridBagConstraints gbcCancel = new GridBagConstraints();
        gbcCancel.insets = new Insets(0, 0, 0, 5);
        gbcCancel.anchor = GridBagConstraints.EAST;
        gbcCancel.gridx = 0;
        gbcCancel.gridy = 0;
        add(cancelLabel, gbcCancel);

        JComboBox comboBox = new JComboBox(appointTimes);
        comboBox.setSelectedIndex(1);
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 1;
        gbc_comboBox.gridy = 0;
        add(comboBox, gbc_comboBox);
        
        confirmButton = new JButton("Confirm");
        GridBagConstraints gbcConfirm = new GridBagConstraints();
        gbcConfirm.insets = new Insets(10,0,0,0);
        gbcConfirm.anchor = GridBagConstraints.PAGE_END;
        gbcConfirm.gridx = 4;
        gbcConfirm.gridy = 4;
        add(confirmButton,gbcConfirm);
        
        button = new JButton("Cancel");
        GridBagConstraints gbcCancel1 = new GridBagConstraints();
        gbcCancel1.insets = new Insets(10,0,0,0);
        gbcCancel1.anchor = GridBagConstraints.PAGE_END;
        gbcCancel1.gridx = 5;
        gbcCancel1.gridy = 4;
        add(button,gbcCancel1);
        
        
        button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent t) {
				JButton cancButton = (JButton) t.getSource();	
				JOptionPane.showConfirmDialog(null,"Are you sure?");		
			}});
        confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton confButton = (JButton) e.getSource();
				JOptionPane.showMessageDialog(null, "Appointment removed");
			}});

       
    }

}