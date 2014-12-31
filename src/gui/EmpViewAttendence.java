package gui;

import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import algo.Employee;
import algo.EmployeeAttendence;
import db.javaconnector;

public class EmpViewAttendence extends JFrame {

	private JPanel contentPane;
	private ButtonGroup btngrp;
	private DatePanel datePanel;
	private JComboBox comboBox;
	private String Lable;
	private JLabel lblDate;
	private JPanel panel_1;
	private ResultSet rs;
	private ResultSet rs1;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	private JRadioButton rdbtnByDate;
	private JRadioButton rdbtnByMonth;
	private JComboBox comboBox_1;
	private String Str = "";
	private JButton btnReport;
	Connection con = null;
	private JasperDesign jasperDesign;
	private HashMap parameters;

	public EmpViewAttendence(Employee empObj) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("View Attendence");
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setBounds(100, 100, 800, 500);
		setLocation(300, 150);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel iconlable = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "view.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		iconlable.setBounds(264, 3, 62, 58);
		getContentPane().add(iconlable);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "View", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 69, 368, 72);
		contentPane.add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Select",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(393, 69, 391, 72);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setViewportBorder(new CompoundBorder());
		scrollPane1.setEnabled(false);
		scrollPane1.setBounds(10, 178, 774, 245);
		getContentPane().add(scrollPane1);
		EmployeeAttendence empatt = new EmployeeAttendence();
		comboBox = new JComboBox();
		comboBox_1 = new JComboBox();
		try {

			ResultSet rs = empatt.DistincitMonth();
			while (rs.next()) {
				String M = Integer.toString(rs.getInt("Month"));
				comboBox.addItem(M);
			}
			ResultSet rs1 = empatt.DistincitYear();
			while (rs1.next()) {
				String Y = Integer.toString(rs1.getInt("Year"));
				comboBox_1.addItem(Y);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		comboBox.setBounds(55, 23, 59, 20);
		panel_1.add(comboBox);
		lblDate = new JLabel("", SwingConstants.CENTER);
		lblDate.setBounds(6, 25, 39, 14);
		panel_1.add(lblDate);

		comboBox_1.setBounds(174, 23, 78, 20);
		panel_1.add(comboBox_1);
		comboBox.setVisible(false);
		comboBox_1.setVisible(false);

		JLabel lblNewLabel = new JLabel("Year:");
		lblNewLabel.setBounds(134, 24, 46, 14);
		panel_1.add(lblNewLabel);
		lblNewLabel.setVisible(false);

		ButtonGroup btngrp = new ButtonGroup();
		datePanel = new DatePanel();

		rdbtnByDate = new JRadioButton("By Date");
		rdbtnByDate.setBounds(46, 27, 109, 23);
		panel.add(rdbtnByDate);
		rdbtnByDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblDate.setText("Date:");
				comboBox.setVisible(false);
				comboBox_1.setVisible(false);
				lblNewLabel.setVisible(false);
				datePanel = new DatePanel();
				datePanel.setBounds(55, 16, 212, 33);
				panel_1.add(datePanel);
				datePanel.setVisible(true);
				btnReport.setVisible(false);
			}
		});
		btngrp.add(rdbtnByDate);
		rdbtnByDate.isSelected();

		rdbtnByMonth = new JRadioButton("By Month");
		rdbtnByMonth.setBounds(157, 27, 109, 23);
		panel.add(rdbtnByMonth);
		rdbtnByMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblDate.setText("Month:");
				datePanel.setVisible(false);
				comboBox.setVisible(true);
				comboBox_1.setVisible(true);
				lblNewLabel.setVisible(true);
			}
		});
		btngrp.add(rdbtnByMonth);

		// ScrollPane for Table

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmpAttendenceFrame empAttFrame = new EmpAttendenceFrame();
				empAttFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(415, 438, 89, 23);
		contentPane.add(btnNewButton);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnByDate.isSelected()) {
					final JTable table1 = new JTable();
					scrollPane1.setViewportView(table1);
					DefaultTableModel model = new DefaultTableModel() {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					table1.setModel(model);
					model.addColumn("Reg_No");
					model.addColumn("Name");
					model.addColumn("Status");

					List lst = new List();
					EmployeeAttendence empatt = new EmployeeAttendence();
					lst = empatt.searchTeachers(datePanel, empObj);
					int i = 0;
					int j = -1;
					while (i < (lst.getItemCount() / 3)) {
						model.addRow(new Object[4]);
						model.setValueAt(lst.getItem(j = j + 1), i, 0);
						model.setValueAt(lst.getItem(j = j + 1), i, 1);
						model.setValueAt(lst.getItem(j = j + 1), i, 2);
						i++;
					}

					for (int x = 0; x < table1.getColumnCount(); x++) {
						table1.getColumnModel().getColumn(x)
								.setCellRenderer(centerRenderer);
					}
				} else if (rdbtnByMonth.isSelected()) {
					btnReport.setVisible(true);
					final JTable table1 = new JTable();
					scrollPane1.setViewportView(table1);
					DefaultTableModel model = new DefaultTableModel() {
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					table1.setModel(model);
					table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					model.addColumn("Reg No");
					model.addColumn("Name");
					model.addColumn("1");
					model.addColumn("2");
					model.addColumn("3");
					model.addColumn("4");
					model.addColumn("5");
					model.addColumn("6");
					model.addColumn("7");
					model.addColumn("8");
					model.addColumn("9");
					model.addColumn("10");
					model.addColumn("11");
					model.addColumn("12");
					model.addColumn("13");
					model.addColumn("14");
					model.addColumn("15");
					model.addColumn("16");
					model.addColumn("17");
					model.addColumn("18");
					model.addColumn("19");
					model.addColumn("20");
					model.addColumn("21");
					model.addColumn("22");
					model.addColumn("23");
					model.addColumn("24");
					model.addColumn("25");
					model.addColumn("26");
					model.addColumn("27");
					model.addColumn("28");
					model.addColumn("29");
					model.addColumn("30");
					model.addColumn("31");
					model.addColumn("Tot P's");
					model.addColumn("Tot A's");
					model.addColumn("%age");

					table1.getColumnModel().getColumn(0).setMaxWidth(50);
					// table1.getColumnModel().getColumn(1).setWidth(1500);
					table1.getColumnModel().getColumn(2).setMaxWidth(25);
					table1.getColumnModel().getColumn(3).setMaxWidth(25);
					table1.getColumnModel().getColumn(4).setMaxWidth(25);
					table1.getColumnModel().getColumn(5).setMaxWidth(25);
					table1.getColumnModel().getColumn(6).setMaxWidth(25);
					table1.getColumnModel().getColumn(7).setMaxWidth(25);
					table1.getColumnModel().getColumn(8).setMaxWidth(25);
					table1.getColumnModel().getColumn(9).setMaxWidth(25);
					table1.getColumnModel().getColumn(10).setMaxWidth(25);
					table1.getColumnModel().getColumn(11).setMaxWidth(25);
					table1.getColumnModel().getColumn(12).setMaxWidth(25);
					table1.getColumnModel().getColumn(13).setMaxWidth(25);
					table1.getColumnModel().getColumn(14).setMaxWidth(25);
					table1.getColumnModel().getColumn(15).setMaxWidth(25);
					table1.getColumnModel().getColumn(16).setMaxWidth(25);
					table1.getColumnModel().getColumn(17).setMaxWidth(25);
					table1.getColumnModel().getColumn(18).setMaxWidth(25);
					table1.getColumnModel().getColumn(19).setMaxWidth(25);
					table1.getColumnModel().getColumn(20).setMaxWidth(25);
					table1.getColumnModel().getColumn(21).setMaxWidth(25);
					table1.getColumnModel().getColumn(22).setMaxWidth(25);
					table1.getColumnModel().getColumn(23).setMaxWidth(25);
					table1.getColumnModel().getColumn(24).setMaxWidth(25);
					table1.getColumnModel().getColumn(25).setMaxWidth(25);
					table1.getColumnModel().getColumn(26).setMaxWidth(25);
					table1.getColumnModel().getColumn(27).setMaxWidth(25);
					table1.getColumnModel().getColumn(28).setMaxWidth(25);
					table1.getColumnModel().getColumn(29).setMaxWidth(25);
					table1.getColumnModel().getColumn(30).setMaxWidth(25);
					table1.getColumnModel().getColumn(31).setMaxWidth(25);
					table1.getColumnModel().getColumn(32).setMaxWidth(25);
					table1.getColumnModel().getColumn(33).setMaxWidth(40);
					table1.getColumnModel().getColumn(34).setMaxWidth(40);
					table1.getColumnModel().getColumn(35).setMaxWidth(50);

					EmployeeAttendence empatt = new EmployeeAttendence();
					List lst = new List();
					lst = empatt.ViewAttendence(empObj, Integer
							.parseInt((String) comboBox.getSelectedItem()),
							Integer.parseInt((String) comboBox_1
									.getSelectedItem()));
					int i = 0;
					int j = -1;
					while (i < (lst.getItemCount() / 36)) {
						model.addRow(new Object[4]);
						model.setValueAt(lst.getItem(j = j + 1), i, 0);
						model.setValueAt(lst.getItem(j = j + 1), i, 1);
						j = j + 1;
						int dates = j + 31;
						int F = 2;
						for (int k = j; k < dates; k++) {
							model.setValueAt(lst.getItem(k), i, F);
							j = j + 1;
							F++;
						}
						model.setValueAt(lst.getItem(j), i, 33);
						model.setValueAt(lst.getItem(j = j + 1), i, 34);
						model.setValueAt(lst.getItem(j = j + 1) + "%", i, 35);
						// j=j-1;
						i++;
					}

					for (int x = 0; x < table1.getColumnCount(); x++) {
						table1.getColumnModel().getColumn(x)
								.setCellRenderer(centerRenderer);
					}
				}
			}
		});
		btnView.setBounds(316, 438, 89, 23);
		contentPane.add(btnView);

		JLabel lblAttendenceRecord = new JLabel("Attendence Record");
		lblAttendenceRecord.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttendenceRecord.setBounds(328, 152, 152, 14);
		contentPane.add(lblAttendenceRecord);

		JLabel lblStudentAttendence = new JLabel("Student Attendence");
		lblStudentAttendence.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentAttendence.setBounds(336, 20, 222, 20);
		contentPane.add(lblStudentAttendence);

		JSeparator separator = new JSeparator();
		separator.setBounds(316, 43, 241, 2);
		contentPane.add(separator);

		btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (rdbtnByMonth.isSelected() || rdbtnByDate.isSelected()) {
						if (rdbtnByMonth.isSelected()) {
							String report = "EmpMonthlyAttReport.jrxml";
							File theFile = new File(report);
							jasperDesign = JRXmlLoader.load(theFile);
							String theQuery = "SELECT distinct a.*, b.Name, b.Designation FROM teacherattendence a, tea_enroll b WHERE b.Designation='"
									+ empObj.getDesignation()
									+ "' and a.Reg_no=b.EReg_no and "
									+ "a.Month="
									+ comboBox.getSelectedItem()
									+ " and a.Year="
									+ comboBox_1.getSelectedItem();
							JRDesignQuery newQuery = new JRDesignQuery();
							newQuery.setText(theQuery);
							jasperDesign.setQuery(newQuery);
							parameters = new HashMap();
							parameters.put("designation",empObj.getDesignation());
							parameters.put("monthyaear", comboBox.getSelectedItem().toString()+"-"+comboBox_1.getSelectedItem().toString());
							JasperReport jr = JasperCompileManager
									.compileReport(jasperDesign);
							JasperPrint jp = JasperFillManager.fillReport(jr,
									parameters, con);
							JasperViewer.viewReport(jp, false);
						}
					}
				} catch (Exception e2) {

					e2.printStackTrace();
				}
			}
		});
		btnReport.setBounds(216, 438, 89, 23);
		contentPane.add(btnReport);
		btnReport.setVisible(false);
		con = javaconnector.ConDb();

	}
}
