/**
 * 
 */
package JavaCallFile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javaDemo.BuildPath;
import javaDemo.Reports;
import javaDemo.Statistics;
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
		int i = 2;
		double square = Math.pow(i, 4);
		System.out.println(square);
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

	public static void complexOps(Reports[] temp) {
		Map<String, Double> chartinfo= new HashMap<String, Double>();
		if (temp.length==0) {System.out.println("There are no records matching your query");}
		else {
			for(int j=0; j < temp.length; j++) {
				if(temp[j] != null) {
					System.out.println(temp[j].getreportId()+": "+Arrays.toString(temp[j].gethighC()));
					chartinfo=Statistics.SampleVariance(temp[j]);
					chartinfo.putAll(Statistics.Range(temp[j]));
					
				
				}
			}
			Statistics.FrequencyTable(temp);
			//build a mode table across all records 
		}
	}
}