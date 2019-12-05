package com.webbuild.javabrains.javaDemo.Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.webbuild.javabrains.Operations.SimpleSerch;
import com.webbuild.javabrains.model.Reports;

class SearchTest {

	static Reports[][] test;
	private static Reports[] result;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception { 
		test = new Reports[2][1];
		//New Array of objects
		test[0]= new Reports[2]; //put in empty rows to test Null error handling
		test[0][0]= new Reports();
		test[0][0].setreportId("test1");
		
		//New Array of objects
		test[1]= new Reports[2]; //put in empty rows to test Null error handling
		test[1][0]= new Reports();
		test[1][0].setreportId("test2");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		result = new Reports[2]; //test to prove rows are dropped
	}
	
						//----Testing SimpleSerch.Operaton private method----\\
	//Test less than operations
	@Test
	void Searchtest_lessthanSymbol() {
		//Given
		test[0][0].setmedian(10); //Returned value
		test[1][0].setmedian(20); //Dropped value
		assertTrue(result.length==2);
		//When
		result=SimpleSerch.search( test, "median < 15", "test");
		//Then
		assertTrue(result[0].getmedian()==10);
		assertTrue(result.length==1); //proof of record drop
	}
	
	@Test
	void Searchtest_lessthanWord() {
		//Given
		test[0][0].setmedian(10); //Returned value
		test[1][0].setmedian(20); //Dropped value
		assertTrue(result.length==2);
		//When
		result=SimpleSerch.search( test, "median less than 15", "test");
		//Then
		assertTrue(result[0].getmedian()==10);
		assertTrue(result.length==1); //proof of record drop
	}

	//Test grater than operations
	@Test
	void Searchtest_graterthanSymbol() {
		//Given
		test[0][0].setmedian(20); //Returned value
		test[1][0].setmedian(10); //Dropped value
		assertTrue(result.length==2);
		//When
		result=SimpleSerch.search( test, "median > 15", "test");
		//Then
		assertTrue(result[0].getmedian()==20);
		assertTrue(result.length==1); //proof of record drop
	}
	
	@Test
	void Searchtest_graterthanWord() {
		//Given
		test[0][0].setmedian(20); //Returned value
		test[1][0].setmedian(10); //Dropped value
		assertTrue(result.length==2);
		//When
		result=SimpleSerch.search( test, "median greater than 15", "test");
		//Then
		assertTrue(result[0].getmedian()==20);
		assertTrue(result.length==1); //proof of record drop
	}
	
	//Test equals to operations
	@Test
	void Searchtest_equalsSymbol() {
		//Given
		test[0][0].setmedian(20); //Returned value
		test[1][0].setmedian(10); //Dropped value
		assertTrue(result.length==2);
		//When
		result=SimpleSerch.search( test, "median = 20", "test");
		//Then
		assertTrue(result[0].getmedian()==20);
		assertTrue(result.length==1); //proof of record drop
	}
	
	@Test
	void Searchtest_equalsWordSet1() {
		//Given
		test[0][0].setmedian(10); //Returned value
		test[1][0].setmedian(20); //Dropped value
		assertTrue(result.length==2);
		//When
		result=SimpleSerch.search( test, "median equal to 20", "test");
		//Then
		assertTrue(result[0].getmedian()==20);
		assertTrue(result.length==1); //proof of record drop
	}
	
	@Test
	void Searchtest_equalsWordSet2() {
		//Given
		test[0][0].setmedian(10); //Returned value
		test[1][0].setmedian(20); //Dropped value
		assertTrue(result.length==2);
		//When
		result=SimpleSerch.search( test, "median equals 20", "test");
		//Then
		assertTrue(result[0].getmedian()==20);
		assertTrue(result.length==1); //proof of record drop
	}
	
	@Test
	void Searchtest_equalsWordSet3() {
		//Given
		test[0][0].setmedian(10); //Returned value
		test[1][0].setmedian(20); //Dropped value
		assertTrue(result.length==2);
		//When
		result=SimpleSerch.search( test, "median equal 20", "test");
		//Then
		assertTrue(result[0].getmedian()==20);
		assertTrue(result.length==1); //proof of record drop
	}

	//Test grater than or equal to operations
	@Test
	void Searchtest_graterthanorequalSymbol() {
		//Given
		test[0][0].setmedian(15); //Returned value
		test[1][0].setmedian(10); //Dropped value
		assertTrue(result.length==2);
		//When
		result=SimpleSerch.search( test, "median >= 15", "test");
		//Then
		assertTrue(result[0].getmedian()==15);
		assertTrue(result.length==1); //proof of record drop
	}
	
	@Test
	void Searchtest_graterthanorequalWord() {
		//Given
		test[0][0].setmedian(15); //Returned value
		test[1][0].setmedian(10); //Dropped value
		assertTrue(result.length==2);
		//When
		result=SimpleSerch.search( test, "median greater than or equal to 15", "test");
		//Then
		assertTrue(result[0].getmedian()==15);
		assertTrue(result.length==1);//proof of record drop
	}
	
	//Test less than or equal to operations
	@Test
	void Searchtest_lessthanorequalSymbol() {
		//Given
		test[0][0].setmedian(15); //Returned value
		test[1][0].setmedian(10); //Returned value
		//When
		result=SimpleSerch.search( test, "median <= 15", "test");
		//Then
		assertTrue(result[0].getmedian()==15);
		assertTrue(result[1].getmedian()==10);
	}
	
	@Test
	void Searchtest_lessthanorequalWord() {
		//Given
		test[0][0].setmedian(15); //Returned value
		test[1][0].setmedian(10); //Returned value
		//When
		result=SimpleSerch.search( test, "median less than or equal to 15", "test");
		//Then
		assertTrue(result[0].getmedian()==15);
		assertTrue(result[1].getmedian()==10);
	}
	
	//Test does not equal operations
	@Test
	void Searchtest_doesnotequalSymbol() {
		//Given
		test[0][0].setmedian(15); //Returned value
		test[1][0].setmedian(10); //Dropped value
		assertTrue(result.length==2);
		//When
		result=SimpleSerch.search( test, "median != 10", "test");
		//Then
		assertTrue(result[0].getmedian()==15);
		assertTrue(result.length==1); //proof of record drop
	}
		
		@Test
		void Searchtest_doesnotequalWord() {
			//Given
			test[0][0].setmedian(15); //Returned value
			test[1][0].setmedian(10); //Dropped value
			assertTrue(result.length==2);
			//When
			result=SimpleSerch.search( test, "median does not equal 10", "test");
			//Then
			assertTrue(result[0].getmedian()==15);
			assertTrue(result.length==1);//proof of record drop
		}
		
		//Test Primary Key attribute search operations
		@Test
		void Searchtest_attributesearchSet1() {
			//Given
			assertTrue(result.length==2);
			//When
			result=SimpleSerch.search( test, "name contains 1", "test");
			//Then
			assertTrue(result[0].getreportId().contentEquals("test1"));
			assertTrue(result.length==1); //proof of record drop
		}
		
		@Test
		void Searchtest_attributesearchSet2() {
			//Given
			assertTrue(result.length==2);
			//When
			result=SimpleSerch.search( test, "name contains the number 1", "test");
			//Then
			assertTrue(result[0].getreportId().contentEquals("test1"));
			assertTrue(result.length==1); //proof of record drop
		}
		
		//Test Mode list operations
		@Test
		void Searchtest_mode() {
			//Given
			test[0][0].setmode(new ArrayList<Double>(Arrays.asList(4.0, 4.0, 5.0, 5.0))); //Returned value
			test[1][0].setmode(new ArrayList<Double>(Arrays.asList(7.0, 7.0, 8.0, 8.0))); //Dropped value
			assertTrue(result.length==2);
			//When
			result=SimpleSerch.search( test, "mode of 5", "test");
			//Then
			assertTrue(result[0].getmode().equals(test[0][0].getmode()));
			assertTrue(result.length==1); //proof of record drop
		}
							//---- End of Testing SimpleSerch.Operaton private method----\\
		
		//Test searchFiles for a no results returned statement
		@Test
		void Searchtest_searchFiles() {
			//Given
			test[0][0].setmode(new ArrayList<Double>(Arrays.asList(3.0, 3.0, 3.0, 3.0))); //Returned value
			test[1][0].setmode(null); //Dropped value due to null
			assertTrue(result.length==2);
			//When
			result=SimpleSerch.search( test, "mode of 5", "test");
			//Then
			assertTrue(result[0].getreportId().equalsIgnoreCase("flag"));
			assertTrue(result.length==1); //proof of record drop
		}
}
