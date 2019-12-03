package com.webbuild.javabrains.javaDemo.Junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.webbuild.javabrains.model.TableObjects;

class TableObjectTest {
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
	
	//Getter and setter tests for orderId
    @Test
    public void testSetter_setORDERID() throws NoSuchFieldException, IllegalAccessException {
       //Given
    	final TableObjects tstFeild = new TableObjects();
    	
    	//when
        tstFeild.setORDERID(name);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("ORDERID");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), name);
    }

    @Test
    public void testGetter_getORDERID() throws NoSuchFieldException, IllegalAccessException {
        //given
    	final TableObjects tstFeild = new TableObjects();
        final Field field = tstFeild.getClass().getDeclaredField("ORDERID");
        field.setAccessible(true);
        field.set(tstFeild, name);

        //when
        final String result = tstFeild.getORDERID();

        //then
        assertEquals(result, name);
    }
	
	//Getter and setter tests for CUSTOMERID
    @Test
    public void testSetter_setCUSTOMERID() throws NoSuchFieldException, IllegalAccessException {
       //Given
    	final TableObjects tstFeild = new TableObjects();
    	
    	//when
        tstFeild.setCUSTOMERID(name);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("CUSTOMERID");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), name);
    }

    @Test
    public void testGetter_getCUSTOMERID() throws NoSuchFieldException, IllegalAccessException {
        //given
    	final TableObjects tstFeild = new TableObjects();
        final Field field = tstFeild.getClass().getDeclaredField("CUSTOMERID");
        field.setAccessible(true);
        field.set(tstFeild, name);

        //when
        final String result = tstFeild.getCUSTOMERID();

        //then
        assertEquals(result, name);
    }
	
	//Getter and setter tests for EMPLOYEEID
    @Test
    public void testSetter_setEMPLOYEEID() throws NoSuchFieldException, IllegalAccessException {
       //Given
    	final TableObjects tstFeild = new TableObjects();
    	
    	//when
        tstFeild.setEMPLOYEEID(name);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("EMPLOYEEID");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), name);
    }

    @Test
    public void testGetter_getEMPLOYEEID() throws NoSuchFieldException, IllegalAccessException {
        //given
    	final TableObjects tstFeild = new TableObjects();
        final Field field = tstFeild.getClass().getDeclaredField("EMPLOYEEID");
        field.setAccessible(true);
        field.set(tstFeild, name);

        //when
        final String result = tstFeild.getEMPLOYEEID();

        //then
        assertEquals(result, name);
    }
    
	//Getter and setter tests for SHIPVIA
    @Test
    public void testSetter_setSHIPVIA() throws NoSuchFieldException, IllegalAccessException {
       //Given
    	final TableObjects tstFeild = new TableObjects();
    	
    	//when
        tstFeild.setSHIPVIA(name);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("SHIPVIA");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), name);
    }

    @Test
    public void testGetter_getSHIPVIA() throws NoSuchFieldException, IllegalAccessException {
        //given
    	final TableObjects tstFeild = new TableObjects();
        final Field field = tstFeild.getClass().getDeclaredField("SHIPVIA");
        field.setAccessible(true);
        field.set(tstFeild, name);

        //when
        final String result = tstFeild.getSHIPVIA();

        //then
        assertEquals(result, name);
    }
    
    //Getter and setter tests for FREIGHT
    @Test
    public void testSetter_setFREIGHT() throws NoSuchFieldException, IllegalAccessException {
       //Given
    	final TableObjects tstFeild = new TableObjects();
    	
    	//when
        tstFeild.setFREIGHT(name);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("FREIGHT");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), name);
    }

    @Test
    public void testGetter_getFREIGHT() throws NoSuchFieldException, IllegalAccessException {
        //given
    	final TableObjects tstFeild = new TableObjects();
        final Field field = tstFeild.getClass().getDeclaredField("FREIGHT");
        field.setAccessible(true);
        field.set(tstFeild, name);

        //when
        final String result = tstFeild.getFREIGHT();

        //then
        assertEquals(result, name);
    }
    
	//Getter and setter tests for SHIPNAME
    @Test
    public void testSetter_setSHIPNAME() throws NoSuchFieldException, IllegalAccessException {
       //Given
    	final TableObjects tstFeild = new TableObjects();
    	
    	//when
        tstFeild.setSHIPNAME(name);

        //then
        final Field field = tstFeild.getClass().getDeclaredField("SHIPNAME");
        field.setAccessible(true);
        assertEquals(field.get(tstFeild), name);
    }

    @Test
    public void testGetter_getSHIPNAME() throws NoSuchFieldException, IllegalAccessException {
        //given
    	final TableObjects tstFeild = new TableObjects();
        final Field field = tstFeild.getClass().getDeclaredField("SHIPNAME");
        field.setAccessible(true);
        field.set(tstFeild, name);

        //when
        final String result = tstFeild.getSHIPNAME();

        //then
        assertEquals(result, name);
    }
    
  	//Getter and setter tests for SHIPCOUNTRY
      @Test
      public void testSetter_setSHIPCOUNTRY() throws NoSuchFieldException, IllegalAccessException {
         //Given
      	final TableObjects tstFeild = new TableObjects();
      	
      	//when
          tstFeild.setSHIPCOUNTRY(name);

          //then
          final Field field = tstFeild.getClass().getDeclaredField("SHIPCOUNTRY");
          field.setAccessible(true);
          assertEquals(field.get(tstFeild), name);
      }

      @Test
      public void testGetter_getSHIPCOUNTRY() throws NoSuchFieldException, IllegalAccessException {
          //given
      	final TableObjects tstFeild = new TableObjects();
          final Field field = tstFeild.getClass().getDeclaredField("SHIPCOUNTRY");
          field.setAccessible(true);
          field.set(tstFeild, name);

          //when
          final String result = tstFeild.getSHIPCOUNTRY();

          //then
          assertEquals(result, name);
      }
      
      @Test
      public void testtable_gettablA() throws NoSuchFieldException, IllegalAccessException {
          //given
    	  final TableObjects tstFeild;

          //when
    	  tstFeild = new TableObjects(name, name, name, name, name, name, name);

          //then
    	  assertEquals(name, tstFeild.getORDERID());
    	  assertEquals(name,tstFeild.getCUSTOMERID());
    	  assertEquals(name,tstFeild.getEMPLOYEEID());
    	  assertEquals(name,tstFeild.getSHIPVIA());
    	  assertEquals(name,tstFeild.getFREIGHT()); 
    	  assertEquals(name,tstFeild.getSHIPNAME());
    	  assertEquals(name,tstFeild.getSHIPCOUNTRY());
      }
}
