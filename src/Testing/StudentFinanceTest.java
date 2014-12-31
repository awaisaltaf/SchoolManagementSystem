package Testing;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import algo.StudentFinance;

public class StudentFinanceTest {

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
	public void testSetTotal() {
		StudentFinance stuFinance = new StudentFinance();
		stuFinance.setTotal(100, 10, 10);
		float result = stuFinance.getTotal();
		assertTrue(result == 120);
	}

	@Test
	public void testFeecalculate() {
		StudentFinance stuFinance = new StudentFinance();
		stuFinance.setremainders(12, 1003);
		Date d=new Date();
		stuFinance.feeCalculate(4, "N", 1003,d);
		float result = stuFinance.getFee();
		System.out.println(result);
		assertTrue(result == 2600);
	}
	@Test
	public void testFine() {
		StudentFinance stuFinance = new StudentFinance();
		stuFinance.setFine(12, 1003,2012);
		float result=stuFinance.getFine();
		assertTrue(result == 40);
	}
	/*
	@Test
	public void testsetLFeeFine() {
		StudentFinance stuFinance = new StudentFinance();
		stuFinance.setlFeeFine(stuFinance.lateFeeFine("11/29/2014 00:24:54"));
		float result=stuFinance.getlFeeFine();
		assertTrue(result == 180);
	}*/
}
