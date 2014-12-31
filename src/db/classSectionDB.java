package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class classSectionDB {

	public ResultSet getCSID(String clas, String sec) {
		String query = "SELECT `CSid` FROM `class_section` WHERE `clas`='"
				+ clas + "'and `section` = '" + sec + "'";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs;
		rs = c.select(query);
		return rs;
	}
	public ResultSet getClassSection(int CS) {
		String query = "SELECT `clas`,`section` FROM `class_section` WHERE `CSid`='"
				+ CS + "'";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs;
		rs = c.select(query);
		return rs;
	}

	public ResultSet getCSidDB(int clas, char sec) {
		String query = "SELECT `CSid` FROM `class_section` WHERE `clas`='"
				+ clas + "'&&`section`='" + sec + "'";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs;
		rs = c.select(query);
		return rs;
	}
	public ResultSet getSUBidDB(int csid,int Lid) {
		String query = "SELECT `Sub_id` FROM `tea_enroll` WHERE `CSid`='"
				+ csid + "' && `Lid`='"+Lid+"'";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs;
		rs = c.select(query);
		return rs;
	}
}
