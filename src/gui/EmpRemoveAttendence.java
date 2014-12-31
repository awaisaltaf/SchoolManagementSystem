package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import algo.Employee;
import algo.EmployeeAttendence;
import algo.StudentAttendence;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

public class EmpRemoveAttendence extends JFrame {

	private JPanel contentPane;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";

	public EmpRemoveAttendence(Employee empObj) {
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

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Select", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(191, 150, 423, 194);
		contentPane.add(panel);
		panel.setLayout(null);

		DatePanel datePanel = new DatePanel();
		datePanel.setBounds(123, 81, 212, 33);
		panel.add(datePanel);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(81, 92, 46, 14);
		panel.add(lblDate);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Employee emp=new Employee();
				Messages msg = new Messages();
				EmployeeAttendence empAtt=new EmployeeAttendence();
				List ls=empAtt.searchTeachers(datePanel, empObj);
				String regno;
				String status = " ";
				if (ls.getItemCount()==0) {
					msg.Failure("Attendence Already UnMarked", "Note");
				} else {
					int i=0,j=0;
					while (i < (ls.getItemCount() / 3)) {
						EmployeeAttendence empAtt1=new EmployeeAttendence();
						emp.setRegNo(Integer.parseInt(ls.getItem(j)));
						empAtt1.setStatus(" ");
						empAtt1.setDate(datePanel);
						empAtt1.UpdateAttendence(emp);
						empAtt1.AttendenceCountPercentage(emp);
						j=j+3;
						i++;
					}
					msg.success("Attendence Successfully Removed",
							"Removed");
				}
			}
		});
		btnRemove.setBounds(301, 438, 89, 23);
		contentPane.add(btnRemove);

		JButton btnCancel = new JButton("Back");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpAttendenceFrame empAttFrame=new EmpAttendenceFrame();
				empAttFrame.setVisible(true);
				dispose();
			}
		});
		btnCancel.setBounds(400, 438, 89, 23);
		contentPane.add(btnCancel);

		JLabel lblRemoveAttendence = new JLabel("Remove Attendence");
		lblRemoveAttendence.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRemoveAttendence.setBounds(321, 29, 188, 23);
		contentPane.add(lblRemoveAttendence);

		JSeparator separator = new JSeparator();
		separator.setBounds(301, 51, 222, 2);
		contentPane.add(separator);

		JLabel lblNewLabel = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "remove.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		lblNewLabel.setBounds(242, 10, 70, 60);
		contentPane.add(lblNewLabel);
	}
}
