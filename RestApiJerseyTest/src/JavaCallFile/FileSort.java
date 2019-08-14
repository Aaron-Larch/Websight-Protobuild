package JavaCallFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaDemo.Reports;
import javaDemo.SimpleSerch;

/**
 * @author gce
 *http://localhost:8181/RestApiJerseyTest/
 *${jboss.bind.address:127.0.0.1}
 */

@WebServlet("/FileSort")
public class FileSort extends HttpServlet{
	public Reports[] resultes;
	Reports[][] box;
	ArrayList<Reports> tempOutput = new ArrayList<Reports>();
	public int i=0;
	String output;
	private static final long serialVersionUID = 1L;
	 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
			String userchoice = request.getParameter("choice");

			if ("add".equalsIgnoreCase(userchoice)) {
				tempOutput.add(resultes[i]);
				i++;
				if(i < resultes.length) {PritResult(request, response);}
				else {CompileFinalReport(request, response);}
			}else if ("discard".equalsIgnoreCase(userchoice)){
				i++;
				if(i < resultes.length) {PritResult(request, response);}
				else {CompileFinalReport(request, response);}
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
			    box = (Reports[][]) request.getSession().getAttribute(ObjectId);
			    request.getSession().removeAttribute(ObjectId);
			    String[] temp=SimpleSerch.dynamicparse (req);
			    
			    //Perform operations
			    if(file==null) {
			    	SendPackage(request, response, "You forgot to select witch file you wanted to search through");
			    }else { 
			    	for(int j=0; j<=temp.length; j++) {
			    		if(j==temp.length || SimpleSerch.SpellCheck(temp[0],3)==true) {
			    			resultes=SimpleSerch.search(box, req, file);
					    	if(resultes[0].getreportId().equalsIgnoreCase("flag")) {
					    		SendPackage(request, response, "Your Search produesd no matching results");
					    	}else if(resultes[0].getreportId().equalsIgnoreCase("Incomplete")) {
					    		SendPackage(request, response, req+" Is an Incompete statement the I cannot act upon");
					    		break;
					    	}else {PritResult(request, response);}
			    		}else {
			    			if(SimpleSerch.SpellCheck(temp[j],j)==false){
						    	SendPackage(request, response, temp[j]+" dose not mach any Words or Numbers that I know");
						    	break;
			    			}
			    		}
			    	}
			    }
	}
	
	private void PritResult(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConsoleOutputCapturer runSoftware= new ConsoleOutputCapturer();
	 	
	 	runSoftware.start();
	 	resultes[i].showRecord();
	 	String printOutputValue=runSoftware.stop();
		
		//print output
		request.setAttribute("Page", "page3");
		request.setAttribute("NumHits", resultes.length-i);
		request.setAttribute("Message", printOutputValue);
		request.getRequestDispatcher("/WEB-INF/SearchFile.jsp").forward(request, response);//page a
	}
	
	private void SendPackage(HttpServletRequest request, HttpServletResponse response, String Error) 
			throws ServletException, IOException {
		String Object = UUID.randomUUID().toString();
		request.getSession().setAttribute(Object, box);
		request.setAttribute("mailbox", Object);
		request.setAttribute("Page", "page2");
		request.setAttribute("Record", box);
    	request.setAttribute("Result", Error);
    	request.getRequestDispatcher("/WEB-INF/SearchFile.jsp").forward(request, response);//page a	
	}
	
	private void CompileFinalReport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		i=0; 
		Reports[] statement  = tempOutput.toArray(new Reports[tempOutput.size()]);
	 	request.getSession().setAttribute("Final", statement);
		request.getSession().setAttribute("ClearAll", box);
		request.setAttribute("LoadPage", "/PrintFinalData");
	 	request.getRequestDispatcher("/WEB-INF/ChartBuild.jsp").forward(request, response);//page b
	 	tempOutput = new ArrayList<Reports>();
	}
}
