package com.webbuild.javabrains.javaDemo.Junit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.webbuild.javabrains.Operations.SimpleSerch;
import com.webbuild.javabrains.Operations.SwitchBoard;
import com.webbuild.javabrains.model.Reports;

class JavaInterfaceTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private InputStream sysInBackup;
	private final static String[] choices= {"SortHi", "SortLo", "Average", "Median", "Mode", "Min", "Max"};
	private final static double[] array = {16.0, 25.0, 37.0, 29.0, 10.0, 15.0, 13.0, 32.0, 37.0, 5.0, 32.0, 26.0, 31.0, 2.0, 37.0, 0.0, 10.0, 10.0, 35.0, 37.0};
	
	static Reports[][] test;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception { 
		test = new Reports[3][];
		//New Array of objects
		test[0]= new Reports[2]; //put in empty rows to test Null error handling
		test[0][0]= new Reports();
		test[0][0].setreportId("Test-1.1");
		SwitchBoard.buildReports(test[0][0], choices, array);
		
		//New Array of objects
		test[1]= new Reports[2]; //put in empty rows to test Null error handling
		test[1][0]= new Reports();
		test[1][0].setreportId("Test-2.4");
		SwitchBoard.buildReports(test[1][0], choices, array);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		sysInBackup = System.in; // backup System.in to restore it later
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		System.setIn(sysInBackup); // optionally, reset System.in to its original
	}

	@Test
	void testJavaInterFaceSearch_noMatch() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream(
				"Test\n----------Name contains 3\n".getBytes());
		System.setIn(in);

		//When
		SimpleSerch.JavaInterFaceSearch(test);

		//Then
		assertThat(outContent.toString(), containsString("There are no records matching your query"));
	}
	
	@Test
	void testJavaInterFaceSearch_incorectSearch() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream(
				"Test\n----------Bob contains 3\n".getBytes());
		System.setIn(in);

		//When
		SimpleSerch.JavaInterFaceSearch(test);

		//Then
		assertThat(outContent.toString(), containsString("Bob contains 3 Is an Incompete statement the I cannot act upon"));
	}
	
	@Test
	void testJavaInterFaceSearch_complexOps() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream(
				"all\n----------Average >= 20\n----------yes\n----------dog\n".getBytes()); //test the all case command
		System.setIn(in);

		//When
		SimpleSerch.JavaInterFaceSearch(test);

		//Then
		assertThat(outContent.toString(), 
				either(containsString("Test-1.1: [37.0, 37.0, 37.0, 37.0, 35.0, 32.0, 32.0, 31.0, 29.0, 26.0, 25.0, 16.0, 15.0, 13.0, 10.0, 10.0, 10.0, 5.0, 2.0, 0.0]"))
				.or(containsString("the Sample Variance of Test-1.1 is: 168.15526315789472"))
				.or(containsString("the Standard Deviation of Test-1.1 is: 12.96746941997145"))
				.or(containsString("lower interquartile range: 10.0"))
				.or(containsString("upper interquartile range: 33.5"))
				.or(containsString("interquartile range: 23.5"))
				.or(containsString("range: 37.0"))
				.or(containsString("{37.0=4}"))
			);
	}
}
