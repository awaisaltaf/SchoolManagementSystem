package algo;

import java.awt.List;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.apache.velocity.runtime.directive.Parse;

import gui.DatePanel;
import gui.Messages;
import db.StudentAttendenceDb;
import db.connectivelyclass;

/**
 * Deal with the Student Attendance Function(CRED)
 * 
 * @author Awais Altaf and Aqeel Ahmad
 *
 */
public class StudentAttendence {
	private String status;
	/** Store the Attendence Status A,P Or L */
	private DatePanel date;
	/** Store the Date When Status recorded */
	private double attendencepercentage=0;
	/** Monthly Attendence Percentage */
	private int Presentcount;
	/** Number of Presents of a Student */
	private int Absentcount;
	/** Number of Absent of a Student */
	private int leavecount;
	/** Number of Leaves of a Student */
	private List ls;
	/** Store all three Types of Status Counts */
	private boolean e;

	/** For Conformation of Updation */

	public StudentAttendence() {
		Presentcount = 0;
		Absentcount = 0;
		leavecount = 0;
	}

	/**
	 * Insert the Attendance of Student
	 * 
	 * @param stuObj
	 *            Student Class Object to get the Registration ID
	 * @return Success or Failure
	 */
	public boolean InsertAttendence(Student stuObj) {
		boolean r;
		StudentAttendenceDb stuAttDb = new StudentAttendenceDb();
		r = stuAttDb.insertion(stuObj.getRegNo(), this.date, this.status);
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
	 * @return ResultSet of Distinct Months
	 */
	public ResultSet DistincitMonth() {
		StudentAttendenceDb stuAttDb = new StudentAttendenceDb();
		return stuAttDb.DistincitMonth();
	}

	/**
	 * 
	 * @return ResultSet of Distinct Months
	 */
	public ResultSet DistincitYear() {
		StudentAttendenceDb stuAttDb = new StudentAttendenceDb();
		return stuAttDb.DistincitYear();
	}

	/**
	 * 
	 * @param Class
	 *            Attendance of This Class of Students
	 * @param sec
	 *            Attendance of This Class of Students
	 * @param month
	 *            Attendance of Students on This month
	 * @param year
	 *            Attendance of Students on This year
	 * @return List of Attendance Which fulfill Above Condition
	 */

	public List ViewAttendence(String Class, String sec, int month, int year) {

		if (month == 0 && year == 0) {
			ls = new List();
			StudentAttendenceDb stuAttDb = new StudentAttendenceDb();
			ResultSet rs = stuAttDb.view(this.date);
			ResultSet rs1 = stuAttDb.view1(Class, sec);
			String Date = Integer.toString(this.date.getDate());
			try {
				int i = 0;
				while (rs1.next()) {
					if (i != 0) {
						rs.first();
					}
					while (rs.next()) {
						if (rs1.getInt("Reg_no") == rs.getInt("Reg_no")) {
							if (!rs.getString(Date).isEmpty()) {
								ls.add(Integer.toString(rs.getInt("Reg_no")));
								ls.add(Integer.toString(rs1.getInt("Roll_no")));
								ls.add(rs1.getString("Name"));
								ls.add(rs.getString(Date));
								i++;
							}
						}
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			StudentAttendenceDb stuAttDB = new StudentAttendenceDb();
			ResultSet rs = stuAttDB.ByMonthview(month, year);
			ResultSet rs1 = stuAttDB.view1(Class, sec);
			try {
				int i = 0;
				ls = new List();
				while (rs.next()) {
					int presentcount = 0;
					int absentcount = 0;
					int leavecount = 0;
					if (i != 0) {
						rs1.beforeFirst();
					}
					while (rs1.next()) {
						if (rs.getInt("Reg_no") == rs1.getInt("Reg_no")) {

							if (!rs1.wasNull()) {
								ls.add(Integer.toString(rs.getInt("Reg_no")));
								ls.add(Integer.toString(rs1.getInt("Roll_no")));
								ls.add(rs1.getString("Name"));
								for (int j = 1; j <= 31; j++) {
									if (rs.getString(Integer.toString(j))
											.isEmpty()) {
										ls.add(" ");
									} else {

										if (rs.getString(Integer.toString(j))
												.matches("P")) {
											presentcount++;
										} else if (rs.getString(
												Integer.toString(j)).matches(
												"A")) {
											absentcount++;
										} else if (rs.getString(
												Integer.toString(j)).matches(
												"L")) {
											leavecount++;
										}
										ls.add(rs.getString(Integer.toString(j)));
									}
								}
								double percentage = CalculatePercentage(
										presentcount, absentcount, leavecount);
								ls.add(Integer.toString(presentcount));
								ls.add(Integer.toString(absentcount));
								ls.add(Double.toString(Math.round(percentage)));
							}
						}
						i++;
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return ls;
	}

	/**
	 * 
	 * @param presentcount
	 *            Number of 'P'
	 * @param absentcount
	 *            Number of 'A'
	 * @param leavecount
	 *            Number of 'L'
	 * @return Percentage of Attendance
	 */
	public double CalculatePercentage(int presentcount, int absentcount,
			int leavecount) {
		double totalattendence = presentcount + absentcount + leavecount;
		if (totalattendence > 0) {
			attendencepercentage = (presentcount / totalattendence) * 100;
		}
		return attendencepercentage;
	}

	public List updateSearch(String regno, String Class, String sec) {
		ResultSet rs, rs1;
		StudentAttendenceDb stuAttDb = new StudentAttendenceDb();
		Student stu = new Student();
		rs = stu.getStudent(Class, sec);
		rs1 = stuAttDb.updateSearch(this.date, regno);
		String d = Integer.toString(this.date.getDate());
		try {
			int i = 0;
			ls = new List();
			while (rs1.next()) {
				if (i != 0) {
					rs.beforeFirst();
				}
				while (rs.next()) {
					if (rs.getInt("Reg_no") == rs1.getInt("Reg_no")) {
						if (!rs1.getString(d).isEmpty()) {
							ls.add(Integer.toString(rs.getInt("Reg_no")));
							ls.add(Integer.toString(rs.getInt("Roll_no")));
							ls.add(rs.getString("Name"));
							ls.add(rs1.getString(d));
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
	 * 
	 * @param regno
	 *            Registration Id Of Student
	 * @param status
	 *            Attendance Status of Student
	 * @return Success Or Failure of Updation
	 */
	public boolean updateAttendence(String regno, String status) {
		StudentAttendenceDb stuAttDb = new StudentAttendenceDb();
		try {
			ResultSet rs;
			rs = stuAttDb.updateSearch(this.date, regno);
			String d = Integer.toString(this.date.getDate());
			while (rs.next()) {
				if (!rs.getString(d).isEmpty()) {
					e = stuAttDb.updation(this.date, regno, status);
				} else {
					e = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return e;
	}

	/**
	 * 
	 * @param month
	 *            current Month
	 * @param regNo
	 *            Student Registration Id
	 * @param year
	 *            Current Year
	 * @return Number of absents in a Month
	 */
	public int absentCount(int month, int regNo, int year) {
		int count = 0;
		int col = 1;
		StudentAttendenceDb sDb = new StudentAttendenceDb();
		ResultSet rs = sDb.getAttendenceOfSingleStudent(month, regNo, year);

		try {
			while (rs.next()) {
				while (col != 31) {
					if (rs.getString(Integer.toString(col)).matches("A")) {
						count++;
					}
					col++;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * Insert the Attendance Counts and Percentage
	 * 
	 * @param stuObj
	 *            Object of Student Class To get Registration ID
	 */
	public void AttendenceCountPercentage(Student stuObj) {
		ResultSet rs;
		StudentAttendenceDb studentAttendenceDb = new StudentAttendenceDb();
		rs = studentAttendenceDb.getAttendenceOfSingleStudent(date.getMonth(),
				stuObj.getRegNo(), date.getYear());
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
				studentAttendenceDb.CountandPercentage(
						Integer.parseInt(ls.getItem(0)),
						Integer.parseInt(ls.getItem(1)), percentage,
						date.getMonth(), date.getYear(), stuObj.getRegNo());
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	/**
	 * Attendance View on a Specific Date
	 * 
	 * @param datePanel
	 *            To View the Attendance On this Date
	 * @return Students Attendance on Given Date
	 */
	public ResultSet view(DatePanel datePanel) {
		StudentAttendenceDb stuAttDb = new StudentAttendenceDb();
		return stuAttDb.view(datePanel);
	}

	/**
	 * 
	 * @param Class
	 *            Student Of this Class
	 * @param section
	 *            Student Of this Section
	 * @return Students Of Given Class and Section
	 */
	public ResultSet studentSearch(String Class, String section) {
		StudentAttendenceDb stuAttDb = new StudentAttendenceDb();
		return stuAttDb.view1(Class, section);
	}

	/**
	 * 
	 * @return Attendance Status of Student
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Set Attendance Status
	 * 
	 * @param status
	 *            Attendance Status of Student
	 * 
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return Date
	 */
	public DatePanel getDate() {
		return this.date;
	}

	/**
	 * Set Date of Attendance
	 * 
	 * @param date
	 *            Value of Date
	 * 
	 */
	public void setDate(DatePanel date) {
		this.date = date;
	}

	/**
	 * 
	 * @return Percentage of Attendance
	 */
	public double getAttendencepercentage() {
		return this.attendencepercentage;
	}

	/**
	 * set the Percentage value
	 * 
	 * @param attendencepercentage
	 *            Value of Percentage
	 */
	public void setAttendencepercentage(float attendencepercentage) {
		this.attendencepercentage = attendencepercentage;
	}

}
