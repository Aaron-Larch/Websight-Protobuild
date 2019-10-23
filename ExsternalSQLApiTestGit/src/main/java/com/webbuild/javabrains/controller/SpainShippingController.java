package com.webbuild.javabrains.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webbuild.javabrains.model.TableObjects;
import com.webbuild.javabrains.repository.ShippingRepository;



@Controller
@RequestMapping(value = "/Shipping")
public class SpainShippingController {
	
	@Autowired //call data table and all stored functions
	ShippingRepository shippingservice;
	
	@RequestMapping(value = "/spain") //web site control statement
	public ModelAndView getAllTableObjects() {
		  ModelAndView model = new ModelAndView("orders_list"); //first load a a named .jsp file
		  List<TableObjects> ordersList = shippingservice.getAllOrders(); //run a sql query
		  model.addObject("ordersList", ordersList); //send objects to jsp page
		  
		  return model; //load page command
	}
	
	@RequestMapping(value = "/SpainSingleObject/{id}")
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
	
	@RequestMapping(value = "/addneworder")
	public ModelAndView addTablePage() {
		ModelAndView model = new ModelAndView(); //start by reading information on the starting page
		TableObjects article = new TableObjects(); //generate new object for table
		model.addObject("NewOrder", article);
		model.setViewName("Add_New_Order"); //call a new jsp page to load the objects into
		return model;
	}
	
	@RequestMapping(value = {"/tableUpdate", "/tableUpdate/{id}"}, method=RequestMethod.POST) //a way to make two pages run off of the same method
	public ModelAndView updateTableObjects(@ModelAttribute("order") TableObjects Topic, @PathVariable(required=false) String id) {
		//check for a flag value to see if which query the user wants to run
		if(id==null) {shippingservice.addOrder(Topic);} //run a sql insert query 
		else{shippingservice.updateOrder(Topic, id);} //run a sql update query
		return new ModelAndView("redirect:/Shipping/spain"); //load a previous page command
	}
	
	@RequestMapping(value = "/SpainDelete/{id}")
	public ModelAndView deleteTableObjects(@PathVariable String id) {
		ModelAndView model = new ModelAndView(); //start by reading information on the starting page
		TableObjects temp = shippingservice.getOrders(id);
		
		//a form of user error handling to prevent error
		if(temp==null) {
			 model.addObject("update", "No such Id found"); //failure message
			 return new ModelAndView("redirect:/Shipping/spain"); //load a previous page command
		}else {
			shippingservice.deleteOrder(id); //run a sql query
			model.addObject("update", "Record has been deleted"); //success message
			return new ModelAndView("redirect:/Shipping/spain"); //load a previous page command
		}
	}
	
	//Secret command to commit table to the main database
	@RequestMapping(value = "/Spain/push", method=RequestMethod.PUT)
	public void PushtoTable(ModelMap model) {
		shippingservice.updateTable();	
	}
}
