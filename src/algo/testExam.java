package algo;

import gui.Messages;

import java.awt.List;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.testExamDB;

/**
 * Contain all the information student acadamics
 * 
 * @author MuhammadAbuBakar
 *
 */
public class testExam {

	private int[] term;
	/** contain the array of the terms */
	private int[] quiz;
	/** contain the array of the quiz */
	private List quizMarks;
	/** contain the List of the quizes marks */
	private List termMarks;
	/** contain the List of the term marks */
	private int examCount;
	/** count of number of quizes or terms */
	private int total;
	/** contain info about the total numbers of quiz or term */
	private int examDate;

	/** contain info about the date of quiz or term */

	/**
	 * 
	 * @return the quiz or term of the date
	 */
	public int getExamDate() {
		return examDate;
	}

	/**
	 * 
	 * @param examDate
	 *            set the quiz or term date to the class variable examDate
	 */
	public void setExamDate(int examDate) {
		this.examDate = examDate;
	}

	/**
	 * 
	 * @return the total number of the quiz or the term
	 */

	public int getTotal() {
		return total;
	}

	/**
	 * 
	 * @param total
	 *            set the total to the class variable total
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * 
	 * @return the List of the related term against specific student
	 */
	public List getTermMarks() {
		return termMarks;
	}

	/**
	 * 
	 * @param termMarks
	 *            set the marks of the student to the class variable termMarks
	 */
	public void setTermMarks(List termMarks) {
		this.termMarks = termMarks;
	}

	/**
	 * 
	 * @return the total number of quizes or the terms
	 */
	public int getExamCount() {
		return examCount;
	}

	/**
	 * 
	 * @param examCount
	 *            set the total number fo quiz of term to the class variable
	 */
	public void setExamCount(int examCount) {
		this.examCount = examCount;
	}

	/**
	 * 
	 * @return return the total terms
	 */
	public int[] getTerm() {
		return term;
	}

	/**
	 * 
	 * @param term
	 *            set the total terms
	 */

	public void setTerm(int[] term) {
		this.term = term;
	}

	/**
	 * 
	 * @return return the total quiz
	 */

	public int[] getQuiz() {
		return quiz;
	}

	/**
	 * 
	 * @param quiz
	 *            set the total quiz
	 */
	public void setQuiz(int[] quiz) {
		this.quiz = quiz;
	}

	/**
	 * 
	 * @return the total marks related to that specific quiz
	 */
	public List getquizMarks() {
		return quizMarks;
	}

	/**
	 * 
	 * @param quizMarks
	 *            contain the total marks of quiz and set to the class variable
	 *            quizMarks
	 */

	public void setquizMarks(List quizMarks) {
		this.quizMarks = quizMarks;
	}

	/**
	 * return date of specific type of exam
	 * 
	 * @param Rid
	 *            Reg_no of the student
	 * @param type
	 *            can be either quiz or term
	 * @param examCount
	 *            contain the total number of the quiz or term
	 * @return date related to above parameters
	 */
	public String ExamDate(int Rid, String type, int examCount) {
		testExamDB objTestExamDB = new testExamDB();
		ResultSet rs = objTestExamDB.getExamDate(Rid, type, examCount);
		String Date = null;
		try {
			while (rs.next()) {
				Date = rs.getString("examDate");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Date;
	}

	/**
	 * return the total number of the quiz or term
	 * 
	 * @param Rid
	 *            Reg_no of the student
	 * @param type
	 *            type can be either quiz or term
	 * @param examCount
	 *            contain the number of the quiz or term
	 * @return the total numbers related to the above paramaters
	 */
	public int ExamTotal(int Rid, String type, int examCount) {
		testExamDB objTestExamDB = new testExamDB();
		ResultSet rs = objTestExamDB.getExamTotal(Rid, type, examCount);
		int total = 0;
		try {
			while (rs.next()) {
				total = rs.getInt("ExamTotal");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * give the marks of the quiz or term
	 * 
	 * @param n
	 *            quiz or term number
	 * @param t
	 *            can be quiz or term
	 * @return marks of quiz or term with his number
	 */

	public List ExamMarks(int n, String t) {
		testExamDB objTestExamDB = new testExamDB();
		List ls = new List();
		ResultSet rs = objTestExamDB.getExamMarks(n, t);
		int reg = 0;
		String marks = null;
		try {
			while (rs.next()) {
				marks = rs.getString("Marks");

				ls.add((marks));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ls;
	}

	/**
	 * check is terms grater than 3
	 * 
	 * @param reg
	 *            Reg_no of the Student
	 * @param examType
	 *            can be quiz or term
	 * @param SubId
	 *            Subject ID related to the Student
	 * @param total
	 *            number of the quiz or the term
	 * @param examDate
	 *            date of the term or the quiz
	 * @return true if terms less than 3 otherwise false
	 */
	public boolean generateColumn(int reg, String examType, int SubId,
			int total, String examDate) {
		testExamDB objTestExamDB = new testExamDB();
		int count = 0;
		boolean checkTermCount = true;
		if (examType == "quiz") {
			objTestExamDB.AddQuiz(reg, SubId, total, examDate);
		} else if (examType == "term") {
			ResultSet rs = objTestExamDB.getCountTerm(reg, SubId);
			try {
				while (rs.next()) {
					count = (rs.getInt("count"));
					// System.out.println("count " + count);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (count <= 2) {
				objTestExamDB.AddTerm(reg, SubId, total, examDate);
				checkTermCount = true;
			} else {
				checkTermCount = false;
			}
		}
		return checkTermCount;
	}

	/**
	 * 
	 * get the marks of term of specific student
	 * 
	 * @param reg
	 *            Reg_no of the Student
	 */
	public void TermColumn(int reg) {
		testExamDB objTestExamDB = new testExamDB();
		ResultSet rs = objTestExamDB.getTermColumn(reg);
		List temp = new List();
		termMarks = new List();
		String tempMarks = null;

		try {
			while (rs.next()) {
				tempMarks = rs.getString("Marks");

				temp.add((tempMarks));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setTermMarks(temp);

	}

	/**
	 * update the marks of the student
	 * 
	 * @param reg
	 *            ID of the student
	 * @param Enumber
	 *            quiz or term number
	 * @param type
	 *            can be quiz or term
	 * @param marks
	 *            previous marks
	 */

	public void update(int reg, int Enumber, String type, float marks) {
		testExamDB objTestExamDB = new testExamDB();
		objTestExamDB.updateExam(reg, Enumber, type, marks);
	}

	/**
	 * 
	 * get the marks of quiz of specific student
	 * 
	 * @param reg
	 *            Reg_no of the Student
	 */

	public void QuizColumn(int reg) {
		testExamDB objTestExamDB = new testExamDB();
		ResultSet rs = objTestExamDB.getQuizColumn(reg);
		List temp = new List();
		quizMarks = new List();
		String tempMarks = null;

		try {
			while (rs.next()) {
				tempMarks = rs.getString("Marks");
				// System.out.println("tempMarks "+tempMarks );
				temp.add((tempMarks));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setquizMarks(temp);

	}

	/**
	 * 
	 * insert the marks exam total and the exam date
	 * 
	 * @param eCount
	 *            Exam number
	 * @param Name
	 *            type of the exam can be quiz or term
	 * @param marks
	 *            marks of the student
	 * @param reg
	 *            ID of the Student
	 * @param examTotal
	 *            term or quiz total
	 * @param examDate
	 *            term or quiz date
	 */
	public void insert(int eCount, String Name, String marks, int reg,
			int examTotal, String examDate) {
		testExamDB objTestExamDB = new testExamDB();
		objTestExamDB.insertRecord(eCount, Name, marks, reg, examTotal,
				examDate);

	}

	/**
	 * Count of the quiz or term
	 * 
	 * @param reg
	 *            ID of the Student
	 * @param type
	 *            type of the exam
	 * @param Sid
	 *            Teacher ID
	 */
	public void QuizTermNumbers(int reg, String type, int Sid) {
		List ls = new List();
		testExamDB objTestExamDB = new testExamDB();

		ResultSet rs = objTestExamDB.getQuizTermNumbers(reg, type, Sid);
		int temp = 0;
		try {
			while (rs.next()) {

				temp = (rs.getInt("ExamCount"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setExamCount(temp);
	}

	/**
	 * Delete the quiz or the term
	 * 
	 * @param reg
	 *            ID of the Student
	 * @param number
	 *            number of the quiz or term
	 * @param type
	 *            can be quiz or term
	 * @param Sid
	 *            Teacher ID
	 */

	public void deleteColumn(int reg, int number, String type, int Sid) {
		testExamDB objTestExamDB = new testExamDB();
		objTestExamDB.delete(reg, number, type, Sid);
		adjustExamCount(reg, number, type, Sid);
	}

	/**
	 * Readjust the quiz or term numbers after deletion
	 * 
	 * @param reg
	 *            ID of the Student
	 * @param number
	 *            number of the quiz or term
	 * @param type
	 *            can be quiz or term
	 * @param Sid
	 *            Teacher ID
	 */
	public void adjustExamCount(int reg, int number, String type, int Sid) {
		testExamDB objTestExamDB = new testExamDB();
		if (type.equals("quiz")) {

			ResultSet rs = objTestExamDB.getCountQuiz(reg, Sid);
			int temp = 0;
			try {
				while (rs.next()) {
					temp = (rs.getInt("count"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (number < temp) {

				for (int i = 0; i < (temp - number); i++) {
					objTestExamDB.adjustCount((number + 1 + i), type, reg);
				}
			}
		} else if (type.equals("term")) {

			ResultSet rs = objTestExamDB.getCountTerm(reg, Sid);
			int temp = 0;
			try {
				while (rs.next()) {
					temp = (rs.getInt("count"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (number < temp) {

				for (int i = 0; i < (temp - number); i++) {
					objTestExamDB.adjustCount((number + 1 + i), type, reg);
				}

			}
		}
	}

	/**
	 * 
	 * @param reg
	 *            ID of the Student
	 * @param SubId
	 *            ID of the Teacher
	 * @return bolean value true if term less than 3
	 * 
	 */
	public boolean CountTerm(int reg, int SubId) {
		testExamDB objTestExamDB = new testExamDB();
		int count = 0;
		boolean checkTermCount = true;
		ResultSet rs = objTestExamDB.getCountTerm(reg, SubId);
		try {
			while (rs.next()) {
				count = (rs.getInt("count"));
				// System.out.println("count " + count);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (count <= 2) {

			checkTermCount = true;
		} else {
			checkTermCount = false;
		}
		return checkTermCount;

	}

	/**
	 * Generate grades when 3 terms are complete
	 * 
	 * @param reg
	 *            ID of the Student
	 * @param Ereg
	 *            ID of the Teacher
	 */

	public void generateGrades(int reg, int Ereg) {
		testExamDB objTestExamDB = new testExamDB();
		ResultSet rs = objTestExamDB.getMarks(reg, Ereg);
		float total = 0;
		String tempTotal = null;
		try {
			while (rs.next()) {
				tempTotal = rs.getString("Marks");
				System.out.println("tempTotal " + tempTotal);
				boolean insertStatus = false;
				byte[] bytes2 = null;
				try {
					bytes2 = tempTotal.getBytes("US-ASCII");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				for (int i = 0; i < bytes2.length; i++) {
					if (bytes2[i] >= 48 && bytes2[i] <= 57 || bytes2[i] == 46) {
						insertStatus = true;

					} else {
						insertStatus = false;

						break;
					}
				}

				if (!insertStatus) {
					System.out.println("--------------------");
					total = total + 0;
				} else if (insertStatus) {
					System.out.println("TOTALLLLL");
					total += Float.parseFloat(tempTotal);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String grade;
		if (total > 90 * 3) {
			grade = "A";
			objTestExamDB.insertGrage(reg, Ereg, grade);

		} else if (total >= 85 * 3 && total <= 90 * 3) {
			grade = "A-";
			objTestExamDB.insertGrage(reg, Ereg, grade);

		} else if (total >= 80 * 3 && total < 85 * 3) {
			grade = "B+";
			objTestExamDB.insertGrage(reg, Ereg, grade);

		} else if (total >= 75 * 3 && total < 80 * 3) {
			grade = "B";
			objTestExamDB.insertGrage(reg, Ereg, grade);

		} else if (total >= 70 * 3 && total < 75 * 3) {
			grade = "B-";
			objTestExamDB.insertGrage(reg, Ereg, grade);

		} else if (total >= 65 * 3 && total < 70 * 3) {
			grade = "C+";
			objTestExamDB.insertGrage(reg, Ereg, grade);

		} else if (total >= 60 * 3 && total < 65 * 3) {
			grade = "C";
			objTestExamDB.insertGrage(reg, Ereg, grade);

		} else if (total >= 55 * 3 && total < 60 * 3) {
			grade = "C-";
			objTestExamDB.insertGrage(reg, Ereg, grade);

		} else if (total >= 50 * 3 && total < 55 * 3) {
			grade = "D+";
			objTestExamDB.insertGrage(reg, Ereg, grade);
		} else if (total >= 45 * 3 && total < 50 * 3) {
			grade = "D";
			objTestExamDB.insertGrage(reg, Ereg, grade);
		} else if (total >= 40 * 3 && total < 45 * 3) {
			grade = "D-";
			objTestExamDB.insertGrage(reg, Ereg, grade);
		} else {
			grade = "F";
			objTestExamDB.insertGrage(reg, Ereg, grade);

		}
		System.out.println("BACHE K MARKS " + total);
	}

	/**
	 * Get grades of the Student
	 * 
	 ** 
	 * @param reg
	 *            ID of the Student
	 * @param Ereg
	 *            ID of the Teacher
	 * @return Grade of the student
	 */

	public String getGrade(int reg, int Ereg) {
		testExamDB objTestExamDB = new testExamDB();
		ResultSet rs = objTestExamDB.getGradeDB(reg, Ereg);
		String g = null;
		try {
			while (rs.next()) {
				g = rs.getString("Grade");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return g;
	}

	/**
	 * 
	 * get the max number of quiz
	 * 
	 * 
	 * *
	 * 
	 * @param reg
	 *            ID of the Student
	 * @param Sid
	 *            ID of the Teacher
	 * @return
	 */
	public int count(int reg, int Sid) {
		testExamDB objTestExamDB = new testExamDB();
		ResultSet rs = objTestExamDB.getCountQuiz(reg, Sid);
		int temp = 0;
		try {
			while (rs.next()) {
				temp = (rs.getInt("count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * Grades is generated or not
	 * 
	 * @param reg
	 *            ID of the Student
	 * @return false if grades is not generated
	 */
	public boolean checkGradeStatus(int reg) {
		testExamDB objTestExamDB = new testExamDB();
		ResultSet rs = objTestExamDB.checkGradeStatusDB(reg);
		String temp = null;
		try {
			while (rs.next()) {
				temp = (rs.getString("Grade"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (temp == "I" || temp == null) {
			return false;
		} else
			return true;

	}

	public void getExamTotal() {
	}
}
