package com.webbuild.springbootrestapitest.Service;

import com.webbuild.springbootrestapitest.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
