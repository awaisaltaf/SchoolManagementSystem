package algo;

import gui.DatePanel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.EmployeeDb;
import db.EmployeeFinanceDb;
import db.StudentDb;
import db.StudentFinanceDb;
import db.connectivelyclass;
/**
 * class deal with Employee's finance
 * @author Aqeel Ahmad
 */
public class EmployeeFinance {
	
	float salary;
	/** Basic Salary of Employee */
	float salaryRemainder;
	/** to store salary of last month if employee didnt receive it */
	int month;
	/** current month */
	int year;
	/** current year */
	String issuedate;
	/** to store salary issue date fetched by database */
	float remainder;
	/**
	 * total
	 * remainder=salaryRemainder+medAllowanceRemainder+transAllowanceRemainder
	 */
	float total;
	/** total payable amount */
	float medAllowance;
	/** medical allowance of employee according to his designation */
	float transAllowance;
	/** transport allowance of employee according to his designation */
	float medAllowanceRemainder;
	/** remainder of medical allowance if it is not paid to employee */
	float transAllowanceRemainder;
	/** remainder of Transport allowance if it is not paid to employee */
	private boolean generate;
	/** to check either fee card has been generated or not */
	Employee eobj;
	/** to get employees info like Reg No */
	String paymentStatus;
	/** simple payment statuse of salary */
	private DatePanel dp = new DatePanel();

	/**
	 * constructor of EmployeeFinance
	 */
	public EmployeeFinance() {
		this.salary = 0;
		this.salaryRemainder = 0;
		this.month = 0;
		this.year = 0;
		this.issuedate = "";
		this.remainder = 0;
		this.total = 0;
		this.medAllowance = 0;
		this.transAllowance = 0;
		this.medAllowanceRemainder = 0;
		this.transAllowanceRemainder = 0;
		this.paymentStatus = "N";
		this.generate = false;
	}

	/**
	 * constructor of EmployeeFinance
	 */
	public EmployeeFinance(Employee e) {
		this.salary = 0;
		this.salaryRemainder = 0;
		this.month = 0;
		this.year = 0;
		this.issuedate = "";
		this.remainder = 0;
		this.total = 0;
		this.medAllowance = 0;
		this.transAllowance = 0;
		this.medAllowanceRemainder = 0;
		this.transAllowanceRemainder = 0;
		this.paymentStatus = "N";
		this.eobj = e;
		this.generate = false;
	}

	/**
	 * 
	 * @return basic salary
	 */
	public float getSalary() {
		return salary;
	}

	/**
	 * 
	 * @param salary
	 *            to set basic Salary
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}

	/**
	 * set salary after fetching info from database
	 * 
	 * @param des
	 *            designation of employee
	 * @param reg
	 *            regNo of Employee
	 */
	private void setSalary(String des, int reg) {
		EmployeeFinanceDb edb = new EmployeeFinanceDb();
		ResultSet rs1 = edb.getESalaryAmounts(des);
		try {
			while (rs1.next()) {
				this.salary = rs1.getInt("Salary");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return remainder of Basic salary
	 */
	public float getSalaryRemainder() {
		return salaryRemainder;
	}

	/**
	 * function set remainder of salary after fetching info from database
	 * 
	 * @param reg
	 *            Employees reg no
	 */
	public void setSalaryRemainder(int reg) {
		EmployeeFinanceDb edb = new EmployeeFinanceDb();
		ResultSet rs = edb.getRemainder(month - 1, reg, year);
		try {
			while (rs.next()) {
				if (rs.getString("Payment_Status").matches("N")) {
					this.salaryRemainder = 0;
					this.salaryRemainder = rs.getInt("salary");
				} else {
					this.salaryRemainder = 0;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * to set enable and disable the "Generate Fee Card" button on gui
	 * 
	 * @return true/false, true if not generated false if generated
	 */
	public boolean isGenerate() {
		int i = 0, j = 0;
		EmployeeDb ed = new EmployeeDb();
		ResultSet rs1 = ed.countEnrolledInstances();
		EmployeeFinanceDb ef = new EmployeeFinanceDb();
		ResultSet rs2 = ef.countSalaryInstances(dp.getMonth(), dp.getYear());
		try {
			while (rs1.next()) {
				i = rs1.getInt("totalEnrolled");
			}
			while (rs2.next()) {
				j = rs2.getInt("totalCards");
			}
			if (i > j) {
				generate = true;
			} else {
				generate = false;
			}
			System.out.println("I "+i);
			System.out.println("I "+j);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return generate;
	}

	/**
	 * 
	 * @return medical allowanvce of employee
	 */
	public float getMedAllowance() {
		return medAllowance;
	}

	/**
	 * 
	 * @param medAllowance
	 *            to set medical allowanvce of employee
	 */
	public void setMedAllowance(float medAllowance) {
		this.medAllowance = transAllowance;
	}

	/**
	 * set employees Medical allowance after fetching data from database
	 * 
	 * @param des
	 *            designation of employee
	 */
	public void setMedAllowance(String des) {
		EmployeeFinanceDb edb = new EmployeeFinanceDb();
		ResultSet rs1 = edb.getESalaryAmounts(des);
		try {
			while (rs1.next()) {
				this.medAllowance = this.salary * rs1.getInt("Med_Allowance")
						/ 100;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.medAllowance = this.medAllowance + this.medAllowanceRemainder;
	}

	/**
	 * 
	 * @return Medical allowance of Employee
	 */
	public float getMedAllowanceRemainder() {
		return medAllowanceRemainder;
	}

	/**
	 * to set the remainder Medical allowance of employee
	 * 
	 * @param reg
	 *            Reg_no of Employee
	 */
	public void setMedAllowanceRemainder(int reg) {
		EmployeeFinanceDb edb = new EmployeeFinanceDb();
		ResultSet rs = edb.getRemainder(month - 1, reg, year);
		try {
			while (rs.next()) {
				if (rs.getString("Payment_Status").matches("N")) {
					this.medAllowanceRemainder = 0;
					this.medAllowanceRemainder = rs.getInt("Med_Allowance");
				} else {
					this.medAllowanceRemainder = 0;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return transport allowance of Employee
	 */
	public float getTransAllowance() {
		return transAllowance;
	}

	/**
	 * 
	 * @param transAllowance
	 *            to set transport allowance of Employee
	 */
	public void setTransAllowance(float transAllowance) {
		this.transAllowance = transAllowance;
	}

	/**
	 * to set transport allowance of Employee after fetching data from database
	 * 
	 * @param des
	 *            designation of Employee
	 */
	public void setTransAllowance(String des) {
		EmployeeFinanceDb edb = new EmployeeFinanceDb();
		ResultSet rs1 = edb.getESalaryAmounts(des);
		try {
			while (rs1.next()) {
				this.transAllowance = this.salary
						* rs1.getInt("Trans_Allowance") / 100;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.transAllowance = this.transAllowance
				+ this.transAllowanceRemainder;
	}

	/**
	 * 
	 * @return Remainder of transport allowance of Employee
	 */
	public float getTransAllowanceRemainder() {
		return transAllowanceRemainder;
	}

	/**
	 * to set Remainder of transport allowance of Employee
	 * 
	 * @param reg
	 *            Reg No of Employee
	 */
	public void setTransAllowanceRemainder(int reg) {
		EmployeeFinanceDb edb = new EmployeeFinanceDb();
		ResultSet rs = edb.getRemainder(month - 1, reg, year);
		try {
			while (rs.next()) {
				if (rs.getString("Payment_Status").matches("N")) {
					this.transAllowanceRemainder = 0;
					this.transAllowanceRemainder = rs.getInt("Med_Allowance");
				} else {
					this.transAllowanceRemainder = 0;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return Month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * 
	 * @param month
	 *            to set month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * 
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * 
	 * @param year
	 *            to set year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * 
	 * @return salary issue date
	 */
	public String getIssuedate() {
		return issuedate;
	}

	/**
	 * 
	 * @param issuedate
	 *            to set salary issue date
	 */
	public void setIssuedate(String issuedate) {
		this.issuedate = issuedate;
	}

	/**
	 * 
	 * 
	 * @return return remainder
	 */
	public float getRemainder() {
		return remainder;
	}

	/**
	 * set total remainder
	 * remainder=salaryRemainder+medAllowanceRemainder+transAllowanceRemainder
	 */
	public void setRemainder() {
		this.remainder = this.salaryRemainder + this.medAllowanceRemainder
				+ this.transAllowanceRemainder;
	}

	/**
	 * 
	 * @return total payable amount
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * set total payable amount after sum of remainder, basic salary and
	 * allowances
	 */
	public void setTotal() {
		this.total = this.salary + this.medAllowance + this.transAllowance;
	}

	/**
	 * 
	 * @return payment Status of salary
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * 
	 * @param paymentStatus
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * generate salary slip(of current month) of all employees who are enrolled
	 */
	public void generateSalarySlip() {
		EmployeeDb eDb = new EmployeeDb();
		EmployeeFinanceDb efdb = new EmployeeFinanceDb();
		ResultSet rs = eDb.getAllEmployees();
		ResultSet rs1;

		try {
			while (rs.next()) {
				rs1 = efdb.getCompleteRecord(rs.getInt("EReg_no"), this.month,
						this.year);
				if (!rs1.last()) {
					setSalaryRemainder(rs.getInt("EReg_no"));
					setSalary(rs.getString("Designation"), rs.getInt("EReg_no"));
					setMedAllowanceRemainder(rs.getInt("EReg_no"));
					setMedAllowance(rs.getString("Designation"));
					setTransAllowanceRemainder(rs.getInt("EReg_no"));
					setTransAllowance(rs.getString("Designation"));
					this.salary = this.salary + this.salaryRemainder;
					efdb.insertSalarySlip(rs.getInt("EReg_no"), this);
				} else {
					System.out.println("Already Generated");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * search salary slip(of current minth) of an employee
	 */
	public void searchSalarySlip() {
		ResultSet rs1;
		EmployeeFinanceDb eDObj = new EmployeeFinanceDb();
		rs1 = eDObj.searchSalarySlip(eobj.getRegNo(), this.month, this.year);
		System.out.println("Reg NO" + eobj.getRegNo());
		ResultSet rs2 = eDObj.getRemainder(this.month - 1, eobj.getRegNo(),
				this.year);
		try {
			while (rs2.next()) {
				this.medAllowanceRemainder = rs2.getInt("Med_Allowance");
				this.transAllowanceRemainder = rs2.getInt("Trans_Allowance");
				this.salaryRemainder = rs2.getInt("Salary");
				this.setRemainder();
				System.out.println("Remainder " + this.remainder);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while (rs1.next()) {
				this.setSalary(rs1.getInt("Salary"));
				this.setTransAllowance(rs1.getInt("Trans_Allowance"));
				this.setMedAllowance(rs1.getInt("Med_Allowance"));
				// this.setMonth(rs1.getInt("Month"));
				this.setTotal();
				this.setPaymentStatus(rs1.getString("Payment_Status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return employees financial history of current year
	 */
	public ResultSet getHistory() {
		EmployeeFinanceDb eDObj = new EmployeeFinanceDb();
		ResultSet rs3 = eDObj.getCompleteRecord(eobj.getRegNo(), this.year);
		return rs3;
	}

	/**
	 * 
	 * @return boolean variable to check salay issued or not
	 */
	public boolean salaryIssue() {
		EmployeeFinanceDb fdObj = new EmployeeFinanceDb();
		boolean c = fdObj.updateSalaryStatus(eobj.getRegNo(), this);
		fdObj.schoolAcountInsertion(this.total);
		return c;
	}

}
