package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.List;
import java.net.*;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TabExpander;
import javax.swing.JButton;

import algo.Student;
import algo.StudentAttendence;
import algo.Teacher;
import algo.classSection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class StuInsertAttendence extends JFrame {

	private StuInsertAttendence frame;
	private StuInsertAttendence f;
	private boolean NewFrame;
	private ImageIcon icon;
	private boolean r = false;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	private String sec = "";
	private String Class = "";
	private ResultSet CS;
	private int Lid;

	public StuInsertAttendence(ResultSet CSSUB) {
		this.f = frame;
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setTitle("Attendence");
		setLocation(300, 150);
		setResizable(false);
		getContentPane().setLayout(null);

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
		JLabel iconlable = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "add.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		iconlable.setBounds(217, 4, 82, 60);
		getContentPane().add(iconlable);

		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 153, 774, 264);
		getContentPane().add(scrollPane);

		// Table
		final JTable table = new JTable();
		scrollPane.setViewportView(table);

		// Model for Table
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 3) {
					return true;

				} else {
					return false;
				}
			}
		};
		table.setModel(model);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Select",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(350, 66, 434, 76);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		DatePanel datePanel = new DatePanel();
		datePanel.setBounds(68, 22, 212, 33);
		panel_1.add(datePanel);
		ButtonGroup btngrp = new ButtonGroup();
		JRadioButton rdbtnAllA = new JRadioButton("All Absent");
		rdbtnAllA.setBounds(318, 40, 95, 23);
		panel_1.add(rdbtnAllA);
		rdbtnAllA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < table.getRowCount(); i++) {
					model.setValueAt("A", i, 3);
				}
			}

		});
		btngrp.add(rdbtnAllA);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(30, 33, 46, 14);
		panel_1.add(lblDate);

		JRadioButton rdbtnAllP = new JRadioButton("All Present");
		rdbtnAllP.setBounds(318, 18, 100, 23);
		panel_1.add(rdbtnAllP);
		rdbtnAllP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < table.getRowCount(); i++) {
					model.setValueAt("P", i, 3);
				}
			}
		});

		btngrp.add(rdbtnAllP);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		model.addColumn("Reg_No");
		model.addColumn("Roll No");
		model.addColumn("Name");
		model.addColumn("Status");
		// Data Row

		StudentAttendence Stuatt = new StudentAttendence();
		Student stu = new Student();
		Teacher t = new Teacher();
		ResultSet rs = t.searchstudents(Class, sec);
		try {
			int i = 0;
			while (rs.next()) {
				model.addRow(new Object[4]);
				model.setValueAt(rs.getInt("Reg_no"), i, 0);
				model.setValueAt(rs.getInt("Roll_no"), i, 1);
				model.setValueAt(rs.getString("Name"), i, 2);
				model.setValueAt("A", i, 3);
				i++;
			}
			for (int x = 0; x < table.getColumnCount(); x++) {
				table.getColumnModel().getColumn(x)
						.setCellRenderer(centerRenderer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] dataItem = { "A", "P", "L" };
		JComboBox Combo = new JComboBox(dataItem);
		((JLabel) Combo.getRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumn Column = table.getColumnModel().getColumn(3);
		Column.setCellEditor(new DefaultCellEditor(Combo));

		// Get Row Selected
		JButton btnGetRowSelected = new JButton("OK");
		btnGetRowSelected.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				CurrentDate currentdate = new CurrentDate();
				Messages msg = new Messages();
				Date todaydate = new Date();
				if (datePanel.getDateObj().after(todaydate)) {

					msg.Failure("In Advance Attendence Can't Be Marked",
							"Warning");

				} else {

					StudentAttendence stuAtt1 = new StudentAttendence();
					Teacher t = new Teacher();
					Student stu = new Student();
					Date date;
					List ls = new List();
					try {
						int check = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							int regno = (int) model.getValueAt(i, 0);
							String status = table.getValueAt(i, 3).toString();
							date = stu.getStudentRegistration(regno);
							if (datePanel.getDateObj().after(date)) {
								check++;
								StudentAttendence stuAtt = new StudentAttendence();
								stu.setRegNo(regno);
								stuAtt.setStatus(status);
								stuAtt.setDate(datePanel);
								r = t.InsertAttendence(stu, stuAtt);
								stuAtt.AttendenceCountPercentage(stu);
								ls = stuAtt1.attendencecount(status);
							}
						}
						if (r == false) {
							if (check > 0) {
								msg.Failure("Attendence Already Marked", "Note");
							} else {
								msg.Failure(
										"All Student are registered after this Date",
										"Note");
							}
						} else {
							msg.success(
									"Attendence Successfully Marked              "
											+ "\nPresent Students:  "
											+ ls.getItem(0)
											+ "\nAbsent Students:    "
											+ ls.getItem(1)
											+ "\nStudents on leave:  "
											+ ls.getItem(2),
									"Attendence Marked");
							StudentAttendence newatt = new StudentAttendence();
						}

					}

					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnGetRowSelected.setBounds(295, 438, 95, 23);
		getContentPane().add(btnGetRowSelected);

		JLabel lblNewLabel = new JLabel("Student Attendence");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(295, 21, 247, 29);
		getContentPane().add(lblNewLabel);

		JButton btnCancle = new JButton("Back");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				StuAttendenceFrame a = new StuAttendenceFrame(CSSUB);
				a.setVisible(true);
			}
		});
		btnCancle.setBounds(402, 438, 95, 23);
		getContentPane().add(btnCancle);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 66, 330, 76);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblClass = new JLabel("Class:");
		lblClass.setBounds(108, 50, 46, 14);
		panel.add(lblClass);

		JLabel lblNewLabel_1 = new JLabel(Class);
		lblNewLabel_1.setBounds(162, 50, 100, 14);
		panel.add(lblNewLabel_1);

		JLabel lblSection = new JLabel("Section:");
		lblSection.setBounds(99, 31, 46, 14);
		panel.add(lblSection);

		JLabel lblNewLabel_2 = new JLabel(sec);
		lblNewLabel_2.setBounds(162, 30, 100, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Total Strength:");
		lblNewLabel_3.setBounds(69, 12, 85, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(Integer.toString(table.getRowCount()));
		lblNewLabel_4.setBounds(164, 11, 46, 14);
		panel.add(lblNewLabel_4);

		JSeparator separator = new JSeparator();
		separator.setBounds(271, 47, 247, 2);
		getContentPane().add(separator);

	}
}
