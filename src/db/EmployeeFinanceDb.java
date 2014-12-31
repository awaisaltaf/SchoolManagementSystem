package db;

import gui.DatePanel;

import java.sql.ResultSet;
import java.sql.SQLException;

import algo.EmployeeFinance;
import algo.StudentFinance;

public class EmployeeFinanceDb {
	public EmployeeFinanceDb() {

	}

	public ResultSet getESalaryAmounts(String Designation) {
		String Query = "SELECT salary,Med_Allowance,Trans_Allowance FROM `salaryamounts` where Designation='"
				+ Designation + "'";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(Query);
		return result;
	}

	public ResultSet getRemainder(int month, int regId, int year) {
		ResultSet result = null;
		String query = "SELECT * FROM `salaryissue` where Reg_no='" + regId
				+ "' and Month='" + month + "' and Year='" + year + "'";
		connectivelyclass obj = new connectivelyclass();
		result = obj.select(query);
		return result;
	}

	public ResultSet getCompleteRecord(int regId, int year) {
		ResultSet result = null;
		String query = "SELECT * FROM `salaryissue` where Reg_no='" + regId
				+ "' and Year='" + year + "'";
		connectivelyclass obj = new connectivelyclass();
		result = obj.select(query);
		return result;
	}

	public ResultSet getCompleteRecord(int regId, int month, int year) {
		ResultSet result = null;
		String query = "SELECT * FROM `salaryissue` where Reg_no='" + regId
				+ "' and Month='" + month + "' and Year='" + year + "'";
		connectivelyclass obj = new connectivelyclass();
		result = obj.select(query);
		return result;
	}

	public void insertSalarySlip(int regno, EmployeeFinance f) {
		String query = "INSERT INTO `sms`.`salaryissue` (`Salary_Slip_ID`, `Month` ,`Year`,`Salary`,`Paid_Date`,`Payment_Status`,`Med_Allowance`,`Trans_Allowance`,`Amount_Paind`,`Reg_no`) VALUES (default,'"
				+ f.getMonth()
				+ "','"
				+ f.getYear()
				+ "','"
				+ f.getSalary()
				+ "',default,'N','"
				+ f.getMedAllowance()
				+ "','"
				+ f.getTransAllowance() + "',default,'" + regno + "')";
		connectivelyclass obj = new connectivelyclass();
		boolean bol = obj.insert(query);
	}

	public ResultSet searchSalarySlip(int regNo, int month, int Year) {
		String Query = "SELECT * FROM `salaryissue` where Reg_no='" + regNo
				+ "' and Month='" + month + "' and Year='" + Year + "'";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(Query);
		return result;
	}

	public boolean updateSalaryStatus(int regNo, EmployeeFinance f) {
		DatePanel d = new DatePanel();
		boolean check = false;
		String Query = "SELECT Payment_Status from salaryissue where reg_no='"
				+ regNo + "' and Month='" + f.getMonth() + "' and Year='"
				+ f.getYear() + "'";
		connectivelyclass obj = new connectivelyclass();
		ResultSet rs = obj.select(Query);
		try {
			while (rs.next()) {
				if (rs.getString("Payment_Status").matches("N")) {
					String query = "UPDATE salaryissue set Payment_Status='Y', Paid_Date='"
							+ d.getCompleteDate()
							+ "', Amount_Paind='"
							+ f.getTotal()
							+ "' where Reg_no='"
							+ regNo
							+ "' and Payment_Status='N' and Month='"
							+ f.getMonth() + "' and Year='" + f.getYear() + "'";
					check = obj.update(query);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	public ResultSet countSalaryInstances(int month,int year)
	{
		String query = "Select count(*) AS totalCards from salaryissue where Month='"+month+"' and Year='"+year+"'";
		connectivelyclass obj = new connectivelyclass();
		ResultSet result = obj.select(query);
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
					float amount = rs.getInt("Amount_Paid");
					amount = amount + total;
					String query = "UPDATE school_acount set Amount_Paid='" + amount
							+ "' where date='"+d.getCompleteDate()+"'";
					obj.update(query);
					bol=true;
				}
			}
			if(bol==false)
			{
					String query="INSERT INTO `sms`.`school_acount` (`P_Key`, `date` ,`Amount_Paid`,`Amount_Received`) VALUES (default,'"+d.getCompleteDate()+"','"+total+"',0 )";
					obj.insert(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
