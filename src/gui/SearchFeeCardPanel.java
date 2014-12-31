package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import algo.Accountant;
import algo.Student;
import algo.StudentFinance;

import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SearchFeeCardPanel extends JPanel {
	private JTextField textField;
	private Accountant ac = new Accountant();
	private JButton btnNewButton;

	/**
	 * Create the panel.
	 */
	public SearchFeeCardPanel() {
		setLayout(null);

		JPanel SearchPanel = new JPanel();
		SearchPanel.setBorder(new TitledBorder(null, "Search",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		SearchPanel.setBounds(10, 11, 306, 80);
		add(SearchPanel);
		SearchPanel.setLayout(null);

		JLabel lblRegId = new JLabel("Reg ID");
		lblRegId.setBounds(6, 16, 99, 23);
		SearchPanel.add(lblRegId);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				String str = textField.getText();
				if (arg0.getKeyCode() < 95 || arg0.getKeyCode() > 106) {

					if (arg0.getKeyCode() == 8 || arg0.getKeyCode() == 127
							|| arg0.getKeyCode() == 37
							|| arg0.getKeyCode() == 38
							|| arg0.getKeyCode() == 39
							|| arg0.getKeyCode() == 40) {
						// do nothing
					} else if (arg0.getKeyCode() == 10) {
						DatePanel d = new DatePanel();
						Student sObj = new Student();
						sObj = ac.searchStudent(Integer.parseInt(textField
								.getText()));
						if (sObj.isBol() == true) {
							StudentFinance fObj = new StudentFinance();
							fObj = ac.searchFeeCard(sObj, d.getMonth(),
									d.getYear());
							ResultSet rs = ac.getfeetHistory(fObj);
							FeeCard obj = new FeeCard(sObj, fObj, rs);
							obj.setVisible(true);
							((Window) getRootPane().getParent()).dispose();
						} else {
							Messages m = new Messages();
							m.Failure("invalid Reg No", "Danger");
						}
					} else {
						if (str != null) {
							str = str.substring(0, str.length());
							Messages m = new Messages();
							m.Failure("invalid character", "warning");
							textField.setText(str);
						} else {
							textField.setText("");
						}
					}
				}
			}
		});
		textField.setBounds(97, 16, 184, 23);
		SearchPanel.add(textField);
		textField.setColumns(10);

		btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatePanel d = new DatePanel();
				Student sObj = new Student();
				sObj = ac.searchStudent(Integer.parseInt(textField.getText()));
				if (sObj.isBol() == true) {
				
					StudentFinance fObj = new StudentFinance();
					fObj = ac.searchFeeCard(sObj, d.getMonth(), d.getYear());
					ResultSet rs = ac.getfeetHistory(fObj);
					FeeCard obj = new FeeCard(sObj, fObj, rs);
					obj.setVisible(true);
					((Window) getRootPane().getParent()).dispose();
				} else {
					Messages m = new Messages();
					m.Failure("invalid Reg No", "Danger");
				}
				// setVisible(false);
				// dispose();
			}
		});
		btnNewButton.setBounds(97, 46, 89, 23);
		SearchPanel.add(btnNewButton);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentMainFrame mf = new StudentMainFrame();
				mf.setVisible(true);
				((Window) getRootPane().getParent()).dispose();
			}
		});
		btnBack.setBounds(192, 46, 89, 23);
		SearchPanel.add(btnBack);

	}
}
