package com.webbuild.javabrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service //Create and store information in the server log
public class SecurityServiceImpl implements SecurityService{
    @Autowired //call the authentication layer
    private AuthenticationManager authenticationManager;

    @Autowired //call the user details method
    private UserDetailsService userDetailsService;

    //create a log object
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override //Search the log for a user
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        return null;
    }

    @Override //Automate the user log in process
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        //Verify the log in information has a match in the Database
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //If found go to the next page in path
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", username)); //log recored added
        }
    }
    
    public String FindAuthentication(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString(); //set Authentication to string
    	return role;
    }
    
    public String FindUserName(){
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //set Authentication to string
    	return username;
    }
}