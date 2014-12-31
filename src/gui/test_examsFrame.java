package gui;

import java.awt.Font;
import java.awt.List;
//import algo.Quries;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import algo.Student;
import algo.Subject;
import algo.Teacher;
import algo.classSection;
import algo.testExam;

public class test_examsFrame extends JFrame {

	private String columnName;
	private showClassSection objShowClassSection;
	private boolean checkQuiz;
	private boolean checkTerm;
	private boolean checkColClicked;
	// private int[] registrationNo;
	private String CS;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	private Student objStudent;
	// private testExam objTestExam;
	private classSection objClassSection;
	private Teacher objTeacher;
	DatePanel datePanel;

	public String getCS() {
		return CS;
	}

	public void setCS(String cS) {
		CS = cS;
		// System.out.println("CS " + CS);
	}

	private DefaultTableModel model;

	private int[] tempRegNo;

	private JButton btnNewButton;
	private JTextField textField;

	private JButton btnAddTerm;

	private JButton btnNewButton_4;

	private boolean checkAgain;

	private boolean insertStatus;

	private boolean checkDate;

	private boolean checkTotal;

	private int position_1;

	private boolean Ggrade;

	private JPanel panel_2;

	private static int ShowGradesCheck;

	// private int total;

	public void setColumnName(String columnName) {

		this.columnName = columnName;

	}

	// /awaein////

	public String getCName() {

		return this.columnName;
	}

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { test_examsFrame frame = new
	 * test_examsFrame(); frame.setVisible(true); } }); }
	 * 
	 * public test_examsFrame(){
	 * 
	 * }
	 */
	public test_examsFrame(classSection objClassSection, List CS, List sub,
			Subject objSubject, List TeaId, int objLogin, testExam objTestExam,
			ResultSet CSSUB) {
		setTitle("Acadamics");
		ImageIcon img = new ImageIcon(address + "sms.png");
		setIconImage(img.getImage());
		setBounds(100, 100, 800, 500);
		setLocation(300, 150);
		setResizable(false);
		setLocation(300, 150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Acadamics");
		getContentPane().setLayout(null);
		// System.out.println("Class Section " + getCS());
		checkQuiz = false;
		checkTerm = false;
		objStudent = new Student();

		objTeacher = new Teacher();

		System.out.println("objSubject.getName() " + objSubject.getName());
		String tempSubName = objSubject.getName();

		// objClassSection=new classSection();
		// System.out.println("TEST EXAM "+objClassSection.getClas()+
		// objClassSection.getSection());
		int clas = objClassSection.getClas();
		char Sec = objClassSection.getSection();
		int CSid = objClassSection.getCSid(clas, Sec);
		int Sid = objSubject.SubID(tempSubName);
		System.out.println("HELLO");
		// datePanel = new DatePanel();
		// datePanel.setVisible(true);
		// datePanel.enable(false);
		// textField.setText(null);

		int TeacherID = objTeacher.getTeaId(CSid, Sid, objLogin);
		System.out.println("TeacherID " + TeacherID);
		int totalStudent = 0;
		List tempListStuName = objStudent.getStudentsIds(clas, Sec);

		tempRegNo = new int[objStudent.getRegistrationNo().length];

		tempRegNo = objStudent.getRegistrationNo();

		System.out.println("tempRegNoAAAAAAAAAa" + tempRegNo.length);
		for (int i = 0; i < tempRegNo.length; i++) {
			System.out.println("tempRegNo " + tempRegNo[i]);

		}

		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TeacherRollFrame obj=new TeacherRollFrame();
				// obj.setVisible(true);
				// Dispose();
				showClassSection objShowClassSection = new showClassSection(CS,
						sub, TeaId, objLogin, CSSUB);
				setVisible(false);
				objShowClassSection.setVisible(true);
			}
		});

		JLabel label_1 = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "acadamic.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		label_1.setBounds(222, 11, 73, 58);
		getContentPane().add(label_1);
		btnNewButton_1.setBounds(683, 438, 87, 20);
		getContentPane().add(btnNewButton_1);
		// if (tempRegNo.length != 0) {

		String stuName = null;

		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(24, 128, 738, 183);
		getContentPane().add(scrollPane);

		// Table
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		final JTable table = new JTable();
		scrollPane.setViewportView(table);

		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Enter", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_2.setBounds(151, 66, 485, 60);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(false);
		JLabel lblEnterTotalMarks = new JLabel("Enter total Marks");
		lblEnterTotalMarks.setBounds(262, 24, 100, 14);
		panel_2.add(lblEnterTotalMarks);

		textField = new JTextField();
		textField.setBounds(358, 21, 100, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		textField.setText("0");

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(6, 27, 46, 14);
		panel_2.add(lblDate);
		textField.setVisible(false);
		lblEnterTotalMarks.setVisible(false);
		lblDate.setVisible(false);

		// total = 0;

		// Model for Table
		position_1 = 0;
		objTestExam.QuizTermNumbers(tempRegNo[0], "quiz", TeacherID);
		int quizCount_1 = objTestExam.getExamCount();
		objTestExam.QuizTermNumbers(tempRegNo[0], "term", TeacherID);
		int termCount_1 = objTestExam.getExamCount();
		position_1 = quizCount_1 + termCount_1 + 2;
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == position_1) {
					return true;

				} else {
					return false;
				}
			}
		};
		table.setModel(model);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// Model for Table

		// ////new quiz//////////////

		int columns = model.getColumnCount();
		System.out.println("columns " + columns);

		insertStatus = false;

		checkDate = false;
		checkTotal = false;
		System.out.println("QQQQQQQQQQQQq " + table.getSelectedColumn() + " "
				+ position_1);

		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		cellSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					private Object selectedData;

					public void valueChanged(ListSelectionEvent e) {
						int row_1 = table.getSelectedRow() - 1;
						int col_1 = table.getSelectedColumn();
						int tempRow_1 = table.getSelectedRow();

						if (tempRow_1 == tempRegNo.length) {
							System.out.println("KKKKKKKKKKKKKKK");
						} else {

							System.out.println("COL " + col_1 + " position_1 "
									+ position_1);
							if (col_1 > position_1 - 1) {

								selectedData = null;

								if (row_1 == -1)
									row_1 = 0;

								if (col_1 != 0) {

									selectedData = (String) table.getValueAt(
											row_1, col_1);
									System.out.println("Selected:AAAAAAAAAA "
											+ selectedData);
									if (row_1 >= 0 && selectedData != null) {
										System.out.println("row_1 " + row_1
												+ " tempRegNo "
												+ tempRegNo.length);
										if ((tempRegNo.length - 1) == row_1) {
											row_1 = tempRow_1;
											System.out.println("INDER paegeya");
										}

										byte[] bytes2 = null;
										try {
											bytes2 = ((String) selectedData)
													.getBytes("US-ASCII");
										} catch (UnsupportedEncodingException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}

										for (int i = 0; i < bytes2.length; i++) {
											if (bytes2[i] >= 48
													&& bytes2[i] <= 57
													|| bytes2[i] == 46) {
												insertStatus = true;

											} else {
												insertStatus = false;
												table.setValueAt("0", row_1,
														col_1);
												Messages msg = new Messages();
												msg.Warning("Invalid Entry",
														"Invalid");
												break;
											}
										}
										String q = (String) model.getValueAt(
												row_1, col_1);
										int ChkTtl = Integer.parseInt(textField
												.getText());
										float getMarks = Float.parseFloat(q);
										if (getMarks > ChkTtl) {
											Messages objMessages = new Messages();
											objMessages
													.Warning(
															"Entered Marks Greater than than the Total",
															"Warning");
											model.setValueAt("0", row_1, col_1);
										}

									}
								}

								if (checkColClicked || checkTerm) {

									Date objDate = new Date();

									int month = objDate.getMonth() + 1;
									int year = objDate.getYear() + 1900;
									int day = objDate.getDate();
									System.out.println("asdasdas " + month
											+ " " + year + " " + day);

									if (datePanel.getDate() > day
											&& datePanel.getMonth() >= month
											&& datePanel.getYear() >= year) {
										Messages objMessages = new Messages();
										objMessages
												.Warning(
														"Selected date is greater than current date",
														"Warning");
										checkDate = false;
									} else {
										checkDate = true;
									}

									if (textField.getText().equals("0")) {
										checkTotal = false;
										Messages objMessages = new Messages();
										objMessages
												.Warning("Enter total ,marks",
														"Warning");
									} else {
										checkTotal = true;
									}
								}

							}
						}
					}

				});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Choose",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(505, 318, 238, 110);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewQuiz = new JButton("New Quiz");
		btnNewQuiz.setBounds(86, 27, 89, 23);
		panel_1.add(btnNewQuiz);

		// ///////End new quiz///////////////

		// //////////add termm/////////////

		btnAddTerm = new JButton("Add Term");
		btnAddTerm.setBounds(86, 61, 89, 23);
		panel_1.add(btnAddTerm);
		btnAddTerm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// int count = ObjAddColumn.AddTerm();
				panel_2.setVisible(true);
				lblEnterTotalMarks.setVisible(true);

				textField.setVisible(true);
				lblDate.setVisible(true);
				datePanel = new DatePanel();
				datePanel.setBounds(43, 16, 212, 33);
				panel_2.add(datePanel);
				boolean check = true;
				for (int index = 0; index < tempRegNo.length; index++) {
					check = objTeacher.teacherGenerateColumn(tempRegNo[index],
							"term", TeacherID, 0, "null");
					// count = ObjAddColumn.AddQuiz(registrationNo[index]);
					if (check == false) {
						System.out.println("NO MORE TERMS");
						check = false;

					}
				}
				int position = 0;
				if (check) {

					objTestExam
							.QuizTermNumbers(tempRegNo[0], "quiz", TeacherID);
					int quizCount = objTestExam.getExamCount();
					objTestExam
							.QuizTermNumbers(tempRegNo[0], "term", TeacherID);
					int termCount = objTestExam.getExamCount();
					position = quizCount + termCount + 1;

					model.addColumn("Term " + (termCount));
					objTestExam
							.QuizTermNumbers(tempRegNo[0], "term", TeacherID);// ///get
					// value
					// later
					int termCount1 = 0;
					termCount1 = objTestExam.getExamCount();

					objTestExam
							.QuizTermNumbers(tempRegNo[0], "quiz", TeacherID);// ///get
					// value
					// later
					int quizCount1 = 0;
					quizCount1 = objTestExam.getExamCount();

					int pos = termCount1 + quizCount1 + 1;

					System.out.println("pos1111111111111 " + pos);
					// table.getColumnModel().getColumn(0).setMaxWidth(50);
					// table.getColumnModel().getColumn(1).setMaxWidth(150);
					for (int i = 2; i <= pos; i++) {

						// table.getColumnModel().getColumn(i).setMaxWidth(200);
					}

					for (int x = 0; x < table.getColumnCount(); x++) {
						table.getColumnModel().getColumn(x)
								.setCellRenderer(centerRenderer);
					}

					checkTerm = true;

					check = true;
				}

				btnNewButton.setEnabled(true);
				btnAddTerm.setEnabled(false);
				btnNewQuiz.setEnabled(false);

			}
		});
		btnNewQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkColClicked = true;

				panel_2.setVisible(true);
				lblEnterTotalMarks.setVisible(true);
				textField.setVisible(true);
				lblDate.setVisible(true);
				datePanel = new DatePanel();
				datePanel.setBounds(43, 16, 212, 33);
				panel_2.add(datePanel);
				for (int index = 0; index < tempRegNo.length; index++) {
					objTeacher.teacherGenerateColumn(tempRegNo[index], "quiz",
							TeacherID, 0, "null");

					// count = ObjAddColumn.AddQuiz(registrationNo[index]);
				}
				objTestExam.QuizTermNumbers(tempRegNo[0], "quiz", TeacherID);
				int quizCount = objTestExam.getExamCount();
				objTestExam.QuizTermNumbers(tempRegNo[0], "term", TeacherID);
				int termCount = objTestExam.getExamCount();

				model.addColumn("Quiz " + (quizCount));
				objTestExam.QuizTermNumbers(tempRegNo[0], "term", TeacherID);// ///get
				// value
				// later
				int termCount1 = 0;
				termCount1 = objTestExam.getExamCount();

				objTestExam.QuizTermNumbers(tempRegNo[0], "quiz", TeacherID);// ///get
				// value
				// later
				int quizCount1 = 0;
				quizCount1 = objTestExam.getExamCount();

				int pos = termCount1 + quizCount1 + 1;
				System.out.println("pos " + pos);
				// table.getColumnModel().getColumn(0).setMaxWidth(50);
				// table.getColumnModel().getColumn(1).setMaxWidth(150);
				for (int i = 2; i <= pos; i++) {

					// table.getColumnModel().getColumn(i).setMaxWidth(200);
				}

				for (int x = 0; x < table.getColumnCount(); x++) {
					table.getColumnModel().getColumn(x)
							.setCellRenderer(centerRenderer);
				}
				checkQuiz = true;

				btnNewButton.setEnabled(true);
				btnNewQuiz.setEnabled(false);
				btnAddTerm.setEnabled(false);

			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Operations",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(27, 317, 463, 110);
		getContentPane().add(panel);
		panel.setLayout(null);

		// ///////END add term////////////////

		btnNewButton = new JButton(new ImageIcon(((new ImageIcon(address
				+ "add.png")).getImage()).getScaledInstance(50, 50,
				java.awt.Image.SCALE_SMOOTH)));
		btnNewButton.setBounds(100, 16, 70, 70);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		btnNewButton.setEnabled(false);

		JButton btnNewButton_2 = new JButton(new ImageIcon(((new ImageIcon(
				address + "remove.png")).getImage()).getScaledInstance(50, 50,
				java.awt.Image.SCALE_SMOOTH)));
		btnNewButton_2.setBounds(290, 16, 70, 70);
		panel.add(btnNewButton_2);
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		JButton btnNewButton_3 = new JButton(new ImageIcon(((new ImageIcon(
				address + "update.png")).getImage()).getScaledInstance(50, 50,
				java.awt.Image.SCALE_SMOOTH)));
		btnNewButton_3.setBounds(195, 16, 70, 70);
		panel.add(btnNewButton_3);

		JLabel lblInsert = new JLabel("Insert");
		lblInsert.setBounds(118, 86, 46, 14);
		panel.add(lblInsert);

		JLabel lblUpdate = new JLabel("Update");
		lblUpdate.setBounds(209, 86, 46, 14);
		panel.add(lblUpdate);

		JLabel lblDelete = new JLabel("Delete");
		lblDelete.setBounds(306, 87, 46, 14);
		panel.add(lblDelete);

		JSeparator separator = new JSeparator();
		separator.setBounds(278, 58, 253, 2);
		getContentPane().add(separator);

		JLabel lblStudentAcadamics = new JLabel("Student Acadamics");
		lblStudentAcadamics.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentAcadamics.setBounds(296, 29, 226, 27);
		getContentPane().add(lblStudentAcadamics);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				updateExam objUpdateExam = new updateExam(objClassSection, CS,
						sub, objSubject, TeaId, objLogin, objTestExam, CSSUB);
				dispose();
				objUpdateExam.setVisible(true);
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				deleteExam objDeleteExam = new deleteExam(objClassSection, CS,
						sub, objSubject, TeaId, objLogin, objTestExam, CSSUB);
				dispose();
				objDeleteExam.setVisible(true);

			}
		});

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("textField.getText()" + textField.getText()
						+ "aaaa");
				System.out.println("QXXXXXXX " + checkDate + " " + checkTotal);
				if ((checkTerm || checkColClicked) && checkDate && checkTotal) {

					System.out.println("IIIIIIIIIIIIIIII");
					String type = null;
					int examCount = 0;
					String date = datePanel.getCompleteDate();
					System.out.println("DAASDA " + date);

					if (checkQuiz == true) {
						type = "quiz";

						objTestExam.QuizTermNumbers(tempRegNo[0], "quiz",
								TeacherID);

						examCount = objTestExam.getExamCount();
						// attr = attr + a;

						checkQuiz = false;
					} else if (checkTerm == true) {
						type = "term";
						objTestExam.QuizTermNumbers(tempRegNo[0], "term",
								TeacherID);

						examCount = objTestExam.getExamCount();
						checkTerm = false;
					}
					int position = 0;
					objTestExam
							.QuizTermNumbers(tempRegNo[0], "quiz", TeacherID);
					int quizCount = objTestExam.getExamCount();
					objTestExam
							.QuizTermNumbers(tempRegNo[0], "term", TeacherID);
					int termCount = objTestExam.getExamCount();
					position = quizCount + termCount + 1;
					// position--;

					// String quiz = null;
					// for (int i = 0; i < table.getRowCount(); i++) {
					// quiz = (String) model.getValueAt(0, position);
					// System.out.println("IN LOOP");

					for (int row = 0; row < tempRegNo.length; row++) {
						System.out.println("ROOOOWWWWWWWWWww " + row);
						String q = (String) model.getValueAt(row, position);

						if (q == null) {
							q = "-";

							System.out
									.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

							// btnNewButton.setEnabled(false);

							int ttl = Integer.parseInt(textField.getText());
							System.out.println("INSERT KARO");
							// System.out.println("marksSSSS "+marks);
							System.out.println("date " + date + " ttl " + ttl);
							objTeacher.teacherInsertRecord(examCount, type, q,
									tempRegNo[row], ttl, date);

							// //////////////////

							checkColClicked = false;
							btnNewButton.setEnabled(false);
							btnNewQuiz.setEnabled(true);
							btnAddTerm.setEnabled(true);
							textField.setVisible(false);
							datePanel.setVisible(false);
							lblEnterTotalMarks.setVisible(false);

							// ///////////////////////////////

							// SwingUtilities.updateComponentTreeUI(btnNewButton);
							// model.fireTableDataChanged();

							// /

							// /

							setVisible(false);

							dispose();
							// setVisible(true);
							System.out.println("ASDASDASDASDASDASDASDASD");
						}
						System.out.println("Q " + q);

						if (q != null) {

							byte[] bytes = null;
							try {
								bytes = q.getBytes("US-ASCII");
							} catch (UnsupportedEncodingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							for (int i = 0; i < bytes.length; i++) {
								if (bytes[i] >= 48 && bytes[i] <= 57
										|| bytes[i] == 46) {
									insertStatus = true;
								} else {
									insertStatus = false;

									break;
								}
							}
							String totalMrks = textField.getText();
							boolean insertStatus1 = false;
							byte[] bytes1 = null;
							try {
								bytes1 = totalMrks.getBytes("US-ASCII");
							} catch (UnsupportedEncodingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							for (int i = 0; i < bytes1.length; i++) {
								if (bytes1[i] >= 48 && bytes1[i] <= 57) {
									insertStatus1 = true;
								} else {
									insertStatus1 = false;

									Messages msg = new Messages();
									msg.Failure("Invalid Entry", "Invalid");
									break;
								}
							}
							boolean chkTtl = true;
							System.out.println("insertStatus1 " + insertStatus1
									+ " insertStatus " + insertStatus);
							if (textField.getText().equals("0")) {
								System.out.println("ZERO");
								Messages msg = new Messages();
								msg.Warning("Enter Total Marks", "Warning");
								chkTtl = false;
							} else if (insertStatus1 && insertStatus) {

								System.out
										.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

								// btnNewButton.setEnabled(false);

								int ttl = Integer.parseInt(textField.getText());
								System.out.println("INSERT KARO");
								// System.out.println("marksSSSS "+marks);
								System.out.println("date " + date + " ttl "
										+ ttl);
								objTeacher.teacherInsertRecord(examCount, type,
										q, tempRegNo[row], ttl, date);

								// //////////////////

								checkColClicked = false;
								btnNewButton.setEnabled(false);
								btnNewQuiz.setEnabled(true);
								btnAddTerm.setEnabled(true);
								textField.setVisible(false);
								datePanel.setVisible(false);
								lblEnterTotalMarks.setVisible(false);

								// ///////////////////////////////

								// SwingUtilities.updateComponentTreeUI(btnNewButton);
								// model.fireTableDataChanged();

								// /

								// /

								setVisible(false);

								dispose();
								// setVisible(true);
								System.out.println("ASDASDASDASDASDASDASDASD");

								// ///////////////////

							} else if (!insertStatus) {
								System.out.println("INVALID ENTRY");
							} else if (!insertStatus1) {
								System.out.println("INVALID ENTRY total marks");
								// row=0;
							}
						}

					}
					new test_examsFrame(objClassSection, CS, sub, objSubject,
							TeaId, objLogin, objTestExam, CSSUB);
					btnNewQuiz.setEnabled(false);
					Messages objMessages = new Messages();
					objMessages.success("Marks Inserted", "Insertion");
				} else {
					Messages objMessages = new Messages();
					objMessages.Warning("Insert Marks", "Insertion");
				}

			}
		});
		boolean check_1 = true;
		check_1 = objTestExam.CountTerm(tempRegNo[0], TeacherID);
		checkAgain = false;
		ShowGradesCheck = 0;
		System.out.println("check_1 " + check_1);
		Vector<String> gradesVector = new Vector<String>();
		String chkGrade1 = objTestExam.getGrade(tempRegNo[0], TeacherID);
		System.out.println("chkGrade1 " + chkGrade1);
		Ggrade = false;
		if (Ggrade && check_1 == false && chkGrade1.equals("I")) {
			System.out.println("Generate Grades");
			btnNewButton_4 = new JButton("Generate Grades");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ShowGradesCheck++;
					checkAgain = true;
					for (int i = 0; i < tempRegNo.length; i++) {
						objTeacher.teacherGenerateGrades(tempRegNo[i],
								TeacherID);
					}

					int qCount = objTestExam.count(tempRegNo[0], TeacherID);
					int tCount = 0;
					int CCposition = 0;
					tCount = objTestExam.getExamCount();
					// model.addColumn("Grades");
					System.out.println("ABUBAKAR");

					for (int i1 = 0; i1 < tempRegNo.length; i1++) {

						// System.out.println("termCount "+termCount);
						// System.out.println("quizCount "+quizCount);
						// System.out.println("i1 "+i1);

						CCposition = 2 + qCount + 3;
						// System.out.println("CCposition "+CCposition);
						// System.out.println("lsTermMarks.getItem(i1) "+lsTermMarks.getItem(i1));
						// System.out.println("i "+i);
						String grade = objTestExam.getGrade(tempRegNo[i1],
								TeacherID);
						System.out.println("GRADE " + grade);
						System.out.println("CCPOSITION " + CCposition);
						System.out.println("quizCount " + qCount);

						// model.setValueAt(grade, i1, CCposition);

					}
					Ggrade = true;
					btnNewButton_4.setVisible(false);

				}
			});
			btnNewButton_4.setBounds(132, 357, 115, 27);
			getContentPane().add(btnNewButton_4);

		}
		// ///END UPdate//////////

		// datePanel.setBounds(48, 51, 212, 33);
		// getContentPane().add(datePanel);

		// String str = datePanel.getCompleteDate();

		// System.out.println("DATEEEEEE " + str);

		// ///////DISPLAY/////////
		// System.out.println("DISPLAY");
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		model.addColumn("REG id");
		// table.getColumnModel().getColumn(0).setMaxWidth(50);
		model.addColumn("Name");

		// table.getColumnModel().getColumn(1).setMaxWidth(50);
		List lsQuizMarks = new List();

		List lsTermMarks = new List();
		String quiz = "Quiz ";
		String term = "Term ";

		int checkQuizCol = 0;
		int checkTermCol = 0;

		Vector<String> v = new Vector<String>();
		Vector<String> dateVector = new Vector<String>();

		v.add(" ");
		v.add(" ");
		dateVector.add(" ");
		dateVector.add(" ");
		// registrationNo = new int[totalStudent];
		// objStudent.getRegistrationNo().length
		for (int i = 0; i < tempRegNo.length; i++) {

			model.addRow(new Object[i]);
			model.setValueAt(tempRegNo[i], i, 0);
			model.setValueAt(tempListStuName.getItem(i), i, 1);
			// System.out.println("tempRegNo[i] "+tempRegNo[i]);
			objTestExam.TermColumn(tempRegNo[i]); // list of term marks
			lsTermMarks = objTestExam.getTermMarks();

			// objTestExam.getQuizCount();

			objTestExam.QuizColumn(tempRegNo[i]); // list of term marks
			lsQuizMarks = objTestExam.getquizMarks();

			objTestExam.QuizTermNumbers(tempRegNo[0], "quiz", TeacherID);// ///get
			// value
			// later
			int quizCount = objTestExam.getExamCount();

			int Cposition = 0;
			System.out.println("quizCount " + quizCount);
			// System.out.println("listOfQuizes.getItemCount() "+listOfQuizes.getItemCount());
			int total = 0;
			String date = null;

			for (int i1 = 0; i1 < quizCount; i1++) {
				Cposition = 2 + i1;
				if (checkQuizCol < quizCount) {
					quiz = quiz + (i1 + 1);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					model.addColumn(quiz);

					// table.getColumnModel().getColumn(Cposition).setMaxWidth(200);
					if (i1 + 1 == quizCount) {
						// table.getColumnModel().getColumn(Cposition).setMaxWidth(200);
					}
					quiz = "Quiz ";
					total = objTestExam.ExamTotal(
							tempRegNo[tempRegNo.length - 1], "quiz", (i1 + 1));
					date = objTestExam.ExamDate(
							tempRegNo[tempRegNo.length - 1], "quiz", (i1 + 1));
					v.add("Total Marks: " + total);
					dateVector.add("Date: " + date);
				}

				// System.out.println("quizCount "+quizCount);

				// System.out.println(lsQuizMarks.getItem(i1)+" " + i+" " +
				// Cposition);
				// /////////////asdasdasdsad////////
				// model.setValueAt(total, 0, Cposition);
				for (int j = 0; j < lsQuizMarks.getItemCount(); j++) {
					System.out.println("lsQuizMarks.getItem(j); "
							+ lsQuizMarks.getItem(j));
				}
				model.setValueAt(lsQuizMarks.getItem(i1), i, Cposition);

				checkQuizCol++;

			}

			objTestExam.QuizTermNumbers(tempRegNo[0], "term", TeacherID);// ///get
			// value
			// later
			int termCount = 0;
			termCount = objTestExam.getExamCount();
			int CCposition = 0;
			for (int i1 = 0; i1 < termCount; i1++) {
				CCposition = 1 + quizCount + (i1 + 1);
				if (checkTermCol < termCount) {
					term = term + (i1 + 1);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					model.addColumn(term);
					// table.getColumnModel().getColumn(CCposition).setMaxWidth(200);
					term = "Term ";
					total = objTestExam.ExamTotal(
							tempRegNo[tempRegNo.length - 1], "term", (i1 + 1));
					date = objTestExam.ExamDate(
							tempRegNo[tempRegNo.length - 1], "term", (i1 + 1));
					v.add("Total Marks: " + total);
					dateVector.add("Date: " + date);

				}
				// System.out.println("termCount "+termCount);
				// System.out.println("quizCount "+quizCount);
				// System.out.println("i1 "+i1);

				// System.out.println("CCposition "+CCposition);
				// System.out.println("lsTermMarks.getItem(i1) "+lsTermMarks.getItem(i1));
				// System.out.println("i "+i);

				model.setValueAt(lsTermMarks.getItem(i1), i, CCposition);

				checkTermCol++;
			}

		}

		// //////
		objTestExam.QuizTermNumbers(tempRegNo[0], "term", TeacherID);// ///get
		// value
		// later
		int termCount11 = 0;
		termCount11 = objTestExam.getExamCount();

		objTestExam.QuizTermNumbers(tempRegNo[0], "quiz", TeacherID);// ///get
		// value
		// later
		int quizCount11 = 0;
		quizCount11 = objTestExam.getExamCount();

		int pos = termCount11 + quizCount11 + 1;
		System.out.println("possssss " + pos);

		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(250);
		for (int iii = 2; iii <= pos; iii++) {

			table.getColumnModel().getColumn(iii).setMaxWidth(300);
		}
		// /////////
		System.out.println("v.size() " + v.size());
		if (v.size() > 2) {
			System.out.println("VECTOR");
			model.addRow(v);
			model.addRow(dateVector);
			// new test_examsFrame(objClassSection, CS, sub, objSubject, TeaId,
			// objLogin, objTestExam);

		}

		System.out.println(" checkAgain " + checkAgain);
		boolean c = objTestExam.CountTerm(tempRegNo[0], TeacherID);
		String chkGrade = objTestExam.getGrade(tempRegNo[0], TeacherID);
		System.out.println("chkGrade " + chkGrade);
		if (/* objTestExam.checkGradeStatus(tempRegNo[0]) && */!c) {
			// /////
			position_1++;

			for (int i = 0; i < tempRegNo.length; i++) {
				objTeacher.teacherGenerateGrades(tempRegNo[i], TeacherID);
			}
			for (int i1 = 0; i1 < tempRegNo.length; i1++) {

				// System.out.println("termCount "+termCount);
				// System.out.println("quizCount "+quizCount);
				// System.out.println("i1 "+i1);

				// System.out.println("CCposition "+CCposition);
				// System.out.println("lsTermMarks.getItem(i1) "+lsTermMarks.getItem(i1));
				// System.out.println("i "+i);
				String grade = objTestExam.getGrade(tempRegNo[i1], TeacherID);
				System.out.println("grade " + grade);
				gradesVector.add(grade);

			}
			btnNewQuiz.setEnabled(false);
			btnAddTerm.setEnabled(false);
			btnNewButton_3.setEnabled(false);
			btnNewButton_2.setEnabled(false);
			// //////
			System.out.println("");
			model.addColumn("Grades", gradesVector);
			// btnNewButton_4.setVisible(false);
		}
		for (int x = 0; x < table.getColumnCount(); x++) {
			table.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
		}
		revalidate();
		repaint();
		setVisible(true);

	}
}
