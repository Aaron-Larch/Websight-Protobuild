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
 * http://localhost:8080/RestApiJerseyTest/
 *
 */
@WebServlet("/BuildRecord")
public class BuildRecord extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static int count;
	Reports[] rep;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		
			String loadValue = request.getParameter("choice");
			String name= (String)request.getSession().getAttribute("Name");
			double[][] array= (double[][])request.getSession().getAttribute("Data");
			boolean newFile=false;
			
			String LoadRecord= "page2, "+loadValue;
			String ObjectId = UUID.randomUUID().toString();
	 		request.getSession().setAttribute(ObjectId, rep);
	 		
	 		request.setAttribute("Record", ObjectId);
	 		request.setAttribute("Data", array);
			request.setAttribute("Name", name);
			request.setAttribute("Page", LoadRecord);
			request.setAttribute("Count", newFile);
			request.getRequestDispatcher("/WEB-INF/DisplayPage.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		double[] arrayinput = null;
		//response handling
		response.setContentType("text/html");
	    //PrintWriter out = response.getWriter();
		
	    //handling request
	    String[] choices = request.getParameterValues("operation");
	    String name = request.getParameter("name");
	    String readValue = request.getParameter("data");
	    int arrValue = Integer.parseInt(request.getParameter("place"));
	    int arrTotal = Integer.parseInt(request.getParameter("length"));
	    boolean check= Boolean.parseBoolean(request.getParameter("reset"));
	    String ObjectId = request.getParameter("file");
	    
	    //Format Variables 
	    rep = (Reports[]) request.getSession().getAttribute(ObjectId);
	    request.getSession().removeAttribute(ObjectId);
	    
	    
	    String[] inputary=readValue.split(" |, |,");
	    if(inputary!=null) {
	    	arrayinput= new double[inputary.length];
	    	for(int i=0;i<inputary.length;i++){
	    	   arrayinput[i]=Double.parseDouble(inputary[i]);
	    	}
	    }else {System.out.println("Input is null");}
	    if(check==true) {count=0;}
	    
	    //Perform operations
	    //Create a stream to hold the output
	 	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    if(count < rep.length) {
	    	rep[count] =  new Reports(); //create new report
	    	rep[count].setreportId(name+"-"+(count+1)+"."+arrValue); //unique flag or primary key for report
	    	
		 	PrintStream ps = new PrintStream(baos);
		 	// IMPORTANT: Save the old System.out!
		 	PrintStream old = System.out;
		 	// Tell Java to use your special stream
		 	System.setOut(ps);
		 	// Print some output: goes to your special stream
		 	SwitchBoard.buildReports(rep[count], choices, arrayinput);//populate the Report
		 	// Put things back
		 	System.out.flush();
		 	System.setOut(old);
		 	// Show what happened
		 	
		 	count++;
		 }
	    //String output=baos.toString().replace("\n", "<br>");
	    String output=rep[count-1].getreportId()+"\n"+baos.toString();
		//print output
 		request.setAttribute("Size", arrTotal);
 		request.setAttribute("Name", name);
 		request.setAttribute("Message", output);
 		request.getRequestDispatcher("/WEB-INF/wileLoopTest.jsp").forward(request, response);

	}

}
