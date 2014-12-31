package algo;

import gui.DatePanel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

import db.EmployeeFinanceDb;
import db.StudentFinanceDb;
import db.StudentDb;

/**
 * class deal with student's finance
 * @author Aqeel Ahmad
 *
 */
public class StudentFinance {
	float fee;
	/** store fee amount */
	float feeRemainder;
	/** store fee remainder(preveious months fee) */
	int month;
	/** current month */
	int year;
	/** current year */
	String dueDate;
	/** due date to submit fee */
	float remainder;
	/** total remainder, sum of fee remainder and fine remainders */
	float fine;
	/** student sttendence fine */
	float fineRemainder;
	/** remainder of student attendence fine */
	float total;
	/** total payable amount */
	Student sObj;
	/** student class's object to get student Reg_id */
	String paymentStatus;
	/** fee payment status */
	float lFeeFine;
	/** late fee fine */
	float lFRemainder;
	/** late fee fine remainder(preveious month's fine) */
	boolean generate;
	/**
	 * simple a boolean check to keep indicate either all of students fee cards
	 * generated or someone is missin
	 */
	DatePanel dp = new DatePanel();

	/** to get date, month ,year */
	/**
	 * costructor of StudentFinance
	 */
	public StudentFinance() {
		this.fee = 0;
		this.month = 0;
		this.remainder = 0;
		this.dueDate = "";
		this.fine = 0;
		this.total = 0;
		this.paymentStatus = "N";
		this.lFeeFine = 0;
		this.feeRemainder = 0;
		this.lFRemainder = 0;
		this.lFeeFine = 0;
		this.generate = false;
	}

	/**
	 * costructor of StudentFinance
	 */
	public StudentFinance(Student s) {
		this.sObj = s;
		this.fee = fee;
		this.month = month;
		this.dueDate = dueDate;
		this.remainder = remainder;
		this.fine = fine;
		this.total = total;
		this.generate = false;
	}

	/**
	 * 
	 * @return fee amount
	 */
	public float getFee() {
		return fee;
	}

	/**
	 * 
	 * @param fee
	 *            to ste fee amount
	 */
	public void setFee(float fee) {
		this.fee = fee;
	}

	/**
	 * 
	 * @return month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * 
	 * @param month
	 *            to set Month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * 
	 * @return Year
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
	 * @return due date of student fee sumission
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * 
	 * @param dueDate
	 *            to set due Date
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * 
	 * @return student attendence fine
	 */
	public float getFine() {
		return fine;
	}

	/**
	 * 
	 * @param fine
	 *            to set student attendence fine
	 */
	public void setFine(float fine) {

		this.fine = fine;
	}

	/**
	 * 
	 * @return true or false, false if some on's fee card is not generated, else
	 *         true
	 */
	public boolean isGenerate() {
		int i = 0, j = 0;
		StudentDb s = new StudentDb();
		ResultSet rs1 = s.countEnrolledInstances();
		StudentFinanceDb sf = new StudentFinanceDb();
		ResultSet rs2 = sf.countfeeInstances(dp.getMonth(), dp.getYear());
		try {
			while (rs1.next()) {
				i = rs1.getInt("totalEnrolled");
			}
			while (rs2.next()) {
				j = rs2.getInt("totalCards");
			}

			System.out.println("I " + i + " J " + j);
			if (i > j) {
				generate = true;
			} else {
				generate = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return generate;
	}
/**
 * 
 * @param mon current month
 * @param regId student Reg No
 * @param year current year
 */
	public void setFine(int mon, int regId,int year) {
		float fine = 0;
		StudentAttendence sa = new StudentAttendence();
		int day = sa.absentCount(mon, regId,this.year);
		fine = day * 20;
		this.fine = fine + this.fineRemainder;
	}

	/**
	 * 
	 * @return late fee fine
	 */
	public float getlFeeFine() {
		return lFeeFine;
	}

	/**
	 * 
	 * @param lFeeFine
	 *            to set late fee fine after adding remainder of late fee fine
	 */
	public void setlFeeFine(float lFeeFine) {
		this.lFeeFine = lFeeFine + this.lFRemainder;
	}

	/**
	 * 
	 * @return fee remainder
	 */
	public float getFeeRemainder() {
		return feeRemainder;
	}

	/**
	 * 
	 * @param feeRemainder
	 *            to set fee remainder
	 */
	public void setFeeRemainder(float feeRemainder) {
		this.feeRemainder = feeRemainder;
	}

	/**
	 * 
	 * @return total remainder
	 */
	public float getRemainder() {
		return remainder;
	}

	/**
	 * 
	 * @param remainder
	 *            to set remainder
	 */
	public void setRemainder(float remainder) {
		this.remainder = remainder;
	}

	/**
	 * 
	 * @return student's attendence fine
	 */
	public float getFineRemainder() {
		return fineRemainder;
	}

	/**
	 * 
	 * @param fineRemainder
	 *            to set value of studnet attendence fine
	 */

	public void setFineRemainder(float fineRemainder) {
		this.fineRemainder = fineRemainder;
	}

	/**
	 * 
	 * @return late fee fine remainder
	 */
	public float getlFRemainder() {
		return lFRemainder;
	}

	/**
	 * 
	 * 
	 * @param lFRemainder
	 *            to set value of late fee fine
	 */
	public void setlFRemainder(float lFRemainder) {
		this.lFRemainder = lFRemainder;
	}

	/**
	 * 
	 * @return total payable amount
	 */
	public float getTotal() {

		return total;
	}

	/**
	 * function set the totsl payable amount after adding fines and fee
	 * 
	 * @param fee
	 *            fee amount
	 * @param fine
	 *            student attendence fine
	 * @param lateFeeFine
	 *            student lare fee fine
	 */
	public void setTotal(float fee, float fine, float lateFeeFine) {
		this.total = fee + fine + lateFeeFine;
		this.total = total;
	}

	/**
	 * 
	 * @return fee payment status either paid or unpaid
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * 
	 * @param paymentStatus
	 *            to set student fee payment status
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * function calculate fee amount for current month after fetching from data
	 * base
	 * 
	 * @param clas
	 *            student's class
	 * @param kinShipStatus
	 *            student kin ship status to give 50% off at fee amount
	 * @param reg
	 *            student Reg number
	 * @param d
	 *            student enrolment date
	 */
	public void feeCalculate(int clas, String kinShipStatus, int reg, Date d) {
		ResultSet result;
		StudentFinanceDb obj = new StudentFinanceDb();
		result = obj.getFeeAmount(clas);
		try {
			while (result.next()) {
				fee = result.getInt("Monthly_Fee");
				if (d.getMonth() + 1 == dp.getMonth()
						&& d.getYear() + 1900 == dp.getYear()) {
					fee = fee + result.getInt("Admission_Fee");
				}
				if (kinShipStatus.matches("y") || kinShipStatus.matches("y")) {
					this.fee = (float) (this.fee * 0.5);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.fee = this.fee + this.feeRemainder;
	}

	/**
	 * function generate fee card after calling other functions of this class
	 * and geting info from gui and database
	 */
	public void generateFeecards() {

		StudentFinanceDb objfd = new StudentFinanceDb();
		Student obj = new Student();
		ResultSet rs;
		rs = obj.getAllStudents();
		ResultSet rs1;

		try {
			while (rs.next()) {
				rs1 = objfd.getCompleteRecord(rs.getInt("Reg_no"), this.month,
						this.year);
				if (!rs1.last()) {
					this.setremainders(month, rs.getInt("Reg_no"));
					this.feeCalculate(rs.getInt("Current_Class"),
							rs.getString("Kinship_Status"),
							rs.getInt("Reg_no"), rs.getDate("Reg_Date"));
					this.setFine(month, rs.getInt("Reg_no"),this.year);
					this.setlFeeFine(this.lateFeeFine("Due_Date"));
					objfd.insertFeeCards(rs.getInt("Reg_no"), this);
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
	 * 
	 * @param mon
	 *            previous month
	 * @param reg
	 *            student reg no
	 */
	public void setremainders(int mon, int reg) {
		StudentFinanceDb obj = new StudentFinanceDb();
		ResultSet rs = obj.getRemainder(mon - 1, reg, this.year);
		try {
			while (rs.next()) {
				if (rs.getString("Payment_Status").matches("N")) {
					this.feeRemainder = 0;
					this.fineRemainder = 0;
					this.lFRemainder = 0;
					this.feeRemainder = rs.getInt("Amount");
					this.fineRemainder = rs.getInt("Att_Fine");
					this.lFRemainder = rs.getInt("LF_Fine");
				} else {
					this.feeRemainder = 0;
					this.fineRemainder = 0;
					this.lFRemainder = 0;
					this.remainder = 0;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * function search fee card of current month and year
	 */
	public void searchFeeCard() {
		ResultSet rs1, rs2;
		DatePanel tDate = new DatePanel();
		String todaydate = tDate.getTimeAndDate();
		StudentFinanceDb fObj = new StudentFinanceDb();
		Date month = new Date();
		rs1 = fObj.searchFeeCard(sObj.getRegNo(), month.getMonth() + 1);
		rs2 = fObj.getRemainder(month.getMonth(), sObj.getRegNo(), this.year);
		try {
			while (rs2.next()) {
				if (rs2.getString("Payment_Status").matches("N")) {
					this.remainder = rs2.getInt("Amount")
							+ rs2.getInt("Att_Fine") + rs2.getInt("LF_Fine");
				} else {
					this.remainder = 0;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while (rs1.next()) {
				this.setDueDate(rs1.getString("Due_Date"));
				this.setFee(rs1.getInt("Amount"));
				this.setMonth(rs1.getInt("Month"));
				this.setFine(rs1.getInt("Att_Fine"));
				this.setlFeeFine(rs1.getInt("LF_Fine"));
				SimpleDateFormat format = new SimpleDateFormat(
						"MM/dd/yyyy HH:mm:ss");
				Date d1 = null;
				Date d2 = null;
				try {
					d1 = format.parse(rs1.getString("Due_Date"));
					d2 = format.parse(todaydate);
					if (d2.after(d1)) {
						this.lFeeFine = this.lFeeFine
								+ this.lateFeeFine(this.dueDate);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// this.setlFeeFine(rs1.getInt("LF_Fine"));
				this.setTotal(this.fee, this.fine, this.lFeeFine);
				this.setPaymentStatus(rs1.getString("Payment_Status"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/**
 * 
 * @return bollean statuse value either true or false
 */
	public boolean feeSubmission() {
		StudentFinanceDb fdObj = new StudentFinanceDb();
		boolean c = fdObj.updateFeeStatus(sObj.getRegNo(), this);
		fdObj.schoolAcountInsertion(this.total);
		return c;
	}
/**
 * 
 * @param duedate of student fee submission 
 * @return total late fee fine after calculating 
 */
	public float lateFeeFine(String duedate) {
		float lFine = 0;
		int days;
		DatePanel tDate = new DatePanel();
		String todaydate = tDate.getTimeAndDate();
		String dDate = dueDate;

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dDate);
			d2 = format.parse(todaydate);

			if (d2.after(d1)) {
				DateTime dt1 = new DateTime(d1);
				DateTime dt2 = new DateTime(d2);
				days = Days.daysBetween(dt1, dt2).getDays();
				lFine = days * 20;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lFine;
	}

	public ResultSet getHistory() {
		StudentFinanceDb eDObj = new StudentFinanceDb();

		ResultSet rs3 = eDObj.getCompleteRecord(sObj.getRegNo(), this.year);
		return rs3;
	}

}
