package algo;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;

import db.LoginDb;
/**
 *Deal with Admin,Teacher and Accountant Logins Functionality    
 * @author Awais Altaf ,Aqeel Ahmad and M Abu Bakar Siddique
 *
 */
public class Login {

	private String userName;/** User Name for Login*/
	
	private String password;/** Password for Login*/
/**
 * 
 * @param userName Login User Name of Employee
 * @param pasword Login Password of Employee
 * @return Class,Section and Subject against above login  Employee
 */
	public ResultSet Login(String userName, String pasword) {
		ResultSet rs, rs1 = null;
		LoginDb lgdb = new LoginDb();
		rs = lgdb.getlogin(userName, pasword);
		classSection CS = new classSection();
		Teacher t = new Teacher();
		int Lid = 0;
		int CSid = 0;
		try {
			while (rs.next()) {
				Lid = rs.getInt("Lid");
			}
			rs1 = t.getClassSectionSubject(Lid);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return rs1;
	}

	/**
	 * Change the Password of Employee Login
	 * 
	 * @param id
	 *            Registration ID of Employee
	 * @param old
	 *            Previous Password
	 * @param New
	 *            New Password
	 * @param retype
	 *            Again New Password
	 * @return success or failure of Password Changing
	 */
	public boolean ChangePassword(int id, String old, String New, String retype) {
		ResultSet rs;
		boolean a = false;
		LoginDb lgdb = new LoginDb();
		rs = lgdb.confirmPassword(id);
		try {
			while (rs.next()) {
				if (old.matches(rs.getString("Password"))) {
					System.out.println(id);
					lgdb.changePassword(id, retype);
					a = true;
				} else {
					a = false;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return a;
	}

	/**
	 * 
	 * @param us
	 *            User Name of Employee
	 * @param ps
	 *            PassWord of Employee
	 * @return Login ID Of the Employee
	 */
	public int getLid(String us, String ps) {
		int id = -1;
		LoginDb objLoginDB = new LoginDb();
		ResultSet rs = objLoginDB.getlogin(us, ps);
		try {
			while (rs.next()) {
				id = rs.getInt("Lid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}
/**
 * 
 * @return User Name
 */
	public String getUserName() {
		return userName;
	}
/**
 * 
 * @param userName To Set value of User Name
 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
/**
 * 
 * @return Password of Employee
 */
	public String getPassword() {
		return password;
	}
/**
 * 
 * @param password To set the Value of Password
 */
	public void setPassword(String password) {
		this.password = password;
	}

}
