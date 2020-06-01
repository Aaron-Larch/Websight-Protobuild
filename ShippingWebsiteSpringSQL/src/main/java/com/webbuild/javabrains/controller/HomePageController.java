package com.webbuild.javabrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

	//Create objects required to add a new user
    @GetMapping("/HomePage")
    public ModelAndView  HomeLab() {
    	ModelAndView model = new ModelAndView();
    	double[] array={450, 414, 520, 460, 450, 500, 480, 480, 410, 500, 480, 510};
    	model.addObject("Data", array);
    	model.addObject("test", "Hello World"); //create new user object
    	model.setViewName("Index");
        return model;  //go to jsp page
    }
}//This Is a work Bench Page that is used for testing code
