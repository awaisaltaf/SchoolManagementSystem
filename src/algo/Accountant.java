package algo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * class Accountant Work as Controller class
 * @author Aqeel Ahmad
 **/
public class Accountant {
	/**
	 * 
	 * @param UserName
	 *            User Name of Teacher
	 * @param pswd
	 *            Password of Teacher
	 * @return Login ID Accountant
	 */
	
	public int signin(String UserName, String pswd) {
		int lid;
		ResultSet rs;
		Login lg = new Login();
		Employee emp = new Employee();
		lid = lg.getLid(UserName, pswd);
		rs = emp.employeeInfo(lid);
		try {
			while (rs.next()) {
				String designation;
				designation = rs.getString("Designation");
				if (!designation.matches("Accountant")) {
					lid = -1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return lid;
	}

	/**
	 * call StudentFinanc class's function to generate fee cards
	 * 
	 * @param dueDate
	 *            due date given by date picker
	 * @param month
	 *            current month
	 * @param year
	 *            current year
	 */
	public void generateFeeCard(String dueDate, int month, int year) {
		StudentFinance sf = new StudentFinance();
		sf.setMonth(month);
		sf.setDueDate(dueDate);
		sf.setYear(year);
		sf.generateFeecards();
	}

	/**
	 * call Student class's function to set values and search student
	 * 
	 * @param reg
	 *            Reg_no of student
	 * @return student class's object containing student's information
	 */
	public Student searchStudent(int reg) {
		Student sObj = new Student();
		sObj.setRegNo(reg);
		sObj.searchStudent();
		return sObj;
	}

	/**
	 * call StudentFinanc class's function to search fee cards
	 * 
	 * @param o
	 *            student class's object to get Reg_no of student
	 * @param month
	 *            current month
	 * @param year
	 *            current year
	 * @return return StudentFinance class's object having all information of
	 *         fee card
	 */
	public StudentFinance searchFeeCard(Student o, int month, int year) {
		StudentFinance fObj = new StudentFinance(o);
		fObj.setMonth(month);
		fObj.setYear(year);
		fObj.searchFeeCard();
		return fObj;
	}

	/**
	 * call StudentFinance's function which givs history(current year) of a
	 * student
	 * 
	 * @param f
	 *            StudentFinance's object
	 * @return current year's finance record
	 */
	public ResultSet getfeetHistory(StudentFinance f) {
		StudentFinance efObj = f;
		ResultSet rs = efObj.getHistory();
		return rs;
	}

	/**
	 * call StudentFinance's function to submit fee
	 * 
	 * @param f
	 *            StudentFinance's object having all finance info
	 */
	public void feeSubmission(StudentFinance f) {
		StudentFinance fObj = f;
		fObj.feeSubmission();
	}

	/**
	 * call EmployeeFinance's functions to set values and generate salary slip
	 * 
	 * @param month
	 *            current month
	 * @param year
	 *            current year
	 */
	public void generateSalarySlip(int month, int year) {
		EmployeeFinance fin = new EmployeeFinance();
		fin.setMonth(month);
		fin.setYear(year);
		fin.generateSalarySlip();
	}

	/**
	 * call Employee class's function to search the employee
	 * 
	 * @param reg
	 *            Reg_no of employee
	 * @return Employee class's object having personal info of employee
	 */
	public Employee searchEmployee(int reg) {
		Employee eObj = new Employee();
		eObj.setRegNo(reg);
		eObj.searchEmployee();
		return eObj;
	}

	/**
	 * call searchEmployee() function of Employee class
	 * 
	 * @param e
	 *            Employee class's object
	 * @param month
	 *            current month
	 * @param year
	 *            current year
	 * @return EmployeeFinance class's object having all financial info of an
	 *         employee
	 */
	public EmployeeFinance searchSalarySlip(Employee e, int month, int year) {
		EmployeeFinance efObj = new EmployeeFinance(e);
		efObj.setMonth(month);
		efObj.setYear(year);
		efObj.searchSalarySlip();
		return efObj;
	}

	/**
	 * current year's record
	 * 
	 * @param e
	 *            EmployeeFinance class's object to get Reg_no
	 * @return return resultset havin current years whole record
	 */
	public ResultSet getSalaryHistory(EmployeeFinance e) {
		EmployeeFinance efObj = e;
		ResultSet rs = efObj.getHistory();
		return rs;
	}

	/**
	 * call EmployeeFinance's function to Issue salary
	 * 
	 * @param ef
	 *            EmployeeFinance's object having all finance info
	 */
	public void SalaryIssue(EmployeeFinance ef) {
		EmployeeFinance efObj = ef;
		efObj.salaryIssue();
	}

}
