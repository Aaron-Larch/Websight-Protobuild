package com.webbuild.javabrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    static User usr = new User();
    
    //Create objects required to add a new user
    @GetMapping("/registration")
    public String registration(Model model) {
    	model.addAttribute("userForm", new User()); //create new user object
    	model.addAttribute("Rolelist", roleRepository.findAll()); //set roles to a list
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
	public String login(Model model, @RequestParam(value = "attr", required = false) String attr, 
			String error, String logout, String resettoken) {
		if (error != null) { //Message for login error
			model.addAttribute("error", "Your username and password is invalid.");
		}
		if (logout != null) { //Message for confirmed log out
			if(usr != null && usr.getRoleid()==2) {usr=userService.saveRecord(usr);}
			model.addAttribute("message", "You have been logged out successfully.");
		}
		if (resettoken != null) { //Message for password recovery
			model.addAttribute("message", attr);
		}
		return "UserInterFace/login";
	}
    
    //Use the user roles to determine what to load in the user home page 
    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
    	usr=userService.findByUsername(securityService.FindUserName());
        //Get authentication Data from the server to determine the current users role 
    	for(Role i:userService.GetRolls()) {
    		if(securityService.FindAuthentication().contains(i.getDIVISIONNAME())){
    			if(i.getDIVISIONID()==2) {
    				userService.LoadRecord(usr);
    				} //load all save data
    			return "redirect:/Shipping/"+i.getDIVISIONNAME(); //load all user information
    		}
    	 }
    	return null;
    }
    
    public static User FetchValues(){return usr;}
}
