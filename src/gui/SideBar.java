package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SideBar extends JPanel {

	/**
	 * Create the panel.
	 */
	public SideBar() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Accountant Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 137, 222);
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Students");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentMainFrame smf=new StudentMainFrame();
				smf.setVisible(true);
				((Window) getRootPane().getParent()).dispose();
			}
		});
		btnNewButton.setBounds(20, 25, 102, 87);
		panel.add(btnNewButton);
		
		JButton btnEmployees = new JButton("Employees");
		btnEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeMainFrame emf=new EmployeeMainFrame();
				emf.setVisible(true);
				((Window) getRootPane().getParent()).dispose();
			}
		});
		btnEmployees.setBounds(20, 113, 102, 87);
		panel.add(btnEmployees);

	}

}
