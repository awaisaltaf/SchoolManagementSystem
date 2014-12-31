package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AdminRole extends JFrame {

	private JPanel contentPane;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";

	public AdminRole() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setTitle("Admin Roles");
		setBounds(100, 100, 800,500);
		setLocation(300, 150);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "admin.png")).getImage()).getScaledInstance(60,60,
				java.awt.Image.SCALE_SMOOTH)));
		label_1.setBounds(225, 11, 74, 60);
		contentPane.add(label_1);
		
		JLabel label = new JLabel("Admin WorkSpace");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(309, 30, 199, 22);
		contentPane.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(289, 55, 221, 2);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton(new ImageIcon(((new ImageIcon(address
				+ "stu_enroll.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(324, 207, 100, 100);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton(new ImageIcon(((new ImageIcon(address
				+ "t_enroll.png")).getImage()).getScaledInstance(60,60,
				java.awt.Image.SCALE_SMOOTH)));
		button.setBounds(202, 206, 100, 100);
		contentPane.add(button);
		
		JButton button_1 = new JButton(new ImageIcon(((new ImageIcon(address
				+ "emp.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			EmpAttendenceFrame empframe=new EmpAttendenceFrame();
			empframe.setVisible(true);
			dispose();
			}
		});
		button_1.setBounds(445, 206, 100, 100);
		contentPane.add(button_1);
		
		JLabel lblStudend = new JLabel("Student Enrollment");
		lblStudend.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblStudend.setBounds(167, 163, 102, 14);
		
		JButton btnBack = new JButton(new ImageIcon(((new ImageIcon(address
				+ "logout.png")).getImage()).getScaledInstance(40, 40,
				java.awt.Image.SCALE_SMOOTH)));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			FrontFrame ff=new FrontFrame();
			ff.setVisible(true);
			dispose();
			}
		});
		btnBack.setBounds(735, 55, 30, 30);
		contentPane.add(btnBack);
		
		JLabel lblEmployeeEnrollment = new JLabel("Employee Enrollment");
		lblEmployeeEnrollment.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmployeeEnrollment.setBounds(189, 308, 119, 14);
		contentPane.add(lblEmployeeEnrollment);
		
		JLabel lblStudentEnrollment = new JLabel("Student Enrollment");
		lblStudentEnrollment.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStudentEnrollment.setBounds(316, 308, 119, 14);
		contentPane.add(lblStudentEnrollment);
		
		JLabel lblEmployeeAttendence = new JLabel("Employee Attendence");
		lblEmployeeAttendence.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmployeeAttendence.setBounds(445, 308, 133, 14);
		contentPane.add(lblEmployeeAttendence);
		
		JLabel label_2 = new JLabel("Logout");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 9));
		label_2.setBounds(734, 85, 46, 14);
		contentPane.add(label_2);
	}
}
