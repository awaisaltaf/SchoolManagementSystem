package algo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import db.EmployeeAttendenceDB;
import db.EmployeeDb;
import db.StudentDb;
import db.classSectionDB;
import db.connectivelyclass;

/**
 * class having Employee's personal info
 * 
 * @author Aqeel Ahmad and Awais Altaf
 */

public class Employee{

	private String eName;
	/** Employee Name */
	private String designation;
	/** Employee designation */
	private String Experience;
	private int regNo;
	/** Employee's Reg_No */
	boolean bol;
	/** true if employee is in my database else false */

	/**
	 * constructor of Employee
	 */
	public Employee() {
		this.eName = "";
		this.designation = "";
		this.regNo = -1;
		this.bol = true;
	}

	/**
	 * 
	 * @return designation of Employee
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * 
	 * @param designation
	 *            to set the designation of Employee
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * 
	 * @return Experience of Employee
	 */
	public String getExperience() {
		return Experience;
	}

	/**
	 * 
	 * @param experience
	 *            to Set Experience of Employee
	 */
	public void setExperience(String experience) {
		this.Experience = experience;
	}

	/**
	 * 
	 * @return Reg_no of Employee
	 */
	public int getRegNo() {
		return this.regNo;
	}

	/**
	 * 
	 * @param regNo
	 *            to set Reg_no of Employee
	 */
	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}

	/**
	 * was the part of Jahanzaib's module to implement
	 */
	public void staffEnrollment() {

	}

	/**
	 * 
	 * @return Employee Name
	 */
	public String geteName() {
		return eName;
	}

	/**
	 * 
	 * @param eName
	 *            Set Employee Name
	 */
	public void seteName(String eName) {
		this.eName = eName;
	}

	/**
	 * 
	 * @param empObj
	 *            Employee's Object can be done by passing this
	 * @return ResultSet having Employees
	 */
	public ResultSet getEmployee(Employee empObj) {
		ResultSet rs;
		EmployeeDb empdb = new EmployeeDb();
		rs = empdb.getEmployee(empObj.getDesignation());
		return rs;
	}

	/**
	 * 
	 * @return return true or false to know about presensce of Employee in my
	 *         database
	 */
	public boolean isBol() {
		return bol;
	}

	/**
	 * function which search the given employee from database
	 */
	public void searchEmployee() {
		EmployeeDb obj = new EmployeeDb();
		ResultSet rs = obj.getSingleEmployee(this.regNo);
		try {
			while (rs.next()) {
				this.seteName(rs.getString("Name"));
				this.setDesignation(rs.getString("Designation"));
				this.setRegNo(rs.getInt("EReg_no"));
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
	 * @param lid
	 *            Login ID Of Employee
	 * @return Complete Information of a single Employee
	 */
	public ResultSet employeeInfo(int lid) {
		ResultSet rs;
		EmployeeDb empdb = new EmployeeDb();
		rs = empdb.getEmployeeinfo(lid);
		return rs;
	}

	/**
	 * 
	 * @return result set having all employee's info to generate Salary Slip
	 */
	public ResultSet getAllEmployee() {
		EmployeeDb eDb = new EmployeeDb();
		ResultSet rs = eDb.getAllEmployees();
		return rs;
	}

	/**
	 * 
	 * @param regNo
	 *            Registration ID of Employee
	 * @return Employee Registration Date
	 */
	public Date getEmployee(int regNo) {
		EmployeeDb empDb = new EmployeeDb();
		ResultSet rs;
		Date date = null;
		rs = empDb.getSingleEmployee(regNo);
		try {
			rs.next();
			date = rs.getDate("Reg_Date");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return date;
	}

}
