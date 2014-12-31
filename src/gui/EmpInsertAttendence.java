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

import algo.Admin;
import algo.Employee;
import algo.EmployeeAttendence;
import algo.StudentAttendence;

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

public class EmpInsertAttendence extends JFrame {

	private StuInsertAttendence frame;
	private StuInsertAttendence f;
	private boolean NewFrame;
	private ImageIcon icon;
	private boolean r = false;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";

	public EmpInsertAttendence(Employee empObj) {
		this.f = frame;
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setLocation(300, 150);
		setResizable(false);
		setTitle("Attendence");
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel iconlable = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "add.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		iconlable.setBounds(251, 3, 76, 60);
		getContentPane().add(iconlable);

		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 148, 774, 269);
		getContentPane().add(scrollPane);

		// Table
		final JTable table = new JTable();
		scrollPane.setViewportView(table);

		// Model for Table
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 2) {
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
		panel_1.setBounds(308, 62, 476, 76);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		DatePanel datePanel = new DatePanel();
		datePanel.setBounds(68, 26, 212, 33);
		panel_1.add(datePanel);
		ButtonGroup btngrp = new ButtonGroup();
		JRadioButton rdbtnAllA = new JRadioButton("All Absent");
		rdbtnAllA.setBounds(356, 41, 95, 23);
		panel_1.add(rdbtnAllA);
		rdbtnAllA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < table.getRowCount(); i++) {
					model.setValueAt("A", i, 2);
				}
			}

		});
		btngrp.add(rdbtnAllA);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(32, 37, 46, 14);
		panel_1.add(lblDate);

		JRadioButton rdbtnAllP = new JRadioButton("All Present");
		rdbtnAllP.setBounds(355, 22, 100, 23);
		panel_1.add(rdbtnAllP);
		rdbtnAllP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < table.getRowCount(); i++) {
					model.setValueAt("P", i, 2);
				}
			}
		});

		btngrp.add(rdbtnAllP);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		Admin admin = new Admin();
		ResultSet rs = admin.getEmployee(empObj);

		model.addColumn("Reg_No");
		model.addColumn("Name");
		model.addColumn("Status");
		// Data Row
		try {
			int i = 0;
			while (rs.next()) {
				model.addRow(new Object[4]);
				model.setValueAt(rs.getInt("EReg_no"), i, 0);
				model.setValueAt(rs.getString("Name"), i, 1);
				model.setValueAt("A", i, 2);
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
		TableColumn Column = table.getColumnModel().getColumn(2);
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
					try {
						List ls = new List();
						EmployeeAttendence empAtt1 = new EmployeeAttendence();
						Admin ad = new Admin();
						Date date;
						Employee emp = new Employee();
						int check = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							int reg = (int) model.getValueAt(i, 0);
							String status = table.getValueAt(i, 2).toString();
							date = emp.getEmployee(reg);
							if (datePanel.getDateObj().after(date)) {
								EmployeeAttendence empAtt = new EmployeeAttendence();
								check++;
								empAtt.setDate(datePanel);
								empAtt.setStatus(status);
								emp.setRegNo(reg);
								r = ad.InsertAttendence(emp, empAtt);
								empAtt.AttendenceCountPercentage(emp);
								ls = empAtt1.attendencecount(status);
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
											+ "\nPresent :    " + ls.getItem(0)
											+ "\nAbsent :     " + ls.getItem(1)
											+ "\nOn Leave :  " + ls.getItem(2),
									"Attendence Marked");
							EmployeeAttendence empAtt2 = new EmployeeAttendence();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnGetRowSelected.setBounds(329, 438, 95, 23);
		getContentPane().add(btnGetRowSelected);

		JLabel lblNewLabel = new JLabel("New Attendence");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(323, 24, 223, 20);
		getContentPane().add(lblNewLabel);

		JButton btnCancle = new JButton("Back");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmpAttendenceFrame empframe = new EmpAttendenceFrame();
				empframe.setVisible(true);
				dispose();
			}
		});
		btnCancle.setBounds(436, 438, 95, 23);
		getContentPane().add(btnCancle);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 62, 290, 76);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Total Strength:");
		lblNewLabel_3.setBounds(70, 33, 85, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(Integer.toString(table.getRowCount()));
		lblNewLabel_4.setBounds(165, 32, 46, 14);
		panel.add(lblNewLabel_4);

		JSeparator separator = new JSeparator();
		separator.setBounds(293, 46, 223, 2);
		getContentPane().add(separator);
	}
}
