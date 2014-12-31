package db;

import gui.Messages;

import java.sql.ResultSet;

public class LoginDb {

	public ResultSet confirmPassword(int id) {
		String r = "SELECT `Password` FROM `login` WHERE `Lid`='"+id+"'";
		connectivelyclass c1 = new connectivelyclass();
		ResultSet rs = c1.select(r);
		return rs;
	}

	public boolean changePassword(int lid,String retype) {
		String q = "UPDATE 	`login` SET `Password`='"
				+ retype + "' WHERE `Lid`='"+lid+"'  ";
		connectivelyclass c1 = new connectivelyclass();
		boolean rs = c1.insert(q);
		return rs;
	}
	
	public ResultSet getlogin(String userName, String password) {
		String query = "SELECT `Lid` FROM `login` WHERE `userName`='"
				+ userName + "' &&`password`='" + password + "'";
		connectivelyclass c = new connectivelyclass();
		ResultSet rs = c.select(query);
		return rs;
	}	
}
