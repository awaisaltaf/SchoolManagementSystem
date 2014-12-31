package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JSeparator;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JButton;

import db.EmployeeAttendenceDB;
import algo.Employee;
import algo.EmployeeAttendence;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import javax.swing.border.TitledBorder;

public class EmpAttendenceFrame extends JFrame {

	private JPanel contentPane;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	private ResultSet rs;
	private String Designation;

	public EmpAttendenceFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setTitle("Employee Attendence");
		setBounds(100, 100, 800, 500);
		setLocation(300, 150);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "emp.png")).getImage()).getScaledInstance(70, 70,
				java.awt.Image.SCALE_SMOOTH)));
		label.setBounds(185, 1, 89, 74);
		contentPane.add(label);

		JSeparator separator = new JSeparator();
		separator.setBounds(253, 50, 269, 2);
		contentPane.add(separator);

		JLabel lblEmployeeAttendence = new JLabel("Employee Attendence");
		lblEmployeeAttendence.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmployeeAttendence.setBounds(283, 25, 234, 22);
		contentPane.add(lblEmployeeAttendence);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Select", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(168, 150, 444, 215);
		contentPane.add(panel);
		panel.setLayout(null);
		JComboBox comboBox = new JComboBox();
		EmployeeAttendence empAtt = new EmployeeAttendence();
		try {

			ResultSet rs = empAtt.Designations();
			while (rs.next()) {
				String D = rs.getString("Designation").toString();
				comboBox.addItem(D);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		comboBox.setBounds(173, 47, 170, 18);
		panel.add(comboBox);

		JLabel lblEmployee = new JLabel("Employee:");
		lblEmployee.setBounds(113, 49, 74, 14);
		panel.add(lblEmployee);
		JButton btnNewButton = new JButton(new ImageIcon(((new ImageIcon(
				address + "add.png")).getImage()).getScaledInstance(40, 40,
				java.awt.Image.SCALE_SMOOTH)));
		btnNewButton.setBounds(113, 111, 50, 50);
		panel.add(btnNewButton);

		JButton button = new JButton(new ImageIcon(((new ImageIcon(address
				+ "update.png")).getImage()).getScaledInstance(40, 40,
				java.awt.Image.SCALE_SMOOTH)));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (comboBox.getSelectedItem().toString().matches("Teacher")) {
					Designation = comboBox.getSelectedItem().toString();
					Employee emp = new Employee();
					emp.setDesignation(Designation);
					EmpUpdateAttendence update = new EmpUpdateAttendence(emp);
					update.setVisible(true);
				} else if (comboBox.getSelectedItem().toString()
						.matches("Accountant")) {
					Designation = comboBox.getSelectedItem().toString();
					Employee emp = new Employee();
					emp.setDesignation(Designation);
					EmpUpdateAttendence update = new EmpUpdateAttendence(emp);
					update.setVisible(true);
				} else if (comboBox.getSelectedItem().toString()
						.matches("Admin")) {
					Designation = comboBox.getSelectedItem().toString();
					Employee emp = new Employee();
					emp.setDesignation(Designation);
					EmpUpdateAttendence update = new EmpUpdateAttendence(emp);
					update.setVisible(true);
				} else if (comboBox.getSelectedItem().toString()
						.matches("Peon")) {
					Designation = comboBox.getSelectedItem().toString();
					Employee emp = new Employee();
					emp.setDesignation(Designation);
					EmpUpdateAttendence update = new EmpUpdateAttendence(emp);
					update.setVisible(true);
				}

				dispose();
			}
		});
		button.setBounds(173, 111, 50, 50);
		panel.add(button);

		JButton button_1 = new JButton(new ImageIcon(((new ImageIcon(address
				+ "view.png")).getImage()).getScaledInstance(40, 40,
				java.awt.Image.SCALE_SMOOTH)));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (comboBox.getSelectedItem().toString().matches("Teacher")) {
					Designation = comboBox.getSelectedItem().toString();
					Employee emp = new Employee();
					emp.setDesignation(Designation);
					EmpViewAttendence empView = new EmpViewAttendence(emp);
					empView.setVisible(true);
				} else if (comboBox.getSelectedItem().toString()
						.matches("Accountant")) {
					Designation = comboBox.getSelectedItem().toString();
					Employee emp = new Employee();
					emp.setDesignation(Designation);
					EmpViewAttendence empView = new EmpViewAttendence(emp);
					empView.setVisible(true);
				} else if (comboBox.getSelectedItem().toString()
						.matches("Admin")) {
					Designation = comboBox.getSelectedItem().toString();
					Employee emp = new Employee();
					emp.setDesignation(Designation);
					EmpViewAttendence empView = new EmpViewAttendence(emp);
					empView.setVisible(true);
				} else if (comboBox.getSelectedItem().toString()
						.matches("Peon")) {
					Designation = comboBox.getSelectedItem().toString();
					Employee emp = new Employee();
					emp.setDesignation(Designation);
					EmpViewAttendence empView = new EmpViewAttendence(emp);
					empView.setVisible(true);
				}

				dispose();
			}
		});
		button_1.setBounds(233, 111, 50, 50);
		panel.add(button_1);

		JButton button_2 = new JButton(new ImageIcon(((new ImageIcon(address
				+ "remove.png")).getImage()).getScaledInstance(40, 40,
				java.awt.Image.SCALE_SMOOTH)));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Designation = comboBox.getSelectedItem().toString();
				Employee emp = new Employee();
				emp.setDesignation(Designation);
				EmpRemoveAttendence empRemAtt = new EmpRemoveAttendence(emp);
				empRemAtt.setVisible(true);
				dispose();
			}
		});
		button_2.setBounds(293, 111, 50, 50);
		panel.add(button_2);
		
		JLabel lblNew = new JLabel("New");
		lblNew.setBounds(126, 164, 46, 14);
		panel.add(lblNew);
		
		JLabel lblUpdate = new JLabel("Update");
		lblUpdate.setBounds(186, 164, 46, 14);
		panel.add(lblUpdate);
		
		JLabel lblView = new JLabel("View");
		lblView.setBounds(243, 164, 46, 14);
		panel.add(lblView);
		
		JLabel lblRemove = new JLabel("Remove");
		lblRemove.setBounds(300, 164, 46, 14);
		panel.add(lblRemove);

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().toString().matches("Teacher")) {
					Designation = comboBox.getSelectedItem().toString();
					Employee emp = new Employee();
					emp.setDesignation(Designation);
					EmpInsertAttendence InsAtt = new EmpInsertAttendence(emp);
					InsAtt.setVisible(true);
				} else if (comboBox.getSelectedItem().toString()
						.matches("Accountant")) {
					Designation = comboBox.getSelectedItem().toString();
					Employee emp = new Employee();
					emp.setDesignation(Designation);
					EmpInsertAttendence InsAtt = new EmpInsertAttendence(emp);
					InsAtt.setVisible(true);
				} else if (comboBox.getSelectedItem().toString()
						.matches("Admin")) {
					Designation = comboBox.getSelectedItem().toString();
					Employee emp = new Employee();
					emp.setDesignation(Designation);
					EmpInsertAttendence InsAtt = new EmpInsertAttendence(emp);
					InsAtt.setVisible(true);
				} else if (comboBox.getSelectedItem().toString()
						.matches("Peon")) {
					Designation = comboBox.getSelectedItem().toString();
					Employee emp = new Employee();
					emp.setDesignation(Designation);
					EmpInsertAttendence InsAtt = new EmpInsertAttendence(emp);
					InsAtt.setVisible(true);
				}
				dispose();
			}
		});
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}

		};
		model.addColumn("Reg ID");
		model.addColumn("Name");

		JButton btnCancel = new JButton("Back");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminRole AR = new AdminRole();
				AR.setVisible(true);
				dispose();
			}
		});
		btnCancel.setBounds(695, 438, 89, 23);
		contentPane.add(btnCancel);
	}
}
