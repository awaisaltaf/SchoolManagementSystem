package algo;

import java.sql.ResultSet;

import db.EmployeeAttendenceDB;
import db.EmployeeDb;
import db.TeacherDB;
/**
 * class Admin Work as Controller class
 * 
 * @author  Awais Altaf
 */

public class Admin {
	/**
	 * 
	 * @param id
	 *            User Name of Teacher
	 * @param pswd
	 *            Password of Teacher
	 * @return Login ID  Of Admin
	 */
	public int signin(String id, String pswd) {
		int lid;
		ResultSet rs;
		Login lg = new Login();
		Employee emp =new Employee(); 
		lid = lg.getLid(id, pswd);
		rs=emp.employeeInfo(lid);
		 try {
			while (rs.next()) {
				String designation;
				designation=rs.getString("Designation");
				if(!designation.matches("Admin")){
				lid=-1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return lid;
	}
	/**
	 * 
	 * @param empObj Employee Object which Contain the Employee Designation
	 * @return Employees of a specific Designation
	 */
	public ResultSet getEmployee(Employee empObj) {
		ResultSet rs;
		Employee emp = new Employee();
		rs = emp.getEmployee(empObj);
		return rs;
	}

	/**
	 * 
	 * @param empObj Employee Object which Contain the Employee Registration No and 
	 * @param empAttObj EmployeeAttendance Object which Contain the Attendance Status and Date 
	 * @return success or failure of insertion of Attendance
	 */
	public boolean InsertAttendence(Employee empObj,
			EmployeeAttendence empAttObj) {
		boolean r;
		EmployeeAttendence emp = new EmployeeAttendence();
		emp.setDate(empAttObj.getDate());
		emp.setStatus(empAttObj.getStatus());
		r = emp.InsertAttendence(empObj);
		return r;
	}

}
