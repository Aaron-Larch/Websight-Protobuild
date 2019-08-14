package javaDemo.Junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javaDemo.Reports;

class ReportsTest {

	final double[] value = {1.0, 2.0, 3.0, 4.0};
	final double number = 12.5;
	final List<Double> list = new ArrayList<Double>();
	final String name = "name";
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}
	
	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	
    @Test
    public void testSetter_setreportId() throws NoSuchFieldException, IllegalAccessException {
       //Given
    	final Reports tstFeild = new Reports();
    	
    	//when
        tstFeild.setreportId(name);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("reportID");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), name);
    }

    @Test
    public void testGetter_getreportId() throws NoSuchFieldException, IllegalAccessException {
        //given
    	final Reports tstFeild = new Reports();
        final Field field = tstFeild.getClass().getDeclaredField("reportID");
        field.setAccessible(true);
        field.set(tstFeild, name);

        //when
        final String result = tstFeild.getreportId();

        //then
        assertEquals(result, name);
    }
    
    @Test
    public void testSetter_sethighC() throws NoSuchFieldException, IllegalAccessException {
    	//Given
    	final Reports tstFeild = new Reports();
    	
    	//when
        tstFeild.sethighC(value);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("highcount");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), value);
    }

    @Test
    public void testGetter_gethighC() throws NoSuchFieldException, IllegalAccessException {
        //given
    	Reports tstFeild = new Reports();
        final Field field = tstFeild.getClass().getDeclaredField("highcount");
        field.setAccessible(true);
        field.set(tstFeild, value);

        //when
        final double[] result = tstFeild.gethighC();

        //then
        assertEquals(result, value);
    }
    
    @Test
    public void testSetter_setlowC() throws NoSuchFieldException, IllegalAccessException {
    	//Given
    	final Reports tstFeild = new Reports();
    	
    	//when
        tstFeild.setlowC(value);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("lowcount");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), value);
    }

    @Test
    public void testGetter_getlowC() throws NoSuchFieldException, IllegalAccessException {
        //given
    	final Reports tstFeild = new Reports();
        final Field field = tstFeild.getClass().getDeclaredField("lowcount");
        field.setAccessible(true);
        field.set(tstFeild, value);

        //when
        final double[] result = tstFeild.getlowC();

        //then
        assertEquals(result, value);
    }
    
    @Test
    public void testSetter_setmax() throws NoSuchFieldException, IllegalAccessException {
    	//Given
    	final Reports tstFeild = new Reports();
    	
    	//when
        tstFeild.setmax(number);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("max");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), number);
    }

    @Test
    public void testGetter_getmax() throws NoSuchFieldException, IllegalAccessException {
        //given
    	final Reports tstFeild = new Reports();
        final Field field = tstFeild.getClass().getDeclaredField("max");
        field.setAccessible(true);
        field.set(tstFeild, number);

        //when
        final double result = tstFeild.getmax();

        //then
        assertEquals(result, number);
    }
    
    @Test
    public void testSetter_setmin() throws NoSuchFieldException, IllegalAccessException {
    	//Given
    	final Reports tstFeild = new Reports();
    	
    	//when
        tstFeild.setmin(number);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("min");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), number);
    }

    @Test
    public void testGetter_getmin() throws NoSuchFieldException, IllegalAccessException {
        //Given
    	final Reports tstFeild = new Reports();
        final Field field = tstFeild.getClass().getDeclaredField("min");
        field.setAccessible(true);
        field.set(tstFeild, number);

        //when
        final double result = tstFeild.getmin();

        //then
        assertEquals(result, number);
    }
    
    @Test
    public void testSetter_setaverage() throws NoSuchFieldException, IllegalAccessException {
    	//Given
    	final Reports tstFeild = new Reports();
    	
    	//when
        tstFeild.setaverage(number);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("ave");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), number);
    }

    @Test
    public void testGetter_getaverage() throws NoSuchFieldException, IllegalAccessException {
        //Given
    	final Reports tstFeild = new Reports();
        final Field field = tstFeild.getClass().getDeclaredField("ave");
        field.setAccessible(true);
        field.set(tstFeild, number);

        //when
        final double result = tstFeild.getaverage();

        //then
        assertEquals(result, number);
    }
    
    @Test
    public void testSetter_setmedian() throws NoSuchFieldException, IllegalAccessException {
    	//Given
    	final Reports tstFeild = new Reports();
    	
    	//when
        tstFeild.setmedian(number);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("mid");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), number);
    }

    @Test
    public void testGetter_getmedian() throws NoSuchFieldException, IllegalAccessException {
        //Given
    	final Reports tstFeild = new Reports();
        final Field field = tstFeild.getClass().getDeclaredField("mid");
        field.setAccessible(true);
        field.set(tstFeild, number);

        //when
        final double result = tstFeild.getmedian();

        //then
        assertEquals(result, number);
    }
    
    @Test
    public void testSetter_setmode() throws NoSuchFieldException, IllegalAccessException {
    	//Given
    	list.add(1.0);
    	list.add(2.0);
    	final Reports tstFeild = new Reports();
    	
    	//when
        tstFeild.setmode(list);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("mode");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), list);
    }

    @Test
    public void testGetter_getmode() throws NoSuchFieldException, IllegalAccessException {
        //Given
    	final Reports tstFeild = new Reports();
        final Field field = tstFeild.getClass().getDeclaredField("mode");
        field.setAccessible(true);
        field.set(tstFeild, list);

        //when
        final List<Double> result = tstFeild.getmode();

        //then
        assertEquals(result, list);
    }
    
    @Test
    void testshowRecord_TestIDOutput() {
		//Given
    	final Reports tstFeild = new Reports();
    	tstFeild.setreportId(name);

        //when
        tstFeild.showRecord();

        //then
        assertThat(outContent.toString(), is("The name given to this record is: "+name+"\n"));
	}
    
    @Test
    void testshowRecord_TestArray1Output() {
		//Given
    	final Reports tstFeild = new Reports();
    	tstFeild.setreportId(name);
    	tstFeild.sethighC(value);
        //when
        tstFeild.showRecord();

        //then
        assertThat(outContent.toString(), is(
        		"The name given to this record is: "+name+"\n"
        		+"your record was sorted high low and now reads: " + Arrays.toString(value)+"\n"
        		));
    }
    
    @Test
    void testshowRecord_TestArray2Output() {
		//Given
    	final Reports tstFeild = new Reports();
    	tstFeild.setreportId(name);
    	tstFeild.setlowC(value);

        //when
        tstFeild.showRecord();

        //then
        assertThat(outContent.toString(), is(
        		"The name given to this record is: "+name+"\n"
        		+"your record was sorted low high and now reads: " + Arrays.toString(value)+"\n"
        		));
    }
    
    @Test
    void testshowRecord_TestAverageOutput() {
		//Given
    	final Reports tstFeild = new Reports();
    	tstFeild.setreportId(name);
    	tstFeild.setaverage(number);

        //when
        tstFeild.showRecord();

        //then
        assertThat(outContent.toString(), is(
        		"The name given to this record is: "+name+"\n"
        		+"the average of your record is: 12.5000 "+"\r\n"
        		));
    }
    
    @Test
    void testshowRecord_TestMedianOutput() {
		//Given
    	final Reports tstFeild = new Reports();
    	tstFeild.setreportId(name);
    	tstFeild.setmedian(number);
    	
    	//when
        tstFeild.showRecord();

        //then
        assertThat(outContent.toString(), is(
        		"The name given to this record is: "+name+"\n"
        		+"the median of your record is: "+number+" \r\n"
        		));
    }
    
    @Test
    void testshowRecord_TestModeOutput() {
		//Given
    	list.add(1.0);
    	list.add(2.0);
    	final Reports tstFeild = new Reports();
    	tstFeild.setreportId(name);
    	tstFeild.setmode(list);

        //when
        tstFeild.showRecord();

        //then
        assertThat(outContent.toString(), is(
        		"The name given to this record is: "+name+"\n"
        		+"the mode(s) of your record is: "+list+"\n"
        		));
    }
    
    @Test
    void testshowRecord_TestMaxOutput() {
		//Given
    	final Reports tstFeild = new Reports();
    	tstFeild.setreportId(name);
    	tstFeild.setmax(number);
    	tstFeild.setmin(0);

        //when
        tstFeild.showRecord();

        //then
        assertThat(outContent.toString(), is(
        		"The name given to this record is: "+name+"\n"
        		+"the highest value of your record is: "+number+"\n"
        		+"the lowest value of your record is: 0.0"+"\n"
        		));
    }
    
    @Test
    void testshowRecord_TestMinOutput() {
		//Given
    	final Reports tstFeild = new Reports();
    	tstFeild.setreportId(name);
    	tstFeild.setmin(0);

        //when
        tstFeild.showRecord();

        //then
        assertThat(outContent.toString(), is("The name given to this record is: "+name+"\n"));
    }
    
    @Test
    void testshowRecord_TestVoidOutput() {
		//Given
    	final Reports tstFeild = new Reports();

        //when
        tstFeild.showRecord();

        //then
        assertThat(outContent.toString(), is("Error: the record dose not Exist."+"\n"));
    }
}
