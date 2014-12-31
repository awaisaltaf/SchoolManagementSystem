package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JSeparator;

import algo.Login;
import algo.Teacher;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.border.TitledBorder;

import org.omg.CORBA.PRIVATE_MEMBER;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblNewPassword;
	private JLabel lblRetypePassword;
	private String Str = "";
	private JLabel lblNewLabel_1;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	private JPanel panel;
	private int lid;

	public ChangePassword(ResultSet CSSUB) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Change Password");
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());

		setBounds(100, 100, 440, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Enter", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(43, 61, 338, 142);
		contentPane.add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(128, 28, 172, 20);
		panel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(128, 63, 172, 20);
		panel.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(128, 95, 172, 20);
		panel.add(passwordField_1);
		passwordField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblNewLabel_1.setVisible(false);
				passwordField_1.setForeground(Color.BLACK);
			}
		});
		passwordField_1.setForeground(Color.BLACK);

		JLabel lblNewLabel = new JLabel("Old Password:");
		lblNewLabel.setBounds(30, 28, 103, 20);
		panel.add(lblNewLabel);

		lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setBounds(29, 63, 104, 20);
		panel.add(lblNewPassword);

		lblRetypePassword = new JLabel("Retype Password:");
		lblRetypePassword.setBounds(18, 95, 115, 20);
		panel.add(lblRetypePassword);

		lblNewLabel_1 = new JLabel(Str);
		lblNewLabel_1.setBounds(137, 115, 147, 14);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(Color.RED);

		try {
			while (CSSUB.next()) {
				lid = CSSUB.getInt("Lid");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				if (passwordField.getText().matches(passwordField_1.getText())) {

					Teacher t = new Teacher();
					Login lg = new Login();
					Messages msg = new Messages();
					boolean flag;
					System.out.println("ccx"+lid);
					flag = lg.ChangePassword(lid, textField.getText(),
							passwordField.getText(), passwordField_1.getText());
					if (flag == true) {
						msg.success("Password Successfully Changed",
								"Password Changed");
					} else {
						msg.Failure("Old Password is not correct", "Failure");
					}

				} else {

					Str = "*Mismatch Password";
					lblNewLabel_1.setText(Str);
					lblNewLabel_1.setVisible(true);
					passwordField_1.setForeground(Color.RED);

				}

			}
		});
		btnChange.setBounds(131, 228, 89, 23);
		contentPane.add(btnChange);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnBack.setBounds(230, 228, 89, 23);
		contentPane.add(btnBack);

		JLabel lblPasswordChange = new JLabel("Password Change");
		lblPasswordChange.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPasswordChange.setBounds(127, 9, 161, 32);
		contentPane.add(lblPasswordChange);

		JSeparator separator = new JSeparator();
		separator.setBounds(95, 37, 210, 20);
		contentPane.add(separator);

	}
}
