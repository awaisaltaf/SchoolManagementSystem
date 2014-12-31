package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import db.javaconnector;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import algo.Accountant;
import algo.Student;
import algo.StudentFinance;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class FeeCard extends JFrame {

	private JPanel contentPane;
	private Student sObj;
	private StudentFinance fObj;
	private Accountant ac = new Accountant();
	private ResultSet rs;
	private JTable table;
	private JasperDesign jasperDesign;
	private HashMap parameters;
	Connection con = null;
	DatePanel d = new DatePanel();
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";

	public FeeCard() {

	}

	public FeeCard(Student s, StudentFinance f, ResultSet r) {
		this.sObj = s;
		this.fObj = f;
		this.rs = r;
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setTitle("Employee Salary Module");
		setBounds(100, 100, 1000, 516);
		setLocation(200, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Fee Card",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(42, 11, 364, 423);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Student Name");
		lblNewLabel.setBounds(10, 16, 101, 19);
		panel.add(lblNewLabel);

		JLabel label = new JLabel(sObj.getsName());
		label.setBounds(252, 16, 101, 16);
		panel.add(label);

		JLabel lblFatherName = new JLabel("Father Name");
		lblFatherName.setBounds(10, 46, 101, 19);
		panel.add(lblFatherName);

		JLabel label_2 = new JLabel(sObj.getgName());
		label_2.setBounds(252, 43, 101, 19);
		panel.add(label_2);

		JLabel lblReg = new JLabel("Reg #");
		lblReg.setBounds(10, 76, 101, 19);
		panel.add(lblReg);

		JLabel label_4 = new JLabel(Integer.toString(sObj.getRegNo()));
		label_4.setBounds(252, 73, 101, 19);
		panel.add(label_4);

		JLabel lblClass = new JLabel("Class");
		lblClass.setBounds(10, 106, 101, 19);
		panel.add(lblClass);

		JLabel label_6 = new JLabel(Integer.toString(sObj.getCurrentClass()));
		label_6.setBounds(252, 103, 101, 19);
		panel.add(label_6);

		JLabel lblSection = new JLabel("Section");
		lblSection.setBounds(10, 136, 101, 19);
		panel.add(lblSection);

		JLabel label_8 = new JLabel(sObj.getSection());
		label_8.setBounds(252, 133, 101, 19);
		panel.add(label_8);

		JLabel lblFee = new JLabel("Fee");
		lblFee.setBounds(10, 231, 101, 16);
		panel.add(lblFee);

		JLabel label_10 = new JLabel(Float.toString(fObj.getFee()));
		label_10.setBounds(222, 231, 131, 17);
		panel.add(label_10);

		JLabel lblFine = new JLabel("Attendence Fine");
		lblFine.setBounds(10, 258, 101, 19);
		panel.add(lblFine);

		JLabel label_12 = new JLabel(Float.toString(fObj.getFine()));
		label_12.setBounds(222, 260, 131, 18);
		panel.add(label_12);

		JLabel lblRemainders = new JLabel("Remainders Of Last Month");
		lblRemainders.setBounds(10, 318, 200, 16);
		panel.add(lblRemainders);

		JLabel label_14 = new JLabel(Float.toString(fObj.getRemainder()));
		label_14.setBounds(222, 319, 131, 16);
		panel.add(label_14);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 344, 101, 19);
		panel.add(lblTotal);

		JLabel label_16 = new JLabel(Float.toString(fObj.getTotal()));
		label_16.setBounds(222, 344, 131, 19);
		panel.add(label_16);

		JLabel lblDueDate = new JLabel("Due Date");
		lblDueDate.setBounds(10, 176, 101, 16);
		panel.add(lblDueDate);

		JLabel label_3 = new JLabel(fObj.getDueDate());
		label_3.setBounds(222, 175, 131, 19);
		panel.add(label_3);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 166, 343, 2);
		panel.add(separator);

		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(10, 204, 101, 16);
		panel.add(lblMonth);

		JLabel label_5 = new JLabel(Integer.toString(fObj.getMonth()));
		label_5.setBounds(222, 203, 131, 19);
		panel.add(label_5);

		JLabel lblPaymentStatus = new JLabel("Payment Status");
		lblPaymentStatus.setBounds(10, 368, 101, 19);
		panel.add(lblPaymentStatus);

		JLabel lblN = new JLabel(fObj.getPaymentStatus());
		lblN.setBounds(222, 368, 131, 19);
		panel.add(lblN);

		JLabel lblLateFeeFine = new JLabel("Late Fee Fine");
		lblLateFeeFine.setBounds(10, 288, 101, 19);
		panel.add(lblLateFeeFine);

		JLabel label_7 = new JLabel(Float.toString(fObj.getlFeeFine()));
		label_7.setBounds(222, 290, 131, 18);
		panel.add(label_7);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Fee Record",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(420, 11, 540, 420);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 520, 390);
		panel_1.add(scrollPane);
		scrollPane.setEnabled(false);

		// Table
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBounds(400, 11, 520, 390);

		// Model for Table
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {

				return false;

			}
		};

		table.setModel(model);

		model.addColumn("Month");
		model.addColumn("Fee");
		model.addColumn("LF_Fine");
		model.addColumn("Att_Fine");
		model.addColumn("Paid Amount");
		model.addColumn("Status");

		try {
			int row = 0;
			while (rs.next()) {
				model.addRow(new Object[0]);
				model.setValueAt(rs.getInt("Month"), row, 0);
				model.setValueAt(rs.getInt("Amount"), row, 1);
				model.setValueAt(rs.getInt("LF_Fine"), row, 2);
				model.setValueAt(rs.getInt("Att_Fine"), row, 3);
				model.setValueAt(rs.getInt("Amount_Paid"), row, 4);
				model.setValueAt(rs.getString("Payment_Status"), row, 5);
				row++;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentMainFrame mf = new StudentMainFrame();
				mf.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(870, 445, 89, 23);
		contentPane.add(btnNewButton);

		JButton button = new JButton("Submit");
		if (fObj.getPaymentStatus().matches("Paid")) {
			button.setEnabled(false);
		}
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Messages m = new Messages();
				if (fObj.getPaymentStatus().matches("UnPaid")) {
					ac.feeSubmission(fObj);
					m.success("Fee Success Fully Submitted", "Fee Submission");
					// ////////////////////////////reporting
					// //////////////////////////////reporting
					try {
						String report = "G:\\School_Management_System\\SchoolManagementSystem\\FeeReceipt.jrxml";
						File theFile = new File(report);
						jasperDesign = JRXmlLoader.load(theFile);
						String theQuery = "select s.`Reg_no`,s.`Name`,s.`Guardian_Name`,s.`Current_Class`,s.`Section`,f.`Due_Date`,f.`Sub_Date` from feesubmission f,stu_enroll s where f.`Reg_no`='"
								+ sObj.getRegNo()
								+ "' and f.`Reg_no`=s.`Reg_no`";
						JRDesignQuery newQuery = new JRDesignQuery();
						newQuery.setText(theQuery);
						jasperDesign.setQuery(newQuery);
						parameters = new HashMap();
						parameters.put("Fee", Float.toString(fObj.getFee()));
						parameters.put("AttFine",
								Float.toString(fObj.getFine()));
						parameters.put("LFFine",
								Float.toString(fObj.getlFeeFine()));
						parameters.put("Remainder",
								Float.toString(fObj.getRemainder()));
						parameters.put("Total", Float.toString(fObj.getTotal()));
						JasperReport jr = JasperCompileManager
								.compileReport(jasperDesign);
						JasperPrint jp = JasperFillManager.fillReport(jr,
								parameters, con);
						JasperViewer.viewReport(jp, false);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					// ////////////////////////////reporting
					// //////////////////////////////reporting
				} else {
					m.Failure("Already Submitted", "Fee Submission");
				}
			}
		});
		button.setBounds(45, 445, 89, 23);
		con = javaconnector.ConDb();
		contentPane.add(button);

		JButton PrintCard = new JButton("PrintFeeCard");
		if (fObj.getPaymentStatus().matches("Paid")) {
			PrintCard.setEnabled(false);
		}
		PrintCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String report = "G:\\School_Management_System\\SchoolManagementSystem\\FeeCard.jrxml";
					File theFile = new File(report);
					jasperDesign = JRXmlLoader.load(theFile);
					String theQuery = "select f.*,s.Reg_no,s.Name,s.Guardian_Name,s.Current_Class,s.Section from feesubmission f,stu_enroll s where f.Year='"
							+ d.getYear()
							+ "' and f.Reg_no='"
							+ sObj.getRegNo() + "' and f.Reg_no=s.Reg_no";
					JRDesignQuery newQuery = new JRDesignQuery();
					newQuery.setText(theQuery);
					jasperDesign.setQuery(newQuery);
					parameters = new HashMap();
					parameters.put("Fee", Float.toString(fObj.getFee()));
					JasperReport jr = JasperCompileManager
							.compileReport(jasperDesign);
					JasperPrint jp = JasperFillManager.fillReport(jr,
							parameters, con);
					JasperViewer.viewReport(jp, false);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

		});
		PrintCard.setBounds(300, 445, 100, 23);
		con = javaconnector.ConDb();
		contentPane.add(PrintCard);

	}
}
