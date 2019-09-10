/**
 * 
 */
package JavaCallFile;

import java.io.IOException;
import java.util.ArrayList;
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
 *http://localhost:8181/Websight-Protobuild/
 *
 */
@WebServlet("/SetNameServlet")
public class SetNameServlet extends HttpServlet{
	//set global variables
	private static ConsoleOutputCapturer runSoftware= new ConsoleOutputCapturer();
	private static BuildPath CallApplet=new BuildPath();
	public Reports[][] box=new Reports[3][]; //by making this a global value it can store all the collected in one place
	private static int i=0;
	private static final long serialVersionUID = 1L;
	
	//this method is for when the user exits the build path before max data is achieved.
	//works just like the else statement but runs as it's own method
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		String Exit="Report Compiled. Files stored for futher operation";
		tableBuild(Exit, request, response);
 		}	
	
	//This Java servelt is used to generate data for the user to work with in lue of a database with meaningful data
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{	
		//raw data needed to make a multdimentonal array
		int[] length = {26,35,20,15,25};
		int[] random = {50,45,40,35,30};
		String Error = "The Array is full. No more data can be stored here.";//end message to notify the use that the program can not hold any more data
		boolean newFile=true;//A path variable to tell later parts of the program when to start creating new objects for an array
		Reports[] rep=new Reports[15];//declare a neew array to stor objects in. the size is arbitrary right now
		
		//response handling
		response.setContentType("text/html");
		
	    //handling request
	    String name= request.getParameter("FileInput");//Retrieve the user generated name from the server
	   
	    /*in case of the user wants to change the name a flag value is set to control the counting variable i
	     * if the flag value is 1 then the user wants to rename the record
	     * if the flag value is 0 the user wants to create a new record*/
	    int flagValue = Integer.parseInt(request.getParameter("flagValue"));//Retrieve the flag path value from the server
	    
	    //Perform operations using the Console capture method to convert java operations into a format than can be sent to the server.
		runSoftware.start();//start recording console out put
		double[][] array=CallApplet.arrayBoxInt(length, random, name); //use the a method from the Javademo package in the SQLLinkFile to generate the data
		String printOutputValue=runSoftware.stop();//stop recording console out put and set output to string
	 	
	 	//Display the randomly generated arrays for the user 
	 	if(i<box.length) {//establish a check to see if the program can store more data if yes continue if no exit this part of the softwere
	 		//Initiate the object that will be used in the second half of the program
	 		i=i-flagValue; //impament the flag value to control witch possession of the array your using at anyone time
		 	box[i]=rep;//store a group of objects into the array at possession i
		 	
		 	//use this method to turn an object into a string and send it to a server in a format that the server can understand.
		 	String ObjectId = UUID.randomUUID().toString();
	 		request.getSession().setAttribute(ObjectId, rep);//the .getSession() makes a variable able to be used by other servelts without needing to go through a jsp page first
	 		
	 		request.setAttribute("Record", ObjectId);//send object to server
	 		request.getSession().setAttribute("Data", array);//send data to server
	 		request.getSession().setAttribute("Name", name);//send user data to server
	 		request.setAttribute("Message", printOutputValue);//Send console print stament to server
	 		request.setAttribute("Page", "page1, 0");//send flag value to server
	 		request.setAttribute("Count", newFile);//send flag to server
	 		request.getRequestDispatcher("/WEB-INF/DisplayPage.jsp").forward(request, response);//go to the DisplayPage.jsp page
	 		i++;//Increase the count value to signal the creation of a new array of objects
	 	}
	 	//to prevent errors from putting data in an array that is full the else statement will instead force the user out to the second part of this program
	 	else {tableBuild(Error, request, response);}
	}
	
	/*The second part of the program is to create a dynamic table of objects the the user can then query to build custom reports
	 * the table will be group each array of objects by the primary key value generated by the user and contain a row or column showing each object
	 * Each object must contain a link that shows the contents of the object
	 *Because all of the user generated data is set to a class variable this method dose not have a data requirement filed to prevent a overloaded
	 *method.  As a result of that choice this method must be private as it's only designed to work with this class
	 */
	private void tableBuild(String Message, HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//Set up variables to send to the server
		String ObjectId = UUID.randomUUID().toString();
 		request.getSession().setAttribute(ObjectId, box);
		ArrayList<String> storage= new ArrayList<String>();
 		
		//every object must be paired with a string that describes the object a nested for loop is the best way to do this
 		for(int j=0; j<box.length; j++) {
 			if(box[j]!=null) { //Filter out empty records to save on memory space
 				for(int k=0; k<box[j].length; k++) {
 					if(box[j][k]!=null) { //Filter out empty records to save on memory space
 						runSoftware.start();
 						box[j][k].showRecord(); //convert the print statement into a string
 						System.out.print("break");
 					 	String printOutputValue=runSoftware.stop();
 					 	storage.add(printOutputValue); //store the string in a list
 					}
 				}
 			}
 		}
 		//lists are a pain to work with in javascript so convert the list to an array for easer conversion on the front end
 		String[] PopUp=storage.toArray(new String[storage.size()]);
 		
 		//send information server side
 		request.getSession().setAttribute("PopUp", PopUp);
 		request.setAttribute("mailbox", ObjectId);
 		request.setAttribute("Page", "page1");
 		request.setAttribute("Record", box);
 		request.setAttribute("Message", Message);
 		request.getRequestDispatcher("/WEB-INF/SearchFile.jsp").forward(request, response); //go to the SearchFile.jsp page
		box=new Reports[3][];//Clear the stored data so the code can be reused
		i=0; //This resets the count function so that the user can start back from the beginning with a clean slate without needing to restart the server		
	}
}
