package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import algo.Accountant;
import algo.EmployeeFinance;

import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenerateSalarySlipPanel extends JPanel {
	private DatePanel datePanel = new DatePanel();
	private Accountant ac = new Accountant();
	EmployeeFinance ef = new EmployeeFinance();

	/**
	 * Create the panel.
	 */
	public GenerateSalarySlipPanel() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Generate Salary Slip",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 11, 200, 79);
		add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel(Integer.toString(datePanel.getMonth()));
		label.setBounds(105, 16, 89, 22);
		panel.add(label);

		JLabel label_1 = new JLabel("Month");
		label_1.setBounds(6, 16, 89, 22);
		panel.add(label_1);

		JButton btnNewButton = new JButton("Generate");
		if (ef.isGenerate()) {
			btnNewButton.setEnabled(true);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ac.generateSalarySlip(datePanel.getMonth(),
							datePanel.getYear());
				}
			});

			btnNewButton.setBounds(6, 49, 89, 23);
			panel.add(btnNewButton);
		}
		else
		{
			btnNewButton.setEnabled(false);
			btnNewButton.setBounds(6, 49, 89, 23);
			panel.add(btnNewButton);
		}

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmployeeMainFrame mf = new EmployeeMainFrame();
				mf.setVisible(true);
				((Window) getRootPane().getParent()).dispose();
			}
		});
		btnBack.setBounds(105, 49, 89, 23);
		panel.add(btnBack);

	}
}
