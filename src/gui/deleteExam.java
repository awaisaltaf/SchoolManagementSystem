package gui;

import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import algo.Student;
import algo.Subject;
import algo.Teacher;
import algo.classSection;
import algo.testExam;

public class deleteExam extends JFrame {

	private JPanel contentPane;

	private Student objStudent;
	private classSection objClassSection;
	private Teacher objTeacher;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";

	public deleteExam(classSection objClassSection, List CS, List sub,
			Subject objSubject, List TeaId, int objLogin, testExam objTestExam,
			ResultSet CSSUB) {
		// objClassSection=new classSection();

		// objupdateExam = new updateExam();
		objStudent = new Student();
		objTeacher = new Teacher();
		int clas = objClassSection.getClas();
		char Sec = objClassSection.getSection();
		int totalStudent = 0;
		String tempSubName = objSubject.getName();
		int Sid = objSubject.SubID(tempSubName);
		int loginId = objLogin;
		int CSid = objClassSection.getCSid(clas, Sec);
		int TeacherID = objTeacher.getTeaId(CSid, Sid, loginId);

		List tempListStuName = objStudent.getStudentsIds(clas, Sec);
		// String tempSubName=objSubject.getName();
		// int Sid=objSubject.SubID(tempSubName);

		// /tempRegNo = new int[objStudent.getRegistrationNo().length];

		// tempRegNo=objStudent.getRegistrationNo();

		int[] tempRegNo = objStudent.getRegistrationNo();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setBounds(100, 100, 800, 500);
		setTitle("Deletion");
		setLocation(300, 150);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Choose", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(179, 149, 417, 204);
		contentPane.add(panel);
		panel.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(151, 114, 177, 20);
		panel.add(comboBox);

		JLabel label = new JLabel("");
		label.setBounds(106, 113, 61, 21);
		panel.add(label);

		ButtonGroup btnGroup = new ButtonGroup();

		JLabel lblNewLabel = new JLabel("Select");
		lblNewLabel.setBounds(70, 110, 42, 27);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JRadioButton rdbtnQuiz = new JRadioButton("Quiz");
		rdbtnQuiz.setBounds(134, 56, 61, 23);
		panel.add(rdbtnQuiz);
		rdbtnQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// System.out.println("ASDASD");
				int size = objStudent.getRegistrationNo().length;
				// System.out.println("size " + size);
				List ls = new List();
				int s = objStudent.getRegistrationNo().length;
				int REG[] = new int[s];
				REG = objStudent.getRegistrationNo();

				objTestExam.QuizTermNumbers(REG[0], "quiz", TeacherID);
				int size1 = objTestExam.getExamCount();
				String[] dataItem = new String[size1];
				DefaultComboBoxModel cbm = new DefaultComboBoxModel(
						new String[] { null });

				String quiz = "quiz ";

				for (int i = 0; i < size1; i++) {
					dataItem[i] = Integer.toString(i + 1);
					dataItem[i] = quiz + dataItem[i];

					cbm.insertElementAt(dataItem[i], i);
				}

				comboBox.setModel(cbm);
				lblNewLabel.setVisible(true);
				label.setText(quiz);
				comboBox.setVisible(true);

				//
			}
		});
		btnGroup.add(rdbtnQuiz);

		JRadioButton rdbtnTerm = new JRadioButton("Term");
		rdbtnTerm.setBounds(222, 56, 67, 23);
		panel.add(rdbtnTerm);
		rdbtnTerm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// System.out.println("ASDASD");
				int size = objStudent.getRegistrationNo().length;
				// System.out.println("size " + size);
				List ls = new List();
				int s = objStudent.getRegistrationNo().length;
				int REG[] = new int[s];
				REG = objStudent.getRegistrationNo();

				objTestExam.QuizTermNumbers(REG[0], "term", TeacherID);
				int size1 = objTestExam.getExamCount();
				String[] dataItem = new String[size1];
				DefaultComboBoxModel cbm = new DefaultComboBoxModel(
						new String[] { null });

				String quiz = "term ";

				for (int i = 0; i < size1; i++) {
					dataItem[i] = Integer.toString(i + 1);
					dataItem[i] = quiz + dataItem[i];

					cbm.insertElementAt(dataItem[i], i);
				}

				comboBox.setModel(cbm);
				lblNewLabel.setVisible(true);
				label.setText(quiz);
				comboBox.setVisible(true);

				//

			}
		});
		btnGroup.add(rdbtnTerm);

		lblNewLabel.setVisible(false);

		comboBox.setVisible(false);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// //////////////////////////////////////////
				int[] temp = objStudent.getRegistrationNo();
				String result = (String) comboBox.getSelectedItem();
				String t = null;

				if (result != null) {
					t = result.substring(0, 4);
					// System.out.println("NULLLLLL");
					if (result.length() == 7) {

						result = result.substring(5, 7);

					} else if (result.length() == 6) {

						result = result.substring(5, 6);
					}

					int examNumber = Integer.parseInt(result);
					// System.out.println("examNumber "+examNumber);
					setVisible(false);

					// System.out.println("NUMBER " + examNumber + " type " +
					// t);

					setVisible(false);
					for (int i = 0; i < temp.length; i++) {
						objTeacher.teacherDelete(temp[i], examNumber, t,
								TeacherID);
						// objTestExam.deleteColumn(temp[i], examNumber, t);
					}
					Messages objMessages = new Messages();
					objMessages.success(t + " deleted", "Deletion");
					// dispose();
					test_examsFrame obj = new test_examsFrame(objClassSection,
							CS, sub, objSubject, TeaId, objLogin, objTestExam,
							CSSUB);
					obj.setVisible(true);
					// objAddColumn.updateExam(r, n, t, marks);

				} else {
					if (rdbtnQuiz.isSelected()) {
						Messages objMessages = new Messages();
						objMessages.Warning("Select quiz", "Selection");
					} else if (rdbtnTerm.isSelected()) {
						Messages objMessages = new Messages();
						objMessages.Warning("Select term", "Selection");
					} else {
						Messages objMessages = new Messages();
						objMessages.Warning("Select quiz/term", "Selection");
					}

				}

				// //////////////////////////////////////////

				/*
				 * String result = (String) comboBox.getSelectedItem(); if
				 * (result != null) {
				 * 
				 * 
				 * 
				 * if (result.length() == 7) { result = result.substring(5, 7);
				 * } else if (result.length() == 6) { result =
				 * result.substring(5, 6); } examNumber =
				 * Integer.parseInt(result); setVisible(false);
				 * 
				 * int n = getExamNumber(); String t = getExamType(); int s =
				 * testExamFrame.getRegistrationNo().length; int REG[] = new
				 * int[s]; REG = testExamFrame.getRegistrationNo();
				 * 
				 * for (int i = 0; i < REG.length; i++) {
				 * objAddColumn.delete(REG[i], n, t); }
				 * 
				 * dispose(); testExamFrame.setVisible(true);
				 * //testExamFrame.main(null); } else{
				 * 
				 * }
				 */
			}
		});
		btnOk.setBounds(293, 436, 89, 23);
		contentPane.add(btnOk);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);

				new test_examsFrame(objClassSection, CS, sub, objSubject,
						TeaId, objLogin, objTestExam, CSSUB).setVisible(true);
			}
		});
		btnBack.setBounds(392, 436, 89, 23);
		contentPane.add(btnBack);

		JSeparator separator = new JSeparator();
		separator.setBounds(274, 58, 253, 2);
		contentPane.add(separator);

		JLabel label_1 = new JLabel("Student Attendence");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_1.setBounds(292, 29, 226, 27);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "Remove.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		label_2.setBounds(213, 11, 73, 58);
		contentPane.add(label_2);

	}
}
