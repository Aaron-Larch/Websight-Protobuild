package com.webbuild.javabrains.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webbuild.javabrains.Operations.EmailEngine;
import com.webbuild.javabrains.model.User;
import com.webbuild.javabrains.repository.UserRepository;
import com.webbuild.javabrains.service.SecurityService;
import com.webbuild.javabrains.service.UserService;

@Controller
@RequestMapping(value = "/Account")
public class AdminController {
	
	@Autowired //call data table and all stored functions
	UserRepository userservice;
	
	@Autowired //call data table and all stored functions
	UserService GenerateToken;
	
	@Autowired //call data table and all stored functions
	SecurityService securityservice;
	
    @Autowired //call the validation methods
    private UserValidator userValidator; 
	
	Resource resource = new ClassPathResource("/validation.properties");
	Properties props;
	
	
	@RequestMapping(value = { "/ResetPassword"}, method=RequestMethod.POST)
	public ModelAndView resetPassword(@RequestParam("Email") String UserEmail, HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = userservice.findUserByEmail(UserEmail);
		if (user != null) {
			String token = UUID.randomUUID().toString();
			GenerateToken.createPasswordResetTokenForUser(user, token);
			String appUrl = "https://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			EmailEngine.constructResetTokenEmail(appUrl, request.getLocale(), token, user);
			String test=props.getProperty("message.resetPasswordEmail");
			model.addObject("message", test);
		}else {
			model.addObject("message", props.getProperty("message.userNotFound"));
	    }
	    model.addObject("UserTable", userservice.findAll());
	    return new ModelAndView("redirect:/Shipping/Pacific");
	}
	
	@GetMapping("/user/changePassword")
	public String showChangePasswordPage(RedirectAttributes ra, final Model model, @RequestParam("token") final String token) {
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
			String result = securityservice.validatePasswordResetToken(token);
			if(result != "validToken") {
				String message = props.getProperty("auth.message." + result);
				ra.addAttribute("attr", message);
				return "redirect:/login?resettoken";
			} else {
				User usr=securityservice.passToken(token).getUser();
				model.addAttribute("token", token);
				model.addAttribute("PasswordForm", usr);
				return "UserInterFace/updatePassword";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/login";
		}
	}

    @PostMapping("/user/changePassword")
    public String savePassword(@ModelAttribute("PasswordForm") User userForm, BindingResult bindingResult) {
    	userValidator.validateToken(userForm, bindingResult); //Check Object for errors
    	
        if (bindingResult.hasErrors()) {
            return "UserInterFace/updatePassword"; //If errors found retun to page with error message
        }
        GenerateToken.changeUserPassword(userForm, userForm.getPasswordConfirm()); //if no errors found save
        securityservice.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm()); //Auto login after successful save

        return "redirect:/welcome";  //go to jsp page
    }
	
}
