package db;

import java.sql.ResultSet;

public class subjectDB {

	public ResultSet getSubject(int Sid) {
		String query = "SELECT `SubName` FROM `subject` WHERE `Sub_id`='" + Sid
				+ "'";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs;
		rs = c.select(query);
		return rs;
	}

	public ResultSet getID(String name) {
		String query = "SELECT `Sub_id` FROM `subject` WHERE `SubName`='"
				+ name + "'";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs;
		rs = c.select(query);
		return rs;
	}
}
