package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JSeparator;
import javax.swing.Icon;

import db.EmployeeAttendenceDB;
import algo.Employee;
import algo.EmployeeAttendence;
import algo.StudentAttendence;
import javax.swing.border.TitledBorder;

public class EmpUpdateAttendence extends JFrame {

	private JPanel contentPane;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	private JTable table;
	private DefaultTableModel model;
	private boolean s;
	private JScrollPane scrollPane;
	private JComboBox Combo;
	private TableColumn Column;
	private JButton btnShow;
	private JButton button;
	private List lst;

	public EmpUpdateAttendence(Employee empObj) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon(address+"sms.png");
		setTitle("Updation");
		setIconImage(img.getImage());
		setBounds(100, 100, 800, 500);
		setLocation(300, 150);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_1 = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "update.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		label_1.setBounds(256, 5, 70, 59);
		contentPane.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Choose Date", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(99, 56, 607, 59);
		contentPane.add(panel);
		panel.setLayout(null);

		DatePanel datePanel = new DatePanel();
		datePanel.setBounds(169, 16, 212, 33);
		panel.add(datePanel);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(138, 26, 46, 14);
		panel.add(lblDate);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
				btnShow = new JButton("Show");
				btnShow.setBounds(391, 20, 74, 23);
				panel.add(btnShow);
				btnShow.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						int rowcount=table.getRowCount();
						for (int a= 0; a < rowcount; a++) {
							model.removeRow(0);
						}
						lst = new List();
						EmployeeAttendence empatt = new EmployeeAttendence();
						lst = empatt.searchTeachers(datePanel, empObj);
						int i = 0;
						int j = -1;
						while (i < (lst.getItemCount() / 3)) {
							model.addRow(new Object[4]);
							model.setValueAt(lst.getItem(j = j + 1), i, 0);
							model.setValueAt(lst.getItem(j = j + 1), i, 1);
							table.setValueAt(lst.getItem(j = j + 1), i, 2);
							i++;
						}
						for (int x = 0; x < table.getColumnCount(); x++) {
							table.getColumnModel().getColumn(x)
									.setCellRenderer(centerRenderer);
						}


					}
				});

		scrollPane = new JScrollPane();
		scrollPane.setEnabled(true);
		scrollPane.setBounds(10, 128, 774, 299);
		getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel() {
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

		model.addColumn("Reg_No");
		model.addColumn("Name");
		model.addColumn("Status");

		String[] dataItem = { "A", "P", "L" };
		Combo = new JComboBox(dataItem);
		((JLabel) Combo.getRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		Column = table.getColumnModel().getColumn(2);
		Column.setCellEditor(new DefaultCellEditor(Combo));

		JLabel label = new JLabel("Update Attendence");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(321, 18, 177, 20);
		contentPane.add(label);

		JSeparator separator = new JSeparator();
		separator.setBounds(297, 43, 242, 2);
		contentPane.add(separator);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmpAttendenceFrame empatt = new EmpAttendenceFrame();
				empatt.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(424, 438, 89, 23);
		contentPane.add(btnBack);

		button = new JButton("Update");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				Employee emp = new Employee();
				Messages msg = new Messages();

				for (int i = 0; i < table.getRowCount(); i++) {
					EmployeeAttendence empatt = new EmployeeAttendence();
					int regno = Integer.parseInt(model.getValueAt(i, 0)
							.toString());
					String status = table.getValueAt(i, 2).toString();
					emp.setRegNo(regno);
					empatt.setStatus(status);
					empatt.setDate(datePanel);					
					s = empatt.UpdateAttendence(emp);
					empatt.AttendenceCountPercentage(emp);
				}
				if (s == true) {
					msg.success("Attendence Updated", "Updated");
				} else {
					msg.Failure("Updation Failed", "Failed");

				}
			}
		});
		button.setBounds(325, 438, 89, 23);
		contentPane.add(button);

	}
}
