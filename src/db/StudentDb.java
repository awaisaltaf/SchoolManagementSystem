package db;

import java.sql.ResultSet;

public class StudentDb {
	public ResultSet SearchStudents(String clas, String sec) {
		String r = "SELECT `Reg_no`,`Roll_no`,`Name` FROM `stu_enroll` WHERE `Current_Class`='"
				+ clas + "' and `Section`='" + sec + "'";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(r);
		return rs;

	}

	public ResultSet getAllStudents() {
		String query = "Select * from stu_enroll";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(query);
		return result;
	}

	public ResultSet getsingleStudents(int regNo) {
		String query = "Select * from stu_enroll where Reg_no='" + regNo + "'";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(query);
		return result;
	}

	public ResultSet getStudentsIds(int clas, char Sec) {

		String query = "Select Reg_no,Name from `sms`.`stu_enroll` where Current_Class='"
				+ clas + "' and Section='" + Sec + "'";

		connectivelyclass c = new connectivelyclass();
		ResultSet rs = c.select(query);
		return rs;
	}

	public ResultSet countEnrolledInstances() {
		String query = "Select count(*) AS totalEnrolled from stu_enroll";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(query);
		return result;
	}
}
