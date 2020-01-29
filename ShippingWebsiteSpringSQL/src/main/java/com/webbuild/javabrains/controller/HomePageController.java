package com.webbuild.javabrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

	//Create objects required to add a new user
    @GetMapping("/HomePage")
    public String HomeLab(Model model) {
    	System.out.println("The Code made it to here");
    	model.addAttribute("test", "Hello World"); //create new user object
        return "Index";  //go to jsp page
    }
}
