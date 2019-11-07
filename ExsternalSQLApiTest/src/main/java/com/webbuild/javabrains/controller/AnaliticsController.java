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
	String[] headders=ExternalConnection.SetSortParamiters();
	ConsoleOutputCapturer runSoftware= new ConsoleOutputCapturer();
	String Name = SpainShippingController.FetchNameValues();
	Reports[] printout;
	ArrayList<Reports> tempOutput = new ArrayList<Reports>();
	int j=0;
	
	//populate a Report object with user choice variables
	@RequestMapping(value = "/BuildRecord/{id}") //web site control statement
	public ModelAndView CreateRecord(@RequestParam("operation") String[] choices, @PathVariable int id ) {
		  ModelAndView model = new ModelAndView("Analitics/Display_Record"); //first load a named .jsp file
		  String placehoder = AnaliticService.BuildRecord(choices, headders[id], id);
		  model.addObject("Message", placehoder);
		  model.addObject("listCategory", headders); //send objects to jsp page
		  return model;
	}
	
	//select new array to analyze
	@RequestMapping(value = "/chosenewdata/{id}") //web site control statement
	public ModelAndView ChooseNewDataset(@PathVariable int id ) {
		  ModelAndView model = new ModelAndView(); //first load a named .jsp file
		  	model.addObject("Information", AnaliticService.getData(id));
			model.addObject("Page", "page2");
			model.addObject("Name", Name);
			model.addObject("id", id);
			model.setViewName("Analitics/Display_Data");
		  return model;
	}
	
	//create a new object group
	@RequestMapping(value = "/createnewrec") //web site control statement
	public ModelAndView CreateNewRec() {
		Name=null;
		AnaliticService.UpdateRecord();
		return new ModelAndView("redirect:/Shipping/home");
	}

	//close out creating record objects and move on to the search engine
	@RequestMapping(value = "/closerecords") //web site control statement
	public ModelAndView CloseRecords() {
		ModelAndView model = new ModelAndView(); //first load a named .jsp file
		AnaliticService.CloseRecord();
		return SendPackage(model, " ");
	}

	//Perform one of several type of search operations
	@RequestMapping(value = "/SearchFile") //web site control statement
	public ModelAndView PreformSerch(@RequestParam("input") String input, @RequestParam(value = "Record", required=false) String row) {
		ModelAndView model = new ModelAndView();
		Reports[][] File= AnaliticService.getFile();
	    String[] temp=SimpleSerch.dynamicparse (input);
	    
	    //forgot to select a row to search
	    if(row==null) {
	    	model = SendPackage(model, "You forgot to select witch file you wanted to search through");
	    }
	    
	    //Selected the display all objects button
	    else if(row!=null && input.isEmpty()) {
	    	List<Reports> holdingzone = new ArrayList<Reports>();
	    	for(int i=0;  i<File.length; i++) {
				if(File[i]!=null) {
					int stop=File[i][0].getreportId().indexOf('-');
					String CheckValue=File[i][0].getreportId().substring(0, stop);
					if(CheckValue.equalsIgnoreCase(row) || row.equalsIgnoreCase("all")) {
						for(int ii=0; ii < File[i].length; ii++) {
							if(File[i][ii]!=null) {holdingzone.add(File[i][ii]);}
						}
					}
				}
			}
	    	printout=holdingzone.toArray(new Reports[holdingzone.size()]);
	    	model = PritResult(model);
	    }
	    
	    //Perform a user generated search query
	    else{ 
	    	for(int j=0; j<=temp.length; j++) {
	    		if(j==temp.length || SimpleSerch.SpellCheck(temp[0],3)==true) {
	    			printout=SimpleSerch.search(File, input, row);
			    	
	    			//check input statement for user Error
	    			if(printout[0].getreportId().equalsIgnoreCase("flag")) {
	    				model = SendPackage(model, "Your Search produesd no matching results");
			    		break;
			    	}else if(printout[0].getreportId().equalsIgnoreCase("Incomplete")) {
			    		model = SendPackage(model, input+" Is an Incorect statement that I cannot act upon");
			    		break;
			    	}else {model = PritResult(model);}
	    		}
	    		//Error handling
	    		else {
	    			if(SimpleSerch.SpellCheck(temp[j],j)==false){
	    				model = SendPackage(model, temp[j]+" dose not mach any Words or Numbers that I know");
				    	break;
	    			}
	    		}
	    	}
	    }
		
		return model;
	}
	
	//the user chooses what record objects to turn into graphs and clear saved cash for better performance.
	@RequestMapping(value = "/buildFinalRecord") //web site control statement
	public ModelAndView buildfolder(@RequestParam("choice") String choice) {
		ModelAndView model = new ModelAndView(); //first load a named .jsp file
		if ("add".equalsIgnoreCase(choice)) {
			tempOutput.add(printout[j]);
			j++;
			if(j < printout.length) {return PritResult(model);}
			else {
				j=0;
				AnaliticService.releaseresources();
				return new ModelAndView("redirect:/Stats/chart");
			}
		}else if ("discard".equalsIgnoreCase(choice)){
			j++;
			if(j < printout.length) {return PritResult(model);}
			else {
				j=0;
				AnaliticService.releaseresources();
				return new ModelAndView("redirect:/Stats/chart");
			}
		}else {return null;}
	}
	
	//gather all the data needed to make a graph out of the record object
	@RequestMapping(value = "/chart") //web site control statement
	public ModelAndView chartbuild() {
		ModelAndView model = new ModelAndView(); //first load a named .jsp file
		Reports[] statement  = tempOutput.toArray(new Reports[tempOutput.size()]);
		model=AnaliticService.DisplayPage(model,statement,j);
		model.setViewName("Analitics/Display_Chart");
		return model;
	}
	
	//stored print file
	private ModelAndView PritResult(ModelAndView model) {
	 	runSoftware.start();
	 	printout[j].showRecord();
	 	String printOutputValue=runSoftware.stop();
		
		//print output
	 	model.addObject("Page", "page2");
	 	model.addObject("NumHits", printout.length-j);
	 	model.addObject("Message", printOutputValue);
	 	model.setViewName("Analitics/Display_Table");
	 	return model;
	}
	
	//stored search result file
	private ModelAndView SendPackage(ModelAndView model, String Error) { 
		model.addObject("Page", "page1");
		model.addObject("PopUp", Arrays.toString(AnaliticService.getObjInfo()));
		model.addObject("Record", AnaliticService.getFile());
		model.addObject("Result", Error);
		model.setViewName("Analitics/Display_Table");
		return model;
	}
}
