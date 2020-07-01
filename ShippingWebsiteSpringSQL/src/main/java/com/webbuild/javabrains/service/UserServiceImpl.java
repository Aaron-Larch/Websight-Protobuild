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
import com.webbuild.javabrains.model.PasswordResetToken;
import com.webbuild.javabrains.model.Role;
import com.webbuild.javabrains.model.User;
import com.webbuild.javabrains.repository.PasswordResetTokenRepository;
import com.webbuild.javabrains.repository.RoleRepository;
import com.webbuild.javabrains.repository.ShippingRepository;
import com.webbuild.javabrains.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {
	@Autowired //call the user table in the database
    private UserRepository userRepository;
    
	@Autowired //call the role table in the database
    private RoleRepository roleRepository;
    
    @Autowired //create a new encryption token
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired //call data table and all stored functions
	ShippingRepository shippingservice;
    
    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;
    
    Set<Role> Security= new HashSet<Role>(); //Create a new role list
    private static AnaliticService Dataprep = new AnaliticService();

    @Override //Create a new user and role pair
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Security.add(roleRepository.findById(user.getRoleid()).get()); //call a single role that matches the user input 
        user.setRoles(Security);
        userRepository.save(user);
    }
    
    //Save data and Release all used resources
    public User saveRecord(User user) {
    	if(user.getRoleid()==2) {
    		try {
    			if(AnaliticService.getFile()!=null) {
    				byte[] compressed = Store.compress(Dataprep.SavedData());
    				user.setTestcolum(compressed);
    				userRepository.save(user);
    			}
    		} catch (IOException e) {e.printStackTrace();}
        	Dataprep.releaseresources();
    	}
    	//reset all stored variables for security and resource management
    	SpainShippingController.ResetValues();
        return null;
    }
    
    //Load a saved object from the SQL table
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
    
    @Override
    public List<Role> GetRolls(){
    	return roleRepository.findAll();
    }
    
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    //SELCET * FROM USER WHERE Email= 'input'
    @Override
    public User findUserByEmail(String Email) {
        return userRepository.findByUsername(Email);
    }
    
    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
    	PasswordResetToken myToken = new PasswordResetToken(token, user);
    	passwordTokenRepository.save(myToken);
    }
    
    public void changeUserPassword(User user, String password) {
    	User update= userRepository.findByUsername(user.getUsername());
        update.setUsername(user.getUsername());
        update.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(update);
        
    }
}