package db;

import gui.DatePanel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentAttendenceDb {
	private boolean e;
	private int count;
	private int reg = -1;

	public boolean insertion(int regno, DatePanel datePanel, String status) {
		String date = datePanel.getCompleteDate();
		int month = datePanel.getMonth();
		int year = datePanel.getYear();
		String day = Integer.toString(datePanel.getDate());
		connectivelyclass c1 = new connectivelyclass();
		String r = "SELECT * FROM `studentattendence` WHERE `Reg_no`='" + regno
				+ "'and `Month`='" + month + "' and `Year`='" + year + "'";
		ResultSet rs = c1.select(r);
		try {
			if (rs.last() == false) {
				String str = "INSERT INTO `studentattendence`(`Reg_no`, `Month`, `Year`,`"
						+ day
						+ "`,`TP`,`TA`,`Percentage`) VALUES ('"
						+ regno
						+ "','"
						+ month
						+ "','"
						+ year + "','" + status + "','0','0','0.0')";
				e = c1.insert(str);
			} else {
				String r1 = "SELECT `" + day
						+ "` FROM `studentattendence` WHERE `Reg_no`='" + regno
						+ "'and `Month`='" + month + "' and `Year`='" + year
						+ "'";
				ResultSet rs1 = c1.select(r1);

				count = 0;
				try {
					if (rs.getString(day).isEmpty()) {
						String str = "UPDATE 	`studentattendence` SET `" + day
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

	public ResultSet DistincitMonth() {
		String query = "SELECT distinct `Month`FROM `studentattendence` order by `Month` desc";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(query);
		return rs;
	}

	public ResultSet DistincitYear() {
		String query = "SELECT distinct `Year` FROM `studentattendence` order by `Year` desc";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(query);
		return rs;
	}

	public ResultSet view(DatePanel datePanel) {
		String r = "SELECT `Reg_no`,`" + datePanel.getDate()
				+ "` FROM `studentattendence` WHERE `Month`='"
				+ datePanel.getMonth() + "' and `Year`='" + datePanel.getYear()
				+ "'";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(r);
		return rs;
	}

	public ResultSet view1(String clas, String sec) {
		String r = "SELECT `Reg_no`,`Roll_no`,`Name` FROM `stu_enroll` WHERE `Current_Class`='"
				+ clas + "' and `Section`='" + sec + "'";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(r);
		return rs;
	}

	public ResultSet ByMonthview(int month, int year) {
		String r = "SELECT * FROM `studentattendence` WHERE `Month`='" + month
				+ "' and `Year`='" + year + "'";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(r);
		return rs;
	}

	public ResultSet updateSearch(DatePanel date, String regno) {
		String r = "SELECT `Reg_no`,`" + date.getDate()
				+ "` FROM `studentattendence` WHERE `Month`='"
				+ date.getMonth() + "' and `Year`='" + date.getYear()
				+ "' and `Reg_no`='" + regno + "' ";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(r);
		return rs;
	}

	public boolean updation(DatePanel datePanel, String regno, String status) {
		int month = datePanel.getMonth();
		int year = datePanel.getYear();
		String day = Integer.toString(datePanel.getDate());
		connectivelyclass c1 = new connectivelyclass();
		String str = "UPDATE 	`studentattendence` SET `" + day + "`='" + status
				+ "' where `Reg_no`= '" + regno + "' and `Month` = '" + month
				+ "'and `Year`='" + year + "' ";
		e = c1.insert(str);
		return e;
	}

	public ResultSet getAttendenceOfSingleStudent(int month, int regId,int year) {
		String query = "Select * from studentattendence where Month='" + month
				+ "' and Reg_no='" + regId + "' and Year='"+year+"'";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(query);
		return result;
	}

	public boolean CountandPercentage(int present, int absent,
			Double percentage, int Month,int Year, int id) {
		connectivelyclass c1 = new connectivelyclass();
		String str = "UPDATE 	`studentattendence` SET `TP`='" + present
				+ "',TA='" + absent + "',Percentage='" + percentage
				+ "' where `Reg_no`= '" + id + "' and `Month` = '" + Month
				+ "' and `Year` = '" + Year
				+ "'";
		e = c1.insert(str);
		return e;
	}

}
