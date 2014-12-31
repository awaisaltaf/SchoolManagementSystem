package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

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
import algo.Employee;
import algo.EmployeeFinance;
import java.awt.Font;
import javax.swing.Icon;

public class SalarySlip extends JFrame {

	private JPanel contentPane;
	private Employee e;
	private EmployeeFinance eF;
	private ResultSet rs;
	private DatePanel datePanel = new DatePanel();
	private Accountant ac = new Accountant();
	private JasperDesign jasperDesign;
	private HashMap parameters;
	Connection con = null;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";

	public SalarySlip(Employee eObj, EmployeeFinance eFObj, ResultSet result) {
		this.e = eObj;
		this.eF = eFObj;
		this.rs = result;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Salary Issue");
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setBounds(100, 100, 800, 500);
		setLocation(300, 150);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_10 = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "cash.png")).getImage()).getScaledInstance(60, 60,
						java.awt.Image.SCALE_SMOOTH)));
		label_10.setBounds(251, 2, 73, 58);
		contentPane.add(label_10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Issue Salary",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(29, 71, 393, 338);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblRegNo = new JLabel("Reg No");
		lblRegNo.setBounds(86, 40, 87, 21);
		panel.add(lblRegNo);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(86, 60, 87, 21);
		panel.add(lblName);

		JLabel lblDesignation = new JLabel("Designation");
		lblDesignation.setBounds(86, 80, 87, 21);
		panel.add(lblDesignation);

		JLabel label = new JLabel(e.geteName());
		label.setBounds(220, 60, 87, 21);
		panel.add(label);

		JLabel label_1 = new JLabel(Integer.toString(e.getRegNo()));
		label_1.setBounds(220, 40, 87, 21);
		panel.add(label_1);

		JLabel label_2 = new JLabel(e.getDesignation());
		label_2.setBounds(220, 80, 87, 21);
		panel.add(label_2);

		JLabel lblMedicalAllownce = new JLabel("Medical Allownce");
		lblMedicalAllownce.setBounds(86, 148, 108, 21);
		panel.add(lblMedicalAllownce);

		JLabel label_4 = new JLabel(Float.toString(eF.getMedAllowance()));
		label_4.setBounds(220, 148, 87, 21);
		panel.add(label_4);

		JLabel lblTransportallownce = new JLabel("TransportAllownce");
		lblTransportallownce.setBounds(86, 180, 108, 21);
		panel.add(lblTransportallownce);

		JLabel label_6 = new JLabel(Float.toString(eF.getTransAllowance()));
		label_6.setBounds(220, 180, 87, 21);
		panel.add(label_6);

		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(86, 212, 87, 21);
		panel.add(lblSalary);

		JLabel label_8 = new JLabel(Float.toString(eF.getSalary()));
		label_8.setBounds(220, 212, 87, 21);
		panel.add(label_8);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(86, 268, 87, 21);
		panel.add(lblTotal);

		JLabel label_5 = new JLabel(Float.toString(eF.getTotal()));
		label_5.setBounds(220, 268, 87, 21);
		panel.add(label_5);

		JSeparator separator = new JSeparator();
		separator.setBounds(67, 105, 221, 2);
		panel.add(separator);
		con = javaconnector.ConDb();

		JLabel lblPaymentStatus = new JLabel("Payment Status");
		lblPaymentStatus.setBounds(86, 292, 108, 21);
		panel.add(lblPaymentStatus);

		JLabel lblN = new JLabel(eF.getPaymentStatus());
		lblN.setBounds(220, 292, 87, 21);
		panel.add(lblN);

		JLabel lblRemainder = new JLabel("Remainder");
		lblRemainder.setBounds(86, 236, 87, 21);
		panel.add(lblRemainder);

		JLabel label_7 = new JLabel(Float.toString(eF.getRemainder()));
		label_7.setBounds(220, 236, 87, 21);
		panel.add(label_7);

		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(86, 116, 108, 21);
		panel.add(lblMonth);

		JLabel label_9 = new JLabel(Integer.toString(datePanel.getMonth()));
		label_9.setBounds(220, 116, 87, 21);
		panel.add(label_9);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Salary Record",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(443, 71, 316, 338);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 296, 305);
		panel_1.add(scrollPane);
		scrollPane.setEnabled(false);

		// Table
		final JTable table = new JTable();
		scrollPane.setViewportView(table);

		// Model for Table
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {

				return false;

			}
		};

		table.setModel(model);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeMainFrame mf = new EmployeeMainFrame();
				mf.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(427, 438, 89, 23);
		contentPane.add(btnBack);
		
				JButton btnNewButton = new JButton("issue");
				btnNewButton.setBounds(333, 438, 89, 23);
				contentPane.add(btnNewButton);
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBounds(295, 47, 191, 2);
				contentPane.add(separator_1);
				
				JLabel lblSalaryIssue = new JLabel("Salary Issue");
				lblSalaryIssue.setFont(new Font("Tahoma", Font.BOLD, 20));
				lblSalaryIssue.setBounds(335, 21, 226, 27);
				contentPane.add(lblSalaryIssue);
				if(eF.getPaymentStatus().matches("Y") || eF.getPaymentStatus().matches("y"))
				{
				btnNewButton.setEnabled(false);
				}
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Messages m = new Messages();
						if (eF.getPaymentStatus().matches("N")
								|| eF.getPaymentStatus().matches("n")) {
							ac.SalaryIssue(eF);
							m.success("Salary SuccessFully Issued", "Salary Issue");
							try {
								String report = "G:\\School_Management_System\\SchoolManagementSystem\\SalarySlip.jrxml";
								File theFile = new File(report);
								jasperDesign = JRXmlLoader.load(theFile);
								String theQuery = "select s.*,t.* from salaryissue s,tea_enroll t where s.Reg_no=t.EReg_no and s.Reg_no='"
										+ e.getRegNo()
										+ "'and Month='"
										+ Integer.toString(datePanel.getMonth())
										+ "'and Year='"
										+ Integer.toString(datePanel.getYear()) + "'";
								System.out.println(theQuery);
								JRDesignQuery newQuery = new JRDesignQuery();
								newQuery.setText(theQuery);
								jasperDesign.setQuery(newQuery);
								parameters = new HashMap();
								parameters.put("med", Float.toString(eF.getMedAllowance()));
								parameters.put("trans", Float.toString(eF.getTransAllowance()));
								parameters.put("sal", Float.toString(eF.getSalary()));
								parameters.put("Remainder",
										Float.toString(eF.getRemainder()));
								parameters.put("total", Float.toString(eF.getTotal()));
								
								
								JasperReport jr = JasperCompileManager
										.compileReport(jasperDesign);
								JasperPrint jp = JasperFillManager.fillReport(jr,
										parameters, con);
								JasperViewer.viewReport(jp, false);
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						} else {
							m.Failure("Already Isued", "Salary Issue");
						}
					}
				});
		model.addColumn("Month");
		model.addColumn("Paid Amount");
		model.addColumn("Issue Date");

		try {
			int row = 0;
			while (rs.next()) {
				model.addRow(new Object[0]);
				model.setValueAt(rs.getInt("Month"), row, 0);
				model.setValueAt(rs.getInt("Amount_Paind"), row, 1);
				model.setValueAt(rs.getString("Paid_Date"), row, 2);
				row++;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
