package com.webbuild.javabrains.service;

import com.webbuild.javabrains.model.User;
//a Reference method to all user actions and operations
public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
