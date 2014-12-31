package algo;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import db.StudentDb;
/**
 * class deal with student's persoal information
 * @author Awais Altaf, Aqeel Ahmad and Abu Bakar
 */

public class Student  {
	

	private int regNo;
	/** Student Reg No */
	private String rollNo;
	/** Student Roll No */
	private int currentClass;
	/** student's current class */
	private String kinshipStatus;
	/** Kinship status to give 50% off on fee amount */
	String sName;
	/** Student Name */
	String gName;
	/** Student Guardian Name */
	String section;
	/** student class's section */
	boolean bol;
	/***/

	private int[] registrationNo;

	/**ids of the  student  */

	/**
	 * 
	 * @return array ids of the student
	 */
	public int[] getRegistrationNo() {
		return registrationNo;
	}
	
	/**
	 * 
	 * @param registrationNo set all ids against some condtions 
	 */

	public void setRegistrationNo(int[] registrationNo) {
		this.registrationNo = registrationNo;
	}


	/**
	 * 
	 * @return student registration no
	 */
	public int getRegNo() {
		return this.regNo;
	}

	/**
	 * 
	 * @param regNo
	 *            to set student registration number
	 */
	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}

	public void studentEnrollment() {

	}

	/**
	 * 
	 * @return student Name
	 */
	public String getsName() {
		return sName;
	}

	/**
	 * 
	 * @param sName
	 *            to set value of student name
	 */
	public void setsName(String sName) {
		this.sName = sName;
	}

	/**
	 * 
	 * @return true or false, true for presenence of student in database and
	 *         false for not
	 */
	public boolean isBol() {
		return bol;
	}

	/**
	 * 
	 * @return guardian name
	 */
	public String getgName() {
		return gName;
	}

	/**
	 * 
	 * @param gName
	 *            set value of guardian name
	 */
	public void setgName(String gName) {
		this.gName = gName;
	}

	/**
	 * getting Students of a Class and Section
	 * 
	 * @param clas
	 *            Class of the Students
	 * @param sec
	 *            Section of the Students
	 * @return students of above Class and Students
	 */
	public ResultSet getStudent(String clas, String sec) {
		ResultSet rs;
		StudentDb stuDb = new StudentDb();
		rs = stuDb.SearchStudents(clas, sec);
		return rs;
	}

	/**
	 * 
	 * @return current class
	 */
	public int getCurrentClass() {
		return currentClass;
	}

	/**
	 * 
	 * @param cClass
	 *            to set value of current class
	 */
	public void setcurrentClass(int cClass) {
		this.currentClass = cClass;
	}

	/**
	 * 
	 * @return section of student
	 */
	public String getSection() {
		return section;
	}

	/**
	 * 
	 * @param section
	 *            to set the value of section data attribute
	 */
	public void setSection(String section) {
		this.section = section;
	}
/**
 * 
 * @param bol to set value of bol
 */
	public void setBol(boolean bol) {
		this.bol = bol;
	}
/**
 * to search student from database
 */
	public void searchStudent() {
		StudentDb obj = new StudentDb();
		ResultSet rs = obj.getsingleStudents(this.getRegNo());
		try {
			while (rs.next()) {
				this.bol = true;
				setsName(rs.getString("Name"));
				setgName(rs.getString("Guardian_Name"));
				setcurrentClass(rs.getInt("Current_Class"));
				setSection(rs.getString("Section"));
			}
			if (!rs.last()) {
				this.bol = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
/**
 * 
 * @return Resultset having all information of all students
 */
	public ResultSet getAllStudents() {
		StudentDb obj = new StudentDb();
		ResultSet rs = obj.getAllStudents();
		return rs;
	}
	/**
	 * get students of a particular class and section
	 * 
	 * @param Clas 
	 * @param Sec
	 * @return list of students
	 */
	
	public List getStudentsIds(int Clas, char Sec) {
		StudentDb objStudentDB = new StudentDb();
		ResultSet rs = objStudentDB.getStudentsIds(Clas, Sec);
		ResultSet rsGetId = objStudentDB.getStudentsIds(Clas, Sec);
		List listStuName = new List();
		int totalRegNo = 0;
		try {
			while (rs.next()) {
				totalRegNo++;
			}

		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		registrationNo = new int[totalRegNo];
		int[] temp = new int[totalRegNo];
		int index = 0;
		try {
			while (rsGetId.next()) {
				temp[index] = rsGetId.getInt("Reg_no");
				listStuName.add(rsGetId.getString("Name"));
				index++;
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		this.setRegistrationNo(temp);
		return listStuName;
	}
/**
 * 
 * @param regNo Registration number of student
 * @return the enrolled date of student
 */
	public Date getStudentRegistration(int regNo) {
		StudentDb stuDb = new StudentDb();
		ResultSet rs;
		Date date = null;
		rs = stuDb.getsingleStudents(regNo);
		try {
			rs.next();
			date = rs.getDate("Reg_Date");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}
}
