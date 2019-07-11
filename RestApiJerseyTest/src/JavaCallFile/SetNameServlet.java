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

import javaDemo.BuildPath;
import javaDemo.Reports;

/**
 * @author gce
 *http://localhost:8080/RestApiJerseyTest/SetNameServlet
 *
 */
@WebServlet("/SetNameServlet")
public class SetNameServlet extends HttpServlet{
	private static BuildPath CallApplet=new BuildPath();
	public Reports[][] box=new Reports[3][];
	private static int i=0;
	private static final long serialVersionUID = 1L;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
	 	String Exit="Report Compiled. Files stored for futher operation";
		String ObjectId = UUID.randomUUID().toString();
 		request.getSession().setAttribute(ObjectId, box);
 		
 		request.setAttribute("mailbox", ObjectId);
 		request.setAttribute("Page", "page1");
 		request.setAttribute("Record", box);
 		request.setAttribute("Message", Exit);
 		request.getRequestDispatcher("/WEB-INF/SearchFile.jsp").forward(request, response);
		
			}	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{	
		//raw data
		int[] length = {26,35,20,15,25};
		int[] random = {50,45,40,35,30};
		String Error = "The Array is full. No more data can be stored here.";
		boolean newFile=true;
		Reports[] rep=new Reports[15];
		
		//response handling
		response.setContentType("text/html");
		
	    //handling request
	    String name= request.getParameter("FileInput");
	    int flagValue = Integer.parseInt(request.getParameter("flagValue"));
	    
	    //Perform operations
	    //Create a stream to hold the output
	 	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 	PrintStream ps = new PrintStream(baos);
	 	// IMPORTANT: Save the old System.out!
	 	PrintStream old = System.out;
	 	// Tell Java to use your special stream
	 	System.setOut(ps);
	 	// Print some output: goes to your special stream
	 	double[][] array=CallApplet.arrayBoxInt(length, random, name);
	 	// Put things back
	 	System.out.flush();
	 	System.setOut(old);
	 	
	 	// Show what happened
	 	if(i<box.length) {
	 		i=i-flagValue;
		 	box[i]=rep;
		 	String ObjectId = UUID.randomUUID().toString();
	 		request.getSession().setAttribute(ObjectId, rep);
	 		
	 		request.setAttribute("Record", ObjectId);
	 		request.getSession().setAttribute("Data", array);
	 		request.getSession().setAttribute("Name", name);
	 		request.setAttribute("Message", baos.toString());
	 		request.setAttribute("Page", "page1, 0");
	 		request.setAttribute("Count", newFile);
	 		request.getRequestDispatcher("/WEB-INF/DisplayPage.jsp").forward(request, response);
	 		i++;
	 	}else {
		 	String ObjectId = UUID.randomUUID().toString();
	 		request.getSession().setAttribute(ObjectId, box);
	 		
	 		request.setAttribute("mailbox", ObjectId);
	 		request.setAttribute("Page", "page1");
	 		request.setAttribute("Record", box);
	 		request.setAttribute("Message", Error);
	 		request.getRequestDispatcher("/WEB-INF/SearchFile.jsp").forward(request, response);
	 	}
	}
	
}
