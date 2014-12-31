package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.Icon;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class AccountantMainFrame extends JFrame {

	private JPanel contentPane;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";

	public AccountantMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setBounds(100, 100, 800, 500);
		setTitle("Accountant Work Space");
		setLocation(300, 150);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "acc.png")).getImage()).getScaledInstance(70, 70,
				java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel.setBounds(194, -1, 90, 90);
		contentPane.add(lblNewLabel);

		JLabel lblAccountantWorkspace = new JLabel("Accountant WorkSpace");
		lblAccountantWorkspace.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAccountantWorkspace.setBounds(269, 27, 279, 24);
		contentPane.add(lblAccountantWorkspace);

		JSeparator separator = new JSeparator();
		separator.setBounds(252, 54, 279, 2);
		contentPane.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Select", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(183, 118, 434, 232);
		contentPane.add(panel);
		panel.setLayout(null);
		
				JButton button = new JButton(new ImageIcon(((new ImageIcon(address
						+ "stu_enroll.png")).getImage()).getScaledInstance(60, 60,
						java.awt.Image.SCALE_SMOOTH)));
				button.setBounds(233, 76, 78, 71);
				panel.add(button);
				
						
				
						JLabel lblEmployee = new JLabel("Employee");
						lblEmployee.setBounds(161, 147, 58, 14);
						panel.add(lblEmployee);
						
								JLabel lblStudent = new JLabel("Student");
								lblStudent.setBounds(250, 148, 46, 14);
								panel.add(lblStudent);
								
								JButton button_1 = new JButton(new ImageIcon(((new ImageIcon(address
										+ "emp.png")).getImage()).getScaledInstance(60, 60,
										java.awt.Image.SCALE_SMOOTH)));
								button_1.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
									EmployeeMainFrame emf=new EmployeeMainFrame();
									emf.setVisible(true);
									dispose();
									}
								});
								button_1.setBounds(151, 76, 78, 71);
								panel.add(button_1);
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				StudentMainFrame smf=new StudentMainFrame();
					smf.setVisible(true);
					dispose();
					}
				});

	
		JButton button_2 = new JButton(new ImageIcon(((new ImageIcon(address
				+ "logout.png")).getImage()).getScaledInstance(40, 40,
				java.awt.Image.SCALE_SMOOTH)));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrontFrame ff = new FrontFrame();
				ff.setVisible(true);
				dispose();
			}
		});
		button_2.setBounds(721, 54, 30, 30);
		contentPane.add(button_2);
		
		JLabel label = new JLabel("Logout");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Tahoma", Font.BOLD, 9));
		label.setBounds(720, 84, 46, 14);
		contentPane.add(label);
	}
}
