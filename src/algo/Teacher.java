package algo;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.TeacherDB;
/**
 * class Accountant Work as Controller class
 * @author Awais Altaf and M Abu Bakar Siddique
 *
 */
public class Teacher {
	/**
	 * 
	 * @param UserName
	 *            User Name of Teacher
	 * @param pswd
	 *            Password of Teacher
	 * @return Class,Section and Subject Assigned to Teacher
	 */
	public ResultSet signin(String UserName, String pswd) {
		Login lg = new Login();
		ResultSet rs;
		rs = lg.Login(UserName, pswd);
		return rs;
	}

	/**
	 * To check login Teacher is Incharge.
	 * 
	 * @param Lid
	 *            Login Id Of Teacher
	 * @param CSid
	 *            Class and Section IDs assigned to Teacher
	 * @return class and section of which Teacher is incharge Otherwise null
	 */
	public ResultSet checkIncharge(int Lid, int CSid) {
		ResultSet rs;
		TeacherDB tdb = new TeacherDB();
		rs = tdb.checkIncharge(Lid, CSid);
		return rs;
	}

	/**
	 * Getting the Students of a specific Class and Section
	 * 
	 * @param clas
	 *            Class Of students
	 * @param sec
	 *            Section Of Students
	 * @return Students which Full fill above parameter Conditions
	 */

	public ResultSet searchstudents(String clas, String sec) {
		ResultSet rs;
		Student stu = new Student();
		rs = stu.getStudent(clas, sec);
		return rs;
	}

	/**
	 * 
	 * @param stuObj
	 *            Object Of Student Class Which gives Registration ID
	 * @param attObj
	 *            Object Of StudentAttendence Class which gives Attendance
	 *            Status and Attendance Date
	 * @return success or failure of insertion of Attendance
	 */
	public boolean InsertAttendence(Student stuObj, StudentAttendence attObj) {
		boolean r;
		StudentAttendence stuAtt = new StudentAttendence();
		stuAtt.setDate(attObj.getDate());
		stuAtt.setStatus(attObj.getStatus());
		r = stuAtt.InsertAttendence(stuObj);
		return r;
	}


	/**
	 * 
	 * 
	 * 
	 * * @param reg Reg_no of the Student
	 * 
	 * @param type
	 *            can be quiz or term
	 * @param SubId
	 *            Subject ID related to the Student
	 * @param total
	 *            number of the quiz or the term
	 * @param examDate
	 *            date of the term or the quiz
	 * 
	 */
	public boolean teacherGenerateColumn(int reg, String type, int SubId,
			int total, String examDate) {
		testExam objTestExam = new testExam();
		boolean check = objTestExam.generateColumn(reg, type, SubId, total,
				examDate);
		return check;
	}

	/**
	 * 
	 * @param examCount
	 *            Exam number
	 * @param type
	 *            type of the exam can be quiz or term
	 * @param marks
	 *            marks of the student
	 * @param tempRegNo
	 *            ID of the Student
	 * @param examTotal
	 *            term or quiz total
	 * @param examDate
	 *            term or quiz date
	 */

	public void teacherInsertRecord(int examCount, String type, String marks,
			int tempRegNo, int examTotal, String examDate) {
		testExam objTestExam = new testExam();
		objTestExam.insert(examCount, type, marks, tempRegNo, examTotal,
				examDate);
	}

	/**
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

	public void teacherUpdate(int reg, int Enumber, String type, float marks) {
		testExam objTestExam = new testExam();
		objTestExam.update(reg, Enumber, type, marks);

	}

	/**
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
	public void teacherDelete(int reg, int number, String type, int Sid) {
		testExam objTestExam = new testExam();
		objTestExam.deleteColumn(reg, number, type, Sid);
	}
	
	

	/**
	 * Get Teacher ID ,class section ID,Subject ID 
	 * @param loginId ID against username and password
	 * @return 
	 */
	public ResultSet getClassSectionSubject(int loginId) {
		ResultSet rs;
		TeacherDB tdb = new TeacherDB();
		rs = tdb.getInchargeClassSection(loginId);
		return rs;
	}

	
	/**
	 * 
	 * @param CS class section ID
	 * @param sub subject ID
	 * @param Lid login ID
	 * @return Teacher ID against above parameters
	 */ 
	public int getTeaId(int CS, int sub, int Lid) {
		TeacherDB objTeacherDB = new TeacherDB();
		ResultSet rs = objTeacherDB.getTeaId(CS, sub, Lid);
		int id = 0;
		try {
			while (rs.next()) {
				id = rs.getInt("EReg_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * 
	 * @param reg
	 *            ID of the Student
	 * @param Ereg
	 *            ID of the Teacher
	 */
	public void teacherGenerateGrades(int reg, int Ereg) {
		testExam objTestExam = new testExam();
		objTestExam.generateGrades(reg, Ereg);
	}


}
