package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import algo.Admin;
import algo.Login;
import algo.Teacher;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ALoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";

	public ALoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setLocation(300, 150);
		setResizable(false);
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setTitle("Admin Login");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_1 = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "admin.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		label_1.setBounds(226, 5, 83, 71);
		contentPane.add(label_1);

		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrontFrame ff = new FrontFrame();
				ff.setVisible(true);
				dispose();
			}
		});
		button.setBounds(406, 432, 89, 23);
		contentPane.add(button);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Enter", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(161, 118, 459, 231);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblInvalidInput = new JLabel();
		lblInvalidInput.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInvalidInput.setForeground(Color.RED);
		lblInvalidInput.setBounds(249, 30, 94, 14);
		lblInvalidInput.setVisible(false);
		panel.add(lblInvalidInput);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == 10) {
					Admin A = new Admin();
					int lid = A.signin(textField.getText().toString(),
							passwordField.getText().toString());
					try {
						if (lid != -1) {
							AdminRole ar = new AdminRole();
							ar.setVisible(true);
							dispose();
						} else {
							Messages msg = new Messages();
							msg.Failure("ID Or Password Is Invalid",
									"Login Failed");
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		});
	
		textField.setBounds(172, 91, 159, 20);
		panel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					Admin A = new Admin();
					int lid = A.signin(textField.getText().toString(),
							passwordField.getText().toString());
					try {
						if (lid != -1) {
							AdminRole ar = new AdminRole();
							ar.setVisible(true);
							dispose();
						} else {
							Messages msg = new Messages();
							msg.Failure("ID Or Password Is Invalid",
									"Login Failed");
						}
					} catch (Exception e1) {
						// TODO: handle exception
					}
				}
			}
		});
		passwordField.setBounds(172, 120, 159, 20);
		panel.add(passwordField);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(150, 94, 46, 14);
		panel.add(lblId);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(107, 123, 63, 14);
		panel.add(lblPassword);
		JButton btnEnter = new JButton("Login");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Admin A = new Admin();
				int lid = A.signin(textField.getText().toString(),
						passwordField.getText().toString());
				try {
					if (lid != -1) {
						AdminRole ar = new AdminRole();
						ar.setVisible(true);
						dispose();
					} else {
						Messages msg = new Messages();
						msg.Failure("ID Or Password Is Invalid", "Login Failed");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnEnter.setBounds(311, 432, 89, 23);
		contentPane.add(btnEnter);

		JLabel lblTeacherLogin = new JLabel("Admin Login");
		lblTeacherLogin.setForeground(Color.BLACK);
		lblTeacherLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeacherLogin.setBounds(316, 27, 128, 21);
		contentPane.add(lblTeacherLogin);

		JSeparator separator = new JSeparator();
		separator.setBounds(301, 50, 146, 2);
		contentPane.add(separator);

		JLabel label = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "login.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		label.setBounds(442, 10, 83, 60);
		contentPane.add(label);

	}
}
