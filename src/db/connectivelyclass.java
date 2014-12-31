package db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connectivelyclass {
	private static String url = "jdbc:mysql://localhost:3306/";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String username = "root";
	private static String password = "";
	private static Connection con;
	private static String dbName = "sms";

	public static Connection getConnection() {
		url += dbName;
		try {
			Class.forName(driverName);
			try {
				con = DriverManager.getConnection(url, username, password);
			
			} catch (SQLException ex) {
				// log an exception.
				//System.out.println("Failed to create the database connection.");
		
			}
		} catch (ClassNotFoundException ex) {
			// log an exception. for example:
			System.out.println("Driver not found.");
		
		}
		return con;
		
	}

	public ResultSet select(String query) {
		ResultSet rs = null;
		//System.out.println(query);
		try {
			con = getConnection();
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return rs;
	}

	public boolean insert(String query) {
		// TODO Auto-generated method stubtry {
		boolean check = false;
		//System.out.println(query);
		try {

			con = getConnection();
			Statement stmt = con.createStatement();
			stmt.execute(query);
			check = true;
		} catch (Exception e) {
			e.printStackTrace();
			check = false;
		}
		return check;
	}

	public boolean update(String query) {
		boolean check = false;
		try {
			con = getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			check = true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return check;
	}

	public boolean delete(String query) {
		boolean check = false;
		try {
			con = getConnection();
			Statement stmt = con.createStatement();
			stmt.execute(query);
			check = true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return check;
	}

	public static void main(String args[]) {

	}
}