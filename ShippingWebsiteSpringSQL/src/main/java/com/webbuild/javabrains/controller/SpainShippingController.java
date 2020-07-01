package com.webbuild.javabrains.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
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
import com.webbuild.javabrains.jdbc.SecondSQLConnection;
import com.webbuild.javabrains.model.Categories;
import com.webbuild.javabrains.model.InvoiceInfo;
import com.webbuild.javabrains.model.OrderDetails;
import com.webbuild.javabrains.model.Products;
import com.webbuild.javabrains.model.Role;
import com.webbuild.javabrains.model.Suppliers;
import com.webbuild.javabrains.model.TableObjects;
import com.webbuild.javabrains.model.User;
import com.webbuild.javabrains.repository.CategoriesRepository;
import com.webbuild.javabrains.repository.OrderDetailsRepository;
import com.webbuild.javabrains.repository.ProductsRepository;
import com.webbuild.javabrains.repository.ShippingRepository;
import com.webbuild.javabrains.repository.SuppliersRepository;
import com.webbuild.javabrains.repository.UserRepository;
import com.webbuild.javabrains.service.SecurityService;


@Controller
@RequestMapping(value = "/Shipping")
public class SpainShippingController {
	//set all global variables
	private static ConsoleOutputCapturer runSoftware= new ConsoleOutputCapturer();
	private int neworder=ExternalConnection.getOrderId();//this should not be here table should handle this
	 
	public static String pageflag="";
	static String Namesave;
	static int range=0;
	static double[][] array;
	List<OrderDetails> discount;
	List<Suppliers> Sellers;
	List<TableObjects> ordersList;
	List<Products> items = new ArrayList<>(Arrays.asList());
	
	@Autowired //call data table and all stored functions
	ShippingRepository shippingservice;
	
	@Autowired //call the security methods
	private SecurityService securityService;
	
	@Autowired //call data table and all stored functions
	CategoriesRepository categoriesservice;
	
	@Autowired //call data table and all stored functions
	ProductsRepository productservice;
	
	@Autowired //call data table and all stored functions
	SuppliersRepository suppliersservice;
	
	@Autowired //call data table and all stored functions
	OrderDetailsRepository orderdetailsservice;
	
	@Autowired //call data table and all stored functions
	UserRepository userservice;
	
	//Set Employee table and homepage information
	@RequestMapping(value = {"/Europe", "/Europe/{id}"}) //web site control statement
	public ModelAndView getManagerPageTableObjects(@PathVariable(required=false) String id) {
		ModelAndView model = new ModelAndView("UserInterFace/welcome"); //first load a named .jsp file
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
	
	//Set User table and homepage information
	@RequestMapping("/America") //web site control statement
	public ModelAndView getUserPageTableObjects() {
		ModelAndView model = new ModelAndView("UserInterFace/welcome"); //first load a named .jsp file
		List<TableObjects> ordersList = null;
		ordersList = shippingservice.getUserTable("GODOS"); //run a default sql  query 
		model.addObject("role", "none"); //Hide All Manager Operations
		//set Global object for web page
		model.addObject("ordersList", ordersList); //send objects to jsp page
		return model; //load page command
	}
	
	//Set Employee table and homepage information
	@RequestMapping(value = {"/Pacific"}) //web site control statement
	public ModelAndView Adminsetup() {
		ModelAndView model = new ModelAndView("UserInterFace/AdminPage");
		Resource resource = new ClassPathResource("/validation.properties");
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			String test=props.getProperty("token.message");
			model.addObject("message", test);
			model.addObject("UserTable", userservice.findAll());
			return model;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addObject("UserTable", userservice.findAll());
		return model;
	}
	
	//Single object call
	@RequestMapping(value = "/SingleObject/{id}")
	public ModelAndView getTableObject(@PathVariable String id) {
		ModelAndView model = new ModelAndView(); //start by reading information on the starting page
		TableObjects temp = shippingservice.getOrders(id); //run a sql query	
		//a form of user error handling to prevent error
		if(temp==null) {//if a non existent id found
			 model.addObject("order", "No such Id found");
		}else {//else proceed as normal
			model.addObject("order", temp);
			model.setViewName("UserInterFace/Update_Table"); //call a new jsp page to load the objects into
		}
		return model;
	}
	
	//walk the user thought the shopping process to minimize user input and user error
	@RequestMapping(value = { "/addneworder", "/addneworder/{id}"})
	public ModelAndView userShopping(@PathVariable(required=false) String id) {
		ModelAndView model = new ModelAndView();//start by reading information on the starting page
		if(id==null) {//then that means this is a new product
			//start by showing a list of all categories of products to minimize data usage
			List<Categories> Storfront=categoriesservice.findAll();//Execute SQL Query
			
			//populate Modal
			model.addObject("Storfront", Storfront);
			model.addObject("Flag", "Step1"); //class display state
			model.setViewName("UserInterFace/Add_New_Order"); //call a new jsp page to load the objects into
		}else if (id.matches("\\d*\\.?\\d+")){//if id is a number then that means the id is a category flag 
			//show a list of all products in a category
			int order=Integer.parseInt(id);//convert input
			//List<Products> Sale= productservice.findByCategoryID(order);//Execute SQL Query
			List<Products> Sale= SecondSQLConnection.CollectProducts(order);//Execute SQL Query
			//populate Modal
			model.addObject("Shop", Sale);
			model.addObject("Flag", "Step2"); //class display state
			model.setViewName("UserInterFace/Add_New_Order"); //call a new jsp page to load the objects into
		}else if(id.matches("\\d*\\.?\\d+")==false){//if id is a word then that means the id is a product flag 
			//Show all available sellers and discounts of a selected product
			items= productservice.findByProductName(id);//Execute SQL product Query
			//declare Temp Variables 
			Sellers = new ArrayList<>(Arrays.asList());
			discount = new ArrayList<>(Arrays.asList());
			
			//populate the sellers and discount objects using the items object as the guiding line so that all the objects line up in final print
			for(Products i:items){
				Sellers.add(suppliersservice.findBySupplierID(i.getSupplierID())); //Execute SQL seller Query
				List<OrderDetails> temp=SecondSQLConnection.order(i.getProductID()); //Execute SQL discount Query
				for(int j=0; j<temp.size(); j++) {
					discount.add(temp.get(j));//one product can have many discount coupons
				}
			}
			
			//check error page flag to determine what error has occurred 
			if(range==1) {//if 1 than order was to large
				range=0;//IMPORTANT! reset flag value or else error message will persist
				model.addObject("error", "The amount requested is larger than what's in stock");//return error message
			}else if(range==2) {//if 2 than order was not a number
				range=0;//IMPORTANT! reset flag value or else error message will persist
				model.addObject("error", "This input is not a number");//return error message
			}//if 0 nothing is wrong
			//populate Modal
			model.addObject("Owner", Sellers);
			model.addObject("Product", items);
			model.addObject("Discnt", discount);
			model.addObject("Flag", "Step3"); //class display state
			model.setViewName("UserInterFace/Add_New_Order");
		}
		
		model.addObject("order", new TableObjects());
		model.addObject("input", new InvoiceInfo());
		return model;
	}
	
	//This is the add new object Controller
	@RequestMapping(value = { "/addneworder/Invoicve"}, method=RequestMethod.POST)
	public ModelAndView addTablePage(@ModelAttribute("input") InvoiceInfo test) {
		ModelAndView model; //start by reading information on the starting page
		TableObjects article = new TableObjects();
		try {
			int Value = Integer.parseInt(test.getAmmount());//convert input
			//check if user amount is grater than the stored amount
			if(Value > items.get(Integer.parseInt(test.getUnits())).getUnitsInStock()) {
					range=1;
					model = new ModelAndView("redirect:/Shipping/addneworder/"+items.get(Integer.parseInt(test.getUnits())).getProductName());
				}else {
					model = new ModelAndView();
					neworder+=1; //create new order id
					double frate;
					if(test.getDiscount()==null) { //if no discount is chosen then calculate with formula a
						frate=Value*items.get(Integer.parseInt(test.getUnits())).getUnitprice();
						article.setFREIGHT(String.valueOf(frate));//total cost calculated
						article.setEMPLOYEEID("8");
					}else {
						//Calculate selected discount with formula b 
						frate=Value*items.get(Integer.parseInt(test.getUnits())).getUnitprice();
						frate=frate*discount.get(Integer.parseInt(test.getDiscount())).getDiscount();
						article.setFREIGHT(String.valueOf(frate));//total cost calculated
						article.setEMPLOYEEID("6");
					}
					//populate user article
					article.setCUSTOMERID("GODOS");
					article.setORDERID(String.valueOf(neworder));
					article.setSHIPVIA("3");
					article.setSHIPNAME(Sellers.get(Integer.parseInt(test.getUnits())).getCompanyName());
					article.setSHIPCOUNTRY(Sellers.get(Integer.parseInt(test.getUnits())).getCountry());
					model.addObject("OrderID", neworder);
					model.setViewName("UserInterFace/Add_New_Order");
				}
				}catch (NumberFormatException e) {
					//check for number value and not null or string or float
					range=2;
					model = new ModelAndView("redirect:/Shipping/addneworder/"+items.get(Integer.parseInt(test.getUnits())).getProductName());
				}
			model.addObject("order", article);
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
	public static void ResetValues(){pageflag="";} //reset stored token values.
	
	//To prevent cross sight scripting a dynamic page redirect is needed
	private ModelAndView premissions() {
		User account=UserController.FetchValues();
		for(Role i:account.getRoles()) {
			if(securityService.FindAuthentication().contains(i.getDIVISIONNAME())){
				return new ModelAndView("redirect:/Shipping/"+i.getDIVISIONNAME()); //Admin Page to be added later
			}	
		}
		return null;
	}
}
