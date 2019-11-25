package com.webbuild.javabrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.webbuild.javabrains.model.User;
import com.webbuild.javabrains.service.UserService;


@Component
public class UserValidator implements Validator {
    @Autowired //call the user table from the database
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        //Check user object to see if the user name is null
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username"); //call Stored error message from the validation resource file
        }
        
        //Check user object to see if user name is already in the data base
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username"); //call Stored error message from the validation resource file
        }

        //Check user object to see if password is null
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) { //Set your password parameters here
            errors.rejectValue("password", "Size.userForm.password"); //call Stored error message from the validation resource file
        }

        //Check user object to see if the password comparison is correct 
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm"); //call Stored error message from the validation resource file
        }
    }
}
