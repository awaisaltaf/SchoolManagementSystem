package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import algo.Employee;
import algo.Subject;
import algo.Teacher;
import algo.classSection;

public class TeacherRollFrame extends JFrame {

	private JPanel contentPane;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	String regno;
	String Name;
	private String incharge = "";
	private String Class = "    ";
	private ResultSet CS;
	private int Lid;
	private JLabel label;

	public TeacherRollFrame(ResultSet CSSUB) {
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setTitle("Teacher WorkSpace");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setLocation(300, 150);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Lid = 0;
		int csid = 0;
		ResultSet checkcs = null;
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
					csid = 0;
				}
			}
			CSSUB.beforeFirst();
		} catch (Exception e) {
			// TODO: handle exception
		}
		ResultSet rs;
		Employee emp = new Employee();
		rs = emp.employeeInfo(Lid);
		try {
			rs.next();
			regno = rs.getString("EReg_no");
			Name = rs.getString("Name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JLabel lblLogout = new JLabel("Logout");
		lblLogout.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblLogout.setForeground(Color.BLACK);
		lblLogout.setBounds(736, 49, 46, 14);
		contentPane.add(lblLogout);
		JLabel lblNewLabel = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "t.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(217, 0, 86, 67);
		contentPane.add(lblNewLabel);

		JLabel lblTeacherWorkSpace = new JLabel("Teacher Work Space");
		lblTeacherWorkSpace.setForeground(Color.BLACK);
		lblTeacherWorkSpace.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeacherWorkSpace.setBounds(313, 26, 231, 22);
		contentPane.add(lblTeacherWorkSpace);

		JButton btnBack = new JButton(new ImageIcon(((new ImageIcon(address
				+ "logout.png")).getImage()).getScaledInstance(40, 40,
				java.awt.Image.SCALE_SMOOTH)));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrontFrame ff = new FrontFrame();
				ff.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(738, 18, 30, 30);
		contentPane.add(btnBack);

		JSeparator separator = new JSeparator();
		separator.setBounds(283, 50, 254, 2);
		contentPane.add(separator);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Module", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(176, 217, 438, 214);
		contentPane.add(panel);
		panel.setLayout(null);

		label = new JLabel("Attendence");
		label.setBounds(233, 128, 68, 14);
		panel.add(label);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label_1 = new JLabel("Acadamics");
		label_1.setBounds(143, 128, 68, 14);
		panel.add(label_1);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton button = new JButton(new ImageIcon(((new ImageIcon(address
				+ "ScAtt.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StuAttendenceFrame mf = new StuAttendenceFrame(CSSUB);
				mf.setVisible(true);
				dispose();
			}
		});
		button.setBounds(227, 49, 80, 80);
		button.setEnabled(false);
		panel.add(button);

		JButton button_1 = new JButton(new ImageIcon(((new ImageIcon(address
				+ "acadamic.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				classSection objClassSection = new classSection();
				Subject objSubject = new Subject();

				int subject = 0;
				int classAndSection = 0;
				int Tid = 0;
				String temp = null;
				String temp_1 = null;
				String temp_2 = null;
				List[] ls = new List[3];
				ls[0] = new List();
				ls[1] = new List();
				ls[2] = new List();
				try {
					CSSUB.beforeFirst();
					while (CSSUB.next()) {
						classAndSection = CSSUB.getInt("CSid");
						subject = CSSUB.getInt("Sub_id");
						Tid = CSSUB.getInt("EReg_no");
						temp = Integer.toString(classAndSection);
						temp_1 = Integer.toString(subject);
						temp_2 = Integer.toString(Tid);
						ls[0].add(temp);
						ls[1].add(temp_1);
						ls[2].add(temp_2);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int i = 0; i < temp.length(); i++) {
					System.out.println("CS " + ls[0].getItem(i));
				}
				for (int i = 0; i < temp_1.length(); i++) {
					System.out.println("S " + ls[1].getItem(i));
				}

				List[] lsClassSecSub = new List[3];
				lsClassSecSub[0] = new List();
				lsClassSecSub[1] = new List();
				lsClassSecSub[2] = new List();
				lsClassSecSub[2] = ls[2];
				for (int i = 0; i < ls[0].getItemCount(); i++) {

					lsClassSecSub[0].add(objClassSection.showClassSection(Integer
							.parseInt(ls[0].getItem(i))));

				}
				for (int j = 0; j < ls[1].getItemCount(); j++) {
					lsClassSecSub[1].add(objSubject.subject(Integer
							.parseInt(ls[1].getItem(j))));

				}
				try {
					CSSUB.beforeFirst();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				showClassSection objShowClassSection = new showClassSection(
						lsClassSecSub[0], lsClassSecSub[1], lsClassSecSub[2],
						Lid, CSSUB);
				objShowClassSection.setVisible(true);
				dispose();
			}
		});
		button_1.setBounds(135, 49, 80, 80);
		panel.add(button_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Teacher info",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(112, 78, 563, 141);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(78, 71, 46, 14);
		panel_1.add(lblName);

		JLabel lblRegistrationId = new JLabel("Registration ID:");
		lblRegistrationId.setBounds(57, 48, 94, 14);
		panel_1.add(lblRegistrationId);

		JLabel lblClass = new JLabel("Class-Section");
		lblClass.setBounds(311, 46, 85, 14);
		panel_1.add(lblClass);

		JLabel lblinch = new JLabel("Incharge:");
		lblinch.setBounds(76, 96, 73, 14);
		panel_1.add(lblinch);

		if (csid != 0) {
			button.setEnabled(true);
			CS = cs.getClassSection(csid);
			try {

				while (CS.next()) {
					incharge = Integer.toString(CS.getInt("clas"));
					incharge = incharge + "-"
							+ CS.getString("section").toString();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			lblinch.setVisible(false);
		}

		JLabel lblNewLabel_3 = new JLabel(regno);
		lblNewLabel_3.setBounds(154, 48, 71, 14);
		panel_1.add(lblNewLabel_3);

		JLabel label_2 = new JLabel(Name);
		label_2.setBounds(152, 72, 105, 14);
		panel_1.add(label_2);

		int classsection;
		int Subject;
		Subject subObj = new Subject();
		try {
			while (CSSUB.next()) {
				classsection = CSSUB.getInt("CSid");
				CS = cs.getClassSection(classsection);
				while (CS.next()) {
					Class = Class + Integer.toString(CS.getInt("clas"));
					Class = Class
							+ "-"
							+ CS.getString("section").toString()
							+ " &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp ";
				}
				Subject = CSSUB.getInt("Sub_id");
				Class = Class + subObj.subject(Subject);
				Class = Class + "<br>";
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		JLabel label_4 = new JLabel(incharge);
		label_4.setBounds(159, 98, 57, 14);
		panel_1.add(label_4);

		JLabel label_3 = new JLabel("<html>" + Class + "<html>");
		label_3.setBounds(336, 51, 151, 74);
		panel_1.add(label_3);

		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(452, 11, 101, 14);
		panel_1.add(btnChangePassword);
		btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 8));

		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(400, 46, 46, 14);
		panel_1.add(lblSubject);
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CSSUB.beforeFirst();	
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				ChangePassword cp = new ChangePassword(CSSUB);
				cp.setVisible(true);
			}
		});

	}
}