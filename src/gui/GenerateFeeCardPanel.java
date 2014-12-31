package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import algo.Accountant;
import algo.StudentFinance;

import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class GenerateFeeCardPanel extends JPanel {

	private DatePanel datePanel;
	private Accountant ac = new Accountant();
	private JButton btnBack;
	private StudentFinance sf=new StudentFinance(); 

	/**
	 * Create the panel.
	 */
	public GenerateFeeCardPanel() {
		setLayout(null);
		
		JPanel GenerateFeeCardPanel = new JPanel();
		GenerateFeeCardPanel.setBorder(new TitledBorder(null, "Generate Fee Card", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GenerateFeeCardPanel.setBounds(10, 11, 333, 139);
		add(GenerateFeeCardPanel);
		GenerateFeeCardPanel.setLayout(null);
		
		JLabel label = new JLabel("Month");
		label.setBounds(6, 16, 99, 31);
		GenerateFeeCardPanel.add(label);
		
		 datePanel = new DatePanel();
		JLabel label_1 = new JLabel(Integer.toString(datePanel.getMonth()));
		label_1.setBounds(145, 16, 130, 31);
		GenerateFeeCardPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Due Date");
		label_2.setBounds(6, 58, 99, 31);
		GenerateFeeCardPanel.add(label_2);
		
		
		datePanel.setBounds(115, 58, 212, 33);
		GenerateFeeCardPanel.add(datePanel);
		
		Date tDate = new Date();
		int d = tDate.getDay();
		int m = tDate.getMonth() + 1;
		int y = tDate.getYear() + 1900;
		
		System.out.println(d);
		System.out.println(m);
		System.out.println(y);

		Messages msg = new Messages();

		
		JButton button = new JButton("Generate");
		if(sf.isGenerate())
		{
			System.out.println("teri IF");
			button.setEnabled(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (datePanel.getDate() > d && datePanel.getMonth() == m && datePanel.getYear() == y) {
					ac.generateFeeCard(datePanel.getTimeAndDate(),datePanel.getMonth(),datePanel.getYear());
					msg.success("Successfully Generated", "Fee Card Generated");
				} else {
					msg.Failure("invalid Due Date", "Invalid Input");
				}
				
			}
		});
		button.setBounds(115, 105, 99, 23);
		GenerateFeeCardPanel.add(button);
		}
		else
		{
			System.out.println("teri else");
			button.setEnabled(false);
			button.setBounds(115, 105, 99, 23);
			GenerateFeeCardPanel.add(button);
		}
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentMainFrame mf=new StudentMainFrame();
				mf.setVisible(true);
				((Window) getRootPane().getParent()).dispose();
			}
		});
		btnBack_1.setBounds(224, 105, 99, 23);
		GenerateFeeCardPanel.add(btnBack_1);

	}
}
