package db;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class testExamDB {

	public ResultSet getExamDate(int Rid, String examType, int examCount) {
		String query = "SELECT `examDate` FROM `test_exams` WHERE `Reg_no`='"
				+ Rid + "' && `ExamType` ='" + examType
				+ "'  && `ExamCount` ='" + examCount + "'  ";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = null;
		rs = c.select(query);
		return rs;
	}

	public ResultSet getExamTotal(int Rid, String examType, int examCount) {
		String query = "SELECT `ExamTotal` FROM `test_exams` WHERE `Reg_no`='"
				+ Rid + "' && `ExamType` ='" + examType
				+ "'  && `ExamCount` ='" + examCount + "'  ";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = null;
		rs = c.select(query);
		return rs;
	}

	public ResultSet getExamMarks(int Enumber, String type) {
		String query = "SELECT `Reg_no`,`Marks` FROM `test_exams` WHERE `ExamType`='"
				+ type + "' && `ExamCount` ='" + Enumber + "'  ";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = null;
		rs = c.select(query);
		return rs;

	}

	public void updateExam(int reg, int Enumber, String type, float marks) {
		System.out.println("REG Enumber type marks " + reg + Enumber + type
				+ marks);
		String query = "UPDATE `test_exams` SET `Marks`= '" + marks
				+ "' WHERE `Reg_no`='" + reg + "' && `ExamType`='" + type
				+ "' && `ExamCount` = '" + Enumber + "'";
		connectivelyclass c = new connectivelyclass();
		c.update(query);
	}

	public void insertRecord(int eCount, String Name, String marks, int reg,
			int examTotal, String examDate) {

		System.out.println("examTotal " + examTotal + "examDate " + examDate);
		String query1 = "UPDATE `sms`.`test_exams` SET Marks = '" + marks
				+ "' where ExamType='" + Name + "' && ExamCount='" + eCount
				+ "' && Reg_no='" + reg + "' ";
		connectivelyclass c = new connectivelyclass();
		c.update(query1);
		String query2 = "UPDATE `sms`.`test_exams` SET `ExamTotal`='"
				+ examTotal + "'where ExamType='" + Name + "' && ExamCount='"
				+ eCount + "' && Reg_no='" + reg + "' ";

		c.update(query2);
		String query3 = "UPDATE `sms`.`test_exams` SET `examDate`='" + examDate
				+ "'   where ExamType='" + Name + "' && ExamCount='" + eCount
				+ "' && Reg_no='" + reg + "' ";

		c.update(query3);

	}

	public int nextQuizNumber(int reg, int Sid) {

		connectivelyclass c = new connectivelyclass();
		ResultSet s = getCountQuiz(reg, Sid);
		int count = 0;
		try {
			while (s.next()) {

				count = s.getInt("count");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count++;
		return count;
	}

	public void AddTerm(int reg, int SubId, int total, String examDate) {
		connectivelyclass c = new connectivelyclass();
		ResultSet s = getCountTerm(reg, SubId);
		int count = 0;
		try {
			while (s.next()) {

				count = s.getInt("count");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count++;
		String query = "INSERT INTO `sms`.`test_exams` (`TestId`, `Reg_no`,`EReg_no`, `ExamType`, `ExamCount`, `Marks`,`ExamTotal`,`examDate`,`Grade`) VALUES (default, '"
				+ reg
				+ "','"
				+ SubId
				+ "', 'term','"
				+ count
				+ "' ,'0','"
				+ total + "','" + examDate + "','I')";

		boolean e = c.insert(query);

	}

	public void AddQuiz(int reg, int Sid, int total, String examDate) {
		connectivelyclass c = new connectivelyclass();
		ResultSet s = getCountQuiz(reg, Sid);
		int count = 0;
		try {
			while (s.next()) {

				count = s.getInt("count");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		count++;
		String query = "INSERT INTO `sms`.`test_exams` (`TestId`, `Reg_no`,`EReg_no`, `ExamType`, `ExamCount`, `Marks`,`ExamTotal`,`examDate`,`Grade`) VALUES (default, '"
				+ reg
				+ "','"
				+ Sid
				+ "', 'quiz','"
				+ count
				+ "' ,'-','"
				+ total + "','" + examDate + "','I')";

		boolean e = c.insert(query);

	}

	public ResultSet getCountQuiz(int reg, int Sid) {

		connectivelyclass c = new connectivelyclass();
		String q = "SELECT MAX(`ExamCount`)as count FROM `test_exams` WHERE `ExamType`='quiz' && `Reg_no` ='"
				+ reg + "' && `EReg_no`='" + Sid + "' ";

		ResultSet s = c.select(q);
		return s;
	}

	public ResultSet getCountTerm(int reg, int Sid) {

		connectivelyclass c = new connectivelyclass();
		String q = "SELECT MAX(`ExamCount`)as count FROM `test_exams` WHERE `ExamType`='term' && `Reg_no` ='"
				+ reg + "' && `EReg_no`='" + Sid + "' ";

		ResultSet s = c.select(q);
		return s;
	}

	public ResultSet getTermColumn(int reg) {

		ResultSet rs = null;
		connectivelyclass c = new connectivelyclass();

		String query1 = "SELECT `Marks` FROM `test_exams` WHERE `ExamType` ='term' &&`Reg_no`='"
				+ reg + "' ";

		rs = c.select(query1);

		return rs;

	}

	public ResultSet getQuizColumn(int reg) {

		ResultSet rs = null;
		connectivelyclass c = new connectivelyclass();

		String query1 = "SELECT `Marks` FROM `test_exams` WHERE `ExamType` ='quiz' &&`Reg_no`='"
				+ reg + "' ";

		rs = c.select(query1);

		return rs;

	}

	public ResultSet getQuizTermNumbers(int reg, String type, int Sid) {

		String query = "SELECT `ExamCount` FROM `test_exams` WHERE `ExamType`='"
				+ type
				+ "' &&`Reg_no`='"
				+ reg
				+ "' &&`EReg_no`='"
				+ Sid
				+ "' ";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = null;
		rs = c.select(query);
		return rs;
	}

	public void delete(int reg, int number, String type, int Sid) {
		System.out.println("DELETE ME AGGEYA");
		String query = "DELETE FROM `test_exams` WHERE `Reg_no`='" + reg
				+ "' && `ExamType`='" + type + "' && `ExamCount`='" + number
				+ "'&& `EReg_no`='" + Sid + "'";
		connectivelyclass c = new connectivelyclass();
		c.update(query);

	}

	public void adjustCount(int newCount, String type, int reg) {
		String query1 = "UPDATE `sms`.`test_exams` SET ExamCount = '"
				+ (newCount - 1) + "'   where ExamType='" + type
				+ "' && Reg_no='" + reg + "' && ExamCount='" + newCount + "' ";
		connectivelyclass c = new connectivelyclass();
		c.update(query1);
	}

	public ResultSet getMarks(int reg, int Ereg) {
		String query = "SELECT `Marks` FROM `test_exams` WHERE `Reg_no`='"
				+ reg + "' &&`EReg_no`='" + Ereg + "' ";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = null;
		rs = c.select(query);
		return rs;
	}

	public void insertGrage(int reg, int Ereg, String grade) {
		String query1 = "UPDATE `sms`.`test_exams` SET Grade = '" + grade
				+ "'   where Reg_no='" + reg + "' && EReg_no='" + Ereg + "'  ";
		connectivelyclass c = new connectivelyclass();
		c.update(query1);
	}

	public ResultSet getGradeDB(int reg, int Ereg) {
		String query = "SELECT `Grade` FROM `test_exams` WHERE `Reg_no`='"
				+ reg + "' &&`EReg_no`='" + Ereg + "' ";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = null;
		rs = c.select(query);
		return rs;
	}

	public ResultSet checkGradeStatusDB(int reg) {
		String query = "SELECT `Grade` FROM `test_exams` WHERE `Reg_no`='"
				+ reg + "'";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = null;
		rs = c.select(query);
		return rs;
	}

	public ResultSet getExamTotalDB(String type, int number, int reg) {
		String query = "SELECT `ExamTotal` FROM `test_exams` WHERE `ExamType`='"
				+ type
				+ "' && `ExamCount`='"
				+ number
				+ "' && `EReg_no`='"
				+ reg + "'";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = null;
		rs = c.select(query);
		return rs;
	}

}
