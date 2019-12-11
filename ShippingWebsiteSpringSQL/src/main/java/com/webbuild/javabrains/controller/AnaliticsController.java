package com.webbuild.javabrains.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webbuild.javabrains.ConsoleOutputCapturer;
import com.webbuild.javabrains.Operations.SimpleSerch;
import com.webbuild.javabrains.jdbc.ExternalConnection;
import com.webbuild.javabrains.model.Reports;
import com.webbuild.javabrains.service.AnaliticService;


@Controller
@RequestMapping(value = "/Stats")
public class AnaliticsController {
	//Declare global variables
	String[] headders=ExternalConnection.SetSortParamiters();
	ConsoleOutputCapturer runSoftware= new ConsoleOutputCapturer();
	String Name;
	Reports[] printout;
	ArrayList<Reports> tempOutput = new ArrayList<Reports>();
	int temp;
	int j=0;
	
	//populate a Report object with user choice variables
	@RequestMapping(value = "/BuildRecord/{id}") //web site control statement
	public ModelAndView CreateRecord(@RequestParam("operation") String[] choices, @PathVariable int id ) {
		  ModelAndView model = new ModelAndView("Analitics/Display_Record"); //first load a named .jsp file
		  Name = SpainShippingController.FetchNameValues(); //call name value from Other controller
		  String placehoder = AnaliticService.BuildRecord(choices, headders[id], Name, id); //run program
		  model.addObject("Message", placehoder); //Arsine output to a web variable
		  model.addObject("listCategory", headders); //send objects to jsp page
		  return model;
	}
	
	//select new array to analyze
	@RequestMapping(value = "/chosenewdata/{id}") //web site control statement
	public ModelAndView ChooseNewDataset(@PathVariable int id ) {
		  ModelAndView model = new ModelAndView(); //first load a named .jsp file
		  	
		  	//set object for web page
		  	model.addObject("Information", AnaliticService.getData(id) );
			model.addObject("Page", "page2");
			model.addObject("Name", Name);
			model.addObject("id", id);
			model.setViewName("Analitics/Display_Data");
		  return model;
	}
	
	//create a new object group
	@RequestMapping(value = "/createnewrec") //web site control statement
	public ModelAndView CreateNewRec() {
		//reset all values
		Name=null;
		AnaliticService.UpdateRecord();
		return new ModelAndView("redirect:/Shipping/Europe");
	}

	//close out creating record objects and move on to the search engine
	@RequestMapping(value = "/closerecords") //web site control statement
	public ModelAndView CloseRecords() {
		ModelAndView model = new ModelAndView(); //first load a named .jsp file
		AnaliticService.CloseRecord(); //run program
		return SendPackage(model, " ");  //print method
	}

	//Perform one of several type of search operations
	@RequestMapping(value = "/SearchFile") //web site control statement
	public ModelAndView PreformSerch(@RequestParam("input") String input, @RequestParam(value = "Record", required=false) String row) {
		ModelAndView model = new ModelAndView();
		Reports[][] File= AnaliticService.getFile();
	    String[] temp=SimpleSerch.dynamicparse (input);
	    
	    //forgot to select a row to search
	    if(row==null) { //Check to see if the user has set the minimal required values for a search
	    	model = SendPackage(model, "You forgot to select witch file you wanted to search through"); //print method
	    } 
	    //Selected the display all objects button
	    else if(row!=null && input.isEmpty()) {
	    	List<Reports> holdingzone = new ArrayList<Reports>();
	    	for(int i=0;  i<File.length; i++) {	//Search ALL possible object
				if(File[i]!=null) { //collect information to compare to user choice
					int stop=File[i][0].getreportId().indexOf('-');
					String CheckValue=File[i][0].getreportId().substring(0, stop);
					if(CheckValue.equalsIgnoreCase(row) || row.equalsIgnoreCase("all")) {
						for(int ii=0; ii < File[i].length; ii++) {
							if(File[i][ii]!=null) {holdingzone.add(File[i][ii]);} //collect all objects that match requirements
						}
					}
				}
			}
	    	printout=holdingzone.toArray(new Reports[holdingzone.size()]);
	    	model = PritResult(model); //run successful print statement
	    }
	    //Perform a user generated search query
	    else{ 
	    	printout=SimpleSerch.search(File, input, row);    	
	    	//check input statement for user Error
	    	if(SimpleSerch.check(temp[0], 3)) {model = SendPackage(model, printout[0].getreportId());} //run basic print statement
			else {model = PritResult(model);} //run successful print statement
	    }
		return model;
	}
	
	//the user chooses what record objects to turn into graphs and clear saved cash for better performance.
	@RequestMapping(value = "/buildFinalRecord") //web site control statement
	public ModelAndView buildfolder(@RequestParam("choice") String choice) {
		ModelAndView model = new ModelAndView(); //first load a named .jsp file
		if ("add".equalsIgnoreCase(choice)) {
			tempOutput.add(printout[j]); //sore object
			j++;
			if(j < printout.length) {return PritResult(model);}
			else {
				//once last value is added or ignored got to print statement to view graphs
				j=0;
				//AnaliticService.releaseresources(); //clear stored values for better data management
				return new ModelAndView("redirect:/Stats/chart");
			}
		}else if ("discard".equalsIgnoreCase(choice)){	//ignore object
			j++;
			if(j < printout.length) {return PritResult(model);}
			else {
				//once last value is added or ignored got to print statement to view graphs
				j=0;
				//AnaliticService.releaseresources(); //clear stored values for better data management
				return new ModelAndView("redirect:/Stats/chart");  //go to other controller instead of page valuer
			}
		}else {return null;}
	}
	
	//gather all the data needed to make a graph out of the record object
	@RequestMapping(value = "/chart") //web site control statement
	public ModelAndView chartbuild(@RequestParam(value = "action", required=false) String value) {
		ModelAndView model = new ModelAndView(); //first load a named .jsp file
		if(value==null) {temp=0;}
		else { temp=Integer.parseInt(value.trim());}
		Reports[] statement  = tempOutput.toArray(new Reports[tempOutput.size()]);
		//Determine which object to view by keeping track of the array position
		if(j>=0 && j<statement.length) {
			j += temp;
			if(j==-1) {j=statement.length-1;}//make sure the back value can never go below 0
			else if(j==statement.length) {j=0;}//make sure the next value can never go above max stored value
		}
		model=AnaliticService.DisplayPage(model,statement,j); //generate graph data
		model.setViewName("Analitics/Display_Chart"); //go to page
		return model;
	}
	
	//stored print file
	private ModelAndView PritResult(ModelAndView model) {
		//collect new stream
		runSoftware.start();
	 	printout[j].showRecord(); //run program
	 	String printOutputValue=runSoftware.stop();
		
		//page output
	 	model.addObject("Page", "page2");
	 	model.addObject("NumHits", printout.length-j);
	 	model.addObject("Message", printOutputValue);
	 	model.setViewName("Analitics/Display_Table");
	 	return model;
	}
	
	//stored failed search result file
	private ModelAndView SendPackage(ModelAndView model, String Error) { 
		//page output
		model.addObject("Page", "page1");
		model.addObject("PopUp", Arrays.toString(AnaliticService.getObjInfo()));
		model.addObject("Record", AnaliticService.getFile());
		model.addObject("Result", Error); //custom error line leave empty and can also load page basec state
		model.setViewName("Analitics/Display_Table");
		return model;
	}
}