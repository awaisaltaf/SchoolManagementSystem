package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.List;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JSeparator;

import algo.Student;
import algo.StudentAttendence;
import algo.Teacher;
import algo.classSection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StuUpdateAttendence extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblUpdated;
	private String str;
	private StuUpdateAttendence ap;
	private List ls;
	private ImageIcon icon;
	private DatePanel datePanel;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	private Messages msg;
	private String text;
	private DefaultTableModel model;
	private JTable table;
	private JButton btnUpdate;
	private String sec = "";
	private String Class = "";
	private ResultSet CS;
	private int Lid;

	public StuUpdateAttendence(ResultSet CSSUB) {
		this.str = "";
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setTitle("Update Attendence");
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
				+ "update.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		iconlable.setBounds(212, 6, 76, 57);
		getContentPane().add(iconlable);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Select", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(62, 97, 650, 157);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblRollNo = new JLabel("Roll No:");
		lblRollNo.setBounds(122, 90, 60, 14);
		panel.add(lblRollNo);
		lblRollNo.setFont(new Font("Tahoma", Font.PLAIN, 14));

		msg = new Messages();

		datePanel = new DatePanel();
		datePanel.setBounds(194, 48, 212, 33);
		panel.add(datePanel);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(194, 89, 212, 20);
		panel.add(comboBox);
		ResultSet rs;
		StudentAttendence stuAtt = new StudentAttendence();
		Student stu = new Student();
		rs = stu.getStudent(Class, sec);
		try {
			while (rs.next()) {
				String regno = Integer.toString(rs.getInt("Reg_no"));
				comboBox.addItem(regno);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(122, 60, 60, 14);
		panel.add(lblDate);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));

		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(62, 265, 650, 46);
		getContentPane().add(scrollPane);

		// Table
		table = new JTable();
		scrollPane.setViewportView(table);

		// Model for Table
		model = new DefaultTableModel() {
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
		model.addColumn("Reg_No");
		model.addColumn("Roll No");
		model.addColumn("Name");
		model.addColumn("Status");

		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < table.getRowCount(); i++) {
					model.removeRow(i);
				}
				List ls;
				StudentAttendence stuAtt = new StudentAttendence();
				stuAtt.setDate(datePanel);
				ls = stuAtt.updateSearch(comboBox.getSelectedItem().toString(),
						Class, sec);
				int i = 0;
				int j = -1;
				while (i < (ls.getItemCount() / 4)) {
					model.addRow(new Object[4]);
					model.setValueAt(ls.getItem(j = j + 1), i, 0);
					model.setValueAt(ls.getItem(j = j + 1), i, 1);
					model.setValueAt(ls.getItem(j = j + 1), i, 2);
					model.setValueAt(ls.getItem(j = j + 1), i, 3);
					i++;
				}

				String[] dataItem = { "A", "P", "L" };
				JComboBox Combo = new JComboBox(dataItem);
				((JLabel) Combo.getRenderer())
						.setHorizontalAlignment(SwingConstants.CENTER);
				TableColumn Column = table.getColumnModel().getColumn(3);
				Column.setCellEditor(new DefaultCellEditor(Combo));

				btnUpdate.setEnabled(true);
			}
		});
		btnShow.setBounds(453, 66, 89, 23);
		panel.add(btnShow);

		CurrentDate currentdate = new CurrentDate();
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				btnUpdate.setEnabled(false);
				Date todaydate = new Date();
				if (datePanel.getDateObj().after(todaydate)) {
					msg.Failure("In Advance Attendence Can't Be Update",
							"Warning");
				} else {
					boolean A;
					if (table.contains(0, 0)) {
						model.fireTableDataChanged();
						StudentAttendence stuAtt = new StudentAttendence();
						Student stuObj = new Student();
						String regno = model.getValueAt(0, 0).toString();
						String status = table.getValueAt(0, 3).toString();
						if (regno
								.matches(comboBox.getSelectedItem().toString())) {
							stuAtt.setDate(datePanel);
							stuObj.setRegNo(Integer.parseInt(regno));
							A = stuAtt.updateAttendence(regno, status);
							stuAtt.AttendenceCountPercentage(stuObj);
							if (A == true) {
								msg.success("Attendence Successfully Updated",
										"Updated");

							} else {
								msg.Failure("Updation Failed", "Failed");
							}

						} else {
							msg.Warning("Once Show then Update", "Warning");
						}

					} else {
						msg.Failure("Attendence Not Marked", "Note");
					}
				}

			}
		});
		btnUpdate.setBounds(315, 438, 89, 23);
		contentPane.add(btnUpdate);
		btnUpdate.setEnabled(false);
		JButton btnCancle = new JButton("Back");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StuAttendenceFrame mf = new StuAttendenceFrame(CSSUB);
				mf.setVisible(true);
				dispose();
			}
		});
		btnCancle.setBounds(414, 438, 89, 23);
		contentPane.add(btnCancle);

		JLabel lblUpdateAttendence = new JLabel("Update Attendence");
		lblUpdateAttendence.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUpdateAttendence.setBounds(297, 21, 187, 20);
		contentPane.add(lblUpdateAttendence);

		JSeparator separator = new JSeparator();
		separator.setBounds(257, 43, 246, 2);
		contentPane.add(separator);

	}
}
