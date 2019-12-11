package com.webbuild.javabrains.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.webbuild.javabrains.model.Role;
import com.webbuild.javabrains.model.User;
import com.webbuild.javabrains.repository.RoleRepository;
import com.webbuild.javabrains.service.SecurityService;
import com.webbuild.javabrains.service.UserService;


@Controller
public class UserController {
    @Autowired //Call the user database
    private UserService userService;
    
    @Autowired //Call the role database
    private RoleRepository roleRepository;

    @Autowired //call the security methods
    private SecurityService securityService;

    @Autowired //call the validation methods
    private UserValidator userValidator;

    //Create objects required to add a new user
    @GetMapping("/registration")
    public String registration(Model model) {
    	List<Role>Rolelist=roleRepository.findAll(); //set roles to a list
    	model.addAttribute("userForm", new User()); //create new user object
    	model.addAttribute("Rolelist", Rolelist);
        return "UserInterFace/registration";  //go to jsp page
    }

    //Verify that collected data meets the required security parameters before saving to database
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult); //Check Object for errors

        if (bindingResult.hasErrors()) {
            return "UserInterFace/registration"; //If errors found retun to page with error message
        }
        userService.save(userForm); //if no errors found save

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm()); //Auto login after successful save

        return "redirect:/welcome";  //go to jsp page
    }

    //Check Token for flag values to prompt the user
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) { //Message for login error
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null) { //Message for confirmed log out
        	//shippingservice.updateTable();
        	SpainShippingController.ResetValues();
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "UserInterFace/login";
    }

    //Use the user roles to determine what to load in the user home page 
    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        //Get authentication Data from the server to determine the current users role 
    	for(Role i:userService.GetRolls()) {
    		if(FindAuthentication().contains(i.getDIVISIONNAME())){
    			return "redirect:/Shipping/"+i.getDIVISIONNAME(); //load all user information
    		}
    	 }
    	return null;
    }
    
    public static String FindAuthentication(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString(); //set Authentication to string
    	return role;
    }
}
