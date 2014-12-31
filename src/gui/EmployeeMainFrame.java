package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.Icon;

import algo.Accountant;

public class EmployeeMainFrame extends JFrame {

	private JPanel contentPane;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	public EmployeeMainFrame() {
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setBounds(100, 100, 800, 500);
		setTitle("Employee Salary Module");
		setLocation(300, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		SideBar sideBar = new SideBar();
		sideBar.setBounds(28, 147, 161, 258);
		contentPane.add(sideBar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Employee Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(28, 83, 724, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		
		GenerateSalarySlipPanel generateSalarySlipPanel = new GenerateSalarySlipPanel();
		generateSalarySlipPanel.setBounds(320, 200, 353, 180);
		
		SearchSalarySlipPanel searchSalarySlipPanel = new SearchSalarySlipPanel();
		searchSalarySlipPanel.setBounds(250, 200, 350, 105);
		
		JButton btnGenerateSalarySlip = new JButton("Generate Salary Slip");
		btnGenerateSalarySlip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//panel.disable();
				setContentPane(generateSalarySlipPanel);
			}
		});
		btnGenerateSalarySlip.setBounds(208, 16, 149, 23);
		panel.add(btnGenerateSalarySlip);
		
		JButton btnIssueSalary = new JButton("Issue Salary");
		btnIssueSalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//panel.disable();
				setContentPane(searchSalarySlipPanel);
				
			}
		});
		btnIssueSalary.setBounds(366, 16, 160, 23);
		panel.add(btnIssueSalary);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(295, 51, 205, 2);
		contentPane.add(separator);
		
		JLabel lblEmployeeSalary = new JLabel("Employee Salary  ");
		lblEmployeeSalary.setForeground(Color.BLACK);
		lblEmployeeSalary.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmployeeSalary.setBounds(310, 21, 195, 27);
		contentPane.add(lblEmployeeSalary);
		
		JLabel label_1 = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "fee.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		label_1.setBounds(226, 0, 80, 80);
		contentPane.add(label_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			AccountantMainFrame acc=new AccountantMainFrame();
			acc.setVisible(true);
			dispose();
			}
		});
		btnBack.setBounds(670, 427, 89, 23);
		contentPane.add(btnBack);
		
		//contentPane.add(searchSalarySlipPanel);
		
		
		//contentPane.add(generateSalarySlipPanel);
	}
}
