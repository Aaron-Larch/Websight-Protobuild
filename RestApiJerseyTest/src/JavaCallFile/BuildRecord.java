/**
 * 
 */
package JavaCallFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaDemo.Reports;
import javaDemo.SwitchBoard;


/**
 * @author gce
 * http://localhost:8181/RestApiJerseyTest/
 *
 */
@WebServlet("/BuildRecord")
public class BuildRecord extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static int count;
	Reports[] rep;
	
	//take the user back to the displaypage.jsp page but in the pages second state so the user can make a second entry into the array
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		
			String loadValue = request.getParameter("choice");//the page will load an array from a set of arrays this is the indicator for that choice 
			String name= (String)request.getSession().getAttribute("Name");
			double[][] array= (double[][])request.getSession().getAttribute("Data"); //turn the data back into java data
			boolean newFile=false;//flag value to prevent new files being created
			
			String LoadRecord= "page2, "+loadValue; // set up the string sequence  to tell the jsp page how to load
			String ObjectId = UUID.randomUUID().toString();
	 		request.getSession().setAttribute(ObjectId, rep);
	 		
	 		request.setAttribute("Record", ObjectId);
	 		request.setAttribute("Data", array);
			request.setAttribute("Name", name);
			request.setAttribute("Page", LoadRecord);
			request.setAttribute("Count", newFile);
			request.getRequestDispatcher("/WEB-INF/DisplayPage.jsp").forward(request, response);
	}
	
	/*This servlet will take all the user generated choices and perform back end operations based on these choices
	 * this servlet will control the populate record function turning the input into statistical operations
	 * */
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		double[] arrayinput = null;
		//response handling
		response.setContentType("text/html");
		
	    //handling request
	    String[] choices = request.getParameterValues("operation"); //String contains all of the users chosen operations
	    String name = request.getParameter("name");//the user generated name for the primary key
	    String readValue = request.getParameter("data");//the array that we are going to perform Statistical operations with
	    int arrValue = Integer.parseInt(request.getParameter("place"));//the value to designate witch array out of the all stored arrays
	    int arrTotal = Integer.parseInt(request.getParameter("length")); //the total number of possible arrays for the drop down menu on the server side
	    boolean check= Boolean.parseBoolean(request.getParameter("reset"));//Flag value to reset the array back to the first value to 0 for new file
	    String ObjectId = request.getParameter("file");
	    
	    //Format Variables 
	    rep = (Reports[]) request.getSession().getAttribute(ObjectId);
	    request.getSession().removeAttribute(ObjectId);
	    
	    //all server side variables are strings you need to convert the string into an array of double values
	    String[] inputary=readValue.split(" |, |,");
	    if(inputary!=null) {//error handling to track the data during development
	    	arrayinput= new double[inputary.length];
	    	for(int i=0;i<inputary.length;i++){
	    	   arrayinput[i]=Double.parseDouble(inputary[i]);//turn each string value into a double value
	    	}
	    }else {System.out.println("Input is null");}
	    if(check==true) {count=0;}//flag check to reset the array position for a new file
	    
	    //Perform operations
	    //Create a stream to hold the output
	 	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    if(count < rep.length) {
	    	rep[count] =  new Reports(); //create new report
	    	rep[count].setreportId(name+"-"+(count+1)+"."+arrValue); //unique flag or primary key for report
	    	
	    	//convert the java console output into a string to send the information over to the server
		 	PrintStream ps = new PrintStream(baos);
		 	// IMPORTANT: Save the old System.out!
		 	PrintStream old = System.out;
		 	// Tell Java to use your special stream
		 	System.setOut(ps);
		 	// Print some output: goes to your special stream
		 	SwitchBoard.buildReports(rep[count], choices, arrayinput);//populate the Report object
		 	// Put things back
		 	System.out.flush();
		 	System.setOut(old);
		 	// Show what happened
		 	
		 	count++;//Increase the count value to signal the creation of a new object in the array
		 }
	    //String output=baos.toString().replace("\n", "<br>");
	    String output=rep[count-1].getreportId()+"\n"+baos.toString();//the -1 is to make sure you only see the record you just created
		//send print out information to the server
 		request.setAttribute("Size", arrTotal);
 		request.setAttribute("Name", name);
 		request.setAttribute("Message", output);
 		request.getRequestDispatcher("/WEB-INF/wileLoopTest.jsp").forward(request, response); //send all the prossed information back to a new server page

	}

}
