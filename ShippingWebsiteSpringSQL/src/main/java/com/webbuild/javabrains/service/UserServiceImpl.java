package com.webbuild.javabrains.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webbuild.javabrains.model.Role;
import com.webbuild.javabrains.model.User;
import com.webbuild.javabrains.repository.RoleRepository;
import com.webbuild.javabrains.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {
	@Autowired //call the user table in the database
    private UserRepository userRepository;
    
	@Autowired //call the role table in the database
    private RoleRepository roleRepository;
    
    @Autowired //create a new encryption token
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    Set<Role> Security= new HashSet<Role>(); //Create a new role list

    @Override //Create a new user and role pair
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Security.add(roleRepository.findById(user.getRoleid()).get()); //call a single role that matches the user input 
        user.setRoles(Security);
        userRepository.save(user);
    }

    //SELCET * FROM USER WHERE username= 'input'
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}