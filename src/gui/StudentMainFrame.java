package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import db.javaconnector;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class StudentMainFrame extends JFrame {

	private JPanel contentPane;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	Connection con = null;
	private JasperDesign jasperDesign;
	
	public StudentMainFrame() {
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setBounds(100, 100, 800, 500);
		setTitle("Student Fee Module");
		setLocation(300, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLocation(0, 0);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Students Options",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(28, 83, 724, 54);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnGenerateFeeCard = new JButton("Generate Fee Card");
		btnGenerateFeeCard.setBounds(198, 16, 149, 23);
		panel.add(btnGenerateFeeCard);

		JButton btnSubmitFee = new JButton("Submit Fee");
		btnSubmitFee.setBounds(356, 16, 149, 23);
		panel.add(btnSubmitFee);
		
		JButton btnNewButton = new JButton("Defaulter List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String report = "defaulterList.jrxml";
				File theFile = new File(report);
				try {
					jasperDesign = JRXmlLoader.load(theFile);
					JasperReport jr = JasperCompileManager
							.compileReport(jasperDesign);
					JasperPrint jp = JasperFillManager.fillReport(jr,
							null, con);
					JasperViewer.viewReport(jp, false);
					
				} catch (JRException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		btnNewButton.setBounds(515, 16, 133, 23);
		con = javaconnector.ConDb();
		panel.add(btnNewButton);

		SideBar sideBar = new SideBar();
		sideBar.setBounds(28, 147, 161, 258);
		contentPane.add(sideBar);
		
		JLabel lblStudentFee = new JLabel("Student Fee  ");
		lblStudentFee.setForeground(Color.BLACK);
		lblStudentFee.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentFee.setBounds(321, 26, 195, 27);
		contentPane.add(lblStudentFee);
		
		JLabel label_1 = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "fee.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		label_1.setBounds(233, 3, 80, 80);
		contentPane.add(label_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(281, 54, 205, 2);
		contentPane.add(separator);

		GenerateFeeCardPanel generateFeeCardPanel = new GenerateFeeCardPanel();
		generateFeeCardPanel.setBounds(250, 200, 353, 180);
		// contentPane.add(generateFeeCardPanel);

		SearchFeeCardPanel searchPanel_1 = new SearchFeeCardPanel();
		searchPanel_1.setBounds(250, 200, 341, 105);
		// contentPane.add(searchPanel_1);

		btnSubmitFee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.disable();
				setContentPane(searchPanel_1);
			}
		});
		btnGenerateFeeCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.disable();
				setContentPane(generateFeeCardPanel);

			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			AccountantMainFrame acc=new AccountantMainFrame();
			acc.setVisible(true);
			dispose();
			}
		});
		btnBack.setBounds(670, 427, 89, 23);
		contentPane.add(btnBack);
		
		
	}
}
