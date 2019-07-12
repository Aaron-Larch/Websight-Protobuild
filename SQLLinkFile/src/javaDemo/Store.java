/**
 * 
 */
package javaDemo;

import java.io.IOException;
import java.util.Random;

/**
 * @author gce
 *
 */
public class Store {
	private static int i;
	private static boolean check;
	private static String Input;
	private static Random rand = new Random();
	
	
	public char symbol() {
		try {
			i=System.in.read(); // get a char
			System.in.skip(i); //dump the buffer
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (char)i;
		}
	
	public String words(){
		//reset stored value to blank
		Input="";
		check=true;
		try {
			do {
				//the askey value for the "enter/return" key is 10
				do { //do...While loop required to set the flag value after the return key is hit a second time
				 i =System.in.read();// get a char
				 if(Input.length()>0 && ((char)i == '\n' | (char)i == '\r')) { 
					 check=false; // set flag after return key is hit
				 }
				} while(((char)i == '\n' | (char)i == '\r') && check!=false); //Ignore the return key by constantly resetting the buffer
				
				Input+=(char)i; //store buffer in a variable
				
			}while(check != false);//exit input loop
			System.in.skip(i);//dump the buffer
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Input.trim();
	}
	
	public int number(){
		//reset stored value to blank
		Input="";
		check=true;
		try {
			do {
				//the askey value for the "enter/return" key is 10
				do { //do...While loop required to set the flag value after the return key is hit a second time
				 i =System.in.read();// get a char
				 if(Input.length()>0 && ((char)i == '\n' | (char)i == '\r')) {
					 check=false; // set flag after return key is hit
				 }
				} while(((char)i == '\n' | (char)i == '\r') && check!=false); //Ignore the return key by constantly resetting the buffer
				
				if(i==' '|| i=='.') {break;} //user error handling integers do not have " " or "."
				Input+=(char)i; //store buffer in a variable
				
			}while(check != false); //exit input loop
			System.in.skip(i); //dump the buffer
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int value = Integer.parseInt(Input.trim()); //turn string into an intager value
		return value;
	}
	
	public double complex(){
		//reset stored value to blank
		Input="";
		check=true;
		try {
			do { 
				//the askey value for the "enter/return" key is 10
				do { //do...While loop required to set the flag value after the return key is hit a second time
				 i =System.in.read();// get a char
				 if(Input.length()>0 && ((char)i == '\n' | (char)i == '\r')) {
					 check=false; // set flag after return key is hit
				 }
				} while(((char)i == '\n' | (char)i == '\r') && check!=false); //Ignore the return key by constantly resetting the buffer
				
				if(i==' ') {break;} //user error handling doubles do not have " " 
				Input+=(char)i; //store buffer in a variable
				
			}while(check != false); //exit input loop
			System.in.skip(i); //dump the buffer
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double value = Double.parseDouble(Input.trim()); //turn string into an double value
		return value;
	}
	
	public float value(){
		//reset stored value to blank
		Input="";
		check=true;
		try {
			do {
				//the askey value for the "enter/return" key is 10
				do { //do...While loop required to set the flag value after the return key is hit a second time
				 i =System.in.read();// get a char
				 if(Input.length()>0 && ((char)i == '\n' | (char)i == '\r')) {
					 check=false; // set flag after return key is hit
				 }
				} while(((char)i == '\n' | (char)i == '\r') && check!=false); //Ignore the return key by constantly resetting the buffer
				
				if(i==' ') {break;} //user error handling float do not have " " 
				Input+=(char)i; //store buffer in a variable
				
			}while(check != false); //exit input loop
			System.in.skip(i); //dump the buffer
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		float value = Float.parseFloat(Input.trim()); //turn string into a float value
		return value;
	}
	
	public int[] rArrayInt(int size, int range) {
		// build an array of random integer values 
		int[] array = new int[Math.abs(size)]; //Use Math.abs() to prevent a negative array length
		for (i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(range);
		}
		return array;
	}

	public double[] rArrayDouble(int size, int range) {
		// build an array of random double values
		double[] array = new double[Math.abs(size)]; //Use Math.abs() to prevent a negative array length
		for (i = 0; i < array.length; i++) {
			array[i] = rand.nextDouble()*range;	//rand.nextDouble() only produces a random value between 0 and 1 multiply by the range to get more diverse results
		}
		return array;
	}
	
	public float[] rArrayFloat(int size, int range) {
		// build an array of random float values
		float[] array = new float[Math.abs(size)]; //Use Math.abs() to prevent a negative array length
		for (i = 0; i < array.length; i++) {
			array[i] = rand.nextFloat()*range; //rand.nextFloat() only produces a random value between 0 and 1 multiply by the range to get more diverse results
		}
		return array;
	}
	
	public String altstyle() throws IOException { //use throw instead of a try catch block
		Input="";  //buffer for input string
		// Read multiple characters 
		while((i=System.in.read())!='\n') {
			Input+=(char)i; //store value
		}
		System.in.skip(i); // dump buffer
		return Input.trim();
	}
}
