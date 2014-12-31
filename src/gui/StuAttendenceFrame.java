package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import algo.Employee;
import algo.StudentAttendence;
import algo.Teacher;
import algo.classSection;

import java.awt.Color;

import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;
import javax.swing.Icon;

public class StuAttendenceFrame extends JFrame {

	public StuAttendenceFrame mf;
	public JPanel contentPane;
	private StuInsertAttendence af;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	private String Class = "";
	private String sec = "";
	private ResultSet CS;
	private int Lid;

	public StuAttendenceFrame(ResultSet CSSUB) {
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setBounds(100, 100, 800, 500);
		setTitle("Student Attendence Module");
		setLocation(300, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Lid = 0;
		int csid = 0;
		ResultSet checkcs;
		Teacher tea = new Teacher();
		classSection cs = new classSection();
		try {
			while (CSSUB.next()) {
				Lid = CSSUB.getInt("Lid");
				csid = CSSUB.getInt("CSid");
				checkcs = tea.checkIncharge(Lid, csid);
				if (checkcs.last() != false) {
					checkcs.first();
					csid = checkcs.getInt("CSid");
					break;
				} else {
					csid=0;
				}
			}
			CS = cs.getClassSection(csid);
			while (CS.next()) {
				Class = Integer.toString(CS.getInt("clas"));
				sec = CS.getString("section").toString();
			}
			CSSUB.beforeFirst();
		} catch (Exception e) {
			// TODO: handle exception
		}
		JLabel lblNewLabel = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "ScAtt.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel.setBounds(195, 0, 80, 80);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Choose", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(161, 138, 481, 228);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton(new ImageIcon(((new ImageIcon(
				address + "add.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		btnNewButton.setBounds(61, 67, 80, 80);
		panel.add(btnNewButton);

		JButton btnView = new JButton(new ImageIcon(((new ImageIcon(address
				+ "view.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		btnView.setBounds(241, 67, 80, 80);
		panel.add(btnView);

		JButton btnUpdateAttendence = new JButton(
				new ImageIcon(
						((new ImageIcon(address + "update.png")).getImage())
								.getScaledInstance(60, 60,
										java.awt.Image.SCALE_SMOOTH)));
		btnUpdateAttendence.setBounds(151, 67, 80, 80);
		panel.add(btnUpdateAttendence);

		JLabel lblAdd = new JLabel("New");
		lblAdd.setBounds(85, 147, 46, 14);
		panel.add(lblAdd);
		lblAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblUpdate = new JLabel("Update");
		lblUpdate.setBounds(171, 147, 46, 14);
		panel.add(lblUpdate);
		lblUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblView = new JLabel("View");
		lblView.setBounds(266, 147, 46, 14);
		panel.add(lblView);
		lblView.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton button = new JButton(new ImageIcon(((new ImageIcon(address
				+ "remove.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		button.setBounds(331, 67, 80, 80);
		panel.add(button);

		JLabel lblRemove = new JLabel("Remove");
		lblRemove.setBounds(341, 147, 59, 14);
		panel.add(lblRemove);
		lblRemove.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CSSUB.beforeFirst();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				StuRemoveAttendence StuRemAtt = new StuRemoveAttendence(CSSUB);
				StuRemAtt.setVisible(true);
				dispose();
			}
		});
		btnUpdateAttendence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CSSUB.beforeFirst();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				StuUpdateAttendence upatt = new StuUpdateAttendence(CSSUB);
				upatt.setVisible(true);
				dispose();
			}
		});
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CSSUB.beforeFirst();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				StuAttendenceView attview = new StuAttendenceView(CSSUB);
				attview.setVisible(true);
				dispose();

			}
		});
		btnNewButton.addActionListener(new ActionListener() {

			private ImageIcon icon;

			public void actionPerformed(ActionEvent e) {
				try {
					CSSUB.beforeFirst();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				af = new StuInsertAttendence(CSSUB);
				af.setVisible(true);
				dispose();

			}
		});

		JButton btnCancle = new JButton("Back");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeacherRollFrame trf = new TeacherRollFrame(CSSUB);
				trf.setVisible(true);
				dispose();
			}
		});
		btnCancle.setBounds(695, 438, 89, 23);
		contentPane.add(btnCancle);

		JLabel lblStudentAttendence = new JLabel("Student Attendence ");
		lblStudentAttendence.setForeground(Color.BLACK);
		lblStudentAttendence.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentAttendence.setBounds(281, 11, 219, 44);
		contentPane.add(lblStudentAttendence);

		JSeparator separator = new JSeparator();
		separator.setBounds(240, 46, 277, 2);
		contentPane.add(separator);

	}
}
