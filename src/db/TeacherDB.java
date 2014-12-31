package db;

import gui.Messages;

import java.sql.ResultSet;

public class TeacherDB {
	public ResultSet getInchargeClassSection(int id) {
		String query = "SELECT `EReg_no`,`CSid`,`Sub_id`,`Lid` FROM `tea_enroll` WHERE `Lid`='"
				+ id + "'";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = c.select(query);
		return rs;
	}

	public ResultSet checkIncharge(int Lid, int csid) {
		String query = "SELECT `CSid` FROM `tea_enroll` WHERE`CSid`='" + csid
				+ "' and  `Lid`='" + Lid + "' and `incharge`='Y'";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = c.select(query);
		return rs;
	}

	public ResultSet getIdsClassSectionSubject(int id) {
		String query = "SELECT `EReg_no`,`CSid`,`Sub_id` FROM `tea_enroll` WHERE `Lid`='"
				+ id + "'";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = c.select(query);
		return rs;
	}

	public ResultSet getTeaId(int CS, int sub, int Lid) {
		String query = "SELECT `EReg_no` FROM `tea_enroll` WHERE `CSid`='" + CS
				+ "'&&`Sub_id`='" + sub + "' && `Lid`= '" + Lid + "' ";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = c.select(query);
		return rs;

	}

}
