package gui;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import algo.Login;
import algo.Student;
import algo.Subject;
import algo.Teacher;
import algo.classSection;
import algo.testExam;

import javax.swing.JSeparator;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.Icon;

public class showExamMarks extends JFrame {

	private JPanel contentPane;
	private updateExam objUpdateExam;
	private int row;

	private classSection objClassSection;
	private Student objStudent;
	private Teacher objTeacher;
	private String address = "G:\\School_Management_System\\SchoolManagementSystem\\src\\images\\";
	private boolean insertStatus;

	public showExamMarks(int n, String t, classSection objClassSection,
			List CS, List sub, Subject objSubject, List TeaId, int objLogin,
			testExam objTestExam, ResultSet CSSUB) {
		// objUpdateExam = null;
		objTeacher = new Teacher();

		// objupdateExam = new updateExam();
		objStudent = new Student();
		int clas = objClassSection.getClas();
		char Sec = objClassSection.getSection();
		int totalStudent = 0;
		List tempListStuName = objStudent.getStudentsIds(clas, Sec);
		// this.objUpdateExam = obj;
		int StuTtl = objStudent.getRegistrationNo().length;

		int[] Rnum = objStudent.getRegistrationNo();
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

		objUpdateExam = new updateExam(objClassSection, CS, sub, objSubject,
				TeaId, objLogin, objTestExam, CSSUB);
		contentPane.setLayout(null);

		JLabel label_1 = new JLabel(new ImageIcon(((new ImageIcon(address
				+ "update.png")).getImage()).getScaledInstance(60, 60,
				java.awt.Image.SCALE_SMOOTH)));
		label_1.setBounds(239, 11, 73, 58);
		contentPane.add(label_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 87, 735, 340);
		// scrollPane.setEnabled(false);
		// scrollPane.setBounds(10, 80, 414, 287);
		contentPane.add(scrollPane);

		// //////////////
		JTable table = new JTable();

		// /////////
		scrollPane.setViewportView(table);

		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				System.out.println("row1111 " + row + " " + column);

				if (column == 0
						|| (column == 1 && (row == Rnum.length + 1 || row == Rnum.length))) {
					return false;

				} else {
					return true;
				}
			}
		};

		table.setModel(model);

		// int Enum = objUpdateExam.getExamNumber();
		// String type = objUpdateExam.getExamType();

		System.out.println("ENUMN " + n + " type " + t);

		// AddColumn objAddColumn = new AddColumn();
		// ResultSet rs = objAddColumn.getExamMarks(n, t);

		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		cellSelectionModel
				.addListSelectionListener(new ListSelectionListener() {
					private Object selectedData;

					public void valueChanged(ListSelectionEvent e) {
						selectedData = null;

						// int[] selectedRow = table.getSelectedRows();
						// int[] selectedColumns = table.getSelectedColumns();
						// System.out.println("selectedRow.length "+selectedRow.length+" selectedColumns.length "+selectedColumns.length);
						int row_1 = table.getSelectedRow() - 1;
						int col_1 = table.getSelectedColumn();
						int tempRow_1 = table.getSelectedRow();
						/*
						 * if ((tempRegNo.length-1)!=tempRow_1){ for (int i = 0;
						 * i < selectedRow.length; i++) { for (int j = 0; j <
						 * selectedColumns.length; j++) { selectedData =
						 * (String) table.getValueAt(selectedRow[i],
						 * selectedColumns[j]); } } }
						 */
						System.out.println("tempRow_1 " + tempRow_1);
						System.out.println("Rnum.length " + Rnum.length);
						System.out.println("COL " + col_1 + " row " + row_1);

						if (tempRow_1 < Rnum.length && col_1 != 0
								&& tempRow_1 < Rnum.length) {

							if (row_1 == -1)
								row_1 = 0;

							if (col_1 != 0) {

								selectedData = (String) table.getValueAt(row_1,
										col_1);
								if (selectedData.equals("-")
										|| selectedData.equals(" ")) {
									System.out.println("PPPPPPPPPPPPPPPPp");
								} else {

									System.out.println("Selected:AAAAAAAAAA "
											+ selectedData);
									if (row_1 >= 0 && selectedData != null) {
										System.out.println("row_1 " + row_1
												+ " StuTtl " + StuTtl);
										if ((StuTtl - 1) == row_1) {
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
										int ChkTtl = objTestExam.ExamTotal(
												Rnum[0], t, n);
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
							}
						} else if (Rnum.length - 1 == tempRow_1 && col_1 != 0) {

							System.out.println("ASDASDASD");
							System.out.println("Selected:AAAAAAAAAA "
									+ selectedData);
							if (row_1 >= 0 && selectedData != null) {
								System.out.println("row_1 " + row_1
										+ " StuTtl " + StuTtl);

								byte[] bytes2 = null;
								try {
									bytes2 = ((String) selectedData)
											.getBytes("US-ASCII");
								} catch (UnsupportedEncodingException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								for (int i = 0; i < bytes2.length; i++) {
									if (bytes2[i] >= 48 && bytes2[i] <= 57
											|| bytes2[i] == 46) {
										insertStatus = true;

									} else {
										insertStatus = false;
										table.setValueAt("0", row_1, col_1);
										Messages msg = new Messages();
										msg.Warning("Invalid Entry", "Invalid");
										break;
									}
								}
								System.out.println("AAAAAAAAAAAAA " + row_1);
								String q = (String) model.getValueAt(row_1,
										col_1);
								System.out.println("QQQQQ " + q);
								int ChkTtl = objTestExam.ExamTotal(Rnum[0], t,
										n);
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

						} else if (col_1 != 0 && tempRow_1 < Rnum.length) {
							System.out.println("ELSE IF " + col_1);
							selectedData = (String) table.getValueAt(row_1,
									col_1);
							if (selectedData.equals("-")) {

							} else {

								System.out.println("Selected:AAAAAAAAAA "
										+ selectedData);
								if (row_1 >= 0 && selectedData != null) {
									System.out.println("row_1 " + row_1
											+ " StuTtl " + StuTtl);

									byte[] bytes2 = null;
									try {
										bytes2 = ((String) selectedData)
												.getBytes("US-ASCII");
									} catch (UnsupportedEncodingException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									for (int i = 0; i < bytes2.length; i++) {
										if (bytes2[i] >= 48 && bytes2[i] <= 57
												|| bytes2[i] == 46) {
											insertStatus = true;

										} else {
											insertStatus = false;
											table.setValueAt("0", row_1, col_1);
											Messages msg = new Messages();
											msg.Warning("Invalid Entry",
													"Invalid");
											break;
										}
									}
									System.out
											.println("AAAAAAAAAAAAA " + row_1);
									String q = (String) model.getValueAt(row_1,
											col_1);
									System.out.println("QQQQQ " + q);
									int ChkTtl = objTestExam.ExamTotal(Rnum[0],
											t, n);
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
						}

					}

				});

		String colName = t + " " + Integer.toString(n);

		model.addColumn("Reg_No");
		model.addColumn(colName);
		int reg = 0;
		int marks = 0;
		row = 0;
		List ls = objTestExam.ExamMarks(n, t);
		System.out.println("ls.getItemCount() " + ls.getItemCount());
		int total = 0;
		String date = null;
		Vector<String> v = new Vector<String>();
		Vector<String> dateVector = new Vector<String>();

		v.add(" ");
		dateVector.add(" ");
		for (int i = 0; i < Rnum.length; i++) {

			model.addRow(new Object[0]);
			System.out.println("Rnum[i] " + Rnum[i]);
			model.setValueAt(Rnum[i], row, 0);

			model.setValueAt(ls.getItem(i), row, 1);
			total = objTestExam.ExamTotal(Rnum[Rnum.length - 1], t, (i + 1));
			date = objTestExam.ExamDate(Rnum[Rnum.length - 1], t, (i + 1));
			v.add("Total Marks: " + total);
			dateVector.add("Date: " + date);
			row++;
		}
		if (v.size() > 1) {
			System.out.println("VECTOR");
			model.addRow(v);
			model.addRow(dateVector);
			// new test_examsFrame(objClassSection, CS, sub, objSubject, TeaId,
			// objLogin, objTestExam);

		}

		/*
		 * String[] dataItem = new String[10];
		 * 
		 * for (int i = 0; i < 10; i++) { dataItem[i] = Integer.toString(i); }
		 * JComboBox Combo = new JComboBox(dataItem);
		 * 
		 * TableColumn Column = table.getColumnModel().getColumn(1);
		 * Column.setCellEditor(new DefaultCellEditor(Combo));
		 */
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("UPDATE");
				// System.out.println("Rnum.length " + row);
				// int rowIndex = table.getSelectedRow();
				// int colIndex = table.getSelectedColumn();
				int rowIndex = 0;
				int colIndex = 1;
				// System.out.println(model.getValueAt(rowIndex, colIndex));
				//

				// System.out.println("marks " + marks);

				String examType = t.substring(0, 4);

				for (int i = 0; i < Rnum.length; i++) {

					// String type=model.getValueAt(i,
					// colIndex).getClass().getName();
					boolean checkType = model.getValueAt(i, colIndex) instanceof String;

					if (checkType) {

						String mark = (String) model.getValueAt(i, colIndex);
						byte[] bytes = null;
						try {
							bytes = mark.getBytes("US-ASCII");
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						boolean insertStatus = false;
						for (int i1 = 0; i1 < bytes.length; i1++) {
							if (bytes[i1] >= 48 && bytes[i1] <= 57
									|| bytes[i1] == 46) {
								insertStatus = true;
							} else {
								insertStatus = false;

								System.out.println("MASLA paey geya");
								break;
							}
						}
						// System.out.println("Float.parseFloat(mark) "+Float.parseFloat(mark));
						if (insertStatus) {

							objTeacher.teacherUpdate(Rnum[i], n, t,
									Float.parseFloat(mark));

						}

						// objAddColumn.updateExam(Rnum[i], n, t,
						// Integer.parseInt(mark));
					} else {
						System.out.println("model.getValueAt(i, colIndex) "
								+ model.getValueAt(i, colIndex));
						objTeacher.teacherUpdate(Rnum[i], n, t,
								(float) model.getValueAt(i, colIndex));
						System.out.println();
						// objAddColumn.updateExam(Rnum[i], n, t,
						// (int) model.getValueAt(i, colIndex));
					}

					if (rowIndex <= (row - 1)) {
						rowIndex++;
					}

				}
				setVisible(false);
				Messages objMessages = new Messages();
				objMessages.success("Marks Updated", "Updation");
				new test_examsFrame(objClassSection, CS, sub, objSubject,
						TeaId, objLogin, objTestExam, CSSUB).setVisible(true);
			}

		});
		btnNewButton.setBounds(288, 438, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new test_examsFrame(objClassSection, CS, sub, objSubject,
						TeaId, objLogin, objTestExam, CSSUB).setVisible(true);
			}
		});
		btnBack.setBounds(387, 438, 89, 23);
		contentPane.add(btnBack);

		JSeparator separator = new JSeparator();
		separator.setBounds(287, 58, 253, 2);
		contentPane.add(separator);

		JLabel lblUpdateMarks = new JLabel("Update Marks");
		lblUpdateMarks.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUpdateMarks.setBounds(337, 29, 170, 27);
		contentPane.add(lblUpdateMarks);

	}
}
