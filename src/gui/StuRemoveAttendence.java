package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import algo.Student;
import algo.StudentAttendence;
import algo.Teacher;
import algo.classSection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

public class StuRemoveAttendence extends JFrame {

	private JPanel contentPane;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	private String sec = "";
	private String Class = "";
	private ResultSet CS;
	private int Lid;

	public StuRemoveAttendence(ResultSet CSSUB) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setBounds(100, 100, 800, 500);
		setLocation(300, 150);
		setResizable(false);
		setTitle("Remove Attendence");
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
					csid = 0;
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
				+ "remove.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel.setBounds(218, 11, 73, 60);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Select", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(170, 145, 424, 176);
		contentPane.add(panel);
		panel.setLayout(null);

		DatePanel datePanel = new DatePanel();
		datePanel.setBounds(126, 71, 212, 33);
		panel.add(datePanel);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(84, 82, 46, 14);
		panel.add(lblDate);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentAttendence stuAtt = new StudentAttendence();
				Messages msg = new Messages();
				stuAtt.setDate(datePanel);
				ResultSet rs, rs1;
				rs = stuAtt.view(datePanel);
				rs1 = stuAtt.studentSearch(Class, sec);
				String Date = Integer.toString(datePanel.getDate());
				try {
					int regno;
					String status = " ";
					int i = 0;
					int check = -1;
					while (rs1.next()) {
						if (i != 0) {
							rs.first();
						}
						while (rs.next()) {
							if (rs1.getInt("Reg_no") == rs.getInt("Reg_no")) {
								if (!rs.getString(Date).isEmpty()) {
									Student stuObj=new Student();//
									StudentAttendence stuAtt1 = new StudentAttendence();
									stuAtt1.setDate(datePanel);
									regno = rs.getInt("Reg_no");
									stuObj.setRegNo(regno);
									stuAtt.updateAttendence(
											Integer.toString(regno), status);
									stuAtt1.AttendenceCountPercentage(stuObj);
									check++;
								} else {
									check = -1;
								}
							}
							i++;
						}
					}

					if (check > 0) {
						msg.success("Attendence Successfully Removed",
								"Removed");
					} else if (check < 0) {
						msg.Failure("Attendence Already UnMarked", "Note");
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRemove.setBounds(304, 428, 89, 23);
		contentPane.add(btnRemove);

		JButton btnCancel = new JButton("Back");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StuAttendenceFrame stuAttFrm = new StuAttendenceFrame(CSSUB);
				stuAttFrm.setVisible(true);
				dispose();
			}
		});
		btnCancel.setBounds(403, 428, 89, 23);
		contentPane.add(btnCancel);
		JLabel lblRemoveAttendence = new JLabel("Remove Attendence");
		lblRemoveAttendence.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRemoveAttendence.setBounds(312, 23, 188, 23);
		contentPane.add(lblRemoveAttendence);

		JSeparator separator = new JSeparator();
		separator.setBounds(286, 47, 239, 2);
		contentPane.add(separator);
	}
}
