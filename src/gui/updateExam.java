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

public class updateExam extends JFrame {

	private JPanel contentPane;
	private test_examsFrame testExamFrame;
	private String examType;
	private int examNumber;
	private updateExam objupdateExam;

	private Student objStudent;
	
	private classSection objClassSection;
	private Teacher objTeacher;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	private JLabel lblNewLabel;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { updateExam frame = new
	 * updateExam(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	/**
	 * Create the frame.
	 */
	public updateExam(classSection objClassSection, List CS, List sub,
			Subject objSubject, List TeaId, int objLogin,testExam objTestExam,ResultSet CSSUB) {
		// OBJ=new classSection();
		
		// objupdateExam = new updateExam();
		objTeacher=new Teacher();
		objStudent = new Student();
		int clas = objClassSection.getClas();
		char Sec = objClassSection.getSection();
		int totalStudent = 0;
		List tempListStuName = objStudent.getStudentsIds(clas, Sec);
		String tempSubName = objSubject.getName();
		int Sid = objSubject.SubID(tempSubName);
		int loginId=objLogin;
		int CSid = objClassSection.getCSid(clas, Sec);
		int TeacherID = objTeacher.getTeaId(CSid, Sid,loginId);

		// /tempRegNo = new int[objStudent.getRegistrationNo().length];

		// tempRegNo=objStudent.getRegistrationNo();

		int[] tempRegNo = objStudent.getRegistrationNo();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Updation");
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setBounds(100, 100, 800, 500);
		setLocation(300, 150);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// /////////
		
		JLabel label_2 = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "update.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		label_2.setBounds(222, 11, 73, 58);
		contentPane.add(label_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Choose", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(199, 161, 376, 184);
		contentPane.add(panel);
		panel.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(131, 100, 155, 20);
		panel.add(comboBox);
		ButtonGroup btnGroup = new ButtonGroup();
				JRadioButton rdbtnQuiz = new JRadioButton("Quiz");
				rdbtnQuiz.setBounds(125, 52, 61, 23);
				panel.add(rdbtnQuiz);
				rdbtnQuiz.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("ASDASD");
						int size = objStudent.getRegistrationNo().length;
						System.out.println("size " + size);
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
						examType = quiz;
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
						rdbtnTerm.setBounds(213, 52, 67, 23);
						panel.add(rdbtnTerm);
						
								 lblNewLabel = new JLabel("Select");
								lblNewLabel.setBounds(16, 96, 42, 27);
								panel.add(lblNewLabel);
								lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
								
										 label = new JLabel("");
										label.setBounds(56, 99, 61, 21);
										panel.add(label);
								
										lblNewLabel.setVisible(false);
						rdbtnTerm.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								System.out.println("ASDASD");
								int size = objStudent.getRegistrationNo().length;
								System.out.println("size " + size);
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
								examType = quiz;
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

		comboBox.setVisible(false);

		

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String result = (String) comboBox.getSelectedItem();
				String t = null;
			//	t = result.substring(0, 4);
				if (result != null) {
					t = result.substring(0, 4);
					//System.out.println("NULLLLLL");
					if (result.length() == 7) {

						result = result.substring(5, 7);

					} else if (result.length() == 6) {

						result = result.substring(5, 6);
					}

					examNumber = Integer.parseInt(result);
					System.out.println("examNumber " + examNumber);
					setVisible(false);

					//System.out.println("NUMBER " + examNumber + " type " + t);

					setVisible(false);
					showExamMarks obj = new showExamMarks(examNumber, t,
							objClassSection, CS, sub, objSubject, TeaId,
							objLogin,objTestExam,CSSUB);
					// dispose();
					obj.setVisible(true);
					// objAddColumn.updateExam(r, n, t, marks);
				} else {
						if(rdbtnQuiz.isSelected()){
							Messages objMessages=new Messages();
							objMessages.Warning("Select quiz", "Selection");
						}
						else if(rdbtnTerm.isSelected()){
							Messages objMessages=new Messages();
							objMessages.Warning("Select term", "Selection");
						}else{
							Messages objMessages=new Messages();
							objMessages.Warning("Select quiz/term", "Selection");
						}
						
							
				}

			}
		});
		btnOk.setBounds(293, 438, 89, 23);
		contentPane.add(btnOk);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				testExamFrame = new test_examsFrame(objClassSection, CS, sub,
						objSubject, TeaId, objLogin,objTestExam,CSSUB);
				testExamFrame.setVisible(true);
			}
		});
		btnBack.setBounds(392, 438, 89, 23);
		contentPane.add(btnBack);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(270, 58, 253, 2);
		contentPane.add(separator);
		
		JLabel lblUpdation = new JLabel("Updation");
		lblUpdation.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUpdation.setBounds(341, 29, 106, 27);
		contentPane.add(lblUpdation);

		// /////////

	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public void setExamNumber(int examNumber) {
		this.examNumber = examNumber;
	}

	public String getExamType() {
		System.out.println(examType);
		return examType;
	}

	public int getExamNumber() {
		System.out.println(examNumber);
		return examNumber;
	}
}
