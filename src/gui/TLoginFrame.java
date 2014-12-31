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

import algo.Login;
import algo.Teacher;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TLoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";

	public TLoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setLocation(300, 150);
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setTitle("Teacher Login");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_1 = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "t.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		label_1.setBounds(221, 11, 89, 70);
		contentPane.add(label_1);

		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrontFrame ff = new FrontFrame();
				ff.setVisible(true);
				dispose();
			}
		});
		button.setBounds(427, 428, 89, 23);
		contentPane.add(button);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Enter", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(160, 154, 472, 176);
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
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					Teacher t = new Teacher();
					ResultSet rs = t.signin(textField.getText().toString(),
							passwordField.getText().toString());
					
					try {	
						if (rs.last()!= false) {
							rs.beforeFirst();
							TeacherRollFrame trf = new TeacherRollFrame(rs);
							trf.setVisible(true);
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
		/*
		 * textField.addKeyListener(new KeyAdapter() {
		 * 
		 * private String text=" "; public void keyPressed(KeyEvent arg0) {
		 * lblInvalidInput.setVisible(false); Messages msg=new Messages(); if
		 * ((arg0.getKeyCode() < 47 || arg0.getKeyCode() > 58) &&
		 * (arg0.getKeyCode() < 96 || arg0.getKeyCode() > 105)) { if
		 * (arg0.getKeyCode() != 8) {
		 * msg.Failure("Invalid Input Try Only Digits", "Wrong Input");
		 * //lblInvalidInput.setText("Invalid Input");
		 * //lblInvalidInput.setVisible(true); if (textField.getText().length()
		 * > 0) { if (!textField.getText().isEmpty()) { textField.setText(text);
		 * } }
		 * 
		 * } } text = textField.getText(); } });
		 */
		textField.setBounds(156, 64, 204, 20);
		panel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					Teacher t = new Teacher();
					ResultSet rs = t.signin(textField.getText().toString(),
							passwordField.getText().toString());
					try {
						if (rs.last()!= false) {
							rs.beforeFirst();
							TeacherRollFrame trf = new TeacherRollFrame(rs);
							trf.setVisible(true);
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
		passwordField.setBounds(156, 93, 204, 20);
		panel.add(passwordField);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(134, 67, 46, 14);
		panel.add(lblId);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(91, 96, 63, 14);
		panel.add(lblPassword);
		JButton btnEnter = new JButton("Login");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Teacher t = new Teacher();
				ResultSet rs = t.signin(textField.getText().toString(),
						passwordField.getText().toString());
				try {
					if (rs.last()!= false) {
						rs.beforeFirst();
						TeacherRollFrame trf = new TeacherRollFrame(rs);
						trf.setVisible(true);
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
		btnEnter.setBounds(332, 428, 89, 23);
		contentPane.add(btnEnter);

		JLabel lblTeacherLogin = new JLabel("Teacher Login");
		lblTeacherLogin.setForeground(Color.BLACK);
		lblTeacherLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeacherLogin.setBounds(320, 40, 138, 21);
		contentPane.add(lblTeacherLogin);

		JSeparator separator = new JSeparator();
		separator.setBounds(305, 63, 160, 2);
		contentPane.add(separator);

		JLabel label = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "login.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		label.setBounds(461, 11, 84, 70);
		contentPane.add(label);

	}
}
