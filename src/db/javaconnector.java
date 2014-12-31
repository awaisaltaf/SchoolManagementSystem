package db;

import java.sql.*;

public class javaconnector {
	public static Connection ConDb(){
		 try {
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
			 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms","root","");
			 return con;
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}