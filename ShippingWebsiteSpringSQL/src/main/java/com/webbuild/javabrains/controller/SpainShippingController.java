package com.webbuild.javabrains.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webbuild.javabrains.ConsoleOutputCapturer;
import com.webbuild.javabrains.jdbc.ExternalConnection;
import com.webbuild.javabrains.model.TableObjects;
import com.webbuild.javabrains.repository.ShippingRepository;
import com.webbuild.javabrains.service.SecurityService;


@Controller
@RequestMapping(value = "/Shipping")
public class SpainShippingController {
	//set all global variables
	private static ConsoleOutputCapturer runSoftware= new ConsoleOutputCapturer();
	private int neworder=ExternalConnection.getOrderId();//this should not be here table should handle this
	 
	public static String pageflag="";
	static String Namesave;
	private static String Role;
	static double[][] array;
	
	@Autowired //call data table and all stored functions
	ShippingRepository shippingservice;
	
	 @Autowired //call the security methods
	 private SecurityService securityService;
	
	//User table information home page generator
	@RequestMapping(value = {"/Europe", "/Europe/{id}"}) //web site control statement
	public ModelAndView getManagerPageTableObjects(@PathVariable(required=false) String id) {
		ModelAndView model = new ModelAndView("UserInterFace/welcome"); //first load a named .jsp file
		List<TableObjects> ordersList = null;
		Role="Europe";
		String[] headders=ExternalConnection.SetSortParamiters();//the list of key values to sort the table by
		//check for starting flag
		if(id==null) {
			pageflag="Spain";//Set a default Table position
			ordersList = shippingservice.getAllOrders(pageflag); //run a default sql  query 
		}else{
			pageflag=id; //save state flag for place keeping
			ordersList = shippingservice.getAllOrders(id); //run a sql query
		} 
		//set object for web page
		model.addObject("role", "block"); //send objects to jsp page
		model.addObject("listCategory", headders); //send objects to jsp page

		//set Global object for web page
		model.addObject("ordersList", ordersList); //send objects to jsp page
		return model; //load page command
	}
	
	@RequestMapping("/America") //web site control statement
	public ModelAndView getUserPageTableObjects() {
		ModelAndView model = new ModelAndView("UserInterFace/welcome"); //first load a named .jsp file
		List<TableObjects> ordersList = null;
		Role="America";
		ordersList = shippingservice.getUserTable("ANTON"); //run a default sql  query 
		model.addObject("role", "none"); //Hide All Manager Operations
		pageflag= ordersList.get(0).getSHIPCOUNTRY();
		//set Global object for web page
		model.addObject("ordersList", ordersList); //send objects to jsp page
		return model; //load page command
	}
	
	//Single object call
	@RequestMapping(value = "/SingleObject/{id}")
	public ModelAndView getTableObject(@PathVariable String id) {
		ModelAndView model = new ModelAndView(); //start by reading information on the starting page
		TableObjects temp = shippingservice.getOrders(id); //run a sql query	
		//a form of user error handling to prevent error
		if(temp==null) {
			 model.addObject("order", "No such Id found");
		}else {
			model.addObject("order", temp);
			model.setViewName("UserInterFace/Update_Table"); //call a new jsp page to load the objects into
		}
		
		return model;
	}
	
	//user starts on this url
	@RequestMapping(value = "/addneworder")
	public ModelAndView addTablePage() {
		//need to add Select MAX(OrderID) from Orders + 1 declare as global maybe?
		ModelAndView model = new ModelAndView(); //start by reading information on the starting page
		TableObjects article = new TableObjects(); //generate new object for table
		neworder++;
		model.addObject("order", article);
		model.addObject("OrderID", neworder);
		model.setViewName("UserInterFace/Add_New_Order"); //call a new jsp page to load the objects into
		return model;
	}
	
	//update a single object
	@RequestMapping(value = {"/tableUpdate", "/tableUpdate/{id}"}, method=RequestMethod.POST) //a way to make two pages run off of the same method
	public ModelAndView updateTableObjects(@ModelAttribute("order") TableObjects Topic, @PathVariable(required=false) String id) {
		//check for a flag value to see if which query the user wants to run
		if(id==null) {shippingservice.addOrder(Topic);} //run a sql insert query 
		else{shippingservice.updateOrder(Topic, id);} //run a sql update query
		return premissions(); //load a previous page command
	}
	
	//delete object
	@RequestMapping(value = "/SpainDelete/{id}")
	public ModelAndView deleteTableObjects(@PathVariable String id) {
		ModelAndView model = new ModelAndView(); //start by reading information on the starting page
		TableObjects temp = shippingservice.getOrders(id);
		
		//a form of user error handling to prevent error
		if(temp==null) {
			 model.addObject("update", "No such Id found"); //failure message
			 return premissions(); //load a previous page command
		}else {
			shippingservice.deleteOrder(id); //run a sql query
			model.addObject("update", "Record has been deleted"); //success message
			return premissions(); //load a previous page command
		}
	}
	
	//Secret command to commit table to the main database
	@RequestMapping(value = "/Spain/push", method=RequestMethod.PUT)
	public void PushtoTable(ModelMap model) {
		shippingservice.updateTable(UserController.FetchValues());	
	}
	
	//request mapping for models/pop-up windows 
	@RequestMapping(value = "/switchup")
	public ModelAndView test(@RequestParam("cn") String cn, @RequestParam("Product") String product) {
		if(securityService.FindAuthentication().contains("Europe")) {
			ModelAndView model = new ModelAndView(); //start by reading information on the starting page
			Namesave=cn;  //reset stored user given record
			//Perform operations
			runSoftware.start();
			array=shippingservice.collectdata(product);
			String printOutputValue=runSoftware.stop();

			//set object for web page
			model.addObject("Message", printOutputValue);
			model.addObject("Information", array[0]);
			model.addObject("Name", Namesave);
			model.addObject("Page", "page1");
			model.addObject("id", "0");
					
			model.setViewName("Analitics/Display_Data"); //call a new jsp page to load the objects into
			return model; //load a previous page command;
		}else {return premissions();} //load a previous page command}
		
	}
				
	//Collect the the processed data and send it over to another Controller file 
	public static double[][] FetchValues(){return (array);}
	public static String FetchNameValues(){return Namesave;}
	public static void ResetValues(){pageflag=""; Role="";} //reset stored token values.
	
	//To prevent cross sight scripting a dynamic page redirect is needed
	private ModelAndView premissions() {
		if(Role.equalsIgnoreCase("America")) {
			return new ModelAndView("redirect:/Shipping/"+Role);
		}else if(Role.equalsIgnoreCase("Europe")) {
			return new ModelAndView("redirect:/Shipping/"+Role+"/"+pageflag);
		}else {
			return new ModelAndView("redirect:/Shipping/"+Role+"/"+pageflag); //Admin Page to be added later
		}	
	}

}
