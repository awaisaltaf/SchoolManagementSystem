package db;

import gui.DatePanel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import algo.StudentFinance;

public class StudentFinanceDb {
	public ResultSet getFeeAmount(int clas) {
		String Query = "SELECT * FROM `feeamounts` where clas='"
				+ clas + "'";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(Query);
		return result;

	}
	
	public ResultSet getCompleteRecord(int regId,int month,int year)
	{
		ResultSet rs;
		String query = "SELECT * FROM `feesubmission` where Reg_no='" + regId
				+ "' and Month='" + month + "' and Year='"+year+"'";
		connectivelyclass obj = new connectivelyclass();
		rs = obj.select(query);
		return rs;
	}
	
	public ResultSet getCompleteRecord(int regId,int year)
	{
		ResultSet rs;
		String query = "SELECT * FROM `feesubmission` where Reg_no='" + regId
				+ "' and Year='"+year+"'";
		connectivelyclass obj = new connectivelyclass();
		rs = obj.select(query);
		return rs;
	}

	public ResultSet getRemainder(int month, int regId,int year) {
		ResultSet result = null;
		String query = "SELECT * FROM `feesubmission` where Reg_no='" + regId
				+ "' and Month='" + month + "' and Year='"+year+"'";
		connectivelyclass obj = new connectivelyclass();
		result = obj.select(query);
		return result;
	}

	public boolean insertFeeCards(int regId, StudentFinance f) {
		String query = "INSERT INTO `sms`.`feesubmission` (`fee_Card_id`, `Reg_no` ,`Due_Date`,`Month`,`Year`,`Payment_Status`,`Amount`,`Amount_Paid`,`Att_Fine`,`LF_Fine`,`Sub_Date`) VALUES (default, '"
				+ regId
				+ "','"
				+ f.getDueDate()
				+ "','"
				+ f.getMonth()
				+ "','"+f.getYear()+"','UnPaid','"
				+ f.getFee()
				+ "','0','" + f.getFine() + "','" + f.getlFeeFine() + "',default)";
		connectivelyclass obj = new connectivelyclass();
		boolean bol = obj.insert(query);
		return bol;

	}

	public ResultSet searchFeeCard(int regNo, int month) {
		System.out.println("Month " + month);
		System.out.println("Reg " + regNo);
		String Query = "SELECT * FROM feesubmission where Reg_no='" + regNo
				+ "' and Month='" + month + "'";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(Query);
		return result;
	}

	public void schoolAcountInsertion(float total) {
		DatePanel d = new DatePanel();
		boolean bol=false;
		String Query = "SELECT * from school_acount";
		connectivelyclass obj = new connectivelyclass();
		ResultSet rs = obj.select(Query);
		try {
			while (rs.next()) {
				if (rs.getString("date").matches(d.getCompleteDate())) {
					float amount = rs.getInt("Amount_Received");
					amount = amount + total;
					String query = "UPDATE school_acount set Amount_Received='" + amount
							+ "' where date='"+d.getCompleteDate()+"'";
					obj.update(query);
					bol=true;
				}
			}
			if(bol==false)
			{
					String query="INSERT INTO `sms`.`school_acount` (`P_Key`, `date` ,`Amount_Received`,`Amount_Paid`) VALUES (default,'"+d.getCompleteDate()+"','"+total+"',0)";
					obj.insert(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean updateFeeStatus(int regNo, StudentFinance f) {
		DatePanel d = new DatePanel();
		boolean check = false;
		String Query = "SELECT Payment_Status from feesubmission where reg_no='"
				+ regNo + "' and Month='" + f.getMonth() + "'";
		connectivelyclass obj = new connectivelyclass();
		ResultSet rs = obj.select(Query);
		try {
			while (rs.next()) {
				if (rs.getString("Payment_Status").matches("UnPaid")) {
					String query = "UPDATE feesubmission set Payment_Status='Paid', Sub_Date='"
							+ d.getCompleteDate()
							+ "', Amount_Paid='"
							+ f.getTotal()
							+ "',LF_Fine='"
							+ f.getlFeeFine()
							+ "' where Reg_no='"
							+ regNo
							+ "' and Payment_Status='UnPaid' and Month='"
							+ f.getMonth() + "'";
					check = obj.update(query);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	public ResultSet countfeeInstances(int month,int year)
	{
		String query = "Select count(*) AS totalCards from feesubmission where Month='"+month+"' and Year='"+year+"'";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(query);
		return result;
	}

}
