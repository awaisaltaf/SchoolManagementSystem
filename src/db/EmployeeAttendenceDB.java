package db;

import gui.DatePanel;
import gui.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeAttendenceDB {

	private boolean e;
	private int count;
	private int reg;

	public ResultSet Designations() {
		String query = "SELECT distinct `Designation` FROM `tea_enroll` order by `Designation` desc";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(query);
		return rs;
	}

	public ResultSet searchbyDate(DatePanel date) {
		String r = "SELECT `Reg_no`,`" + date.getDate()
				+ "` FROM `teacherattendence` WHERE `Month`='"
				+ date.getMonth() + "' and `Year`='" + date.getYear() + "' ";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(r);
		return rs;
	}

	public boolean insertion(int regno, DatePanel datePanel, String status) {
		String date = datePanel.getCompleteDate();
		int month = datePanel.getMonth();
		int year = datePanel.getYear();
		String day = Integer.toString(datePanel.getDate());
		connectivelyclass c1 = new connectivelyclass();
		String r = "SELECT * FROM `teacherattendence` WHERE `Reg_no`='" + regno
				+ "'and `Month`='" + month + "' and `Year`='" + year + "'";
		ResultSet rs = c1.select(r);
		try {
			if (rs.last() == false) {
				String str = "INSERT INTO `teacherattendence`(`Reg_no`, `Month`, `Year`,`"
						+ day
						+ "`,`TP`,`TA`,`Percentage`) VALUES ('"
						+ regno
						+ "','" + month + "','" + year + "','" + status + "','0','0','0.0')";
				e = c1.insert(str);
			} else {
				String r1 = "SELECT `" + day
						+ "` FROM `teacherattendence` WHERE `Reg_no`='" + regno
						+ "'and `Month`='" + month + "' and `Year`='" + year
						+ "'";
				ResultSet rs1 = c1.select(r1);
				count = 0;
				try {
					if (rs.getString(day).isEmpty()) {
						String str = "UPDATE 	`teacherattendence` SET `" + day
								+ "`='" + status + "' where `Reg_no`= '"
								+ regno + "' and `Month` = '" + month
								+ "'and `Year`='" + year + "' ";
						e = c1.insert(str);
					} else {
						e = false;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return e;
	}

	public boolean updation(DatePanel datePanel, int regno, String status) {
		int month = datePanel.getMonth();
		int year = datePanel.getYear();
		String day = Integer.toString(datePanel.getDate());
		connectivelyclass c1 = new connectivelyclass();
		String str = "UPDATE 	`teacherattendence` SET `" + day + "`='" + status
				+ "' where `Reg_no`= '" + regno + "' and `Month` = '" + month
				+ "'and `Year`='" + year + "' ";
		e = c1.insert(str);
		return e;
	}

	public ResultSet DistincitMonth() {
		String query = "SELECT distinct `Month`FROM `teacherattendence` order by `Month` desc";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(query);
		return rs;
	}

	public ResultSet DistincitYear() {
		String query = "SELECT distinct `Year` FROM `teacherattendence` order by `Year` desc";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(query);
		return rs;
	}

	public ResultSet ByMonthview(int month, int year) {
		String r = "SELECT * FROM `teacherattendence` WHERE `Month`='" + month
				+ "' and `Year`='" + year + "'";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(r);
		return rs;
	}

	public ResultSet MonthlyAttendenceOfSingleEmployee(int month, int year,
			int regno) {
		connectivelyclass c1 = new connectivelyclass();
		String r = "SELECT * FROM `teacherattendence` WHERE `Reg_no`='" + regno
				+ "'and `Month`='" + month + "' and `Year`='" + year + "'";
		ResultSet rs1 = c1.select(r);
		return rs1;
	}

	public boolean CountandPercentage(int present, int absent,
			Double percentage, int Month, int Year, int id) {
		connectivelyclass c1 = new connectivelyclass();
		String str = "UPDATE `teacherattendence` SET `TP`='" + present
				+ "',TA='" + absent + "',Percentage='" + percentage
				+ "' where `Reg_no`= '" + id + "' and `Month` = '" + Month
				+ "' and `Year` = '" + Year + "'";
		e = c1.insert(str);
		return e;
	}

}
