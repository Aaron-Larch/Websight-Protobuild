package com.webbuild.javabrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.webbuild.javabrains.jdbc.ExternalConnection;
import com.webbuild.javabrains.service.AnaliticService;

@Controller
@RequestMapping(value = "/Stats")
public class AnaliticsController {
	String[] headders=ExternalConnection.SetSortParamiters();

	
	@RequestMapping(value = "/BuildRecord/{id}") //web site control statement
	public ModelAndView CreateRecord(@RequestParam("operation") String[] choices, @PathVariable int id ) {
		  ModelAndView model = new ModelAndView("Analitics/Display_Record"); //first load a named .jsp file
		  String placehoder = AnaliticService.BuildRecord(choices, headders[id], id);
		  model.addObject("Message", placehoder);
		  model.addObject("listCategory", headders); //send objects to jsp page
		  return model;
	}
	
	@RequestMapping(value = "/chosenewdata/{id}") //web site control statement
	public ModelAndView ChooseNewDataset(@PathVariable int id ) {
		  ModelAndView model = new ModelAndView(); //first load a named .jsp file
		  	model.addObject("Information", AnaliticService.getData(id));
			model.addObject("Page", "page2");
			model.addObject("id", id);
			model.setViewName("Analitics/Display_Data");
		  return model;
	}

}
