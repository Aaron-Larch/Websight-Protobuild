package com.webbuild.javabrains.javaDemo.Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.webbuild.javabrains.Store;

class UserInputTest {
	Store tstscanner = new Store();
	private static int i;
	private static char test;
	private static double check;
	private static String Input;
	private static float ii;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private InputStream sysInBackup;

	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		sysInBackup = System.in; // backup System.in to restore it later
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		System.setOut(new PrintStream(outContent));
		System.setIn(sysInBackup); // optionally, reset System.in to its original
	}

	@Test
	void testUserinputChar() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("A".getBytes());
		System.setIn(in);

		//When
		test=tstscanner.symbol();

		//Then
		assertEquals('A', test);
	}
	
	//Test String input type with leading return error handling
	@Test
	void testUserinputString_noreturn() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("Hello World!\n".getBytes());
		System.setIn(in);

		//When
		Input=tstscanner.words();

		//Then
		assertEquals("Hello World!", Input);
	}
	
	@Test
	void testUserinputString_altstyle() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("Hello World!\n".getBytes());
		System.setIn(in);

		//When
		try {
			Input=tstscanner.altstyle();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Given
		assertEquals("Hello World!", Input);
	}
	
	@Test
	void testUserinputString_retuntype() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("Hello World!\r".getBytes());
		System.setIn(in);

		//When
		Input=tstscanner.words();

		//Then
		assertEquals("Hello World!", Input);
	}
	
	@Test
	void testUserinputString_catchreturn() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("\nHello World!\n".getBytes());
		System.setIn(in);

		//When
		Input=tstscanner.words();

		//Then
		assertEquals("Hello World!", Input);
	}
	
	//Test integer input type with leading return space and decimal error handling
	@Test
	void testUserinputint_noreturn() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("45\n".getBytes());
		System.setIn(in);

		//When
		i=tstscanner.number();

		//Then
		assertEquals(45, i);
	}
		
	@Test
	void testUserinputint_catchreturn() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("\n456\n".getBytes());
		System.setIn(in);

		//When
		i=tstscanner.number();

		//Then
		assertEquals(456, i);
	}
	
	@Test
	void testUserinputint_intspace() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("45 6\n".getBytes());
		System.setIn(in);

		//When
		i=tstscanner.number();

		//Then
		assertEquals(45, i);
	}
		
	@Test
	void testUserinputint_intdecimal() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("456.6768\n".getBytes());
		System.setIn(in);

		//When
		i=tstscanner.number();

		//Then
		assertEquals(456, i);
	}

	//Test double input type with leading return and space error handling
	@Test
	void testUserinputdouble_noreturn() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("45.4\n".getBytes());
		System.setIn(in);

		//When
		check=tstscanner.complex();

		//Then
		assertEquals(45.4, check);
	}
		
	@Test
	void testUserinputdouble_catchreturn() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("\n456.45\n".getBytes());
		System.setIn(in);

		//When
		check=tstscanner.complex();

		//Then
		assertEquals(456.45, check);
	}
	
	@Test
	void testUserinputdouble_space() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("45 6.5\n".getBytes());
		System.setIn(in);

		//When
		check=tstscanner.complex();

		//Then
		assertEquals(45.0, check);
	}

	//Test float input type with leading return and space error handling
	@Test
	void testUserinputfloat_noreturn() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("45.4\n".getBytes());
		System.setIn(in);

		//When
		ii=tstscanner.value();

		//Then
		assertEquals(Float.parseFloat("45.4"), ii);
	}
			
	@Test
	void testUserinputfloat_catchreturn() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("\n456.45\n".getBytes());
		System.setIn(in);

		//When
		ii=tstscanner.value();

		//Then
		assertEquals(Float.parseFloat("456.45"), ii);
	}
		
	@Test
	void testUserinputfloat_space() {
		//Given
		ByteArrayInputStream in = new ByteArrayInputStream("45 6.5\n".getBytes());
		System.setIn(in);

		//When
		ii=tstscanner.value();

		//Then
		assertEquals(45.0, ii);
	}
	
	//Test a Random number generator
	@Test
	void testrandomarry_int() {
		//Given
		int Size= 6;
		int range = 10;

		//When
		int[] test= tstscanner.rArrayInt(Size, range);
		i=test[0];
		for(int j=0; j<test.length; j++) {
			if(test[j]>i) {i=test[j];}
		}

		//Then
		assertEquals(Size, test.length);
		assertTrue(range>=ii);
	}
	
	@Test
	void testrandomarry_double() {
		//Given
		int Size= 6;
		int range = 10;

		//When
		double[] test= tstscanner.rArrayDouble(Size, range);
		check=test[0];
		for(int j=0; j<test.length; j++) {
			if(test[j]>check) {check=test[j];}
		}

		//Then
		assertEquals(Size, test.length);
		assertTrue(range>=check);
	}
	
	@Test
	void testrandomarry_float() {
		//Given
		int Size= 6;
		int range = 10;

		//When
		float[] test= tstscanner.rArrayFloat(Size, range);
		ii=test[0];
		for(int j=0; j<test.length; j++) {
			if(test[j]>ii) {ii=test[j];}
		}

		//Then
		assertEquals(Size, test.length);
		assertTrue(range>=ii);
	}
}
