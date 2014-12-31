package gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import algo.Accountant;
import algo.Employee;
import algo.EmployeeFinance;

import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchSalarySlipPanel extends JPanel {
	private JTextField textField;
	private Accountant ac = new Accountant();
	private DatePanel d = new DatePanel();

	/**
	 * Create the panel.
	 */
	public SearchSalarySlipPanel() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 341, 80);
		add(panel);

		JLabel label = new JLabel("Reg ID");
		label.setBounds(6, 16, 99, 23);
		panel.add(label);

		textField = new JTextField(null);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String str=textField.getText();
				if (e.getKeyCode() < 95 || e.getKeyCode() > 106) {
					if (e.getKeyCode() == 8 || e.getKeyCode() ==127|| e.getKeyCode() ==37|| e.getKeyCode() ==38|| e.getKeyCode() ==39|| e.getKeyCode() ==40 ) {
						// do nothing
					}else if(e.getKeyCode() == 10)
					{
						Employee eObj = new Employee();
						eObj = ac.searchEmployee(Integer.parseInt(textField.getText()));
						System.out.println(eObj.isBol());
						if (eObj.isBol() == true) {
							System.out.println(eObj.getRegNo());
							EmployeeFinance eFObj = new EmployeeFinance(eObj);
							eFObj = ac.searchSalarySlip(eObj, d.getMonth(), d.getYear());
							System.out.println(eFObj.getMedAllowance());
							ResultSet rs = ac.getSalaryHistory(eFObj);
							SalarySlip obj = new SalarySlip(eObj, eFObj, rs);
							obj.setVisible(true);
							((Window) getRootPane().getParent()).dispose();
						} else {
							
							Messages m = new Messages();
							m.Failure("invalid Reg No", "Danger");
							
						}

					}
					else {
						if(str!=null)
						{
						str=str.substring(0, str.length());
						Messages m = new Messages();
						m.Failure("invalid character", "warning");
						textField.setText(str);
						}
						else
						{
							textField.setText("");
						}
						
						
					}
				}
			}
		});
		textField.setColumns(10);
		textField.setBounds(129, 16, 184, 23);
		panel.add(textField);

		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Employee eObj = new Employee();
				eObj = ac.searchEmployee(Integer.parseInt(textField.getText()));
				System.out.println(eObj.isBol());
				if (eObj.isBol() == true) {
					System.out.println(eObj.getRegNo());
					EmployeeFinance eFObj = new EmployeeFinance(eObj);
					eFObj = ac.searchSalarySlip(eObj, d.getMonth(), d.getYear());
					System.out.println(eFObj.getMedAllowance());
					ResultSet rs = ac.getSalaryHistory(eFObj);
					SalarySlip obj = new SalarySlip(eObj, eFObj, rs);
					obj.setVisible(true);
					((Window) getRootPane().getParent()).dispose();
				} else {
					Messages m = new Messages();
					m.Failure("invalid Reg No", "Danger");
				}

			}
		});
		button.setBounds(129, 46, 89, 23);
		panel.add(button);

		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmployeeMainFrame mf = new EmployeeMainFrame();
				mf.setVisible(true);
				((Window) getRootPane().getParent()).dispose();
			}
		});
		button_1.setBounds(224, 46, 89, 23);
		panel.add(button_1);

	}
}
