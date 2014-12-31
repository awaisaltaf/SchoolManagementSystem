package Testing;

import static org.junit.Assert.*;
import gui.DatePanel;

import java.awt.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import algo.Employee;
import algo.EmployeeAttendence;
import algo.Student;
import algo.StudentAttendence;

public class EmployeeAttendenceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCalculatePercentage() {
		EmployeeAttendence empAtt = new EmployeeAttendence();
		double result = empAtt.CalculatePercentage(22, 6, 2);
		System.out.println(result);
		assertTrue(result == 73.33333333333333);
		// assertEquals("Testing OK", 73.33333333333333, result);
	}
	@Test
	public void testAttendencecount() {
		EmployeeAttendence empAtt = new EmployeeAttendence();
		List ls=new List();
		String names[] = {"P","A","P","L","P"};
		for (int i = 0; i < names.length; i++) {
			ls=empAtt.attendencecount(names[i]);	
		}
		assertTrue(ls.getItem(0).matches("3"));	
	}

	@Test
	public void testInsertAttendence() {
		EmployeeAttendence empAtt = new EmployeeAttendence();
		Employee empObj=new Employee();
		DatePanel datePanel=new DatePanel();
		empObj.setRegNo(1000);
		empAtt.setStatus("A");
		empAtt.setDate(datePanel);
		boolean result=empAtt.InsertAttendence(empObj);	
		assertTrue(result == true);
	}
	@Test
	public void testUpdateAttendence() {
		EmployeeAttendence empAtt = new EmployeeAttendence();
		DatePanel datePanel=new DatePanel();
		Employee empObj=new Employee();
		empObj.setRegNo(10002);
		empAtt.setDate(datePanel);
		empAtt.setStatus("P");
		boolean result = empAtt.UpdateAttendence(empObj);
		assertTrue(result == true);
	}
}
