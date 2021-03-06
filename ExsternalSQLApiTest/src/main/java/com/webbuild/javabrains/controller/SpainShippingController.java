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


@Controller
@RequestMapping(value = "/Shipping")
public class SpainShippingController {
	private static ConsoleOutputCapturer runSoftware= new ConsoleOutputCapturer();
	public String pageflag="home";
	static String Namesave;
	static double[][] array;
	
	@Autowired //call data table and all stored functions
	ShippingRepository shippingservice;
	
	//table information for BA home page
	@RequestMapping(value = {"/home", "/{id}"}) //web site control statement
	public ModelAndView getAllTableObjects(@PathVariable(required=false) String id) {
		  ModelAndView model = new ModelAndView("orders_list"); //first load a named .jsp file
		  List<TableObjects> ordersList;
		  String[] headders=ExternalConnection.SetSortParamiters();//the list of key values to sort the table by
		  //check for starting flag
		  if(id==null) {
			  pageflag="Spain";
			  ordersList = shippingservice.getAllOrders("Spain"); //run a default sql  query 
		  } 
		  else{
			  pageflag=id; //save state flag for place keeping
			  ordersList = shippingservice.getAllOrders(id); //run a sql query
		  } 
		  
		  //set object for web page
		  model.addObject("ordersList", ordersList); //send objects to jsp page
		  model.addObject("listCategory", headders); //send objects to jsp page
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
			model.setViewName("Update_Table"); //call a new jsp page to load the objects into
		}
		
		return model;
	}
	
	//user starts on this url
	@RequestMapping(value = "/addneworder")
	public ModelAndView addTablePage() {
		//need to add Select MAX(OrderID) from Orders + 1 declare as global maybe?
		ModelAndView model = new ModelAndView(); //start by reading information on the starting page
		TableObjects article = new TableObjects(); //generate new object for table
		model.addObject("order", article);
		model.setViewName("Add_New_Order"); //call a new jsp page to load the objects into
		return model;
	}
	
	//update a single object
	@RequestMapping(value = {"/tableUpdate", "/tableUpdate/{id}"}, method=RequestMethod.POST) //a way to make two pages run off of the same method
	public ModelAndView updateTableObjects(@ModelAttribute("order") TableObjects Topic, @PathVariable(required=false) String id) {
		//check for a flag value to see if which query the user wants to run
		if(id==null) {shippingservice.addOrder(Topic);} //run a sql insert query 
		else{shippingservice.updateOrder(Topic, id);} //run a sql update query
		return new ModelAndView("redirect:/Shipping/"+pageflag); //load a previous page command
	}
	
	//delete object
	@RequestMapping(value = "/SpainDelete/{id}")
	public ModelAndView deleteTableObjects(@PathVariable String id) {
		ModelAndView model = new ModelAndView(); //start by reading information on the starting page
		TableObjects temp = shippingservice.getOrders(id);
		
		//a form of user error handling to prevent error
		if(temp==null) {
			 model.addObject("update", "No such Id found"); //failure message
			 return new ModelAndView("redirect:/Shipping/"+pageflag); //load a previous page command
		}else {
			shippingservice.deleteOrder(id); //run a sql query
			model.addObject("update", "Record has been deleted"); //success message
			return new ModelAndView("redirect:/Shipping/"+pageflag); //load a previous page command
		}
	}
	
	//Secret command to commit table to the main database
	@RequestMapping(value = "/Spain/push", method=RequestMethod.PUT)
	public void PushtoTable(ModelMap model) {
		shippingservice.updateTable();	
	}
	
	//request mapping for models/pop-up windows 
	@RequestMapping(value = "/switchup", method=RequestMethod.POST)
	public ModelAndView SwitchControlers(@RequestParam("cn") String cn, @RequestParam("Product") String product) {
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
		return model;
	}
	
	//Collect the the processed data and send it over to another Controller file 
	public static double[][] FetchValues(){return (array);}
	public static String FetchNameValues(){return Namesave;}

}
