package com.webbuild.javabrains.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

	//Create objects required to add a new user
	@RequestMapping("/HomePage")
    public ModelAndView  HomeLab() {
		ModelAndView model = new ModelAndView();
		double[] array={450, 414, 520, 460, 450, 500, 480, 480, 410, 500, 480, 510};
		List<String> imageUrlList = new ArrayList<>(Arrays.asList());  
		File imageDir = new File("C:\\Users\\gce\\eclipse-workbook\\ShippingWebsiteSpringSQL\\src\\main\\webapp\\ImageAssets");  
		for(File imageFile : imageDir.listFiles()){  
			String imageFileName = imageFile.getName();  
			imageUrlList.add(imageFileName); // add this images name to the list we are building up  
		}  
		
		model.addObject("imageUrlList", imageUrlList);
    	model.addObject("Data", array);
    	model.addObject("test", "Products and services we offer."); //create new user object
    	model.setViewName("Index");
        return model;  //go to jsp page
    }
    
  //update a single object
	@RequestMapping(value = {"/TestPage"}, method=RequestMethod.POST) //a way to make two pages run off of the same method
  	public ModelAndView TestMail(@ModelAttribute("testVal") String input) {
  		ModelAndView model = new ModelAndView();
  		System.out.println("hello");
  		double[] array={450, 414, 520, 460, 450, 500, 480, 480, 410, 500, 480, 510};
    	model.addObject("Data", array);
    	model.addObject("test", input); //create new user object
    	model.setViewName("Index");
        return model;  //go to jsp page 
  	}
}//This Is a work Bench Page that is used for testing code