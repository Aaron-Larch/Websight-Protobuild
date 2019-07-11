/**
 * 
 */
package JavaCallFile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javaDemo.BuildPath;
import javaDemo.systemTest;

/**
 * @author gce
 *
 */
public class CallClass {
	private static BuildPath CallApplet=new BuildPath();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println("hello World");
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