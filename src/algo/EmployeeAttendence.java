package algo;

import gui.DatePanel;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.EmployeeAttendenceDB;
import db.EmployeeDb;
import db.StudentAttendenceDb;
import db.connectivelyclass;

/**
 * Deal with the Employee Attendance
 * @author Awais Altaf
 *
 */
public class EmployeeAttendence {

	private String Status;
	/** Store the Attendance Status A,P Or L */
	private DatePanel date;
	/** Store the Date When Status recorded */
	private double attendencePercentage;
	/** Monthly Attendance Percentage */
	private int Presentcount;
	/** Number of Presents of a Student */
	private int Absentcount;
	/** Number of Absent of a Student */
	private int leavecount;
	/** Number of Leaves of a Student */
	private List ls;
	/** Store all three Types of Status Counts */
	private List l;
	/** Store By Month and Year Attendance */

	/**
	 * Constructor of This Class
	 */
	public EmployeeAttendence() {
		Presentcount = 0;
		Absentcount = 0;
		leavecount = 0;
	}

	/**
	 * Attendance View By Month
	 * 
	 * @param empObj
	 *            Object Of Employee Class from Which we get Registration ID
	 * @param month
	 *            Attendance of Employee on this month
	 * @param year
	 *            Attendance of Employee on this year
	 * @return List of Attendance which fulfill above Parameter Conditions
	 */
	public List ViewAttendence(Employee empObj, int month, int year) {
		EmployeeAttendenceDB empAttDb = new EmployeeAttendenceDB();
		EmployeeDb empDb = new EmployeeDb();
		ResultSet rs = empDb.getEmployee(empObj.getDesignation());
		ResultSet rs1 = empAttDb.ByMonthview(month, year);

		try {
			int i = 0;
			l = new List();
			while (rs.next()) {
				int presentcount = 0;
				int absentcount = 0;
				int leavecount = 0;
				if (i != 0) {
					rs1.beforeFirst();
				}
				while (rs1.next()) {
					if (rs.getInt("EReg_no") == rs1.getInt("Reg_no")) {
						if (!rs1.wasNull()) {
							l.add(Integer.toString(rs.getInt("EReg_no")));
							l.add(rs.getString("Name"));
							for (int j = 1; j <= 31; j++) {
								if (rs1.getString(Integer.toString(j))
										.isEmpty()) {
									l.add(" ");
								} else {

									if (rs1.getString(Integer.toString(j))
											.matches("P")) {
										presentcount++;
									} else if (rs1.getString(
											Integer.toString(j)).matches("A")) {
										absentcount++;
									} else if (rs1.getString(
											Integer.toString(j)).matches("L")) {
										leavecount++;
									}
									l.add(rs1.getString(Integer.toString(j)));
								}

							}
							double percentage = CalculatePercentage(
									presentcount, absentcount, leavecount);
							l.add(Integer.toString(presentcount));
							l.add(Integer.toString(absentcount));
							l.add(Double.toString(Math.round(percentage)));
						}
					}
					i++;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return l;
	}

	/**
	 * 
	 * @param empObj
	 *            Object Of Employee Class from Which we get Registration ID
	 * @return Success Or Failure of Insertion of Attendance
	 */
	public boolean InsertAttendence(Employee empObj) {
		boolean r;
		EmployeeAttendenceDB empdb = new EmployeeAttendenceDB();
		r = empdb.insertion(empObj.getRegNo(), this.date, this.Status);
		return r;
	}

	/**
	 * Count the three Kind of Statuses
	 * 
	 * @param status
	 *            Student Attendance Status
	 * @return List of Count of All kind of Status
	 */

	public List attendencecount(String status) {
		List ls = new List();
		if (status.matches("P")) {
			Presentcount++;
		} else if (status.matches("A")) {
			Absentcount++;
		} else if (status.matches("L")) {
			leavecount++;
		}
		ls.add(Integer.toString(Presentcount));
		ls.add(Integer.toString(Absentcount));
		ls.add(Integer.toString(leavecount));
		return ls;
	}

	/**
	 * 
	 * @param datePanel
	 *            Date of Attendance
	 * @param empObj
	 *            Object Of Employee Class From Which get Registration ID and
	 *            Designation
	 * @return List of Employees Which Fulfill above parameter Condiions
	 */
	public List searchTeachers(DatePanel datePanel, Employee empObj) {

		EmployeeAttendenceDB empAttDb = new EmployeeAttendenceDB();
		EmployeeDb empDb = new EmployeeDb();
		ResultSet rs = empDb.getEmployee(empObj.getDesignation());
		ResultSet rs1 = empAttDb.searchbyDate(datePanel);
		String date = Integer.toString(datePanel.getDate());

		try {
			int i = 0;
			ls = new List();
			while (rs.next()) {
				if (i != 0) {
					rs1.beforeFirst();
				}
				while (rs1.next()) {
					if (rs.getInt("EReg_no") == rs1.getInt("Reg_no")) {
						if (!rs1.getString(date).isEmpty()) {
							ls.add(Integer.toString(rs.getInt("EReg_no")));
							ls.add(rs.getString("Name"));
							ls.add(rs1.getString(date));
							i++;
						}
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}

	/**
	 * Insert the Attendance Counts and Percentage
	 * 
	 * @param empObj
	 *            Object of Student Class To get Registration ID
	 */
	public void AttendenceCountPercentage(Employee empObj) {
		ResultSet rs;
		EmployeeAttendenceDB employeeAttendenceDB = new EmployeeAttendenceDB();
		rs = employeeAttendenceDB.MonthlyAttendenceOfSingleEmployee(
				date.getMonth(), date.getYear(), empObj.getRegNo());
		List ls = new List();
		try {
			while (rs.next()) {
				for (int j = 1; j < 32; j++) {
					String status = rs.getString(Integer.toString(j));
					ls = attendencecount(status);
				}

				double percentage = CalculatePercentage(
						Integer.parseInt(ls.getItem(0)),
						Integer.parseInt(ls.getItem(1)),
						Integer.parseInt(ls.getItem(2)));
				employeeAttendenceDB.CountandPercentage(
						Integer.parseInt(ls.getItem(0)),
						Integer.parseInt(ls.getItem(1)), percentage,
						date.getMonth(), date.getYear(), empObj.getRegNo());
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	/**
	 * Update the Employee Attendance
	 * 
	 * @param empObj
	 *            Object of Employee Class From which we get Registration
	 * @return Success Or Failure of Updation
	 */
	public boolean UpdateAttendence(Employee empObj) {
		EmployeeAttendenceDB empDb = new EmployeeAttendenceDB();
		boolean e = empDb.updation(this.date, empObj.getRegNo(),
				this.getStatus());
		return e;
	}

	/**
	 * 
	 * @return Unique Months Of Attendance
	 */
	public ResultSet DistincitMonth() {
		EmployeeAttendenceDB empDb = new EmployeeAttendenceDB();
		return empDb.DistincitMonth();
	}

	/**
	 * 
	 * @return Unique Years Of Attendance
	 */
	public ResultSet DistincitYear() {
		EmployeeAttendenceDB empDb = new EmployeeAttendenceDB();
		return empDb.DistincitYear();
	}

	/**
	 * 
	 * @param presentcount
	 *            Number of 'P' in a Month
	 * @param absentcount
	 *            Number of 'A' in a Month
	 * @param leavecount
	 *            Number of 'L' in a Month
	 * @return Percentage of Attendance of a Month
	 */
	public double CalculatePercentage(int presentcount, int absentcount,
			int leavecount) {

		double totalattendence = presentcount + absentcount + leavecount;
		attendencePercentage = (presentcount / totalattendence) * 100;
		return attendencePercentage;
	}

	/**
	 * 
	 * @return Unique Designations of Employees
	 */
	public ResultSet Designations() {
		ResultSet rs;
		EmployeeAttendenceDB empDb = new EmployeeAttendenceDB();
		rs = empDb.Designations();
		return rs;
	}

	/**
	 * 
	 * @return Attendance Status Of Student
	 */
	public String getStatus() {
		return this.Status;
	}

	/**
	 * Set Attendance Status
	 * 
	 * @param status
	 *            Attendance Status Value
	 */
	public void setStatus(String status) {
		this.Status = status;
	}

	/**
	 * Return Date
	 * 
	 * @return Date of Attendance
	 */
	public DatePanel getDate() {
		return this.date;
	}

	/**
	 * Set Date
	 * 
	 * @param date
	 *            Value Of Date
	 */
	public void setDate(DatePanel date) {
		this.date = date;
	}

	/**
	 * Return Percentage
	 * 
	 * @return Attendance Percentage
	 */
	public double getAttendencePercentage() {
		return this.attendencePercentage;
	}

	/**
	 * Set Attendance Percentage
	 * 
	 * @param attendencePercentage
	 *            Value of Percentage
	 */
	public void setAttendencePercentage(int attendencePercentage) {
		this.attendencePercentage = attendencePercentage;
	}
}
