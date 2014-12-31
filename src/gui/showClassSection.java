package gui;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import algo.Subject;
import algo.classSection;
import algo.testExam;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.Icon;

public class showClassSection extends JFrame {

	private JPanel contentPane;
	private testExam objTestExam;
	private classSection objClassSection;
	private Subject objSubject;
	private JLabel lblNewLabel;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	/**
	 * Create the frame.
	 */
	public showClassSection(List CS, List sub, List TeaId, int objLogin,
			ResultSet CSSUB) {
	
		objTestExam = new testExam();
		objClassSection = new classSection();
		objSubject = new Subject();
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
		
		JLabel label_1 = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "acadamic.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		label_1.setBounds(228, 6, 73, 58);
		contentPane.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(223, 164, 320, 145);
		contentPane.add(panel);
		panel.setLayout(null);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(114, 81, 155, 20);
		panel.add(comboBox_1);
		

		 lblNewLabel = new JLabel("Subject:");
		 lblNewLabel.setBounds(32, 80, 130, 24);
		 panel.add(lblNewLabel);
		 JComboBox comboBox = new JComboBox();
		 comboBox.setBounds(111, 44, 156, 20);
		 panel.add(comboBox);
		 comboBox.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		if (comboBox.getSelectedItem() != null) {
		 			String comboCS = (String) comboBox.getSelectedItem();

		 			String clas = comboCS.substring(0, 1);
		 			int clas_1 = Integer.parseInt(clas);
		 			String sec = comboCS.substring(2, 3);
		 			//
		 			char sec_1 = sec.charAt(0);
		 			// System.out.println("clas_1 "+clas_1+" sec "+sec_1);
		 			int CSid = objClassSection.getCSid(clas_1, sec_1);
		 			// System.out.println("CSid "+CSid);

		 			sub.removeAll();

		 			List SubIdList = objClassSection.getSUBid(CSid,objLogin);
		 			System.out.println("SubIdList.getItemCount() "
		 					+ SubIdList.getItemCount());
		 			for (int i = 0; i < SubIdList.getItemCount(); i++) {
		 				System.out.println("Integer.parseInt(SubIdListItem(i)))"
		 						+ Integer.parseInt(SubIdList.getItem(i)));
		 				String sub_1 = objSubject.subject(Integer
		 						.parseInt(SubIdList.getItem(i)));

		 				sub.add(sub_1);
		 			}
		 			System.out.println("sub.getItemCount() "
		 					+ sub.getItemCount());
		 			for (int i = 0; i < sub.getItemCount(); i++) {
		 				System.out.println(sub.getItem(i));
		 			}

		 			DefaultComboBoxModel cbm1 = new DefaultComboBoxModel(
		 					new String[] { null });

		 			for (int i = 0; i < sub.getItemCount(); i++) {

		 				cbm1.insertElementAt(sub.getItem(i), i);
		 			}

		 			comboBox_1.setModel(cbm1);
		 			lblNewLabel.setVisible(true);
		 			comboBox_1.setVisible(true);
		 		}
		 	}
		 });
		 
		
		 		
		 		JLabel lblClasssection = new JLabel("Class-Section:");
		 		lblClasssection.setBounds(10, 47, 103, 14);
		 		panel.add(lblClasssection);
		 lblNewLabel.setVisible(false);
		
				comboBox_1.setVisible(false);

		/*
		 * for (int i = 0; i < CS.getItemCount(); i++) {
		 * System.out.println(CS.getItem(i) + " " + sub.getItem(i)); }
		 */

		DefaultComboBoxModel cbm = new DefaultComboBoxModel(
				new String[] { null });

		for (int i = 0; i < CS.getItemCount(); i++) {

			cbm.insertElementAt(CS.getItem(i), i);
		}
		 comboBox.setModel(cbm);
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String comboClasSec = (String) comboBox.getSelectedItem();
				String tempSubject = (String) comboBox_1.getSelectedItem();
				// System.out.println("comboClasSecssssssssssssssssssssssssss "
				// + comboClasSec);

				if (comboClasSec != null && tempSubject != null) {

					int clas;
					char section = ' ';
					String tempClass = comboClasSec.substring(0, 1);
					String tempSec = comboClasSec.substring(2, 3);

					clas = Integer.parseInt(tempClass);

					section = tempSec.charAt(0);

					Subject objSubject = new Subject();

					classSection objClassSection = new classSection();

					objSubject.setName(tempSubject);

					objClassSection.setClas(clas);
					objClassSection.setSection(section);
					test_examsFrame objTest_examsFrame = new test_examsFrame(
							objClassSection, CS, sub, objSubject, TeaId,
							objLogin, objTestExam, CSSUB);
					setVisible(false);

					objTest_examsFrame.setVisible(true);
				}else if (comboBox.getSelectedItem()==null) {
					Messages msg=new Messages();
					msg.Failure("Select Class/Section","Note");
				} 
				else if (comboBox_1.getSelectedItem()==null)  {
				Messages msg=new Messages();
				msg.Failure("Select Subject","Note");
				}

			}
		});
		btnOk.setBounds(297, 438, 89, 23);
		contentPane.add(btnOk);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				TeacherRollFrame objTeacherRollFrame=new TeacherRollFrame(CSSUB);
				dispose();
				objTeacherRollFrame.setVisible(true);

			}

		});
		btnBack.setBounds(392, 438, 89, 23);
		contentPane.add(btnBack);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(286, 53, 253, 2);
		contentPane.add(separator);
		
		JLabel lblStudentAcadamics = new JLabel("Student Acadamics");
		lblStudentAcadamics.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentAcadamics.setBounds(304, 24, 226, 27);
		contentPane.add(lblStudentAcadamics);
		
		

	}
}
