/**
 * 
 */
package javaDemo.Junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javaDemo.Reports;
import javaDemo.SwitchBoard;

/**
 * @author gce
 *
 */
class SwitchTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final Reports file=new Reports();
	private final double[] array = {16.0, 25.0, 37.0, 29.0, 10.0, 15.0, 13.0, 32.0, 37.0, 5.0, 32.0, 26.0, 31.0, 2.0, 37.0, 0.0, 10.0, 10.0, 35.0, 37.0};
	
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
	 * Test method for {@link javaDemo.SwitchBoard#buildReports(Reports rep, String[] inputary, double[] input)}.
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
	
	// Mode, Min, MaxSortHi, SortLo, Average, Median, Mode, Min, Max
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
	
	/**
	 * Test method for {@link javaDemo.SwitchBoard#JavaInterFaceBuildObject(double[][] input, String name)}.
	 */
	@Test
	void testJavaInterFaceBuildObject_produeseOneObject() {
		
	}
}
