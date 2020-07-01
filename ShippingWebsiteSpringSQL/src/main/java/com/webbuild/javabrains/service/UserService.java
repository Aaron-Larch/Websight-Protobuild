package com.webbuild.javabrains.service;

import java.util.List;

import com.webbuild.javabrains.model.Role;
import com.webbuild.javabrains.model.User;
//a Reference method to all user actions and operations
public interface UserService {
    void save(User user);

    User findByUsername(String username);

	List<Role> GetRolls();

	User saveRecord(User usr);
	
	void LoadRecord(User usr);
	
	List<User> findAll();

	User findUserByEmail(String Email);
	
	void createPasswordResetTokenForUser(User user, String token);

	void changeUserPassword(User userForm, String passwordConfirm);

}
