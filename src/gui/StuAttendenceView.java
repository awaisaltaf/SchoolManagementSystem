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
import algo.StudentAttendence;
import algo.Teacher;
import algo.classSection;
import db.javaconnector;

public class StuAttendenceView extends JFrame {

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
	Connection con = null;
	private JasperDesign jasperDesign;
	private HashMap parameters;
	private JButton btnReport;
	private String sec = "";
	private String Class = "";
	private ResultSet CS;
	private int Lid;

	public StuAttendenceView(ResultSet CSSUB) {
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
				+ "view.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		iconlable.setBounds(222, 0, 73, 58);
		getContentPane().add(iconlable);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "View", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 69, 379, 72);
		contentPane.add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Select",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(393, 69, 381, 72);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setViewportBorder(new CompoundBorder());
		scrollPane1.setEnabled(false);
		scrollPane1.setBounds(10, 178, 764, 239);
		getContentPane().add(scrollPane1);
		StudentAttendence SA = new StudentAttendence();
		comboBox = new JComboBox();
		comboBox_1 = new JComboBox();
		try {
			ResultSet rs = SA.DistincitMonth();
			while (rs.next()) {
				String M = Integer.toString(rs.getInt("Month"));
				comboBox.addItem(M);
			}
			ResultSet rs1 = SA.DistincitYear();
			while (rs1.next()) {
				String Y = Integer.toString(rs1.getInt("Year"));
				comboBox_1.addItem(Y);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		comboBox.setBounds(67, 27, 59, 20);
		panel_1.add(comboBox);
		lblDate = new JLabel("", SwingConstants.CENTER);
		lblDate.setBounds(17, 32, 39, 14);
		panel_1.add(lblDate);

		comboBox_1.setBounds(186, 27, 78, 20);
		panel_1.add(comboBox_1);
		comboBox.setVisible(false);
		comboBox_1.setVisible(false);

		JLabel lblNewLabel = new JLabel("Year:");
		lblNewLabel.setBounds(146, 28, 46, 14);
		panel_1.add(lblNewLabel);
		lblNewLabel.setVisible(false);

		ButtonGroup btngrp = new ButtonGroup();
		datePanel = new DatePanel();

		rdbtnByDate = new JRadioButton("By Date");
		rdbtnByDate.setBounds(66, 28, 109, 23);
		panel.add(rdbtnByDate);
		rdbtnByDate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				lblDate.setText("Date:");
				comboBox.setVisible(false);
				comboBox_1.setVisible(false);
				lblNewLabel.setVisible(false);
				datePanel = new DatePanel();
				datePanel.setBounds(55, 24, 212, 40);
				panel_1.add(datePanel);
				datePanel.setVisible(true);
				btnReport.setVisible(false);
			}
		});
		btngrp.add(rdbtnByDate);
		rdbtnByDate.isSelected();

		rdbtnByMonth = new JRadioButton("By Month");
		rdbtnByMonth.setBounds(192, 27, 109, 23);
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
				StuAttendenceFrame attframe = new StuAttendenceFrame(CSSUB);
				attframe.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(464, 428, 89, 23);
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
					model.addColumn("Roll_No");
					model.addColumn("Name");
					model.addColumn("Status");

					List lst = new List();
					StudentAttendence stuAtt = new StudentAttendence();
					stuAtt.setDate(datePanel);
					lst = stuAtt.ViewAttendence(Class, sec, 0, 0);
					int i = 0;
					int j = -1;
					while (i < (lst.getItemCount() / 4)) {
						model.addRow(new Object[4]);
						model.setValueAt(lst.getItem(j = j + 1), i, 0);
						model.setValueAt(lst.getItem(j = j + 1), i, 1);
						model.setValueAt(lst.getItem(j = j + 1), i, 2);
						model.setValueAt(lst.getItem(j = j + 1), i, 3);
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
					model.addColumn("Reg_No");
					model.addColumn("Roll_No");
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
					table1.getColumnModel().getColumn(1).setMaxWidth(50);
					// table.getColumnModel().getColumn(2).setMaxWidth(150);
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
					table1.getColumnModel().getColumn(33).setMaxWidth(25);
					table1.getColumnModel().getColumn(34).setMaxWidth(40);
					table1.getColumnModel().getColumn(35).setMaxWidth(40);
					table1.getColumnModel().getColumn(36).setMaxWidth(50);

					StudentAttendence stuAtt = new StudentAttendence();
					List lst = new List();
					stuAtt.setDate(datePanel);
					lst = stuAtt.ViewAttendence(Class, sec, Integer
							.parseInt((String) comboBox.getSelectedItem()),
							Integer.parseInt((String) comboBox_1
									.getSelectedItem()));
					int i = 0;
					int j = -1;
					while (i < (lst.getItemCount() / 37)) {
						model.addRow(new Object[4]);
						model.setValueAt(lst.getItem(j = j + 1), i, 0);
						model.setValueAt(lst.getItem(j = j + 1), i, 1);
						model.setValueAt(lst.getItem(j = j + 1), i, 2);
						j = j + 1;
						int dates = j + 31;
						int F = 3;
						for (int k = j; k < dates; k++) {
							model.setValueAt(lst.getItem(k), i, F);
							j = j + 1;
							F++;
						}
						model.setValueAt(lst.getItem(j), i, 34);
						model.setValueAt(lst.getItem(j = j + 1), i, 35);
						model.setValueAt(lst.getItem(j = j + 1) + "%", i, 36);
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
		btnView.setBounds(365, 428, 89, 23);
		contentPane.add(btnView);

		JLabel lblAttendenceRecord = new JLabel("Attendence Record");
		lblAttendenceRecord.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAttendenceRecord.setBounds(336, 152, 152, 14);
		contentPane.add(lblAttendenceRecord);

		JLabel lblStudentAttendence = new JLabel("Student Attendence");
		lblStudentAttendence.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentAttendence.setBounds(288, 18, 226, 27);
		contentPane.add(lblStudentAttendence);

		JSeparator separator = new JSeparator();
		separator.setBounds(270, 47, 253, 2);
		contentPane.add(separator);

		btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (rdbtnByMonth.isSelected() || rdbtnByDate.isSelected()) {
						if (rdbtnByMonth.isSelected()) {
							String report = "MonthAttReport.jrxml";
							File theFile = new File(report);
							jasperDesign = JRXmlLoader.load(theFile);
							String theQuery = "SELECT  a.*, b.Name,b.Roll_no,b.Current_Class,b.Section FROM studentattendence a, stu_enroll b WHERE b.Current_Class="
									+ Class
									+ " and b.Section='"
									+ sec
									+ "' and a.Reg_no=b.Reg_no and a.Month="
									+ comboBox.getSelectedItem()
									+ " and a.Year="
									+ comboBox_1.getSelectedItem();
							JRDesignQuery newQuery = new JRDesignQuery();
							newQuery.setText(theQuery);
							jasperDesign.setQuery(newQuery);
							parameters = new HashMap();
							parameters.put("class", Class);
							parameters.put("section", sec);
							parameters.put("month", comboBox.getSelectedItem());
							parameters.put("year", comboBox_1.getSelectedItem());
						} else if (rdbtnByDate.isSelected()) {
							String report = "DateAttReport.jrxml";
							File theFile = new File(report);
							jasperDesign = JRXmlLoader.load(theFile);
							String theQuery = "SELECT a.Reg_no,a.`"
									+ datePanel.getDate()
									+ "`,b.Name,b.Roll_no FROM studentattendence a, stu_enroll b WHERE a.Reg_no=b.Reg_no and a.Month="
									+ datePanel.getMonth() + " and a.Year="
									+ datePanel.getYear();
							parameters = new HashMap();
							parameters.put("Date",
									Integer.toString(datePanel.getDate()));
							parameters.put("Query", theQuery);
						}

						JasperReport jr = JasperCompileManager
								.compileReport(jasperDesign);
						JasperPrint jp = JasperFillManager.fillReport(jr,
								parameters, con);
						JasperViewer.viewReport(jp, false);
					}
				} catch (Exception e2) {

					e2.printStackTrace();
				}
			}
		});
		btnReport.setBounds(270, 428, 89, 23);
		contentPane.add(btnReport);
		btnReport.setVisible(false);
		con = javaconnector.ConDb();

	}
}
