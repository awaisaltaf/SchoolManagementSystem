package Testing;

import static org.junit.Assert.*;
import gui.DatePanel;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import db.StudentAttendenceDb;
import algo.Student;
import algo.StudentAttendence;

public class StudentAttendenceTest {

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
		StudentAttendence stuAtt = new StudentAttendence();
		double result = stuAtt.CalculatePercentage(22, 6, 2);
		System.out.println(result);
		assertTrue(result == 73.33333333333333);
		// assertEquals("Testing OK", 73.33333333333333, result);
	}
	@Test
	public void testUpdateAttendence() {
		StudentAttendence stuAtt = new StudentAttendence();
		DatePanel datePanel=new DatePanel();
		stuAtt.setDate(datePanel);
		boolean result = stuAtt.updateAttendence("1005", "P");
		assertTrue(result == true);
	}
	@Test
	public void testInsertAttendence() {
		StudentAttendence stuAtt = new StudentAttendence();
		Student stuObj=new Student();
		DatePanel datePanel=new DatePanel();
		stuObj.setRegNo(1018);
		stuAtt.setStatus("A");
		stuAtt.setDate(datePanel);
		boolean result=stuAtt.InsertAttendence(stuObj);	
		assertTrue(result == true);
	}
	@Test
	public void testAttendencecount() {
		StudentAttendence stuAtt = new StudentAttendence();
		List ls=new List();
		String names[] = {"P","A","P","L","P"};
		for (int i = 0; i < names.length; i++) {
			ls=stuAtt.attendencecount(names[i]);	
		}
		assertTrue(ls.getItem(0).matches("3"));	
	}


}
