package com.webbuild.javabrains.Security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webbuild.javabrains.model.User;
import com.webbuild.javabrains.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository; //call the user data layer

    //call the current user data that is on the server
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    //load all user data
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username); //Find a user
        if (user == null) throw new UsernameNotFoundException(username); //error handling for missing users
        UserPrincipal userPrincipal = new UserPrincipal(user); //set roles and permissions
		
        //return a token
        return new org.springframework.security.core.userdetails
        				.User(user.getUsername(), user.getPassword(), userPrincipal.getAuthorities());
    }
}