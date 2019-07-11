package JavaCallFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
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
 *http://localhost:8080/RestApiJerseyTest/FileSort
 */

@WebServlet("/FileSort")
public class FileSort extends HttpServlet{
	public Reports[] resultes;
	ArrayList<Reports> tempOutput = new ArrayList<Reports>();
	public int i=0;
	int count=0;
	String output;
	private static final long serialVersionUID = 1L;
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
			String userchoice = request.getParameter("choice");

			if ("yes".equalsIgnoreCase(userchoice)) {
				tempOutput.add(resultes[i]);
				count++; i++;
				if(i < resultes.length) {PritResult(request, response);}
				else {
					i=0; 
					Reports[] statement = tempOutput.toArray(new Reports[tempOutput.size()]);;
				 	request.getSession().setAttribute("Final", statement);
				 	request.getRequestDispatcher("/WEB-INF/ChartBuild.jsp").forward(request, response);//page b
				 }
			}else if ("no".equalsIgnoreCase(userchoice)){
				i++;
				if(i < resultes.length) {PritResult(request, response);}
				else {
					i=0; 
					Reports[] statement  = tempOutput.toArray(new Reports[tempOutput.size()]);
				 	request.getSession().setAttribute("Final", statement);
				 	request.getRequestDispatcher("/WEB-INF/ChartBuild.jsp").forward(request, response);//page b
				 }
			}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{	
				//response handling
				response.setContentType("text/html");
				
			    //handling request
			    String file = request.getParameter("Record");
			    String req = request.getParameter("input");
			    String ObjectId = request.getParameter("data");
			    
			    //Format Variables 
			    Reports[][] box = (Reports[][]) request.getSession().getAttribute(ObjectId);
			    request.getSession().removeAttribute(ObjectId); 
			    
			    //Perform operations
			    resultes=SwitchBoard.search(box, req, file);
			    
			    if(resultes[0].getreportId().equalsIgnoreCase("flag")) {
			    	String Object = UUID.randomUUID().toString();
				 	request.getSession().setAttribute(Object, box);
				 	request.setAttribute("mailbox", Object);
				 	request.setAttribute("Page", "page2");
			    	request.setAttribute("Record", box);
			    	request.setAttribute("Result", "Your Search produesd no matching results");
			    	request.getRequestDispatcher("/WEB-INF/SearchFile.jsp").forward(request, response);//page a	
			    }
			    else {PritResult(request, response);}
	}
	
	private void PritResult(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Create a stream to hold the output
	 	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 	PrintStream ps = new PrintStream(baos);
	 	// IMPORTANT: Save the old System.out!
	 	PrintStream old = System.out;
	 	// Tell Java to use your special stream
	 	System.setOut(ps);
	 	// Print some output: goes to your special stream
	 	resultes[i].showRecord();
		// Put things back
		System.out.flush();
		System.setOut(old);
		
		//print output
		request.setAttribute("Page", "page3");
		request.setAttribute("NumHits", resultes.length-i);
		request.setAttribute("Message", baos.toString());
		request.getRequestDispatcher("/WEB-INF/SearchFile.jsp").forward(request, response);//page a
	}
}
