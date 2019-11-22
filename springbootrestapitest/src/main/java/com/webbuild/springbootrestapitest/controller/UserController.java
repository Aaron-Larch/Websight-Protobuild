package com.webbuild.springbootrestapitest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.webbuild.springbootrestapitest.Service.SecurityService;
import com.webbuild.springbootrestapitest.Service.UserService;
import com.webbuild.springbootrestapitest.model.Role;
import com.webbuild.springbootrestapitest.model.User;
import com.webbuild.springbootrestapitest.respository.RoleRepository;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private RoleRepository roleRepository;
    
    User user= new User();

    @GetMapping("/registration")
    public String registration(Model model) {
    	List<Role>Rolelist=roleRepository.findAll();
    	model.addAttribute("userForm", user);
    	model.addAttribute("Rolelist", Rolelist);
        return "jsp/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "jsp/registration";
        }
        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "jsp/login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(HttpServletRequest httpServletRequest, Model model) {
        
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
    	
    	if(role.contains("America")) {
        	model.addAttribute("message", "You are a customer.");
        }else if(role.contains("Europe")){
        	model.addAttribute("message", "You are a Employee.");
        }else if(role.contains("Pacific")){
        	model.addAttribute("message", "You are a manager.");
        }else {
        	model.addAttribute("message", "no value found.");
        }
    	return "jsp/welcome";
    }
}
