package com.webbuild.springbootrestapitest.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webbuild.springbootrestapitest.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}