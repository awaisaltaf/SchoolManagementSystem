package db;

import java.sql.ResultSet;

public class EmployeeDb {
	public ResultSet getEmployee(String designation) {
		String r = "SELECT distinct `EReg_no`,`Name` FROM `tea_enroll` WHERE `Designation`='"
				+ designation + "'";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(r);
		return rs;
	}
	public ResultSet getAllEmployees() {
		String query = "Select * from tea_enroll";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(query);
		return result;
	}

	public ResultSet getSingleEmployee(int regNo) {
		String query = "Select * from tea_enroll where EReg_no='" + regNo + "'";     ////aqeel method change by awais changing t_enroll->tea_enroll
		connectivelyclass obj = new connectivelyclass();							//
		ResultSet result = obj.select(query);
		return result;
	}
	
	public ResultSet getEmployeeinfo(int lid) {
		String query = "Select * from tea_enroll where Lid='"+lid+"'";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(query);
		return result;
	}
public ResultSet countEnrolledInstances()
	{
		String query = "Select count(distinct EReg_no) AS totalEnrolled from tea_enroll";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(query);
		return result;
	}
}
