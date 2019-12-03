/**
 * 
 */
package com.webbuild.javabrains.javaDemo.Junit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.webbuild.javabrains.Operations.SwitchBoard;
import com.webbuild.javabrains.model.Reports;

/**
 * @author gce
 *
 */
class SwitchTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final Reports file=new Reports();
	private Reports[] filearray;
	
	private final double[] array = {16.0, 25.0, 37.0, 29.0, 10.0, 15.0, 13.0, 32.0, 37.0, 5.0, 32.0, 26.0, 31.0, 2.0, 37.0, 0.0, 10.0, 10.0, 35.0, 37.0};
	private final double[][] darray = {array, array, array};
	
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
		System.setErr(new PrintStream(errContent));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	/**
	 * Test method for {@link com.webbuild.javabrains.Operations.SwitchBoard#buildReports(Reports rep, String[] inputary, double[] input)}.
	 */
	@Test
	void testBuildReports_SortHi() {
		//Given
		String[] choice= {"SortHi"};
		double[] checkValue = {37.0, 37.0, 37.0, 37.0, 35.0, 32.0, 32.0, 31.0, 29.0, 26.0, 25.0, 16.0, 15.0, 13.0, 10.0, 10.0, 10.0, 5.0, 2.0, 0.0};
		
		//When
		SwitchBoard.buildReports(file, choice, array);
		
		//Then
		assertThat(outContent.toString(), is("Your Data was sorted high low and now reads: "+Arrays.toString(checkValue)+"\n"));
		//and
		assertTrue(Arrays.equals(checkValue, file.gethighC()));
	}
	
	@Test
	void testBuildReports_SortLo() {
		//Given
		String[] choice= {"SortLo"};
		double[] checkValue = {0.0, 2.0, 5.0, 10.0, 10.0, 10.0, 13.0, 15.0, 16.0, 25.0, 26.0, 29.0, 31.0, 32.0, 32.0, 35.0, 37.0, 37.0, 37.0, 37.0};
		
		//When
		SwitchBoard.buildReports(file, choice, array);
		
		//Then
		assertThat(outContent.toString(), is("Your Data was sorted low high and now reads: "+Arrays.toString(checkValue)+"\n"));
		//and
		assertTrue(Arrays.equals(checkValue, file.getlowC()));
	}
	
	@Test
	void testBuildReports_Average() {
		//Given
		String[] choice= {"Average"};
		double checkValue = 21.95;
		
		//When
		SwitchBoard.buildReports(file, choice, array);
		
		//Then
		assertThat(outContent.toString(), is("The average of your Data is: 21.9500\r\n"));
		//and
		assertEquals(checkValue, file.getaverage());
	}
	
	@Test
	void testBuildReports_Median() {
		//Given
		String[] choice= {"Median"};
		double checkValue = 25.5;
			
		//When
		SwitchBoard.buildReports(file, choice, array);
			
		//Then
		assertThat(outContent.toString(), is("The median of your Data is: 25.5\r\n"));
		//and
		assertEquals(checkValue, file.getmedian());
	}
	
	@Test
	void testBuildReports_Mode() {
		//Given
		String[] choice= {"Mode"};
		List<Double> checkValue = new ArrayList<Double>();
		checkValue.add(37.0);
		checkValue.add(37.0);
		checkValue.add(37.0);
		checkValue.add(37.0);
		
		//When
		SwitchBoard.buildReports(file, choice, array);
		
		//Then
		assertThat(outContent.toString(), is("The mode(s) of your Data is: " + checkValue + "\n"));
		//and
		assertEquals(checkValue, file.getmode());
	}
	
	@Test
	void testBuildReports_Min() {
		//Given
		String[] choice= {"Min"};
		double checkValue = 0;
		
		//When
		SwitchBoard.buildReports(file, choice, array);
		
		//Then
		assertThat(outContent.toString(), is("The lowest posibule value of your Data is: " + checkValue +"\n"));
		//and
		assertEquals(checkValue, file.getmin());
	}
	
	@Test
	void testBuildReports_Max() {
		//Given
		String[] choice= {"Max"};
		double checkValue = 37.0;
			
		//When
		SwitchBoard.buildReports(file, choice, array);
			
		//Then
		assertThat(outContent.toString(), is("The highest posibule value of your Data is: " + checkValue + "\n"));
		//and
		assertEquals(checkValue, file.getmax());
	}
	
	@Test
	void testBuildReports_Error() {
		//Given
		String[] choice= {"frog"};
		String checkValue = "frog";
			
		//When
		SwitchBoard.buildReports(file, choice, array);
			
		//Then
		assertThat(outContent.toString(), is(checkValue + " appears to be an invalid operation. This report has been reset.\r\n"));
		//and
		assertEquals(0,file.getaverage());
		assertNull(file.gethighC());
		assertNull(file.getlowC());
		assertEquals(0, file.getmax());
		assertEquals(0, file.getmedian());
		assertEquals(0, file.getmin());
		assertNull(file.getmode());
		assertNull(file.getreportId());
	}
	
	// test multiple Mode, Min, MaxSortHi, SortLo, "Max", Median, Average
	@Test
	void testBuildReports_MultipleChoice() {
		//Given
		String[] choice= {"Max", "Average", "Median"};
		double checkValueMax = 37.0;
		double checkValueAverage = 21.95;
		double checkValueMedian = 25.5;

		
		//When
		SwitchBoard.buildReports(file, choice, array);
		
		//Then
		assertThat(outContent.toString(), is("The highest posibule value of your Data is: " + checkValueMax + "\n"
			+"The average of your Data is: 21.9500\r\n"
			+"The median of your Data is: 25.5\r\n"));
		//and
		assertEquals(checkValueAverage, file.getaverage());
		assertEquals(checkValueMax, file.getmax());
		assertEquals(checkValueMedian, file.getmedian());
	}
	
	/**
	 * Test method for {@link com.webbuild.javabrains.Operations.SwitchBoard#JavaInterFaceBuildObject(double[][] input, String name)}.
	 */
	@Test //test paths are n
	void testJavaInterFaceBuildObject_produeseOneObject() {
		//Given
		String input = "Max, Average, Median\n"		// the user select operation
		               + "n\n";		//End Program

		//when
		filearray=WraperClassInput.TestInterFace(darray, "name", new Scanner(input));
		
		//then
		assertEquals("name-1.0", filearray[0].getreportId());
		assertEquals(21.95, filearray[0].getaverage());
		assertEquals(37.0, filearray[0].getmax());
		assertEquals(25.5, filearray[0].getmedian());
	}
	
	@Test //test paths are y/y/n
	void testJavaInterFaceBuildObject_produesemanyObjectSame() {
		//Given
		String input = "Max, Average, Median\n"		// the user select operation
		               + "y\n"
		               //New Object same array
		               +"y\n"
		               +"Max, Average, Median\n"		// the user select operation
		               + "n\n"	;	//End Program
		
		//when
		filearray=WraperClassInput.TestInterFace(darray, "name", new Scanner(input));
		
		//then
		assertEquals("name-1.0", filearray[0].getreportId());
		assertEquals(21.95, filearray[0].getaverage());
		assertEquals(37.0, filearray[0].getmax());
		assertEquals(25.5, filearray[0].getmedian());
		//and
		assertEquals("name-2.0", filearray[1].getreportId());
		assertEquals(21.95, filearray[1].getaverage());
		assertEquals(37.0, filearray[1].getmax());
		assertEquals(25.5, filearray[1].getmedian());
	}
	
	@Test //test paths are n, y/y, y/n, y/end, error(back), error(start)
	void testJavaInterFaceBuildObject_produesemanyObjectDifferent() {
		//Given
		String input = "Max, Average, Median\n"		// the user select operation
		               + "y\n"
		               //New Object same array
		               +"n\n"
		               +"Max, Average, Median\n"		// the user select operation
		               + "n\n"	;	//End Program
		
		//when
		filearray=WraperClassInput.TestInterFace(darray, "name", new Scanner(input));
		
		//then
		assertEquals("name-1.0", filearray[0].getreportId());
		assertEquals(21.95, filearray[0].getaverage());
		assertEquals(37.0, filearray[0].getmax());
		assertEquals(25.5, filearray[0].getmedian());
		//and
		assertEquals("name-2.1", filearray[1].getreportId());
		assertEquals(21.95, filearray[1].getaverage());
		assertEquals(37.0, filearray[1].getmax());
		assertEquals(25.5, filearray[1].getmedian());
	}
	
	@Test //test paths are error handling
	void testJavaInterFaceBuildObject_error() {
		//Given
		String input = "Max, Average, Median\n"		// the user select operation
			           + "end\n"
			           //New Object same array
			           +"Max, Average, Median\n"		// the user select operation
			           + "n\n"	;	//End Program
			
		//when
		filearray=WraperClassInput.TestInterFace(darray, "name", new Scanner(input));
			
		//then
		assertEquals("name-1.0", filearray[0].getreportId());
		assertEquals(21.95, filearray[0].getaverage());
		assertEquals(37.0, filearray[0].getmax());
		assertEquals(25.5, filearray[0].getmedian());
		//and
		assertEquals("name-2.0", filearray[1].getreportId());
		assertEquals(21.95, filearray[1].getaverage());
		assertEquals(37.0, filearray[1].getmax());
		assertEquals(25.5, filearray[1].getmedian());
	}
	
	@Test //test paths error(full)
	void testJavaInterFaceBuildObject_errorfull() {
		//Given
		String input = "Max, Average, Median\n"		// the user select operation
		               + "y\n"
		               //New Object same array
		               +"n\n"
		               +"Max, Average, Median\n"		// the user select operation
		               + "y\n"	//End Program
					   //New Object same array
		               	+"n\n"
        				+"Max, Average, Median\n"		// the user select operation
        				+ "n\n"	;	//End Program
		
		//when
		filearray=WraperClassInput.TestInterFace(darray, "name", new Scanner(input));
		
		//then
		assertThat(outContent.toString(), containsString("Report is at capacity. No more files can be stored. Exiting program."));
	}
}
