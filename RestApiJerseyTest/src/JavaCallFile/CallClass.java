/**
 * 
 */
package JavaCallFile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

//import javax.ws.rs.*;
import javaDemo.BuildPath;
import javaDemo.SimpleSerch;
import javaDemo.systemTest;

/**
 * @author gce
 *Scrap code file for testing methods and other ideas
 */
public class CallClass {
	private static BuildPath CallApplet=new BuildPath();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println("hello World");
		String someItems= "report id contains the number 5";
		String[] field=new String[3];
		field=SimpleSerch.dynamicparse(someItems);
		System.out.println(Arrays.toString(field));
		systemTest.Export();
	}
	
	public void ChannelChanger() {
		//raw data
		int[] length = {26,35,20,15,25};
		int[] random = {50,45,40,35,30};
		//PrintWriter out = response.getWriter();
		//Create a stream to hold the output
	 	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 	PrintStream ps = new PrintStream(baos);
	 	// IMPORTANT: Save the old System.out!
	 	PrintStream old = System.out;
	 	// Tell Java to use your special stream
	 	System.setOut(ps);
	 	// Print some output: goes to your special stream
	 	CallApplet.arrayBoxInt(length, random, "test");
	 	// Put things back
	 	System.out.flush();
	 	System.setOut(old);
	 	// Show what happened
	 	System.out.println(baos.toString());
	 	System.out.println(baos.toString().replace("\n", System.getProperty("line.separator")));
	 	//out.write(file+"<br>");
	}
}