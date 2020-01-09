package com.webbuild.javabrains.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webbuild.javabrains.Store;
import com.webbuild.javabrains.controller.SpainShippingController;
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
    private static AnaliticService Dataprep = new AnaliticService();

    @Override //Create a new user and role pair
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Security.add(roleRepository.findById(user.getRoleid()).get()); //call a single role that matches the user input 
        user.setRoles(Security);
        userRepository.save(user);
    }
      
    public User saveRecord(User user) {
    	if(user.getRoleid()==2) {
    		try {
    			byte[] compressed = Store.compress(Dataprep.SavedData());
    			user.setTestcolum(compressed);
    			userRepository.save(user);
    		} catch (IOException e) {e.printStackTrace();}
    	}
    	//reset all stored variables for security and resource management
    	Dataprep.releaseresources();
    	SpainShippingController.ResetValues();
        return null;
    }
    
    public void LoadRecord(User user) {
    	if(user.getTestcolum() != null) {
    		byte[] compressed=user.getTestcolum();
			try {
				String decomp = Store.decompress(compressed);
				Dataprep.LoadSavedData(decomp);
			} catch (IOException e) {e.printStackTrace();}		
    	}
    }

    //SELCET * FROM USER WHERE username= 'input'
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public List<Role> GetRolls(){
    	return roleRepository.findAll();
    }
}